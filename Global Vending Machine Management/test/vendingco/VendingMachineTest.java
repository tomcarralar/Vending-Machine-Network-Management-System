package vendingco;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import fabricante.externo.tarjetas.TarjetaMonedero;

/**
 * Proporciona todas las pruebas de la clase VendingMachine
 * 
 * @author tomcarr
 * @author javiram
 */
public class VendingMachineTest {	
	Product galletas; 
	VendingMachineLine linea;
	VendingMachine vendingMachine1;
	
	Product productoCocaCola; 
	Product productoPatatas; 
	Pack pack;
	ArrayList <Product> listaPack;
	
	@Before
	public void init() {
		vendingMachine1 = new VendingMachine(0,true, 15, 8, 8);
		galletas = new Product("galletas", 2.3, "1-1-2025", "112345678902"); 
		linea = new VendingMachineLine(0, galletas, 8);
		
		productoCocaCola = new Product("CocaCola", 1, "1-1-2029", "112345678902");
		productoPatatas = new Product("PatatasFritas", 2.5, "1-1-2023", "999999999993");
		listaPack = new ArrayList<>(); 
		listaPack.add(productoCocaCola); 
		listaPack.add(productoPatatas);
		pack = new Pack("PackPrueba1", listaPack, 0); 		
	}

	// Comienzo pruebas VendingMachine()
	@Test
	public void testVendingMachineEntadasCorrectas() {
		VendingMachine vendingMachine2 = new VendingMachine(2,false, 15, 8, 10);
		assertNotNull(vendingMachine2);
		
		assertEquals(2, vendingMachine2.getId());
		assertFalse(vendingMachine2.getState());
		assertEquals(15, vendingMachine2.getFilas());
		assertEquals(8, vendingMachine2.getColumnas());
		assertEquals(10, vendingMachine2.getLongitudLineas());
	}
	
	@Test (expected = IllegalArgumentException.class) 
	public void testVendingMachineEntradaFilasIgual0() {
		new VendingMachine(2,false, 0, 8, 8);
	}
	

	@Test (expected = IllegalArgumentException.class) 
	public void testVendingMachineEntradaFilasNegativo() {
		new VendingMachine(2,false, -15, 8, 8);
	}
	
	@Test (expected = IllegalArgumentException.class) 
	public void testVendingMachineEntradaColumnasIgual0() {
		new VendingMachine(2,false, 15, 0, 8);
	}
	
	@Test (expected = IllegalArgumentException.class) 
	public void testVendingMachineEntradaColumnasNegativo() {
		new VendingMachine(2,false, 15, -8, 8);
	}
	
	@Test (expected = IllegalArgumentException.class) 
	public void testVendingMachineEntradaLongitudLineasIgual0() {
		new VendingMachine(2,false, 15, 8, 0);
	}
	
	@Test (expected = IllegalArgumentException.class) 
	public void testVendingMachineEntradaLongitudLineasNegativo() {
		 new VendingMachine(2,false, 15, 8, -8);
	}
		// Parte correspondiente a la copia de máquina
	@Test 
	public void testCopiaVendingMachineEntradaCorrecta() {
		assertNotNull(vendingMachine1); 
		VendingMachine cp_vendingMachine1 = new VendingMachine(1,vendingMachine1); 
		
		assertNotNull(cp_vendingMachine1);
		assertNotEquals(cp_vendingMachine1.getId(), vendingMachine1.getId());
	}
	
	@Test (expected = NullPointerException.class) 
	public void testCopiaVendingMachineNullVendingMachineCorrecta() {
		new VendingMachine(2, null);
	}
	// Fin pruebas VendingMachine()
	
	// Comienzo pruebas getId()
	
