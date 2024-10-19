package vendingco;

import java.util.ArrayList;

/*
 * Proporciona la gestion de todas las maquinas de vending de la empresa VendingCo
 * Se puede obtener una lista con los identificadores de las maquinas que gestiona
 * Facilita tanto la creacion como la eliminacion de nuevas maquinas de vending
 * Permite saber que maquinas se encuentran operativas y una lista de aquellas que tengan alguna de sus lineas de producto vacias
 * 
 * @author tomcarr
 * @author javiram
 */
public class VendingCity {
	private String nombreprovincia;
	private ArrayList<VendingMachine> machineList;	
	
	/**
	 *  Construye el sistema de Vending de la ciudad indicada
	 *  @param Nombreprovincia en la que se quiere crear el sistema de Vending
	 *  @throws NullPointerException cuando {@code nombreprovincia == null}
	 *  @throws IllegalArgumentException cuando {@code idprovincia <= 0}
	 */
	public VendingCity(String nombreprovincia) {
		setNombreprovincia(nombreprovincia);
		setMachineList(new ArrayList<>());
	}
	
	/**
	 * Construye un nuevo sistema de gestion de maquinas vending y crea las maquinas asociadas directamente (operativas)
	 * @param Nombreprovincia en la que se quiere crear el sistema de Vending
	 * @param VendingMachinesNumber
	 * @param filas, de la maquina
	 * @param columnas, de la maquina
	 * @param longitudfilas, de cada linea de la maquina
	 * @throws NullPointerException cuando {@code nombreprovincia == null}
	 * @throws IllegalArgumentException cuando {@code VendingMachinesNumber < 0}
	 * @throws IllegalArgumentException cuando {@code filas<=0}
	 * @throws IllegalArgumentException cuando {@code columnas<=0}
	 * @throws IllegalArgumentException cuando {@code longitudLineas <= 0}
	 */
	public VendingCity(String nombreprovincia, int VendingMachinesNumber, int filas, int columnas, int longitudfila) {
		if (VendingMachinesNumber < 0) throw new IllegalArgumentException("VendingMachinesNumber tiene que ser mayor que 0");
		
		setNombreprovincia(nombreprovincia);
		setMachineList(new ArrayList<>());
		
		for (int i = 0; i < VendingMachinesNumber; i++) {
			addVendingMachine(i, true, filas, columnas, longitudfila);
		}
	}
	
	
	/**
	 * Constructor de Copia
	 * @param Nombreprovincia en la que se quiere copiar el sistema de Vending
	 * @param VendingCity, del que se realizara la copia
	 * @throws NullPointerException cuando {@code VendingCity == null}
	 * @throws IllegalArgumentException cuando {@code nombreprovincia == null}
	 */
	public VendingCity(String nombreprovincia, VendingCity VendingCity) {
		if(VendingCity == null) throw new NullPointerException("VendingCity no puede ser null");
		setNombreprovincia(nombreprovincia);
		setMachineList(VendingCity.getMachineList());	
	}

	
	/**
	 * Consulta el nombre de la provincia gestionada
	 * @return NombreProvincia
	 */
	public String getNombreprovincia() {
		return nombreprovincia;
	}

	/**
	 * Actualiza el nombre de la provincia
	 * @param NombreProvincia
	 * @throws NullPointerException cuando {@code nombreprovincia == null}
	 */
	private void setNombreprovincia(String nombreprovincia) {
		if (nombreprovincia == null) throw new NullPointerException("Se ha proporcionado un nombre null");
		this.nombreprovincia = nombreprovincia;
	}
	

	/**
	 * Consulta las maquinas que se encuentran en esta ciudad
	 * @return ArrayList con una copia de las maquinas
	 */
	public ArrayList<VendingMachine> getMachineList() {
		return new ArrayList<>(machineList);
	}
	
	/**
	 * Consulta todos los identificadores de las maquinas que son controlan por este sistema
	 * @return ArrayList con los identificadores
	 */
	public ArrayList<Integer> getIdVendingMachines(){
		ArrayList<Integer> tmp = new ArrayList<>();
		for(int i = 0; i < machineList.size(); i++) {
			tmp.add(machineList.get(i).getId());
		}
		return tmp;
	}
	
