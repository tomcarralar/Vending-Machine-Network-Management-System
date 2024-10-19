package vendingco;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * Proporciona todas las pruebas de la clase VendingCity
 * 
 * @author tomcarr
 * @author javiram
 */
public class VendingCityTest {
	
	VendingCity vendingCity;
	VendingCity vendingCityVacio;
	
	VendingMachine vendingMachine1;
	
	Product producto1;
	Product producto2;
	
	@Before
    public void init() {		
		producto1 = new Product("CocaCola", 1.5, "1-1-2022", "100000000007"); 
		producto2 = new Product("galletas", 2.3, "1-1-2025", "112345678902"); 
		
		vendingMachine1 = new VendingMachine(0, true, 1, 3, 10);
		vendingMachine1.addLinea(0, producto1, 5);
		vendingMachine1.addLinea(1, producto1, 3);	
		vendingMachine1.addLinea(2, producto2, 4);	

		vendingCity = new VendingCity("Valladolid");
		vendingCity.addVendingMachineCopy(0, vendingMachine1);
		vendingCity.addVendingMachineCopy(1, vendingMachine1);
		
		vendingCityVacio = new VendingCity("Madrid");
    }
	
	
	
	@Test
	public void testConstructorVacioVendingCityCorrecto() {
		VendingCity vendingCityActual = new VendingCity("Valladolid");
		ArrayList<VendingMachine> ListaEsperada = new ArrayList<>();
		
		assertNotNull(vendingCityActual);
		assertEquals(ListaEsperada, vendingCityActual.getMachineList());
	}
	

	@Test (expected = NullPointerException.class)
	public void testConstructorVendingCityNullIncorrecto() {
		@SuppressWarnings("unused")
		VendingCity VendingCity = new VendingCity(null);
	}	
	
	
	
	
	
	@Test
	public void testConstructorVendingCityCorrecto1() {
		VendingCity vendingCityActual = new VendingCity("Valladolid", 0, 1,1,1);
		ArrayList<VendingMachine> ListaEsperada = new ArrayList<>();
		
		assertNotNull(vendingCityActual);
		assertNotNull(vendingCityActual.getMachineList());
		assertEquals(ListaEsperada, vendingCityActual.getMachineList());
	}
	
