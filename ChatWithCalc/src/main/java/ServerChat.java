

/**
 * Created by Саоша on 21.12.2016.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServerChat {

    private static final int PORT = 3333;

    private static volatile HashSet<String> usernames = new HashSet<String>();

    private static Set<PrintWriter> printWriters = new HashSet<PrintWriter>();

    public static void main(String[] args) throws Exception {
        System.out.println("The chat server is running.");


        ServerSocket serverSocket = new ServerSocket(PORT);
        try {
            while (true) {
                new Handler(serverSocket.accept()).start();
            }
        } finally {
            serverSocket.close();
        }
    }

    private static class Handler extends Thread {


        private String username;

        private Date date;

        private Calculator calculation;

        private Socket socket;

        private BufferedReader in;

        private PrintWriter out;

        public Handler(Socket socket) {
            date = new Date();
            this.socket = socket;
            calculation = new Calculator();
            System.out.println("New socket was connected");
        }

        public void run() {
            try {

                in = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                while (true) {
                    out.println("SUBMITNAME");
                    username = in.readLine();
                    if (username == null || !isUsernameCorrect(username)) {
                        continue;
                    }

                    if (!usernames.contains(username)) {
                        usernames.add(username);
                        break;
                    }
                }

                System.out.println("<" + username + ">  joined the chat in " + getTime());

                out.println("NAMEACCEPTED");
                printWriters.add(out);

                for (PrintWriter printWriter : printWriters) {
                    printWriter.println("MESSAGE <" + username + ">  joined the chat in " + getTime());
                }

                while (true) {
                    String input = in.readLine();
                    if (input == null) {
                        return;
                    }
                    if (input.charAt(0) == '=') {

                        if(isMathRight(input)) {
                                out.println("MESSAGE " + getTime() + " <SERVER> " + calculation.doMath(input));
                                System.out.println(getTime()
                                        + " <" + username + "> done math");
                            } else {
                                out.println("MESSAGE " + getTime() + " <SERVER> Wrong math =(");
                                System.out.println(getTime()
                                + " <" + username +"> tried to do math");
                            }

                    } else

                    {
                        for (PrintWriter writer : printWriters) {
                            writer.println("MESSAGE " + getTime()
                                    + " <" + username + ">: " + input);
                            System.out.println(getTime()
                                    + " <" + username + "> send a message");
                        }
                    }
                }

            } catch (IOException e) {
                System.out.println("User leaves");
            } finally {
                if (username != null) {
                    usernames.remove(username);
                    for (PrintWriter printWriter : printWriters) {
                        printWriter.println("MESSAGE  <" + username + "> left the chat in " + getTime());
                        System.out.println(" <" + username + "> left the chat in " + getTime());
                    }
                }
                if (out != null) {
                    printWriters.remove(out);
                }
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }

        private static boolean isUsernameCorrect(String username) {
            Pattern pattern = Pattern.compile("[a-zA-Z0-9-_]{3,16}");
            Matcher matcher = pattern.matcher(username);
            return matcher.matches();
        }

        private String getTime() {
            return date.getHours() +" : " + date.getMinutes();
        }

        private static boolean isMathRight(String math) {
            Pattern pattern = Pattern.compile("^=([0-9]+[*/+-])+[0-9]+$");
            Matcher matcher = pattern.matcher(math);
            return matcher.matches();
        }
    }

}
