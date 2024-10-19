package vendingco;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList; 
/**
 * Proporciona todas las pruebas de la clase Pack
 * @author tomcarr
 * @author javiram
 */
public class PackTest {
	
	Product galletas; 
	Product cola;
	Product cafe; 
	Pack pack1;
	Pack pack2; 
	ArrayList <Product> listaDeProductos1;
	ArrayList <Product> listaDeProductos2;
	private static final String NAMEGALLETAS = "galletas";
	private static final String UPCGALLETAS = "112345678902";
	private static final String CADUGALLETAS = "1-1-2025";
	private static final String CADUCOLA = "20-3-2023";
	private static final String CADUMIN = "1-1-2023";
	private static final String UPCCOLA = "100000000007";
	private static final String UPCLIBRE = "110000000006"; 
	private static final String NAMEPACK1 = "pack1";
	private static final String NAMEPACK2 = "pack2";
	@Before
	public void init() {
		galletas = new Product(NAMEGALLETAS, 2.3, CADUGALLETAS, UPCGALLETAS);
		cola = new Product("Coca-Cola", 1.5, CADUCOLA , UPCCOLA );
		cafe = new Product("Coffe", 6.7, "20-3-2050", "999999999993");
		listaDeProductos1 = new ArrayList<>(); 
		listaDeProductos1.add(galletas); 
		listaDeProductos1.add(cola); 
		listaDeProductos1.add(cafe); 
		listaDeProductos2 = new ArrayList<>(); 
		listaDeProductos2.add(cola); 
		listaDeProductos2.add(galletas); 
		pack1 = new Pack(NAMEPACK1, listaDeProductos1, 1);
		pack2 = new Pack(NAMEPACK2, listaDeProductos2, 2);
	}
	
	// Comienzo pruebas Pack()
	@Test
	public void testConstructorParametrosCorrectos() {
		assertNotNull(pack1);
	}
	
	@Test
	public void testConstructorValidaListaProductos() {
		assertEquals(listaDeProductos1.size(), pack1.getPaquete().size());
		for (int i = 0; i < listaDeProductos1.size(); i++) {
			assertTrue(listaDeProductos1.get(i).equals(pack1.getPaquete().get(i))); 
		}
	}
	
	@Test
	public void testConstructorValidaFechaCaduciad() {
		assertEquals(CADUCOLA, pack1.getCadu()); 
	}
	
	@Test 
	public void testConstructorValidaIdPack() {
		assertEquals(1, pack1.getId()); 
	}
	