	@Test
	public void testConstructorVendingCityCorrecto2() {
		VendingCity vendingCityActual = new VendingCity("Valladolid", 3, 5, 7,10);
		
		assertNotNull(vendingCityActual);
		assertNotNull(vendingCityActual.getMachineList());
		assertEquals("Valladolid", vendingCityActual.getNombreprovincia());
		assertEquals(3, vendingCityActual.getMachineList().size());
		assertEquals(5, vendingCityActual.getMachineList().get(1).getFilas());
		assertEquals(7, vendingCityActual.getMachineList().get(1).getColumnas());
		assertEquals(10, vendingCityActual.getMachineList().get(1).getLongitudLineas());
	}
		
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorVendingCityNegativeVendingMachineIncorrecto() {
		@SuppressWarnings("unused")
		VendingCity VendingCity = new VendingCity("Valladolid", -1,1,1,1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorVendingCityCeroFilasIncorrecto() {
		@SuppressWarnings("unused")
		VendingCity VendingCity = new VendingCity("Valladolid", 1,0,1,1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorVendingCityCeroColumnasIncorrecto() {
		@SuppressWarnings("unused")
		VendingCity VendingCity = new VendingCity("Valladolid", 2,1,0,1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorVendingCityCeroLongitudLineaIncorrecto() {
		@SuppressWarnings("unused")
		VendingCity VendingCity = new VendingCity("Valladolid", 3,1,1,0);
	}
	
	
	

	
	@Test
	public void testConstructorCopiaVendingCityCorrecto() {
		VendingCity vendingCityActual = new VendingCity("Valladolid", vendingCity);
		
		assertNotNull(vendingCityActual);
		
		assertNotSame(vendingCity.getMachineList(), vendingCityActual.getMachineList());
		
		assertNotSame(vendingCity.findVendingMachine(0), vendingCityActual.findVendingMachine(0));
		assertNotSame(vendingCity.findVendingMachine(1), vendingCityActual.findVendingMachine(1));
		
		assertEquals(vendingCity.findVendingMachine(0).getId(), vendingCityActual.findVendingMachine(0).getId());
		assertEquals(vendingCity.findVendingMachine(1).getId(), vendingCityActual.findVendingMachine(1).getId());

		assertEquals(vendingCity.findVendingMachine(0).getLineas().length, vendingCityActual.findVendingMachine(0).getLineas().length);
		assertEquals(vendingCity.findVendingMachine(1).getLineas().length, vendingCityActual.findVendingMachine(1).getLineas().length);
		
		assertEquals(Product.class, vendingCityActual.findVendingMachine(0).findLineCopy(1).getVendible().getClass());
		Product producto = (Product) vendingCityActual.findVendingMachine(0).findLineCopy(1).getVendible();
		assertEquals(producto1.getUpc(), producto.getUpc());
		
		assertEquals(3, vendingCityActual.findVendingMachine(0).findLineCopy(1).getQuantity());
	
	}
	
	@Test (expected = NullPointerException.class)
	public void testConstructorCopiaVendingCityNullIncorrecto() {
		@SuppressWarnings("unused")
		VendingCity VendingCity = new VendingCity("Valladolid", null);
	}	
	



	
	@Test
	public void testgetVendingMachinesCorrecto() {			
		assertNotNull(vendingCity);
		
		assertEquals(2, vendingCity.getMachineList().size());
		
		assertEquals(0, vendingCity.getMachineList().get(0).getId());
		assertEquals(1, vendingCity.getMachineList().get(1).getId());
		
		assertNotSame(vendingMachine1, vendingCity.getMachineList().get(0));
		assertNotSame(vendingMachine1, vendingCity.getMachineList().get(1));
	}
	
	
	
	
	
	@Test
	public void testgetIdVendingMachinesCorrecto() {		
		vendingCityVacio.addVendingMachineCopy(0, vendingMachine1);
		vendingCityVacio.addVendingMachineCopy(1, vendingMachine1);
		vendingCityVacio.addVendingMachineCopy(51, vendingMachine1);
		vendingCityVacio.addVendingMachineCopy(2031, vendingMachine1);
		
		assertNotNull(vendingCityVacio);
		assertNotNull(vendingMachine1);
		
		assertEquals(4, vendingCityVacio.getMachineList().size());
		
		assertEquals(0, vendingCityVacio.getIdVendingMachines().get(0), 0);
		assertEquals(1, vendingCityVacio.getIdVendingMachines().get(1), 0);
		assertEquals(51, vendingCityVacio.getIdVendingMachines().get(2), 0);
		assertEquals(2031, vendingCityVacio.getIdVendingMachines().get(3), 0);
	}
	
	
	
	
	
	@Test
	public void testsetVendingMachinesCorrecto() {
		ArrayList<VendingMachine> ListaEsperada = new ArrayList<>();
		ListaEsperada.add(vendingMachine1);
		
		VendingCity VendingCity = new VendingCity("Valladolid");
		VendingCity.setMachineList(ListaEsperada);
		
		assertNotNull(ListaEsperada);
		assertNotNull(VendingCity);
		assertNotSame(ListaEsperada, VendingCity.getMachineList());
		assertEquals(ListaEsperada.size(), VendingCity.getMachineList().size());
		assertEquals(ListaEsperada.get(0).getId(), VendingCity.getMachineList().get(0).getId());
	}
	
	@Test (expected = NullPointerException.class)
	public void testsetVendingMachinesIncorrecto() {		
		VendingCity VendingCity = new VendingCity("Valladolid");
		VendingCity.setMachineList(null);
	}
	
	
	
	
	
	@Test
	public void testAddVendingMachineCorrecto1() {
		vendingCityVacio.addVendingMachine(0, true, 1, 2, 3);
		
		assertNotNull(vendingCityVacio);
		assertEquals(1, vendingCityVacio.getMachineList().size());
		
		assertEquals(0, vendingCityVacio.getMachineList().get(0).getId());
		assertEquals(1, vendingCityVacio.getMachineList().get(0).getFilas());
		assertEquals(2, vendingCityVacio.getMachineList().get(0).getColumnas());
		assertEquals(3, vendingCityVacio.getMachineList().get(0).getLongitudLineas());
		assertTrue(vendingCityVacio.getMachineList().get(0).getState());
	}
	
	@Test
	public void testAddVendingMachineCorrecto2() {
		vendingCityVacio.addVendingMachine(0, false, 1, 2, 3);
		
		assertNotNull(vendingCityVacio);
		assertEquals(1, vendingCityVacio.getMachineList().size());
		
		assertEquals(0, vendingCityVacio.getMachineList().get(0).getId());
		assertEquals(1, vendingCityVacio.getMachineList().get(0).getFilas());
		assertEquals(2, vendingCityVacio.getMachineList().get(0).getColumnas());
		assertEquals(3, vendingCityVacio.getMachineList().get(0).getLongitudLineas());
		assertFalse(vendingCityVacio.getMachineList().get(0).getState());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAddVendingMachineIdRepetidoIncorrecto() {	
		vendingCityVacio.addVendingMachine(0, true, 1, 2, 3);
		vendingCityVacio.addVendingMachine(0, true, 1, 2, 3);
	}	
	
	@Test (expected = IllegalArgumentException.class)
	public void testAddVendingMachineIdNegativeIncorrecto() {	
		vendingCityVacio.addVendingMachine(-1, true, 1, 1, 1);
	}	
	
	@Test (expected = IllegalArgumentException.class)
	public void testAddVendingMachineFilasNegativeIncorrecto() {	
		vendingCityVacio.addVendingMachine(0, true, 0, 1, 1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAddVendingMachineColumnasNegativeIncorrecto() {	
		vendingCityVacio.addVendingMachine(0, true, 1, 0, 1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAddVendingMachineLongitudLineasNegativeIncorrecto() {	
		vendingCityVacio.addVendingMachine(0, true, 1, 1, 0);
	}
	
	
	
	
	
	@Test
	public void testAddVendingMachineCopyCorrecto1() {
		vendingCityVacio.addVendingMachineCopy(0, vendingMachine1);
		
		assertNotNull(vendingCityVacio);
		assertEquals(1, vendingCityVacio.getMachineList().size());
		
		assertEquals(0, vendingCityVacio.getMachineList().get(0).getId());
		assertEquals(1, vendingCityVacio.getMachineList().get(0).getFilas());
		assertEquals(3, vendingCityVacio.getMachineList().get(0).getColumnas());
		assertEquals(10, vendingCityVacio.getMachineList().get(0).getLongitudLineas());
		assertTrue(vendingCityVacio.getMachineList().get(0).getState());
	}
	
	@Test
	public void testAddVendingMachineCopyCorrecto() {
		VendingMachine VendingMachineExtra = new VendingMachine(0, false, 1, 3, 10);
		vendingCityVacio.addVendingMachineCopy(0, VendingMachineExtra);
		
		assertNotNull(vendingCityVacio);
		assertEquals(1, vendingCityVacio.getMachineList().size());
		
		assertEquals(0, vendingCityVacio.getMachineList().get(0).getId());
		assertEquals(1, vendingCityVacio.getMachineList().get(0).getFilas());
		assertEquals(3, vendingCityVacio.getMachineList().get(0).getColumnas());
		assertEquals(10, vendingCityVacio.getMachineList().get(0).getLongitudLineas());
		assertFalse(vendingCityVacio.getMachineList().get(0).getState());
	}
	
	@Test (expected = NullPointerException.class)
	public void testAddVendingMachineCopyNullVendingMachineIncorrecto() {	
		vendingCityVacio.addVendingMachineCopy(0, null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAddVendingMachineCopyIdRepetidoIncorrecto() {	
		vendingCityVacio.addVendingMachineCopy(0, vendingMachine1);
		vendingCityVacio.addVendingMachineCopy(0, vendingMachine1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAddVendingMachineCopyIdNegativoIncorrecto() {	
		vendingCityVacio.addVendingMachineCopy(-1, vendingMachine1);
	}
	
	
	
	
	
	@Test
	public void testDeleteVendingMachineCorrecto1() {
		vendingCityVacio.addVendingMachineCopy(0, vendingMachine1);
		vendingCityVacio.addVendingMachineCopy(1, vendingMachine1);
		vendingCityVacio.addVendingMachineCopy(513, vendingMachine1);
		
		assertNotNull(vendingCityVacio);
		assertEquals(3, vendingCityVacio.getMachineList().size());
		
		vendingCityVacio.deleteVendingMachine(1);

		assertEquals(2, vendingCityVacio.getMachineList().size());
		assertEquals(0, vendingCityVacio.getMachineList().get(0).getId());
		assertEquals(513, vendingCityVacio.getMachineList().get(1).getId());
	}
	
	@Test
	public void testDeleteVendingMachineCorrecto2() {
		vendingCityVacio.addVendingMachineCopy(0, vendingMachine1);
		vendingCityVacio.addVendingMachineCopy(1, vendingMachine1);
		vendingCityVacio.addVendingMachineCopy(513, vendingMachine1);
		vendingCityVacio.addVendingMachineCopy(312, vendingMachine1);
		
		assertNotNull(vendingCityVacio);
		assertEquals(4, vendingCityVacio.getMachineList().size());
		
		vendingCityVacio.deleteVendingMachine(513);

		assertEquals(3, vendingCityVacio.getMachineList().size());
		assertEquals(0, vendingCityVacio.getMachineList().get(0).getId());
		assertEquals(1, vendingCityVacio.getMachineList().get(1).getId());
		assertEquals(312, vendingCityVacio.getMachineList().get(2).getId());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testDeleteVendingMachineNoExisteIdIncorrecto() {	
		vendingCity.deleteVendingMachine(10);
	}
	
	
	
	
	
	@Test
	public void testDeleteAllVendingMachineCorrecto() {
		vendingCityVacio.addVendingMachineCopy(0, vendingMachine1);
		vendingCityVacio.addVendingMachineCopy(1, vendingMachine1);
		vendingCityVacio.addVendingMachineCopy(10, vendingMachine1);
		vendingCityVacio.addVendingMachineCopy(213, vendingMachine1);
		vendingCityVacio.addVendingMachineCopy(312, vendingMachine1);
		vendingCityVacio.addVendingMachineCopy(513, vendingMachine1);
		
		assertNotNull(vendingCityVacio);
		assertEquals(6, vendingCityVacio.getMachineList().size());
		
		vendingCityVacio.deleteAllVendingMachine();

		assertNotNull(vendingCityVacio.getMachineList());
		assertEquals(0, vendingCityVacio.getMachineList().size());
	}
	
	
	
	
	
	@Test
	public void testOperativeVendingMachineCorrecto1() {
		VendingMachine NotOperativeVendingMachine = new VendingMachine(0, false, 5, 7, 10);
		
		vendingCityVacio.addVendingMachineCopy(0, NotOperativeVendingMachine);
		vendingCityVacio.addVendingMachineCopy(1, vendingMachine1);
		vendingCityVacio.addVendingMachineCopy(2, vendingMachine1);
		vendingCityVacio.addVendingMachineCopy(3, vendingMachine1);
		vendingCityVacio.addVendingMachineCopy(4, vendingMachine1);
		vendingCityVacio.addVendingMachineCopy(5, NotOperativeVendingMachine);
		vendingCityVacio.addVendingMachineCopy(6, NotOperativeVendingMachine);
		vendingCityVacio.addVendingMachineCopy(7, vendingMachine1);
		vendingCityVacio.addVendingMachineCopy(8, vendingMachine1);
		vendingCityVacio.addVendingMachineCopy(9, NotOperativeVendingMachine);
		
		assertNotNull(vendingCityVacio);
		assertEquals(10, vendingCityVacio.getMachineList().size());
		
		ArrayList<VendingMachine> ListaResultado;
		ListaResultado = vendingCityVacio.operativeVendingMachines();
		
		assertNotNull(ListaResultado);
		assertEquals(6, ListaResultado.size());
		assertEquals(1, ListaResultado.get(0).getId());
		assertEquals(2, ListaResultado.get(1).getId());
		assertEquals(3, ListaResultado.get(2).getId());
		assertEquals(4, ListaResultado.get(3).getId());
		assertEquals(7, ListaResultado.get(4).getId());
		assertEquals(8, ListaResultado.get(5).getId());
	}
	
	@Test
	public void testOperativeVendingMachineCorrecto2() {				
		assertNotNull(vendingCityVacio);
		assertEquals(0, vendingCityVacio.getMachineList().size());
		
		ArrayList<VendingMachine> ListaResultado;
		ListaResultado = vendingCityVacio.operativeVendingMachines();
		
		assertNotNull(ListaResultado);
		assertEquals(0, ListaResultado.size());		
	}
	
	@Test
	public void testOperativeVendingMachineCorrecto3() {
		VendingMachine NotOperativeVendingMachine = new VendingMachine(0, false, 5, 7, 10);
		
		vendingCityVacio.addVendingMachineCopy(0, NotOperativeVendingMachine);
		
		assertNotNull(vendingCityVacio);
		assertEquals(1, vendingCityVacio.getMachineList().size());
		
		ArrayList<VendingMachine> ListaResultado;
		ListaResultado = vendingCityVacio.operativeVendingMachines();
		
		assertNotNull(ListaResultado);
		assertEquals(0, ListaResultado.size());
	}
	
	@Test
	public void testOperativeVendingMachineCorrecto4() {
		
		vendingCityVacio.addVendingMachineCopy(12, vendingMachine1);
		
		assertNotNull(vendingCityVacio);
		assertEquals(1, vendingCityVacio.getMachineList().size());
		
		ArrayList<VendingMachine> ListaResultado;
		ListaResultado = vendingCityVacio.operativeVendingMachines();
		
		assertNotNull(ListaResultado);
		assertEquals(1, ListaResultado.size());
		assertEquals(12, ListaResultado.get(0).getId());
	}
	
	
	
	
	
	@Test
	public void testNotOperativeVendingMachineCorrecto1() {
		VendingMachine NotOperativeVendingMachine = new VendingMachine(0, false, 5, 7, 10);
		
		vendingCityVacio.addVendingMachineCopy(0, NotOperativeVendingMachine);
		vendingCityVacio.addVendingMachineCopy(1, vendingMachine1);
		vendingCityVacio.addVendingMachineCopy(2, vendingMachine1);
		vendingCityVacio.addVendingMachineCopy(3, vendingMachine1);
		vendingCityVacio.addVendingMachineCopy(4, vendingMachine1);
		vendingCityVacio.addVendingMachineCopy(5, NotOperativeVendingMachine);
		vendingCityVacio.addVendingMachineCopy(6, NotOperativeVendingMachine);
		vendingCityVacio.addVendingMachineCopy(7, vendingMachine1);
		vendingCityVacio.addVendingMachineCopy(8, vendingMachine1);
		vendingCityVacio.addVendingMachineCopy(9, NotOperativeVendingMachine);
		
		assertNotNull(vendingCityVacio);
		assertEquals(10, vendingCityVacio.getMachineList().size());
		
		ArrayList<VendingMachine> ListaResultado;
		ListaResultado = vendingCityVacio.notOperativeVendingMachines();
		
		assertNotNull(ListaResultado);
		assertEquals(4, ListaResultado.size());
		assertEquals(0, ListaResultado.get(0).getId());
		assertEquals(5, ListaResultado.get(1).getId());
		assertEquals(6, ListaResultado.get(2).getId());
		assertEquals(9, ListaResultado.get(3).getId());
	}
	
	@Test
	public void testNotOperativeVendingMachineCorrecto2() {				
		assertNotNull(vendingCityVacio);
		assertEquals(0, vendingCityVacio.getMachineList().size());
		
		ArrayList<VendingMachine> ListaResultado;
		ListaResultado = vendingCityVacio.notOperativeVendingMachines();
		
		assertNotNull(ListaResultado);
		assertEquals(0, ListaResultado.size());		
	}
	
	@Test
	public void testNotOperativeVendingMachineCorrecto3() {
		VendingMachine NotOperativeVendingMachine = new VendingMachine(0, false, 5, 7, 10);
		
		vendingCityVacio.addVendingMachineCopy(21, NotOperativeVendingMachine);
		
		assertNotNull(vendingCityVacio);
		assertEquals(1, vendingCityVacio.getMachineList().size());
		
		ArrayList<VendingMachine> ListaResultado;
		ListaResultado = vendingCityVacio.notOperativeVendingMachines();
		
		assertNotNull(ListaResultado);
		assertEquals(1, ListaResultado.size());
		assertEquals(21, ListaResultado.get(0).getId());
	}
	
	@Test
	public void testNotOperativeVendingMachineCorrecto4() {
		
		vendingCityVacio.addVendingMachineCopy(12, vendingMachine1);
		
		assertNotNull(vendingCityVacio);
		assertEquals(1, vendingCityVacio.getMachineList().size());
		
		ArrayList<VendingMachine> ListaResultado;
		ListaResultado = vendingCityVacio.notOperativeVendingMachines();
		
		assertNotNull(ListaResultado);
		assertEquals(0, ListaResultado.size());
	}
	
	
	
	
	
	@Test
	public void testIdVaciasVendingMachineCorrecto1() {
		VendingMachine VendingMachineVacia1 = new VendingMachine(0, false, 5, 7, 10);
		VendingMachineVacia1.addLinea(0, producto1, 0);
		
		vendingCityVacio.addVendingMachineCopy(0, VendingMachineVacia1);
		vendingCityVacio.addVendingMachineCopy(1, vendingMachine1);
		vendingCityVacio.addVendingMachineCopy(2, VendingMachineVacia1);
		vendingCityVacio.addVendingMachineCopy(4, vendingMachine1);
		vendingCityVacio.addVendingMachineCopy(5, vendingMachine1);
		vendingCityVacio.addVendingMachineCopy(12345, VendingMachineVacia1);

		ArrayList<Integer> ListaResultado;		
		ListaResultado = vendingCityVacio.idVaciasVendingMachines();
		
		assertNotNull(vendingCityVacio);
		assertNotNull(ListaResultado);		

		assertEquals(6, vendingCityVacio.getMachineList().size());
		assertEquals(3, ListaResultado.size());
		assertEquals(0, ListaResultado.get(0), 0);
		assertEquals(2, ListaResultado.get(1), 0);
		assertEquals(12345, ListaResultado.get(2), 0);
	}
	
	@Test
	public void testIdVaciasVendingMachineCorrecto2() {
		ArrayList<Integer> ListaResultado;		
		ListaResultado = vendingCityVacio.idVaciasVendingMachines();
		
		assertNotNull(vendingCityVacio);
		assertNotNull(ListaResultado);		

		assertEquals(0, vendingCityVacio.getMachineList().size());
		assertEquals(0, ListaResultado.size());
	}
	
	
	
	
	
	@Test
	public void testVaciasVendingMachineCorrecto1() {
		VendingMachine VendingMachineVacia1 = new VendingMachine(0, false, 5, 7, 10);
		VendingMachineVacia1.addLinea(0, producto1, 0);
		
		vendingCityVacio.addVendingMachineCopy(0, VendingMachineVacia1);
		vendingCityVacio.addVendingMachineCopy(1, vendingMachine1);
		vendingCityVacio.addVendingMachineCopy(2, VendingMachineVacia1);
		vendingCityVacio.addVendingMachineCopy(4, vendingMachine1);
		vendingCityVacio.addVendingMachineCopy(5, vendingMachine1);
		vendingCityVacio.addVendingMachineCopy(12345, VendingMachineVacia1);

		ArrayList<VendingMachine> ListaResultado;		
		ListaResultado = vendingCityVacio.vaciasVendingMachines();
		
		assertNotNull(vendingCityVacio);
		assertNotNull(ListaResultado);		

		assertEquals(6, vendingCityVacio.getMachineList().size());
		assertEquals(3, ListaResultado.size());
		assertNotSame(VendingMachineVacia1, ListaResultado.get(0));
		assertNotSame(VendingMachineVacia1, ListaResultado.get(1));
		assertNotSame(VendingMachineVacia1, ListaResultado.get(2));
		assertEquals(0, ListaResultado.get(0).getId(), 0);
		assertEquals(2, ListaResultado.get(1).getId(), 0);
		assertEquals(12345, ListaResultado.get(2).getId(), 0);
	}
	
	@Test
	public void testVaciasVendingMachineCorrecto2() {
		ArrayList<VendingMachine> ListaResultado;		
		ListaResultado = vendingCityVacio.vaciasVendingMachines();
		
		assertNotNull(vendingCityVacio);
		assertNotNull(ListaResultado);		

		assertEquals(0, vendingCityVacio.getMachineList().size());
		assertEquals(0, ListaResultado.size());
	}
	
	
	
	
	
	@Test
	public void testfindVendingMachineCorrecto1() {
		vendingCityVacio.addVendingMachineCopy(0, vendingMachine1);
		vendingCityVacio.addVendingMachineCopy(2, vendingMachine1);
		vendingCityVacio.addVendingMachineCopy(3, vendingMachine1);
		vendingCityVacio.addVendingMachineCopy(4, vendingMachine1);
		vendingCityVacio.addVendingMachineCopy(9999, vendingMachine1);
		
		VendingMachine VendingMachine;
		VendingMachine = vendingCityVacio.findVendingMachine(9999);
		
		assertNotNull(vendingCityVacio);
		assertNotNull(VendingMachine);
		
		assertNotSame(vendingMachine1, VendingMachine);
		assertEquals(9999, VendingMachine.getId());
	}
	
	@Test
	public void testfindVendingMachineCorrecto2() {
		vendingCityVacio.addVendingMachineCopy(0, vendingMachine1);
		
		VendingMachine VendingMachine;
		VendingMachine = vendingCityVacio.findVendingMachine(0);
		
		assertNotNull(vendingCityVacio);
		assertNotNull(VendingMachine);
		
		assertNotSame(vendingMachine1, VendingMachine);
		assertEquals(0, VendingMachine.getId());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testfindVendingMachineNoApareceIncorrecto() {
		vendingCityVacio.addVendingMachineCopy(0, vendingMachine1);
		vendingCityVacio.addVendingMachineCopy(2, vendingMachine1);
		
		vendingCityVacio.findVendingMachine(9999);
	}
	
	
	
	// HashCode y Equals
	// Pruebas HashCode
	
		@Test
		public void testHashCodeCiudadesIguales() {
			VendingCity vendingCity1 = new VendingCity("Valladolid", 2, 10, 10, 5);
			VendingCity vendingCity2 = new VendingCity("Valladolid", 2, 10, 10, 5);
			int codigo1 = vendingCity1.hashCode();
			int codigo2 = vendingCity2.hashCode();
			
			assertNotNull(vendingCity1);
			assertNotNull(vendingCity2);
			assertNotSame(vendingCity1, vendingCity2);
			
			assertEquals(codigo1, codigo2, 0);
		}
		
		@Test
		public void testHashCodeCiudadesDistintoNombre() {
			VendingCity vendingCity1 = new VendingCity("Valladolid", 2, 10, 10, 5);
			VendingCity vendingCity2 = new VendingCity("Madrid", 2, 10, 10, 5);
			int codigo1 = vendingCity1.hashCode();
			int codigo2 = vendingCity2.hashCode();
			
			assertNotNull(vendingCity1);
			assertNotNull(vendingCity2);
			assertNotSame(vendingCity1, vendingCity2);
			
			assertNotEquals(codigo1, codigo2, 0);
		}
		
		@Test
		public void testHashCodeCiudadesDistintosNumeroMaquinas() {
			VendingCity vendingCity1 = new VendingCity("Valladolid", 2, 10, 10, 5);
			VendingCity vendingCity2 = new VendingCity("Valladolid", 5, 10, 10, 5);
			int codigo1 = vendingCity1.hashCode();
			int codigo2 = vendingCity2.hashCode();
			
			assertNotNull(vendingCity1);
			assertNotNull(vendingCity2);
			assertNotSame(vendingCity1, vendingCity2);
			
			assertNotEquals(codigo1, codigo2, 0);
		}
		
		@Test
		public void testHashCodeCiudadesDistintasFilas() {
			VendingCity vendingCity1 = new VendingCity("Valladolid", 2, 10, 10, 5);
			VendingCity vendingCity2 = new VendingCity("Valladolid", 2, 15, 10, 5);
			int codigo1 = vendingCity1.hashCode();
			int codigo2 = vendingCity2.hashCode();
			
			assertNotNull(vendingCity1);
			assertNotNull(vendingCity2);
			assertNotSame(vendingCity1, vendingCity2);
			
			assertNotEquals(codigo1, codigo2, 0);
		}
		
		@Test
		public void testHashCodeCiudadesDistintasColumnas() {
			VendingCity vendingCity1 = new VendingCity("Valladolid", 2, 10, 10, 5);
			VendingCity vendingCity2 = new VendingCity("Valladolid", 2, 10, 15, 5);
			int codigo1 = vendingCity1.hashCode();
			int codigo2 = vendingCity2.hashCode();
			
			assertNotNull(vendingCity1);
			assertNotNull(vendingCity2);
			assertNotSame(vendingCity1, vendingCity2);
			
			assertNotEquals(codigo1, codigo2, 0);
		}
		
		
		
		@Test
		public void testHashCodeCiudadesDistintasProfundidades() {
			VendingCity vendingCity1 = new VendingCity("Valladolid", 2, 10, 10, 5);
			VendingCity vendingCity2 = new VendingCity("Valladolid", 2, 10, 10, 10);
			int codigo1 = vendingCity1.hashCode();
			int codigo2 = vendingCity2.hashCode();
			
			assertNotNull(vendingCity1);
			assertNotNull(vendingCity2);
			assertNotSame(vendingCity1, vendingCity2);
			
			assertNotEquals(codigo1, codigo2, 0);
		}
		// Fin test HashCode

		
		// Pruebas Equals
		@Test
		public void testEqualsCiudadObject() {
			Object obj = new Object();
			VendingCity vendingCity1 = new VendingCity("Valladolid", 2, 10, 10, 5);
			
			assertNotNull(vendingCity1);
			assertFalse(vendingCity1.equals(obj));
		}
		
		@Test
		public void testEqualsCiudadNull() {
			VendingCity vendingCity1 = new VendingCity("Valladolid", 2, 10, 10, 5);
			
			assertNotNull(vendingCity1);
			assertFalse(vendingCity1.equals(null));
		}
		

		@Test
		public void testEqualsMismaCiudadProduct() {
			VendingCity vendingCity1 = new VendingCity("Valladolid", 2, 10, 10, 5);
			
			assertNotNull(vendingCity1);
			assertTrue(vendingCity1.equals(vendingCity1));
		}
		

		
		@Test
		public void testEqualsCiudadesIguales() {
			VendingCity vendingCity1 = new VendingCity("Valladolid", 2, 10, 10, 5);
			VendingCity vendingCity2 = new VendingCity("Valladolid", 2, 10, 10, 5);
			
			assertNotNull(vendingCity1);
			assertNotNull(vendingCity2);
			assertTrue(vendingCity1.equals(vendingCity2));
		}
		
		@Test
		public void testEqualsCiudadesDistintoNombre() {
			VendingCity vendingCity1 = new VendingCity("Valladolid", 2, 10, 10, 5);
			VendingCity vendingCity2 = new VendingCity("Madrid", 2, 10, 10, 5);
			
			assertNotNull(vendingCity1);
			assertNotNull(vendingCity2);
			assertFalse(vendingCity1.equals(vendingCity2));
		}
		
		@Test
		public void testEqualsCiudadesDistintoNumeroMaquinas() {
			VendingCity vendingCity1 = new VendingCity("Valladolid", 2, 10, 10, 5);
			VendingCity vendingCity2 = new VendingCity("Valladolid", 3, 10, 10, 5);
			
			assertNotNull(vendingCity1);
			assertNotNull(vendingCity2);
			assertFalse(vendingCity1.equals(vendingCity2));
		}
		
		@Test
		public void testEqualsCiudadesDistintasFilas() {
			VendingCity vendingCity1 = new VendingCity("Valladolid", 2, 10, 10, 5);
			VendingCity vendingCity2 = new VendingCity("Valladolid", 2, 15, 10, 5);
			
			assertNotNull(vendingCity1);
			assertNotNull(vendingCity2);
			assertFalse(vendingCity1.equals(vendingCity2));
		}
		

		@Test
		public void testEqualsCiudadesDistintasColumnas() {
			VendingCity vendingCity1 = new VendingCity("Valladolid", 2, 10, 10, 5);
			VendingCity vendingCity2 = new VendingCity("Valladolid", 2, 10, 15, 5);
			
			assertNotNull(vendingCity1);
			assertNotNull(vendingCity2);
			assertFalse(vendingCity1.equals(vendingCity2));
		}
		
		
		@Test
		public void testEqualsCiudadesDistintasProfundidades() {
			VendingCity vendingCity1 = new VendingCity("Valladolid", 2, 10, 10, 5);
			VendingCity vendingCity2 = new VendingCity("Valladolid", 2, 10, 10, 10);
			
			assertNotNull(vendingCity1);
			assertNotNull(vendingCity2);
			assertFalse(vendingCity1.equals(vendingCity2));
		}
		
		// Fin pruebas equals

		
	
}
