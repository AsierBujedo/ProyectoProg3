package Chat;

import java.io.*;
import java.awt.*;
import javax.swing.*;
import CustomLogger.*;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.*;

public class TextChatClient {
	CustomLogger logger = new CustomLogger();
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
		logger.create("chatClient.log");
		ChatWindow();

		try {
			socket = new Socket(HOST, PORT);
			chat.append("[SYSTEM]   Connected to " + socket.getInetAddress() + "\n");
			logger.log(LEVEL.INFO, "Connected to " + socket.getInetAddress());
			connected = true;
			// Obtenemos el método de entrada de datos e interacción con el exterior
			inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			Outputpw = new PrintWriter(socket.getOutputStream(), true);
			while (connected) {
				chat.append("[REMOTO]   " + inputReader.readLine() + "\n");
				logger.log(LEVEL.INFO, "RECEIVED: "+inputReader.readLine());
			}
			while (true) {
				if (!connected) {
					socket.close();
					Outputpw.close();
					inputReader.close();
					logger.log(LEVEL.END, "Connection closed");
					logger.close();
				}
			}
		} catch (IOException ex) {
			logger.log(LEVEL.ERROR, ex.toString());
			logger.log(LEVEL.END, "Couldn´t establish connection with host");
			logger.close();
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
						logger.log(LEVEL.INFO, "!clear command issued by user");
						chat.setText("");
						text.setText("");
						logger.log(LEVEL.INFO, "Chat cleared");
					} else if (text.getText().equals("!time")) {
						logger.log(LEVEL.INFO, "!time command issued by user");
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
						chat.append("Current time: " + dtf.format(LocalDateTime.now()) + "\n");
						text.setText("");
						logger.log(LEVEL.INFO, "got the time!");
					} else if (text.getText().equals("!close")) {
						logger.log(LEVEL.INFO, "!close command issued by user");
						frame.dispose();
						Outputpw.println("Connection closed");
						connected = false;
						logger.log(LEVEL.END, "Connection closed");
						logger.close();
						
					} else {
						chat.append("[Tú]   " + text.getText() + "\n");
						if (connected) {
							Outputpw.println(text.getText());
							logger.log(LEVEL.INFO, "SENT: "+ text.getText());
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
		String HOST = JOptionPane.showInputDialog("ENTER HOST", "localhost");
		new TextChatClient().InitChat(HOST, 6666);
	}
}