package Tienda;

public class PersonaPrueba implements DatoParaTabla {
	protected String nombre;
	protected String apellido;
	protected int edad;
	protected String DNI;
	
	// Constructor sin argumentos
		public PersonaPrueba() {
			super();
		}
		
	// Constructor con argumentos
	public PersonaPrueba(String nombre, String apellido, int edad, String DNI) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.DNI = DNI;
	}
	
	// Constructor copia
		public PersonaPrueba(PersonaPrueba p) {
			super();
			this.nombre = p.nombre;
			this.apellido = p.apellido;
			this.edad = p.edad;
			this.DNI = p.DNI;
		}


	@Override
	public Object getValor(int col) {
		switch (col) {
    	case 0: { return nombre; }
    	case 1: { return apellido; }
    	case 2: { return edad; }
    	case 3: { return DNI; }
	}
	return null;
	}

	@Override
	public void setValor(Object valor, int col) {
		try {
	    	switch (col) {
		    	case 0: { nombre = (String) valor; break; }
		    	case 1: { apellido = (String) valor; break; }
		    	case 2: { edad = (int) valor; break; }
		    	case 3: { DNI = (String) valor; break; }
	    	}
    	} catch (Exception e) {
    		// Error en conversión. Intentando asignar un tipo incorrecto
    		e.printStackTrace();
    	}	
	}
}
