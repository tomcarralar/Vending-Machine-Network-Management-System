package vendingco;

import java.util.ArrayList;
import java.util.Arrays;

import fabricante.externo.tarjetas.TarjetaMonedero;

/**
 * Proporciona el funcionamiento de la maquina de vending de la empresa VendingCo
 * Se pueden obtener todos los identificadores de linea de la maquina
 * Permite obtener una lista de las lineas que se encuentran vacias
 * Permite la creacion y eliminacion de lineas de la maquina
 * Implementa la funcionalidad de comprar un producto de una de las lineas 
 * 
 * @author tomcarr
 * @author javiram
 */


public class VendingMachine {
	
	private int id;
	private boolean state;
	private VendingMachineLine[] lineas;
	private int columnas;
	private int longitudLineas;
	private int indiceposicionvacia = 0;
	

	/**
	 * Contruye una nueva maquina vending vacia en el estado indicado con las dimensiones indicadas
	 * @param id, de la maquina
	 * @param state, de la maquina
	 * @param filas, de la maquina
	 * @param columnas, de la maquina
	 * @param longitudLineas, profundidad de cada una de las lineas de la maquina
	 * @throws IllegalArgumentException cuando {@code filas<=0}
	 * @throws IllegalArgumentException cuando {@code columnas<=0}
	 * @throws IllegalArgumentException cuando {@code longitudLineas <= 0}
	 * @throws IllegalArgumentException cuando {@code id < 0}
	 */
	public VendingMachine(int id, boolean state, int filas, int columnas, int longitudLineas) {
		setState(state);
		createLineas(filas, columnas);
		setLongitudLineas(longitudLineas);
		setId(id);
	}
	
	
	/**
	 * Constructor de copia
	 * @param id, de la maquina
	 * @param VendingMachine, del que se realizara la copia
	 * @throws NullPointerException cuando {@code VendingMachine == null}
	 * @throws IllegalArgumentException cuando {@code id < 0}
	 */
	public VendingMachine(int id, VendingMachine VendingMachine) {
		if(VendingMachine == null) throw new NullPointerException();
		setState(VendingMachine.getState());
		createLineas(VendingMachine.getFilas(), VendingMachine.getColumnas());
		setLineas(VendingMachine.getLineas());
		setLongitudLineas(VendingMachine.getLongitudLineas());
		setId(id);
	}

	
	/**
	 * Consulta el id de la maquina
	 * @return id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Actualiza el id de la maquina
	 * @param id
	 * @throws IllegalArgumentException cuando {@code id < 0}
	 */
	public void setId(int id) {
		if(id < 0) throw new IllegalArgumentException("El identificador tiene que ser positivo o 0");
		this.id = id;
	}
	
	/**
	 * Consulta la longitud de las filas de la maquina
	 * @return Longitud de las lineas
	 */
	public int getLongitudLineas() {
		return longitudLineas;
	}
	
	/**
	 * Actualiza la longitud de las filas de la maquina (solo se puede hacer en la creacion de la maquina)
	 * @param longitudLineas 
	 * @throws IllegalArgumentException cuando {@code longitudLineas <= 0}
	 */
	private void setLongitudLineas(int longitudLineas) {
		if(longitudLineas <= 0) throw new IllegalArgumentException("La longitud de cada linea tiene que ser mayor que 0");
		this.longitudLineas = longitudLineas;
	}
	
	/**
	 * Consulta el estado de la maquina
	 * @return state (True = Operativa) (False = Fuera de servicio)
	 */
	public boolean getState() {
		return state;
	}
	
	/**
	 * Actualiza el estado de la maquina
	 * @param state
	 */
	public void setState(boolean state) {
		this.state = state;
	}
	
	/**
	 * Funcion que devuelve una copia de las lineas de la maquina
	 * @return Array con una copia de las lineas
	 */
	public VendingMachineLine[] getLineas(){		
		VendingMachineLine[] copyLineas = Arrays.copyOf(lineas, lineas.length);
		return copyLineas;
	}
	