	/**
	 * Actualiza la lista de identificadores de la maquina
	 * @param ArrayList con la nueva lista de maquinas
	 * @throws NullPointerException cuando {@code MachineList == null}
	 */
	public void setMachineList(ArrayList<VendingMachine> MachineList) {
		if (MachineList == null) throw new NullPointerException("La lista de maquinas no puede ser null");
		this.machineList = new ArrayList<>(MachineList);
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
		for(int i=0; i<machineList.size(); i++) {
			if(machineList.get(i).getId() == id) throw new IllegalArgumentException("Se ha proporcionado un id repetido");
		}
		 VendingMachine VendingMachine = new VendingMachine(id, state, filas, columnas, longitudlinea);
		 machineList.add(VendingMachine);
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
		for(int i=0; i<machineList.size(); i++) {
			if(machineList.get(i).getId() == id) throw new IllegalArgumentException("Se ha proporcionado un id repetido");
		}
		VendingMachine NuevaVendingMachine = new VendingMachine(id, VendingMachine);
		machineList.add(NuevaVendingMachine);
	 }
	 
	 /**
	  * Elimina la maquina indicada de la lista (y desplaza el resto para quitar su espacio)
	  * @param id, de la maquina que se desea borrar
	  * @throws IllegalArgumentException cuando {@code id no existe en este sistema}
	  */
	 public void deleteVendingMachine(int id) {	
		boolean error = true; 
		for(int i=0; i < machineList.size(); i++) {
			
			if(id == machineList.get(i).getId()) {
				machineList.remove(i);
				error = false;
			}		
		}
		if(error) throw new IllegalArgumentException("No se ha encontrado ese identificador");
	 }
	 
	 /**
	  * Elimina todas las maquinas que estaban siendo gestionadas
	  */
	 public void deleteAllVendingMachine() {
		 machineList.clear();
	 }
	
	 
	 /**
	  * Devuelve una lista las maquinas en un estado determinado
	  * @param state
	  * @return ArrayList con una copia de las maquinas en ese estado
	  */
	 private ArrayList<VendingMachine> stateVendingMachines(boolean state) {
		 ArrayList<VendingMachine> tmp = new ArrayList<>();
		 
		 for(int i = 0; i < machineList.size(); i++) {
			 if (machineList.get(i).getState() == state) {
				 tmp.add(new VendingMachine(machineList.get(i).getId() ,machineList.get(i)));
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
		 ArrayList<Integer> tmp = new ArrayList<>();
		 
		 for(int i = 0; i < machineList.size(); i++) {
			 if (machineList.get(i).hasLineaVacia()) {
				 tmp.add(machineList.get(i).getId());
			 }
		 }
		 return tmp;
	 }
	 
	 /**
	  * Devuelve una lista con una copia de todas las maquinas que tienen alguna línea vacia
	  * @return ArrayList con las maquinas
	  */
	 public ArrayList<VendingMachine> vaciasVendingMachines() {
		 ArrayList<VendingMachine> tmp = new ArrayList<>();
		 
		 for(int i = 0; i < machineList.size(); i++) {
			 if (machineList.get(i).hasLineaVacia()) {
				 VendingMachine vm = new VendingMachine(machineList.get(i).getId(), machineList.get(i));
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
		 for(int i=0; i<machineList.size(); i++) {
			 if (machineList.get(i).getId() == id) return new VendingMachine(id, machineList.get(i));
		 }
		 throw new IllegalArgumentException("No se ha encontrado ese identificador");
	 }

	 /**
	  * Devuelve un hashcode para el objeto
	  * @return hashcode
	  */	 
	 @Override
	 public int hashCode() {
		final int primo = 31;
		int resultado = 1;
		
		resultado = primo * resultado + machineList.hashCode();
		resultado = primo * resultado + nombreprovincia.hashCode();
		
		return resultado;
	}	
	 
	 /**
	  * Indica si un objeto es igual a otro
	  * @param objeto que se quiere comparar
	  * @return true si los objetos son iguales y false si no lo son
	  */
	 @Override
	 public boolean equals(Object objeto) {
		if (this == objeto) return true;
		if (objeto == null)	return false;
		if (getClass() != objeto.getClass()) return false;
		
		VendingCity vendingCity = (VendingCity) objeto;
		
		if (!machineList.equals(vendingCity.machineList)) return false;
		
		return nombreprovincia.equals(vendingCity.nombreprovincia);
	}	
}
