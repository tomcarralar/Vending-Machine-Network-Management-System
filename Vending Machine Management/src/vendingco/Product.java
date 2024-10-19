package vendingco;
import java.time.*;

import java.time.format.DateTimeFormatter;
/**
 * Tipo de datos que implementa todas las características y funcionalidades relativas a un producto. 
 * @author tomcarr
 * @author javiram
 */

/**
 * Product clase tiene asociado: 
 * Un nombre, que debe ser un cadena (String)
 * Un precio
 * Una fecha de consumo preferente, que debe ser un string de la forma: "num_dia(guion)num_mes(guion)num_año"
 * Un codigo de producto (tipo de producto) UPC/**
 * 
 * @author tomcarr
 * @author javiram
 */
public class Product {
	
	private String name; 
	private double precio; 
	private LocalDate cadu;
	private String upc; 
	
	/**
	 * Constructor de producto. 
	 * Crea un producto a partir de las siguientes datos del mismo. 
	 * @param name, nombre del producto. 
	 * @param cadUPC, string que contiene todos los 11 de los 12 digitos del UPC
	 * @param precio, cantida de dinero que cuesta el producto 
	 * @param cadu, fecha de consumición preferente del producto en cuestion.
	 * @throws IllegalArgumentException cuando {@code name == ""}
	 * @throws IllegalArgumentException cuando {@code name == null}
	 * @throws IllegalArgumentException cuando {@code precio < 0}
	 * @throws IllegalArgumentException cuando {@code cadu == null}
	 * @throws IllegalArgumentException cuando {@code cadu == ""}
	 * @throws IllegalArgumentException cuando {@code cadu representa una fecha anterior a la fecha del dispositivo}
	 * @throws IllegalArgumentException cuando {@code cadUPC == null} 
	 * @throws IllegalArgumentException cuando {@code CadUPC == "" or CadUPC contiene solo espacios}
	 * @throws IllegalArgumentException cuando {@code cadUPC contiene caracteres o su longitud es distinta de 11 u 12}
	 */
	public Product (String name, double precio, String cadu, String cadUPC) {
		setName(name);
		 
		setPrecio(precio); 
		 
		setCadu(cadu);
		 
		setUpc(cadUPC);
		  
	}
	/**
	 * Constructor de producto sin argumentos
	 * Crea un producto, cuando el usuario no envía los valores de sus campos. 
	 * Se estable el nombre como cadena vacia, el precio igual a 0, fecha de caducidad el 1 de enero de 2130
	 * y el upc = "00000000000"
	 */
	public Product () {
		setName("Libre");
		setPrecio(0); 
		setCadu("1-1-2130"); 
		setUpc("100000000007"); 
	}
	
	/**
	 * Construcutor de copia de producto. 
	 * @return copy, nuevo producto que es una copia del otro. 
	 */
	public Product copyProduct() {
		Product copy = new Product(); 
		
		copy.setName(getName());
		copy.setPrecio(getPrecio()); 
		copy.setCadu(getCadu());
		copy.setUpc(getUpc());
		
		return copy;
	}
	
