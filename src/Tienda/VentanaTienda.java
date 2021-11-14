package Tienda;

import static javax.swing.WindowConstants.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VentanaTienda {
	static JMenuItem loginItem = new JMenuItem("Login");
	static JMenuItem logoutItem = new JMenuItem("Logout");

	public void InitWindow() {
		Image icon = Toolkit.getDefaultToolkit().getImage("logo.png");
		JFrame frame = new JFrame();
		JMenu menucliente = new JMenu("Area cliente");
		JMenu atcliente = new JMenu("Atencion al cliente");
		JMenuItem chat = new JMenuItem("Chat con un agente");
		atcliente.add(chat);
		JMenuBar bar = new JMenuBar();
		menucliente.add(loginItem);
		logoutItem.setEnabled(false);
		menucliente.add(logoutItem);
		bar.add(menucliente);
		bar.add(atcliente);
		JTabbedPane tabs = new JTabbedPane();
		JPanel elect = new JPanel();
		JPanel main = new JPanel();
		JPanel ropa = new JPanel();
		JPanel videojuego = new JPanel();
		JPanel cesta = new JPanel();
		tabs.add("Principal", new JScrollPane(main));
		tabs.add("Electronica", new JScrollPane(elect));
		tabs.add("Ropa", new JScrollPane(ropa));
		tabs.add("Videojuegos", new JScrollPane(videojuego));
		tabs.add("Cesta", new JScrollPane(cesta));
		frame.add(bar, BorderLayout.NORTH);
		frame.add(tabs);
		chat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Chat.TextChatClient().InitChat("localhost", 6666);

			}
		});

		loginItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new Login().doLogin();

			}
		});

		logoutItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				loginItem.setText("Login");
				loginItem.setEnabled(true);
				logoutItem.setEnabled(false);

			}
		});

		main.paintComponents(null);
		frame.setVisible(true);
		frame.setTitle("Emai");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(1000, 900);
		frame.setIconImage(icon);

	}

	public static void main(String[] args) {
		new VentanaTienda().InitWindow();
	}
}
