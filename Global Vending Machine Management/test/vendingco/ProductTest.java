package vendingco;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test; 
 
/**
 * Proporciona todas las pruebas de la clase Product
 * @author tomcarr
 * @author javiram
 */
public class ProductTest {
	
	Product galletas; 
	private static final String NOMBREPRODUCTO = "galletas";
	private static final String UPCLIBRE = "110000000006";
	private static final String CADUGALLETAS = "1-1-2025";
	private static final String UPCGALLETAS = "112345678902"; 
	private static final String UPCMIN = "100000000007";
	@Before
    public void init() {
		galletas = new Product(NOMBREPRODUCTO, 2.3, CADUGALLETAS, UPCGALLETAS);
    }
	
	// Comienzo pruebas de Product()
	
	@Test 
	public void testConstructorNamePrecioCaducadUpcCorrectos() {
		
		assertNotNull(galletas);

	}
	
	@SuppressWarnings("unused")
	@Test
	public void testConstructorVacio() {
		Product galletas2 = new Product();

	}
	
	@Test
	public void testConstructorNamePrecioIgual0CaduCadUpcCorrecto() {
		galletas = new Product(NOMBREPRODUCTO, 0, CADUGALLETAS, UPCGALLETAS);
		assertNotNull(galletas); 
		assertEquals(0, galletas.getPrecio(), 0.1);
	}
	
	
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorNameNull() {
		@SuppressWarnings("unused")
		Product galletasMal = new Product(null, 0, CADUGALLETAS, UPCGALLETAS);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorPrecioNegativo() {
		@SuppressWarnings("unused")
		Product galletasMal = new Product(NOMBREPRODUCTO, -2.3, CADUGALLETAS, UPCGALLETAS);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorCaduNull() {
		@SuppressWarnings("unused")
		Product galletasMal = new Product(NOMBREPRODUCTO, 2.3, null, UPCGALLETAS);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorCaduCaducada() {
		@SuppressWarnings("unused")
		Product galletasMal = new Product(NOMBREPRODUCTO, 2.3, "1-1-2000", UPCGALLETAS);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorUpcNull() {
		@SuppressWarnings("unused")
		Product galletasMal = new Product(NOMBREPRODUCTO, -2.3, CADUGALLETAS, null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorCadConChars() {
		@SuppressWarnings("unused")
		Product galletasMal = new Product(NOMBREPRODUCTO, 2.3, CADUGALLETAS, "b12345678905");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorCadConNumcharsIncorrecto() {
		@SuppressWarnings("unused")
		Product galletasMal = new Product(NOMBREPRODUCTO, 2.3, CADUGALLETAS, "32442");
	}
	
	// Fin pruebas de Product()
	
	// Comienzo pruebas generateUPC() 
	@Test
	public void testGenerateUpcEntradaCorrecta() {
		String cad = galletas.generateUPC(UPCGALLETAS); 
		
		assertEquals(UPCGALLETAS, cad);
	}
	
	@Test
	public void testGenerateUpcEntradaCorrectaLimiteSuperior() {
		String cad = galletas.generateUPC(UPCLIBRE); 
		
		assertEquals(UPCLIBRE, cad);
	}
	
	@Test
	public void testGenerateUPCEntradaCorrectaUpcMinimoValido() {
		String cad = galletas.generateUPC(UPCMIN); 
		
		assertEquals(UPCMIN, cad);
	}
	@Test
	public void testGenerateUpcEntradaCorrectaConSumaIgual9() {
		String cad = galletas.generateUPC("111010000000"); 
		
		assertEquals("111010000000", cad);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testGenerateUPCEntradaConCharsInvalidos() {
		@SuppressWarnings( "unused" )
		String cad = galletas.generateUPC("b123456789b0");
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testGenerateUpcEntradaConNumCharErroneo() {
		
		@SuppressWarnings("unused")
		String cad = galletas.generateUPC("434500345");
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testGenerateUpcConEntradaNull() {
		@SuppressWarnings("unused")
		String cad = galletas.generateUPC(null);
		
	}
	@Test (expected = IllegalArgumentException.class)
	public void testGenerateUpcConEntradaCadenaVacia() {
		@SuppressWarnings("unused")
		String cad = galletas.generateUPC("     ");
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testGenerateUpcConEntradaCadenaVaciaEspacios() {
		@SuppressWarnings("unused")
		String cad = galletas.generateUPC("");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testGenerateUpcConEntradaDoceavoDigitoIncorrecto() {
		@SuppressWarnings("unused")
		String cad = galletas.generateUPC("112345678904");
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testGenerateUpcConEntradaConNumeroInferiorPermitidos() {
		@SuppressWarnings("unused")
		String cad = galletas.generateUPC("012345678905");
		
	}
	
	// Fin pruebas generateUPC()
	
	//Comienzo pruebas copiaProducto()
	
	@Test 
	public void testCopiaProductoSobreProductoNotNull() {
		Product galletasCopia = (Product) galletas.copy(); 
		assertNotNull(galletasCopia); 
		
	}
	
	//Fin pruebas copiaProducto()
	
	
	// Comienzo de pruebas equalsUpc()
	
	@Test
	public void testEqualsUpcProductoConMimsoUpc() {
		Product cookies = new Product(NOMBREPRODUCTO, 2.3, CADUGALLETAS, UPCGALLETAS);
		assertTrue(galletas.equalsUPC(cookies));
		
	}
	@Test
	public void testEqualsUpcProductoConUpcDistinto() {
		Product cookies = new Product(NOMBREPRODUCTO, 2.3, CADUGALLETAS, UPCMIN);
		assertFalse(galletas.equalsUPC(cookies));
		
	}
	
	@SuppressWarnings("unused")
	@Test (expected = IllegalArgumentException.class)
	public void testEqualsUpcProductoConProducto2Null() {
		Product cookies = null; 
		assertNotNull(galletas);
		Boolean a = galletas.equalsUPC(cookies); 
	}
	// Fin de pruebas equalsUpc()
	
	// Comienzo pruebas getName()
	@Test
	public void testgetName () {
		assertEquals(NOMBREPRODUCTO, galletas.getName()); 
	}
	// Fin pruebas getName()
	
	
	
	// Comienzo pruebas getCadu()
	@Test
	public void testGetCadu () {
		assertEquals(CADUGALLETAS, galletas.getCadu()); 
	}
	// Fin pruebas getCadu()
	
	
	// Comienzo pruebas getPrecio()
	@Test
	public void testGetPrecio () {
		assertEquals(2.3, galletas.getPrecio(), 0.1); 
	}
	// Fin pruebas getPrecio()
	
	
	// Comienzo pruebas getUpc()
	@Test
	public void testGetUpc () {
		assertEquals( UPCGALLETAS, galletas.getUpc()); 
	}
	// Fin pruebas getUpc()
	
	// Comienzo pruebas setName()
	@Test
	public void testSetNameNombreCorrecto() {
		galletas.setName("cookies");
		assertEquals("cookies", galletas.getName());
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetNameNombreNull() {
		galletas.setName(null);
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetNameNombreVacio() {
		galletas.setName("");
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetNameNombreVacioEspacios() {
		galletas.setName("    ");
		
	}
	// Fin pruebas setName()
	
	
	// Comienzo pruebas setUpc()
	@Test
	public void testSetUpcNombreCorrecto() {
		galletas.setUpc(UPCMIN);
		assertEquals(UPCMIN, galletas.getUpc());
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetUpcCadenaUpcNull() {
		galletas.setUpc(null);
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetUpcCadenaUpcCadenaVacia() {
		galletas.setUpc("");
		
	}
	// Fin pruebas setUpc()
	
	
	// Comienzo pruebas setPrecio()
	@Test
	public void testSetPrecioPrecioValido() {
		galletas.setPrecio(10.0);
		assertEquals(10.0, galletas.getPrecio(), 0.1);
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetPrecioPrecioNegativo() {
		galletas.setPrecio(-5.0);
	}
	// Fin pruebas setPrecio()
	
	// Comienzo pruebas setCadu()
	@Test
	public void testSetCaduCaduValida () {
		galletas.setCadu("1-1-2130");
		assertEquals("1-1-2130", galletas.getCadu());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetCaduCaduCaducada() {
		galletas.setCadu("7-5-2002");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetCaduCaduNull() {
		galletas.setCadu(null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetCaduCaduVacia() {
		galletas.setCadu("");
	}
	@Test (expected = IllegalArgumentException.class)
	public void testSetCaduCaduVaciaEspacios() {
		galletas.setCadu("   ");
	}
	// Fin pruebas setCadu()
	
	// Comienzo pruebas de SetUpc()
	@Test
	public void testSetUpcUpcValida () {
		galletas.setUpc("200000000004");
		assertEquals("200000000004", galletas.getUpc());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetCaduUpcNull() {
		galletas.setUpc(null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetUpcUpcConCaracteres() {
		galletas.setUpc("1000000000d0");
	}
	@Test (expected = IllegalArgumentException.class)
	public void testSetUpcConUpcPopuestoDeTamannoIncorrecto() {
		galletas.setUpc("4345");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetUpcUpcVacio() {
		galletas.setUpc("");
	}
	@Test (expected = IllegalArgumentException.class)
	public void testSetUpcUpcVacioEspacios() {
		galletas.setUpc("  ");
	}
	// Fin pruebas de SetUpc()
	
	// Comienzo pruebas de equals()
	@Test
	public void testEqualsProductoNull() {
		Vendible cookies = null; 
		assertFalse(galletas.equals(cookies)); 
	}
	
	@Test
	public void testEqualsEnviandoProductoDistinto() {
		Vendible cookies = new Product(NOMBREPRODUCTO, 2.3, CADUGALLETAS, UPCMIN);
		assertFalse(galletas.equals(cookies));
	}
	
	@Test
	public void testEqualsEnviandoProductoIgual() {
		Product cookies = new Product(NOMBREPRODUCTO, 2.3, CADUGALLETAS, UPCGALLETAS);
		assertTrue(galletas.equals(cookies));
	}
	@Test
	public void testEqualsEnviandoMismaDireccion() {
		Product cookies = galletas; 
		assertTrue(galletas.equals(cookies));
	}
	
	@Test 
	public void testEqualsPackConProduct() {
		Vendible cookies = new Product(NOMBREPRODUCTO, 2.3, CADUGALLETAS, UPCGALLETAS); 
		Product cola = new Product("Coca-Cola", 1.5, "20-3-2023", UPCMIN);
		Product cafe = new Product("Coffe", 6.7, "20-3-2050", UPCLIBRE);
		ArrayList <Product> listaDeProductos1 = new ArrayList<>(); 
		listaDeProductos1.add((Product)cookies); 
		listaDeProductos1.add(cola); 
		listaDeProductos1.add(cafe); 
		Pack pack1 = new Pack("pack1", listaDeProductos1, 1);
		assertFalse(cookies.equals(pack1)); 
	}
	// Fin pruebas de equals()
	
	// Comienzo pruebas de equals()
		
	@Test
	public void testHashCodeEnviandoProductoDistinto() {
		Product cookies = new Product(NOMBREPRODUCTO, 2.3, CADUGALLETAS, UPCMIN);
		int hash1 = galletas.hashCode(); 
		int hash2 = cookies.hashCode(); 
		assertNotSame(cookies, galletas);
		assertNotEquals(hash1, hash2);
	}
		
	@Test
	public void testHashCodeEnviandoProductoIgual() {
		Product cookies = new Product(NOMBREPRODUCTO, 2.3, CADUGALLETAS, UPCGALLETAS);
		int hash1 = galletas.hashCode(); 
		int hash2 = cookies.hashCode(); 
		assertNotSame(cookies, galletas);
		assertEquals(hash1, hash2);
	}
	// Fin pruebas de equals()
	
	// Comienzo pruebas setId()
	
	@Test
	public void testSetId() {
		long beforeSetId = galletas.getId(); 
		galletas.setId(435);
		assertEquals(beforeSetId, galletas.getId());	// es correcto porque el Id de un producto solo cambia si cambia su UPC
	}
	
	// Fin pruebas setId()
}
