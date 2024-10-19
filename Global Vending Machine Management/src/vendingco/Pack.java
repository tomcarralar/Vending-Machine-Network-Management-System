package vendingco;

import java.util.ArrayList;
import java.time.*;
import java.time.format.DateTimeFormatter;
/*
 * @author tomcarr
 * @author javiram
 * Clase que implementa una extension de Vendible, se trata de un paquete de productos y su funcionalidad:
 * 
 * 	Para que un paquete pueda crearse y de hecho existir tiene que tener al menos 2 productos. Ademas,
 *  una vez creado un paquete se pueden annadir y quitar productos siemrpe y cuando se respete esto.  
 *  
 *  Cada paquete tiene asignado un Id numercio natural, el cual debe ser aportartado por el usuario, este identificador
 *  si se implementa tambien nuestra clase VendingMachine no podra ser el mismo para dos paquetes diferentes 
 *  en una misma maquina, aunque si que puede ocurrir que dos maquinas distinas tengan paquetes con el mismo Id. 
 *  
 *  El paquete al ser Vendible tiene una fecha de caducidad asociada, esta es de entre todos los productos 
 * 	que lo forman la mas cercana a la fecha del sistema. Tambien como cualquier vendible tiene asocidado un 
 * 	nombre.
 *  
 *  El precio del paquete se corresponde con la suma de los precios de cada uno de los productos
 *  que lo forman,  aplicandole a esta suma una reduccion del 20 %.
 *  
 * 	Ademas tambien se implementa funcionalidad para poder: saber si un producto pertenece a un paquete, 
 * 	crear una copia del paquete asi como tambien saber si dada una lista de productos, esta contiene dos 
 * 	productos igulaes y por tanto no puede ser el paquete de productos de un PACK. 
 */
public class Pack extends Vendible{
	
	private ArrayList<Product> productList;
	static final  double DISCONT = 0.2;
	/**
	 * Constructor de paquete.
	 * Crea el paquete a partir de los siguientes elementos. 
	 * @param name, nombre que se le quiere poner al paquete
	 * @param componentes, un ArrayList de productos, los cuales no pueden tener el mismo UPC
	 * @param idPack, identificador del paquete, el cual debe ser unico
	 * @throws IllegalArgumentException cuando {@code name null}
	 * @throws IllegalArgumentException cuando {@code name == "" o bien contenga solo espacios}
	 * @throws IllegalArgumentException cuando {@code componentes contenga menos de 2 productos}
	 * @throws IllegalArgumentException cuando {@code dos productos tengan el mismo UPC, esto es sean iguales}
	 * @throws IllegalArgumentException cuando {@code componentes sea null}
	 * @throws IllegalArgumentException cuando {@code idPack este repetido en esa maquina}
	 */
	public Pack( String name, ArrayList <Product> componentes, long idPack) {
		super(name);
		if (componentes == null)
			throw new IllegalArgumentException("El array de productos no debe ser null");
		if(componentes.size() < 2)
			throw new IllegalArgumentException("Un paquete debe contener almenos dos productos"); 
		
		if(unicidadDeProductos(componentes))
			throw new IllegalArgumentException("La lista contiene productos iguales."); 
		
		setPaquete(componentes);
		
		setId(idPack);
		
		setPrecio(0);
		
		setCadu();
	}
	
	/**
	 * Constructor de copia del producto. 
	 * @return copia del producto con el mimso Id. 
	 */
	public Vendible copy() {
		return new Pack(getName(), getPaquete(), getId());
	}

	/**
	 * Consulta la lista de productos que forman el paquete
	 * @return paquete, una copia de la lista de productos del paquete, que no la propia lista del paquete
	 */
	public ArrayList<Product> getPaquete() {
		ArrayList<Product> copiaPaquete = new ArrayList<>(); 
		for (int i = 0; i < productList.size(); i++) {
			copiaPaquete.add((Product)productList.get(i).copy());
		}
		return copiaPaquete;
	}

