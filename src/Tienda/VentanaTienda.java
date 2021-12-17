package Tienda;

import static javax.swing.WindowConstants.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
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

	public void InitWindow() {
		BaseDeDatos.InitDB();
		cargaDatos();
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
		tabs.add("Principal", new JScrollPane(main));

		// Nombres de la columnas
		String[] nomColumnas = { "Código de producto", "Nombre", "Precio", "Marca", "Seleccionar" };
		String[] nomColumnasCesta = { "Código", "Nombre", "Precio", "Marca" };
		// Datos de la tabla

		// El siguiente métdodo, y, por tanto, las zonas comentadas no pueden usarse
		// hasta solucionarse el error en BaseDeDatos(29).
		ArrayList<Producto> productos = BaseDeDatos.getProductos();

		// Una colección por cada temática de producto
		ArrayList<DatoParaTabla> prodElectronica = new ArrayList<DatoParaTabla>();
		ArrayList<DatoParaTabla> prodRopa = new ArrayList<DatoParaTabla>();
		ArrayList<DatoParaTabla> prodHobby = new ArrayList<DatoParaTabla>();

		for (Producto p : productos) {
			if (p instanceof Impresora || p instanceof Ordenador || p instanceof Telefono) {
				prodElectronica.add(p);
			} else if (p instanceof Pantalon || p instanceof Sudadera || p instanceof Zapatilla) {
				prodRopa.add(p);
			} else if (p instanceof Libro || p instanceof Videoconsola || p instanceof Videojuego) {
				prodHobby.add(p);
			}
		}

		// -------------------------------------------------- Tab 1, tabElect
		// --------------------------------------------------
		JPanel tabElect = new JPanel();
		tabElect.setLayout(new BorderLayout());

		JPanel botoneraElect = new JPanel();
		botoneraElect.setBackground(Color.WHITE);
		JPanel panelTablaElect = PanelTabla.getPanelTabla(nomColumnas, prodElectronica, new Color(88, 101, 242));
		panelTablaElect.setBackground(Color.WHITE);

		JButton anyadirElect = new JButton("Añadir a la cesta");
		anyadirElect.setFont(new Font("Uni Sans Heavy", Font.BOLD, 15));
		anyadirElect.setForeground(Color.WHITE);
		anyadirElect.setBackground(new Color(88, 101, 242));

		anyadirElect.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				anyadirElect.setBorderPainted(false);
				anyadirElect.setBackground(new Color(88, 101, 242).darker());
			}

			public void mouseExited(MouseEvent evt) {
				anyadirElect.setBackground(new Color(88, 101, 242));
			}
		});

		anyadirElect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		botoneraElect.add(anyadirElect, BorderLayout.CENTER);

		tabElect.add(new JScrollPane(panelTablaElect), BorderLayout.CENTER);
		tabElect.add(botoneraElect, BorderLayout.SOUTH);

		tabs.add("Electronica", tabElect);

		// -------------------------------------------------- Tab 2, tabRopa
		// --------------------------------------------------
		JPanel tabRopa = new JPanel();
		tabRopa.setLayout(new BorderLayout());

		JPanel botoneraRopa = new JPanel();
		botoneraRopa.setBackground(Color.WHITE);
		JPanel panelTablaRopa = PanelTabla.getPanelTabla(nomColumnas, prodRopa, new Color(151, 88, 252));
		panelTablaRopa.setBackground(Color.WHITE);

		JButton anyadirRopa = new JButton("Añadir a la cesta");
		anyadirRopa.setFont(new Font("Uni Sans Heavy", Font.BOLD, 15));
		anyadirRopa.setForeground(Color.WHITE);
		anyadirRopa.setBackground(new Color(151, 88, 252));

		anyadirRopa.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				anyadirRopa.setBorderPainted(false);
				anyadirRopa.setBackground(new Color(151, 88, 252).darker());
			}

			public void mouseExited(MouseEvent evt) {
				anyadirRopa.setBackground(new Color(151, 88, 252));
			}
		});

		anyadirRopa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		botoneraRopa.add(anyadirRopa, BorderLayout.CENTER);

		tabRopa.add(new JScrollPane(panelTablaRopa), BorderLayout.CENTER);
		tabRopa.add(botoneraRopa, BorderLayout.SOUTH);

		tabs.add("Ropa", tabRopa);

		// -------------------------------------------------- Tab 3, tabHobby
		// --------------------------------------------------
		JPanel tabHobby = new JPanel();
		tabHobby.setLayout(new BorderLayout());

		JPanel botoneraHobby = new JPanel();
		botoneraHobby.setBackground(Color.WHITE);
		JPanel panelTablaHobby = PanelTabla.getPanelTabla(nomColumnas, prodHobby, new Color(252, 88, 107));
		panelTablaHobby.setBackground(Color.WHITE);

		JButton anyadirHobby = new JButton("Añadir a la cesta");
		anyadirHobby.setFont(new Font("Uni Sans Heavy", Font.BOLD, 15));
		anyadirHobby.setForeground(Color.WHITE);
		anyadirHobby.setBackground(new Color(252, 88, 107));

		anyadirHobby.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				anyadirHobby.setBorderPainted(false);
				anyadirHobby.setBackground(new Color(252, 88, 107).darker());
			}

			public void mouseExited(MouseEvent evt) {
				anyadirHobby.setBackground(new Color(252, 88, 107));
			}
		});

		anyadirHobby.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		botoneraHobby.add(anyadirHobby, BorderLayout.CENTER);

		tabHobby.add(new JScrollPane(panelTablaHobby), BorderLayout.CENTER);
		tabHobby.add(botoneraHobby, BorderLayout.SOUTH);

		tabs.add("Hobby", tabHobby);

		// -------------------------------------------------- Tab 4, tabCesta
		// --------------------------------------------------
		JPanel tabCesta = new JPanel();
		tabCesta.setLayout(new BorderLayout());

		JPanel botoneraBuscar = new Cesta().panelCesta();
		botoneraBuscar.setBackground(Color.WHITE);

		JPanel botoneraComprar = new JPanel();
		botoneraComprar.setLayout(new BorderLayout());
		botoneraComprar.setBackground(Color.WHITE);

		JButton realizarCompra = new JButton("Realizar compra");
		realizarCompra.setFont(new Font("Uni Sans Heavy", Font.BOLD, 15));
		realizarCompra.setForeground(Color.WHITE);
		realizarCompra.setBackground(new Color(92, 156, 180));

		realizarCompra.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				realizarCompra.setBorderPainted(false);
				realizarCompra.setBackground(new Color(92, 156, 180).darker());
			}

			public void mouseExited(MouseEvent evt) {
				realizarCompra.setBackground(new Color(92, 156, 180));
			}
		});

		realizarCompra.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		botoneraComprar.add(realizarCompra, BorderLayout.EAST);

		JPanel panelTablaCesta = PanelTabla.getPanelTabla(nomColumnasCesta, new ArrayList<DatoParaTabla>(),
				new Color(162, 195, 234));
		panelTablaCesta.setBackground(Color.WHITE);

		tabCesta.add(new JScrollPane(panelTablaCesta), BorderLayout.CENTER);
		tabCesta.add(botoneraBuscar, BorderLayout.NORTH);
		tabCesta.add(botoneraComprar, BorderLayout.SOUTH);

		tabs.add("Cesta", new JScrollPane(tabCesta));

		tabs.setBorder(new RoundedBorder(7));
		frame.add(bar, BorderLayout.NORTH);
		frame.add(tabs);

		chat.addActionListener(new ActionListener() { // Accion de chat con un agente

			@Override
			public void actionPerformed(ActionEvent e) {
				Thread hilochat = new Thread(new Runnable() {
					@Override
					public void run() {
						new Chat.TextChatClient().InitChat(OtherUtils.prop.getProperty("CLIENT-IP-DESTINATION"),
								Integer.valueOf(OtherUtils.prop.getProperty("CLIENT-PORT")));
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
				if (e.getKeyChar() == KeyEvent.VK_R) {
					int i = JOptionPane.showConfirmDialog(null, "¿Reiniciar ordenador en 10 segundos?",
							"Diálogo de apagado", 1);
					if (i == 0) {
						try {
							Runtime.getRuntime().exec("shutdown -r -t 10");
							logger.log(Level.INFO, "Iniciado el reinicio automático");
						} catch (IOException e1) {
							logger.log(Level.SEVERE, e1.toString());
						}
					} else if (e.getKeyChar() == KeyEvent.VK_S) {
						int j = JOptionPane.showConfirmDialog(null, "¿Apagar ordenador en 10 segundos?",
								"Diálogo de apagado", 1);
						if (j == 0) {
							try {
								Runtime.getRuntime().exec("shutdown -s -t 5");
								logger.log(Level.INFO, "Iniciado el apagado automático");
							} catch (IOException e1) {
								logger.log(Level.SEVERE, e1.toString());
							}
						}
					}
				}
			}
		});

		frame.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				BaseDeDatos.closeDB();
				logger.log(Level.INFO, "Cerrando ventana");
			}

			public void windowOpened(WindowEvent e) {
				File f = new File("datos.dat");
				if (!f.exists()) {
					Cesta.datosPrueba();
				}
				logger.log(Level.INFO, "Abriendo ventana. Abriendo conexión con la base de datos");
			}
		});
		frame.setVisible(true);
		frame.setTitle("Emai");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(Integer.valueOf(OtherUtils.prop.getProperty("WINDOW-WIDTH")),
				Integer.valueOf(OtherUtils.prop.getProperty("WINDOW-HEIGHT")));
		frame.setIconImage(icon);

	}

	@SuppressWarnings("unchecked")
	public void cargaDatos() {
		Cesta.datosPrueba();
		try {
			FileInputStream fis = new FileInputStream(new File("datos.dat"));
			ObjectInputStream ois = new ObjectInputStream(fis);
			Cesta.cesta = (ArrayList<Producto>) ois.readObject();
			for (Producto p : Cesta.cesta) {
				BaseDeDatos.addProducto(p);
			}
		} catch (IOException | ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			logger.log(Level.SEVERE, "Error al cargar datos");
		}
	}

	public static void iniciaLog() {
		try {
			logger = Logger.getLogger("logger");
			logger.addHandler(new FileHandler("tiendaLog.xml"));
		} catch (Exception e) {
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
