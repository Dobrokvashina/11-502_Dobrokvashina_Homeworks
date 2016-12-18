
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Control {
    private boolean shutdown=false;
    public static Control _control;
    ArrayList clientList=new ArrayList();
    Control(){
        _control=this;
        StartServer();
    }
    private void StartServer(){
        try {
            ServerSocket ss=new ServerSocket(1000);
            System.out.println("Server started");
            System.out.println("Connection wait...");
            while(!shutdown){
                Socket incoming=ss.accept();
                System.out.println("Client connected");
                ClientThread client=new ClientThread(incoming);
                clientList.add(client);
                Thread t=new Thread(client);
                t.start();
            }
        } catch (IOException ex) {
            System.out.println("Server internal error "+ex.getMessage());
        }
    }
    public void ShutdownClient(ClientThread c){
        clientList.remove(c);
    }
    public void SendAll(String message){
        for(int i=0;i<clientList.size();i++){
             getClient(i).Send(message);  
        }
    }
    public ClientThread getClient(int index){
        return((ClientThread)clientList.get(index));
    }
}
