/**
 * Created by Саоша on 21.12.2016.
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ClientChat {

    private static final String HOST = "localhost";

    private static final int PORT = 3333;

    private BufferedReader in;

    private PrintWriter out;

    private JFrame jFrame;

    private JTextField textField;

    private JTextArea messageArea;

    public ClientChat() {

        jFrame = new JFrame("Chat");

        textField = new JTextField();
        messageArea = new JTextArea();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.LIGHT_GRAY);
        southPanel.setLayout(new GridBagLayout());

        textField = new JTextField(30);
        textField.requestFocusInWindow();

        JButton sendMessage = new JButton("Send");
        sendMessage.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && textField.getText().length() > 0) {
                    out.println(textField.getText());
                    textField.setText("");
                }
            }
        });

        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (textField.getText().length() > 0) {
                    out.println(textField.getText());
                    textField.setText("");
                }
            }
        });

        sendMessage.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (textField.getText().length() > 0) {
                    out.println(textField.getText());
                    textField.setText("");
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        messageArea = new JTextArea();
        messageArea.setEditable(false);
        messageArea.setLineWrap(true);

        mainPanel.add(new JScrollPane(messageArea), BorderLayout.CENTER);


        southPanel.add(textField);
        southPanel.add(sendMessage);

        mainPanel.add(BorderLayout.SOUTH, southPanel);

        jFrame.add(mainPanel);
        jFrame.pack();
        jFrame.setBounds(500, 500, 500, 500);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        ClientChat client = new ClientChat();

        client.run();
    }

    private void run() throws IOException {

        Socket socket = new Socket(HOST, PORT);
        in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        while (true) {
            String line = in.readLine();
            if (line.startsWith("SUBMITNAME")) {
                String username = getUsername();
                while (username == null){
                    username = getUsername();
                }
                out.println(username);
            } else if (line.startsWith("NAMEACCEPTED")) {
                textField.setEditable(true);
            } else if (line.startsWith("MESSAGE")) {
                messageArea.append(line.substring(8) + "\n");
            }
        }
    }

    private String getUsername() {
        return JOptionPane.showInputDialog(
                jFrame,
                "Enter your username: " +
                        "\n Username must contain only letters, numbers, - and _  " +
                        "\n Between 3 and 16 signs",
                "Choose your username",
                JOptionPane.PLAIN_MESSAGE);
    }
}
