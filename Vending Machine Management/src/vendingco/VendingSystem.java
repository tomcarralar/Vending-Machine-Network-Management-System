package vendingco;

import java.util.ArrayList;

/**
 * Proporciona la gestion de todas las maquinas de vending de la empresa VendingCo
 * Se puede obtener una lista con los identificadores de las maquinas que gestiona
 * Facilita tanto la creacion como la eliminacion de nuevas maquinas de vending
 * Permite saber que maquinas se encuentran operativas y una lista de aquellas que tengan alguna de sus lineas de producto vacias
 * 
 * @author tomcarr
 * @author javiram
 */
public class VendingSystem {

	private ArrayList<VendingMachine> VendingMachines;
	
	/**
	 * Constuye un nuevo sistema de gestion de maquinas vending completamente vacio
	 */
	public VendingSystem() {
		setVendingMachines(new ArrayList<VendingMachine>());
	}

	/**
	 * Construye un nuevo sistema de gestion de maquinas vending y crea las maquinas asociadas directamente (operativas)
	 * @param VendingMachinesNumber
	 * @param filas, de la maquina
	 * @param columnas, de la maquina
	 * @param longitudfilas, de cada linea de la maquina
	 * @throws IllegalArgumentException cuando {@code VendingMachinesNumber < 0}
	 * @throws IllegalArgumentException cuando {@code filas<=0}
	 * @throws IllegalArgumentException cuando {@code columnas<=0}
	 * @throws IllegalArgumentException cuando {@code longitudLineas <= 0}
	 */
	public VendingSystem(int VendingMachinesNumber, int filas, int columnas, int longitudfila) {
		if (VendingMachinesNumber < 0) throw new IllegalArgumentException("VendingMachinesNumber tiene que ser mayor que 0");
		
		setVendingMachines(new ArrayList<VendingMachine>());
		for (int i = 0; i < VendingMachinesNumber; i++) {
			addVendingMachine(i, true, filas, columnas, longitudfila);
		}
	}
	
	
	/**
	 * Constructor de Copia
	 * @param VendingSystem, del que se realizara la copia
	 * @throws NullPointerException cuando {@code VendingSystem == null}
	 */
	public VendingSystem(VendingSystem VendingSystem) {
		if(VendingSystem == null) throw new NullPointerException("VendingSystem no puede ser null");
		setVendingMachines(VendingSystem.getVendingMachines());	
	}

	/**
	 * Consulta la lista de maquinas se gestionan en este sistema
	 * @return Copia de la lista compuesta por las maquinas gestionadas en este momento
	 */
	public ArrayList<VendingMachine> getVendingMachines() {
		ArrayList<VendingMachine> VendingMachinesCopy = new ArrayList<VendingMachine>(VendingMachines);
		return VendingMachinesCopy;
	}
	
	
	/**
	 * Consulta todos los identificadores de las maquinas que son controlan por este sistema
	 * @return ArrayList con los identificadores
	 */
	public ArrayList<Integer> getIdVendingMachines(){
		ArrayList<Integer> tmp = new ArrayList<Integer>();
		for(int i = 0; i < VendingMachines.size(); i++) {
			tmp.add(VendingMachines.get(i).getId());
		}
		return tmp;
	}
	

	/**
	 * Actualiza las maquinas que son gestionadas por este sistema
	 * @param ArrayList, con la nueva lista de maquinas
	 * @throws NullPointerException cuando {@code VendingMachines == null}
	 */
	public void setVendingMachines(ArrayList<VendingMachine> VendingMachines) {
		if (VendingMachines == null) throw new NullPointerException("VendingMachines no puede ser null");
		this.VendingMachines = new ArrayList<VendingMachine>(VendingMachines);
	}
	
	/**
	 * Agrega una nueva maquina operativa con las caracteristicas indicadas a la lista de gestion
	 * @param id, de la nueva maquina
	 * @param state, de la nueva maquina
	 * @param filas, de la nueva maquina
	 * @param columnas, de la nueva maquina
	 * @param longitudfilas, de cada linea de la nueva maquina
	 * @throws IllegalArgumentException cuando el identificador es repetido
	 * @throws IllegalArgumentException cuando {@code id < 0}
	 * @throws IllegalArgumentException cuando {@code filas<=0}
	 * @throws IllegalArgumentException cuando {@code columnas<=0}
	 * @throws IllegalArgumentException cuando {@code longitudLineas <= 0}
	 */
	public void addVendingMachine(int id, boolean state, int filas, int columnas, int longitudlinea) {
		for(int i=0; i<VendingMachines.size(); i++) {
			if(VendingMachines.get(i).getId() == id) throw new IllegalArgumentException("Se ha proporcionado un id repetido");
		}
		 VendingMachine VendingMachine = new VendingMachine(id, state, filas, columnas, longitudlinea);
		 VendingMachines.add(VendingMachine);
	 }
	 
