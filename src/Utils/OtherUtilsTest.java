package Utils;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import BD.BaseDeDatos;
import Tienda.Producto;

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
	
	
// Null porque todavia no hay productos
//	@Test
//	public void testBuscarEnLista() {
//		ArrayList<Producto> prods = BaseDeDatos.getProductos();
//		Producto prod = OtherUtils.buscarEnLista(prods, 1, 0);
//		assertEquals(prod.getNombre(), "");
//	}
	
	
// Todavía no hay productos
//	@Test
//	public void testOrdenaLista() {
//		ArrayList<Producto> prods = BaseDeDatos.getProductos();
//		ArrayList<Producto> prods2 = OtherUtils.ordenaLista(prods);
//		double mayor = 0;
//		for (Producto p : prods2) {
//			if(p.getPrecio()> mayor) {
//				mayor = p.getPrecio();
//				assertTrue(true);
//			}else {
//				assertTrue(false);
//				break;
//			}
//			
//			
//		}
//	}

}
