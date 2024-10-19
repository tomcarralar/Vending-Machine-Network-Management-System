package vendingco;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

/**
 * Proporciona todas las pruebas de la clase VendingSystem
 * 
 * @author tomcarr
 * @author javiram
 */
public class VendingSystemTest {
	
	String valladolid = "Valladolid";
	String madrid = "Madrid";
	String barcelona = "Barcelona";
	String santander = "Santander";
	VendingSystem vendingSystem;
	VendingCity vendingCityVacio;
	
	
	VendingCity vendingCityLleno1;
	VendingMachine vendingMachine1;
	
	VendingCity vendingCityLleno2;
	VendingMachine vendingMachine2;
	
	Product cocaCola;
	Product galletas;
	Product patatas;
	
	@Before
    public void init() {		
		vendingSystem = new VendingSystem(0, valladolid);
		vendingSystem.addSede(1, madrid);
		vendingSystem.addSede(2, barcelona);
		vendingSystem.addSede(1234567890, santander);
		
		vendingCityVacio = new VendingCity(valladolid);
		
		
		cocaCola = new Product("CocaCola", 1.5, "1-1-2022", "100000000007"); 
		galletas = new Product("galletas", 2.3, "1-1-2025", "112345678902");
		patatas = new Product("Patatas", 5, "20-3-2027", "999999999993"); 
		
		vendingMachine1 = new VendingMachine(0, true, 1, 3, 10);
		vendingMachine1.addLinea(0, cocaCola, 5);
		vendingMachine1.addLinea(1, cocaCola, 3);	
		vendingMachine1.addLinea(2, galletas, 4);
		
		vendingCityLleno1 = new VendingCity(barcelona);
		vendingCityLleno1.addVendingMachineCopy(1, vendingMachine1);
		

		vendingMachine2 = new VendingMachine(1, true, 3, 3, 100);
		vendingMachine2.addLinea(0, patatas, 1);
		vendingMachine2.addLinea(1, patatas, 2);	
		vendingMachine2.addLinea(2, galletas, 3);
		vendingMachine2.addLinea(3, cocaCola, 3);
		
		vendingCityLleno2 = new VendingCity(madrid);
		vendingCityLleno2.addVendingMachineCopy(1, vendingMachine1);
		vendingCityLleno2.addVendingMachineCopy(2, vendingMachine2);
	}
	
	@Test
	public void testConstructorVendingSystemVacio() {
		VendingSystem vs = new VendingSystem();
		
		assertNotNull(vs);
	}
	
	
	
	@Test
	public void testConstructorVendingSystemCorrectoParametros1() {
		VendingSystem vs = new VendingSystem(1, valladolid);
		
		assertNotNull(vs);
		assertEquals(1, vs.getNumeroProvincias());
		assertEquals(valladolid, vs.getNombreProvincia(1));
	}
	