	/**
	 * Recibe el String del UPC y calcula el UPC entero
	 * @param cadenaUPC, el string del upc insertado por el cliente que puede tener 11 u 12 digitos
	 * @return cadenaUPC, String del UPC completo: 11 caracteres del introducidos por el usuario + el 12avo calculado por la funcion.  
	 * Aclaraciones sobre el UPC: 
	 * 	UPC es un numero de 12 digitos caracterizados por: 
	 * 	Digitos 6 a 9 digitos: forman el codigo de la compania. 
	 * 	Digitos 6 a 11: se utilizan para identificar el producto
	 * 	Digito nº 12: digito de control, obtecion
	 * 		suma = n1*(3) + n2*(1) + n3*(3) + n4*(1) + n5*(3) + n6*(1) + n7*(3) + n8*(1) + n9*(3) + n10*(1) + n11*(3)
	 * 			(impares por 3, pares por 1)
	 * 		Obetenemos el multiplo de 10 cercano a "suma", si la suma es exacta ese, sino el proximo, calculada "suma" previamente 
	 * 		El digito doceavo del UPC será el valor absoluto de la resta de "suma" menos el multiplo que acabamos de clacular: digito_doceavo = |suma - multiplo_de_10|
	 * @throws IllegalArgumentException cuando {@code cadenaUPC represente un numero inferior al 100000000007}
	 * @throws IllegalArgumentException cuando {@code cadenaUPC == null} 
	 * @throws IllegalArgumentException cuando {@code cadenaUPC == "" or CadUPC contiene solo espacios}
	 * @throws IllegalArgumentException cuando {@code cadenaUPC contiene caracteres o su longitud es distinta de 11 u 12}
	 */
	public String generateUPC (String cadenaUPC) {
		int suma = 0, multiplo_10_mas_cercano;
		int doceavo_digito; 
		if (cadenaUPC == null) throw new IllegalArgumentException("upc no puede ser null");
		else if (cadenaUPC == "" || stringVacio(cadenaUPC)) throw new IllegalArgumentException("upc no puede ser vacio");
		else if (cadenaUPC.charAt(0) == 48) throw new IllegalArgumentException("El upc no puede ser inferior a 100000000007");
		else if (!validaUPC(cadenaUPC))
				throw new IllegalArgumentException("El UPC introucido no es válido");
		else {
		
			for(int i = 0; i < 11; i++) {
			
				doceavo_digito = (int)cadenaUPC.charAt(i) - 48;			// en este bucle se utiliza la variable local doceavo_digito por no crear otra 
			
				if(i%2 == 0) {											// Cuando sea resto 0, la posición será impar (*3), cuando sea 1 impar (*1)
					suma = suma + doceavo_digito*3;
				}else { suma = suma + doceavo_digito;	}	
			}
		
			if (suma%10 > 0) {											// Si el resto es mayor que 0, debemos coger el siguiente multiplo. 
				multiplo_10_mas_cercano = suma + (10-suma%10);
				
			}else { multiplo_10_mas_cercano = suma - suma%10;}			// Caso contrario para obtener el multiplo restamos a la variable suma el resto de dividirla por 10. 
			
			doceavo_digito = suma - multiplo_10_mas_cercano;			// El doceavo digito será el valor absoluto de suma - el multiplo
			
			if (doceavo_digito < 0)										
				doceavo_digito = doceavo_digito*(-1);
			
			if (doceavo_digito != (cadenaUPC.charAt(11)-48))
				throw new IllegalArgumentException("El ultimo digito del UPC no es valido");
			else
				return cadenaUPC;
		}
	}
	
	/**
	 * Consulta el nombre del producto en cuestion. 
	 * @return el nombre del producto. 
	 */
	public String getName() {
		return name;
	}

	/**
	 * Aztualiza el nombre del producto en cuestion 
	 * @param name contiene el nuevo nombre del producto 
	 * @throws IllegalArgumentException cuando {@code name == null}
	 * @throws IllegalArgumentException cuando {@code name == "" or CadUPC contiene solo espacios}
	 */
	public void setName(String name) {
		if (name == null) throw new IllegalArgumentException("name no puede ser null");
		else 	if (name == "" || stringVacio(name)) throw new IllegalArgumentException("name no puede ser vacia");
				else this.name = name;
	}

	/**
	 * Consulta el upc del producto en cuestion. 
	 * @return upc en formato String
	 */
	public String getUpc() {
		return upc; 
	}

