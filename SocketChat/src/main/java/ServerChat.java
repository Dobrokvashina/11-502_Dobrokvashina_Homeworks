import com.esotericsoftware.kryonet.*;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Саоша on 09.12.2016.
 */
public class ServerChat extends Listener {

    private static Server server;
    private static int udp = 6666 ,
    tcp = 6666;
    private List<Connection>  clients = new LinkedList<Connection>();
    private String[][] names = new String[100][2];
    private int i = 0;


    public static void main(String[] args) {

        System.out.println("Создаем сервер");
        server = new Server();
        server.start();
        server.getKryo().register(Message.class);
        server.getKryo().register(RegisterMessage.class);
        try {
            server.bind(tcp, udp);
        } catch (IOException e) {
            e.printStackTrace();
        }


        server.addListener(new ServerChat());
    }

    @Override
    public void connected(Connection connection) {
        clients.add(connection);
        Message mess = new Message();
        mess.message = "Welcome";
        mess.nickname = "SERVER";
        connection.sendTCP(mess);
    }

    @Override
    public synchronized void received(Connection connection, Object message) {

        if(message instanceof RegisterMessage) {
            connection.setTimeout(50000);
            names[i][0] = "" +connection.getID();
            names[i][1] = ((RegisterMessage) message).name;
            connection.sendTCP(new Message());
            i++;
        } else
            if (message instanceof Message) {
                connection.setTimeout(50000);
                Iterator<Connection> iterator = clients.iterator();
                while(iterator.hasNext()) {
                    iterator.next().sendTCP(message);
                }
            }
    }

    @Override
    public  void disconnected(Connection connection) {
        clients.remove(connection);
    }


}
