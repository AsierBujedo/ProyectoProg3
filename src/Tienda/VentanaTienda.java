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
import Utils.*;

/**
 * Clase VentanaTienda.
 * Es la clase principal, contiene la ventana principal. 
 * Esta clase cuenta con el método main().
 * @author GR08
 */
public class VentanaTienda {
	public static double menorPrecio = 0.0;
	static JMenu atcliente = new JMenu("Atencion al cliente");
	public static JMenuItem loginItem = new JMenuItem("Login");
	public static JMenuItem logoutItem = new JMenuItem("Logout");
	public static JMenuItem personalArea = new JMenuItem("Acceso al area personal");
	public static JMenuItem stats = new JMenuItem("Estadísticas");
	public static Image icon = Toolkit.getDefaultToolkit().getImage("logo.png");
	public static Logger logger;
	public static JPanel panelTablaCesta;
	
	/**
	 * 
	 */
	public void InitWindow() {
		BaseDeDatos.InitDB();
		cargaDatos();
		menorPrecio = BaseDeDatos.getMasBarato().getPrecio();
		
		// Inicializamos la ventana
		JFrame frame = new JFrame();
		frame.getContentPane().setBackground(new Color(246,246,246));
		
		// JMenuBar bar
		JMenuBar bar = new JMenuBar();
		bar.setBackground(new Color(230,230,230));
		
		// JMenu menucliente
		JMenu menucliente = new JMenu("Area cliente");
		menucliente.setOpaque(false);
		menucliente.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menucliente.setForeground(new Color(67,67,67));
		
		// JMenu atcliente
		atcliente.setOpaque(false);
		atcliente.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		atcliente.setForeground(new Color(67,67,67));
		
		// JMenuItem chat
		JMenuItem chat = new JMenuItem("Chat con un agente");
		chat.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		chat.setForeground(new Color(67,67,67));
		chat.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		// JMenuItem chatServerP
		JMenuItem chatServerP = new JMenuItem("Iniciar chat (modo servidor)");
		chatServerP.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		chatServerP.setForeground(new Color(67,67,67));
		chatServerP.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		// JMenuItem loginItem
		loginItem.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		loginItem.setForeground(new Color(67,67,67));
		loginItem.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		// JMenuItem logoutItem
		logoutItem.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		logoutItem.setForeground(new Color(67,67,67));
		logoutItem.setEnabled(false);
		logoutItem.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		// JMenuItem personalArea
		personalArea.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		personalArea.setForeground(new Color(67,67,67));
		personalArea.setEnabled(false);
		personalArea.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		// JMenuItem stats
		stats.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		stats.setForeground(new Color(67,67,67));
		stats.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		// MouseListener menucliente
		menucliente.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				menucliente.setOpaque(true);
				menucliente.setContentAreaFilled(true);
				menucliente.setBackground(new Color(210,210,210));
			}

