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

	/**
	 * Inicializa la base de datos.
	 * @return true si todo va bien, false si hay alg�n error.
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
					"CREATE TABLE USER (USER_ID int ALTERNATIVE KEY NOT NULL, USERNAME varchar(100) NOT NULL, MAIL varchar(100) PRIMARY KEY NOT NULL, PASS varchar(100) NOT NULL, TELF int(15) DEFAULT NULL, SEXO varchar(10) DEFAULT NULL, NACIMIENTO date DEFAULT NULL, DIR varchar(100) DEFAULT NULL)");
			stmt.executeUpdate(
					"CREATE TABLE PRODUCTO (COD_PRODUCTO varchar(15) PRIMARY KEY NOT NULL, NOMBRE varchar(100), PRECIO double, MARCA varchar(100))");
			stmt.executeUpdate(
					"CREATE TABLE COMPRA (MAIL varchar(100) NOT NULL, PRECIO double, TOTAL_PRODS int, FECHA date, FOREIGN KEY (MAIL) REFERENCES USER (MAIL))");
			stmt.executeUpdate("INSERT INTO USER(USER_ID, USERNAME, MAIL, PASS) VALUES (0, 'admin', 'admin@gmail.com', 12345)"); // Fila de prueba para	la tabla USER														
			VentanaTienda.logger.log(Level.INFO, "Creaci�n de tablas correcta");
			BaseDeDatos.editUserSpecs("admin@gmail.com", 666666666, Genero.MASCULINO, new Date(System.currentTimeMillis()), "Avda de Las Universidades 24, 48007, Bilbao, Bizkaia");
			return true;
		} catch (SQLException e) {
			VentanaTienda.logger.log(Level.SEVERE, e.toString());
			return false;
		}
	}

	/**
	 * A�ade una columna a una tabla.
	 * @param TABLE Nombre de la tabla a la que se quiere a�adir la columna.
	 * @param name  Nombre de la columna a a�adir.
	 * @return true si la columna se a�ade correctamente, false si hay alg�n error.
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
			VentanaTienda.logger.log(Level.INFO, "A�adida en " + TABLE.toString() + " la columna " + name);
			return true;
		} catch (SQLException e) {
			VentanaTienda.logger.log(Level.SEVERE, e.toString());
			return false;
		}
	}

	/**
	 * Elimina una columna de una tabla.
	 * @param TABLE  Nombre de la tabla que posee la columna a eliminar.
	 * @param COLUMN Nombre de la columna a eliminar.
	 * @return true si la columna se borra correctamente, false si es una columna por defecto o hay alg�n error.
	 */
	// No se podr�n borrar las columnas por defecto
	public static boolean removeColumn(TABLES TABLE, String COLUMN) {
		try {
			if (!COLUMN.equals(COLS.USERNAME.toString()) || !COLUMN.equals(COLS.MAIL.toString())
					|| !COLUMN.equals(COLS.PASS.toString())) {
				pstmt = con.prepareStatement("ALTER TABLE " + TABLE.toString() + " DROP " + COLUMN);
				pstmt.executeUpdate();
				VentanaTienda.logger.log(Level.INFO, "Eliminada de " + TABLE.toString() + " la columna " + COLUMN);
				return true;
			} else {
				VentanaTienda.logger.log(Level.SEVERE, "No se puede borrar una columna programada por defecto");
				return false;
			}
		} catch (Exception e) {
			VentanaTienda.logger.log(Level.SEVERE, e.toString());
			return false;
		}
	}

	/**
	 * Elimina un usuario de la tabla USER.
	 * @param MAIL Direcci�n de correo electr�nico del usuario.
	 * @param PASS Contrase�a del usuario.
	 * @return true si el usuario elimina correctamente, false si hay alg�n error.
	 */
	// El usuario se identifica por su direcci�n de correo electr�nico y su
	// contrase�a, no por su identificativo �nico
	public static boolean removeUser(String MAIL, String PASS) {
		try {
			pstmt = con.prepareStatement("DELETE FROM USER WHERE MAIL = '" + MAIL + "' AND PASS = '" + PASS + "'");
			pstmt.executeUpdate();
			VentanaTienda.logger.log(Level.INFO, "Usuario con mail: " + MAIL + " eliminado correctamente");
			return true;
		} catch (SQLException e) {
			VentanaTienda.logger.log(Level.SEVERE, e.toString());
			return false;
		}

	}

	/**
	 * A�ade un nuevo usuario a la tabla USER.
	 * @param USERNAME Nombre de usuario.
	 * @param MAIL Direcci�n de correo electr�nico del usuario.
	 * @param PASS Contrase�a del usuario.
	 * @return true si el usuario se a�ade correctamente, false si hay alg�n error.
	 */
	// Autom�ticamente se le asigna un ID como c�digo identificativo �nico, su
	// PRIMARY KEY
	public static boolean addUser(String USERNAME, String MAIL, String PASS, int TELF, Genero SEXO, java.util.Date date, String DIR) {
		try {
			pstmt = con.prepareStatement("INSERT INTO USER (USERNAME, MAIL, PASS, USER_ID, TELF, SEXO, NACIMIENTO, DIR) VALUES ('" + USERNAME + "','"
					+ MAIL + "','" + PASS + "','" + USER_IDS + "',"+TELF+",'"+SEXO.toString()+"',"+date+",'"+DIR+"')");
			pstmt.executeUpdate();
			VentanaTienda.logger.log(Level.INFO,
					"Nuevo usuario: " + MAIL + ", ID: " + USER_IDS + ". A�adido correctamente");
			USER_IDS++;
			return true;
		} catch (SQLException e) {
			VentanaTienda.logger.log(Level.SEVERE, e.toString());
			return false;
		}
	}

	/**
	 * Cierra la conexi�n con la base de datos.
	 * @return true si la conexi�n se cierra correctamente, false si hay alg�n error.
	 */
	// Deber� cerrarse siempre la conexion con la base de datos mediante este m�todo
	public static boolean closeDB() {
		try {
			con.close();
			VentanaTienda.logger.log(Level.INFO, "Conexi�n cerrada con la base de datos");
			return true;
		} catch (SQLException e) {
			VentanaTienda.logger.log(Level.SEVERE, e.toString());
			return false;
		}
	}

	/**
	 * Lee los usuarios de la tabla USER.
	 * @param MAIL Direcci�n de correo electr�nico del usuario.
	 * @param PASS Contrase�a del usuario.
	 * @return "Nombre de usuario", "Error" si hay alg�n error.
	 */
	public static String getUser(String MAIL, String PASS) {
		try {
			pstmt = con.prepareStatement(
					"SELECT USERNAME FROM USER WHERE MAIL = '" + MAIL + "' AND PASS = '" + PASS + "'");
			ResultSet rs = pstmt.executeQuery();
			String user = rs.getString("USERNAME");
			rs.close();
			return user;
		} catch (SQLException e) {
			VentanaTienda.logger.log(Level.SEVERE, e.toString());
			return "Error";
		}
	}
	
	public static String getUserMail(String name) {
		try {
			pstmt = con.prepareStatement(
					"SELECT MAIL FROM USER WHERE USERNAME = '"+ name + "'");
			ResultSet rs = pstmt.executeQuery();
			String mail = rs.getString("MAIL");
			rs.close();
			return mail;
		} catch (SQLException e) {
			VentanaTienda.logger.log(Level.SEVERE, e.toString());
			return "Error";
		}
	}

	/**
	 * Edita un usuario de la tabla USER.
	 * @param COL  Nombre de la columna de la tabla USER.
	 * @param MAIL Direcci�n de correo electr�nico del usuario.
	 * @param PASS Contrase�a del usuario.
	 * @param NEW
	 * @return true si el cambio es efectivo, false si hay alg�n error.
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
	/**Metodo que edita varios atributos de los Usuarios, entre ellos: TELF, SEXO, NACIMIENTO Y LA DIRECCION.
	 * 
	 * @param MAIL
	 * @param TELF
	 * @param SEXO
	 * @param NACIMIENTO
	 * @param DIR
	 * @return true, false
	 */
	public static boolean editUserSpecs(String MAIL, int TELF, Genero SEXO, Date NACIMIENTO, String DIR) {
		try {
			pstmt = con.prepareStatement("UPDATE USER SET " + "TELF" + "= " + TELF + " WHERE MAIL = '" + MAIL+"';");
			pstmt.executeUpdate();
			pstmt = con.prepareStatement("UPDATE USER SET " + "SEXO" + "= '" + SEXO.toString() + "' WHERE MAIL = '" + MAIL+"';");
			pstmt.executeUpdate();
			pstmt = con.prepareStatement("UPDATE USER SET " + "NACIMIENTO" + "= " + NACIMIENTO + " WHERE MAIL = '" + MAIL+"';");
			pstmt.executeUpdate();
			pstmt = con.prepareStatement("UPDATE USER SET " + "DIR" + "= '" + DIR + "' WHERE MAIL = '" + MAIL+"';");
			pstmt.executeUpdate();
			VentanaTienda.logger.log(Level.INFO, "Se ha actualizado el usuario con MAIL: "+ MAIL);
			return true;
		} catch (SQLException e) {
			VentanaTienda.logger.log(Level.SEVERE, e.toString());
			return false;
		}
	}

	/**
	 * Lee los productos de la tabla PRODUCTO.
	 * @return Lista completa de productos, null si hay alg�n error.
	 */
	public static ArrayList<Producto> getProductos() {
		try {
			ArrayList<Producto> productos = new ArrayList<Producto>();
			pstmt = con.prepareStatement("SELECT * FROM PRODUCTO");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String codigoProducto = rs.getString("COD_PRODUCTO");
				String nombre = rs.getString("NOMBRE");
				double precio = rs.getDouble("PRECIO");
				String marca = rs.getString("MARCA");

				if (codigoProducto.contains("I")) {
					productos.add(new Impresora(codigoProducto, nombre, precio, marca));
				} else if (codigoProducto.contains("L")) {
					productos.add(new Libro(codigoProducto, nombre, precio, marca));
				} else if (codigoProducto.contains("O")) {
					productos.add(new Ordenador(codigoProducto, nombre, precio, marca));
				} else if (codigoProducto.contains("P")) {
					productos.add(new Pantalon(codigoProducto, nombre, precio, marca));
				} else if (codigoProducto.contains("S")) {
					productos.add(new Sudadera(codigoProducto, nombre, precio, marca));
				} else if (codigoProducto.contains("T")) {
					productos.add(new Telefono(codigoProducto, nombre, precio, marca));
				} else if (codigoProducto.contains("VC")) {
					productos.add(new Videoconsola(codigoProducto, nombre, precio, marca));
				} else if (codigoProducto.contains("VJ")) {
					productos.add(new Videojuego(codigoProducto, nombre, precio, marca));
				} else if (codigoProducto.contains("Z")) {
					productos.add(new Zapatilla(codigoProducto, nombre, precio, marca));
				} else if (codigoProducto.contains("VJ")) {
					productos.add(new Videojuego(codigoProducto, nombre, precio, marca));
				}
			}
			return productos;
		} catch (SQLException e) {
			VentanaTienda.logger.log(Level.SEVERE, e.toString());
			return null;
		}
	}
	/**M�todo que consulta en la base de datos el Producto m�s barato
	 * @return Producto
	 */
	public static Producto getMasBarato() {
		try {
			pstmt = con.prepareStatement(
					"SELECT COD_PRODUCTO AS C�DIGO, NOMBRE, MIN(PRECIO) AS PRECIO, MARCA FROM PRODUCTO WHERE PRECIO IN (SELECT MIN(PRECIO) FROM PRODUCTO);");
			ResultSet rs = pstmt.executeQuery();
			
			String code = rs.getString(1);
			String name = rs.getString(2);
			double precio = rs.getDouble(3);
			String marca = rs.getString(4);
			VentanaTienda.logger.log(Level.INFO, "Operaci�n en la base de datos realizada");
			return new Generico(code, name, precio, marca);

		} catch (SQLException e) {
			VentanaTienda.logger.log(Level.SEVERE, e.toString());
			return null;
		}
	}
	/**
	 * Funcion que realiza una consulta y obtiene el producto m�s caro.
	 * @return Producto
	 */
	public static Producto getMasCaro() {
		try {
			pstmt = con.prepareStatement(
					"SELECT COD_PRODUCTO AS C�DIGO, NOMBRE, MAX(PRECIO) AS PRECIO, MARCA FROM PRODUCTO WHERE PRECIO IN (SELECT MAX(PRECIO) FROM PRODUCTO);");
			ResultSet rs = pstmt.executeQuery();
			
			String code = rs.getString(1);
			String name = rs.getString(2);
			double precio = rs.getDouble(3);
			String marca = rs.getString(4);
			VentanaTienda.logger.log(Level.INFO, "Operaci�n en la base de datos realizada");
			return new Generico(code, name, precio, marca);

		} catch (SQLException e) {
			VentanaTienda.logger.log(Level.SEVERE, e.toString());
			return null;
		}
	}
	/**
	 * Funcion que realiza una consulta y obtiene el producto m�s barato.
	 * @return Producto
	 */
	public static boolean addProducto(Producto p) {
		try {
			pstmt = con.prepareStatement("INSERT INTO PRODUCTO VALUES ('" + p.getCodigoProducto() + "', '"
					+ p.getNombre() + "', " + p.getPrecio() + ", '" + p.getMarca() + "')");
			VentanaTienda.logger.log(Level.INFO, "Se ha a�adido el producto con c�digo: " + p.getCodigoProducto());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			VentanaTienda.logger.log(Level.SEVERE, e.toString());
			return false;
		}
	}
	
	/**
	 * Lee los productos de la tabla PRODUCTO.
	 * @param COD_PRODUCTO C�digo del producto.
	 * @param NOMBRE Nombre del producto.
	 * @return Producto con el c�digo y nombre que buscamos , null si hay alg�n error.
	 */	
	public static String getProducto(String NOMBRE) {
		try {
			pstmt = con.prepareStatement("SELECT COD_PRODUCTO FROM PRODUCTO WHERE NOMBRE = '" + NOMBRE + "'");
			ResultSet rs = pstmt.executeQuery();
			VentanaTienda.logger.log(Level.INFO, "Operaci�n en la base de datos realizada");
			return rs.getString("COD_PRODUCTO");

		} catch (SQLException e) {
			VentanaTienda.logger.log(Level.SEVERE, e.toString());
			return "Error";
		}	
	
	}
	
	/**
	 * A�ade una nueva compra a la tabla COMPRA.
	 * @param MAIL Direcci�n de correo electr�nico del usuario.
	 * @return true si la compra se a�ade correctamente, false si hay alg�n error.
	 */
	public static boolean addCompra(String MAIL) {
		try {
			double precio = 0.0;
			for (Producto p : Cesta.lastCompra.get(MAIL)) {
				precio += p.getPrecio();
			}
			Date fecha = new Date(System.currentTimeMillis());
			pstmt = con.prepareStatement("INSERT INTO COMPRA VALUES ('" + MAIL + "','" + precio + "','" + Cesta.lastCompra.get(MAIL).size() + "','" + fecha + "')");
			pstmt.executeUpdate();
			VentanaTienda.logger.log(Level.INFO, "Nueva compra: " + MAIL + ". A�adida correctamente");
			return true;
		} catch (SQLException e) {
			VentanaTienda.logger.log(Level.SEVERE, e.toString());
			return false;
		}
	}
	
	/**
	 * Lee las compras de la tabla COMPRA
	 * @return La suma del los precios de todas las compras, 0 si hay alg�n error.
	 */
	public static double getPrecioCompras() {
		try {
			pstmt = con.prepareStatement("SELECT SUM(PRECIO) AS DINERO_TOTAL FROM COMPRA");
			ResultSet rs = pstmt.executeQuery();
			VentanaTienda.logger.log(Level.INFO, "Operaci�n en la base de datos realizada");
			return rs.getDouble("DINERO_TOTAL");
		} catch (SQLException e) {
			VentanaTienda.logger.log(Level.SEVERE, e.toString());
			return 0;
		}
	}
	
	/**
	 * Lee las compras de la tabla COMPRA.
	 * @return El n�mero de compras, null si hay alg�n error.
	 */
	public static Integer getNumeroCompras() {
		try {
			pstmt = con.prepareStatement("SELECT COUNT(*) AS COMPRAS_TOTALES FROM COMPRA");
			ResultSet rs = pstmt.executeQuery();
			VentanaTienda.logger.log(Level.INFO, "Operaci�n en la base de datos realizada");
			return rs.getInt("COMPRAS_TOTALES");
		} catch (SQLException e) {
			VentanaTienda.logger.log(Level.SEVERE, e.toString());
			return null;
		}
	}

	/**
	 * Lee las compras de la tabla COMPRA.
	 * @return Total de clientes/usuarios, null si hay alg�n error.
	 */
	public static Integer getTotalClientes() {
		try {
			pstmt = con.prepareStatement("SELECT COUNT(*) AS CLIENTES FROM USER");
			ResultSet rs = pstmt.executeQuery();
			VentanaTienda.logger.log(Level.INFO, "Operaci�n en la base de datos realizada");
			return rs.getInt("CLIENTES");
		} catch (SQLException e) {
			VentanaTienda.logger.log(Level.SEVERE, e.toString());
			return null;
		}
	}

}
