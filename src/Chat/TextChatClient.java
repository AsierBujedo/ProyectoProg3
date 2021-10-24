package Chat;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class TextChatClient {
	InputStream in;
	OutputStream os;
	PrintWriter Outputpw;
	BufferedReader inputReader;
	JFrame frame = new JFrame();
	JTextArea chat = new JTextArea();
	JTextField text = new JTextField();
	Socket socket = null;
	boolean connected;

	public void InitChat(String HOST, int PORT) {
		ChatWindow();
		try {
			socket = new Socket(HOST, PORT);
			chat.append("[SYSTEM]   Connected to " + socket.getInetAddress() + "\n");
			connected = true;
			inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			Outputpw = new PrintWriter(socket.getOutputStream(), true);
			while (connected) {
				chat.append("[HOST]   " + inputReader.readLine() + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void ChatWindow() {
		chat.setEditable(false);
		frame.add(new JScrollPane(chat), BorderLayout.CENTER);
		frame.add(text, BorderLayout.SOUTH);

		text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					chat.append("[Tú]   " + text.getText() + "\n");
					if (connected) {
						Outputpw.write(text.getText() + "\n");
					}
					text.setText("");
				}

			}
		});


		frame.setTitle("Chat del juego");
		frame.setSize(300, 350);
		frame.setDefaultCloseOperation(1);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new TextChatClient().InitChat("localhost", 6666);
	}
}
