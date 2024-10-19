package vendingco;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test; 
 
/**
 * Proporciona todas las pruebas de la clase Product
 * 
 * @author tomcarr
 * @author javiram
 */
public class ProductTest {
	
	Product galletas; 
	
	@Before
    public void init() {
        galletas = new Product("galletas", 2.3, "1-1-2025", "112345678902");
    }
	
	// Comienzo pruebas de Product()
	
	@Test 
	public void test_Constructor_namepreciocaducadUpc_Correctos() {
		
		assertNotNull(galletas); 

	}
	
	@SuppressWarnings("unused")
	@Test
	public void test_Constructor_vacio() {
		Product galletas = new Product();

	}
	
	@Test
	public void test_Constructor_name_precio_igual_0_cadu_cad_Upc_Correcto() {
		galletas = new Product("galletas", 0, "1-1-2025", "112345678902");
		assertNotNull(galletas); 
		assertEquals(0, galletas.getPrecio(), 0.1);
	}
	
	
	@Test (expected = IllegalArgumentException.class)
	public void test_Constructor_name_null() {
		@SuppressWarnings("unused")
		Product galletas = new Product(null, 0, "1-1-2025", "112345678902");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_Constructor_precio_negativo() {
		@SuppressWarnings("unused")
		Product galletas = new Product("galletas", -2.3, "1-1-2025", "112345678902");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_Constructor_cadu_null() {
		@SuppressWarnings("unused")
		Product galletas = new Product("galletas", 2.3, null, "112345678902");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_Constructor_cadu_caducada() {
		@SuppressWarnings("unused")
		Product galletas = new Product("galletas", 2.3, "1-1-2000", "112345678902");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_Constructor_upc_null() {
		@SuppressWarnings("unused")
		Product galletas = new Product("galletas", -2.3, "1-1-2025", null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor_cad_con_chars() {
		@SuppressWarnings("unused")
		Product galletas = new Product("galletas", 2.3, "1-1-2025", "b12345678905");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_Constructor_cad_con_numchars_incorrecto() {
		@SuppressWarnings("unused")
		Product galletas = new Product("galletas", 2.3, "1-1-2025", "32442");
	}
	
	// Fin pruebas de Product()
	
	// Comienzo pruebas generateUPC() 
	@Test
	public void test_generateUPC_entrada_correcta() {
		String cad = galletas.generateUPC("112345678902"); 
		
		assertEquals("112345678902", cad);
	}
	
	@Test
	public void test_generateUPC_entrada_correcta_especial() {
		String cad = galletas.generateUPC("100000000007"); 
		
		assertEquals("100000000007", cad);
	}
	
	@Test
	public void test_generateUPC_entrada_correcta_limite_superior() {
		String cad = galletas.generateUPC("999999999993"); 
		
		assertEquals("999999999993", cad);
	}
	
	@Test
	public void test_generateUPC_entrada_correcta_con_suma_igual_tres() {
		String cad = galletas.generateUPC("100000000007"); 
		
		assertEquals("100000000007", cad);
	}
	@Test
	public void test_generateUPC_entrada_correcta_con_suma_igual_nueve() {
		String cad = galletas.generateUPC("111010000000"); 
		
		assertEquals("111010000000", cad);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_generateUPC_entrada_con_chars_invalidos() {
		@SuppressWarnings( "unused" )
		String cad = galletas.generateUPC("b123456789b0");
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_generateUPC_entrada_con_num_char_erroneo() {
		
		@SuppressWarnings("unused")
		String cad = galletas.generateUPC("434500345");
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_generateUPC_con_entrada_null() {
		@SuppressWarnings("unused")
		String cad = galletas.generateUPC(null);
		
	}
	@Test (expected = IllegalArgumentException.class)
	public void test_generateUPC_con_entrada_cadena_vacia() {
		@SuppressWarnings("unused")
		String cad = galletas.generateUPC("     ");
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_generateUPC_con_entrada_cadena_vacia_espacios() {
		@SuppressWarnings("unused")
		String cad = galletas.generateUPC("");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_generateUPC_con_entrada_con_doceavo_digito_incorrecto() {
		@SuppressWarnings("unused")
		String cad = galletas.generateUPC("112345678904");
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_generateUPC_con_entrada_con_numero_inferior_permitidos() {
		@SuppressWarnings("unused")
		String cad = galletas.generateUPC("012345678905");
		
	}
	
	// Fin pruebas getUPC()
	
	// Comienzo pruebas validaUPC()
/**
* 	@Test
*	public void test_validaUPC_con_char_inferior_al_digito_0() {
*		
*		assertFalse(galletas.validaUPC(" 01234567890")); 
*	}
*	
*	@Test
*	public void test_validaUPC_con_char_superior_al_digito_9() {
*		
*		assertFalse(galletas.validaUPC("b12345678905")); 
*	}
*	
*	@Test
*	public void test_validaUPC_con_numero_incorrecto_caracteres() {
*		
*		assertFalse(galletas.validaUPC("123456789")); 
*	}
*/
	// Fin pruebas validaUPC()
	
	//Comienzo pruebas copiaProducto()
	
	@Test 
	public void test_copiaProducto_sobre_un_producto_not_null() {
		Product galletas_copia = galletas.copyProduct(); 
		assertNotNull(galletas_copia); 
		
	}
	
	//Fin pruebas copiaProducto()
	
	
	// Comienzo de pruebas equalsUpc()
	
	@Test
	public void test_equalsUpc_producto_con_mimso_upc() {
		Product cookies = new Product("galletas", 2.3, "1-1-2025", "112345678902");
		assertTrue(galletas.equalsUPC(cookies));
		
	}
	@Test
	public void test_equalsUpc_producto_con_upc_distinto() {
		Product cookies = new Product("galletas", 2.3, "1-1-2025", "100000000007");
		assertFalse(galletas.equalsUPC(cookies));
		
	}
	
	@SuppressWarnings("unused")
	@Test (expected = IllegalArgumentException.class)
	public void test_equalsUpc_producto_con_producto2_null() {
		Product cookies = null; 
		assertNotNull(galletas);
		Boolean a = galletas.equalsUPC(cookies); 
	}
	// Fin de pruebas equalsUpc()
	
	// Comienzo pruebas getName()
	@Test
	public void test_getName () {
		assertEquals("galletas", galletas.getName()); 
	}
	// Fin pruebas getName()
	
	
	
	// Comienzo pruebas getCadu()
	@Test
	public void test_getCadu () {
		assertEquals("1-1-2025", galletas.getCadu()); 
	}
	// Fin pruebas getCadu()
	
	
	// Comienzo pruebas getPrecio()
	@Test
	public void test_getPrecio () {
		assertEquals(2.3, galletas.getPrecio(), 0.1); 
	}
	// Fin pruebas getPrecio()
	
	
	// Comienzo pruebas getUpc()
	@Test
	public void test_getUpc () {
		assertEquals( "112345678902", galletas.getUpc()); 
	}
	// Fin pruebas getUpc()
	
	// Comienzo pruebas setName()
	@Test
	public void test_setName_nombrecorrecto() {
		galletas.setName("cookies");
		assertEquals("cookies", galletas.getName());
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_setName_nombre_null() {
		galletas.setName(null);
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_setName_nombre_vacio() {
		galletas.setName("");
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_setName_nombre_vacio_espacios() {
		galletas.setName("    ");
		
	}
	// Fin pruebas setName()
	
	
	// Comienzo pruebas setUpc()
	@Test
	public void test_setUpc_nombre_correcto() {
		galletas.setUpc("100000000007");
		assertEquals("100000000007", galletas.getUpc());
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_setUpc_cadenaUpc_null() {
		galletas.setUpc(null);
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_setUpc_cadenaUpc_cadena_vacia() {
		galletas.setUpc("");
		
	}
	// Fin pruebas setUpc()
	
	
	// Comienzo pruebas setPrecio()
	@Test
	public void test_setPrecio_precio_valido() {
		galletas.setPrecio(10.0);
		assertEquals(10.0, galletas.getPrecio(), 0.1);
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_setPrecio_precio_negativo() {
		galletas.setPrecio(-5.0);
	}
	// Fin pruebas setPrecio()
	
	// Comienzo pruebas setCadu()
	@Test
	public void test_setCadu_cadu_valida () {
		galletas.setCadu("1-1-2130");
		assertEquals("1-1-2130", galletas.getCadu());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_setCadu_cadu_caducada() {
		galletas.setCadu("7-5-2002");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_setCadu_cadu_null() {
		galletas.setCadu(null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_setCadu_cadu_vacia() {
		galletas.setCadu("");
	}
	@Test (expected = IllegalArgumentException.class)
	public void test_setCadu_cadu_vacia_espacios() {
		galletas.setCadu("   ");
	}
	// Fin pruebas setCadu()
	
	// Comienzo pruebas de SetUpc()
	@Test
	public void test_setUpc_upc_valida () {
		galletas.setUpc("100000000007");
		assertEquals("100000000007", galletas.getUpc());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_setCadu_upc_null() {
		galletas.setUpc(null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_setUpc_upc_con_caracteres() {
		galletas.setUpc("1000000000d0");
	}
	@Test (expected = IllegalArgumentException.class)
	public void test_setUpc_upc_con_tamaño_incorrecto() {
		galletas.setUpc("4345");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_setUpc_upc_vacio() {
		galletas.setUpc("");
	}
	@Test (expected = IllegalArgumentException.class)
	public void test_setUpc_upc_vacio_espacios() {
		galletas.setUpc("  ");
	}
	// Fin pruebas de SetUpc()
	
}
