package Chat;

import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.awt.event.*;

public class TextChat {
	Locale l = Locale.getDefault();
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

	public void InitChat(int PORT) {

		ChatWindow();

		try {
			serversocket = new ServerSocket(PORT);
			socket = serversocket.accept(); // Aceptamos la conexión e iniciamos el servidor
			chat.append("[SYSTEM]   Connected to " + socket.getInetAddress() + "\n");
			connected = true;
			// Obtenemos el método de entrada y salida de datos.
			inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			Outputpw = new PrintWriter(socket.getOutputStream(), true);
			while (connected) {
				chat.append("[REMOTO]   " + inputReader.readLine() + "\n");
			}
			while (true) {
				if (!connected) {
					socket.close();
					serversocket.close();
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
		new TextChat().InitChat(6666);
	}
}
