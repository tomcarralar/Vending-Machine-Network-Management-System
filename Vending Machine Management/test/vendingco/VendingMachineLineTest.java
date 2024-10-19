package vendingco;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Proporciona todas las pruebas de la clase VendingMachineLine
 * 
 * @author tomcarr
 * @author javiram
 */
public class VendingMachineLineTest {
	
	Product Producto; 
	VendingMachineLine VendingMachineLineExtra1;
	VendingMachineLine VendingMachineLineExtra2;
	VendingMachineLine VendingMachineLineExtra3;
	VendingMachineLine VendingMachineLineExtra4;
	VendingMachineLine VendingMachineLineExtra5;
	
	@Before
    public void init() {
		Producto = new Product("galletas", 2.3, "1-1-2025", "112345678902"); 
		VendingMachineLineExtra1 = new VendingMachineLine(0, Producto, 100, 50);
		VendingMachineLineExtra2 = new VendingMachineLine(1, Producto, 100000, 100000);
		VendingMachineLineExtra3 = new VendingMachineLine(100000, Producto, 5, 4);
		VendingMachineLineExtra4 = new VendingMachineLine(2, Producto, 100000, 0);
		VendingMachineLineExtra5 = new VendingMachineLine(3, Producto, 1, 0);
    }
	
	// Comienzo de pruebas sobre constuctores
	
