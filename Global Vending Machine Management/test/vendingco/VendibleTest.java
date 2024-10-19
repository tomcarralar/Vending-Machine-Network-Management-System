package vendingco;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Proporciona todas las pruebas de la clase Vendible
 * @author tomcarr
 * @author javiram
 */
public class VendibleTest {

	Vendible galletas; 
	Vendible cola;
	Vendible cafe; 
	Vendible pack1; 
	ArrayList <Product> listaDeProductos;
	private static final String NAMEGALLETAS = "galletas";
	private static final String UPCGALLETAS = "112345678902";
	private static final String CADUGALLETAS = "1-1-2025";
	@Before
	public void init() {
		galletas = new Product(NAMEGALLETAS , 2.3, CADUGALLETAS, UPCGALLETAS);
		cola = new Product("Coca-Cola", 1.5, "20-3-2023", "100000000007");
		cafe = new Product("Coffe", 6.7, "20-3-2050", "999999999993");
		listaDeProductos = new ArrayList<>(); 
		listaDeProductos.add((Product) galletas); 
		listaDeProductos.add((Product) cola); 
		listaDeProductos.add((Product) cafe); 
		pack1 = new Pack("pack1", listaDeProductos, 232);
	}
	
	// Comienzo tests del Constructor
	
	@Test
	public void testConstructorVendibleProductos(){
		
		assertNotNull(galletas); 
		assertNotNull(cola);
		assertNotNull(cafe);
	}
	
	@Test
	public void testConstructorVenidiblePack() {
		
		assertNotNull(pack1); 
	}
	
	@Test 
	public void testConstuctorVendibleProductoNameCorrecto() {
		assertEquals(NAMEGALLETAS , galletas.getName()); 
	}
	
	@Test 
	public void testConstuctorVendiblePackNameCorrecto() {
		Vendible cookies = new Product("  galletas", 2.3, CADUGALLETAS, UPCGALLETAS);
		assertEquals("  galletas", cookies.getName()); 
	}
	
	@Test 
	public void testConstuctorVendiblePackCorrectoConEspaciosAlPrincipio() {
		Vendible pack11 = new Pack("  pack1", listaDeProductos, 232);	
		assertEquals("  pack1", pack11.getName()); 
	}
	
	@SuppressWarnings("unused")
	@Test (expected = IllegalArgumentException.class) 
	public void testConstuctorVendibleProductoNameNull() {
		Vendible cookies = new Product(null, 2.3, CADUGALLETAS, UPCGALLETAS);
	}
	
	@SuppressWarnings("unused")
	@Test (expected = IllegalArgumentException.class) 
	public void testConstuctorVendiblePackNameNull() {
		Vendible pack11 = new Pack(null, listaDeProductos, 232);
	}
	
	@SuppressWarnings("unused")
	@Test (expected = IllegalArgumentException.class) 
	public void testConstuctorVendibleProductoNameEspacios() {
		Vendible cookies = new Product("   ", 2.3, CADUGALLETAS, UPCGALLETAS);
	}
	
	@SuppressWarnings("unused")
	@Test (expected = IllegalArgumentException.class) 
	public void testConstuctorVendiblePackNameEspacios() {
		Vendible pack11 = new Pack("   ", listaDeProductos, 232);
	}
	
	@SuppressWarnings("unused")
	@Test (expected = IllegalArgumentException.class) 
	public void testConstuctorVendibleProductoNameVacio() {
		Vendible cookies = new Product("", 2.3, CADUGALLETAS, UPCGALLETAS);
	}
	
	@SuppressWarnings("unused")
	@Test (expected = IllegalArgumentException.class) 
	public void testConstuctorVendiblePackNameVacio() {
		Vendible pack11 = new Pack("", listaDeProductos, 232);
	}
	
	// Fin tests del Constructor
	
	// Comienzo pruebas getCadu()
	@Test
	public void testGetCaduProduct() {
		assertEquals(CADUGALLETAS, galletas.getCadu()); 
	}
	
	@Test 
	public void testGetCaduPack() {
		assertEquals("20-3-2023", pack1.getCadu());
	}
	
	// Fin pruebas getCadu()
	
	// Comienzo prubeas de setName()
	@Test 
	public void testSetNamePack() {
		pack1.setName("paqueteNombre");
		assertEquals("paqueteNombre", pack1.getName());
	}
	
	@Test 
	public void testSetNameProducto() {
		galletas.setName("galletasNombre");
		assertEquals("galletasNombre", galletas.getName());
	}
	@Test
	public void testSetNamePackCorrectoComenzandoConEspacios() {
		pack1.setName("     Espacios");
		assertEquals("     Espacios", pack1.getName());
	}
	
	@Test 
	public void testSetNameProductoCorrectoComenzandoConEspacios() {
		galletas.setName("    Nombre");
		assertEquals("    Nombre", galletas.getName());
	}
	
	@Test
	public void testSetNamePackCorrectoConEspaciosEntreMedias() {
		pack1.setName("Espa    cios");
		assertEquals("Espa    cios", pack1.getName());
	}
	
	@Test 
	public void testSetNameProductoCorrectoConEspaciosEntreMedias() {
		galletas.setName("Nom    bre");
		assertEquals("Nom    bre", galletas.getName());
	}
	
	@Test
	public void testSetNamePackCorrectoConEspaciosAlFinal() {
		pack1.setName("Espacios    ");
		assertEquals("Espacios    ", pack1.getName());
	}
	
	@Test 
	public void testSetNameProductoCorrectoConEspaciosAlFinal() {
		galletas.setName("Nombre    ");
		assertEquals("Nombre    ", galletas.getName());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetNameProductoNombreNull() {
		galletas.setName(null);
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetNameProductoNombreVacio() {
		galletas.setName("");
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetNameProdcutoNombreVacioEspacios() {
		galletas.setName("    ");
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetNamePackNombreNull() {
		pack1.setName(null);
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetNamePackNombreVacio() {
		pack1.setName("");
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetNamePackNombreVacioEspacios() {
		pack1.setName("    ");
		
	}
	
	// Fin pruebas setName()
	
	// Comienzo pruebas getName()
	@Test
	public void testGetNamePack() {
		assertEquals("pack1", pack1.getName()); 
	}
	
	@Test
	public void testGetNameProducto() {
		assertEquals(NAMEGALLETAS, galletas.getName()); 
	}
	
	// Fin pruebas getName()
	
	// Comienzo pruebas setPrecio()
	@Test 
	public void testSetPrecioPackVacio() {
		double precio = pack1.getPrecio();
		pack1.setPrecio();
		assertEquals(precio, pack1.getPrecio(), 0.1); 
	}
	
	@Test 
	public void testSetPrecioProductoVacio() {
		double precio = 0;
		galletas.setPrecio();
		assertEquals(precio, galletas.getPrecio(), 0.1); 
	}
	// Fin pruebas setPrecio()
	
	// Comienzo pruebas getId()
	@Test 
	public void testGetIdProducto() {
		long idGalletas = 0; 
		idGalletas = Long.parseLong(UPCGALLETAS);  
		assertEquals(idGalletas, galletas.getId());
	}
	
	@Test
	public void testGetIPack() {
		Pack pack11 = new Pack("pack11", listaDeProductos, 1);
		long idPack11 = 0; 
		idPack11 = 1;  
		assertEquals(idPack11, pack11.getId());
	}
	// Fin pruebas getPrecio()
}