	/**
	 * Actualiza la lista de productos que forman el paquete
	 * @param paquete, lista de productos que formarán el paquete. 
	 * @throws IllegalArgumentException cuando {@code paquete sea null}
	 * @throws IllegalArgumentException cuando {@code paquete contenga menos de 2 productos}
	 * @throws IllegalArgumentException cuando {@code paquete contenga 2 productos iguales}
	 */
	public void setPaquete(ArrayList<Product> componentes) {
		if (componentes == null) throw new IllegalArgumentException("La lista de productos no puede ser null");
		if (componentes.size() < 2) throw new IllegalArgumentException("Un paquete debe contener almenos 2 productos");
		if (unicidadDeProductos(componentes))throw new IllegalArgumentException("Un paquete no debe contener productos iguales");
		this.productList = componentes;
		setPrecio(0);
	}

	/**
	 * Cosulta el precio del paquete. 
	 * @return precioPack, double que reprenta el precio del paquete. 
	 */
	public double getPrecio() {
		setPrecio(0);
		return precio;
	}

	/**
	 * Actualiza el precio del paquete en funcion de los productos que forman el mimso. 
	 * @param precioPack, precio de la suma de sus productos con una reduccion del 20%. 
	 * @param c, no es necesario que lo introduzca para actualizar el precio, ya que este no 
	 * 	se impone sino que depende de los productos del paquete. 
	 *  Funciona sin poner el parametro c, por el metodo que hay en Vendible
	 */
	public void setPrecio(double c) {
		double precio = 0; 
		for (int i = 0; i < productList.size(); i++) {
			precio = precio + productList.get(i).getPrecio();
		}
		
		precio = precio - DISCONT*precio; 
		
		this.precio = precio;
	}

	/**
	 * Acutualiza el id que reprenta al paquete en cuestion. 
	 * @param idPack, el id que identificara al paquete a partir de ese momento. 
	 * @throws IllegalArgumentException cuando {@code idPack < 0}
	 * @throws IllegalArgumentException cuando {@code idPack ya haya sido asignado a otro paquete}
	 */
	public void setId(long idPack) {
		if (idPack < 0) throw new IllegalArgumentException("El id del paquete no puede ser negativo");
		this.id = idPack;
	}
	/**
	 * Actualiza la fecha de caducidad de un pack.
	 * La fecha de caducidad se corresponde con la de producto que antes caduque.
	 * @param caducidad, no es necesario que lo introduzca para actualizar el precio, ya que este no 
	 * 	se impone sino que depende de los productos del paquete. 
	 *  Funciona sin poner el parametro caduducidad, gracias al metodo de Vendible
	 */
	public void setCadu(String caducidad) {
		cadu = LocalDate.parse(productList.get(0).getCadu(), DateTimeFormatter.ofPattern(FORMATFECHA)); 
		LocalDate cadu2; 
		for (int i = 1; i < productList.size(); i++) {
			cadu2 = LocalDate.parse(productList.get(i).getCadu(), DateTimeFormatter.ofPattern(FORMATFECHA));
			if ( cadu.until(cadu2).getDays() < 0)
				cadu = LocalDate.parse(productList.get(i).getCadu(), DateTimeFormatter.ofPattern(FORMATFECHA)); 
		}
	}

	/**
	 * Actualiza los productos que forman parte de un pack, annadiendo el producto enviado. 
	 * @param newProduct, producto que se quiere añadir al paquete. 
	 * @throws IllegalArgumentException cuando {@code newProduct ya pertenezca al paquete}
	 * @throws IllegalArgumentException cuando {@code newProduct == null}
	 */
	public void addProduct(Product newProduct) {
		if (newProduct == null) throw new IllegalArgumentException("El producto que desea añadir no puede ser null");
		for (int i = 0; i < productList.size(); i++) {
			if (productList.get(i).equalsUPC(newProduct))
					throw new IllegalArgumentException("Un pack no puede contener dos producto iguales."); 
			}	
		productList.add(newProduct); 
		setCadu(null);
	}
	