	@Test
	public void test_Constructor_VendingMachineLine_Correcto() {
		VendingMachineLine VendingMachineLineActual = new VendingMachineLine(0, Producto, 1);
		
		assertNotNull(VendingMachineLineActual);
		assertEquals(0, VendingMachineLineActual.getIdlinea());
		assertNotSame(Producto, VendingMachineLineActual.getProducto());
		assertEquals(Producto.getUpc(), VendingMachineLineActual.getProducto().getUpc());
		assertEquals(0, VendingMachineLineActual.getQuantity());
		assertEquals(1, VendingMachineLineActual.getMaxQuantity());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_Constructor_VendingMachineLine_Id_Incorrecto() {
		@SuppressWarnings("unused")
		VendingMachineLine VendingMachineLineActual = new VendingMachineLine(-1, Producto, 10);
	}
	
	@Test (expected = NullPointerException.class)
	public void test_Constructor_VendingMachineLine_NullProduct_Incorrecto() {
		@SuppressWarnings("unused")
		VendingMachineLine VendingMachineLineActual = new VendingMachineLine(1, null, 10);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_Constructor_VendingMachineLine_CeroMaxQuantity_Incorrecto() {
		@SuppressWarnings("unused")
		VendingMachineLine VendingMachineLineActual = new VendingMachineLine(1, Producto, 0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_Constructor_VendingMachineLine_NegativeMaxQuantity_Incorrecto() {
		@SuppressWarnings("unused")
		VendingMachineLine VendingMachineLineActual = new VendingMachineLine(1, Producto, -1);
	}
	
	
	
	
	
	@Test
	public void test_ConstructorconCantidad_VendingMachineLine_Correcto1() {
		VendingMachineLine VendingMachineLineActual = new VendingMachineLine(0, Producto, 1, 0);
		
		assertNotNull(VendingMachineLineActual);
		assertEquals(0, VendingMachineLineActual.getIdlinea());
		assertNotSame(Producto, VendingMachineLineActual.getProducto());
		assertEquals(Producto.getUpc(), VendingMachineLineActual.getProducto().getUpc());
		assertEquals(0, VendingMachineLineActual.getQuantity());
		assertEquals(1, VendingMachineLineActual.getMaxQuantity());
	}
	
	@Test
	public void test_ConstructorconCantidad_VendingMachineLine_Correcto2() {
		VendingMachineLine VendingMachineLineActual = new VendingMachineLine(7, Producto, 1, 1);
		
		assertNotNull(VendingMachineLineActual);
		assertEquals(7, VendingMachineLineActual.getIdlinea());
		assertNotSame(Producto, VendingMachineLineActual.getProducto());
		assertEquals(Producto.getUpc(), VendingMachineLineActual.getProducto().getUpc());
		assertEquals(1, VendingMachineLineActual.getQuantity());
		assertEquals(1, VendingMachineLineActual.getMaxQuantity());
	}
	
	@Test
	public void test_ConstructorconCantidad_VendingMachineLine_Correcto3() {
		VendingMachineLine VendingMachineLineActual = new VendingMachineLine(123456789, Producto, 100, 100);
		
		assertNotNull(VendingMachineLineActual);
		assertEquals(123456789, VendingMachineLineActual.getIdlinea());
		assertNotSame(Producto, VendingMachineLineActual.getProducto());
		assertEquals(Producto.getUpc(), VendingMachineLineActual.getProducto().getUpc());
		assertEquals(100, VendingMachineLineActual.getQuantity());
		assertEquals(100, VendingMachineLineActual.getMaxQuantity());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_ConstructorconCantidad_VendingMachineLine__Id_Inorrecto() {
		@SuppressWarnings("unused")
		VendingMachineLine VendingMachineLineActual = new VendingMachineLine(-1, Producto, 10, 5);
	}
	
	@Test (expected = NullPointerException.class)
	public void test_ConstructorconCantidad_VendingMachineLine__NullProduct_Inorrecto() {
		@SuppressWarnings("unused")
		VendingMachineLine VendingMachineLineActual = new VendingMachineLine(1, null, 10, 5);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_ConstructorconCantidad_VendingMachineLine__CeroMaxQuantity_Inorrecto() {
		@SuppressWarnings("unused")
		VendingMachineLine VendingMachineLineActual = new VendingMachineLine(1, Producto, 0, 5);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_ConstructorconCantidad_VendingMachineLine__NegativeMaxQuantity_Inorrecto() {
		@SuppressWarnings("unused")
		VendingMachineLine VendingMachineLineActual = new VendingMachineLine(1, Producto, -1, 5);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_ConstructorconCantidad_VendingMachineLine__NegativeQuantity_Inorrecto() {
		@SuppressWarnings("unused")
		VendingMachineLine VendingMachineLineActual = new VendingMachineLine(1, Producto, 10, -1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_ConstructorconCantidad_VendingMachineLine__ExcesiveQuantity_Inorrecto() {
		@SuppressWarnings("unused")
		VendingMachineLine VendingMachineLineActual = new VendingMachineLine(1, Producto, 99, 100);
	}
	
	
	
	
	
	@Test
	public void test_ConstructorCopia_VendingMachineLine_Correcto1() {
		VendingMachineLine VendingMachineLineActual = new VendingMachineLine(0, VendingMachineLineExtra1);
		
		assertNotNull(VendingMachineLineActual);
		assertEquals(0, VendingMachineLineActual.getIdlinea());
		assertNotSame(Producto, VendingMachineLineActual.getProducto());
		assertEquals(Producto.getUpc(), VendingMachineLineActual.getProducto().getUpc());
		assertEquals(50, VendingMachineLineActual.getQuantity());
		assertEquals(100, VendingMachineLineActual.getMaxQuantity());
	}
	
	@Test
	public void test_ConstructorCopia_VendingMachineLine_Correcto2() {
		VendingMachineLine VendingMachineLineActual = new VendingMachineLine(1, VendingMachineLineExtra1);
		
		assertNotNull(VendingMachineLineActual);
		assertEquals(1, VendingMachineLineActual.getIdlinea());
		assertNotSame(Producto, VendingMachineLineActual.getProducto());
		assertEquals(Producto.getUpc(), VendingMachineLineActual.getProducto().getUpc());
		assertEquals(50, VendingMachineLineActual.getQuantity());
		assertEquals(100, VendingMachineLineActual.getMaxQuantity());
	}
		
	@Test (expected = IllegalArgumentException.class)
	public void test_ConstructorCopia_VendingMachineLine_Id_Incorrecto() {
		@SuppressWarnings("unused")
		VendingMachineLine VendingMachineLineActual = new VendingMachineLine(-1, VendingMachineLineExtra1);
	}
	
	@Test (expected = NullPointerException.class)
	public void test_ConstructorconCantidad_VendingMachineLine__NullVendingMachineLine_Inorrecto() {
		@SuppressWarnings("unused")
		VendingMachineLine VendingMachineLineActual = new VendingMachineLine(1, null);
	}	
		
	// Fin de pruebas sobre constuctores
	
	
	

	//Comienzo de pruebas sobre getters y setters
	@Test
	public void test_getId_Correcto1() {		
		assertNotNull(VendingMachineLineExtra1);
		assertEquals(0, VendingMachineLineExtra1.getIdlinea());
	}
	
	@Test
	public void test_getId_Correcto2() {		
		assertNotNull(VendingMachineLineExtra3);
		assertEquals(100000, VendingMachineLineExtra3.getIdlinea());
	}
	
	
	
	
	
	@Test
	public void test_setId_Correcto1() {
		VendingMachineLine VendingMachineLineActual = new VendingMachineLine(5, VendingMachineLineExtra1);
		VendingMachineLineActual.setIdlinea(0);
		
		assertNotNull(VendingMachineLineActual);
		assertEquals(0, VendingMachineLineActual.getIdlinea());
	}
	
	@Test
	public void test_setId_Correcto2() {
		VendingMachineLine VendingMachineLineActual = new VendingMachineLine(5, VendingMachineLineExtra1);
		VendingMachineLineActual.setIdlinea(50);
		
		assertNotNull(VendingMachineLineActual);
		assertEquals(50, VendingMachineLineActual.getIdlinea());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_setId_Negative_Incorrecto() {
		VendingMachineLine VendingMachineLineActual = new VendingMachineLine(5, VendingMachineLineExtra1);
		VendingMachineLineActual.setIdlinea(-1);
	}
	
	
	
	
	
	@Test
	public void test_getQuantity_Correcto1() {		
		assertNotNull(VendingMachineLineExtra4);
		assertEquals(0, VendingMachineLineExtra4.getQuantity());
	}
	
	@Test
	public void test_getQuantity_Correcto2() {		
		assertNotNull(VendingMachineLineExtra2);
		assertEquals(100000, VendingMachineLineExtra2.getQuantity());
	}
	
	
	
	
	
	@Test
	public void test_setQuantity_Correcto1() {
		VendingMachineLine VendingMachineLineActual = new VendingMachineLine(5, VendingMachineLineExtra1);
		VendingMachineLineActual.setQuantity(0);
		
		assertNotNull(VendingMachineLineActual);
		assertEquals(0, VendingMachineLineActual.getQuantity());
	}
	
	@Test
	public void test_setQuantity_Correcto2() {
		VendingMachineLine VendingMachineLineActual = new VendingMachineLine(5, VendingMachineLineExtra2);
		VendingMachineLineActual.setQuantity(100000);
		
		assertNotNull(VendingMachineLineActual);
		assertEquals(100000, VendingMachineLineActual.getQuantity());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_setQuantity_Negative_Incorrecto() {
		VendingMachineLine VendingMachineLineActual = new VendingMachineLine(5, VendingMachineLineExtra1);
		VendingMachineLineActual.setQuantity(-1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_setQuantity_Excesive_Incorrecto() {
		VendingMachineLine VendingMachineLineActual = new VendingMachineLine(5, VendingMachineLineExtra1);
		VendingMachineLineActual.setQuantity(101);
	}
	
	
	
	
	
	@Test
	public void test_getMaxQuantity_Correcto1() {		
		assertNotNull(VendingMachineLineExtra5);
		assertEquals(1, VendingMachineLineExtra5.getMaxQuantity());
	}
	
	@Test
	public void test_getMaxQuantity_Correcto2() {		
		assertNotNull(VendingMachineLineExtra2);
		assertEquals(100000, VendingMachineLineExtra2.getMaxQuantity());
	}
	
	
	
	
	
	@Test
	public void test_getProducto_Correcto() {		
		assertNotNull(VendingMachineLineExtra5);
		assertNotSame(Producto, VendingMachineLineExtra5.getProducto());
		assertEquals(Producto.getName(), VendingMachineLineExtra5.getProducto().getName());
		assertEquals(Producto.getPrecio(), VendingMachineLineExtra5.getProducto().getPrecio(), 0);
		assertEquals(Producto.getCadu(), VendingMachineLineExtra5.getProducto().getCadu());
		assertEquals(Producto.getUpc(), VendingMachineLineExtra5.getProducto().getUpc());
	}
	
	
	
	
	
	@Test
	public void test_setProducto_Correcto() {
		VendingMachineLine VendingMachineLineActual = new VendingMachineLine(5, VendingMachineLineExtra1);
		Product nuevoProducto = new Product("CocaCola", 1, "1-1-2022", "100000000007");
		VendingMachineLineActual.setProducto(nuevoProducto);
		
		assertNotNull(VendingMachineLineActual);
		assertNotSame(nuevoProducto, VendingMachineLineActual.getProducto());
		assertEquals(nuevoProducto.getName(), VendingMachineLineActual.getProducto().getName());
		assertEquals(nuevoProducto.getPrecio(), VendingMachineLineActual.getProducto().getPrecio(), 0);
		assertEquals(nuevoProducto.getCadu(), VendingMachineLineActual.getProducto().getCadu());
		assertEquals(nuevoProducto.getUpc(), VendingMachineLineActual.getProducto().getUpc());
	}
	
	
	@Test (expected = NullPointerException.class)
	public void test_setProducto_Null_Incorrecto() {
		VendingMachineLine VendingMachineLineActual = new VendingMachineLine(5, VendingMachineLineExtra1);
		VendingMachineLineActual.setProducto(null);
	}
	
	// Fin de pruebas sobre getters y setters
}
