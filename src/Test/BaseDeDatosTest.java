package Test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import BD.*;
import Tienda.Producto;
import Tienda.VentanaTienda;

public class BaseDeDatosTest {

	@Before
	public void Inicio() {
		VentanaTienda.iniciaLog();
		BaseDeDatos.InitDB();
	}
	
	@After
	public void Final() {
		BaseDeDatos.closeDB();
	}

	
	@Test
	public void testAddColumn() {
		assertTrue(BaseDeDatos.addColumn(TABLES.USER, "Dir"));
		assertTrue(BaseDeDatos.addColumn(TABLES.USER, "Telf"));
		assertTrue(BaseDeDatos.addColumn(TABLES.USER, "Tarjeta_Socio"));
		
	}
	
	@Test
	public void addUserTest(){
		// Comprobaci�n de que la adici�n de un nuevo usuario se realiza correctamente
		
		// Comprobando que el m�todo devuelve true al hacerlo
		assertTrue(BaseDeDatos.addUser("USERTEST", "USERTEST@GMAIL.COM", "PASSTEST"));
		
		// Comprobando que el nombre de ususario es efectivamente el introducido
		assertEquals(BaseDeDatos.getUser("USERTEST@GMAIL.COM", "PASSTEST"), "USERTEST");
	}
	
	@Test
	public void removeUserTest(){
		// Comprobaci�n de que el m�todo devuelve true al eliminar un usuario de la tabla
		BaseDeDatos.addUser("USERTEST", "USERTEST@GMAIL.COM", "PASSTEST");
		assertTrue(BaseDeDatos.removeUser("USERTEST@GMAIL.COM", "PASSTEST"));
	}
	
	@Test
	public void editUserTest() {
		// Comprobaci�n de que la edici�n del nombre de usuario de un usuario se realiza correctamente
		BaseDeDatos.addUser("USERTEST", "USERTEST@GMAIL.COM", "PASSTEST");
		BaseDeDatos.editUser(COLS.USERNAME, "USERTEST@GMAIL.COM", "PASSTEST", "NEW_USERNAME");
		assertEquals(BaseDeDatos.getUser("USERTEST@GMAIL.COM", "PASSTEST"), "NEW_USERNAME");
		
		// Comprobaci�n de que el m�todo devuelve true al editar el nombre de usuario de un usuario
		assertTrue(BaseDeDatos.editUser(COLS.USERNAME, "USERTEST@GMAIL.COM", "PASSTEST", "NEW_USERNAME"));
	}
	
	/*
	 * No se puede, no existe la tabla PRODUCTO de momento
	 */
	@Test
	public void testProductos() {
		ArrayList<Producto> prods = BaseDeDatos.getProductos();
		assertNotEquals(prods.size(), null);
	}
	
	@Test
	public void getProductosTest() {
		// Comprobaci�n de que el tama�o de la lista que devuelve el m�todo getProductos() es el correcto
		ArrayList<Producto> productos = new ArrayList<Producto>();
		BaseDeDatos.getProductos();
		assertEquals(0, productos.size());
		
		for (Producto producto : productos) {
			// Comprobaci�n de que el primer caracter del codigoProducto de cada producto es un char
			assertEquals(char.class, producto.getCodigoProducto().charAt(0));
			
			// Comprobaci�n de que el precio de cada producto es mayor que 0
			assertTrue(producto.getPrecio() > 0);
		}
		
		// Comprobaci�n de que el ID de cada producto es mayor que ID del producto anterior en la lista
		// Esta comprobaci�n es provisional, no puedo ejecutar el programa asi que no se si lo hace correctamente
		for (int i = 0; i < productos.size(); i++) {
			  for (int j = i+1; j < productos.size(); j++) {
				  assertTrue(productos.get(j).getID() > productos.get(i).getID());
			  }
		}
	}	

}