	@Test
	public void testConstructorVendingSystemCorrectoParametros2() {
		VendingSystem vs = new VendingSystem(2147483647, valladolid);
		
		assertNotNull(vs);
		assertEquals(1, vs.getNumeroProvincias());
		assertEquals(valladolid, vs.getNombreProvincia(2147483647));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorVendingSystemIncorrectoCodigoNegativo() {
		new VendingSystem(-1, valladolid);
	}
	
	@Test (expected = NullPointerException.class)
	public void testConstructorVendingSystemIncorrectoNull() {
		new VendingSystem(1, null);
	}
	
	
	@Test
	public void testGetNombreProvinciaCorrecto() {
		assertNotNull(vendingSystem);
		assertEquals(4, vendingSystem.getNumeroProvincias());
		assertEquals(valladolid, vendingSystem.getNombreProvincia(0));
		assertEquals(madrid, vendingSystem.getNombreProvincia(1));
		assertEquals(barcelona, vendingSystem.getNombreProvincia(2));
		assertEquals(santander, vendingSystem.getNombreProvincia(1234567890));
	}
	
	
	
	@Test
	public void testConstructorSedeCorrecto() {
		VendingSystem vendingSystemActual = new VendingSystem(vendingCityVacio, 0);
		
		assertNotNull(vendingSystemActual);
		assertEquals(valladolid, vendingSystemActual.getNombreProvincia(0));
		assertEquals(1, vendingSystemActual.getNumeroProvincias());
	}
	
	@Test (expected = NullPointerException.class)
	public void testConstructorSedeIncorrectoNull() {
		new VendingSystem(null, 0);	
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorSedeIncorrectoCodigo() {
		new VendingSystem(vendingCityVacio, -1);	
	}
	
	
	
	@Test
	public void getNombreProvinciaCodigoCorrecto() {
		VendingSystem vendingSystemActual = new VendingSystem(5, "Sevilla");
		
		assertNotNull(vendingSystemActual);
		assertEquals("Sevilla", vendingSystemActual.getNombreProvincia(5));
	}
	
	@Test
	public void getNombreProvinciaVariosCodigoCorrecto() {
		assertNotNull(vendingSystem);
		
		assertEquals("Valladolid", vendingSystem.getNombreProvincia(0));
		assertEquals("Madrid", vendingSystem.getNombreProvincia(1));
		assertEquals("Barcelona", vendingSystem.getNombreProvincia(2));
		assertEquals("Santander", vendingSystem.getNombreProvincia(1234567890));
	
	}
	
	@Test (expected = NullPointerException.class)
	public void getNombreProvinciaCodigoNoExiste() {
		assertNotNull(vendingSystem);
		
		assertEquals("", vendingSystem.getNombreProvincia(999));
	}
	
	@Test (expected = NullPointerException.class)
	public void getNombreProvinciaCodigoExisteCodigoNoExiste() {
		assertNotNull(vendingSystem);
		

		assertEquals("Valladolid", vendingSystem.getNombreProvincia(0));
		assertEquals("", vendingSystem.getNombreProvincia(100));
	}
	
	@Test (expected = NullPointerException.class)
	public void getNombreProvinciaCodigoNegativo() {
		assertNotNull(vendingSystem);
		
		assertEquals("", vendingSystem.getNombreProvincia(-1));
	}
	
	
	
	
	@Test
	public void testGetCopiaProvinciaCorrecto() {
		VendingCity vendingCityEsperado = new VendingCity(valladolid);
		VendingSystem vendingSystemActual = new VendingSystem(vendingCityEsperado, 1);
		
		assertNotNull(vendingCityEsperado);
		assertNotNull(vendingSystemActual);
		assertNotSame(vendingCityEsperado, vendingSystemActual.getCopiaProvincia(1));
		
		assertTrue(vendingCityEsperado.equals(vendingSystemActual.getCopiaProvincia(1)));
	}
	
	@Test (expected = NullPointerException.class)
	public void testGetCopiaProvinciaCodigoNoExiste() {
		VendingCity vendingCityEsperado = new VendingCity(valladolid);
		VendingSystem vendingSystemActual = new VendingSystem(vendingCityEsperado, 1);
		
		assertNotNull(vendingCityEsperado);
		assertNotNull(vendingSystemActual);
		assertNotSame(vendingCityEsperado, vendingSystemActual.getCopiaProvincia(1));
		
		vendingSystemActual.getCopiaProvincia(100);
	}
	
	@Test (expected = NullPointerException.class)
	public void testGetCopiaProvinciaCodigoNegativo() {
		VendingCity vendingCityEsperado = new VendingCity(valladolid);
		VendingSystem vendingSystemActual = new VendingSystem(vendingCityEsperado, 1);
		
		assertNotNull(vendingCityEsperado);
		assertNotNull(vendingSystemActual);
		assertNotSame(vendingCityEsperado, vendingSystemActual.getCopiaProvincia(1));
		
		vendingSystemActual.getCopiaProvincia(-1);
	}
	
	
	
	@Test
	public void testAppendSedeCorrecto() {
		VendingCity vendingCityEsperado = new VendingCity(valladolid);
		VendingSystem vendingSystemActual = new VendingSystem();
		
		assertNotNull(vendingCityEsperado);
		assertNotNull(vendingSystemActual);
		
		vendingSystemActual.appendSede(0, vendingCityEsperado);
		
		assertEquals(1, vendingSystemActual.getNumeroProvincias());
		assertEquals(1, vendingSystemActual.getCodigos().size());
	
		assertNotSame(vendingCityEsperado, vendingSystemActual.getCopiaProvincia(0));
		assertTrue(vendingCityEsperado.equals(vendingSystemActual.getCopiaProvincia(0)));
	}
	
	@Test
	public void testAppendSedeCorrectoVariasSedes() {
		VendingCity vendingCityEsperado1 = new VendingCity(valladolid);
		VendingCity vendingCityEsperado2 = new VendingCity(madrid);
		VendingSystem vendingSystemActual = new VendingSystem();
		
		assertNotNull(vendingCityEsperado1);
		assertNotNull(vendingCityEsperado2);
		assertNotNull(vendingSystemActual);
		
		vendingSystemActual.appendSede(0, vendingCityEsperado1);
		vendingSystemActual.appendSede(1, vendingCityEsperado2);
		
		assertEquals(2, vendingSystemActual.getNumeroProvincias());
		assertEquals(2, vendingSystemActual.getCodigos().size());
		
		assertNotSame(vendingCityEsperado1, vendingSystemActual.getCopiaProvincia(0));
		assertTrue(vendingCityEsperado1.equals(vendingSystemActual.getCopiaProvincia(0)));

		assertNotSame(vendingCityEsperado2, vendingSystemActual.getCopiaProvincia(1));
		assertTrue(vendingCityEsperado2.equals(vendingSystemActual.getCopiaProvincia(1)));
	}
	
	
	@Test (expected = IllegalArgumentException.class)
	public void testAppendSedeIncorrectoCodigoNegativo() {
		VendingCity vendingCityActual = new VendingCity(valladolid);
		VendingSystem vendingSystemActual = new VendingSystem();
		
		vendingSystemActual.appendSede(-1, vendingCityActual);
	}
	
	@Test (expected = NullPointerException.class)
	public void testAppendSedeIncorrectoSedeNull() {
		VendingSystem vendingSystemActual = new VendingSystem();
		
		vendingSystemActual.appendSede(0, null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAppendSedeIncorrectoSedeCodigoRepetido() {
		VendingCity vendingCityActual1 = new VendingCity(valladolid);
		VendingCity vendingCityActual2 = new VendingCity(madrid);
		VendingSystem vendingSystemActual = new VendingSystem();
		
		vendingSystemActual.appendSede(0, vendingCityActual1);
		vendingSystemActual.appendSede(0, vendingCityActual2);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAppendSedeIncorrectoSedeNombreRepetido() {
		VendingCity vendingCityActual1 = new VendingCity(valladolid);
		VendingCity vendingCityActual2 = new VendingCity(valladolid);
		VendingSystem vendingSystemActual = new VendingSystem();
		
		vendingSystemActual.appendSede(0, vendingCityActual1);
		vendingSystemActual.appendSede(1, vendingCityActual2);
	}
	
	
	
	@Test
	public void testAddSedeCorrecta() {
		VendingCity vendingCityEsperado = new VendingCity(valladolid);
		VendingSystem vendingSystemActual = new VendingSystem();
		
		assertNotNull(vendingSystemActual);
		assertNotNull(vendingCityEsperado);
		
		vendingSystemActual.addSede(0, valladolid);
		
		assertEquals(1, vendingSystemActual.getNumeroProvincias());
		assertEquals(1, vendingSystemActual.getCodigos().size());
		
		assertNotSame(valladolid, vendingSystemActual.getCopiaProvincia(0));
		assertTrue(vendingCityEsperado.equals(vendingSystemActual.getCopiaProvincia(0)));
	}
	
	@Test
	public void testAddSedeCorrectaVarias() {
		VendingCity vendingCityEsperado1 = new VendingCity(valladolid);
		VendingCity vendingCityEsperado2 = new VendingCity(madrid);
		VendingSystem vendingSystemActual = new VendingSystem();
		
		assertNotNull(vendingSystemActual);
		assertNotNull(vendingCityEsperado1);
		assertNotNull(vendingCityEsperado2);
		
		vendingSystemActual.addSede(0, valladolid);
		vendingSystemActual.addSede(1, madrid);
		
		assertEquals(2, vendingSystemActual.getNumeroProvincias());
		assertEquals(2, vendingSystemActual.getCodigos().size());
		
		assertNotSame(valladolid, vendingSystemActual.getCopiaProvincia(0));
		assertTrue(vendingCityEsperado1.equals(vendingSystemActual.getCopiaProvincia(0)));

		assertNotSame(vendingCityEsperado2, vendingSystemActual.getCopiaProvincia(1));
		assertTrue(vendingCityEsperado2.equals(vendingSystemActual.getCopiaProvincia(1)));
	}
	

	@Test (expected = NullPointerException.class)
	public void testAddSedeIncorrectaNombreNull() {
		VendingSystem vendingSystemActual = new VendingSystem();
		
		vendingSystemActual.addSede(0, null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAddSedeIncorrectaCodigoNegativo() {
		VendingSystem vendingSystemActual = new VendingSystem();
		
		vendingSystemActual.addSede(-1, valladolid);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAddSedeIncorrectaCodigoRepetido() {
		VendingSystem vendingSystemActual = new VendingSystem();
		
		vendingSystemActual.addSede(1, valladolid);
		vendingSystemActual.addSede(1, madrid);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAddSedeIncorrectaCodigoRepetidoAvanzado() {
		VendingSystem vendingSystemActual = new VendingSystem();
		
		vendingSystemActual.addSede(1, valladolid);
		vendingSystemActual.addSede(2, madrid);
		vendingSystemActual.addSede(3, barcelona);

		vendingSystemActual.addSede(2, santander);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAddSedeIncorrectaNombreRepetido() {
		VendingSystem vendingSystemActual = new VendingSystem();
		
		vendingSystemActual.addSede(1, valladolid);
		vendingSystemActual.addSede(2, valladolid);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAddSedeIncorrectaCodigoNombreAvanzado() {
		VendingSystem vendingSystemActual = new VendingSystem();
		
		vendingSystemActual.addSede(1, valladolid);
		vendingSystemActual.addSede(2, madrid);
		vendingSystemActual.addSede(3, barcelona);

		vendingSystemActual.addSede(4, madrid);
	}
	
	
	
	@Test
	public void testDeleteSedeCorrecto() {
		VendingSystem vendingSystemActual = new VendingSystem();
		
		assertNotNull(vendingSystemActual);
		
		vendingSystemActual.addSede(1, valladolid);
		

		assertEquals(1, vendingSystemActual.getNumeroProvincias());
		assertEquals(1, vendingSystemActual.getCodigos().size());
		assertEquals(valladolid, vendingSystemActual.getNombreProvincia(1));
		
		vendingSystemActual.deleteSede(1);

		assertEquals(0, vendingSystemActual.getNumeroProvincias());
		assertEquals(0, vendingSystemActual.getCodigos().size());		
	}
	
	@Test
	public void testDeleteSedeCorrectoVarios() {
		VendingSystem vendingSystemActual = new VendingSystem();
		
		assertNotNull(vendingSystemActual);
		
		vendingSystemActual.addSede(1, valladolid);
		vendingSystemActual.addSede(2, madrid);
		vendingSystemActual.addSede(3, barcelona);
		

		assertEquals(3, vendingSystemActual.getNumeroProvincias());
		assertEquals(3, vendingSystemActual.getCodigos().size());
		assertEquals(valladolid, vendingSystemActual.getNombreProvincia(1));
		assertEquals(madrid, vendingSystemActual.getNombreProvincia(2));
		assertEquals(barcelona, vendingSystemActual.getNombreProvincia(3));
		
		vendingSystemActual.deleteSede(2);

		assertEquals(2, vendingSystemActual.getNumeroProvincias());
		assertEquals(2, vendingSystemActual.getCodigos().size());
		
		ArrayList<Integer> codigos = vendingSystemActual.getCodigos();
		for (int i = 0; i < codigos.size(); i++) {
			assertNotEquals(2, codigos.get(i), 0); 
		}

		assertEquals(valladolid, vendingSystemActual.getNombreProvincia(1));
		assertEquals(barcelona, vendingSystemActual.getNombreProvincia(3));	
	}
	
	@Test
	public void testDeleteSedeCorrectoNoEsta() {
		VendingSystem vendingSystemActual = new VendingSystem();
		
		assertNotNull(vendingSystemActual);
		
		vendingSystemActual.addSede(1, valladolid);
		

		assertEquals(1, vendingSystemActual.getNumeroProvincias());
		assertEquals(1, vendingSystemActual.getCodigos().size());
		assertEquals(valladolid, vendingSystemActual.getNombreProvincia(1));
		
		vendingSystemActual.deleteSede(3);

		assertEquals(1, vendingSystemActual.getNumeroProvincias());
		assertEquals(1, vendingSystemActual.getCodigos().size());	
		assertEquals(valladolid, vendingSystemActual.getNombreProvincia(1));	
	}
	
	@Test
	public void testDeleteSedeCorrectoNoEstaNegativo() {
		VendingSystem vendingSystemActual = new VendingSystem();
		
		assertNotNull(vendingSystemActual);
		
		vendingSystemActual.addSede(1, valladolid);
		

		assertEquals(1, vendingSystemActual.getNumeroProvincias());
		assertEquals(1, vendingSystemActual.getCodigos().size());
		assertEquals(valladolid, vendingSystemActual.getNombreProvincia(1));
		
		vendingSystemActual.deleteSede(-1);

		assertEquals(1, vendingSystemActual.getNumeroProvincias());
		assertEquals(1, vendingSystemActual.getCodigos().size());	
		assertEquals(valladolid, vendingSystemActual.getNombreProvincia(1));	
	}
	
	@Test
	public void testDeleteSedeCorrectoNoEstaCero() {
		VendingSystem vendingSystemActual = new VendingSystem();
		
		assertNotNull(vendingSystemActual);
		
		vendingSystemActual.addSede(1, valladolid);
		

		assertEquals(1, vendingSystemActual.getNumeroProvincias());
		assertEquals(1, vendingSystemActual.getCodigos().size());
		assertEquals(valladolid, vendingSystemActual.getNombreProvincia(1));
		
		vendingSystemActual.deleteSede(0);

		assertEquals(1, vendingSystemActual.getNumeroProvincias());
		assertEquals(1, vendingSystemActual.getCodigos().size());	
		assertEquals(valladolid, vendingSystemActual.getNombreProvincia(1));	
	}
	
	@Test
	public void testDeleteSedeCorrectoVariosNoEsta() {
		VendingSystem vendingSystemActual = new VendingSystem();
		
		assertNotNull(vendingSystemActual);
		
		vendingSystemActual.addSede(1, valladolid);
		vendingSystemActual.addSede(2, madrid);
		vendingSystemActual.addSede(3, barcelona);
		

		assertEquals(3, vendingSystemActual.getNumeroProvincias());
		assertEquals(3, vendingSystemActual.getCodigos().size());
		assertEquals(valladolid, vendingSystemActual.getNombreProvincia(1));
		assertEquals(madrid, vendingSystemActual.getNombreProvincia(2));
		assertEquals(barcelona, vendingSystemActual.getNombreProvincia(3));
		
		vendingSystemActual.deleteSede(4);

		assertEquals(3, vendingSystemActual.getNumeroProvincias());
		assertEquals(3, vendingSystemActual.getCodigos().size());
		assertEquals(valladolid, vendingSystemActual.getNombreProvincia(1));
		assertEquals(madrid, vendingSystemActual.getNombreProvincia(2));
		assertEquals(barcelona, vendingSystemActual.getNombreProvincia(3));	
	}
	
	@Test
	public void testDeleteSedeCorrectoVariosNoEstaNegativo() {
		VendingSystem vendingSystemActual = new VendingSystem();
		
		assertNotNull(vendingSystemActual);
		
		vendingSystemActual.addSede(1, valladolid);
		vendingSystemActual.addSede(2, madrid);
		vendingSystemActual.addSede(3, barcelona);
		

		assertEquals(3, vendingSystemActual.getNumeroProvincias());
		assertEquals(3, vendingSystemActual.getCodigos().size());
		assertEquals(valladolid, vendingSystemActual.getNombreProvincia(1));
		assertEquals(madrid, vendingSystemActual.getNombreProvincia(2));
		assertEquals(barcelona, vendingSystemActual.getNombreProvincia(3));
		
		vendingSystemActual.deleteSede(-1);

		assertEquals(3, vendingSystemActual.getNumeroProvincias());
		assertEquals(3, vendingSystemActual.getCodigos().size());
		assertEquals(valladolid, vendingSystemActual.getNombreProvincia(1));
		assertEquals(madrid, vendingSystemActual.getNombreProvincia(2));
		assertEquals(barcelona, vendingSystemActual.getNombreProvincia(3));
	}
	
	
	
	@Test
	public void testGetCodigosCorrectoVacio() {
		ArrayList<Integer> arrayEsperado = new ArrayList<>();
		
		VendingSystem vendingSystemActual = new VendingSystem();
		
		assertNotNull(arrayEsperado);
		assertNotNull(vendingSystemActual);
		
		assertNotSame(arrayEsperado, vendingSystemActual.getCodigos());
		
		assertEquals(arrayEsperado, vendingSystemActual.getCodigos());
	}
	
	@Test
	public void testGetCodigosCorrectoCodigo() {
		ArrayList<Integer> arrayEsperado = new ArrayList<>();
		arrayEsperado.add(1);
		
		VendingSystem vendingSystemActual = new VendingSystem(1, valladolid);
		
		assertNotNull(arrayEsperado);
		assertNotNull(vendingSystemActual);
		
		assertNotSame(arrayEsperado, vendingSystemActual.getCodigos());
		
		assertEquals(arrayEsperado, vendingSystemActual.getCodigos());
	}
	
	@Test
	public void testGetCodigosCorrectoVariosCodigo() {
		ArrayList<Integer> arrayEsperado = new ArrayList<>();
		arrayEsperado.add(1);
		arrayEsperado.add(2);
		arrayEsperado.add(3);
		arrayEsperado.add(123456789);
		
		VendingSystem vendingSystemActual = new VendingSystem(1, valladolid);
		vendingSystemActual.addSede(2, madrid);
		vendingSystemActual.addSede(3, barcelona);
		vendingSystemActual.addSede(123456789, santander);
		
		assertNotNull(arrayEsperado);
		assertNotNull(vendingSystemActual);
		
		assertNotSame(arrayEsperado, vendingSystemActual.getCodigos());
		
		assertEquals(arrayEsperado, vendingSystemActual.getCodigos());
	}
	
	@Test
	public void testGetCodigosCorrectoCodigoDelete() {
		ArrayList<Integer> arrayEsperado = new ArrayList<>();
		arrayEsperado.add(2);
		arrayEsperado.add(123456789);
		
		VendingSystem vendingSystemActual = new VendingSystem(1, valladolid);
		vendingSystemActual.addSede(2, madrid);
		vendingSystemActual.addSede(3, barcelona);
		vendingSystemActual.addSede(123456789, santander);
		vendingSystemActual.deleteSede(1);
		vendingSystemActual.deleteSede(3);
		
		assertNotNull(arrayEsperado);
		assertNotNull(vendingSystemActual);
		
		assertNotSame(arrayEsperado, vendingSystemActual.getCodigos());
		
		assertEquals(arrayEsperado, vendingSystemActual.getCodigos());
	}
	
	
	
	
	@Test
	public void testGetNumeroMaquinasVendingVacio() {
		VendingSystem vendingSystemActual = new VendingSystem(vendingCityVacio, 1);
		
		assertNotNull(vendingSystemActual);
		assertNotNull(vendingCityVacio);
		
		assertEquals(0, vendingSystemActual.getNumeroMaquinasVending(1));
	}
	
	@Test
	public void testGetNumeroMaquinasVendingLleno1() {
		VendingSystem vendingSystemActual = new VendingSystem(vendingCityLleno1, 1);
		
		assertNotNull(vendingSystemActual);
		assertNotNull(vendingCityLleno1);
		
		assertEquals(1, vendingSystemActual.getNumeroMaquinasVending(1));
	}
	
	@Test
	public void testGetNumeroMaquinasVendingLleno2() {
		VendingSystem vendingSystemActual = new VendingSystem(vendingCityLleno2, 1);
		
		assertNotNull(vendingSystemActual);
		assertNotNull(vendingCityLleno2);
		
		assertEquals(2, vendingSystemActual.getNumeroMaquinasVending(1));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testGetNumeroMaquinasVendingCodigoNoEsta() {
		VendingSystem vendingSystemActual = new VendingSystem(vendingCityVacio, 1);
		
		assertNotNull(vendingSystemActual);
		assertNotNull(vendingCityVacio);
		
		assertEquals(0, vendingSystemActual.getNumeroMaquinasVending(2));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testGetNumeroMaquinasVendingCodigoNegativo() {
		VendingSystem vendingSystemActual = new VendingSystem(vendingCityVacio, 1);
		
		assertNotNull(vendingSystemActual);
		assertNotNull(vendingCityVacio);
		
		assertEquals(0, vendingSystemActual.getNumeroMaquinasVending(-1));
	}
	
	
	
	
	@Test
	public void testGetMaquinasVendingVacio() {
		VendingSystem vendingSystemActual = new VendingSystem(vendingCityVacio, 1);
		ArrayList<Integer> listaEsperada = new ArrayList<>();
		
		assertNotNull(vendingSystemActual);
		assertNotNull(vendingCityVacio);
		
		assertNotSame(listaEsperada, vendingSystemActual.getMaquinasVending(1));
		assertEquals(listaEsperada, vendingSystemActual.getMaquinasVending(1));
	}
	
	@Test
	public void testGetMaquinasVendingLleno1() {
		VendingSystem vendingSystemActual = new VendingSystem(vendingCityLleno1, 1);
		
		VendingCity vendingCityEsperado = new VendingCity(barcelona);
		vendingCityEsperado.addVendingMachineCopy(1, vendingMachine1);
		VendingSystem vendingSystemEsperado = new VendingSystem(vendingCityEsperado, 1);
		
		assertNotNull(vendingSystemActual);
		assertNotNull(vendingCityEsperado);
		assertNotNull(vendingSystemEsperado);

		assertNotSame(vendingSystemEsperado, vendingSystemActual);
		assertNotSame(vendingSystemEsperado.getMaquinasVending(1), vendingSystemActual.getMaquinasVending(1));
		
		for (int i = 0; i < vendingSystemEsperado.getMaquinasVending(1).size(); i++) {
			assertEquals(vendingSystemEsperado.getMaquinasVending(1).get(i), vendingSystemActual.getMaquinasVending(1).get(i));
		}
	}
	
	
	@Test
	public void testGetMaquinasVendingLleno2() {
		VendingSystem vendingSystemActual = new VendingSystem(vendingCityLleno2, 1);
		
		VendingCity vendingCityEsperado = new VendingCity(madrid);
		vendingCityEsperado.addVendingMachineCopy(1, vendingMachine1);
		vendingCityEsperado.addVendingMachineCopy(2, vendingMachine2);
		VendingSystem vendingSystemEsperado = new VendingSystem(vendingCityEsperado, 1);
		
		assertNotNull(vendingSystemActual);
		assertNotNull(vendingCityEsperado);
		assertNotNull(vendingSystemEsperado);

		assertNotSame(vendingSystemEsperado, vendingSystemActual);
		assertNotSame(vendingSystemEsperado.getMaquinasVending(1), vendingSystemActual.getMaquinasVending(1));
		
		for (int i = 0; i < vendingSystemEsperado.getMaquinasVending(1).size(); i++) {
			assertEquals(vendingSystemEsperado.getMaquinasVending(1).get(i), vendingSystemActual.getMaquinasVending(1).get(i));
		}
	}
	

	@Test (expected = IllegalArgumentException.class)
	public void testGetMaquinasVendingCodigoNoEsta() {
		VendingSystem vendingSystemActual = new VendingSystem(vendingCityLleno1, 1);
		vendingSystemActual.getMaquinasVending(2);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testGetMaquinasVendingCodigoNegativo() {
		VendingSystem vendingSystemActual = new VendingSystem(vendingCityLleno1, 1);
		
		vendingSystemActual.getMaquinasVending(-1);
	}
	
	
	
	@Test
	public void testGetAllNumeroMaquinasVendingCorrectoVacio() {
		VendingSystem vendingSystemActual = new VendingSystem(vendingCityVacio, 1);
		
		assertNotNull(vendingSystemActual);
		assertNotNull(vendingCityVacio);
		
		assertEquals(0, vendingSystemActual.getAllNumeroMaquinasVending());	
	}
	
	
	@Test
	public void testGetAllNumeroMaquinasVendingCorrectoLleno1() {
		VendingSystem vendingSystemActual = new VendingSystem(vendingCityLleno1, 1);

		assertNotNull(vendingSystemActual);
		assertNotNull(vendingCityLleno1);
		
		assertEquals(1, vendingSystemActual.getAllNumeroMaquinasVending());	
	}
	
	@Test
	public void testGetAllNumeroMaquinasVendingCorrectoLleno2() {
		VendingSystem vendingSystemActual = new VendingSystem(vendingCityLleno2, 1);

		assertNotNull(vendingSystemActual);
		assertNotNull(vendingCityLleno1);
		
		assertEquals(2, vendingSystemActual.getAllNumeroMaquinasVending());	
	}
	
	
	@Test
	public void testGetAllNumeroMaquinasVendingCorrectoLlenoVarios1() {
		VendingCity vendingCityActual = new VendingCity(santander);
		vendingCityActual.addVendingMachineCopy(0, vendingMachine1);
		vendingCityActual.addVendingMachineCopy(1, vendingMachine1);
		vendingCityActual.addVendingMachineCopy(2, vendingMachine2);
		vendingCityActual.addVendingMachineCopy(3, vendingMachine2);
		vendingCityActual.addVendingMachineCopy(4, vendingMachine1);
		
		VendingSystem vendingSystemActual = new VendingSystem(vendingCityActual, 1);
		vendingSystemActual.appendSede(2, vendingCityVacio);
		vendingSystemActual.appendSede(3, vendingCityLleno1);
		vendingSystemActual.appendSede(4, vendingCityLleno2);

		assertNotNull(vendingSystemActual);
		assertNotNull(vendingCityActual);
		assertNotNull(vendingCityVacio);
		assertNotNull(vendingCityLleno1);
		assertNotNull(vendingCityLleno2);
		
		assertEquals(8, vendingSystemActual.getAllNumeroMaquinasVending());		
	}
	
	
	
	

	@Test
	public void testGetAllMaquinasVendingCorrectoVacio() {
		VendingSystem vendingSystemActual = new VendingSystem(vendingCityVacio, 1);
		Map<String, ArrayList<VendingMachine>> hashEsperado = new HashMap();	
		hashEsperado.put(valladolid, vendingCityVacio.getMachineList());
		
		assertNotNull(vendingSystemActual);
		assertNotNull(vendingCityVacio);
		assertNotNull(hashEsperado);
		
		assertEquals(hashEsperado, vendingSystemActual.getAllMaquinasVending());	
	}
	
	
	@Test
	public void testGetAllMaquinasVendingCorrectoLleno1() {
		VendingSystem vendingSystemActual = new VendingSystem(vendingCityLleno1, 1);
		Map<String, ArrayList<VendingMachine>> hashEsperado = new HashMap();	
		hashEsperado.put(barcelona, vendingCityLleno1.getMachineList());
		
		assertNotNull(vendingSystemActual);
		assertNotNull(vendingCityLleno1);
		assertNotNull(hashEsperado);
		
		assertEquals(hashEsperado, vendingSystemActual.getAllMaquinasVending());	
	}
	
	@Test
	public void testGetAllMaquinasVendingCorrectoLleno2() {
		VendingSystem vendingSystemActual = new VendingSystem(vendingCityLleno2, 1);
		Map<String, ArrayList<VendingMachine>> hashEsperado = new HashMap();	
		hashEsperado.put(madrid, vendingCityLleno2.getMachineList());
		
		assertNotNull(vendingSystemActual);
		assertNotNull(vendingCityLleno2);
		assertNotNull(hashEsperado);
		
		assertEquals(hashEsperado, vendingSystemActual.getAllMaquinasVending());	
	}
	
	
	@Test
	public void testGetAllMaquinasVendingCorrectoLlenoVarios1() {
		VendingCity vendingCityActual = new VendingCity(santander);
		vendingCityActual.addVendingMachineCopy(0, vendingMachine1);
		vendingCityActual.addVendingMachineCopy(1, vendingMachine1);
		vendingCityActual.addVendingMachineCopy(2, vendingMachine2);
		vendingCityActual.addVendingMachineCopy(3, vendingMachine2);
		vendingCityActual.addVendingMachineCopy(4, vendingMachine1);
		
		VendingSystem vendingSystemActual = new VendingSystem(vendingCityActual, 1);
		vendingSystemActual.appendSede(2, vendingCityVacio);
		vendingSystemActual.appendSede(3, vendingCityLleno1);
		vendingSystemActual.appendSede(4, vendingCityLleno2);
		
		Map<String, ArrayList<VendingMachine>> hashEsperado = new HashMap();	
		hashEsperado.put(santander, vendingCityActual.getMachineList());
		hashEsperado.put(valladolid, vendingCityVacio.getMachineList());
		hashEsperado.put(barcelona, vendingCityLleno1.getMachineList());
		hashEsperado.put(madrid, vendingCityLleno2.getMachineList());

		assertNotNull(vendingSystemActual);
		assertNotNull(vendingCityActual);
		assertNotNull(vendingCityVacio);
		assertNotNull(vendingCityLleno1);
		assertNotNull(vendingCityLleno2);
		assertNotNull(hashEsperado);
		
		assertEquals(hashEsperado, vendingSystemActual.getAllMaquinasVending());	
	}
	
	
	
	@Test
	public void testGetNumeroProvinciasMaquinasVendingCorrectoVacio() {
		VendingSystem vendingSystemActual = new VendingSystem(vendingCityVacio, 1);
		Map<String, Integer> hashEsperado = new HashMap();	
		hashEsperado.put(valladolid, vendingCityVacio.getMachineList().size());
		
		assertNotNull(vendingSystemActual);
		assertNotNull(vendingCityVacio);
		assertNotNull(hashEsperado);
		
		assertEquals(hashEsperado, vendingSystemActual.getNumeroProvinciasMaquinasVending());	
	}
	
	
	
	
	@Test
	public void testGetNumeroProvinciasMaquinasVendingCorrectoLleno1() {
		VendingSystem vendingSystemActual = new VendingSystem(vendingCityLleno1, 1);
		Map<String, Integer> hashEsperado = new HashMap();	
		hashEsperado.put(barcelona, vendingCityLleno1.getMachineList().size());
		
		assertNotNull(vendingSystemActual);
		assertNotNull(vendingCityLleno1);
		assertNotNull(hashEsperado);
		
		assertEquals(hashEsperado, vendingSystemActual.getNumeroProvinciasMaquinasVending());	
	}
	
	@Test
	public void testGetNumeroProvinciasMaquinasVendingCorrectoLleno2() {
		VendingSystem vendingSystemActual = new VendingSystem(vendingCityLleno2, 1);
		Map<String, Integer> hashEsperado = new HashMap();	
		hashEsperado.put(madrid, vendingCityLleno2.getMachineList().size());
		
		assertNotNull(vendingSystemActual);
		assertNotNull(vendingCityLleno2);
		assertNotNull(hashEsperado);
		
		assertEquals(hashEsperado, vendingSystemActual.getNumeroProvinciasMaquinasVending());	
	}
	
	
	@Test
	public void testGetNumeroProvinciasMaquinasVendingCorrectoLlenoVarios1() {
		VendingCity vendingCityActual = new VendingCity(santander);
		vendingCityActual.addVendingMachineCopy(0, vendingMachine1);
		vendingCityActual.addVendingMachineCopy(1, vendingMachine1);
		vendingCityActual.addVendingMachineCopy(2, vendingMachine2);
		vendingCityActual.addVendingMachineCopy(3, vendingMachine2);
		vendingCityActual.addVendingMachineCopy(4, vendingMachine1);
		
		VendingSystem vendingSystemActual = new VendingSystem(vendingCityActual, 1);
		vendingSystemActual.appendSede(2, vendingCityVacio);
		vendingSystemActual.appendSede(3, vendingCityLleno1);
		vendingSystemActual.appendSede(4, vendingCityLleno2);
		
		Map<String, Integer> hashEsperado = new HashMap();	
		hashEsperado.put(santander, vendingCityActual.getMachineList().size());
		hashEsperado.put(valladolid, vendingCityVacio.getMachineList().size());
		hashEsperado.put(barcelona, vendingCityLleno1.getMachineList().size());
		hashEsperado.put(madrid, vendingCityLleno2.getMachineList().size());

		assertNotNull(vendingSystemActual);
		assertNotNull(vendingCityActual);
		assertNotNull(vendingCityVacio);
		assertNotNull(vendingCityLleno1);
		assertNotNull(vendingCityLleno2);
		assertNotNull(hashEsperado);
		
		assertEquals(hashEsperado, vendingSystemActual.getNumeroProvinciasMaquinasVending());	
	}
	
	
	
	
	@Test
	public void testGetAllProvinciasCorrecto() {
		ArrayList<String> listaEsperada = new ArrayList<>();
		VendingSystem vendingSystemActual = new VendingSystem(vendingCityVacio, 1);
		
		assertNotNull(vendingSystemActual);
		assertNotNull(listaEsperada);
		
		listaEsperada.add(valladolid);
		
		assertNotSame(listaEsperada, vendingSystemActual.getAllProvincias());
		assertEquals(listaEsperada, vendingSystemActual.getAllProvincias());
		}
	
	@Test
	public void testGetAllProvinciasCorrectoVarios() {
		ArrayList<String> listaEsperada = new ArrayList<>();
		VendingSystem vendingSystemActual = new VendingSystem(vendingCityVacio, 1);
		vendingSystemActual.addSede(2, madrid);
		vendingSystemActual.addSede(3, barcelona);
		vendingSystemActual.addSede(4, santander);
		
		assertNotNull(vendingSystemActual);
		assertNotNull(listaEsperada);
		
		listaEsperada.add(valladolid);
		listaEsperada.add(madrid);
		listaEsperada.add(barcelona);
		listaEsperada.add(santander);
		
		assertNotSame(listaEsperada, vendingSystemActual.getAllProvincias());
		assertEquals(listaEsperada, vendingSystemActual.getAllProvincias());
		
	}
	
	@Test
	public void testGetAllProvinciasCorrectoNinguna() {
		ArrayList<String> listaEsperada = new ArrayList<>();
		VendingSystem vendingSystemActual = new VendingSystem();
		
		assertNotNull(vendingSystemActual);
		assertNotNull(listaEsperada);
		
		assertNotSame(listaEsperada, vendingSystemActual.getAllProvincias());
		assertEquals(listaEsperada, vendingSystemActual.getAllProvincias());
	}
	
	
	
	
	
	
	
	@Test
	public void testGetNumeroProvinciasCorrecto() {
		VendingSystem vendingSystemActual = new VendingSystem(vendingCityVacio, 1);
		
		assertNotNull(vendingSystemActual);
		
		assertEquals(1, vendingSystemActual.getNumeroProvincias());
	}
	
	@Test
	public void testGetNumeroProvinciasCorrectoVarios() {
		VendingSystem vendingSystemActual = new VendingSystem(vendingCityVacio, 1);
		vendingSystemActual.addSede(2, madrid);
		vendingSystemActual.addSede(3, barcelona);
		vendingSystemActual.addSede(4, santander);
		
		assertNotNull(vendingSystemActual);
		
		assertEquals(4, vendingSystemActual.getNumeroProvincias());
	}
	
	@Test
	public void testGetNumeroProvinciasCorrectoNinguna() {
		VendingSystem vendingSystemActual = new VendingSystem();
		
		assertNotNull(vendingSystemActual);
		
		assertEquals(0, vendingSystemActual.getNumeroProvincias());
	}

	
	
	
	
	@Test
	public void testGetOperativeMachineCorrectoNinguna() {
		VendingSystem vendingSystemActual = new VendingSystem(1, valladolid);
		ArrayList<VendingMachine> listaEsperada = new ArrayList<>();
		
		assertNotNull(vendingSystemActual);
		assertNotNull(listaEsperada);
		
		assertNotSame(listaEsperada, vendingSystemActual.getOperativeMachines(1));
		assertEquals(listaEsperada, vendingSystemActual.getOperativeMachines(1));
	}
	
	
	@Test
	public void testGetOperativeMachineCorrecto1() {
		VendingSystem vendingSystemActual = new VendingSystem(vendingCityLleno1, 1);
		
		assertNotNull(vendingSystemActual);
		assertNotNull(vendingCityLleno1);
		
		assertNotSame(vendingCityLleno1.operativeVendingMachines(), vendingSystemActual.getOperativeMachines(1));
		assertEquals(vendingCityLleno1.operativeVendingMachines(), vendingSystemActual.getOperativeMachines(1));
		
		for (int i = 0; i < vendingSystemActual.getOperativeMachines(1).size(); i++) {
			assertTrue(vendingCityLleno1.operativeVendingMachines().get(i).equals(vendingSystemActual.getOperativeMachines(1).get(i)));
		}
	}
	
	@Test
	public void testGetOperativeMachineCorrecto2() {
		VendingSystem vendingSystemActual = new VendingSystem(vendingCityLleno2, 1);
		
		assertNotNull(vendingSystemActual);
		assertNotNull(vendingCityLleno2);
		
		assertNotSame(vendingCityLleno2.operativeVendingMachines(), vendingSystemActual.getOperativeMachines(1));
		assertEquals(vendingCityLleno2.operativeVendingMachines(), vendingSystemActual.getOperativeMachines(1));
		
		for (int i = 0; i < vendingSystemActual.getOperativeMachines(1).size(); i++) {
			assertTrue(vendingCityLleno2.operativeVendingMachines().get(i).equals(vendingSystemActual.getOperativeMachines(1).get(i)));
		}
		
	}
	
	@Test
	public void testGetOperativeMachineCorrectoTodasNoOperativas() {
		VendingSystem vendingSystemActual = new VendingSystem();
		
		assertNotNull(vendingSystemActual);
		
		VendingCity vendingCityActual = new VendingCity(valladolid);
		vendingCityActual.addVendingMachine(1, false, 1, 3, 10);
		vendingCityActual.addVendingMachine(2, false, 1, 3, 10);
		vendingCityActual.addVendingMachine(3, false, 1, 3, 10);
		vendingCityActual.addVendingMachine(4, false, 1, 3, 10);
		vendingCityActual.addVendingMachine(5, false, 1, 3, 10);
		vendingCityActual.addVendingMachine(6, false, 1, 3, 10);
		vendingCityActual.addVendingMachine(7, false, 1, 3, 10);
		vendingCityActual.addVendingMachine(8, false, 1, 3, 10);
		
		assertNotNull(vendingCityActual);
		
		vendingSystemActual.appendSede(1, vendingCityActual);

		assertNotSame(vendingCityActual.operativeVendingMachines(), vendingSystemActual.getOperativeMachines(1));
		assertEquals(vendingCityActual.operativeVendingMachines(), vendingSystemActual.getOperativeMachines(1));
		
		for (int i = 0; i < vendingSystemActual.getOperativeMachines(1).size(); i++) {
			assertTrue(vendingCityActual.operativeVendingMachines().get(i).equals(vendingSystemActual.getOperativeMachines(1).get(i)));
		}
		
	}
	
	@Test
	public void testGetOperativeMachineCorrectoTodasVariadas() {
		VendingSystem vendingSystemActual = new VendingSystem();
		
		assertNotNull(vendingSystemActual);
		
		VendingCity vendingCityActual = new VendingCity(valladolid);
		vendingCityActual.addVendingMachine(1, false, 1, 3, 10);
		vendingCityActual.addVendingMachine(2, true, 1, 3, 10);
		vendingCityActual.addVendingMachine(3, true, 1, 3, 10);
		vendingCityActual.addVendingMachine(4, false, 1, 3, 10);
		vendingCityActual.addVendingMachine(5, false, 1, 3, 10);
		vendingCityActual.addVendingMachine(6, true, 1, 3, 10);
		vendingCityActual.addVendingMachine(7, false, 1, 3, 10);
		vendingCityActual.addVendingMachine(8, false, 1, 3, 10);
		
		assertNotNull(vendingCityActual);
		
		vendingSystemActual.appendSede(1, vendingCityActual);
		
		assertNotSame(vendingCityActual.operativeVendingMachines(), vendingSystemActual.getOperativeMachines(1));
		assertEquals(vendingCityActual.operativeVendingMachines(), vendingSystemActual.getOperativeMachines(1));
		
		for (int i = 0; i < vendingSystemActual.getOperativeMachines(1).size(); i++) {
			assertTrue(vendingCityActual.operativeVendingMachines().get(i).equals(vendingSystemActual.getOperativeMachines(1).get(i)));
		}
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testGetOperativeMachineNoCodigo() {
		VendingSystem vendingSystemActual = new VendingSystem(1, valladolid);
		
		vendingSystemActual.getOperativeMachines(0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testGetOperativeMachineCodigoNegativo() {
		VendingSystem vendingSystemActual = new VendingSystem(1, valladolid);
		
		vendingSystemActual.getOperativeMachines(-1);
	}

	
	
	
	
	@Test
	public void testGetNotOperativeMachineCorrectoNinguna() {
		VendingSystem vendingSystemActual = new VendingSystem(1, valladolid);
		ArrayList<VendingMachine> listaEsperada = new ArrayList<>();
		
		assertNotNull(vendingSystemActual);
		assertNotNull(listaEsperada);
		
		assertNotSame(listaEsperada, vendingSystemActual.getNotOperativeMachines(1));
		assertEquals(listaEsperada, vendingSystemActual.getNotOperativeMachines(1));
	}
	
	
	@Test
	public void testGetNotOperativeMachineCorrecto1() {
		VendingSystem vendingSystemActual = new VendingSystem(vendingCityLleno1, 1);
		
		assertNotNull(vendingSystemActual);
		assertNotNull(vendingCityLleno1);
		
		assertNotSame(vendingCityLleno1.notOperativeVendingMachines(), vendingSystemActual.getNotOperativeMachines(1));
		assertEquals(vendingCityLleno1.notOperativeVendingMachines(), vendingSystemActual.getNotOperativeMachines(1));
		
		for (int i = 0; i < vendingSystemActual.getNotOperativeMachines(1).size(); i++) {
			assertTrue(vendingCityLleno1.notOperativeVendingMachines().get(i).equals(vendingSystemActual.getNotOperativeMachines(1).get(i)));
		}
	}
	
	@Test
	public void testGetNotOperativeMachineCorrecto2() {
		VendingSystem vendingSystemActual = new VendingSystem(vendingCityLleno2, 1);
		
		assertNotNull(vendingSystemActual);
		assertNotNull(vendingCityLleno2);
		
		assertNotSame(vendingCityLleno2.notOperativeVendingMachines(), vendingSystemActual.getNotOperativeMachines(1));
		assertEquals(vendingCityLleno2.notOperativeVendingMachines(), vendingSystemActual.getNotOperativeMachines(1));
		
		for (int i = 0; i < vendingSystemActual.getNotOperativeMachines(1).size(); i++) {
			assertTrue(vendingCityLleno2.notOperativeVendingMachines().get(i).equals(vendingSystemActual.getNotOperativeMachines(1).get(i)));
		}
		
	}
	
	@Test
	public void testGetNotOperativeMachineCorrectoTodasNoOperativas() {
		VendingSystem vendingSystemActual = new VendingSystem();
		
		assertNotNull(vendingSystemActual);
		
		VendingCity vendingCityActual = new VendingCity(valladolid);
		vendingCityActual.addVendingMachine(1, false, 1, 3, 10);
		vendingCityActual.addVendingMachine(2, false, 1, 3, 10);
		vendingCityActual.addVendingMachine(3, false, 1, 3, 10);
		vendingCityActual.addVendingMachine(4, false, 1, 3, 10);
		vendingCityActual.addVendingMachine(5, false, 1, 3, 10);
		vendingCityActual.addVendingMachine(6, false, 1, 3, 10);
		vendingCityActual.addVendingMachine(7, false, 1, 3, 10);
		vendingCityActual.addVendingMachine(8, false, 1, 3, 10);
		
		assertNotNull(vendingCityActual);
		
		vendingSystemActual.appendSede(1, vendingCityActual);

		assertNotSame(vendingCityActual.notOperativeVendingMachines(), vendingSystemActual.getNotOperativeMachines(1));
		assertEquals(vendingCityActual.notOperativeVendingMachines(), vendingSystemActual.getNotOperativeMachines(1));
		
		for (int i = 0; i < vendingSystemActual.getNotOperativeMachines(1).size(); i++) {
			assertTrue(vendingCityActual.notOperativeVendingMachines().get(i).equals(vendingSystemActual.getNotOperativeMachines(1).get(i)));
		}
		
	}
	
	@Test
	public void testGetNotOperativeMachineCorrectoTodasVariadas() {
		VendingSystem vendingSystemActual = new VendingSystem();
		
		assertNotNull(vendingSystemActual);
		
		VendingCity vendingCityActual = new VendingCity(valladolid);
		vendingCityActual.addVendingMachine(1, false, 1, 3, 10);
		vendingCityActual.addVendingMachine(2, true, 1, 3, 10);
		vendingCityActual.addVendingMachine(3, true, 1, 3, 10);
		vendingCityActual.addVendingMachine(4, false, 1, 3, 10);
		vendingCityActual.addVendingMachine(5, false, 1, 3, 10);
		vendingCityActual.addVendingMachine(6, true, 1, 3, 10);
		vendingCityActual.addVendingMachine(7, false, 1, 3, 10);
		vendingCityActual.addVendingMachine(8, false, 1, 3, 10);
		
		assertNotNull(vendingCityActual);
		
		vendingSystemActual.appendSede(1, vendingCityActual);
		
		assertNotSame(vendingCityActual.notOperativeVendingMachines(), vendingSystemActual.getNotOperativeMachines(1));
		assertEquals(vendingCityActual.notOperativeVendingMachines(), vendingSystemActual.getNotOperativeMachines(1));
		
		for (int i = 0; i < vendingSystemActual.getNotOperativeMachines(1).size(); i++) {
			assertTrue(vendingCityActual.notOperativeVendingMachines().get(i).equals(vendingSystemActual.getNotOperativeMachines(1).get(i)));
		}
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testGetNotOperativeMachineNoCodigo() {
		VendingSystem vendingSystemActual = new VendingSystem(1, valladolid);
		
		vendingSystemActual.getNotOperativeMachines(0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testGetNotOperativeMachineCodigoNegativo() {
		VendingSystem vendingSystemActual = new VendingSystem(1, valladolid);
		
		vendingSystemActual.getNotOperativeMachines(-1);
	}
	
	
	
	
	
	
	// TEST HASH CODE Y EQUALS
	// Pruebas HashCode
	@Test
	public void testHashCodeSistemasIguales() {
		VendingSystem vendingSystem1 = new VendingSystem(1, valladolid);
		VendingSystem vendingSystem2 = new VendingSystem(1, valladolid);
		int codigo1 = vendingSystem1.hashCode();
		int codigo2 = vendingSystem2.hashCode();
		
		assertNotNull(vendingSystem1);
		assertNotNull(vendingSystem2);
		assertNotSame(vendingSystem1, vendingSystem2);
		
		assertEquals(codigo1, codigo2, 0);
	}
	
	@Test
	public void testHashCodeSistemasDistintosCodigos() {
		VendingSystem vendingSystem1 = new VendingSystem(1, valladolid);
		VendingSystem vendingSystem2 = new VendingSystem(2, valladolid);
		int codigo1 = vendingSystem1.hashCode();
		int codigo2 = vendingSystem2.hashCode();
		
		assertNotNull(vendingSystem1);
		assertNotNull(vendingSystem2);
		assertNotSame(vendingSystem1, vendingSystem2);
		
		assertNotEquals(codigo1, codigo2, 0);
	}
	
	@Test
	public void testHashCodeSistemasDistintosNombres() {
		VendingSystem vendingSystem1 = new VendingSystem(1, valladolid);
		VendingSystem vendingSystem2 = new VendingSystem(1, barcelona);
		int codigo1 = vendingSystem1.hashCode();
		int codigo2 = vendingSystem2.hashCode();
		
		assertNotNull(vendingSystem1);
		assertNotNull(vendingSystem2);
		assertNotSame(vendingSystem1, vendingSystem2);
		
		assertNotEquals(codigo1, codigo2, 0);
	}
	
	
	@Test
	public void testHashCodeSistemasIgualesVariasSedes() {
		VendingSystem vendingSystem1 = new VendingSystem(1, valladolid);
		vendingSystem1.addSede(2, barcelona);
		vendingSystem1.addSede(3, madrid);
		VendingSystem vendingSystem2 = new VendingSystem(1, valladolid);
		vendingSystem2.addSede(2, barcelona);
		vendingSystem2.addSede(3, madrid);
		int codigo1 = vendingSystem1.hashCode();
		int codigo2 = vendingSystem2.hashCode();
		
		assertNotNull(vendingSystem1);
		assertNotNull(vendingSystem2);
		assertNotSame(vendingSystem1, vendingSystem2);
		
		assertEquals(codigo1, codigo2, 0);
	}
	

	@Test
	public void testHashCodeSistemasDistintosVariasSedes() {
		VendingSystem vendingSystem1 = new VendingSystem(1, valladolid);
		vendingSystem1.addSede(2, barcelona);
		vendingSystem1.addSede(3, madrid);
		VendingSystem vendingSystem2 = new VendingSystem(1, valladolid);
		vendingSystem2.addSede(2, barcelona);
		int codigo1 = vendingSystem1.hashCode();
		int codigo2 = vendingSystem2.hashCode();
		
		assertNotNull(vendingSystem1);
		assertNotNull(vendingSystem2);
		assertNotSame(vendingSystem1, vendingSystem2);
		
		assertNotEquals(codigo1, codigo2, 0);
	}
	// Fin test HashCode

	
	// Pruebas Equals
	@Test
	public void testEqualsSystemObject() {
		Object obj = new Object();
		VendingSystem vendingSystemActual = new VendingSystem(0, valladolid);
		
		assertNotNull(vendingSystemActual);
		assertFalse(vendingSystemActual.equals(obj));
	}
	
	@Test
	public void testEqualsSystemNull() {
		VendingSystem vendingSystemActual = new VendingSystem(0, valladolid);
		
		assertNotNull(vendingSystemActual);
		assertFalse(vendingSystemActual.equals(null));
	}
	
	
	@Test
	public void testEqualsMismoSystem() {
		VendingSystem vendingSystemActual = new VendingSystem(0, valladolid);
		
		assertNotNull(vendingSystemActual);
		assertTrue(vendingSystemActual.equals(vendingSystemActual));
	}

	@Test
	public void testEqualsSystemIguales() {
		VendingSystem vendingSystem1 = new VendingSystem(0, valladolid);
		VendingSystem vendingSystem2 = new VendingSystem(0, valladolid);
		
		assertNotNull(vendingSystem1);
		assertNotNull(vendingSystem2);
		assertTrue(vendingSystem1.equals(vendingSystem2));
	}
	
	@Test
	public void testEqualsSystemDistintoCodigo() {
		VendingSystem vendingSystem1 = new VendingSystem(0, valladolid);
		VendingSystem vendingSystem2 = new VendingSystem(1, valladolid);
		
		assertNotNull(vendingSystem1);
		assertNotNull(vendingSystem2);
		assertFalse(vendingSystem1.equals(vendingSystem2));
	}
	
	@Test
	public void testEqualsSystemDistintoNombre() {
		VendingSystem vendingSystem1 = new VendingSystem(0, valladolid);
		VendingSystem vendingSystem2 = new VendingSystem(0, barcelona);
		
		assertNotNull(vendingSystem1);
		assertNotNull(vendingSystem2);
		assertFalse(vendingSystem1.equals(vendingSystem2));
	}
	
	@Test
	public void testEqualsSystemIgualesVariasSedes() {
		VendingSystem vendingSystem1 = new VendingSystem(1, valladolid);
		vendingSystem1.addSede(2, barcelona);
		vendingSystem1.addSede(3, madrid);
		VendingSystem vendingSystem2 = new VendingSystem(1, valladolid);
		vendingSystem2.addSede(2, barcelona);
		vendingSystem2.addSede(3, madrid);
		
		assertNotNull(vendingSystem1);
		assertNotNull(vendingSystem2);
		assertNotSame(vendingSystem1, vendingSystem2);
		
		assertTrue(vendingSystem1.equals(vendingSystem2));
	}
	

	@Test
	public void testEqualsSystemDistintasVariasSedes() {
		VendingSystem vendingSystem1 = new VendingSystem(1, valladolid);
		vendingSystem1.addSede(2, barcelona);
		vendingSystem1.addSede(3, madrid);
		VendingSystem vendingSystem2 = new VendingSystem(1, valladolid);
		vendingSystem2.addSede(2, barcelona);
		
		assertNotNull(vendingSystem1);
		assertNotNull(vendingSystem2);
		assertNotSame(vendingSystem1, vendingSystem2);
		
		assertFalse(vendingSystem1.equals(vendingSystem2));
	}
	// Fin pruebas equals	
}