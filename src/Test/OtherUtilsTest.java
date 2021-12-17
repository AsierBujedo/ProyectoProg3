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
import BD.BaseDeDatos;
import Tienda.Cesta;
import Tienda.Producto;
import Utils.OtherUtils;

public class OtherUtilsTest {
	
	@SuppressWarnings("unchecked")
	@Before
	public void Inicio() {
		BaseDeDatos.InitDB();
		Tienda.VentanaTienda.iniciaLog();
		try {
			FileInputStream fis = new FileInputStream(new File("datos.dat"));
			ObjectInputStream ois = new ObjectInputStream(fis);
			Cesta.cesta = (ArrayList<Producto>) ois.readObject();
			for (Producto p : Cesta.cesta) {
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
	public void testBuscarEnLista() {
		ArrayList<Producto> productos = BaseDeDatos.getProductos();
		Producto producto = OtherUtils.buscarEnLista(productos, 1, 0);
		// Comprobación de que existe en la lista el producto con el nombre que insertemos
		assertEquals(producto.getNombre(), productos.get(0).getNombre());
	}
	
	
	@Test
	public void testOrdenaLista() {
		ArrayList<Producto> productos = BaseDeDatos.getProductos();
		ArrayList<Producto> productos2 = OtherUtils.ordenaLista(productos);
		double mayor = 0;
		for (Producto p : productos2) {
			if(p.getPrecio()> mayor) {
				mayor = p.getPrecio();
				//	Comprobación de que está ordenado y devuelve true si el siguiente producto tiene un precio mayor
				assertTrue(true);
			}else {
				//	Comprobación que devuelve false en caso contrario
				assertTrue(false);
				break;
			}
			
			
		}
	}

}
