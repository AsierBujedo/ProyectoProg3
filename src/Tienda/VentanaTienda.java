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
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

import BD.BaseDeDatos;
import Utils.OtherUtils;
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
	public static Logger logger;
	public static BaseDeDatos bd = new BaseDeDatos();

	public void InitWindow() {
		bd.InitDB();
		
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
		JPanel main = new JPanel();
		JPanel elect = new JPanel();
		JPanel ropa = new JPanel();
		JPanel hobby = new JPanel();
		JPanel cesta = new Cesta().panelCesta();
		tabs.add("Principal", new JScrollPane(main));		
		
		// Nombres de la columnas
		String[] nomColumnas = {"Cod_Producto", "Nombre", "Precio", "Marca"};
		// Datos de la tabla
		
		//El siguiente métdodo, y, por tanto, las zonas comentadas no pueden usarse
		//hasta solucionarse el error en BaseDeDatos(29).
		//ArrayList<Producto> productos = bd.getProductos();
		
		// Una colección por cada temática de producto
		ArrayList<DatoParaTabla> prodElectronica = new ArrayList<DatoParaTabla>();
		ArrayList<DatoParaTabla> prodRopa = new ArrayList<DatoParaTabla>();
		ArrayList<DatoParaTabla> prodHobby = new ArrayList<DatoParaTabla>();
		
//		for (Producto p : productos) {
//			if (p instanceof Impresora || p instanceof Ordenador || p instanceof Telefono) {
//				prodElectronica.add(p);
//			}
//			else if (p instanceof Pantalon || p instanceof Sudadera || p instanceof Zapatilla) {
//				prodRopa.add(p);
//			}
//			else if (p instanceof Libro || p instanceof Videoconsola || p instanceof Videojuego) {
//				prodHobby.add(p);
//			}
//		}
		
//		tabs.add("Electronica", new JScrollPane(elect));
		tabs.add("Electronica", new JScrollPane(PanelTabla.getPanelTabla(nomColumnas, prodElectronica)));
		
//		tabs.add("Ropa", new JScrollPane(ropa));
		tabs.add("Ropa", new JScrollPane(PanelTabla.getPanelTabla(nomColumnas, prodRopa)));
		
//		tabs.add("Hobby", new JScrollPane(hobby));
		tabs.add("Hobby", new JScrollPane(PanelTabla.getPanelTabla(nomColumnas, prodHobby)));
		
		tabs.add("Cesta", new JScrollPane(cesta));
		
//		tabs.add("Tabla Prov.", new JScrollPane(PanelTabla.getPanelTabla()));
		
		tabs.setBorder(new RoundedBorder(7));
		frame.add(bar, BorderLayout.NORTH);
		frame.add(tabs);
		
		chat.addActionListener(new ActionListener() { // Accion de chat con un agente

			@Override
			public void actionPerformed(ActionEvent e) {
				Thread hilochat = new Thread(new Runnable() {
					@Override
					public void run() {
						new Chat.TextChatClient().InitChat(OtherUtils.prop.getProperty("CLIENT-IP-DESTINATION"), Integer.valueOf(OtherUtils.prop.getProperty("CLIENT-PORT")));
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
						new Chat.TextChat().InitChat(Integer.valueOf(OtherUtils.prop.getProperty("HOST-PORT")));
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
			public void windowOpened(WindowEvent e) {
				logger.log(Level.INFO, "Abriendo ventana. Abriendo conexión con la base de datos");
			}
		});
		frame.setVisible(true);
		frame.setTitle("Emai");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(Integer.valueOf(OtherUtils.prop.getProperty("WINDOW-WIDTH")), Integer.valueOf(OtherUtils.prop.getProperty("WINDOW-HEIGHT")));
		frame.setIconImage(icon);

	}
	public static void iniciaLog() {
		try {
			logger=Logger.getLogger("logger");
			logger.addHandler(new FileHandler("tiendaLog.xml"));
			}catch(Exception e) {
			}
			logger.log(Level.INFO, "Logger inicializado");
	}

	// Esta es la única clase que cuenta con un método main()
	public static void main(String[] args) {
		iniciaLog();
		OtherUtils.restartProperties();
		new VentanaTienda().InitWindow();
		logger.log(Level.INFO, "Ventana inicializada");

	}
}