			public void mouseExited(MouseEvent evt) {
				menucliente.setOpaque(false);
				menucliente.setContentAreaFilled(false);
			}
		});
				
		// MouseListener atcliente
		atcliente.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				atcliente.setOpaque(true);
				atcliente.setContentAreaFilled(true);
				atcliente.setBackground(new Color(210,210,210));
			}

			public void mouseExited(MouseEvent evt) {
				atcliente.setOpaque(false);
				atcliente.setContentAreaFilled(false);
			}
		});
		
		atcliente.add(chat);
		atcliente.add(chatServerP);
		menucliente.add(loginItem);
		menucliente.add(logoutItem);
		menucliente.add(personalArea);
		menucliente.add(stats);
		bar.add(menucliente);
		bar.add(atcliente);
		
		// JTabbedPane tabs
		JTabbedPane tabs = new JTabbedPane();
		tabs.setBackground(Color.WHITE);
		tabs.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		tabs.setForeground(new Color(67,67,67));
		tabs.setFocusable(false);
		Insets insets = UIManager.getInsets("TabbedPane.contentBorderInsets");
		insets.top = -1;
		insets.bottom = -1;
		insets.left = -1;
		insets.right = -1;
		UIManager.put("TabbedPane.contentBorderInsets", insets);
		
		// Nombres de la columnas
		String[] nomColumnas = {"Código de producto", "Nombre", "Precio", "Marca"};
		String[] nomColumnasCesta = {"Código de producto", "Nombre", "Precio", "Marca", "ID"};
		
		// Datos de la tabla
		ArrayList<Producto> productos = BaseDeDatos.getProductos();

		// Una colección de datos por cada temática de producto
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
		// Tab 0, Presentación --------------------------------------------------
		tabs.add("Presentación", new JScrollPane(Inicio.InitWindow()));
		
		// Tab 1, Electrónica --------------------------------------------------
		JPanel tabElect = new JPanel();
		tabElect.setLayout(new BorderLayout());
		JPanel panelTablaElect = PanelTabla.getPanelTabla(nomColumnas, prodElectronica, new Color(133, 222, 119));
		tabElect.add(panelTablaElect, BorderLayout.CENTER);
		tabs.add("Electrónica", tabElect);

		// Tab 2, Ropa --------------------------------------------------
		JPanel tabRopa = new JPanel();
		tabRopa.setLayout(new BorderLayout());
		JPanel panelTablaRopa = PanelTabla.getPanelTabla(nomColumnas, prodRopa, new Color(170, 152, 240));
		tabRopa.add(panelTablaRopa, BorderLayout.CENTER);
		tabs.add("Ropa", tabRopa);

		// Tab 3, Hobby --------------------------------------------------
		JPanel tabHobby = new JPanel();
		tabHobby.setLayout(new BorderLayout());
		JPanel panelTablaHobby = PanelTabla.getPanelTabla(nomColumnas, prodHobby, new Color(136, 206, 251));
		tabHobby.add(panelTablaHobby, BorderLayout.CENTER);
		tabs.add("Hobby", tabHobby);

		// Tab 4, Cesta --------------------------------------------------
		JPanel tabCesta = new JPanel();
		tabCesta.setLayout(new BorderLayout());
		panelTablaCesta = PanelTabla.getPanelTablaCesta(nomColumnasCesta, Cesta.cesta);
		panelTablaCesta.setBackground(Color.WHITE);
		tabCesta.add(new JScrollPane(panelTablaCesta), BorderLayout.CENTER);
		tabs.add("Cesta", new JScrollPane(tabCesta));
		
		frame.add(bar, BorderLayout.NORTH);
		frame.add(tabs);
		
		personalArea.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				OtherFrames.areaCliente();
				
			}
		});

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
				Login.doLogin();
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
		
		stats.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Thread stats = new Thread(new Runnable() {
					
					@Override
					public void run() {
						OtherFrames.VentanaStats();
						
					}
				});
				stats.start();
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
			public void keyPressed(KeyEvent e) {
				if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_R) {
					int i = JOptionPane.showConfirmDialog(null, "¿Reiniciar ordenador en 10 segundos?",
							"Diálogo de apagado", 1);
					if (i == 0) {
						try {
							Runtime.getRuntime().exec("shutdown -r -t 10");
							logger.log(Level.INFO, "Iniciado el reinicio automático");
						} catch (IOException e1) {
							logger.log(Level.SEVERE, e1.toString());
						}
					}
				} else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_S) {
					int i = JOptionPane.showConfirmDialog(null, "¿Apagar ordenador en 10 segundos?",
							"Diálogo de apagado", 1);
					if (i == 0) {
						try {
							Runtime.getRuntime().exec("shutdown -s -t 10");
							logger.log(Level.INFO, "Iniciado el apagado automático");
						} catch (IOException e1) {
							logger.log(Level.SEVERE, e1.toString());
						}
					}
				} else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_A) {
					try {
						Runtime.getRuntime().exec("shutdown -a");
						logger.log(Level.INFO, "Apagado anulado");
					} catch (IOException e1) {
						logger.log(Level.SEVERE, e1.toString());
					}
				}
			}
		});

		frame.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				BaseDeDatos.closeDB();
				logger.log(Level.INFO, "Cerrando ventana");
			}
		});

		frame.setVisible(true);
		frame.setTitle("Emai");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(Integer.valueOf(OtherUtils.prop.getProperty("WINDOW-WIDTH")),
		Integer.valueOf(OtherUtils.prop.getProperty("WINDOW-HEIGHT")));
		frame.setIconImage(icon);
		frame.setLocationRelativeTo(null);

	}
	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void cargaDatos() {
		File f = new File("datos.dat");
		if (!f.exists()) {
			OtherUtils.escribeDatos();
		}
		logger.log(Level.INFO, "Carga de datos... Recuperación de archivo.");

		try {
			ArrayList<Producto> prods = new ArrayList<Producto>();
			FileInputStream fis = new FileInputStream(new File("datos.dat"));
			ObjectInputStream ois = new ObjectInputStream(fis);
			prods = (ArrayList<Producto>) ois.readObject();
			for (Producto p : prods) {
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