	/**
	 * Consulta los identificadores de todas las lineas de esta maquina
	 * @return Lista con los identificadores
	 */
	public int[] getidLineas() {
		int[] tmp = new int[lineas.length];
		
		for(int i=0; i<lineas.length; i++) {
			 tmp[i] = lineas[i].getIdlinea();
		}
		
		return tmp;
	}
		
	/**
	 * Obtiene una copia de todas las lineas vacias de la maquina
	 * @return ArrayList con las copias de las lineas vacias
	 */
	public ArrayList<VendingMachineLine> getLineasVacias(){		
		ArrayList<VendingMachineLine>  tmp = new ArrayList<VendingMachineLine>();
		
		for(int i=0; i<lineas.length; i++) {
			 if(lineas[i].getQuantity() == 0) tmp.add(lineas[i]);
		}
		 
		return tmp;
	}
	
	/**
	 * Consulta los identificadores de todas las lineas vacias de la maquina
	 * @return ArrayList con los identificadores (enteros) de las lineas vacias
	 */
	public ArrayList<Integer> getLineasVaciasid(){		
		ArrayList<Integer>  tmp = new ArrayList<Integer>();
		
		for(int i=0; i<lineas.length; i++) {
			 if(lineas[i].getQuantity() == 0) tmp.add(lineas[i].getIdlinea());
		}
		 
		return tmp;
	}
	
	/**
	 * Comprueba si la maquina tiene alguna linea vacia 
	 * @return boolean si tiene o no lineas vacias
	 */
	public boolean hasLineaVacia() {		
		for(int i=0; i<lineas.length; i++) {
			 if(lineas[i].getQuantity() == 0) return true;
		}
		return false;
	}
	
	/**
	 * Funcion encargada de la creacion de la lista lineas con la dimension indicada
	 * @param filas
	 * @param columnas
	 * @throws IllegalArgumentException cuando {@code filas<=0}
	 * @throws IllegalArgumentException cuando {@code columnas<=0}
	 */
	private void createLineas(int filas, int columnas) {
		if(filas <= 0) throw new IllegalArgumentException();
		if(columnas <= 0) throw new IllegalArgumentException();
		
		this.columnas = columnas;
		int dimension = filas*columnas;
		lineas = new VendingMachineLine[dimension];
		
		for(int i=0; i<lineas.length; i++) {
				lineas[i] = null;
		}
	}
	
	/**
	 * Actualiza la lista de las lineas de la maquina (si su dimension es menor que la de la maquina el resto de los valores ser�n null)
	 * @param Array, con la nueva lista de lineas
	 * @throws IllegalArgumentException cuando {@code LineasCamb==null}
	 * @throws IllegalArgumentException cuando no tienes espacio suficiente {@code LineasCamb.length > lineas.length}
	 */
	public void setLineas(VendingMachineLine[] lineasCamb) {
		if(lineasCamb == null) throw new IllegalArgumentException("La nueva lista no puede ser null");
		if(lineasCamb.length > lineas.length) throw new IllegalArgumentException("Tantas lineas no pueden entrar en esta maquina");
		for(int i=0; i<lineas.length; i++) {
			if(i<lineasCamb.length) {
				lineas[i]=lineasCamb[i];
			} else {
				lineas[i] = null;
			}
		}
	}
	
	/**
	 * Consulta el numero de filas de la maquina
	 * @return numero de filas
	 */
	public int getFilas() {
		return(lineas.length/columnas);
	}
	
	/**
	 * Consulta el numero de columnas de la maquina
	 * @return numero de columnas
	 */
	public int getColumnas() {
		return(columnas);
	}
	
