package vendingco;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * Proporciona todas las pruebas de la clase VendingSystem
 * 
 * @author tomcarr
 * @author javiram
 */
public class VendingSystemTest {
		
	VendingSystem VendingSystem1;
	VendingSystem VendingSystemVacio;
	
	VendingMachine VendingMachine1;
	
	Product Producto1;
	Product Producto2;
	
	@Before
    public void init() {		
		Producto1 = new Product("CocaCola", 1.5, "1-1-2022", "100000000007"); 
		Producto2 = new Product("galletas", 2.3, "1-1-2025", "112345678902"); 
		
		VendingMachine1 = new VendingMachine(0, true, 1, 3, 10);
		VendingMachine1.addLineaProducto(0, Producto1, 5);
		VendingMachine1.addLineaProducto(1, Producto1, 3);	
		VendingMachine1.addLineaProducto(2, Producto2, 4);	

		VendingSystem1 = new VendingSystem();
		VendingSystem1.addVendingMachineCopy(0, VendingMachine1);
		VendingSystem1.addVendingMachineCopy(1, VendingMachine1);
		
		VendingSystemVacio = new VendingSystem();
		
    }
	
	
	
	@Test
	public void test_ConstructorVacio_VendingSystem_Correcto() {
		VendingSystem VendingSystemActual = new VendingSystem();
		ArrayList<VendingMachine> ListaEsperada = new ArrayList<VendingMachine>();
		
		assertNotNull(VendingSystemActual);
		assertEquals(ListaEsperada, VendingSystemActual.getVendingMachines());
	}
	
	
	
	
	
	@Test
	public void test_Constructor_VendingSystem_Correcto1() {
		VendingSystem VendingSystemActual = new VendingSystem(0, 1,1,1);
		ArrayList<VendingMachine> ListaEsperada = new ArrayList<VendingMachine>();
		
		assertNotNull(VendingSystemActual);
		assertNotNull(VendingSystemActual.getVendingMachines());
		assertEquals(ListaEsperada, VendingSystemActual.getVendingMachines());
	}
	
	@Test
	public void test_Constructor_VendingSystem_Correcto2() {
		VendingSystem VendingSystemActual = new VendingSystem(3, 5, 7,10);
		
		assertNotNull(VendingSystemActual);
		assertNotNull(VendingSystemActual.getVendingMachines());
		assertEquals(3, VendingSystemActual.getVendingMachines().size());
		assertEquals(5, VendingSystemActual.getVendingMachines().get(1).getFilas());
		assertEquals(7, VendingSystemActual.getVendingMachines().get(1).getColumnas());
		assertEquals(10, VendingSystemActual.getVendingMachines().get(1).getLongitudLineas());
	}
		
