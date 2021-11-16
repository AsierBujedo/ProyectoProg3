package Tienda;

import static javax.swing.WindowConstants.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Utils.RoundedBorder;

public class VentanaTienda {
	static JMenuItem loginItem = new JMenuItem("Login");
	static JMenuItem logoutItem = new JMenuItem("Logout");
	static JMenu atcliente = new JMenu("Atencion al cliente");
	static JMenuItem personalArea = new JMenuItem("Acceso al area personal");
	public static Image icon = Toolkit.getDefaultToolkit().getImage("logo.png");
	public void InitWindow() {
		
		
		// Inicializamos la ventana
		JFrame frame = new JFrame();
		JMenu menucliente = new JMenu("Area cliente");
		JMenu atcliente = new JMenu("Atencion al cliente");
		personalArea.setBorder(new RoundedBorder(7));
		atcliente.setBorder(new RoundedBorder(7));
		JMenuItem chat = new JMenuItem("Chat con un agente");
		JMenuItem chatServerP = new JMenuItem("Iniciar chat (modo servidor)");
		chat.setBorder(new RoundedBorder(7));
		chatServerP.setBorder(new RoundedBorder(7));
		atcliente.add(chat);
		atcliente.add(chatServerP);
		JMenuBar bar = new JMenuBar();
		loginItem.setBorder(new RoundedBorder(7));
		menucliente.add(loginItem);
		logoutItem.setEnabled(false);
		logoutItem.setBorder(new RoundedBorder(7));
		menucliente.add(logoutItem);
		menucliente.setBorder(new RoundedBorder(7));
		personalArea.setEnabled(false);
		menucliente.add(personalArea);
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
		tabs.add("Tabla Prov.", new JScrollPane(PanelTabla.getPanelTabla()));
		tabs.setBorder(new RoundedBorder(7));
		frame.add(bar, BorderLayout.NORTH);
		frame.add(tabs);
		
		chat.addActionListener(new ActionListener() {	//Accion de chat con un agente

			@Override
			public void actionPerformed(ActionEvent e) {
				Thread hilochat = new Thread(new Runnable() {
					@Override
					public void run() {
						new Chat.TextChatClient().InitChat("localhost", 6666);
					}
				});
				hilochat.start();
			}
		});

		loginItem.addActionListener(new ActionListener() {	//Accion de inicio de sesion

			@Override
			public void actionPerformed(ActionEvent e) {

				new Login().doLogin();

			}
		});

		logoutItem.addActionListener(new ActionListener() {	//Accion de cerrado de sesion

			@Override
			public void actionPerformed(ActionEvent e) {

				loginItem.setText("Login");
				loginItem.setEnabled(true);
				logoutItem.setEnabled(false);
				personalArea.setEnabled(false);

			}
		});
		
		chatServerP.addActionListener(new ActionListener() {  //Accion de chat, modo servidor
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Thread hilochat = new Thread(new Runnable() {
					@Override
					public void run() {
						new Chat.TextChat().InitChat(6666);
					}
				});
				hilochat.start();
				
			}
		});


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
