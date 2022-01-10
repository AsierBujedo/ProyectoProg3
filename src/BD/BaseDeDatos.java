package BD;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;

import Tienda.*;
	/**Clase BaseDeDatos.
	 * Gestiona la base de datos de la aplicaci�n.
	 * @author GR08
	 */
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
					"CREATE TABLE USER (USER_ID int ALTERNATIVE KEY NOT NULL, USERNAME varchar(100) NOT NULL, MAIL varchar(100) PRIMARY KEY NOT NULL, PASS varchar(100) NOT NULL, TELF int(15) DEFAULT NULL, GENERO varchar(10) DEFAULT NULL, NACIMIENTO date DEFAULT NULL, DIR varchar(100) DEFAULT NULL)");
			stmt.executeUpdate(
					"CREATE TABLE PRODUCTO (COD_PRODUCTO varchar(15) PRIMARY KEY NOT NULL, NOMBRE varchar(100), PRECIO double, MARCA varchar(100))");
			stmt.executeUpdate(
					"CREATE TABLE COMPRA (MAIL varchar(100) NOT NULL, PRECIO double, TOTAL_PRODS int, FECHA date, FOREIGN KEY (MAIL) REFERENCES USER (MAIL))");
			stmt.executeUpdate("INSERT INTO USER(USER_ID, USERNAME, MAIL, PASS) VALUES (0, 'admin', 'admin@gmail.com', 12345)"); // Fila de prueba para	la tabla USER														
			VentanaTienda.logger.log(Level.INFO, "Creaci�n de tablas correcta");
			BaseDeDatos.editUserSpecs("admin@gmail.com", "admin", 666666666, Genero.MASCULINO, new Date(System.currentTimeMillis()), "Avda de Las Universidades 24, 48007, Bilbao, Bizkaia");
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
	 * @param MAIL Correo electr�nico del usuario.
	 * @param PASS Contrase�a del usuario.
	 * @return true si el usuario se elimina correctamente, false si hay alg�n error.
	 */
	// El usuario se identifica por su correo electr�nico y su contrase�a, no por su identificativo �nico
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
	 * @param MAIL Correo electr�nico del usuario.
	 * @param PASS Contrase�a del usuario.
	 * @return true si el usuario se a�ade correctamente, false si hay alg�n error.
	 */
	// Autom�ticamente se le asigna un ID como c�digo identificativo �nico, su PRIMARY KEY
	public static boolean addUser(String USERNAME, String MAIL, String PASS, Integer TELF, Genero GENERO, Date date, String DIR) {
		try {
			pstmt = con.prepareStatement("INSERT INTO USER (USERNAME, MAIL, PASS, USER_ID, TELF, GENERO, NACIMIENTO, DIR) VALUES ('" + USERNAME + "','"
					+ MAIL + "','" + PASS + "','" + USER_IDS + "',"+TELF+",'"+GENERO.toString()+"','"+date+"','"+DIR+"')");
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
	 * Obtiene el nombre de usuario mediante su correo electr�nico y su contrase�a.
	 * @param MAIL Correo electr�nico del usuario.
	 * @param PASS Contrase�a del usuario.
	 * @return nombre de usuario del usuario, "Error" si hay alg�n error.
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
	
	/**
	 * Obtiene el correo electr�nico del usuario mediante su nombre de usuario.
	 * @param name Nombre de usuario.
	 * @return correo electr�nico del usuario, "Error" si hay alg�n error.
	 */
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
	 * @param MAIL Correo electr�nico del usuario.
	 * @param PASS Contrase�a del usuario.
	 * @param NEW Nuevo dato para almacenar.
	 * @return true si el cambio es efectivo, false si hay alg�n error.
	 */
	// Se permite el cambio de nombre de usuario, correo electr�nico y/o password
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
	
	/**
	 * Edita el nombre de usuario, el n�mero de tel�fono, el sexo, la fecha de nacimiento y la direcci�n del usuario mediante su correo electr�nico.
	 * @param MAIL Correo electr�nico del usuario.
	 * @param USERNAME Nombre de usuario.
	 * @param TELF N�mero de tel�fono del usuario.
	 * @param SEXO Sexo del usuario.
	 * @param NACIMIENTO Fecha de nacimiento del usuario.
	 * @param DIR Direcci�n del usuario.
	 * @return true si el cambio es efectivo, false si hay alg�n error.
	 */
	public static boolean editUserSpecs(String MAIL, String USERNAME, Integer TELF, Genero GENERO, Date NACIMIENTO, String DIR) {
		try {
			pstmt = con.prepareStatement("UPDATE USER SET " + "USERNAME" + "= '" + USERNAME + "' WHERE MAIL = '" + MAIL+"';");
			pstmt.executeUpdate();
			pstmt = con.prepareStatement("UPDATE USER SET " + "TELF" + "= " + TELF + " WHERE MAIL = '" + MAIL+"';");
			pstmt.executeUpdate();
			pstmt = con.prepareStatement("UPDATE USER SET " + "GENERO" + "= '" + GENERO.toString() + "' WHERE MAIL = '" + MAIL+"';");
			pstmt.executeUpdate();
			pstmt = con.prepareStatement("UPDATE USER SET " + "NACIMIENTO" + "= '" + NACIMIENTO + "' WHERE MAIL = '" + MAIL+"';");
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
	 * Lee los productos de la tabla PRODUCTO y los almacena en una lista.
	 * @return lista completa de productos, null si hay alg�n error.
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
			rs.close();
			return productos;
		} catch (SQLException e) {
			VentanaTienda.logger.log(Level.SEVERE, e.toString());
			return null;
		}
	}
	
	/**
	 * Consulta en la base de datos y obtiene el producto m�s barato.
	 * @return producto m�s barato, null si hay alg�n error.
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
			rs.close();
			return new Generico(code, name, precio, marca);

		} catch (SQLException e) {
			VentanaTienda.logger.log(Level.SEVERE, e.toString());
			return null;
		}
	}
	
	/**
	 * Consulta en la base de datos y obtiene el producto m�s caro.
	 * @return producto m�s caro, null si hay alg�n error.
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
			rs.close();
			return new Generico(code, name, precio, marca);

		} catch (SQLException e) {
			VentanaTienda.logger.log(Level.SEVERE, e.toString());
			return null;
		}
	}
	
	/**
	 * A�ade un nuevo producto a la tabla PRODUCTO. 
	 * @return true si el producto se a�ade correctamente, false si hay alg�n error.
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
	 * Obtiene el c�digo del producto mediante su nombre.
	 * @param NOMBRE Nombre del producto.
	 * @return c�digo del producto, null si hay alg�n error.
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
	 * @param MAIL Correo electr�nico del usuario.
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
	 * Obtiene la suma total de todos los precios por compra de la tabla COMPRA.
	 * @return precio total de todas las compras, 0 si hay alg�n error.
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
	 * Obtiene el n�mero total de compras de la tabla COMPRA.
	 * @return n�mero total de compras, null si hay alg�n error.
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
	 * Obtiene el n�mero total de usuarios de la tabla USER.
	 * @return n�mero total de usuarios, null si hay alg�n error.
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
		
	/**
	* Obtiene la suma total de todas las cantidades de productos por compra de la tabla COMPRA.
	* @return n�mero total de productos vendidos, null si hay alg�n error.
	*/
	public static Integer getTotalProductos() {
		try {
			pstmt = con.prepareStatement("SELECT SUM(TOTAL_PRODS) AS TOTAL FROM COMPRA");
			ResultSet rs = pstmt.executeQuery();
			VentanaTienda.logger.log(Level.INFO, "Operaci�n en la base de datos realizada");
			return rs.getInt("TOTAL");
		} catch (SQLException e) {
			VentanaTienda.logger.log(Level.SEVERE, e.toString());
			return null;
		}
	}
	
	/**
	 * Obtiene el n�mero tel�fono del usuario mediante su correo electr�nico.
	 * @param MAIL Correo electr�nico del usuario.
	 * @return n�mero de tel�fono del usuario, null si hay alg�n error.
	 */
	public static Integer getUserTelf(String MAIL) {
		try {
			pstmt = con.prepareStatement("SELECT TELF FROM USER WHERE MAIL = '"+ MAIL + "'");
			ResultSet rs = pstmt.executeQuery();
			VentanaTienda.logger.log(Level.INFO, "Operaci�n en la base de datos realizada");
			return rs.getInt("TELF");
		} catch (SQLException e) {
			VentanaTienda.logger.log(Level.SEVERE, e.toString());
			return null;
		}
	}
	
	/**
	 * Obtiene el g�nero del usuario mediante su correo electr�nico.
	 * @param MAIL Correo electr�nico del usuario.
	 * @return g�nero del usuario, null si hay alg�n error.
	 */
	public static Genero getUserGenero(String MAIL) {
		try {
			pstmt = con.prepareStatement("SELECT GENERO FROM USER WHERE MAIL = '"+ MAIL + "'");
			ResultSet rs = pstmt.executeQuery();
			VentanaTienda.logger.log(Level.INFO, "Operaci�n en la base de datos realizada");
			String genero = rs.getString("GENERO");
			return Genero.valueOf(genero);
		} catch (SQLException e) {
			VentanaTienda.logger.log(Level.SEVERE, e.toString());
			return null;
		}
	}
	
	/**
	 * Obtiene la direcci�n del usuario mediante su correo electr�nico.
	 * @param MAIL Correo electr�nico del usuario.
	 * @return direcci�n del usuario, null si hay alg�n error.
	 */
	public static String getUserDir(String MAIL) {
		try {
			pstmt = con.prepareStatement("SELECT DIR FROM USER WHERE MAIL = '"+ MAIL + "'");
			ResultSet rs = pstmt.executeQuery();
			VentanaTienda.logger.log(Level.INFO, "Operaci�n en la base de datos realizada");
			return rs.getString("DIR");
		} catch (SQLException e) {
			VentanaTienda.logger.log(Level.SEVERE, e.toString());
			return null;
		}
	}
	
	/**
	 * Obtiene la fecha de nacimiento del usuario mediante su correo electr�nico.
	 * @param MAIL Correo electr�nico del usuario.
	 * @return fecha de nacimiento del usuario.
	 */
	public static String getUserDate(String MAIL) {
		try {
			pstmt = con.prepareStatement("SELECT NACIMIENTO FROM USER WHERE MAIL = '"+ MAIL + "'");
			ResultSet rs = pstmt.executeQuery();
			VentanaTienda.logger.log(Level.INFO, "Operaci�n en la base de datos realizada");
			return rs.getString("NACIMIENTO");
		} catch (SQLException e) {
			VentanaTienda.logger.log(Level.SEVERE, e.toString());
			return null;
		}
	}
	
}