	@Test (expected = IllegalArgumentException.class)
	public void test_Constructor_VendingSystem_NegativeVendingMachine_Incorrecto() {
		@SuppressWarnings("unused")
		VendingSystem VendingSystem = new VendingSystem(-1,1,1,1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_Constructor_VendingSystem_CeroFilas_Incorrecto() {
		@SuppressWarnings("unused")
		VendingSystem VendingSystem = new VendingSystem(1,0,1,1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_Constructor_VendingSystem_CeroColumnas_Incorrecto() {
		@SuppressWarnings("unused")
		VendingSystem VendingSystem = new VendingSystem(2,1,0,1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_Constructor_VendingSystem_CeroLongitudLinea_Incorrecto() {
		@SuppressWarnings("unused")
		VendingSystem VendingSystem = new VendingSystem(3,1,1,0);
	}
	
	
	

	
	@Test
	public void test_ConstructorCopia_VendingSystem_Correcto() {
		VendingSystem VendingSystemActual = new VendingSystem(VendingSystem1);
		
		assertNotNull(VendingSystemActual);
		
		assertNotSame(VendingSystem1.getVendingMachines(), VendingSystemActual.getVendingMachines());
		
		assertNotSame(VendingSystem1.findVendingMachine(0), VendingSystemActual.findVendingMachine(0));
		assertNotSame(VendingSystem1.findVendingMachine(1), VendingSystemActual.findVendingMachine(1));
		
		assertEquals(VendingSystem1.findVendingMachine(0).getId(), VendingSystemActual.findVendingMachine(0).getId());
		assertEquals(VendingSystem1.findVendingMachine(1).getId(), VendingSystemActual.findVendingMachine(1).getId());

		assertEquals(VendingSystem1.findVendingMachine(0).getLineas().length, VendingSystemActual.findVendingMachine(0).getLineas().length);
		assertEquals(VendingSystem1.findVendingMachine(1).getLineas().length, VendingSystemActual.findVendingMachine(1).getLineas().length);
		
		assertEquals(Producto1.getUpc(), VendingSystemActual.findVendingMachine(0).findLineCopy(1).getProducto().getUpc());
		
		assertEquals(3, VendingSystemActual.findVendingMachine(0).findLineCopy(1).getQuantity());
	
	}
	
	@Test (expected = NullPointerException.class)
	public void test_ConstructorCopia_VendingSystem_NullVS_Incorrecto() {
		@SuppressWarnings("unused")
		VendingSystem VendingSystem = new VendingSystem(null);
	}	
	



	
	@Test
	public void test_getVendingMachines_Correcto() {			
		assertNotNull(VendingSystem1);
		
		assertEquals(2, VendingSystem1.getVendingMachines().size());
		
		assertEquals(0, VendingSystem1.getVendingMachines().get(0).getId());
		assertEquals(1, VendingSystem1.getVendingMachines().get(1).getId());
		
		assertNotSame(VendingMachine1, VendingSystem1.getVendingMachines().get(0));
		assertNotSame(VendingMachine1, VendingSystem1.getVendingMachines().get(1));
	}
	
	
	
	
	
	@Test
	public void test_getIdVendingMachines_Correcto() {		
		VendingSystemVacio.addVendingMachineCopy(0, VendingMachine1);
		VendingSystemVacio.addVendingMachineCopy(1, VendingMachine1);
		VendingSystemVacio.addVendingMachineCopy(51, VendingMachine1);
		VendingSystemVacio.addVendingMachineCopy(2031, VendingMachine1);
		
		assertNotNull(VendingSystemVacio);
		assertNotNull(VendingMachine1);
		
		assertEquals(4, VendingSystemVacio.getVendingMachines().size());
		
		assertEquals(0, VendingSystemVacio.getIdVendingMachines().get(0), 0);
		assertEquals(1, VendingSystemVacio.getIdVendingMachines().get(1), 0);
		assertEquals(51, VendingSystemVacio.getIdVendingMachines().get(2), 0);
		assertEquals(2031, VendingSystemVacio.getIdVendingMachines().get(3), 0);
	}
	
	
	
	
	
	@Test
	public void test_setVendingMachines_Correcto() {
		ArrayList<VendingMachine> ListaEsperada = new ArrayList<VendingMachine>();
		ListaEsperada.add(VendingMachine1);
		
		VendingSystem VendingSystem = new VendingSystem();
		VendingSystem.setVendingMachines(ListaEsperada);
		
		assertNotNull(ListaEsperada);
		assertNotNull(VendingSystem);
		assertNotSame(ListaEsperada, VendingSystem.getVendingMachines());
		assertEquals(ListaEsperada.size(), VendingSystem.getVendingMachines().size());
		assertEquals(ListaEsperada.get(0).getId(), VendingSystem.getVendingMachines().get(0).getId());
	}
	
	@Test (expected = NullPointerException.class)
	public void test_setVendingMachines_Incorrecto() {		
		VendingSystem VendingSystem = new VendingSystem();
		VendingSystem.setVendingMachines(null);
	}
	
	
	
	
	
	@Test
	public void test_addVendingMachine_Correcto1() {
		VendingSystemVacio.addVendingMachine(0, true, 1, 2, 3);
		
		assertNotNull(VendingSystemVacio);
		assertEquals(1, VendingSystemVacio.getVendingMachines().size());
		
		assertEquals(0, VendingSystemVacio.getVendingMachines().get(0).getId());
		assertEquals(1, VendingSystemVacio.getVendingMachines().get(0).getFilas());
		assertEquals(2, VendingSystemVacio.getVendingMachines().get(0).getColumnas());
		assertEquals(3, VendingSystemVacio.getVendingMachines().get(0).getLongitudLineas());
		assertTrue(VendingSystemVacio.getVendingMachines().get(0).getState());
	}
	
	@Test
	public void test_addVendingMachine_Correcto2() {
		VendingSystemVacio.addVendingMachine(0, false, 1, 2, 3);
		
		assertNotNull(VendingSystemVacio);
		assertEquals(1, VendingSystemVacio.getVendingMachines().size());
		
		assertEquals(0, VendingSystemVacio.getVendingMachines().get(0).getId());
		assertEquals(1, VendingSystemVacio.getVendingMachines().get(0).getFilas());
		assertEquals(2, VendingSystemVacio.getVendingMachines().get(0).getColumnas());
		assertEquals(3, VendingSystemVacio.getVendingMachines().get(0).getLongitudLineas());
		assertFalse(VendingSystemVacio.getVendingMachines().get(0).getState());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_addVendingMachine_IdRepetido_Incorrecto() {	
		VendingSystemVacio.addVendingMachine(0, true, 1, 2, 3);
		VendingSystemVacio.addVendingMachine(0, true, 1, 2, 3);
	}	
	
	@Test (expected = IllegalArgumentException.class)
	public void test_addVendingMachine_IdNegative_Incorrecto() {	
		VendingSystemVacio.addVendingMachine(-1, true, 1, 1, 1);
	}	
	
	@Test (expected = IllegalArgumentException.class)
	public void test_addVendingMachine_FilasNegative_Incorrecto() {	
		VendingSystemVacio.addVendingMachine(0, true, 0, 1, 1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_addVendingMachine_ColumnasNegative_Incorrecto() {	
		VendingSystemVacio.addVendingMachine(0, true, 1, 0, 1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_addVendingMachine_LongitudLineasNegative_Incorrecto() {	
		VendingSystemVacio.addVendingMachine(0, true, 1, 1, 0);
	}
	
	
	
	
	
	@Test
	public void test_addVendingMachineCopy_Correcto1() {
		VendingSystemVacio.addVendingMachineCopy(0, VendingMachine1);
		
		assertNotNull(VendingSystemVacio);
		assertEquals(1, VendingSystemVacio.getVendingMachines().size());
		
		assertEquals(0, VendingSystemVacio.getVendingMachines().get(0).getId());
		assertEquals(1, VendingSystemVacio.getVendingMachines().get(0).getFilas());
		assertEquals(3, VendingSystemVacio.getVendingMachines().get(0).getColumnas());
		assertEquals(10, VendingSystemVacio.getVendingMachines().get(0).getLongitudLineas());
		assertTrue(VendingSystemVacio.getVendingMachines().get(0).getState());
	}
	
	@Test
	public void test_addVendingMachineCopy_Correcto() {
		VendingMachine VendingMachineExtra = new VendingMachine(0, false, 1, 3, 10);
		VendingSystemVacio.addVendingMachineCopy(0, VendingMachineExtra);
		
		assertNotNull(VendingSystemVacio);
		assertEquals(1, VendingSystemVacio.getVendingMachines().size());
		
		assertEquals(0, VendingSystemVacio.getVendingMachines().get(0).getId());
		assertEquals(1, VendingSystemVacio.getVendingMachines().get(0).getFilas());
		assertEquals(3, VendingSystemVacio.getVendingMachines().get(0).getColumnas());
		assertEquals(10, VendingSystemVacio.getVendingMachines().get(0).getLongitudLineas());
		assertFalse(VendingSystemVacio.getVendingMachines().get(0).getState());
	}
	
	@Test (expected = NullPointerException.class)
	public void test_addVendingMachineCopy_NullVendingMachine_Incorrecto() {	
		VendingSystemVacio.addVendingMachineCopy(0, null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_addVendingMachineCopy_IdRepetido_Incorrecto() {	
		VendingSystemVacio.addVendingMachineCopy(0, VendingMachine1);
		VendingSystemVacio.addVendingMachineCopy(0, VendingMachine1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_addVendingMachineCopy_IdNegativo_Incorrecto() {	
		VendingSystemVacio.addVendingMachineCopy(-1, VendingMachine1);
	}
	
	
	
	
	
	@Test
	public void test_DeleteVendingMachine_Correcto1() {
		VendingSystemVacio.addVendingMachineCopy(0, VendingMachine1);
		VendingSystemVacio.addVendingMachineCopy(1, VendingMachine1);
		VendingSystemVacio.addVendingMachineCopy(513, VendingMachine1);
		
		assertNotNull(VendingSystemVacio);
		assertEquals(3, VendingSystemVacio.getVendingMachines().size());
		
		VendingSystemVacio.deleteVendingMachine(1);

		assertEquals(2, VendingSystemVacio.getVendingMachines().size());
		assertEquals(0, VendingSystemVacio.getVendingMachines().get(0).getId());
		assertEquals(513, VendingSystemVacio.getVendingMachines().get(1).getId());
	}
	
	@Test
	public void test_DeleteVendingMachine_Correcto2() {
		VendingSystemVacio.addVendingMachineCopy(0, VendingMachine1);
		VendingSystemVacio.addVendingMachineCopy(1, VendingMachine1);
		VendingSystemVacio.addVendingMachineCopy(513, VendingMachine1);
		VendingSystemVacio.addVendingMachineCopy(312, VendingMachine1);
		
		assertNotNull(VendingSystemVacio);
		assertEquals(4, VendingSystemVacio.getVendingMachines().size());
		
		VendingSystemVacio.deleteVendingMachine(513);

		assertEquals(3, VendingSystemVacio.getVendingMachines().size());
		assertEquals(0, VendingSystemVacio.getVendingMachines().get(0).getId());
		assertEquals(1, VendingSystemVacio.getVendingMachines().get(1).getId());
		assertEquals(312, VendingSystemVacio.getVendingMachines().get(2).getId());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_DeleteVendingMachine_NoExisteId_Incorrecto() {	
		VendingSystem1.deleteVendingMachine(10);
	}
	
	
	
	
	
	@Test
	public void test_DeleteAllVendingMachine_Correcto() {
		VendingSystemVacio.addVendingMachineCopy(0, VendingMachine1);
		VendingSystemVacio.addVendingMachineCopy(1, VendingMachine1);
		VendingSystemVacio.addVendingMachineCopy(10, VendingMachine1);
		VendingSystemVacio.addVendingMachineCopy(213, VendingMachine1);
		VendingSystemVacio.addVendingMachineCopy(312, VendingMachine1);
		VendingSystemVacio.addVendingMachineCopy(513, VendingMachine1);
		
		assertNotNull(VendingSystemVacio);
		assertEquals(6, VendingSystemVacio.getVendingMachines().size());
		
		VendingSystemVacio.deleteAllVendingMachine();

		assertNotNull(VendingSystemVacio.getVendingMachines());
		assertEquals(0, VendingSystemVacio.getVendingMachines().size());
	}
	
	
	
	
	
	@Test
	public void test_OperativeVendingMachine_Correcto1() {
		VendingMachine NotOperativeVendingMachine = new VendingMachine(0, false, 5, 7, 10);
		
		VendingSystemVacio.addVendingMachineCopy(0, NotOperativeVendingMachine);
		VendingSystemVacio.addVendingMachineCopy(1, VendingMachine1);
		VendingSystemVacio.addVendingMachineCopy(2, VendingMachine1);
		VendingSystemVacio.addVendingMachineCopy(3, VendingMachine1);
		VendingSystemVacio.addVendingMachineCopy(4, VendingMachine1);
		VendingSystemVacio.addVendingMachineCopy(5, NotOperativeVendingMachine);
		VendingSystemVacio.addVendingMachineCopy(6, NotOperativeVendingMachine);
		VendingSystemVacio.addVendingMachineCopy(7, VendingMachine1);
		VendingSystemVacio.addVendingMachineCopy(8, VendingMachine1);
		VendingSystemVacio.addVendingMachineCopy(9, NotOperativeVendingMachine);
		
		assertNotNull(VendingSystemVacio);
		assertEquals(10, VendingSystemVacio.getVendingMachines().size());
		
		ArrayList<VendingMachine> ListaResultado;
		ListaResultado = VendingSystemVacio.operativeVendingMachines();
		
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
	public void test_OperativeVendingMachine_Correcto2() {				
		assertNotNull(VendingSystemVacio);
		assertEquals(0, VendingSystemVacio.getVendingMachines().size());
		
		ArrayList<VendingMachine> ListaResultado;
		ListaResultado = VendingSystemVacio.operativeVendingMachines();
		
		assertNotNull(ListaResultado);
		assertEquals(0, ListaResultado.size());		
	}
	
	@Test
	public void test_OperativeVendingMachine_Correcto3() {
		VendingMachine NotOperativeVendingMachine = new VendingMachine(0, false, 5, 7, 10);
		
		VendingSystemVacio.addVendingMachineCopy(0, NotOperativeVendingMachine);
		
		assertNotNull(VendingSystemVacio);
		assertEquals(1, VendingSystemVacio.getVendingMachines().size());
		
		ArrayList<VendingMachine> ListaResultado;
		ListaResultado = VendingSystemVacio.operativeVendingMachines();
		
		assertNotNull(ListaResultado);
		assertEquals(0, ListaResultado.size());
	}
	
	@Test
	public void test_OperativeVendingMachine_Correcto4() {
		
		VendingSystemVacio.addVendingMachineCopy(12, VendingMachine1);
		
		assertNotNull(VendingSystemVacio);
		assertEquals(1, VendingSystemVacio.getVendingMachines().size());
		
		ArrayList<VendingMachine> ListaResultado;
		ListaResultado = VendingSystemVacio.operativeVendingMachines();
		
		assertNotNull(ListaResultado);
		assertEquals(1, ListaResultado.size());
		assertEquals(12, ListaResultado.get(0).getId());
	}
	
	
	
	
	
	@Test
	public void test_NotOperativeVendingMachine_Correcto1() {
		VendingMachine NotOperativeVendingMachine = new VendingMachine(0, false, 5, 7, 10);
		
		VendingSystemVacio.addVendingMachineCopy(0, NotOperativeVendingMachine);
		VendingSystemVacio.addVendingMachineCopy(1, VendingMachine1);
		VendingSystemVacio.addVendingMachineCopy(2, VendingMachine1);
		VendingSystemVacio.addVendingMachineCopy(3, VendingMachine1);
		VendingSystemVacio.addVendingMachineCopy(4, VendingMachine1);
		VendingSystemVacio.addVendingMachineCopy(5, NotOperativeVendingMachine);
		VendingSystemVacio.addVendingMachineCopy(6, NotOperativeVendingMachine);
		VendingSystemVacio.addVendingMachineCopy(7, VendingMachine1);
		VendingSystemVacio.addVendingMachineCopy(8, VendingMachine1);
		VendingSystemVacio.addVendingMachineCopy(9, NotOperativeVendingMachine);
		
		assertNotNull(VendingSystemVacio);
		assertEquals(10, VendingSystemVacio.getVendingMachines().size());
		
		ArrayList<VendingMachine> ListaResultado;
		ListaResultado = VendingSystemVacio.notOperativeVendingMachines();
		
		assertNotNull(ListaResultado);
		assertEquals(4, ListaResultado.size());
		assertEquals(0, ListaResultado.get(0).getId());
		assertEquals(5, ListaResultado.get(1).getId());
		assertEquals(6, ListaResultado.get(2).getId());
		assertEquals(9, ListaResultado.get(3).getId());
	}
	
	@Test
	public void test_NotOperativeVendingMachine_Correcto2() {				
		assertNotNull(VendingSystemVacio);
		assertEquals(0, VendingSystemVacio.getVendingMachines().size());
		
		ArrayList<VendingMachine> ListaResultado;
		ListaResultado = VendingSystemVacio.notOperativeVendingMachines();
		
		assertNotNull(ListaResultado);
		assertEquals(0, ListaResultado.size());		
	}
	
	@Test
	public void test_NotOperativeVendingMachine_Correcto3() {
		VendingMachine NotOperativeVendingMachine = new VendingMachine(0, false, 5, 7, 10);
		
		VendingSystemVacio.addVendingMachineCopy(21, NotOperativeVendingMachine);
		
		assertNotNull(VendingSystemVacio);
		assertEquals(1, VendingSystemVacio.getVendingMachines().size());
		
		ArrayList<VendingMachine> ListaResultado;
		ListaResultado = VendingSystemVacio.notOperativeVendingMachines();
		
		assertNotNull(ListaResultado);
		assertEquals(1, ListaResultado.size());
		assertEquals(21, ListaResultado.get(0).getId());
	}
	
	@Test
	public void test_NotOperativeVendingMachine_Correcto4() {
		
		VendingSystemVacio.addVendingMachineCopy(12, VendingMachine1);
		
		assertNotNull(VendingSystemVacio);
		assertEquals(1, VendingSystemVacio.getVendingMachines().size());
		
		ArrayList<VendingMachine> ListaResultado;
		ListaResultado = VendingSystemVacio.notOperativeVendingMachines();
		
		assertNotNull(ListaResultado);
		assertEquals(0, ListaResultado.size());
	}
	
	
	
	
	
	@Test
	public void test_idVaciasVendingMachine_Correcto1() {
		VendingMachine VendingMachineVacia1 = new VendingMachine(0, false, 5, 7, 10);
		VendingMachineVacia1.addLineaProducto(0, Producto1, 0);
		
		VendingSystemVacio.addVendingMachineCopy(0, VendingMachineVacia1);
		VendingSystemVacio.addVendingMachineCopy(1, VendingMachine1);
		VendingSystemVacio.addVendingMachineCopy(2, VendingMachineVacia1);
		VendingSystemVacio.addVendingMachineCopy(4, VendingMachine1);
		VendingSystemVacio.addVendingMachineCopy(5, VendingMachine1);
		VendingSystemVacio.addVendingMachineCopy(12345, VendingMachineVacia1);

		ArrayList<Integer> ListaResultado;		
		ListaResultado = VendingSystemVacio.idVaciasVendingMachines();
		
		assertNotNull(VendingSystemVacio);
		assertNotNull(ListaResultado);		

		assertEquals(6, VendingSystemVacio.getVendingMachines().size());
		assertEquals(3, ListaResultado.size());
		assertEquals(0, ListaResultado.get(0), 0);
		assertEquals(2, ListaResultado.get(1), 0);
		assertEquals(12345, ListaResultado.get(2), 0);
	}
	
	@Test
	public void test_idVaciasVendingMachine_Correcto2() {
		ArrayList<Integer> ListaResultado;		
		ListaResultado = VendingSystemVacio.idVaciasVendingMachines();
		
		assertNotNull(VendingSystemVacio);
		assertNotNull(ListaResultado);		

		assertEquals(0, VendingSystemVacio.getVendingMachines().size());
		assertEquals(0, ListaResultado.size());
	}
	
	
	
	
	
	@Test
	public void test_VaciasVendingMachine_Correcto1() {
		VendingMachine VendingMachineVacia1 = new VendingMachine(0, false, 5, 7, 10);
		VendingMachineVacia1.addLineaProducto(0, Producto1, 0);
		
		VendingSystemVacio.addVendingMachineCopy(0, VendingMachineVacia1);
		VendingSystemVacio.addVendingMachineCopy(1, VendingMachine1);
		VendingSystemVacio.addVendingMachineCopy(2, VendingMachineVacia1);
		VendingSystemVacio.addVendingMachineCopy(4, VendingMachine1);
		VendingSystemVacio.addVendingMachineCopy(5, VendingMachine1);
		VendingSystemVacio.addVendingMachineCopy(12345, VendingMachineVacia1);

		ArrayList<VendingMachine> ListaResultado;		
		ListaResultado = VendingSystemVacio.vaciasVendingMachines();
		
		assertNotNull(VendingSystemVacio);
		assertNotNull(ListaResultado);		

		assertEquals(6, VendingSystemVacio.getVendingMachines().size());
		assertEquals(3, ListaResultado.size());
		assertNotSame(VendingMachineVacia1, ListaResultado.get(0));
		assertNotSame(VendingMachineVacia1, ListaResultado.get(1));
		assertNotSame(VendingMachineVacia1, ListaResultado.get(2));
		assertEquals(0, ListaResultado.get(0).getId(), 0);
		assertEquals(2, ListaResultado.get(1).getId(), 0);
		assertEquals(12345, ListaResultado.get(2).getId(), 0);
	}
	
	@Test
	public void test_VaciasVendingMachine_Correcto2() {
		ArrayList<VendingMachine> ListaResultado;		
		ListaResultado = VendingSystemVacio.vaciasVendingMachines();
		
		assertNotNull(VendingSystemVacio);
		assertNotNull(ListaResultado);		

		assertEquals(0, VendingSystemVacio.getVendingMachines().size());
		assertEquals(0, ListaResultado.size());
	}
	
	
	
	
	
	@Test
	public void test_findVendingMachine_Correcto1() {
		VendingSystemVacio.addVendingMachineCopy(0, VendingMachine1);
		VendingSystemVacio.addVendingMachineCopy(2, VendingMachine1);
		VendingSystemVacio.addVendingMachineCopy(3, VendingMachine1);
		VendingSystemVacio.addVendingMachineCopy(4, VendingMachine1);
		VendingSystemVacio.addVendingMachineCopy(9999, VendingMachine1);
		
		VendingMachine VendingMachine;
		VendingMachine = VendingSystemVacio.findVendingMachine(9999);
		
		assertNotNull(VendingSystemVacio);
		assertNotNull(VendingMachine);
		
		assertNotSame(VendingMachine1, VendingMachine);
		assertEquals(9999, VendingMachine.getId());
	}
	
	@Test
	public void test_findVendingMachine_Correcto2() {
		VendingSystemVacio.addVendingMachineCopy(0, VendingMachine1);
		
		VendingMachine VendingMachine;
		VendingMachine = VendingSystemVacio.findVendingMachine(0);
		
		assertNotNull(VendingSystemVacio);
		assertNotNull(VendingMachine);
		
		assertNotSame(VendingMachine1, VendingMachine);
		assertEquals(0, VendingMachine.getId());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_findVendingMachine_NoAparece_Incorrecto() {
		VendingSystemVacio.addVendingMachineCopy(0, VendingMachine1);
		VendingSystemVacio.addVendingMachineCopy(2, VendingMachine1);
		
		VendingSystemVacio.findVendingMachine(9999);
	}
	
	
	
}
