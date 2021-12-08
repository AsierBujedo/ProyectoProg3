package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import BD.BaseDeDatos;
import Tienda.Producto;
import Utils.OtherUtils;

public class OtherUtilsTest {
	
	@Before
	public void Inicio() {
		BaseDeDatos.InitDB();
		Tienda.VentanaTienda.iniciaLog();
	}
	
	@After
	public void Final() {
		BaseDeDatos.closeDB();
	}
	
	
// 	Null porque todavia no hay productos
//	@Test
//	public void testBuscarEnLista() {
//		ArrayList<Producto> productos = BaseDeDatos.getProductos();
//		Producto producto = OtherUtils.buscarEnLista(productos, 1, 0);
//		// Comprobación de que existe en la lista el producto con el nombre que insertemos
//		assertEquals(producto.getNombre(), "");
//	}
	
	
//	Todavía no hay productos
//	@Test
//	public void testOrdenaLista() {
//		ArrayList<Producto> productos = BaseDeDatos.getProductos();
//		ArrayList<Producto> productos2 = OtherUtils.ordenaLista(productos);
//		double mayor = 0;
//		for (Producto p : productos2) {
//			if(p.getPrecio()> mayor) {
//				mayor = p.getPrecio();
//				//	Comprobación de que está ordenado y devuelve true si el siguiente producto tiene un precio mayor
//				assertTrue(true);
//			}else {
//				//	Comprobación que devuelve false en caso contrario
//				assertTrue(false);
//				break;
//			}
//			
//			
//		}
//	}

}
