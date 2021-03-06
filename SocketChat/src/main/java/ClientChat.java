import com.esotericsoftware.kryonet.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by Саоша on 12.12.2016.
 */
public class ClientChat extends Listener {
    private static Client client;
    private static String ip = "localhost";
    private static int tcp = 6666, udp = 6666;

    private MessageChecker check;

    public static void main(String[] args) {

        System.out.println("Подключаемся к серверу");
        client = new Client();
        client.start();

        client.getUpdateThread();
        client.getKryo().register(Message.class);
        client.getKryo().register(RegisterMessage.class);

        //Запускаем клиент
        //Клиент начинает подключатся к серверу
        try {
            client.connect(5000, ip, tcp, udp);
        } catch (IOException e) {
            System.out.println("Не могу подключиться =(");
        }

        client.addListener(new ClientChat());
        while(true) {
                client.getUpdateThread();
        }


    }


    @Override
    public void received(Connection connection, Object object) {
        if(check == null || !check.isAlive()) {
            check = new MessageChecker(connection);
            check.start();
        }
        if (object instanceof Message) {
            Message mess = (Message) object;
            if(mess.message != null)
            System.out.println(mess.toString());

        }
    }

}

class MessageChecker extends Thread {

    private Message message;
    private RegisterMessage reg;
    private String nick;
    private boolean registered;
    private Connection connection;

    MessageChecker( Connection connection) {
        this.connection = connection;
        registered = false;
    }

    public void run() {
        while(true) {
            if (checkMessage()) {
                if (message != null) {
                    connection.sendTCP(message);
                    message = null;
                } else {
                    if (reg != null) {
                        connection.sendTCP(reg);
                        reg = null;
                    }
                }
            }
        }
    }


    private boolean checkMessage() {
        if (message != null) {
            return true;
        } else {
            if (registered) {
                Scanner scanner = new Scanner(System.in);
                String text = scanner.nextLine();
                if (text != null && text.compareTo("") != 0) {
                    message = new Message();
                    message.nickname = nick;
                    message.message = text;
                    return true;
                } else {
                    return false;
                }
            } else {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Your first message gonna be your nickname:");
                String name = scanner.next();
                if (name != null && name.compareTo("") != 0) {
                    reg = new RegisterMessage();
                    reg.name = name;
                    nick = name;
                    registered = true;
                    return true;
                } else {
                    return false;
                }
            }
        }
    }
}

