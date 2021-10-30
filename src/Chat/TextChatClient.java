package Chat;

import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.*;

public class TextChatClient {
	ServerSocket serversocket = null;
	Socket socket = null;
	InputStream in;
	OutputStream os;
	PrintWriter Outputpw;
	BufferedReader inputReader;
	JFrame frame = new JFrame();
	JTextArea chat = new JTextArea();
	JTextField text = new JTextField();
	static boolean connected;

	public void InitChat(String HOST, int PORT) {

		ChatWindow();

		try {
			socket = new Socket(HOST, PORT);
			chat.append("[SYSTEM]   Connected to " + socket.getInetAddress() + "\n");
			connected = true;
			// Obtenemos el método de entrada de datos e interacción con el exterior
			inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			Outputpw = new PrintWriter(socket.getOutputStream(), true);
			while (connected) {
				chat.append("[REMOTO]   " + inputReader.readLine() + "\n");
			}
			while (true) {
				if (!connected) {
					socket.close();
					Outputpw.close();
					inputReader.close();
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
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
					if (text.getText().equals("!clear")) {
						chat.setText("");
						text.setText("");
					} else if (text.getText().equals("!time")) {
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
						chat.append("Current time: " + dtf.format(LocalDateTime.now()) + "\n");
						text.setText("");
					} else if (text.getText().equals("!close")) {
						frame.dispose();
						Outputpw.println("Connection closed");
						connected = false;
					} else {
						chat.append("[Tú]   " + text.getText() + "\n");
						if (connected) {
							Outputpw.println(text.getText());
						}
						text.setText("");
					}
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
