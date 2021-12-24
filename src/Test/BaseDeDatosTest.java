package Test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import BD.*;
import Tienda.Cesta;
import Tienda.Producto;
import Tienda.VentanaTienda;
import Tienda.Videojuego;

public class BaseDeDatosTest {

	@Before
	public void Inicio() {
		VentanaTienda.iniciaLog();
		BaseDeDatos.InitDB();
		try {
			FileInputStream fis = new FileInputStream(new File("datos.dat"));
			ObjectInputStream ois = new ObjectInputStream(fis);
			ArrayList<Producto> productos = (ArrayList<Producto>) ois.readObject();
			for (Producto p : productos) {
				BaseDeDatos.addProducto(p);
			}
		} catch (IOException | ClassNotFoundException e1) {
			System.err.println("datos.dat no encontrado");
		}
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
		// Comprobación de que la adición de un nuevo usuario se realiza correctamente
		
		// Comprobando que el método devuelve true al hacerlo
		assertTrue(BaseDeDatos.addUser("USERTEST", "USERTEST@GMAIL.COM", "PASSTEST"));
		
		// Comprobando que el nombre de ususario es efectivamente el introducido
		assertEquals(BaseDeDatos.getUser("USERTEST@GMAIL.COM", "PASSTEST"), "USERTEST");
	}
	
	@Test
	public void removeUserTest(){
		// Comprobación de que el método devuelve true al eliminar un usuario de la tabla
		BaseDeDatos.addUser("USERTEST", "USERTEST@GMAIL.COM", "PASSTEST");
		assertTrue(BaseDeDatos.removeUser("USERTEST@GMAIL.COM", "PASSTEST"));
	}
	
	@Test
	public void editUserTest() {
		// Comprobación de que la edición del nombre de usuario de un usuario se realiza correctamente
		BaseDeDatos.addUser("USERTEST", "USERTEST@GMAIL.COM", "PASSTEST");
		BaseDeDatos.editUser(COLS.USERNAME, "USERTEST@GMAIL.COM", "PASSTEST", "NEW_USERNAME");
		assertEquals(BaseDeDatos.getUser("USERTEST@GMAIL.COM", "PASSTEST"), "NEW_USERNAME");
		
		// Comprobación de que el método devuelve true al editar el nombre de usuario de un usuario
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
		// Comprobación de que el tamaño de la lista que devuelve el método getProductos() es el correcto
		ArrayList<Producto> productos = BaseDeDatos.getProductos();
		//Sabiendo que son 15 los pridyctos que hemos anyadido...
		assertEquals(15, productos.size());
		
		for (Producto producto : productos) {
			// Comprobación de que el codigo del producto es un String
			assertEquals(String.class.getName(), producto.getCodigoProducto().getClass().getName());
			
			// Comprobación de que el precio de cada producto es mayor que 0
			assertTrue(producto.getPrecio() > 0);
		}
		
		// Comprobación de que el ID de cada producto es mayor que ID del producto anterior en la lista
		for (int i = 0; i < productos.size(); i++) {
			  for (int j = i+1; j < productos.size(); j++) {
				  assertTrue(productos.get(j).getID() > productos.get(i).getID());			  
			  }
		}
		
		// Comprobación de que el precio de cada producto es mayor que precio del producto anterior en la lista
		for (int i = 0; i < productos.size(); i++) {
			  for (int j = i + 1; j < productos.size(); j++) {
				  assertTrue(productos.get(j).getPrecio() > productos.get(i).getPrecio());
				 }
		}
	}
	
	@Test
	public void addProductoTest() {
		// Comprobación de que la adición de un nuevo producto se realiza correctamente
		
		// Comprobando que el método devuelve true al hacerlo
		assertTrue(BaseDeDatos.addProducto(new Videojuego("V545", "XBOX ONE", 399.99, "Microsoft")));
				
		// Comprobando que el producto es efectivamente el introducido
		assertEquals(BaseDeDatos.getProducto("XBOX ONE"), "V545");
	}
	
	@Test
	public void getMasCaroTest(){
		ArrayList<Producto> productos = BaseDeDatos.getProductos();
		double mayor = BaseDeDatos.getMasBarato().getPrecio();
		
		for (Producto p : productos) {
			if(p.getPrecio()< mayor) {
				mayor = p.getPrecio();
				//	Comprobación de que getMasCaro() efectivamente devuelve el producto más caro
				assertEquals(mayor, BaseDeDatos.getMasCaro().getPrecio(), 0.0001);
			}
		}
	}
	
	@Test
	public void getMasBaratoTest(){
		ArrayList<Producto> productos = BaseDeDatos.getProductos();
		double menor = BaseDeDatos.getMasCaro().getPrecio();
		
		for (Producto p : productos) {
			if(p.getPrecio()> menor) {
				menor = p.getPrecio();
				//	Comprobación de que getMasBarato() efectivamente devuelve el producto más barato
				assertEquals(menor, BaseDeDatos.getMasBarato().getPrecio(), 0.0001);
			}
		}
	}
}