	@Test 
	public void testGetId() { 
		assertEquals(vendingMachine1.getId(), 0);
	}
	// Fin pruebas getId()

	
	// Comienzo pruebas setId()
	@Test 
	public void testSetId() { 
		vendingMachine1.setId(6);
		assertEquals(vendingMachine1.getId(), 6);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetIdNegativo() { 
		vendingMachine1.setId(-66);
	}
	// Fin pruebas setId()

	
	// Comienzo pruebas getLongitudLineas()
	@Test 
	public void testGetLongitudLineas() {
		assertEquals(8, vendingMachine1.getLongitudLineas());
	}
	// Fin pruebas getLongitudLineas()
	
	
	// Comienzo pruebas getState()
	@Test 
	public void testGetState() {
		assertTrue(vendingMachine1.getState());
		VendingMachine vendingMachine2 = new VendingMachine(2,false, 15, 8, 8);
		assertFalse(vendingMachine2.getState());
	}
	// Fin pruebas getState()
	
	
	// Comienzo pruebas getState()
	@Test 
	public void testSetStateFalse() {
		vendingMachine1.setState(false); 
		assertFalse(vendingMachine1.getState());
	}
	
	@Test 
	public void testSetStateTrue() {
		vendingMachine1.setState(true); 
		assertTrue(vendingMachine1.getState());
	}
	
	// Fin pruebas getState()
		
		
	// Comienzo pruebas getColumnas()
	@Test 
	public void testGetColumnas() {
		assertEquals(8, vendingMachine1.getColumnas());
	}
	// Fin pruebas getColumnas()
	
	// Comienzo pruebas getLineas()
	@Test 
	public void testGetLineas() {
		int dim = 8*15; // la maquina expendedora tienen 8 columnas, 15 filas y por tanto 120 lineas. 
		VendingMachineLine[] VendingMachineLine = new VendingMachineLine[dim]; 
		VendingMachineLine[0] = linea; 
		vendingMachine1.setLineas(VendingMachineLine);
		assertArrayEquals(VendingMachineLine, vendingMachine1.getLineas());
	}
	
	// Fin pruebas getlineas()
	
	
	// Comienzo pruebas getidLines()
	@Test 
	public void testGetIds() {
		VendingMachineLine[] VendingMachineLine  = new VendingMachineLine[120]; 
		int [] lineas_id_camb = new int [120]; 
		for (int i = 0; i < 120; i++) {
			lineas_id_camb[i] = i; 
			linea = new VendingMachineLine(i, galletas, 4);
			VendingMachineLine[i] = linea; 
		}
		vendingMachine1.setLineas(VendingMachineLine);
		for (int i = 0; i < 120; i++) {
			assertEquals(lineas_id_camb[i], vendingMachine1.getidLineas()[i]);
		}
		
	}
	
	// Fin pruebas getidLines()
	
	// Comienzo de pruebas getLineasVacias()
	@Test
	public void testGetLineasVaciasTodasVacias() {
		VendingMachineLine[] lineas  = new VendingMachineLine[120]; 
		for (int i = 0; i < 120; i++) {
			lineas[i] = linea; 
		}
		vendingMachine1.setLineas(lineas);
		
		for (int i = 0; i < 120; i++) {
			assertEquals(lineas[i], vendingMachine1.getLineasVacias().get(i)); 
		}
	}
	@Test 
	
	public void testGetLineasVaciasNinguna() {
		VendingMachineLine[] lineas  = new VendingMachineLine[120]; 
		linea = new VendingMachineLine(0, galletas, 8, 8);
		for (int i = 0; i < 120; i++) {
			lineas[i] = linea; 
		}
		
		vendingMachine1.setLineas(lineas);
		
		assertEquals(0, vendingMachine1.getLineasVacias().size()); 

	}
	@Test
	public void testGetLineasVaciasSoloAlgunas() {
		VendingMachineLine linea2 = new VendingMachineLine(0, galletas, 8, 8);
		VendingMachineLine[] lineas  = new VendingMachineLine[120]; 
		VendingMachineLine[] todas_lineas = new VendingMachineLine[120]; 
		int j = 0; 
		for (int i = 0; i < 120; i++) {
			
			if (i%2==0) {
				lineas[j] = linea; 
				j++; 
				todas_lineas[i] = linea; 
			}else todas_lineas[i] = linea2;
		}
		
		vendingMachine1.setLineas(todas_lineas);
		
		for (int i = 0; i < 60; i++) {
			assertEquals(lineas[i], vendingMachine1.getLineasVacias().get(i));
		}
	}
	
	// Fin pruebas getLineasVacias()
		
	
	// Comienzo de pruebas getLineasVacias()
	@Test
	public void testGetLineasVaciasIdTodasVacias() {
		ArrayList<Integer> ids = new ArrayList<>() ; 
		VendingMachineLine[] lineas  = new VendingMachineLine[120]; 
		for (int i = 0; i < 120; i++) {
			lineas[i] = linea; 
			ids.add(lineas[i].getIdlinea()); 
		}
		vendingMachine1.setLineas(lineas);
		
		assertEquals(ids, vendingMachine1.getLineasVaciasid()); 

	}
	@Test 
	
	public void testGetLineasVaciasIdNinguna() {
		VendingMachineLine[] lineas  = new VendingMachineLine[120]; 
		linea = new VendingMachineLine(0, galletas, 8, 8);
		for (int i = 0; i < 120; i++) {
			lineas[i] = linea; 
		}
		
		vendingMachine1.setLineas(lineas);
		
		assertEquals(0, vendingMachine1.getLineasVaciasid().size()); 

	}
	@Test
	public void testGetLineasVaciasIdSoloAlgunas() {
		VendingMachineLine linea2 = new VendingMachineLine(0, galletas, 8, 8);
		ArrayList<Integer> ids = new ArrayList<>();
		VendingMachineLine[] todas_lineas = new VendingMachineLine[120]; 
		for (int i = 0; i < 120; i++) {
			
			if (i%2==0) {
				ids.add(linea.getIdlinea()); 
				todas_lineas[i] = linea; 
			}else todas_lineas[i] = linea2;
		}
		
		vendingMachine1.setLineas(todas_lineas);
		assertEquals(ids, vendingMachine1.getLineasVaciasid());
	}
	
	// Fin pruebas getLineasVaciasid()	
			
	
	// Comienzo pruebas hasLineaVacias()
	@Test 
	public void testHasLineasVacias() {
		VendingMachineLine[] lineas  = new VendingMachineLine[120]; 
		for (int i = 0; i < 120; i++) {
			lineas[i] = linea; 
		}
		
		vendingMachine1.setLineas(lineas);
		assertTrue(vendingMachine1.hasLineaVacia()); 
		
		linea = new VendingMachineLine(0, galletas, 8, 8);
		for (int i = 0; i < 120; i++) {
			lineas[i] = linea; 
		}
		
		vendingMachine1.setLineas(lineas);
		
		assertFalse(vendingMachine1.hasLineaVacia());
	}
	// Fin pruebas hasLineaVacias()
	
	
	// Comienzo pruebas createLineas()
	 // Ya están comprobadas, puesto que solo se puede llamar a este método al crear una máquina. 
	// Fin pruebas createLineas()
	
	// Comienzo pruebas setLineas()
	@Test
	public void testSetLineasEntradaCorrecta() {
		VendingMachineLine[] lineas  = new VendingMachineLine[120]; 
		for (int i = 0; i < 120; i++) {
			lineas[i] = linea; 
		}
		vendingMachine1.setLineas(lineas);
		assertArrayEquals(lineas, vendingMachine1.getLineas());
	}
	
	@Test
	public void testSetLineasEntradaCorrectaMenosLineas() {
		VendingMachineLine[] lineas  = new VendingMachineLine[100]; 
		VendingMachineLine[] lineas_esperadas  = new VendingMachineLine[120];
		for (int i = 0; i < 120; i++) {
			if (i<100) {
				lineas[i] = linea;
				lineas_esperadas[i] = linea;
			}
			else{lineas_esperadas[i] = null; }
		}
		vendingMachine1.setLineas(lineas);
		assertArrayEquals(lineas_esperadas, vendingMachine1.getLineas());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetLineasEntradaIncorrecta() {
		VendingMachineLine[] lineas  = new VendingMachineLine[130]; 
		for (int i = 0; i < 130; i++) {
			lineas[i] = linea; 
		}
		vendingMachine1.setLineas(lineas);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetLineasEntradaNull() {
		
		vendingMachine1.setLineas(null);
	}
	// Fin preubas setLineas()
	
	// Comienzo pruebas addLineaProducto()
	@Test 
	public void testAddLineaProductoEntradaCorrecta() {
		vendingMachine1.addLinea(1, galletas, 8);
		assertEquals(1, vendingMachine1.getLineas()[0].getIdlinea());
		assertEquals(galletas, vendingMachine1.getLineas()[0].getVendible());
	}
	
	@Test (expected = IllegalArgumentException.class )
	public void testAddLineaProductoIncorrecta() {
		VendingMachineLine[] lineas  = new VendingMachineLine[1]; 
		lineas[0] = linea; 
		vendingMachine1.setLineas(lineas);
		vendingMachine1.addLinea(0, galletas, 8);
	}
	
	@Test (expected = IllegalStateException.class)
	public void testAddLineaProducto() {
		VendingMachineLine[] lineas  = new VendingMachineLine[1]; 
		lineas[0] = linea; 
		vendingMachine1.setLineas(lineas);
		for (int i = 1; i < 120; i++) {
			vendingMachine1.addLinea(i, galletas, 8);
		}
		vendingMachine1.addLinea(120, galletas, 8);
		
	}
	
	@Test 
	public void testAddLineaProductoVaciaEntradaCorrecta() {
		vendingMachine1.addLineaVacia(0, galletas);
		vendingMachine1.addLineaVacia(1, galletas);
		assertEquals(0, vendingMachine1.getLineas()[0].getIdlinea());
		assertEquals(1, vendingMachine1.getLineas()[1].getIdlinea());
		assertEquals(galletas, vendingMachine1.getLineas()[0].getVendible());
		assertEquals(galletas, vendingMachine1.getLineas()[1].getVendible());
	}
	
	// Fin pruebas addLineaProducto()
	
	// Comienzo pruebas addLineaPack()
		@Test 
		public void testAddLineaPackEntradaCorrecta() {
			assertNotNull(pack);
			vendingMachine1.addLinea(1, pack, 8);
			assertEquals(1, vendingMachine1.getLineas()[0].getIdlinea());
			assertEquals(pack, vendingMachine1.getLineas()[0].getVendible());
		}
		
		@Test (expected = IllegalArgumentException.class )
		public void testAddLineaPackIncorrecta() {
			assertNotNull(pack);
			VendingMachineLine[] lineas  = new VendingMachineLine[1]; 
			lineas[0] = linea; 
			vendingMachine1.setLineas(lineas);
			vendingMachine1.addLinea(0, pack, 8);
		}
		
		@Test (expected = IllegalStateException.class)
		public void testAddLineaPack() {
			assertNotNull(pack);
			VendingMachineLine[] lineas  = new VendingMachineLine[1]; 
			lineas[0] = linea; 
			vendingMachine1.setLineas(lineas);
			for (int i = 1; i < 120; i++) {
				vendingMachine1.addLinea(i, pack, 8);
			}
			vendingMachine1.addLinea(120, pack, 8);
			
		}
		
		@Test 
		public void testAddLineaPackVaciaEntradaCorrecta() {
			assertNotNull(pack);
			vendingMachine1.addLineaVacia(0, pack);
			vendingMachine1.addLineaVacia(1, pack);
			
			assertEquals(0, vendingMachine1.getLineas()[0].getIdlinea());
			assertEquals(1, vendingMachine1.getLineas()[1].getIdlinea());
			
			assertEquals(pack, vendingMachine1.getLineas()[0].getVendible());
			assertEquals(pack, vendingMachine1.getLineas()[1].getVendible());
		}
		
		// Fin pruebas addLineaPack()
	
	// Comienzo pruebas deleteLinea()
	@Test 
	public void testDeleteLineaConId100() {
		VendingMachineLine[] lineas  = new VendingMachineLine[120];
		VendingMachineLine[] lineas_esperada  = new VendingMachineLine[119]; 
		for (int i = 0; i < 120; i++) {
			lineas[i] = new VendingMachineLine(i, galletas, 8);
			if (i < 100) lineas_esperada[i] = new VendingMachineLine(i, galletas, 8);
			else {
				if (i < 119)lineas_esperada[i] = new VendingMachineLine(i+1, galletas, 8); 
			}
		}
		vendingMachine1.setLineas(lineas);
		
		vendingMachine1.deleteLinea(100);
		for (int i = 0; i < 119; i++) {
			assertEquals(lineas_esperada[i].getIdlinea(), vendingMachine1.getLineas()[i].getIdlinea());
		}
		
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testDeleteLineaConIdInexistente() {
		VendingMachineLine[] lineas  = new VendingMachineLine[120];
		VendingMachineLine[] lineas_esperada  = new VendingMachineLine[119]; 
		for (int i = 0; i < 120; i++) {
			lineas[i] = new VendingMachineLine(i, galletas, 8);
			if (i < 100) lineas_esperada[i] = new VendingMachineLine(i, galletas, 8);
			else {
				if (i < 119)lineas_esperada[i] = new VendingMachineLine(i+1, galletas, 8); 
			}
		}
		vendingMachine1.setLineas(lineas);
		
		vendingMachine1.deleteLinea(130);
			
	}
	
	// Fin pruebas deleteLinea
	
	// Comienza pruebas rellenarLinea()
	
	@Test
	public void testRellenarLineasEntradaCorrecta() {
		vendingMachine1.addLinea(2, galletas, 0);
		vendingMachine1.addLinea(0, galletas, 0);
		
		vendingMachine1.rellenarLinea(0, 8);
		assertEquals(vendingMachine1.getLineas()[1].getQuantity(), 8);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testRellenarLineasCantidadIgual0() {
		vendingMachine1.addLinea(0, galletas, 0);
		
		vendingMachine1.rellenarLinea(0, 0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testRellenarLineasIdInexistente() {
		vendingMachine1.addLinea(0, galletas, 0);
		
		vendingMachine1.rellenarLinea(130, 0);
	}
	
	// Fin pruebas rellenarLinea()
	
	
	// Comienzo pruebas vaciarLinea()
	
	@Test
	public void testVaciarLineasEntradaCorrecta() {
		for (int i = 0; i < 120; i++) {
			vendingMachine1.addLinea(i, galletas, 8);
		}
		
		vendingMachine1.vaciarLinea(1, 4);
		assertEquals(4, vendingMachine1.getLineas()[1].getQuantity());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testVaciarLineasCantidadSuperiorMaxima() {
		for (int i = 0; i < 120; i++) {
			vendingMachine1.addLinea(i, galletas, 8);
		}
		
		vendingMachine1.vaciarLinea(1, 10);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testVaciarLineasIdInexistente() {
		for (int i = 0; i < 120; i++) {
			vendingMachine1.addLinea(i, galletas, 8);
		}
		
		vendingMachine1.vaciarLinea(130, 4);
	}
	
	@Test
	public void testVaciarLineasCantidadIgual0() {
		for (int i = 0; i < 120; i++) {
			vendingMachine1.addLinea(i, galletas, 8);
		}
		
		vendingMachine1.vaciarLinea(1, 0);
		assertEquals(8, vendingMachine1.getLineas()[1].getQuantity());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testVaciarLineasCantidadNegativa() {
		for (int i = 0; i < 120; i++) {
			vendingMachine1.addLinea(i, galletas, 8);
		}
		
		vendingMachine1.vaciarLinea(0, -4);
	}
	
	@Test
	public void testVaciarLineaCompletamente() {
		for (int i = 0; i < 120; i++) {
			vendingMachine1.addLinea(i, galletas, 8);
		}
		
		vendingMachine1.vaciarCompletamenteLinea(0); 
		assertEquals(0, vendingMachine1.getLineas()[0].getQuantity());
	}
	
	// Fin pruebas vaciarLinea()
	
	
	// Comienzo pruebas findLinea()
	
	@Test
	public void testFindLineaIdCorrecto() {
		for (int i = 0; i < 120; i++) {
			vendingMachine1.addLinea(i, galletas, 8);
		}
		VendingMachineLine linea_buscada = new VendingMachineLine(54, galletas, 8);
		assertEquals(vendingMachine1.findLineCopy(54).getIdlinea(), linea_buscada.getIdlinea());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testFindLineaIdInexistente() {
		for (int i = 0; i < 120; i++) {
			vendingMachine1.addLinea(i, galletas, 8);
		}
		vendingMachine1.findLineCopy(130); 
	}
	// Fin pruebas findLinea()
	
	// Comienzo pruebas compar()
	@Test (expected = IllegalStateException.class)
	public void testComprarStateFalse() {
		TarjetaMonedero tarjeta = new TarjetaMonedero("A156Bv09_1zXo894", 10.0);
		
		vendingMachine1 = new VendingMachine(0,false, 15, 8, 8);
		vendingMachine1.addLinea(0, galletas, 8);
		vendingMachine1.comprar(0, tarjeta, "6Z1y00Nm31aA-571" );
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testComprarLineaVacia() {
		TarjetaMonedero tarjeta = new TarjetaMonedero("A156Bv09_1zXo894", 10.0);
		for (int i = 0; i < 120; i++) {
			vendingMachine1.addLinea(i, galletas, 0);
		}
		
		vendingMachine1.comprar(0, tarjeta, "6Z1y00Nm31aA-571" );
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testComprarIdleniaInexistente() {
		TarjetaMonedero tarjeta = new TarjetaMonedero("A156Bv09_1zXo894", 10.0);
		for (int i = 0; i < 120; i++) {
			vendingMachine1.addLinea(i, galletas, 8);
		}
		vendingMachine1.comprar(130, tarjeta, "6Z1y00Nm31aA-571" );
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testComprarIdleniaSaldoInsuficiente() {
		TarjetaMonedero tarjeta = new TarjetaMonedero("A156Bv09_1zXo894", 2.0);
		for (int i = 0; i < 120; i++) {
			vendingMachine1.addLinea(i, galletas, 8);
		}
		vendingMachine1.comprar(100, tarjeta, "6Z1y00Nm31aA-571" );
	}
	
	@Test
	public void testComprarEntradaCorrecta() {
		TarjetaMonedero tarjeta = new TarjetaMonedero("A156Bv09_1zXo894", 10.0);
		for (int i = 0; i < 120; i++) {
			vendingMachine1.addLinea(i, galletas, 8);
		}
		vendingMachine1.comprar(100, tarjeta, "6Z1y00Nm31aA-571" );
		assertEquals(7, vendingMachine1.getLineas()[100].getQuantity());
		assertEquals(7.7, tarjeta.getSaldoActual(), 0.1); 
	}
	
	// Fin pruebas comprar()
	
	
	// Comienzo pruebas opcionesCompra()
	
	@Test
	public void testOpcionesComprar() {
		int [] idsLineas_esperados = new int[11]; 
		TarjetaMonedero tarjeta = new TarjetaMonedero("A156Bv09_1zXo894", 10.0);
		for (int i = 0; i < 120; i++) {
			Product producto = new Product("galletas", i, "1-1-2025", "112345678902");
			vendingMachine1.addLinea(i, producto, 8);
			if (i <= 10) {
				idsLineas_esperados[i] = i; 
			}
		}
		for (int i = 0; i < 10; i++) {
			int valor_obtenido = vendingMachine1.opcionesCompra(tarjeta).get(i); 
			assertEquals(idsLineas_esperados[i],valor_obtenido );
		}
	}
	
	// Fin pruebas opcionesCompra()
	
	// Comienzo pruebas getIdPack()
	
	@Test
	public void getIdPackCorrecto() {
		VendingMachine vendingMachine1 = new VendingMachine(0, true, 10, 10, 10);
		vendingMachine1.addLinea(1, pack, 8);
		
		assertNotNull(pack);
		assertNotNull(vendingMachine1);
		
		assertEquals(0, vendingMachine1.getId());
		assertEquals(1, vendingMachine1.getLineas()[0].getIdlinea());
		assertEquals(0, vendingMachine1.getIdPack(pack));
	}
	
	@Test
	public void getVariosIdPackCorrecto() {
		VendingMachine vendingMachine1 = new VendingMachine(0, true, 10, 10, 10);
		vendingMachine1.addLinea(1, pack, 8);
		Pack pack2 = new Pack("PackPrueba2", listaPack, 0); 
		vendingMachine1.addLinea(200, pack2, 8);
		
		assertNotNull(pack);
		assertNotNull(pack2);
		assertNotNull(vendingMachine1);
		
		assertEquals(0, vendingMachine1.getId());
		assertEquals(1, vendingMachine1.getLineas()[0].getIdlinea());
		assertEquals(200, vendingMachine1.getLineas()[1].getIdlinea());
		assertEquals(0, vendingMachine1.getIdPack(pack));
		assertEquals(1, vendingMachine1.getIdPack(pack2));
	}
	
	@Test (expected = NullPointerException.class)
	public void getIdPackIncorrectoNull() {
		vendingMachine1.getIdPack(null);
	}
	
	// Fin pruebas getIdPack()
	
	
	// Pruebas HashCode
	
		@Test
		public void testHashCodeMaquinasIguales() {
			VendingMachine vendingMachineActual1 = new VendingMachine(1, true, 10, 15, 20);
			VendingMachine vendingMachineActual2 = new VendingMachine(1, true, 10, 15, 20);
			int codigo1 = vendingMachineActual1.hashCode();
			int codigo2 = vendingMachineActual2.hashCode();
			
			assertNotNull(vendingMachineActual1);
			assertNotNull(vendingMachineActual2);
			assertNotSame(vendingMachineActual1, vendingMachineActual2);
			
			assertEquals(codigo1, codigo2, 0);
		}
		
		@Test
		public void testHashCodeMaquinasDistintosId() {
			VendingMachine vendingMachineActual1 = new VendingMachine(1, true, 10, 15, 20);
			VendingMachine vendingMachineActual2 = new VendingMachine(2, true, 10, 15, 20);
			int codigo1 = vendingMachineActual1.hashCode();
			int codigo2 = vendingMachineActual2.hashCode();
			
			assertNotNull(vendingMachineActual1);
			assertNotNull(vendingMachineActual2);
			assertNotSame(vendingMachineActual1, vendingMachineActual2);
			
			assertNotEquals(codigo1, codigo2, 0);
		}
		
		@Test
		public void testHashCodeMaquinasIgualesAunqueEstadosDistintos() {
			VendingMachine vendingMachineActual1 = new VendingMachine(1, true, 10, 15, 20);
			VendingMachine vendingMachineActual2 = new VendingMachine(1, false, 10, 15, 20);
			int codigo1 = vendingMachineActual1.hashCode();
			int codigo2 = vendingMachineActual2.hashCode();
			
			assertNotNull(vendingMachineActual1);
			assertNotNull(vendingMachineActual2);
			assertNotSame(vendingMachineActual1, vendingMachineActual2);
			
			assertEquals(codigo1, codigo2, 0);
		}
		
		@Test
		public void testHashCodeMaquinasDistintosFilas() {
			VendingMachine vendingMachineActual1 = new VendingMachine(1, true, 10, 15, 20);
			VendingMachine vendingMachineActual2 = new VendingMachine(1, true, 12, 15, 20);
			int codigo1 = vendingMachineActual1.hashCode();
			int codigo2 = vendingMachineActual2.hashCode();
			
			assertNotNull(vendingMachineActual1);
			assertNotNull(vendingMachineActual2);
			assertNotSame(vendingMachineActual1, vendingMachineActual2);
			
			assertNotEquals(codigo1, codigo2, 0);
		}
		
		@Test
		public void testHashCodeMaquinasDistintosColumnas() {
			VendingMachine vendingMachineActual1 = new VendingMachine(1, true, 10, 15, 20);
			VendingMachine vendingMachineActual2 = new VendingMachine(1, true, 10, 18, 20);
			int codigo1 = vendingMachineActual1.hashCode();
			int codigo2 = vendingMachineActual2.hashCode();
			
			assertNotNull(vendingMachineActual1);
			assertNotNull(vendingMachineActual2);
			assertNotSame(vendingMachineActual1, vendingMachineActual2);
			
			assertNotEquals(codigo1, codigo2, 0);
		}
		
		
		
		@Test
		public void testHashCodeMaquinasDistintasProfundidades() {
			VendingMachine vendingMachineActual1 = new VendingMachine(1, true, 10, 15, 20);
			VendingMachine vendingMachineActual2 = new VendingMachine(1, true, 10, 15, 18);
			int codigo1 = vendingMachineActual1.hashCode();
			int codigo2 = vendingMachineActual2.hashCode();
			
			assertNotNull(vendingMachineActual1);
			assertNotNull(vendingMachineActual2);
			assertNotSame(vendingMachineActual1, vendingMachineActual2);
			
			assertNotEquals(codigo1, codigo2, 0);
		}
		// Fin test HashCode

		
		// Pruebas Equals
		@Test
		public void testEqualsMaquinasObject() {
			Object obj = new Object();
			VendingMachine vendingMachineActual1 = new VendingMachine(1, true, 10, 15, 20);
			
			assertNotNull(vendingMachineActual1);
			assertFalse(vendingMachineActual1.equals(obj));
		}
		
		@Test
		public void testEqualsMaquinasNull() {
			VendingMachine vendingMachineActual1 = new VendingMachine(1, true, 10, 15, 20);
			
			assertNotNull(vendingMachineActual1);
			assertFalse(vendingMachineActual1.equals(null));
		}
		

		@Test
		public void testEqualsMismaMaquinas() {
			VendingMachine vendingMachineActual1 = new VendingMachine(1, true, 10, 15, 20);
			
			assertNotNull(vendingMachineActual1);
			assertTrue(vendingMachineActual1.equals(vendingMachineActual1));
		}
		

		
		@Test
		public void testEqualsMaquinasIguales() {
			VendingMachine vendingMachineActual1 = new VendingMachine(1, true, 10, 15, 20);
			VendingMachine vendingMachineActual2 = new VendingMachine(1, true, 10, 15, 20);
			
			assertNotNull(vendingMachineActual1);
			assertTrue(vendingMachineActual1.equals(vendingMachineActual2));
		}
		
		@Test
		public void testEqualsMaquinasDistintoId() {
			VendingMachine vendingMachineActual1 = new VendingMachine(1, true, 10, 15, 20);
			VendingMachine vendingMachineActual2 = new VendingMachine(2, true, 10, 15, 20);
			
			assertNotNull(vendingMachineActual1);
			assertFalse(vendingMachineActual1.equals(vendingMachineActual2));
		}
		
		@Test
		public void testEqualsMaquinasDistintoState() {
			VendingMachine vendingMachineActual1 = new VendingMachine(1, true, 10, 15, 20);
			VendingMachine vendingMachineActual2 = new VendingMachine(1, false, 10, 15, 20);
			
			assertNotNull(vendingMachineActual1);
			assertFalse(vendingMachineActual1.equals(vendingMachineActual2));
		}
		
		@Test
		public void testEqualsMaquinasDistintoFilas() {
			VendingMachine vendingMachineActual1 = new VendingMachine(1, true, 10, 15, 20);
			VendingMachine vendingMachineActual2 = new VendingMachine(2, true, 12, 15, 20);
			
			assertNotNull(vendingMachineActual1);
			assertFalse(vendingMachineActual1.equals(vendingMachineActual2));
		}
		

		@Test
		public void testEqualsMaquinasDistintoColumnas() {
			VendingMachine vendingMachineActual1 = new VendingMachine(1, true, 10, 15, 20);
			VendingMachine vendingMachineActual2 = new VendingMachine(2, true, 10, 18, 20);
			
			assertNotNull(vendingMachineActual1);
			assertFalse(vendingMachineActual1.equals(vendingMachineActual2));
		}
		
		
		@Test
		public void testEqualsMaquinasDistintoProfundidad() {
			VendingMachine vendingMachineActual1 = new VendingMachine(1, true, 10, 15, 20);
			VendingMachine vendingMachineActual2 = new VendingMachine(2, true, 10, 15, 18);
			
			assertNotNull(vendingMachineActual1);
			assertFalse(vendingMachineActual1.equals(vendingMachineActual2));
		}
		// Fin pruebas equals

		
	
}