	/**
	 * Elimina un producto de un paquete, recibiendo como parametro el Upc del producto. 
	 * @param UpcProduct, Upc del producto a eliminar. 
	 * @throws IllegalArgumentException cuando {@code UpcProduct sea null}
	 * @throws IllegalArgumentException cuando {@code UpcProduct no pertenezca al pack}
	 * @throws IllegalArgumentException cuando {@code productList contenga solo dos productos}
	 */
	public void delProduct(String upcProduct) {
		if(upcProduct == null) throw new IllegalArgumentException("Un producot no puede tener upc null");
		if(!haveProduct(upcProduct)) throw new IllegalArgumentException("Dicho producto no esta en el paquete");
		Product productToDel = null; 
		for (int i = 0; i < productList.size(); i++) {
			if (productList.get(i).getUpc().equals(upcProduct))
				productToDel = productList.get(i);  	
		}
		if (productList.size() == 2) throw new IllegalArgumentException("No se puede eliminar el producto porque dejaría de ser un paquete");
		productList.remove(productToDel);
		setCadu(null);
	}
	
	/**
	 * Elimina un producto de un paquete, recibiendo como parametro el propio producto. 
	 * @param UpcProduct, producto a eliminar. 
	 * @throws IllegalArgumentException cuando {@code productToDel sea null}
	 * @throws IllegalArgumentException cuando {@code productToDel no pertenezca al pack}
	 * @throws IllegalArgumentException cuando {@code productList contenga solo dos productos}
	 */
	public void delProduct(Product productToDel) {
		if (productToDel == null) throw new IllegalArgumentException("No existen productos null.");
		if(!haveProduct(productToDel)) throw new IllegalArgumentException("Dicho producto no esta en el paquete");
		delProduct(productToDel.getUpc());
	}
	
	/**
	 * Comprueba si un producto dato contiene u no un producto. 
	 * @return true, cuando el producto forma parte del paquete 
	 * @return false cuando el producto no forma parte del paquete. 
	 */
	public boolean haveProduct(Product productoBuscado) {
		if (productoBuscado == null) throw new IllegalArgumentException("Un producto no puede ser null");
		boolean contenido = false; 
		for (int i = 0; i < productList.size(); i++) {
			if (productoBuscado.equals(productList.get(i))) contenido = true; 
		}
		return contenido;
	}
	/**
	 * Comprueba si un dado un UPC, el paquete contiene un producto con dicho UPC. 
	 * @return true, cuando el producto forma parte del paquete 
	 * @return false cuando el producto no forma parte del paquete. 
	 */
	public boolean haveProduct(String upc) {
		if (upc == null) throw new IllegalArgumentException("El upc null no se corresponde con ningun producto");
		boolean contenido = false; 
		for (int i = 0; i < getPaquete().size(); i++) {
			if(upc.equals(getPaquete().get(i).getUpc())) contenido = true; 
		}
		return contenido; 
	}
	
	/**
	  * Devuelve un hashcode para el objeto
	  * @return hashcode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(precio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + productList.hashCode();
		result = prime * result + (getName().hashCode()); 
		result = prime * result + (getCadu().hashCode());
		return result;
	}

	/**
	 * Implemetacion del metodo de Object equals para la clase Pack, dos paquetes seran iguales cuando 
	 * tengan el mimso nombre, el mimso identificador, fecha de caducidad y los mismos productos. 
	 * @param otroPaquete, paquete a comparar. 
	 * @return true si los paquetes son iguales
	 * @return false si alguno de los campos de los paquetes no es igual. 
	 * @throws IllegalArgumentException cuando {@code otroPaquete == null}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pack otroPaquete = (Pack) obj;
		
		if (!otroPaquete.getName().equals(getName())) return false; 
		
		if (!otroPaquete.getCadu().equals(getCadu())) return false; 
		
		if (otroPaquete.getPrecio() != getPrecio()) return false; 
		
		if (otroPaquete.getPaquete().size() != getPaquete().size()) return false; 
		
		for (int i = 0; i < productList.size(); i++) {
			if(!productList.get(i).equals(otroPaquete.getPaquete().get(i))) return false; 
		}
		
		return true; 
	}
	
	/**
	 * Dada una lista de producto, determina si contiene dos iguales
	 * @return false, si todos son distintos, o true en caso si alguno esta repetido. 
	 */
	private boolean unicidadDeProductos(ArrayList <Product> componentes) {
		for (int i = 0; i < componentes.size(); i++) {
			for (int j = i+1; j < componentes.size(); j++) {
				if (componentes.get(i).equalsUPC(componentes.get(j))) 
					return true; 
			}
		}
		return false; 
	}
}
