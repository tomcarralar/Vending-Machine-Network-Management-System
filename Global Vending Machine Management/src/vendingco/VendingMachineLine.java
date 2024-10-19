package vendingco;

/**
 * Proporciona el funcionamiento de cada una de las lineas de una maquina expendedora de la empresa VendingCo
 * 
 * @author tomcarr
 * @author javiram
 */
public class VendingMachineLine {
	
	private int idlinea;
	private int quantity;
	private Vendible vendible;
	private int maxquantity;
		
	/**
	 * Crear una nueva linea vacia de un determinado producto 
	 * @param id, de la linea
	 * @param vendible, que tiene esta linea
	 * @param maxquantity, profundidad de la linea
	 * @throws IllegalArgumentException cuando {@code maxquantity <= 0}
	 * @throws NullPointerException cuando {@code vendible == null}
	 * @throws IllegalArgumentException cuando {@code id < 0}
	 */
	public VendingMachineLine(int id, Vendible vendible, int maxquantity) {
		setMaxQuantity(maxquantity);
		setQuantity(0);
		setVendible(vendible);
		setIdlinea(id);
	}
	
	/**
	 * Crear una nueva linea con una cantidad indicada de un determinado producto 
	 * @param id, de la linea
	 * @param vendible, que tiene esta linea
	 * @param maxquantity, profundidad de la linea
	 * @param quantity, que tiene inicialmente esta linea
	 * @throws IllegalArgumentException cuando {@code maxquantity <= 0}
	 * @throws IllegalArgumentException cuando {@code quantity > maxquantity}
	 * @throws NullPointerException cuando {@code vendible == null}
	 * @throws IllegalArgumentException cuando {@code id < 0}
	 */
	public VendingMachineLine(int id, Vendible vendible, int maxquantity, int quantity) {
		setMaxQuantity(maxquantity);
		setQuantity(quantity);
		setVendible(vendible);
		setIdlinea(id);
	}
	
	/**
	 * Constructor de copia
	 * @param id, de la linea
	 * @param VendingMachineLine, de la que se realizara la copia
	 * @throws NullPointerException cuando {@code VendingMachineLine == null}
	 * @throws IllegalArgumentException cuando {@code id < 0}
	 */
	public VendingMachineLine(int id, VendingMachineLine VendingMachineLine) {
		if(VendingMachineLine == null) throw new NullPointerException();
		setMaxQuantity(VendingMachineLine.getMaxQuantity());
		setQuantity(VendingMachineLine.getQuantity());
		setVendible(VendingMachineLine.getVendible());
		setIdlinea(id);
	}
	

	/**
	 * Consulta el identificador de esta linea
	 * @return id de la linea
	 */
	public int getIdlinea() {
		return idlinea;
	}
	
	/**
	 * Actualiza el identificador de la linea
	 * @param idlinea
	 * @throws IllegalArgumentException cuando {@code id < 0}
	 */
	public void setIdlinea(int id) {
		if(id < 0) throw new IllegalArgumentException("El identificador tiene que ser positivo");
		this.idlinea = id;
	}

	/**
	 * Consulta la cantidad de producto que queda
	 * @return quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * Actualiza la cantidad de producto que tiene esta linea
	 * @param quantity
	 * @throws IllegalArgumentException cuando {@code quantity < 0}
	 * @throws IllegalArgumentException cuando {@code quantity > maxquantity}
	 */
	public void setQuantity(int quantity) {
		if (quantity < 0) throw new IllegalArgumentException("La cantidad tiene que ser positiva");
		if (quantity > maxquantity) throw new IllegalArgumentException("La cantidad proporcionada no entra en esta linea");
		this.quantity = quantity;
	}

	/**
	 * Consulta la maxima cantidad de producto que puede tener esta linea
	 * @return maxquantity
	 */
	public int getMaxQuantity() {
		return maxquantity;
	}
	
	/**
	 * Se indica cual es la maxima cantidad de producto que puede tener esta linea (solo se puede realizar en su creacion)
	 * @param maxquantity
	 * @throws IllegalArgumentException cuando {@code maxquantity <= 0}
	 */
	private void setMaxQuantity(int maxquantity) {
		if (maxquantity <= 0) throw new IllegalArgumentException("La profundidad de la linea tiene que ser positiva");
		this.maxquantity = maxquantity;
	}
	

	/**
	 * Consulta el vendible que tiene esta linea
	 * @return Vendible que tiene este linea
	 */
	public Vendible getVendible() {
		return vendible;
	}
	
	/**
	 * Actualiza el vendible que se tiene en esta linea
	 * @param vendible que se quiere introducir
	 * @throws NullPointerException cuando {@code vendible == null}
	 */
	public void setVendible(Vendible vendible) {
		if (vendible == null) throw new NullPointerException("Producto no puede ser null"); 
		
		if (vendible.getClass() == Product.class)
		{
			Product tmp = (Product) vendible;
			this.vendible = new Product(tmp.getName(), tmp.getPrecio(), tmp.getCadu(), tmp.getUpc());
		} else {
			Pack tmp = (Pack) vendible;
			this.vendible = new Pack(tmp.getName(), tmp.getPaquete(), tmp.getId());
		}
	}
	

	/**
	 * Devuelve un hashcode para el objeto
	 * @return hashcode
	 */
	@Override
	public int hashCode() {
		final int primo = 31;
		int code = 1;
		
		code = code * primo + idlinea;
		code = code * primo + quantity;
		code = code * primo + maxquantity;
		
		if (isProduct()) {
			Product producto = (Product) vendible;
			code = code * primo + producto.getUpc().hashCode();
		} else {
			Pack pack = (Pack) vendible;
			code = code * primo + (int)pack.getId();
			code = code * primo + pack.getCadu().hashCode();
			code = code * primo + pack.getName().hashCode();
		}	
		 
		return code;
	}	
	
	
	 
	/**
	 * Indica si un objeto es igual a otro
	 * @param objeto que se quiere comparar
	 * @return true si los objetos son iguales y false si no lo son
	 */
	@Override
	public boolean equals(Object objeto) {
		if (this == objeto) return true;
		if (objeto == null) return false;
		if (getClass() != objeto.getClass()) return false;
		
		
		VendingMachineLine vm = (VendingMachineLine) objeto;
		if (getIdlinea() != vm.getIdlinea()) return false;
		if (getQuantity() != vm.getQuantity()) return false;
		if (getMaxQuantity() != vm.getMaxQuantity()) return false;				
		if (getVendible().getClass() != vm.getVendible().getClass()) return false;
		
		if (getVendible().getClass()==Product.class) {
			Product producto = (Product) getVendible();
			Product productoOtro = (Product) vm.getVendible();
			return(producto.equals(productoOtro));
		} else {
			Pack pack = (Pack) vendible;
			Pack packOtro = (Pack) vm.getVendible();	
			return(pack.equals(packOtro));
		}
	}
	
	/**
	 * Comprueba si el atributo vendible de esta linea es un producto en este momento
	 * @return Boolean indicando si es un producto (true) o no (false)
	 */
	private boolean isProduct() {
		return (vendible.getClass()==Product.class);
	}		
}
