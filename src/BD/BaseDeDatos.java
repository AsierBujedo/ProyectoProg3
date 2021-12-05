package BD;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;

import Tienda.*;

public class BaseDeDatos {
	private static Connection con;
	private Statement stmt;
	private static PreparedStatement pstmt;
	private static int USER_IDS = 1;

	public boolean InitDB() {
		/*
		 * Este método deberá ejecutarse antes de comenzar a usar la base de datos. La
		 * base de datos se iniciará y la función devolverá un true si todo ha ido bien,
		 * de lo contrario devolverá un false.
		 */
		try {
			con = DriverManager.getConnection("jdbc:sqlite:tienda.db");
			stmt = con.createStatement();
			stmt.executeUpdate("DROP TABLE IF EXISTS USER");
			stmt.executeUpdate("DROP TABLE IF EXISTS PRODUCTO");
			stmt.executeUpdate("DROP TABLE IF EXISTS COMPRA");

			stmt.executeUpdate(
					"CREATE TABLE USER (USER_ID int PRIMARY KEY NOT NULL, USERNAME varchar(100) NOT NULL, MAIL varchar(100) NOT NULL, PASS varchar(100) NOT NULL)");
			stmt.executeUpdate(
					"CREATE TABLE PRODUCTO (COD_PRODUCTO varchar(15) PRIMARY KEY NOT NULL, NOMBRE varchar(100), PRECIO double, MARCA varchar(100)");
			stmt.executeUpdate(
					"CREATE TABLE COMPRA (USER_ID int NOT NULL, COD_PRODUCTO int NOT NULL, CANTIDAD int, FECHA bigint, FOREIGN KEY (USER_ID) REFERENCES USER (USER_ID), FOREIGN KEY (COD_PRODUCTO) REFERENCES PRODUCTO (CODIGO_PRODUCTO))");
			stmt.executeUpdate(
					"INSERT INTO USER VALUES(0, 'ADMIN', '123@GMAIL.COM', 445566)");
			return true;
		} catch (SQLException e) {
			VentanaTienda.logger.log(Level.SEVERE, e.toString());
			return false;
		}
	}

	public boolean addColumn(TABLES TABLE, String name) {
		/*
		 * Este método, crea una nueva columna en la tabla "TABLE", puede llamarse en
		 * cualquier momento (mientras la base de datos esté iniciada), el valor de
		 * cualquier elemento que se añada será null.
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

	public boolean removeColumn(TABLES TABLE, String COLUMN) {

		/*
		 * Mediante este método, se elimina una columa, identificada por su nombre y por
		 * la tabla que la posee. No se podrán borrar las columnas por defecto.
		 */

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

	public boolean removeUser(String MAIL, String PASS) {

		/*
		 * Mediante este método, se elimina un usuario, identificado por su mail y su
		 * password. No por su ID.
		 */

		try {
			pstmt = con.prepareStatement("DELETE FROM USER WHERE MAIL = '" + MAIL + "' AND PASS = '" + PASS + "'");
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			VentanaTienda.logger.log(Level.SEVERE, e.toString());
			return false;
		}

	}

	public boolean addUser(String USERNAME, String MAIL, String PASS) {
		/*
		 * Método que permite añadir un nuevo usuario a la tabla "USER" . Se recibe su
		 * nombre, mail y correo, automáticamente se le asigna un ID como código
		 * identificativo único, esta es su PRIMARY KEY.
		 */
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

	public boolean closeDB() {
		// Deberá cerrarse siempre la conexión con la base de datos mediante este
		// método.
		try {
			con.close();
			return true;
		} catch (SQLException e) {
			VentanaTienda.logger.log(Level.SEVERE, e.toString());
			return false;
		}
	}
	
	public String getUser(String MAIL, String PASS) {
		try {
			pstmt = con.prepareStatement("SELECT USERNAME FROM USER WHERE MAIL = '"+MAIL+"' AND PASS = '"+PASS+"'" );
			ResultSet rs = pstmt.executeQuery();
			return rs.getString("USERNAME");
		} catch (SQLException e) {
			VentanaTienda.logger.log(Level.SEVERE, e.toString());
			return "Error";
		}
	}
	

	public boolean editUser(COLS COL, String MAIL, String PASS, String NEW) {

		/*
		 * Este método permite editar un usuario de la tabla "USER" mediante el enum
		 * "COLS", se permite el cambio de nombre de usuario, mail y/o la password. Si
		 * el cambio es efectivo, recibiremos true, de lo contrario, el método devolverá
		 * false.
		 */

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

	public static void main(String[] args) {
		BaseDeDatos bd = new BaseDeDatos();
		bd.InitDB();

		// COLOCAR AQUÍ LAS PRUEBAS CON LA BASE DE DATOS

		
		bd.closeDB();

	}
	
	public static ArrayList<Producto> getProductos() {
		try {
			ArrayList<Producto> productos = new ArrayList();
			pstmt = con.prepareStatement("SELECT * FROM PRODUCTO");
			ResultSet rs = pstmt.executeQuery();
			
			while( rs.next() ) {
				String id = rs.getString("PRODUCTO_ID");
				String nombre = rs.getString("NOMBRE");
				double precio = rs.getDouble("PRECIO");
				String marca = rs.getString("MARCA");
				
				if (id.contains("L")) {
//					productos.add( new Libro(id, nombre, precio, marca) );
				} 
				
			}
			
			return productos;
		} catch (SQLException e) {
			VentanaTienda.logger.log(Level.SEVERE, e.toString());
			return null;
		}
	}
}
