package BD;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;

import Tienda.*;

public class BaseDeDatos {
	private static Connection con;
	private static Statement stmt;
	private static PreparedStatement pstmt;
	private static int USER_IDS = 1;
	
	/** Inicializa la base de datos
	 * @return True si todo va bien, false si hay alg�n error
	 */
	// Este m�todo deber� ejecutarse antes de comenzar a usar la base de datos
	public static boolean InitDB() {
		try {
			con = DriverManager.getConnection("jdbc:sqlite:tienda.db");
			stmt = con.createStatement();
			stmt.executeUpdate("DROP TABLE IF EXISTS USER");
			stmt.executeUpdate("DROP TABLE IF EXISTS PRODUCTO");
			stmt.executeUpdate("DROP TABLE IF EXISTS COMPRA");

			stmt.executeUpdate(
					"CREATE TABLE USER (USER_ID int PRIMARY KEY NOT NULL, USERNAME varchar(100) NOT NULL, MAIL varchar(100) NOT NULL, PASS varchar(100) NOT NULL)");
			stmt.executeUpdate(
				"CREATE TABLE PRODUCTO (COD_PRODUCTO varchar(15) PRIMARY KEY NOT NULL, NOMBRE varchar(100), PRECIO double, MARCA varchar(100))");
//			Error con la creaci�n de esta tabla. "incomplete input".
			stmt.executeUpdate(
					"CREATE TABLE COMPRA (USER_ID int NOT NULL, COD_PRODUCTO int NOT NULL, CANTIDAD int, FECHA bigint, FOREIGN KEY (USER_ID) REFERENCES USER (USER_ID), FOREIGN KEY (COD_PRODUCTO) REFERENCES PRODUCTO (COD_PRODUCTO))");
			stmt.executeUpdate(
					"INSERT INTO USER VALUES (0, 'ADMIN', 'ADMIN@GMAIL.COM', 12345)"); // Fila de prueba para la tabla USER
			return true;
		} catch (SQLException e) {
			VentanaTienda.logger.log(Level.SEVERE, e.toString());
			return false;
		}
	}
	
	/** A�ade una columna a una tabla
	 * @param TABLE Nombre de la tabla a la que se quiere a�adir la columna
	 * @param name Nombre de la columna a a�adir
	 * @return True si la columna se a�ade correctamente, false si hay alg�n error
	 */
	public static boolean addColumn(TABLES TABLE, String name) {
		/*
		 * Este m�todo, crea una nueva columna en la tabla "TABLE", puede llamarse en
		 * cualquier momento (mientras la base de datos est� iniciada), el valor de
		 * cualquier elemento que se a�ada ser� null.
		 */
		try {
			pstmt = con.prepareStatement("ALTER TABLE " + TABLE.toString() + " ADD " + name + " string DEFAULT null");
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			VentanaTienda.logger.log(Level.SEVERE, e.toString());
			return false;
		}
	}
	
	/** Elimina una columna de una tabla
	 * @param TABLE Nombre de la tabla que posee la columna a eliminar
	 * @param COLUMN Nombre de la columna a eliminar
	 * @return True si la columna se borra correctamente, false si es una columna por defecto o hay alg�n error
	 */
	// No se podr�n borrar las columnas por defecto
	public static boolean removeColumn(TABLES TABLE, String COLUMN) {
		try {
			if (!COLUMN.equals(COLS.USERNAME.toString()) || !COLUMN.equals(COLS.MAIL.toString()) || !COLUMN.equals(COLS.PASS.toString()) || COLUMN.equals("PLAYED") || COLUMN.equals("WON")) {
				pstmt = con.prepareStatement("ALTER TABLE " + TABLE.toString() + " DROP " + COLUMN);
				pstmt.executeUpdate();
				return true;
			} else {
				System.out.println("No se puede borrar una columna programada por defecto");
				return false;
			}
		} catch (Exception e) {
			VentanaTienda.logger.log(Level.SEVERE, e.toString());
			return false;
		}
	}
	
	/** Elimina un usuario de la tabla USER 
	 * @param MAIL Direcci�n de correo electr�nico del usuario
	 * @param PASS Contrase�a del usuario
	 * @return True si el usuario elimina correctamente, false si hay alg�n error
	 */
	// El usuario se identifica por su direcci�n de correo electr�nico y su contrase�a, no por su identificativo �nico
	public static boolean removeUser(String MAIL, String PASS) {
		try {
			pstmt = con.prepareStatement("DELETE FROM USER WHERE MAIL = '" + MAIL + "' AND PASS = '" + PASS + "'");
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			VentanaTienda.logger.log(Level.SEVERE, e.toString());
			return false;
		}

	}
	