	/**
	 * Crea una nueva linea en la maquina del producto indicado con la cantidad indicada
	 * @param idlinea, con el identificador que se dara a la linea
	 * @param Producto, con el producto a colocar en esa linea
	 * @param quantity, con la cantidad que se introduce incialmente a esa fila
	 * @throws IllegalStateException cuando la maquina esta llena {@code indiceposicionvacia >= lineas.length}
	 * @throws IllegalStateException cuando se da un identificador repetido
	 */
	public void addLineaProducto(int idlinea, Product Producto, int quantity) {
		if(indiceposicionvacia >= lineas.length) throw new IllegalStateException("La maquina esta llena");
		else {
		for(int i = 0 ; i<lineas.length; i++) {
			if (lineas[indiceposicionvacia] != null)
				indiceposicionvacia = i;
			if(lineas[i] != null) {
				if(lineas[i].getIdlinea() == idlinea) throw new IllegalArgumentException("Se ha proporcionado un id repetido");
			}
		}	
		
		lineas[indiceposicionvacia] = (new VendingMachineLine(idlinea, new Product(Producto.getName(), Producto.getPrecio(), Producto.getCadu(), Producto.getUpc()), longitudLineas, quantity));
		indiceposicionvacia ++;
		}
	}
	
	/**
	 * Crea una nueva linea en la maquina del producto indicado
	 * @param idlinea, con el identificador de la nueva linea
	 * @param Producto, con el producto que se colocara en esa linea
	 * @throws IllegalStateException cuando la maquina esta llena {@code indiceposicionvacia >= lineas.length}
	 * @throws IllegalStateException cuando se da un identificador repetido
	 */
	public void addLineaProductoVacia(int idlinea, Product Producto) {
		addLineaProducto(idlinea, Producto, 0);
	}
	
	/**
	 * Se crea una linea con la cantidad determinada de un producto utilizando sus componentes
	 * @param idlinea, con el identificador que se dara a la linea
	 * @param quantity, con la cantidad que se introduce incialmente a esa fila
	 * @param nombre, del producto
	 * @param precio, del producto
	 * @param caduciad, del producto
	 * @param UPC, del producto
	 * @throws IllegalStateException cuando la maquina esta llena {@code indiceposicionvacia >= lineas.length}
	 * @throws IllegalStateException cuando se da un identificador repetido
	 */
	public void addLineaComponentes(int idlinea, int quantity, String nombre, double precio, String caducidad, String UPC) {
		Product Producto = new Product(nombre, precio, caducidad, UPC);
		addLineaProducto(idlinea, Producto, quantity);
	}
		
	
	/**
	 * Elimina una linea de la maquina dado su identificador
	 * @param idlinea a eliminar
	 * @throws IllegalArgumentException cuando el id linea no existe en la maquina}
	 */
	public void deleteLinea(int idlinea) {	
		int num_vacia = indiceposicionvacia; 
		for(int i=0; i < lineas.length; i++) {
			
			if(idlinea == lineas[i].getIdlinea()) {
				indiceposicionvacia --;
				int intercambio = i;
				for(int j = intercambio + 1; j < lineas.length; j++) {
					lineas[intercambio] = lineas[j];
					intercambio ++;
				}
				lineas[(lineas.length-1)] = null;  
				break;
				
			}		
		}
		if (num_vacia == indiceposicionvacia)
			throw new IllegalArgumentException();
	}
	
	/**
	 * Agrega la cantidad indicada a una linea
	 * @param idlinea
	 * @param quantity
	 * @throws IllegalArgumentException cuando {@code quantity <= 0}
	 * @throws IllegalArgumentException cuando {@code idlinea no se corresponda con ninguna otra}
	 */
	public void rellenarLinea(int idlinea, int quantity) {
		if (quantity <= 0) throw new IllegalArgumentException("La cantidad tiene que ser mayor que 0");
		
		VendingMachineLine rellenarlinea = findLine(idlinea);
		
		int newquantity = rellenarlinea.getQuantity() + quantity;
		rellenarlinea.setQuantity(newquantity);		
	}
	
	
	/**
	 * Reduce la cantidad de producto de la linea indicada
	 * @param idlinea, de la que se desea quitar elementos
	 * @param quantity, que se desea quitar
	 * @throws IllegalArgumentException cuando {@code quantity > tmplinea.getQuantity()}
	 * @throws IllegalArgumentException cuadno {@code id no se encuentra en esa maquina}
	 */
	public void vaciarLinea(int idlinea, int quantity) {
		VendingMachineLine tmplinea = findLine(idlinea);
		if (quantity > tmplinea.getQuantity()) throw new IllegalArgumentException("La linea no tiene espacio para tantos productos");
		tmplinea.setQuantity(tmplinea.getQuantity() - quantity);
	}
	
