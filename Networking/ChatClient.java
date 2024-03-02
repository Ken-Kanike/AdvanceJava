import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChatClient {

    private static Socket soc;
    private static JTextArea chatArea;
    private static JTextField inputField;
    private static DataInputStream dIn;
    private static DataOutputStream dOut;

    public static void main(String[] args) throws Exception {
        soc = new Socket("localhost", 1254);
        dIn = new DataInputStream(soc.getInputStream());
        dOut = new DataOutputStream(soc.getOutputStream());

        // Create and configure the Swing UI
        JFrame frame = new JFrame("Chat Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        panel.add(new JScrollPane(chatArea), BorderLayout.CENTER);

        inputField = new JTextField();
        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        panel.add(inputField, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);

        // Start a thread to listen for incoming messages
        Thread messageListener = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        String message = dIn.readUTF();
                        appendToChatArea("Server: " + message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        messageListener.start();
    }

    private static void sendMessage() {
        try {
            String message = inputField.getText();
            dOut.writeUTF(message);
            appendToChatArea("Client: " + message);
            inputField.setText("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void appendToChatArea(String message) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                chatArea.append(message + "\n");
            }
        });
    }
}