	 /**
	  * Agrega una copia de la maquina indicada (en el mismo estado que la original)
	  * @param id, de la nueva maquina
	  * @param VendingMachine, de la que se realizara la copia
	  * @throws NullPointerException cuando {@code VendingMachine == null}
	  * @throws IllegalArgumentException cuando el identificador es repetido
	  * @throws IllegalArgumentException cuando {@code id < 0}
	  */
	public void addVendingMachineCopy(int id, VendingMachine VendingMachine) {
		if(VendingMachine == null) throw new NullPointerException("VendingMachine no puede ser null");
		for(int i=0; i<VendingMachines.size(); i++) {
			if(VendingMachines.get(i).getId() == id) throw new IllegalArgumentException("Se ha proporcionado un id repetido");
		}
		VendingMachine NuevaVendingMachine = new VendingMachine(id, VendingMachine);
		VendingMachines.add(NuevaVendingMachine);
	 }
	 
	 /**
	  * Elimina la maquina indicada de la lista (y desplaza el resto para quitar su espacio)
	  * @param id, de la maquina que se desea borrar
	  * @throws IllegalArgumentException cuando {@code id no existe en este sistema}
	  */
	 public void deleteVendingMachine(int id) {	
		boolean error = true; 
		for(int i=0; i < VendingMachines.size(); i++) {
			
			if(id == VendingMachines.get(i).getId()) {
				VendingMachines.remove(i);
				error = false;
			}		
		}
		if(error) throw new IllegalArgumentException("No se ha encontrado ese identificador");
	 }
	 
	 /**
	  * Elimina todas las maquinas que estaban siendo gestionadas
	  */
	 public void deleteAllVendingMachine() {
		 VendingMachines.clear();
	 }
	
	 
	 /**
	  * Devuelve una lista las maquinas en un estado determinado
	  * @param state
	  * @return ArrayList con una copia de las maquinas en ese estado
	  */
	 private ArrayList<VendingMachine> stateVendingMachines(boolean state) {
		 ArrayList<VendingMachine> tmp = new ArrayList<VendingMachine>();
		 
		 for(int i = 0; i < VendingMachines.size(); i++) {
			 if (VendingMachines.get(i).getState() == state) {
				 tmp.add(new VendingMachine(VendingMachines.get(i).getId() ,VendingMachines.get(i)));
			 }
		 }
		 return tmp;
	 }
	 
	 /**
	 * Devuelve una lista con las maquinas operativas
	 * @return ArrayList con una copia de las maquinas
	 */
	 public ArrayList<VendingMachine> operativeVendingMachines() {
		 return stateVendingMachines(true);
	 }
	 
	 /**
	  * Devuelve una lista con las maquinas fuera de servicio
	  * @return ArrayList con la copia de las maquinas
	  */
	 public ArrayList<VendingMachine> notOperativeVendingMachines() {
		 return stateVendingMachines(false);
	 }
	 
	 
	 /**
	  * Devuelve una lista con los identificadores de todas las maquinas que tienen alguna línea vacia
	  * @return ArrayList con la lista de los identificadores
	  */
	 public ArrayList<Integer> idVaciasVendingMachines() {
		 ArrayList<Integer> tmp = new ArrayList<Integer>();
		 
		 for(int i = 0; i < VendingMachines.size(); i++) {
			 if (VendingMachines.get(i).hasLineaVacia()) {
				 tmp.add(VendingMachines.get(i).getId());
			 }
		 }
		 return tmp;
	 }
	 
	 /**
	  * Devuelve una lista con una copia de todas las maquinas que tienen alguna línea vacia
	  * @return ArrayList con las maquinas
	  */
	 public ArrayList<VendingMachine> vaciasVendingMachines() {
		 ArrayList<VendingMachine> tmp = new ArrayList<VendingMachine>();
		 
		 for(int i = 0; i < VendingMachines.size(); i++) {
			 if (VendingMachines.get(i).hasLineaVacia()) {
				 VendingMachine vm = new VendingMachine(VendingMachines.get(i).getId(), VendingMachines.get(i));
				 tmp.add(vm);
			 }
		 }
		 return tmp;
	 }
	 
	 
	 /**
	  * Devuelve una copia de la maquina buscada por su identificador
	  * @param id, que se desea buscar
	  * @return VendingMachine
	  * @throws IllegalArgumentException cuando id no está en la lista
	  */
	 public VendingMachine findVendingMachine(int id) {
		 for(int i=0; i<VendingMachines.size(); i++) {
			 if (VendingMachines.get(i).getId() == id) return new VendingMachine(id, VendingMachines.get(i));
		 }
		 throw new IllegalArgumentException("No se ha encontrado ese identificador");
	 }
}