	/**
	 * Vacia todo el producto de la linea indicada
	 * @param idlinea, que se desea vaciar por completo
	 * @throws IllegalArgumentException cuando {@code quantity > tmplinea.getQuantity()}
	 * @throws IllegalArgumentException cuadno {@code id no se encuentra en esa maquina}
	 */
	public void vaciarCompletamenteLinea(int idlinea) {
		vaciarLinea(idlinea, findLine(idlinea).getQuantity());
	}
	
	
	/**
	 * Compra un producto de una de las lineas de la maquina
	 * @param idlinea, en la que se encuentra el producto
	 * @param tarjeta, con la que se paga
	 * @param credencial, de la tarjeta
	 * @throws IllegalStateException cuando {@code state == false}
	 * @throws IllegalArgumentException cuando {@code idlinea no existe en la maquina}
	 * @throws IllegalArgumentException cuando {@code saldotarjeta < precioprodcuto} 
	 * @throws IllegalArgumentException cuando {@code idlinea.quantity == 0}
	 */
	public void comprar(int idlinea, TarjetaMonedero tarjeta, String credencial) {
		if (getState() == false) throw new IllegalStateException("La maquina no esta operativa");
		
		VendingMachineLine lineacompra = findLine(idlinea);
		double saldo = tarjeta.getSaldoActual();
		
		if (lineacompra.getQuantity() == 0) throw new IllegalArgumentException("El producto deseado no tiene existencias");
		if (saldo < lineacompra.getProducto().getPrecio()) throw new IllegalArgumentException("Saldo insuficiente");
		
		int newquantity = lineacompra.getQuantity() - 1;
		lineacompra.setQuantity(newquantity);
		
		tarjeta.descontarDelSaldo(credencial, lineacompra.getProducto().getPrecio());

	}
	
	/**
	 * Te permite saber que productos puedes comprar con el saldo disponible
	 * @param tarjeta
	 * @return ArrayList con los identificadores de todas las lineas de las que puedes comprar un producto
	 */
	public ArrayList<Integer> opcionesCompra(TarjetaMonedero tarjeta) {
		ArrayList<Integer> tmp = new ArrayList<Integer>();
		
		for(int i=0; i < lineas.length; i++) {
			if(lineas[i].getProducto().getPrecio() <= tarjeta.getSaldoActual()) {
				tmp.add(lineas[i].getIdlinea());
			}
		}
		
		return tmp;
	}
		
	/**
	 * Encuenta la linea con el identificador indicado
	 * @param idlinea, que se busca
	 * @return VendingMachineLine (Original)
	 * @throws IllegalArgumentException cuando {@code idlinea no existe en la maquina}
	 */
	private VendingMachineLine findLine(int idlinea) {
		for(int i=0; i < lineas.length; i++) {
			if(idlinea == lineas[i].getIdlinea()) {
				return lineas[i];
			}		
		}
		throw new IllegalArgumentException("No se ha encontrado ese identificador");
	}
	
	/**
	 * Encuenta la linea con el identificador indicado y devuelve una copia
	 * @param idlinea, que se quiere buscar
	 * @return VendingMachineLine
	 * @throws IllegalArgumentException cuando {@code idlinea no existe en la maquina}
	 */
	public VendingMachineLine findLineCopy(int idlinea) {
		for(int i=0; i < lineas.length; i++) {
			if(idlinea == lineas[i].getIdlinea()) {
				return (new VendingMachineLine(idlinea, lineas[i]));
			}		
		}
		throw new IllegalArgumentException("No se ha encontrado ese identificador");
		
	}
	
		
	
}
