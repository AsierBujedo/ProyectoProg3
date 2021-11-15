package BD;

import java.sql.*;

public class BaseDeDatos {
	private static Connection con;
	private Statement stmt;
	private static PreparedStatement pstmt;
	private static int USER_IDS = 1;

	public boolean InitDB() {
		/*
		 * Este m�todo deber� ejecutarse antes de comenzar a usar la base de datos. La
		 * base de datos se iniciar� y la funci�n devolver� un true si todo ha ido bien,
		 * de lo contrario devolver� un false.
		 */
		try {
			con = DriverManager.getConnection("jdbc:sqlite:ajedrez.db");
			stmt = con.createStatement();
			stmt.executeUpdate("DROP TABLE IF EXISTS USERS");
			stmt.executeUpdate("DROP TABLE IF EXISTS USER_STATS");
			stmt.executeUpdate(
					"CREATE TABLE USERS (USERNAME string NOT NULL, MAIL string NOT NULL, PASS string NOT NULL, USER_ID int PRIMARY KEY NOT NULL)");
			stmt.executeUpdate(
					"CREATE TABLE USER_STATS (PLAYED int DEFAULT 0, WON int DEFAULT 0, USER_ID int NOT NULL, FOREIGN KEY (USER_ID) REFERENCES USERS (USER_ID))");

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean addColumn(TABLES TABLE, String name) {
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
			e.printStackTrace();
			return false;
		}
	}

	public boolean removeColumn(TABLES TABLE, String COLUMN) {

		/*
		 * Mediante este m�todo, se elimina una columa, identificada por su nombre y por
		 * la tabla que la posee. No se podr�n borrar las columnas por defecto.
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
			e.printStackTrace();
			return false;
		}
	}

	public boolean removeUser(String MAIL, String PASS) {

		/*
		 * Mediante este m�todo, se elimina un usuario, identificado por su mail y su
		 * password. No por su ID.
		 */

		try {
			pstmt = con.prepareStatement("DELETE FROM USERS WHERE MAIL = '" + MAIL + "' AND PASS = '" + PASS + "'");
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean addUser(String USERNAME, String MAIL, String PASS) {
		/*
		 * M�todo que permite a�adir un nuevo usuario a la tabla "USERS" . Se recibe su
		 * nombre, mail y correo, autom�ticamente se le asigna un ID como c�digo
		 * identificativo �nico, esta es su PRIMARY KEY.
		 */
		try {
			pstmt = con.prepareStatement("INSERT INTO USERS (USERNAME, MAIL, PASS, USER_ID) VALUES ('" + USERNAME
					+ "','" + MAIL + "','" + PASS + "','" + USER_IDS + "')");
			pstmt.executeUpdate();
			USER_IDS++;
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean closeDB() {
		// Deber� cerrarse siempre la conexi�n con la base de datos mediante este
		// m�todo.
		try {
			con.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public String getUser(String MAIL, String PASS) {
		try {
			pstmt = con.prepareStatement("SELECT USERNAME FROM USERS WHERE MAIL = '"+MAIL+"' AND PASS = '"+PASS+"'" );
			ResultSet rs = pstmt.executeQuery();
			return rs.getString("USERNAME");
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error";
		}
	}
	

	public boolean editUser(COLS COL, String MAIL, String PASS, String NEW) {

		/*
		 * Este m�todo permite editar un usuario de la tabla "USERS" mediante el enum
		 * "COLS", se permite el cambio de nombre de usuario, mail y/o la password. Si
		 * el cambio es efectivo, recibiremos true, de lo contrario, el m�todo devolver�
		 * false.
		 */

		try {
			pstmt = con.prepareStatement("UPDATE USERS SET " + COL.toString() + "= '" + NEW + "' WHERE MAIL = '" + MAIL
					+ "' AND PASS = '" + PASS + "'");
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void main(String[] args) {
		BaseDeDatos bd = new BaseDeDatos();
		bd.InitDB();

		// COLOCAR AQU� LAS PRUEBAS CON LA BASE DE DATOS

		
		bd.closeDB();

	}
}
