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
	private Product Producto;
	private int maxquantity;
		
	/**
	 * Crear una nueva linea vacia de un determinado producto 
	 * @param id, de la linea
	 * @param producto, que tiene esta linea
	 * @param maxquantity, profundidad de la linea
	 * @throws IllegalArgumentException cuando {@code maxquantity <= 0}
	 * @throws NullPointerException cuando {@code producto == null}
	 * @throws IllegalArgumentException cuando {@code id < 0}
	 */
	public VendingMachineLine(int id, Product producto, int maxquantity) {
		setMaxQuantity(maxquantity);
		setQuantity(0);
		setProducto(producto);
		setIdlinea(id);
	}
	
	/**
	 * Crear una nueva linea con una cantidad indicada de un determinado producto 
	 * @param id, de la linea
	 * @param producto, que tiene esta linea
	 * @param maxquantity, profundidad de la linea
	 * @param quantity, que tiene inicialmente esta linea
	 * @throws IllegalArgumentException cuando {@code maxquantity <= 0}
	 * @throws IllegalArgumentException cuando {@code quantity > maxquantity}
	 * @throws NullPointerException cuando {@code producto == null}
	 * @throws IllegalArgumentException cuando {@code id < 0}
	 */
	public VendingMachineLine(int id, Product producto, int maxquantity, int quantity) {
		setMaxQuantity(maxquantity);
		setQuantity(quantity);
		setProducto(producto);
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
		setProducto(VendingMachineLine.getProducto());
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
	 * Consulta el producto que tiene esta linea
	 * @return Producto
	 */
	public Product getProducto() {
		return Producto;
	}

	/**
	 * Actualiza el producto que tiene esta linea
	 * @param producto
	 * @throws NullPointerException cuando {@code producto == null}
	 */
	public void setProducto(Product producto) {
		if (producto == null) throw new NullPointerException("Producto no puede ser null");
		this.Producto = new Product(producto.getName(), producto.getPrecio(), producto.getCadu(), producto.getUpc());
	}
}
