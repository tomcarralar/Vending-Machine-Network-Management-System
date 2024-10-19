package vendingco;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
	private Map<Integer, VendingCity> sedes = new HashMap<>(100, (float) 0.66);
	private ArrayList<Integer> codigos;

	/**
	 * Se crea un sistema de vending completamente vacio
	 */
	public VendingSystem() {
		codigos = new ArrayList<>();
	}
		
	/**
	 * Se crea un sistema de vending con una sede con los parámetros indicados
	 * @param codigo
	 * @param nombre 
	 * @throws IllegalArgumentException cuando {@code codigo < 0}
	 * @throws NullPointerException cuando {@code sede == null}
	 */
	public VendingSystem(int codigo, String nombre) {
		if (nombre == null) throw new NullPointerException("El nombre no puede ser un string null");
		codigos = new ArrayList<>();
		addSede(codigo, nombre);
	}
	
	
	/**
	 * Se crea un sistema de vending con la sede indicada
	 * @param sede
	 * @param codigo de la sede
	 * @throws IllegalArgumentException cuando {@code codigo < 0}
	 * @throws IllegalArgumentException cuando {@code sede == null}
	 * @throws IllegalArgumentException cuando el codigo ya estaba en el sistema
	 * @throws IllegalArgumentException cuando el nombre de provincia ya esta en el sistema
	 */
	public VendingSystem(VendingCity sede, int codigo) {
		if (sede == null) throw new NullPointerException("La sede no puede ser nula");
		codigos = new ArrayList<>();
		appendSede(codigo, sede);
	}
	
	/**
	 * Busca cual es el nombre de una provincia introduciendo el codigo
	 * @param codigo de la provincia que se quiere buscar
	 * @throws NullPointerException cuando el codigo no esta en la maquina
	 */
	public String getNombreProvincia(int codigo) {
		return sedes.get(codigo).getNombreprovincia();
	}
	
	/**
	 * Devuelve una copia de de la ciudad
	 * @param codigo
	 * @return VendingCity, copia exacta de la ciudad que se tiene en el sistema de gestion
	 * @throws NullPointerException si el codigo introducido no estaba en la maquina
	 */
	public VendingCity getCopiaProvincia(int codigo) {
		return(new VendingCity(sedes.get(codigo).getNombreprovincia(), sedes.get(codigo)));
	}
	
	/**
	 * Incluye una sede a la lista de sedes que gestiona este sistema
	 * @param codigo, de la sede
	 * @param sede
	 * @throws IllegalArgumentException cuando {@code codigo < 0}
	 * @throws NullPointerException cuando {@code sede == null}
	 * @throws IllegalArgumentException cuando el codigo ya estaba en el sistema
	 * @throws IllegalArgumentException cuando el nombre de provincia ya esta en el sistema
	 */
	public void appendSede(int codigo, VendingCity sede) {
		if (codigo < 0) throw new IllegalArgumentException("El código de la sede no puede ser negativo");
		if (sede == null) throw new NullPointerException("La sede no puede ser null");
		if (sedes.get(codigo) != null) throw new IllegalArgumentException("El código de la sede esta repetido"); 
		if (isProvincia(sede.getNombreprovincia())) throw new IllegalArgumentException("Esa provincia ya está en el sistema");
		
		sedes.put(codigo, sede);
		codigos.add(codigo);
	}
	
	/**
	 * Incluye una nueva sede a la lista de las sedes que gestiona este sistema
	 * @param codigo, de la sede
	 * @param nombre, de la sede
	 * @throws NullPointerException cuando {@code nombre == null}
	 * @throws IllegalArgumentException cuando {@code codigo < 0}
	 * @throws IllegalArgumentException cuando el codigo ya estaba en la maquina
	 * @throws IllegalArgumentException cuando el nombre de provincia ya esta en el sistema
	 */
	public void addSede(int codigo, String nombre) {
		if (nombre == null) throw new NullPointerException("No se ha proporcionado un nombre a la sede");
		VendingCity vc = new VendingCity(nombre);
		appendSede(codigo, vc);
	}
	
	/**
	 * Elimina una sede de la lista de sedes
	 * @param codigo de la sede que se desea eliminar (si no se encuentra no se hace ninguna modificacion)
	 */
	public void deleteSede(int codigo) {
		sedes.remove(codigo);
		
		for (int i = 0; i < codigos.size(); i++) {
			if (codigos.get(i) == codigo) {
				codigos.remove(i);
			}

		}
	}
	
	/**
	 * Consulta la lista de codigos que ya se han utilizado en este sistema
	 * @return Copia de la lista de codigos de la maquina
	 */
	public ArrayList<Integer> getCodigos() {
		ArrayList<Integer> tmp = new ArrayList<>();
		for (int i = 0; i < codigos.size(); i++) {
			tmp.add(codigos.get(i));
		}
		return tmp;
	}
	
	/**
	 * Devuelve el numero de maquinas vending que se tienen en la sede indicada
	 * @param codigo, de la sede
	 * @returns cantidad de maquinas que se gestionan
	 * @throws IllegalArgumentException cuando el codigo no esta en la lista de sedes
	 */
	public int getNumeroMaquinasVending(int codigo) {
		ArrayList<VendingMachine> machineList = getMaquinasVending(codigo);
		return (machineList.size());
	}
	
	/**
	 * Devuelve una copia de las maquinas que se tienen en la sede con el codigo introducido
	 * @param codigo, de la ciudad
	 * @return ArrayList copia de la lista de maquinas
	 * @throws IllegalArgumentException cuando el codigo no esta en la lista de sedes
	 */
	public ArrayList<VendingMachine> getMaquinasVending(int codigo) {
		VendingCity ciudad = findCity(codigo);
		return(ciudad.getMachineList());
	}
	

	/**
	 * Devuelve el numero de maquinas vending para cada una de las sedes 
	 * @return numero de maquinas vending en el sistema
	 */
	public int getAllNumeroMaquinasVending() {
		ArrayList<VendingMachine> tmp = new ArrayList<>();
		
		for(int i=0; i < codigos.size(); i++) {
			VendingCity ciudad = findCity(codigos.get(i));
			
			for(int j = 0; j < ciudad.getMachineList().size(); j++)	{
				tmp.add(ciudad.getMachineList().get(j));
			}
			
		}
		return tmp.size();
	}
	
	/**
	 * Devuelve copias de cada una de las listas de maquinas vending para cada una de las provincias
	 * @return Mapa con el nombre de la provincia con una lista de las maquinas que se tienen en esa provincia
	 */	
	public Map<String, ArrayList<VendingMachine>> getAllMaquinasVending() {
		Map<String, ArrayList<VendingMachine>> maquinasProvincia = new HashMap<>();		
		
		for(int i=0; i < codigos.size(); i++) {
			VendingCity ciudad = findCity(codigos.get(i));
			
			maquinasProvincia.put(ciudad.getNombreprovincia(), ciudad.getMachineList());
		}
		return maquinasProvincia;
	}
	
	
	/**
	 * Devuelve copias de el numero de maquinas vending de todas las provincias
	 * @return Mapa con el nombre de la provincia con el numero de maquinas que se tienen en esa provincia
	 */	
	public Map<String, Integer> getNumeroProvinciasMaquinasVending() {
		Map<String, Integer> maquinasProvincia = new HashMap<>();		
		
		for(int i=0; i < codigos.size(); i++) {
			VendingCity ciudad = findCity(codigos.get(i));
			
			maquinasProvincia.put(ciudad.getNombreprovincia(), ciudad.getMachineList().size());
		}
		return maquinasProvincia;
	}
	

	/**
	 * Devuelve una lista con los nombres de todas las provincias que gestiona
	 * @return ArrayList con los nombres de las provincias
	 */
	public ArrayList<String> getAllProvincias() {
		ArrayList<String> provincias = new ArrayList<>();
		
		for (int i=0; i < codigos.size(); i++) {
			VendingCity ciudad = findCity(codigos.get(i));
			provincias.add(ciudad.getNombreprovincia());
		}
		return provincias;
	}
	
	/**
	 * Devuelve la cantidad de provincias que gestiona este sistema de vending
	 * @return numero de provincias que se gestionan
	 */
	public int getNumeroProvincias() {
		ArrayList<String> provincias = getAllProvincias();
		return provincias.size();
	}
	

	/**
	 * Devuelve la lista de maquinas operativas de la ciudad indicada
	 * @param codigo
	 * @return ArrayList con la lista de las maquinas operativas
	 * @throws IllegalArgumentException cuando el codigo no esta en la lista de sedes
	 */
	public ArrayList<VendingMachine> getOperativeMachines(int codigo){
		VendingCity ciudad = findCity(codigo);
		return(ciudad.operativeVendingMachines());
	}
	
	/**
	 * Devuelve la lista de maquinas no operativas de la ciudad indicada
	 * @param codigo
	 * @return ArrayList con la lista de las maquinas no operativas
	 * @throws IllegalArgumentException cuando el codigo no esta en la lista de sedes
	 */
	public ArrayList<VendingMachine> getNotOperativeMachines(int codigo){
		VendingCity ciudad = findCity(codigo);
		return(ciudad.notOperativeVendingMachines());
	}
	
	/**
	 * Encuentra una ciudad dado su código
	 * @param codigo
	 * @return VendingCity
	 * @throws IllegalArgumentException cuando el codigo no esta en la lista de sedes
	 */
	private VendingCity findCity(int codigo) {
		VendingCity city = sedes.get(codigo);
		if (city == null) throw new IllegalArgumentException("Ese código no se ha encontrado");
		return city;
	}
	
	/**
	 * Comprueba si el nombre corresponde a alguna de las sedes que ya se encuentran en el sistema
	 * @param nombre, que se quiere buscar
	 * @return True si el nombre ya está en el sistema y False si el nombre no esta
	 */
	private boolean isProvincia(String nombre) {
		for (int i = 0; i < codigos.size(); i++) {
			if (sedes.get(codigos.get(i)).getNombreprovincia().equalsIgnoreCase(nombre)) return true;
		}
		return false;
	}

	/**
	 * Devuelve un hashcode para el objeto
	 * @return hashcode
	 */
	@Override
	public int hashCode() {
		final int primo = 31;
		int resultado = 1;
		
		resultado = primo * resultado + codigos.hashCode();
		resultado = primo * resultado + sedes.hashCode();
		
		return resultado;
	}

	/**
	 * Indica si un objeto es igual a otro
	 * @param objeto, que se quiere comparar
	 * @return true si los objetos son iguales y false si no lo son
	 */
	@Override
	public boolean equals(Object objeto) {
		if (this == objeto)	return true;
		if (objeto == null)	return false;
		if (getClass() != objeto.getClass()) return false;
		
		VendingSystem vendingSystem = (VendingSystem) objeto;
		
		if (!codigos.equals(vendingSystem.codigos))	return false;
		return (sedes.equals(vendingSystem.sedes));
	}
	
	
	
}
