package Tienda;

import java.util.ArrayList;

import javax.swing.*;

import Utils.OtherUtils;
import Utils.RoundedBorder;

import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Cesta {
	protected static ArrayList<Producto> cesta = new ArrayList<Producto>();

	public ArrayList<Producto> getCesta() {
		return cesta;
	}

	public void datosPrueba() {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream("datos.dat");
			@SuppressWarnings("resource")
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			ArrayList<Producto> list = new ArrayList<Producto>();
			list.add(new Libro(123, "La casa de los esp�ritus", 19.99, "Editorial", "Isabel Allende", "Editorial",
					Color.BLUE, true));
			list.add(new Libro(124, "Harry Potter", 24.99, "Editorial", "J. K. Rowling", "Editorial", Color.RED, true));
			list.add(new Ordenador(223, "Lenovo Ideapad", 500.00, "Lenovo", "Ideapad 2", 500, 8, 2));
			list.add(new Zapatilla(323, "Nike Air", 99.99, "Nike", null, 42.00, Color.BLACK, Pais.CHINA));
			list.add(new Sudadera(423, "Sudadera", 22.99, "Billabong", Talla.M, Color.GREEN, true, Pais.CHINA));
			oos.writeObject(list);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setCesta(ArrayList<Producto> cesta) {
		Cesta.cesta = cesta;
	}

	public void addToCesta(Producto p) {
		Cesta.cesta.add(p);
	}

	public JPanel panelCesta() {
		JPanel panel = new JPanel();
		JTextField buscarProd = new JTextField(10);
		buscarProd.setBorder(new RoundedBorder(7));
		JButton buscar = new JButton("Buscar en carro");
		buscar.setBorder(new RoundedBorder(7));
		panel.add(buscarProd, BorderLayout.NORTH);
		panel.add(buscar, BorderLayout.CENTER);

		buscar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				System.out.println(new OtherUtils().buscarEnLista(cesta, Integer.valueOf(buscarProd.getText()), 0));
				new OtherUtils().buscarEnLista(cesta, 1, 0);
				System.out.println(new OtherUtils().ordenaLista(cesta));
			}
		});
		return panel;
	}
}