import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientThread implements Runnable {
    private Socket s;
    private boolean shutdown = false;
    private InputStreamReader inStream;
    private OutputStreamWriter outStream;
    private Scanner scanner;
    private String message;
    private PrintWriter out;
    private int result = 0;

    public ClientThread(Socket s) {
        this.s = s;
    }


    public void run() {

        while (s != null) {
            try {
                inStream = new InputStreamReader(s.getInputStream(), "UTF8");
                outStream = new OutputStreamWriter(s.getOutputStream(), "UTF8");
                scanner = new Scanner(inStream);
                out = new PrintWriter(outStream, true);
                out.println("HTTP/1.1 200 OK");
                out.println("Content-type:text/html; Charset:UTF-8");
                String text = "Welcome! <br>" +
                        " <br> Please enter your math here: <br> " +
                        "<form action=\"/\" method=\"get\"> <br>" +
                        "<input name = \"math\" type =\"text\">  <br>" +
                        "<input type=\"submit\"> <br>" ;
                out.println("Content-length:" + (text.length() + 200));
                out.println();
                out.println(text);
                while (!shutdown && scanner.hasNextLine()) {
                    message = scanner.nextLine();

                    int placeStart = message.indexOf("math");
                    if (placeStart != -1) {
                        int placeEnd = message.indexOf(" ", placeStart);
                        char[] math;
                        if (placeEnd != -1)
                            math = message.substring(placeStart + 4, placeEnd).toCharArray();
                        else
                            math = message.substring(placeStart + 4).toCharArray();
                        doMath(math);
                        out.println("Result:" + result);
                    }
                }
                out.flush();
            } catch (IOException ex) {
                System.out.println("Error initialization clients streams:  " + ex.getMessage());
            } finally {
                try {
                    inStream.close();
                    shutdown = true;
                    s.close();
                    s = null;
                    Control._control.ShutdownClient(this);
                    System.out.println("Client disconnect");
                } catch (IOException ex) {
                    System.out.println("Client thread error:  " + ex.getMessage());
                }

            }
        }
    }

//
//    public void Send() {
//        out.println("Result:" + result);
//    }

    void doMath(char[] math) {
        int cur = 0;
        int operation = 1;
        //0-пусто, 1-сложение, 2-вычитание, 3-умножение, 4-деление
        for (int i = 0; i < math.length; i++) {
            switch (math[i]) {
                case '%':
                    if(math[i+1] == '2' && math[i+2] == 'B') {
                    choseOper(operation, cur);
                    cur = 0;
                    operation = 1;
                    } else {
                        if(math[i+1] == '2' && math[i+2] == 'F') {
                            choseOper(operation, cur);
                            cur = 0;
                            operation = 4;
                        }
                    }
                    i=i+2;

                    break;
                case '-':
                    choseOper(operation, cur);
                    cur = 0;
                    operation = 2;
                    break;
                case '*':
                    choseOper(operation, cur);
                    cur = 0;
                    operation = 3;
                    break;
                case '1':
                    cur = cur * 10 + 1;
                    break;
                case '2':
                    cur = cur * 10 + 2;
                    break;
                case '3':
                    cur = cur * 10 + 3;
                    break;
                case '4':
                    cur = cur * 10 + 4;
                    break;
                case '5':
                    cur = cur * 10 + 5;
                    break;
                case '6':
                    cur = cur * 10 + 6;
                    break;
                case '7':
                    cur = cur * 10 + 7;
                    break;
                case '8':
                    cur = cur * 10 + 8;
                    break;
                case '9':
                    cur = cur * 10 + 9;
                    break;
                case '0':
                    cur = cur * 10;
                    break;
            }

        }
        choseOper(operation, cur);

    }

    private void choseOper(int operation, int cur) {
        if (operation != 0)
            switch (operation) {
                case 1:
                    result += cur;
                    break;
                case 2:
                    result -= cur;
                    break;
                case 3:
                    result *= cur;
                    break;
                case 4:
                    result /= cur;
                    break;
            }
    }
}
