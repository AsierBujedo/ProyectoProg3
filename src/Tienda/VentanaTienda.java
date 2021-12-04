package Tienda;

import static javax.swing.WindowConstants.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

import Utils.RoundedBorder;

/**
 * @author GR08 Clase VentanaTienda, es la clase principal, contiene la ventana
 *         principal. Esta clase cuenta con el método main().
 */
public class VentanaTienda {
	static JMenuItem loginItem = new JMenuItem("Login");
	static JMenuItem logoutItem = new JMenuItem("Logout");
	static JMenu atcliente = new JMenu("Atencion al cliente");
	static JMenuItem personalArea = new JMenuItem("Acceso al area personal");
	public static Image icon = Toolkit.getDefaultToolkit().getImage("logo.png");
	static Logger logger;

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
		JPanel cesta = new Cesta().panelCesta();
		tabs.add("Principal", new JScrollPane(main));
		tabs.add("Electronica", new JScrollPane(elect));
		tabs.add("Ropa", new JScrollPane(ropa));
		tabs.add("Videojuegos", new JScrollPane(videojuego));
		tabs.add("Cesta", new JScrollPane(cesta));
		tabs.add("Tabla Prov.", new JScrollPane(PanelTabla.getPanelTabla()));
		tabs.setBorder(new RoundedBorder(7));
		frame.add(bar, BorderLayout.NORTH);
		frame.add(tabs);
		
		chat.addActionListener(new ActionListener() { // Accion de chat con un agente

			@Override
			public void actionPerformed(ActionEvent e) {
				Thread hilochat = new Thread(new Runnable() {
					@Override
					public void run() {
						new Chat.TextChatClient().InitChat("localhost", 6666);
					}
				});
				logger.log(Level.INFO, "Iniciando chat");
				hilochat.start();
			}
		});
		loginItem.addActionListener(new ActionListener() { // Accion de inicio de sesion

			@Override
			public void actionPerformed(ActionEvent e) {
				new Login().doLogin();
				logger.log(Level.INFO, "Inicio de sesion");

			}
		});

		logoutItem.addActionListener(new ActionListener() { // Accion de cerrado de sesion

			@Override
			public void actionPerformed(ActionEvent e) {

				loginItem.setText("Login");
				loginItem.setEnabled(true);
				logoutItem.setEnabled(false);
				personalArea.setEnabled(false);
				logger.log(Level.INFO, "Cerrado de sesion");

			}
		});

		chatServerP.addActionListener(new ActionListener() { // Accion de chat, modo servidor

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

		tabs.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				System.out.println(e.getKeyCode());
				if (e.getKeyChar() == KeyEvent.VK_L) {
					JOptionPane.showMessageDialog(null, "Cargando datos de prueba...", "Carga de datos", 1);
					try {
						FileInputStream fis = new FileInputStream(new File("datos.dat"));
						ObjectInputStream ois = new ObjectInputStream(fis);
						Cesta.cesta = (ArrayList<Producto>) ois.readObject();
					} catch (IOException | ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						logger.log(Level.SEVERE, "Error al cargar datos");
					}
				}
			}
		
		});
		frame.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				logger.log(Level.INFO, "Cerrando ventana");
			}
		});
		frame.setVisible(true);
		frame.setTitle("Emai");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(1000, 900);
		frame.setIconImage(icon);

	}

	/**
	 * Método main(). Ésta clase es la única que cuenta con uno.
	 */
	public static void main(String[] args) {
		try {
		logger=Logger.getLogger("logger");
		logger.addHandler(new FileHandler("tiendaLog.xml"));
		}catch(Exception e) {
		}
		logger.log(Level.INFO, "Logger inicializado");
		new VentanaTienda().InitWindow();
		logger.log(Level.INFO, "Ventana inicializada");

	}
}
