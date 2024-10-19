package vendingco;

import java.time.*;
import java.time.format.DateTimeFormatter;


/*
 * La clase Vendible es una abstracción. 
 * Cada obejto vendible tiene los siguientes parametros: 
 * 	- nombre
 * 	- precio
 * 	- id
 * 	- caducidad
 * 
 * @author tomcarr
 * @author javiram
 */
public abstract class Vendible {
	
	protected LocalDate cadu; 
	protected static final String FORMATFECHA = "d-M-yyyy";
	private String name; 
	protected double precio;
	protected long id; 
	
	/**
	 * Constructor de vendible
	 * @param name, nombre del vendible que se pretende crear
	 * @throws IllegalArgumentException cuando {@code name == null}
	 * @throws IllegalArgumentException cuando {@code name == ""}
	 * @throws IllegalArgumentException cuando {@code name es una cadena de espacios}
	 */
	protected Vendible(String name) {
		setName(name);
	}
	
	/**
	 * Constructor de copia del objeto vendible 
	 * @return copia del vendible en cuestion 
	 */
	public abstract Vendible copy();
	
	/**
	 * Consulta el precio del objeto vendible
	 * @return precio, el precio del vendible en cuestion 
	 */
	public abstract double getPrecio(); 
	
	/**
	 * Actuaiza el precio de un objeto vendible. 
	 * @param precio, nuevo precio del vendible, si es un pack lo calcula solo. 
	 */
	public abstract void setPrecio(double precio); 
	
	/**
	 * En los paquetes el precio no lo pone el usuario, depende de los precios de sus productos
	 */
	public void setPrecio() {
		setPrecio(0); 
	}
	
	/**
	 * Acutaliza la fecha de caducidad del objeto vendible.
	 * @param caducidad, String que contiene la nueva fecha de caducidad, solo para objetos vendibles
	 *	en los que la fecha no dependa de atributos del propio obejto.  
	 */
	public abstract void setCadu(String caducidad);
	
	/**
	 * En los paquetes la caduciad no lo pone el usuario, depende de sus productos que forman el propio paquete
	 */
	public void setCadu() {
		setCadu("12-2-5323"); 
	}
	
	/**
	 * Consulta la fecha de caducidad del objeto vendible.
	 * @return cadu, string que contiene la fecha de caducidad.  
	 */
	public String getCadu() {
		return cadu.format(DateTimeFormatter.ofPattern(FORMATFECHA));
	}
	
	/**
	 * Consulta el nombre del vendible en cuestion. 
	 * @return el nombre del vendible. 
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Actualiza el nombre del vendible en cuestion 
	 * @param name contiene el nuevo nombre del vendible 
	 * @throws IllegalArgumentException cuando {@code name == null}
	 * @throws IllegalArgumentException cuando {@code name == "" or name contiene solo espacios}
	 */
	public void setName(String name) {
		if (name == null) throw new IllegalArgumentException("name no puede ser null");
		else 	if (name.isEmpty() || stringVacio(name)) throw new IllegalArgumentException("name no puede ser vacia");
				else this.name = name;
	}
	
	/**
	 * Dado un String determina si esta formado solo por espacios. 
	 * @param cad
	 * @return true cuando sea un nombre aceptable
	 * @return false, cuando contenga solo espacios
	 */
	protected boolean stringVacio(String cad) {
		boolean stringVacio = true; 
		for (int i= 0; i < cad.length(); i++) {
			if (cad.charAt(i) != 32) 
				stringVacio = false; 
		}
		return stringVacio; 
	}
	/**
	 * Consulta el id de un vendible 
	 * @return id del vendible en cuestion
	 */
	public long getId() {
		return id; 
	}
	
	/**
	 * Actualiza el id de un vendible.
	 * @param id
	 */
	public abstract void setId(long id);
	
}