	/** A�ade un nuevo usuario a la tabla USER
	 * @param USERNAME Nombre de usuario
	 * @param MAIL Direcci�n de correo electr�nico del usuario
	 * @param PASS Contrase�a del usuario
	 * @return True si el usuario se a�ade correctamente, false si hay alg�n error
	 */
	// Autom�ticamente se le asigna un ID como c�digo identificativo �nico, su PRIMARY KEY
	public static boolean addUser(String USERNAME, String MAIL, String PASS) {
		try {
			pstmt = con.prepareStatement("INSERT INTO USER (USERNAME, MAIL, PASS, USER_ID) VALUES ('" + USERNAME
					+ "','" + MAIL + "','" + PASS + "','" + USER_IDS + "')");
			pstmt.executeUpdate();
			USER_IDS++;
			return true;
		} catch (SQLException e) {
			VentanaTienda.logger.log(Level.SEVERE, e.toString());
			return false;
		}
	}
	
	/** Cierra la conexi�n con la base de datos
	 * @return True si la conexi�n se cierra correctamente, false si hay alg�n error
	 */
	// Deber� cerrarse siempre la conexion con la base de datos mediante este m�todo
	public static boolean closeDB() {
		try {
			con.close();
			return true;
		} catch (SQLException e) {
			VentanaTienda.logger.log(Level.SEVERE, e.toString());
			return false;
		}
	}
	
	/** Lee los usuarios de la tabla USER
	 * @param MAIL Direcci�n de correo electr�nico del usuario
	 * @param PASS Contrase�a del usuario
	 * @return "Nombre de usuario", "Error" si hay alg�n error
	 */
	public static String getUser(String MAIL, String PASS) {
		try {
			pstmt = con.prepareStatement("SELECT USERNAME FROM USER WHERE MAIL = '"+MAIL+"' AND PASS = '"+PASS+"'" );
			ResultSet rs = pstmt.executeQuery();
			return rs.getString("USERNAME");
		} catch (SQLException e) {
			VentanaTienda.logger.log(Level.SEVERE, e.toString());
			return "Error";
		}
	}
	
	/**Edita un usuario de la tabla USER
	 * @param COL Nombre de la columna de la tabla USER
	 * @param MAIL Direcci�n de correo electr�nico del usuario
	 * @param PASS Contrase�a del usuario
	 * @param NEW
	 * @return True si el cambio es efectivo, false si hay alg�n error
	 */
	// Se permite el cambio de nombre de usuario, mail y/o password
	public static boolean editUser(COLS COL, String MAIL, String PASS, String NEW) {
		try {
			pstmt = con.prepareStatement("UPDATE USER SET " + COL.toString() + "= '" + NEW + "' WHERE MAIL = '" + MAIL
					+ "' AND PASS = '" + PASS + "'");
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			VentanaTienda.logger.log(Level.SEVERE, e.toString());
			return false;
		}
	}
	
	/** Lee los productos de la tabla PRODUCTO
	 * @return Lista completa de productos, null si hay alg�n error
	 */
	public static ArrayList<Producto> getProductos() {
		try {
			ArrayList<Producto> productos = new ArrayList<Producto>();
			pstmt = con.prepareStatement("SELECT * FROM PRODUCTO");
			ResultSet rs = pstmt.executeQuery();
			
			while( rs.next() ) {
				String codigoProducto = rs.getString("COD_PRODUCTO");
				String nombre = rs.getString("NOMBRE");
				double precio = rs.getDouble("PRECIO");
				String marca = rs.getString("MARCA");
				
				if (codigoProducto.contains("I")) {
					productos.add( new Impresora(codigoProducto, nombre, precio, marca, 0) );
				}
				else if (codigoProducto.contains("L")) {
					productos.add(new Libro(codigoProducto, nombre, precio, marca, 0));
				}
				else if (codigoProducto.contains("O")) {
					productos.add(new Ordenador(codigoProducto, nombre, precio, marca, 0));
				}
				else if (codigoProducto.contains("P")) {
					productos.add(new Pantalon(codigoProducto, nombre, precio, marca, 0));
				}
				else if (codigoProducto.contains("S")) {
					productos.add(new Sudadera(codigoProducto, nombre, precio, marca, 0));
				}
				else if (codigoProducto.contains("T")) {
					productos.add(new Telefono(codigoProducto, nombre, precio, marca, 0));
				}
				else if (codigoProducto.contains("VC")) {
					productos.add(new Videoconsola(codigoProducto, nombre, precio, marca, 0));
				}
				else if (codigoProducto.contains("VJ")) {
					productos.add(new Videojuego(codigoProducto, nombre, precio, marca, 0));
				}
				else if (codigoProducto.contains("Z")) {
					productos.add(new Zapatilla(codigoProducto, nombre, precio, marca, 0));
				}				
			}			
			return productos;
		} catch (SQLException e) {
			VentanaTienda.logger.log(Level.SEVERE, e.toString());
			return null;
		}
	}
	
//	public static void main(String[] args) {
//		BaseDeDatos bd = new BaseDeDatos();
//		bd.InitDB();
//
//		// COLOCAR AQU� LAS PRUEBAS CON LA BASE DE DATOS
//
//		
//		bd.closeDB();
//
//	}
}
