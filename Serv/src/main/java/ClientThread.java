

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientThread implements Runnable {
    Socket s;
    private boolean shutdown=false;
    private InputStreamReader inStream;
    private OutputStreamWriter outStream;
    private Scanner scanner;
    private String message;
    private PrintWriter out;
    ClientThread(Socket s){
        this.s=s;
    }

    @Override
    public void run() {

        while(s!=null){                 
                 try {
                        inStream =new InputStreamReader(s.getInputStream(),"UTF8");
                        outStream=new OutputStreamWriter(s.getOutputStream(),"UTF8");
                        scanner=new Scanner(inStream);
                        out=new PrintWriter(outStream,true);
                        out.println("Welcome!");
                        while(!shutdown&&scanner.hasNextLine()){
                            message=scanner.nextLine();
                            Control._control.SendAll(message);
                            System.out.println(message);
                        }
                    } catch (IOException ex) {
                        System.out.println("Error initialization clients streams:  "+ex.getMessage());
                    }finally{
                       try {
                          inStream.close();
                          shutdown=true; 
                          s.close();
                          s=null;
                          Control._control.ShutdownClient(this);
                          System.out.println("Client disconnect");
                        } catch (IOException ex) {
                           System.out.println("Client thread error:  "+ex.getMessage());
                        }

                    }
        }
    }
    public void Send(String s){
        out.println(s);
    }
    
}
