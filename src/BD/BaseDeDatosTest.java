package BD;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Tienda.Producto;

public class BaseDeDatosTest {

	@Before
	public void Inicio() {
		BaseDeDatos.InitDB();
		Tienda.VentanaTienda.iniciaLog();
	}
	
	@After
	public void Final() {
		BaseDeDatos.closeDB();
	}

	
//	@Test
//	public void testAddColumn() {
//		assertTrue(BaseDeDatos.addColumn(TABLES.USER, null));
//		assertTrue(BaseDeDatos.addColumn(TABLES.USER, null));
//		assertTrue(BaseDeDatos.addColumn(TABLES.USER, null));
//		
//	}
	
	@Test
	public void testAddUser(){
		BaseDeDatos.addUser("test1", "test2", "test3");
		assertEquals(BaseDeDatos.getUser("test2", "test3"), "test1");
	}
	
	@Test
	public void testRemoveUser(){
		BaseDeDatos.addUser("test1", "test2", "test3");
		assertTrue(BaseDeDatos.removeUser("test2", "test3"));
	}
	
	@Test
	public void testEditUser() {
		BaseDeDatos.addUser("test1", "test2", "test3");
		BaseDeDatos.editUser(COLS.USERNAME, "test2", "test3", "test4");
		assertEquals(BaseDeDatos.getUser("test2", "test3"), "test4");
	}
	
	
	/*
	 * No se puede, no existe la tabla PRODUCTO de momento
	 */
//	@Test
//	public void testProductos() {
//		ArrayList<Producto> prods = BaseDeDatos.getProductos();
//		assertNotEquals(prods.size(), null);
//	}
	
	

}