	/**
	 * Actualiza el upc del producto en cuestion, llamando al a la función generadora del UPC. 
	 * @param upc, string de los primeros 11 digitos del upc
	 * @throws IllegalArgumentException cuando {@code upc == null}
	 * @throws IllegalArgumentException cuando {@code upc == "" or upc contiene solo espacios}
	 * @throws las excepciones de la funcion generateUPC(), ya que la utiliza
	 * @throws IllegalArgumentException cuando {@code cadenaUPC represente un numero inferior al 100000000007}
	 */
	public void setUpc(String upc) {
		if (upc == null) throw new IllegalArgumentException("Upc no puede ser null");
		else 	
			if (upc == "" || stringVacio(upc)) throw new IllegalArgumentException("Upc no puede ser cadena vacia");
			else {this.upc = generateUPC(upc);}
	}

	/**
	 * Consulta el precio del producto en cuestion. 
	 * @return precio del producto. 
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * Actualiza el precio del producto en cuestion.
	 * @param precio, nuevo precio del producto. 
	 * @throws IllegalArgumentException cuando {@code precio < 0}
	 */
	public void setPrecio(double precio) {
		if (precio < 0) throw new IllegalArgumentException("precio debe ser mayor o igual que 0");
		else this.precio = precio;
	}

	/**
	 * Consulta la fecha de cadudicad del producto en cuestion. 
	 * @return cadu, string que contiene la fecha de caducidad. 
	 */
	public String getCadu() {
		return cadu.format(DateTimeFormatter.ofPattern("d-M-yyyy"));
	}

	/**
	 * precondion que la fecha se introduzca como un String de formato "num_dia(guion)num_mes(guion)num_año"
	 * Actualiza la fecha de caducidad del producto en cuestion. 
	 * @param cadu, nueva fecha de caducidad. 
	 * @throws IllegalArgumentException cuando {@code cadu == null}
	 * @throws IllegalArgumentException cuando {@code cadu == null or cadu contiene solo espacios}
	 * @throws Excepcion cuando {@code fecha que representa cadu no sea posterior a la fecha del sistema}
	 */
	public void setCadu(String cadu) {
		LocalDate hoy = LocalDate.now();
		if (cadu == null) throw new IllegalArgumentException("cadu no puede ser null");
		else if (cadu == "" || stringVacio(cadu)) throw new IllegalArgumentException("cadu no puede ser vacia");
			else {
			if ( hoy.until(LocalDate.parse(cadu, DateTimeFormatter.ofPattern("d-M-yyyy"))).getDays() < 0)
				throw new IllegalArgumentException("Su producto está caducado"); 
			else {
				this.cadu = LocalDate.parse(cadu, DateTimeFormatter.ofPattern("d-M-yyyy"));
			}
		}
	}
	
	/**
	 * Determina si la cadena introducida por el usuario para el UPC puede ser u no válida. 
	 * @param cadupc, cadena que contiene los primeros 11 dígitos del UPC 
	 * @return false si no se trata de un UPC correcto y true en caso contrario 
	 * upc será incorrecto cuando alguno de sus caracteres no sea un entero positivo pero también cuando la cadena tenga
	 * un numero de caracteres diferente de 11. 
	 */
	private Boolean validaUPC(String cadupc) {
		if (cadupc.length() != 12) return false; 
		
		for(int i = 0;  i < cadupc.length(); i++ )
			if ((int)cadupc.charAt(i) < 48 || (int)cadupc.charAt(i) > 57 )
				return false;
		
		return true; 
		
	}
	/*
	 * Determina si dos prodcutos tienen el mismo UPC
	 * @return true en caso correcto, false en caso de no tener el mismo UPC 
	 * @throws IllegalArgumentException cuando {@code otro_producto == null}
	 */
	public Boolean equalsUPC(Product otro_producto) {
		if (otro_producto == null)
			throw new IllegalArgumentException("El producto no puede ser null"); 
		else {if ( getUpc().equals(otro_producto.getUpc())) {
					return true;
				}else
					return false; 
		}
	}
	
	private boolean stringVacio(String cad) {
		boolean stringVacio = true; 
		for (int i= 0; i < cad.length(); i++) {
			if (cad.charAt(i) != 32) 
				stringVacio = false; 
		}
		return stringVacio; 
	}
		
	
	
}