package Chat;

import java.io.*;
import java.awt.*;
import javax.swing.*;

import CustomLogger.CustomLogger;
import CustomLogger.LEVEL;

import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.*;

public class TextChat { //Creo todas las variables a usar durante el programa.
	CustomLogger logger = new CustomLogger();
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
		logger.create("chatHost.log"); //Creo el archivo del logger.
		ChatWindow(); //LLamo a la ventana

		try {
			serversocket = new ServerSocket(PORT); //Inicio socket y me quedo escuchando a posibles conexiones
			socket = serversocket.accept(); // Aceptamos la conexi�n e iniciamos el servidor
			chat.append("[SYSTEM]   Connected to " + socket.getInetAddress() + "\n");
			logger.log(LEVEL.INFO, "Connected to " + socket.getInetAddress());
			connected = true;
			// Obtenemos el m�todo de entrada y salida de datos.
			inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			Outputpw = new PrintWriter(socket.getOutputStream(), true);
			while (connected) {
				chat.append("[REMOTO]   " + inputReader.readLine() + "\n");
				logger.log(LEVEL.INFO, "RECEIVED: "+inputReader.readLine());
			}
			while (true) {
				if (!connected) {
					socket.close();
					serversocket.close();
					Outputpw.close();
					inputReader.close();
					logger.log(LEVEL.END, "Connection closed");
					logger.close();
				}
			}
		} catch (IOException ex) {
			logger.log(LEVEL.ERROR, ex.toString());
			logger.log(LEVEL.END, "Couldn�t establish connection");
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
						chat.append("[T�]   " + text.getText() + "\n");
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
		new TextChat().InitChat(6666);
	}
}