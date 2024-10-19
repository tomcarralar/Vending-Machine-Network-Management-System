package vendingco;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import fabricante.externo.tarjetas.TarjetaMonedero;

/**
 * Proporciona todas las pruebas de la clase VendingMachine
 * 
 * @author tomcarr
 * @author javiram
 */
public class VendingMachineTest {

	
	static Product Producto; 
	static VendingMachineLine linea;
	static VendingMachine expendedora1;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		expendedora1 = new VendingMachine(0,true, 15, 8, 8);
		Producto = new Product("galletas", 2.3, "1-1-2025", "112345678902"); 
		linea = new VendingMachineLine(0, Producto, 8);
		
	}

	// Comienzo pruebas VendingMachine()
	@Test
	public void test_VendingMachine_entadas_correctas() {
		assertNotNull(expendedora1);
		VendingMachine expendedora2 = new VendingMachine(2,false, 15, 8, 8);
		assertNotNull(expendedora2);
	}
	
	@Test (expected = IllegalArgumentException.class) 
	public void test_VendingMachine_entrada_filas_igual_0() {
		@SuppressWarnings("unused")
		VendingMachine expendedora2 = new VendingMachine(2,false, 0, 8, 8);
	}
	

	@Test (expected = IllegalArgumentException.class) 
	public void test_VendingMachine_entrada_filas_negativo() {
		@SuppressWarnings("unused")
		VendingMachine expendedora2 = new VendingMachine(2,false, -15, 8, 8);
	}
	
	@Test (expected = IllegalArgumentException.class) 
	public void test_VendingMachine_entrada_columnas_igual_0() {
		@SuppressWarnings("unused")
		VendingMachine expendedora2 = new VendingMachine(2,false, 15, 0, 8);
	}
	
	@Test (expected = IllegalArgumentException.class) 
	public void test_VendingMachine_entrada_columnas_negativo() {
		@SuppressWarnings("unused")
		VendingMachine expendedora2 = new VendingMachine(2,false, 15, -8, 8);
	}
	
	@Test (expected = IllegalArgumentException.class) 
	public void test_VendingMachine_entrada_longitudLineas_igual_0() {
		@SuppressWarnings("unused")
		VendingMachine expendedora2 = new VendingMachine(2,false, 15, 8, 0);
	}
	
	@Test (expected = IllegalArgumentException.class) 
	public void test_VendingMachine_entrada_longitudLineas_negativo() {
		@SuppressWarnings("unused")
		VendingMachine expendedora2 = new VendingMachine(2,false, 15, 8, -8);
	}
		// Parte correspondiente a la copia de máquina
	@Test 
	public void test_copia_vendingMachine_entrada_correcta() {
		assertNotNull(expendedora1); 
		VendingMachine cp_expendedora1 = new VendingMachine(1,expendedora1); 
		assertNotNull(cp_expendedora1);
		assertNotEquals(cp_expendedora1.getId(), expendedora1.getId() );
	}
	
	@Test (expected = NullPointerException.class) 
	public void test_copia_vendingMachine_null_vendingmachine_correcta() {
		@SuppressWarnings("unused")
		VendingMachine expendedora = new VendingMachine(2, null);
	}
	// Fin pruebas VendingMachine()
	
	// Comienzo pruebas getId()
	
	@Test 
	public void test_getId() { 
		assertEquals(expendedora1.getId(), 0);
	}
	// Fin pruebas getId()

	
	// Comienzo pruebas setId()
	@Test 
	public void test_setId() { 
		expendedora1.setId(6);
		assertEquals(expendedora1.getId(), 6);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_setId_negativo() { 
		expendedora1.setId(-66);
	}
	// Fin pruebas setId()

	
	// Comienzo pruebas getLongitudLineas()
	@Test 
	public void test_getLongitudLineas() {
		assertEquals(8, expendedora1.getLongitudLineas());
	}
	// Fin pruebas getLongitudLineas()
	
	
	// Comienzo pruebas getState()
	@Test 
	public void test_getState() {
		assertTrue(expendedora1.getState());
		VendingMachine expendedora2 = new VendingMachine(2,false, 15, 8, 8);
		assertFalse(expendedora2.getState());
	}
	// Fin pruebas getState()
	
	
	// Comienzo pruebas getState()
	@Test 
	public void test_setState_a_false() {
		expendedora1.setState(false); 
		assertFalse(expendedora1.getState());
	}
	
	@Test 
	public void test_setState_a_true() {
		expendedora1.setState(true); 
		assertTrue(expendedora1.getState());
	}
	
	// Fin pruebas getState()
		
		
	// Comienzo pruebas getColumnas()
	@Test 
	public void test_getColumnas() {
		assertEquals(8, expendedora1.getColumnas());
	}
	// Fin pruebas getColumnas()
	
	// Comienzo pruebas getLineas()
	@Test 
	public void test_getLineas() {
		int dim = 8*15; // la maquina expendedora tienen 8 columnas, 15 filas y por tanto 120 lineas. 
		VendingMachineLine[] VendingMachineLine = new VendingMachineLine[dim]; 
		VendingMachineLine[0] = linea; 
		expendedora1.setLineas(VendingMachineLine);
		assertArrayEquals(VendingMachineLine, expendedora1.getLineas());
	}
	
	// Fin pruebas getlineas()
	
	
	// Comienzo pruebas getidLines()
	@Test 
	public void test_getids() {
		VendingMachineLine[] VendingMachineLine  = new VendingMachineLine[120]; 
		int [] lineas_id_camb = new int [120]; 
		for (int i = 0; i < 120; i++) {
			lineas_id_camb[i] = i; 
			linea = new VendingMachineLine(i, Producto, 4);
			VendingMachineLine[i] = linea; 
		}
		expendedora1.setLineas(VendingMachineLine);
		for (int i = 0; i < 120; i++) {
			assertEquals(lineas_id_camb[i], expendedora1.getidLineas()[i]);
		}
		
	}
	
	// Fin pruebas getidLines()
	
	// Comienzo de pruebas getLineasVacias()
	@Test
	public void test_getLineasVacias_todas_vacias() {
		VendingMachineLine[] lineas  = new VendingMachineLine[120]; 
		for (int i = 0; i < 120; i++) {
			lineas[i] = linea; 
		}
		expendedora1.setLineas(lineas);
		
		for (int i = 0; i < 120; i++) {
			assertEquals(lineas[i], expendedora1.getLineasVacias().get(i)); 
		}
	}
	@Test 
	
	public void test_getLineasVacias_ninguna() {
		VendingMachineLine[] lineas  = new VendingMachineLine[120]; 
		linea = new VendingMachineLine(0, Producto, 8, 8);
		for (int i = 0; i < 120; i++) {
			lineas[i] = linea; 
		}
		
		expendedora1.setLineas(lineas);
		
		assertEquals(0, expendedora1.getLineasVacias().size()); 

	}
	@Test
	public void test_getLineasVacias_solo_algunas() {
		VendingMachineLine linea2 = new VendingMachineLine(0, Producto, 8, 8);
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
		
		expendedora1.setLineas(todas_lineas);
		
		for (int i = 0; i < 60; i++) {
			assertEquals(lineas[i], expendedora1.getLineasVacias().get(i));
		}
	}
	
	// Fin pruebas getLineasVacias()
		
	
	// Comienzo de pruebas getLineasVacias()
	@Test
	public void test_getLineasVaciasid_todas_vacias() {
		expendedora1 = new VendingMachine(0,true, 15, 8, 8);
		Producto = new Product("galletas", 2.3, "1-1-2025", "112345678902"); 
		linea = new VendingMachineLine(0, Producto, 8);
		ArrayList<Integer> ids = new ArrayList<Integer>() ; 
		VendingMachineLine[] lineas  = new VendingMachineLine[120]; 
		for (int i = 0; i < 120; i++) {
			lineas[i] = linea; 
			ids.add(lineas[i].getIdlinea()); 
		}
		expendedora1.setLineas(lineas);
		
		assertEquals(ids, expendedora1.getLineasVaciasid()); 

	}
	@Test 
	
	public void test_getLineasVaciasid_ninguna() {
		VendingMachineLine[] lineas  = new VendingMachineLine[120]; 
		linea = new VendingMachineLine(0, Producto, 8, 8);
		for (int i = 0; i < 120; i++) {
			lineas[i] = linea; 
		}
		
		expendedora1.setLineas(lineas);
		
		assertEquals(0, expendedora1.getLineasVaciasid().size()); 

	}
	@Test
	public void test_getLineasVaciasid_solo_algunas() {
		expendedora1 = new VendingMachine(0,true, 15, 8, 8);
		Producto = new Product("galletas", 2.3, "1-1-2025", "112345678902"); 
		linea = new VendingMachineLine(0, Producto, 8);
		VendingMachineLine linea2 = new VendingMachineLine(0, Producto, 8, 8);
		ArrayList<Integer> ids = new ArrayList<Integer>();
		VendingMachineLine[] todas_lineas = new VendingMachineLine[120]; 
		for (int i = 0; i < 120; i++) {
			
			if (i%2==0) {
				ids.add(linea.getIdlinea()); 
				todas_lineas[i] = linea; 
			}else todas_lineas[i] = linea2;
		}
		
		expendedora1.setLineas(todas_lineas);
		assertEquals(ids, expendedora1.getLineasVaciasid());
	}
	
	// Fin pruebas getLineasVaciasid()	
			
	
	// Comienzo pruebas hasLineaVacias()
	@Test 
	public void test_hasLineasVacias() {
		VendingMachineLine[] lineas  = new VendingMachineLine[120]; 
		for (int i = 0; i < 120; i++) {
			lineas[i] = linea; 
		}
		
		expendedora1.setLineas(lineas);
		assertTrue(expendedora1.hasLineaVacia()); 
		
		linea = new VendingMachineLine(0, Producto, 8, 8);
		for (int i = 0; i < 120; i++) {
			lineas[i] = linea; 
		}
		
		expendedora1.setLineas(lineas);
		
		assertFalse(expendedora1.hasLineaVacia());
	}
	// Fin pruebas hasLineaVacias()
	
	
	// Comienzo pruebas createLineas()
	 // Ya están comprobadas, puesto que solo se puede llamar a este método al crear una máquina. 
	// Fin pruebas createLineas()
	
	// Comienzo pruebas setLineas()
	@Test
	public void test_setLineas_entrada_correcta() {
		VendingMachineLine[] lineas  = new VendingMachineLine[120]; 
		for (int i = 0; i < 120; i++) {
			lineas[i] = linea; 
		}
		expendedora1.setLineas(lineas);
		assertArrayEquals(lineas, expendedora1.getLineas());
	}
	
	@Test
	public void test_setLineas_entrada_correcta_pero_con_menos_lineas() {
		VendingMachineLine[] lineas  = new VendingMachineLine[100]; 
		VendingMachineLine[] lineas_esperadas  = new VendingMachineLine[120];
		for (int i = 0; i < 120; i++) {
			if (i<100) {
				lineas[i] = linea;
				lineas_esperadas[i] = linea;
			}
			else{lineas_esperadas[i] = null; }
		}
		expendedora1.setLineas(lineas);
		assertArrayEquals(lineas_esperadas, expendedora1.getLineas());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_setLineas_entrada_incorrecta() {
		VendingMachineLine[] lineas  = new VendingMachineLine[130]; 
		for (int i = 0; i < 130; i++) {
			lineas[i] = linea; 
		}
		expendedora1.setLineas(lineas);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_setLineas_entrada_null() {
		
		expendedora1.setLineas(null);
	}
	// Fin preubas setLineas()
	
	// Comienzo pruebas addLineaProducto()
	@Test 
	public void test_addLineaProducto_entrada_correcta() {
		expendedora1 = new VendingMachine(0,true, 15, 8, 8);
		expendedora1.addLineaProducto(1, Producto, 8);
		assertEquals(1, expendedora1.getLineas()[0].getIdlinea());
	}
	
	@Test (expected = IllegalArgumentException.class )
	public void test_addLineaProducto_() {
		expendedora1 = new VendingMachine(0,true, 15, 8, 8);
		Producto = new Product("galletas", 2.3, "1-1-2025", "11234567892"); 
		linea = new VendingMachineLine(0, Producto, 8);
		VendingMachineLine[] lineas  = new VendingMachineLine[1]; 
		lineas[0] = linea; 
		expendedora1.setLineas(lineas);
		expendedora1.addLineaProducto(0, Producto, 8);
	}
	
	@Test (expected = IllegalStateException.class)
	public void test_addLineaProducto() {
		VendingMachineLine[] lineas  = new VendingMachineLine[1]; 
		lineas[0] = linea; 
		expendedora1.setLineas(lineas);
		for (int i = 1; i < 120; i++) {
			expendedora1.addLineaProducto(i, Producto, 8);
		}
		expendedora1.addLineaProducto(120, Producto, 8);
		
	}
	
	@Test 
	public void test_addLineaProductoVacia_entrada_correcta() {
		expendedora1 = new VendingMachine(0,true, 15, 8, 8);
		expendedora1.addLineaProductoVacia(0, Producto);
		expendedora1.addLineaProductoVacia(1, Producto);
		assertEquals(0, expendedora1.getLineas()[0].getIdlinea());
		assertEquals(1, expendedora1.getLineas()[1].getIdlinea());
	}
	
	@Test 
	public void test_addLineComponentes_entrada_correcta() {
		expendedora1 = new VendingMachine(0,true, 15, 8, 8);
		expendedora1.addLineaComponentes(0, 8, "galletas", 2.3, "1-1-2025", "112345678902");
		expendedora1.addLineaProductoVacia(1, Producto);
		assertEquals(0, expendedora1.getLineas()[0].getIdlinea());
		assertEquals(1, expendedora1.getLineas()[1].getIdlinea());
	}
	// Fin pruebas addLineaProducto()
	
	
	// Comienzo pruebas deleteLinea()
	@Test 
	public void test_deleteLinea_con_id_100() {
		VendingMachineLine[] lineas  = new VendingMachineLine[120];
		VendingMachineLine[] lineas_esperada  = new VendingMachineLine[119]; 
		for (int i = 0; i < 120; i++) {
			lineas[i] = new VendingMachineLine(i, Producto, 8);
			if (i < 100) lineas_esperada[i] = new VendingMachineLine(i, Producto, 8);
			else {
				if (i < 119)lineas_esperada[i] = new VendingMachineLine(i+1, Producto, 8); 
			}
		}
		expendedora1.setLineas(lineas);
		
		expendedora1.deleteLinea(100);
		for (int i = 0; i < 119; i++) {
			assertEquals(lineas_esperada[i].getIdlinea(), expendedora1.getLineas()[i].getIdlinea());
		}
		
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_deleteLinea_con_id_inxistente() {
		VendingMachineLine[] lineas  = new VendingMachineLine[120];
		VendingMachineLine[] lineas_esperada  = new VendingMachineLine[119]; 
		for (int i = 0; i < 120; i++) {
			lineas[i] = new VendingMachineLine(i, Producto, 8);
			if (i < 100) lineas_esperada[i] = new VendingMachineLine(i, Producto, 8);
			else {
				if (i < 119)lineas_esperada[i] = new VendingMachineLine(i+1, Producto, 8); 
			}
		}
		expendedora1.setLineas(lineas);
		
		expendedora1.deleteLinea(130);
			
	}
	
	// Fin pruebas deleteLinea
	
	// Comienza pruebas rellenarLinea()
	
	@Test
	public void test_rellenarLineas_entrada_correcta() {
		expendedora1 = new VendingMachine(0,true, 15, 8, 8);
		expendedora1.addLineaProducto(2, Producto, 0);
		expendedora1.addLineaProducto(0, Producto, 0);
		
		expendedora1.rellenarLinea(0, 8);
		assertEquals(expendedora1.getLineas()[1].getQuantity(), 8);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_rellenarLineas_cantidad_igual0() {
		expendedora1 = new VendingMachine(0,true, 15, 8, 8);
		expendedora1.addLineaProducto(0, Producto, 0);
		
		expendedora1.rellenarLinea(0, 0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_rellenarLineas_id_inexistente() {
		expendedora1 = new VendingMachine(0,true, 15, 8, 8);
		expendedora1.addLineaProducto(0, Producto, 0);
		
		expendedora1.rellenarLinea(130, 0);
	}
	
	// Fin pruebas rellenarLinea()
	
	
	// Comienzo pruebas vaciarLinea()
	
	@Test
	public void test_vaciarLineas_entrada_correcta() {
		expendedora1 = new VendingMachine(0,true, 15, 8, 8);
		for (int i = 0; i < 120; i++) {
			expendedora1.addLineaProducto(i, Producto, 8);
		}
		
		expendedora1.vaciarLinea(1, 4);
		assertEquals(4, expendedora1.getLineas()[1].getQuantity());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_vaciarLineas_cantidad_superior_maxima() {
		expendedora1 = new VendingMachine(0,true, 15, 8, 8);
		for (int i = 0; i < 120; i++) {
			expendedora1.addLineaProducto(i, Producto, 8);
		}
		
		expendedora1.vaciarLinea(1, 10);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_vaciarLineas_id_inexistente() {
		expendedora1 = new VendingMachine(0,true, 15, 8, 8);
		for (int i = 0; i < 120; i++) {
			expendedora1.addLineaProducto(i, Producto, 8);
		}
		
		expendedora1.vaciarLinea(130, 4);
	}
	
	@Test
	public void test_vaciarLineas_cantidad_igual0() {
		expendedora1 = new VendingMachine(0,true, 15, 8, 8);
		for (int i = 0; i < 120; i++) {
			expendedora1.addLineaProducto(i, Producto, 8);
		}
		
		expendedora1.vaciarLinea(1, 0);
		assertEquals(8, expendedora1.getLineas()[1].getQuantity());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_vaciarLineas_cantidad_negativa() {
		expendedora1 = new VendingMachine(0,true, 15, 8, 8);
		for (int i = 0; i < 120; i++) {
			expendedora1.addLineaProducto(i, Producto, 8);
		}
		
		expendedora1.vaciarLinea(0, -4);
	}
	
	@Test
	public void test_vaciarLineaCompletamente() {
		expendedora1 = new VendingMachine(0,true, 15, 8, 8);
		for (int i = 0; i < 120; i++) {
			expendedora1.addLineaProducto(i, Producto, 8);
		}
		
		expendedora1.vaciarCompletamenteLinea(0); 
		assertEquals(0, expendedora1.getLineas()[0].getQuantity());
	}
	
	// Fin pruebas vaciarLinea()
	
	
	// Comienzo pruebas findLinea()
	
	@Test
	public void test_findLinea_id_correcto() {
		expendedora1 = new VendingMachine(0,true, 15, 8, 8);
		for (int i = 0; i < 120; i++) {
			expendedora1.addLineaProducto(i, Producto, 8);
		}
		VendingMachineLine linea_buscada = new VendingMachineLine(54, Producto, 8);
		assertEquals(expendedora1.findLineCopy(54).getIdlinea(), linea_buscada.getIdlinea());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_findLinea_id_inexistente() {
		expendedora1 = new VendingMachine(0,true, 15, 8, 8);
		for (int i = 0; i < 120; i++) {
			expendedora1.addLineaProducto(i, Producto, 8);
		}
		expendedora1.findLineCopy(130); 
	}
	// Fin pruebas findLinea()
	
	// Comienzo pruebas compar()
	@Test (expected = IllegalStateException.class)
	public void test_comprar_state_false() {
		TarjetaMonedero tarjeta = new TarjetaMonedero("A156Bv09_1zXo894", 10.0);
		
		expendedora1 = new VendingMachine(0,false, 15, 8, 8);
		expendedora1.addLineaProducto(0, Producto, 8);
		expendedora1.comprar(0, tarjeta, "6Z1y00Nm31aA-571" );
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_comprar_linea_vacia() {
		TarjetaMonedero tarjeta = new TarjetaMonedero("A156Bv09_1zXo894", 10.0);
		expendedora1 = new VendingMachine(0,true, 15, 8, 8);
		for (int i = 0; i < 120; i++) {
			expendedora1.addLineaProducto(i, Producto, 0);
		}
		
		expendedora1.comprar(0, tarjeta, "6Z1y00Nm31aA-571" );
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_comprar_idlenia_inexistente() {
		TarjetaMonedero tarjeta = new TarjetaMonedero("A156Bv09_1zXo894", 10.0);
		expendedora1 = new VendingMachine(0,true, 15, 8, 8);
		for (int i = 0; i < 120; i++) {
			expendedora1.addLineaProducto(i, Producto, 8);
		}
		expendedora1.comprar(130, tarjeta, "6Z1y00Nm31aA-571" );
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_comprar_idlenia_saldo_insuficiente() {
		TarjetaMonedero tarjeta = new TarjetaMonedero("A156Bv09_1zXo894", 2.0);
		expendedora1 = new VendingMachine(0,true, 15, 8, 8);
		for (int i = 0; i < 120; i++) {
			expendedora1.addLineaProducto(i, Producto, 8);
		}
		expendedora1.comprar(100, tarjeta, "6Z1y00Nm31aA-571" );
	}
	
	@Test
	public void test_compar_entrada_correcta() {
		TarjetaMonedero tarjeta = new TarjetaMonedero("A156Bv09_1zXo894", 10.0);
		expendedora1 = new VendingMachine(0,true, 15, 8, 8);
		for (int i = 0; i < 120; i++) {
			expendedora1.addLineaProducto(i, Producto, 8);
		}
		expendedora1.comprar(100, tarjeta, "6Z1y00Nm31aA-571" );
		assertEquals(7, expendedora1.getLineas()[100].getQuantity());
		assertEquals(7.7, tarjeta.getSaldoActual(), 0.1); 
	}
	
	// Fin pruebas comprar()
	
	
	// Comienzo pruebas opcionesCompra()
	
	@Test
	public void test_opcionesComprar() {
		int [] idsLineas_esperados = new int[11]; 
		TarjetaMonedero tarjeta = new TarjetaMonedero("A156Bv09_1zXo894", 10.0);
		expendedora1 = new VendingMachine(0,true, 15, 8, 8);
		for (int i = 0; i < 120; i++) {
			expendedora1.addLineaComponentes(i, 8, "galletas", i, "1-1-2025", "112345678902");
			if (i <= 10) {
				idsLineas_esperados[i] = i; 
			}
		}
		for (int i = 0; i < 10; i++) {
			 int valor_obtenido = expendedora1.opcionesCompra(tarjeta).get(i); 
			assertEquals(idsLineas_esperados[i],valor_obtenido );
		}
	}
	
	// Fin pruebas opcionesCompra()
	
}