	@Test
	public void testConstructorValidaPrecio() {
		double precioPack = 0.8*(2.3+1.5+6.7); 
		assertEquals(precioPack, pack1.getPrecio() ,0.1); 
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorDosProductosConMismoUpc() {
		Product galletas2 = new Product("cookies", 2.8,"22-5-2024", UPCGALLETAS);
		listaDeProductos1.add(galletas2); 
		pack1 = new Pack(NAMEPACK1, listaDeProductos1, 1); 
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorUnSoloProductoEnListaProductos() {
		listaDeProductos2.remove(galletas); 
		pack1 = new Pack(NAMEPACK1, listaDeProductos2, 1); 
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorConListaProductosNull() {
		pack1 = new Pack(NAMEPACK1, null, 1); 
	}
	// Fin pruebas Pack()
	
	// Comienzo pruebas copy()
	
	@Test 
	public void testCopy() {
		Pack copyPack1 = (Pack) pack1.copy(); 
		assertNotNull(copyPack1);
		assertEquals(copyPack1.getName(), pack1.getName());
		assertEquals(listaDeProductos1.size(), pack1.getPaquete().size());
		for (int i = 0; i < listaDeProductos1.size(); i++) {
			assertTrue(listaDeProductos1.get(i).equals(pack1.getPaquete().get(i))); 
		}
		assertEquals(copyPack1.getId(), pack1.getId()); 
		assertEquals(copyPack1.getPrecio(), pack1.getPrecio(), 0.1); 
		assertEquals(copyPack1.getCadu(), pack1.getCadu());
		
	}
	
	// Fin pruebas copy()
	
	// Comienzo pruebas getPaquete()
	
	@Test
	public void testGetPaquete() {
		assertNotSame(listaDeProductos1, pack1.getPaquete());
		assertEquals(listaDeProductos1.size(), pack1.getPaquete().size());
		for (int i = 0; i < listaDeProductos1.size(); i++) {
			assertTrue(listaDeProductos1.get(i).equals(pack1.getPaquete().get(i))); 
		}
	}

	// Fin pruebas getPaquete()
	

	// Comienzo pruebas setPaquete()
	
	@Test 
	public void testSetPaqueteCorrecto() {
		listaDeProductos2.add(cafe); 
		pack2.setPaquete(listaDeProductos2);
		assertNotSame(listaDeProductos2, pack2.getPaquete());
		assertEquals(listaDeProductos2.size(), pack2.getPaquete().size());
		for (int i = 0; i < listaDeProductos2.size(); i++) {
			assertTrue(listaDeProductos2.get(i).equals(pack2.getPaquete().get(i))); 
		}
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetPaqueteConPaqueteNull() {
		pack2.setPaquete(null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testsetpaqueteConPaqueteSizeMenor2() {
		listaDeProductos2.remove(galletas);
		pack2.setPaquete(listaDeProductos2);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testsetpaqueteConDosProductosIguales() {
		listaDeProductos2.add(galletas);
		pack2.setPaquete(listaDeProductos2);
	}
	// Fin pruebas setPaquete()
	
	// Comienzo pruebas getPrecio()
	
	@Test 
	public void testgetPrecioCorrecto() {
		double precioPack1 = 0.8*(2.3 + 1.5 + 6.7); 
		assertEquals(precioPack1, pack1.getPrecio(), 0.1);
	}
	@Test 
	public void testgetPrecioHabiendoCambiadoPrecioDeAlgunProducto() {
		double precioP1Inicial = pack1.getPrecio(); 
		
		cola.setPrecio(1);
		assertNotEquals(precioP1Inicial, pack1.getPrecio(),0.1);
		
		double nuevoPrecioP1 = 0.8*(2.3+1+6.7); 
		assertEquals(nuevoPrecioP1, pack1.getPrecio(), 0.1);
		
	}
	// Fin pruebas getPrecio()
	
	// Comienzo de pruebas setPrecio()
	@Test
	public void testSetPrecioCambiandoPrecioproductos() {
		cola.setPrecio(1);
		pack1.setPrecio();
		double nuevoPrecioP1 = 0.8*(2.3+1+6.7); 
		assertEquals(nuevoPrecioP1, pack1.getPrecio(), 0.1);
	}
	
	@Test
	public void testSetPrecioAnnadiendoUnproductoNuevo() {
		Product escalofrio = new Product ("Escalofrios Risi", 0.10, "22-4-2027", UPCLIBRE);
		pack1.addProduct(escalofrio);
		pack1.setPrecio();
		double nuevoPrecioP1 = 0.8*(2.3+1.5+0.1+6.7); 
		assertEquals(nuevoPrecioP1, pack1.getPrecio(), 0.1);
	}
	
	@Test
	public void testSetPrecioAlEliminarUnproducto() {
		pack1.delProduct(cola);
		pack1.setPrecio();
		double nuevoPrecioP1 = 0.8*(2.3+6.7); 
		assertEquals(nuevoPrecioP1, pack1.getPrecio(), 0.1);
	}
	
	@Test 
	public void testSetPrecioSinCambiarPreciosProductos() {
		pack1.setPrecio(434);
		double precioP1 = 0.8*(2.3+1.5+6.7); 
		assertEquals(precioP1, pack1.getPrecio(), 0.1);
	}
	@Test
	public void testSetPrecioSinCambiarPreciosProductosVacio() {
		pack1.setPrecio();
		double precioP1 = 0.8*(2.3+1.5+6.7); 
		assertEquals(precioP1, pack1.getPrecio(), 0.1);
	}
	// Fin pruebas setPrecio()
	
	// Comienzo pruebas getIdPack()
	
	@Test 
	public void testGetIdPack() {
		assertEquals(1, pack1.getId());
	}
	
	// Fin pruebas getIdPack()
	
	// Comienzo pruebas setIdPack()
	
	@Test 
	public void testSetIdPackCorrecto() {
		pack1.setId(45);
		assertEquals(45, pack1.getId());
	}
	
	@Test 
	public void testSetIdPackIdLimiiteInferior0() {
		pack1.setId(0);
		assertEquals(0, pack1.getId());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testsetIdPackIdNull() {
		pack1.setId(-45);
	}
	// Fin pruebas setIdPack()
	
	// Comienzo pruebas setCadu()
	
	@Test
	public void testSetCaduDiferenciaDeAnnos() {
		ArrayList<Product>listaProductosX = new ArrayList<>(); 
		listaProductosX.add(cola); 			// cola.getCadu() = "20-3-2023"
		listaProductosX.add(galletas); 		// galletas.getCadu() = "1-1-2025"
		listaProductosX.add(cafe);	// cafe.getCadu() = "20-3-2050"
		pack2.setPaquete(listaProductosX);
		pack2.setCadu(); 
		String caducidad = CADUCOLA;		// De todos los productos introducidos, es el que se caduca antes
		assertEquals(caducidad, pack2.getCadu());
	}
	
	@Test
	public void testSetCaduDiferenciaDeMeses() {
		ArrayList<Product>listaProductosX = new ArrayList<>(); 
		cola.setCadu(CADUCOLA);
		listaProductosX.add(cola); 			// cola.getCadu() = "20-3-2023"
		galletas.setCadu(CADUMIN);
		listaProductosX.add(galletas); 		// galletas.getCadu() = "1-1-2023"
		cafe.setCadu(CADUCOLA);
		listaProductosX.add(cafe);	// cafe.getCadu() = "20-3-2023"
		pack2.setPaquete(listaProductosX);
		pack2.setCadu(); 
		
		assertEquals(CADUMIN, pack2.getCadu());
	}
	
	@Test
	public void testSetCaduDiferenciaDeDias() {
		ArrayList<Product>listaProductosX = new ArrayList<>(); 
		cola.setCadu("20-1-2023");
		listaProductosX.add(cola); 			// cola.getCadu() = "20-1-2023"
		galletas.setCadu(CADUMIN);
		listaProductosX.add(galletas); 		// galletas.getCadu() = "1-1-2023"
		cafe.setCadu("20-1-2023");
		listaProductosX.add(cafe);	// cafe.getCadu() = "20-1-2023"
		pack2.setPaquete(listaProductosX);
		pack2.setCadu(); 
		String caducidad = CADUMIN;		// De todos los productos introducidos, es el que se caduca antes
		assertEquals(caducidad, pack2.getCadu());
	}
	
	@Test
	public void testSetCaduConDiasIgulales() {
		ArrayList<Product>listaProductosX = new ArrayList<>(); 
		cola.setCadu(CADUMIN);
		listaProductosX.add(cola); 			// cola.getCadu() = "1-1-2023"
		galletas.setCadu(CADUMIN);
		listaProductosX.add(galletas); 		// galletas.getCadu() = "1-1-2023"
		cafe.setCadu(CADUMIN);
		listaProductosX.add(cafe);	// cafe.getCadu() = "1-1-2023"
		pack2.setPaquete(listaProductosX);
		pack2.setCadu();
		assertEquals(CADUMIN, pack2.getCadu());
	}
	
	// Fin pruebas setCadu()
	
	// Comienzo pruebas addProduct()
	
	@Test 
	public void testAddProductoToTestProductoCorrecto() {
		Product escalofrio = new Product ("Escalofrios", 0.10, "22-4-2026", UPCLIBRE);
		pack1.addProduct(escalofrio);
		assertTrue(pack1.haveProduct(escalofrio)); 
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAddProductoNull() {
		pack1.addProduct(null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAddProductoConUnProductoRepetido() {
		pack1.addProduct(galletas);
	}
	
	// Fin pruebas addProduct()
	
	
	// Comienzo pruebas delProduct()
	
	@Test
	public void testDelProductEliminableEnviandoUnProducto() {
		pack1.delProduct(galletas);
		assertFalse(pack1.haveProduct(galletas));
	}
	
	@Test
	public void testDelProductEliminableEnviandoUnUpc() {
		pack1.delProduct(UPCGALLETAS);
		assertFalse(pack1.haveProduct(UPCGALLETAS));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testDelProductConProductoUpcNull() {
		String pdel = null; 
		pack1.delProduct(pdel);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testDelProductConProductoNull() {
		Product escalope = null; 
		pack1.delProduct(escalope);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testDelProductDeUnPackConSolo2PrductosEnviandoProducto() {
		pack2.delProduct(cola);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testDelProductDeUnPackConSolo2PrductosEnviandoUpc() {
		pack2.delProduct(UPCCOLA);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testDelProductEnUnPaqueteDondeNoEstaDichoProductoPorUPC() {
		pack2.delProduct(UPCLIBRE);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testDelProductEnUnPaqueteDondeNoEstaDichoProducto() {
		pack2.delProduct(cafe);
	}
	// Fin pruebas delProduct()
	
	// Comienzo pruebas haveProduct()
	
	@Test 
	public void testHaveProductEnviandoUnProductoContenido() {
		assertTrue(pack1.haveProduct(galletas));
	}
	
	@Test
	public void testHaveProductEnviandoUpcDeProductoContenido() {
		assertTrue(pack1.haveProduct(UPCCOLA));
	}
	
	@Test
	public void testHaveProductEnviandoUnProductoNoContenido() {
		Product escalofrio = new Product ("Escalofriosss", 0.10, "22-4-2026", UPCLIBRE);
		assertFalse(pack1.haveProduct(escalofrio));
	}
	
	@Test
	public void testHaveProductEnviandoUnUpcDeUnProductoNoContenido() {
		assertFalse(pack1.haveProduct(UPCLIBRE));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testhaveProductEnviandoProductoNull() {
		Product pnull = null; 
		pack1.haveProduct(pnull); 
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testhaveProductEnviandoUnUpcNull() {
		String upcNull = null; 
		pack1.haveProduct(upcNull); 
	}
	
	// Fin pruebas haveProduct()
	
	// Comienzo pruebas equals()
	
	@Test 
	public void testEquals() {
		Pack prueba = pack1; 
		assertTrue(pack1.equals(prueba));
	}
	
	@Test 
	public void testEqualsPackConProduct() { 
		Vendible cookies = new Product(NAMEGALLETAS, 2.3, CADUGALLETAS, UPCGALLETAS);
		Vendible pack11 = new Pack(NAMEPACK1, listaDeProductos1, 1);
		assertFalse(pack11.equals(cookies));
	}
	
	@Test 
	public void testEqualsConPacksIguales() {
		Pack prueba = new Pack(NAMEPACK1, listaDeProductos1, 3);
		assertTrue(pack1.equals(prueba)); 
	}
	
	@Test 
	public void testEqualsConPacksExctamenteIgulaesMenosLosUpcsDeLosProducts() {
		listaDeProductos1.remove(galletas);
		cafe.setCadu(CADUGALLETAS);
		cafe.setPrecio(2.3); 
		Pack prueba = new Pack(NAMEPACK2, listaDeProductos1, 3);
		assertFalse(pack2.equals(prueba)); 
	}
	
	@Test 
	public void testEqualsConPacksConDiferentesNombres() {
		Pack prueba = new Pack(NAMEPACK2, listaDeProductos1, 3);
		assertFalse(pack1.equals(prueba)); 
	}
	
	@Test
	public void testEqualsConPacksIndenticosEnTodoMenosPrecios() {
		Product galletas2 = new Product(NAMEGALLETAS, 3.5, CADUGALLETAS, UPCGALLETAS);
		ArrayList <Product> listaDeProductosX = new ArrayList<>(); 
		listaDeProductosX.add(galletas2); 
		listaDeProductosX.add(cola); 
		Pack prueba = new Pack(NAMEPACK1, listaDeProductosX, 3);
		assertFalse(pack1.equals(prueba)); 
	}
	
	@Test
	public void testEqualsConPacksIndenticosEnTodoMenosCaduciad() {
		Product galletas2 = new Product(NAMEGALLETAS, 2.3, "1-1-2022", UPCGALLETAS);
		ArrayList <Product> listaDeProductosX = new ArrayList<>(); 
		listaDeProductosX.add(galletas2); 
		listaDeProductosX.add(cola); 
		listaDeProductosX.add(cafe); 
		Pack prueba = new Pack(NAMEPACK1, listaDeProductosX, 3);
		assertFalse(pack1.equals(prueba)); 
	}
	
	@Test
	public void testEqualsConPacksIndenticosEnTodoMenosNumProducts() {
		Product galletas2 = new Product(NAMEGALLETAS, 9 , CADUCOLA, UPCGALLETAS);
		ArrayList <Product> listaDeProductosX = new ArrayList<>(); 
		listaDeProductosX.add(galletas2); 
		listaDeProductosX.add(cola); 
		Pack prueba = new Pack(NAMEPACK1, listaDeProductosX, 3);
		assertFalse(pack1.equals(prueba)); 
	}
	
	@Test
	public void testEqualsConPackNull() {
		Pack nullPack = null; 
		assertFalse(pack1.equals(nullPack));
	}
	// Fin pruebas equals()
	
	// Comienzo pruebas hashCode()
	
	@Test 
	public void testHashCodeIgualesTotalmente() {
		Pack prueba = pack1; 
		int hash1 = pack1.hashCode(); 
		int hash2 = prueba.hashCode(); 
		assertSame(prueba, pack1);
		assertEquals(hash1, hash2);
	}
		
	@Test 
	public void testHashCodePackConProduct() { 
		int hash1 = pack1.hashCode(); 
		int hash2 = galletas.hashCode(); 
		assertNotSame(galletas, pack1);
		assertNotEquals(hash1, hash2);
	}
		
	@Test 
	public void testHashCodeConPacksIgualesPeroNoLosMismos() {
		Pack prueba = new Pack(NAMEPACK1, listaDeProductos1, 3);
		int hash1 = pack1.hashCode(); 
		int hash2 = prueba.hashCode(); 
		assertNotSame(prueba, pack1);
		assertEquals(hash1, hash2);
	}
		
	@Test 
	public void testHasCodeConPacksExctamenteIgulaesMenosLosUpcsDeLosProducts() {
		listaDeProductos1.remove(galletas);
		cafe.setCadu(CADUGALLETAS);
		cafe.setPrecio(2.3); 
		Pack prueba = new Pack(NAMEPACK2, listaDeProductos1, 3);
		int hash1 = pack1.hashCode(); 
		int hash2 = prueba.hashCode(); 
		assertNotSame(prueba, pack1);
		assertNotEquals(hash1, hash2);
	}
		
	@Test 
	public void testHashCodeConPacksConDiferentesNombres() {
		Pack prueba = new Pack(NAMEPACK2, listaDeProductos1, 3);
		int hash1 = pack1.hashCode(); 
		int hash2 = prueba.hashCode(); 
		assertNotSame(prueba, pack1);
		assertNotEquals(hash1, hash2); 
	}
		
	@Test
	public void testHashCodeConPacksIndenticosEnTodoMenosPrecios() {
		Product galletas2 = new Product(NAMEGALLETAS, 3.5, CADUGALLETAS, UPCGALLETAS);
		ArrayList <Product> listaDeProductosX = new ArrayList<>(); 
		listaDeProductosX.add(galletas2); 
		listaDeProductosX.add(cola); 
		Pack prueba = new Pack(NAMEPACK1, listaDeProductosX, 3);
		int hash1 = pack1.hashCode(); 
		int hash2 = prueba.hashCode(); 
		assertNotSame(prueba, pack1);
		assertNotEquals(hash1, hash2);
	}
		
	@Test
	public void testHashCodeConPacksIndenticosEnTodoMenosCaduciad() {
		Product galletas2 = new Product(NAMEGALLETAS, 2.3, "1-1-2022", UPCGALLETAS);
		ArrayList <Product> listaDeProductosX = new ArrayList<>(); 
		listaDeProductosX.add(galletas2); 
		listaDeProductosX.add(cola); 
		listaDeProductosX.add(cafe); 
		Pack prueba = new Pack(NAMEPACK1, listaDeProductosX, 3);
		int hash1 = pack1.hashCode(); 
		int hash2 = prueba.hashCode(); 
		assertNotSame(prueba, pack1);
		assertNotEquals(hash1, hash2);
	}
		
	@Test
	public void testHashCodeConPacksIndenticosEnTodoMenosNumProducts() {
		Product galletas2 = new Product(NAMEGALLETAS, 9 , CADUCOLA, UPCGALLETAS);
		ArrayList <Product> listaDeProductosX = new ArrayList<>(); 
		listaDeProductosX.add(galletas2); 
		listaDeProductosX.add(cola); 
		Pack prueba = new Pack(NAMEPACK1, listaDeProductosX, 3);
		assertFalse(pack1.equals(prueba));
		int hash1 = pack1.hashCode(); 
		int hash2 = prueba.hashCode(); 
		assertNotSame(prueba, pack1);
		assertNotEquals(hash1, hash2);
	}
		
	// Fin pruebas hasCode()
}
