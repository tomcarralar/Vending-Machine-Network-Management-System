package vendingco;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * Proporciona todas las pruebas de la clase VendingMachineLine
 * 
 * @author tomcarr
 * @author javiram
 */
public class VendingMachineLineTest {
	
	Product productoGalletas; 
	VendingMachineLine vendingMachineLineExtraProducto1;
	VendingMachineLine vendingMachineLineExtraProducto2;
	VendingMachineLine vendingMachineLineExtraProducto3;
	VendingMachineLine vendingMachineLineExtraProducto4;
	VendingMachineLine vendingMachineLineExtraProducto5;

	Product productoCocaCola; 
	Product productoPatatas; 
	Pack pack;
	ArrayList <Product> listaPack;
	VendingMachineLine vendingMachineLineExtraPack1;
	VendingMachineLine vendingMachineLineExtraPack2;
	VendingMachineLine vendingMachineLineExtraPack3;
	VendingMachineLine vendingMachineLineExtraPack4;
	VendingMachineLine vendingMachineLineExtraPack5;
	
	@Before
    public void init() {
		productoGalletas = new Product("galletas", 2.3, "1-1-2025", "112345678902"); 
		vendingMachineLineExtraProducto1 = new VendingMachineLine(0, productoGalletas, 100, 50);
		vendingMachineLineExtraProducto2 = new VendingMachineLine(1, productoGalletas, 100000, 100000);
		vendingMachineLineExtraProducto3 = new VendingMachineLine(100000, productoGalletas, 5, 4);
		vendingMachineLineExtraProducto4 = new VendingMachineLine(2, productoGalletas, 100000, 0);
		vendingMachineLineExtraProducto5 = new VendingMachineLine(3, productoGalletas, 1, 0);
		
		
		productoCocaCola = new Product("CocaCola", 1, "1-1-2029", "112345678902");
		productoPatatas = new Product("PatatasFritas", 2.5, "1-1-2023", "999999999993");
		listaPack = new ArrayList<>(); 
		listaPack.add(productoCocaCola); 
		listaPack.add(productoPatatas);
		pack = new Pack("PackPrueba", listaPack, 0); 
		vendingMachineLineExtraPack1 = new VendingMachineLine(0, pack, 100, 50);
		vendingMachineLineExtraPack2 = new VendingMachineLine(1, pack, 100000, 100000);
		vendingMachineLineExtraPack3 = new VendingMachineLine(100000, pack, 5, 4);
		vendingMachineLineExtraPack4 = new VendingMachineLine(2, pack, 100000, 0);
		vendingMachineLineExtraPack5 = new VendingMachineLine(3, pack, 1, 0);
    }
	
	// Pruebas sobre lineas con productos
	
	// Comienzo de pruebas sobre constuctores
	
	@Test
	public void productTestConstructorVendingMachineLineCorrecto() {
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(0, productoGalletas, 1);
		
		assertNotNull(vendingMachineLineActual);
		assertEquals(0, vendingMachineLineActual.getIdlinea());
		
		assertEquals(Product.class, vendingMachineLineActual.getVendible().getClass());
		Product productoMaquina = (Product)	vendingMachineLineActual.getVendible();
		assertNotSame(productoGalletas, productoMaquina);
		assertEquals(productoGalletas.getUpc(), productoMaquina.getUpc());
		
		assertEquals(0, vendingMachineLineActual.getQuantity());
		assertEquals(1, vendingMachineLineActual.getMaxQuantity());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void productTestConstructorVendingMachineLineIdIncorrecto() {
		@SuppressWarnings("unused")
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(-1, productoGalletas, 10);
	}
	
	@Test (expected = NullPointerException.class)
	public void productTestConstructorVendingMachineLineNullProductIncorrecto() {
		@SuppressWarnings("unused")
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(1, null, 10);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void productTestConstructorVendingMachineLineCeroMaxQuantityIncorrecto() {
		@SuppressWarnings("unused")
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(1, productoGalletas, 0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void productTestConstructorVendingMachineLineNegativeMaxQuantityIncorrecto() {
		@SuppressWarnings("unused")
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(1, productoGalletas, -1);
	}
	
	
	
	
	
	@Test
	public void productTestConstructorconCantidadVendingMachineLineCorrecto1() {
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(0, productoGalletas, 1, 0);
		
		assertNotNull(vendingMachineLineActual);
		assertEquals(0, vendingMachineLineActual.getIdlinea());
				
		assertEquals(Product.class, vendingMachineLineActual.getVendible().getClass());
		Product productoMaquina = (Product)	vendingMachineLineActual.getVendible();
		assertNotSame(productoGalletas, productoMaquina);
		assertEquals(productoGalletas.getUpc(), productoMaquina.getUpc());
		
		assertEquals(0, vendingMachineLineActual.getQuantity());
		assertEquals(1, vendingMachineLineActual.getMaxQuantity());
	}
	
	@Test
	public void productTestConstructorconCantidadVendingMachineLineCorrecto2() {
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(7, productoGalletas, 1, 1);
		
		assertNotNull(vendingMachineLineActual);
		assertEquals(7, vendingMachineLineActual.getIdlinea());

		assertEquals(Product.class, vendingMachineLineActual.getVendible().getClass());
		Product productoMaquina = (Product)	vendingMachineLineActual.getVendible();
		assertNotSame(productoGalletas, productoMaquina);
		assertEquals(productoGalletas.getUpc(), productoMaquina.getUpc());
		
		assertEquals(1, vendingMachineLineActual.getQuantity());
		assertEquals(1, vendingMachineLineActual.getMaxQuantity());
	}
	
	@Test
	public void productTestConstructorconCantidadVendingMachineLineCorrecto3() {
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(123456789, productoGalletas, 100, 100);
		
		assertNotNull(vendingMachineLineActual);
		assertEquals(123456789, vendingMachineLineActual.getIdlinea());

		assertEquals(Product.class, vendingMachineLineActual.getVendible().getClass());
		Product productoMaquina = (Product)	vendingMachineLineActual.getVendible();
		assertNotSame(productoGalletas, productoMaquina);
		assertEquals(productoGalletas.getUpc(), productoMaquina.getUpc());
		
		assertEquals(100, vendingMachineLineActual.getQuantity());
		assertEquals(100, vendingMachineLineActual.getMaxQuantity());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void productTestConstructorconCantidadVendingMachineLineIdIncorrecto() {
		@SuppressWarnings("unused")
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(-1, productoGalletas, 10, 5);
	}
	
	@Test (expected = NullPointerException.class)
	public void productTestConstructorconCantidadVendingMachineLineNullProductIncorrecto() {
		@SuppressWarnings("unused")
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(1, null, 10, 5);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void productTestConstructorconCantidadVendingMachineLineCeroMaxQuantityIncorrecto() {
		@SuppressWarnings("unused")
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(1, productoGalletas, 0, 5);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void productTestConstructorconCantidadVendingMachineLineNegativeMaxQuantityIncorrecto() {
		@SuppressWarnings("unused")
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(1, productoGalletas, -1, 5);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void productTestConstructorconCantidadVendingMachineLineNegativeQuantityIncorrecto() {
		@SuppressWarnings("unused")
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(1, productoGalletas, 10, -1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void productTestConstructorconCantidadVendingMachineLineExcesiveQuantityIncorrecto() {
		@SuppressWarnings("unused")
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(1, productoGalletas, 99, 100);
	}
	
	
	
	
	
	@Test
	public void productTestConstructorCopiaVendingMachineLineCorrecto1() {
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(0, vendingMachineLineExtraProducto1);
		
		assertNotNull(vendingMachineLineActual);
		assertEquals(0, vendingMachineLineActual.getIdlinea());

		assertEquals(Product.class, vendingMachineLineActual.getVendible().getClass());
		Product productoMaquina = (Product)	vendingMachineLineActual.getVendible();
		assertNotSame(productoGalletas, productoMaquina);
		assertEquals(productoGalletas.getUpc(), productoMaquina.getUpc());
		
		assertEquals(50, vendingMachineLineActual.getQuantity());
		assertEquals(100, vendingMachineLineActual.getMaxQuantity());
	}
	
	@Test
	public void productTestConstructorCopiaVendingMachineLineCorrecto2() {
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(1, vendingMachineLineExtraProducto1);
		
		assertNotNull(vendingMachineLineActual);
		assertEquals(1, vendingMachineLineActual.getIdlinea());

		assertEquals(Product.class, vendingMachineLineActual.getVendible().getClass());
		Product productoMaquina = (Product)	vendingMachineLineActual.getVendible();
		assertNotSame(productoGalletas, productoMaquina);
		assertEquals(productoGalletas.getUpc(), productoMaquina.getUpc());
		
		assertEquals(50, vendingMachineLineActual.getQuantity());
		assertEquals(100, vendingMachineLineActual.getMaxQuantity());
	}
		
	@Test (expected = IllegalArgumentException.class)
	public void productTestConstructorCopiaVendingMachineLineIdIncorrecto() {
		@SuppressWarnings("unused")
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(-1, vendingMachineLineExtraProducto1);
	}
	
	@Test (expected = NullPointerException.class)
	public void productTestConstructorconCantidadVendingMachineLineNullVendingMachineLineIncorrecto() {
		@SuppressWarnings("unused")
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(1, null);
	}	
		
	// Fin de pruebas sobre constuctores
	
	
	

	//Comienzo de pruebas sobre getters y setters
	@Test
	public void productTestgetIdCorrecto1() {		
		assertNotNull(vendingMachineLineExtraProducto1);
		assertEquals(0, vendingMachineLineExtraProducto1.getIdlinea());
	}
	
	@Test
	public void productTestgetIdCorrecto2() {		
		assertNotNull(vendingMachineLineExtraProducto3);
		assertEquals(100000, vendingMachineLineExtraProducto3.getIdlinea());
	}
	
	
	
	
	
	@Test
	public void productTestsetIdCorrecto1() {
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(5, vendingMachineLineExtraProducto1);
		vendingMachineLineActual.setIdlinea(0);
		
		assertNotNull(vendingMachineLineActual);
		assertEquals(0, vendingMachineLineActual.getIdlinea());
	}
	
	@Test
	public void productTestsetIdCorrecto2() {
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(5, vendingMachineLineExtraProducto1);
		vendingMachineLineActual.setIdlinea(50);
		
		assertNotNull(vendingMachineLineActual);
		assertEquals(50, vendingMachineLineActual.getIdlinea());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void productTestsetIdNegativeIncorrecto() {
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(5, vendingMachineLineExtraProducto1);
		vendingMachineLineActual.setIdlinea(-1);
	}
	
	
	
	
	
	@Test
	public void productTestgetQuantityCorrecto1() {		
		assertNotNull(vendingMachineLineExtraProducto4);
		assertEquals(0, vendingMachineLineExtraProducto4.getQuantity());
	}
	
	@Test
	public void productTestgetQuantityCorrecto2() {		
		assertNotNull(vendingMachineLineExtraProducto2);
		assertEquals(100000, vendingMachineLineExtraProducto2.getQuantity());
	}
	
	
	
	
	
	@Test
	public void productTestsetQuantityCorrecto1() {
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(5, vendingMachineLineExtraProducto1);
		vendingMachineLineActual.setQuantity(0);
		
		assertNotNull(vendingMachineLineActual);
		assertEquals(0, vendingMachineLineActual.getQuantity());
	}
	
	@Test
	public void productTestsetQuantityCorrecto2() {
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(5, vendingMachineLineExtraProducto2);
		vendingMachineLineActual.setQuantity(100000);
		
		assertNotNull(vendingMachineLineActual);
		assertEquals(100000, vendingMachineLineActual.getQuantity());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void productTestsetQuantityNegativeIncorrecto() {
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(5, vendingMachineLineExtraProducto1);
		vendingMachineLineActual.setQuantity(-1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void productTestsetQuantityExcesiveIncorrecto() {
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(5, vendingMachineLineExtraProducto1);
		vendingMachineLineActual.setQuantity(101);
	}
	
	
	
	
	
	@Test
	public void productTestgetMaxQuantityCorrecto1() {		
		assertNotNull(vendingMachineLineExtraProducto5);
		assertEquals(1, vendingMachineLineExtraProducto5.getMaxQuantity());
	}
	
	@Test
	public void productTestgetMaxQuantityCorrecto2() {		
		assertNotNull(vendingMachineLineExtraProducto2);
		assertEquals(100000, vendingMachineLineExtraProducto2.getMaxQuantity());
	}
	
	
	
	
	
	@Test
	public void productTestgetVendibleCorrecto() {		
		assertNotNull(vendingMachineLineExtraProducto5);

		assertEquals(Product.class, vendingMachineLineExtraProducto5.getVendible().getClass());
		Product productoMaquina = (Product)	vendingMachineLineExtraProducto5.getVendible();
				
		assertNotSame(productoGalletas, productoMaquina);
		assertEquals(productoGalletas.getName(), productoMaquina.getName());
		assertEquals(productoGalletas.getPrecio(), productoMaquina.getPrecio(), 0);
		assertEquals(productoGalletas.getCadu(), productoMaquina.getCadu());
		assertEquals(productoGalletas.getUpc(), productoMaquina.getUpc());
	}
	
	
	
	
	
	@Test
	public void productTestsetVendibleCorrecto() {
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(5, vendingMachineLineExtraProducto1);
		Product nuevoProducto = new Product("CocaCola", 1, "1-1-2022", "100000000007");
		vendingMachineLineActual.setVendible(nuevoProducto);
		
		assertNotNull(vendingMachineLineActual);
		
		assertEquals(Product.class, vendingMachineLineActual.getVendible().getClass());
		Product productoMaquina = (Product)	vendingMachineLineActual.getVendible();
		
		assertNotSame(nuevoProducto, productoMaquina);
		assertEquals(nuevoProducto.getName(), productoMaquina.getName());
		assertEquals(nuevoProducto.getPrecio(), productoMaquina.getPrecio(), 0);
		assertEquals(nuevoProducto.getCadu(), productoMaquina.getCadu());
		assertEquals(nuevoProducto.getUpc(), productoMaquina.getUpc());
	}
	
	
	@Test (expected = NullPointerException.class)
	public void productTestsetVendibleNullIncorrecto() {
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(5, vendingMachineLineExtraProducto1);
		vendingMachineLineActual.setVendible(null);
	}
	// Fin de pruebas sobre getters y setters
	
	// Fin de pruebas sobre lineas con productos
		
	/* ---------------------------------------------------------------------------------------------------------------------------- */	
		
	// Pruebas sobre lineas con packs
	
	// Comienzo de pruebas sobre constuctores
	
	@Test
	public void packTestConstructorVendingMachineLineCorrecto() {
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(0, pack, 1);
		
		assertNotNull(vendingMachineLineActual);
		assertEquals(0, vendingMachineLineActual.getIdlinea());
		
		assertEquals(Pack.class, vendingMachineLineActual.getVendible().getClass());
		Pack packMaquina = (Pack) vendingMachineLineActual.getVendible();
		assertNotSame(pack, packMaquina);
		pack.equals(packMaquina);
		
		assertEquals(0, vendingMachineLineActual.getQuantity());
		assertEquals(1, vendingMachineLineActual.getMaxQuantity());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void packTestConstructorVendingMachineLineIdIncorrecto() {
		@SuppressWarnings("unused")
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(-1, pack, 10);
	}
	
	@Test (expected = NullPointerException.class)
	public void packTestConstructorVendingMachineLineNullProductIncorrecto() {
		@SuppressWarnings("unused")
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(1, null, 10);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void packTestConstructorVendingMachineLineCeroMaxQuantityIncorrecto() {
		@SuppressWarnings("unused")
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(1, pack, 0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void packTestConstructorVendingMachineLineNegativeMaxQuantityIncorrecto() {
		@SuppressWarnings("unused")
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(1, pack, -1);
	}
	
	
	
	
	
	@Test
	public void packTestConstructorconCantidadVendingMachineLineCorrecto1() {
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(0, pack, 1, 0);
		
		assertNotNull(vendingMachineLineActual);
		assertEquals(0, vendingMachineLineActual.getIdlinea());

		assertEquals(Pack.class, vendingMachineLineActual.getVendible().getClass());
		Pack packMaquina = (Pack) vendingMachineLineActual.getVendible();
		assertNotSame(pack, packMaquina);
		pack.equals(packMaquina);
		
		assertEquals(0, vendingMachineLineActual.getQuantity());
		assertEquals(1, vendingMachineLineActual.getMaxQuantity());
	}
	
	@Test
	public void packTestConstructorconCantidadVendingMachineLineCorrecto2() {
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(7, pack, 1, 1);
		
		assertNotNull(vendingMachineLineActual);
		assertEquals(7, vendingMachineLineActual.getIdlinea());

		assertEquals(Pack.class, vendingMachineLineActual.getVendible().getClass());
		Pack packMaquina = (Pack) vendingMachineLineActual.getVendible();
		assertNotSame(pack, packMaquina);
		pack.equals(packMaquina);
		
		assertEquals(1, vendingMachineLineActual.getQuantity());
		assertEquals(1, vendingMachineLineActual.getMaxQuantity());
	}
	
	@Test
	public void packTestConstructorconCantidadVendingMachineLineCorrecto3() {
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(123456789, pack, 100, 100);
		
		assertNotNull(vendingMachineLineActual);
		assertEquals(123456789, vendingMachineLineActual.getIdlinea());

		assertEquals(Pack.class, vendingMachineLineActual.getVendible().getClass());
		Pack packMaquina = (Pack) vendingMachineLineActual.getVendible();
		assertNotSame(pack, packMaquina);
		pack.equals(packMaquina);
		
		assertEquals(100, vendingMachineLineActual.getQuantity());
		assertEquals(100, vendingMachineLineActual.getMaxQuantity());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void packTestConstructorconCantidadVendingMachineLineIdIncorrecto() {
		@SuppressWarnings("unused")
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(-1, pack, 10, 5);
	}
	
	@Test (expected = NullPointerException.class)
	public void packTestConstructorconCantidadVendingMachineLineNullProductIncorrecto() {
		@SuppressWarnings("unused")
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(1, null, 10, 5);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void packTestConstructorconCantidadVendingMachineLineCeroMaxQuantityIncorrecto() {
		@SuppressWarnings("unused")
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(1, pack, 0, 5);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void packTestConstructorconCantidadVendingMachineLineNegativeMaxQuantityIncorrecto() {
		@SuppressWarnings("unused")
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(1, pack, -1, 5);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void packTestConstructorconCantidadVendingMachineLineNegativeQuantityIncorrecto() {
		@SuppressWarnings("unused")
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(1, pack, 10, -1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void packTestConstructorconCantidadVendingMachineLineExcesiveQuantityIncorrecto() {
		@SuppressWarnings("unused")
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(1, pack, 99, 100);
	}
	
	
	
	
	
	@Test
	public void packTestConstructorCopiaVendingMachineLineCorrecto1() {
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(0, vendingMachineLineExtraPack1);
		
		assertNotNull(vendingMachineLineActual);
		assertEquals(0, vendingMachineLineActual.getIdlinea());

		assertEquals(Pack.class, vendingMachineLineActual.getVendible().getClass());
		Pack packMaquina = (Pack) vendingMachineLineActual.getVendible();
		assertNotSame(pack, packMaquina);
		pack.equals(packMaquina);
		
		assertEquals(50, vendingMachineLineActual.getQuantity());
		assertEquals(100, vendingMachineLineActual.getMaxQuantity());
	}
	
	@Test
	public void packTestConstructorCopiaVendingMachineLineCorrecto2() {
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(1, vendingMachineLineExtraPack1);
		
		assertNotNull(vendingMachineLineActual);
		assertEquals(1, vendingMachineLineActual.getIdlinea());

		assertEquals(Pack.class, vendingMachineLineActual.getVendible().getClass());
		Pack packMaquina = (Pack) vendingMachineLineActual.getVendible();
		assertNotSame(pack, packMaquina);
		pack.equals(packMaquina);
		
		assertEquals(50, vendingMachineLineActual.getQuantity());
		assertEquals(100, vendingMachineLineActual.getMaxQuantity());
	}
		
	@Test (expected = IllegalArgumentException.class)
	public void packTestConstructorCopiaVendingMachineLineIdIncorrecto() {
		@SuppressWarnings("unused")
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(-1, vendingMachineLineExtraPack1);
	}
	
	@Test (expected = NullPointerException.class)
	public void packTestConstructorconCantidadVendingMachineLineNullVendingMachineLineIncorrecto() {
		@SuppressWarnings("unused")
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(1, null);
	}	
		
	// Fin de pruebas sobre constuctores
	
	
	

	//Comienzo de pruebas sobre getters y setters
	@Test
	public void packTestgetIdCorrecto1() {		
		assertNotNull(vendingMachineLineExtraPack1);
		assertEquals(0, vendingMachineLineExtraPack1.getIdlinea());
	}
	
	@Test
	public void packTestgetIdCorrecto2() {		
		assertNotNull(vendingMachineLineExtraPack3);
		assertEquals(100000, vendingMachineLineExtraPack3.getIdlinea());
	}
	
	
	
	
	
	@Test
	public void packTestsetIdCorrecto1() {
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(5, vendingMachineLineExtraPack1);
		vendingMachineLineActual.setIdlinea(0);
		
		assertNotNull(vendingMachineLineActual);
		assertEquals(0, vendingMachineLineActual.getIdlinea());
	}
	
	@Test
	public void packTestsetIdCorrecto2() {
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(5, vendingMachineLineExtraPack1);
		vendingMachineLineActual.setIdlinea(50);
		
		assertNotNull(vendingMachineLineActual);
		assertEquals(50, vendingMachineLineActual.getIdlinea());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void packTestsetIdNegativeIncorrecto() {
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(5, vendingMachineLineExtraPack1);
		vendingMachineLineActual.setIdlinea(-1);
	}
	
	
	
	
	
	@Test
	public void packTestgetQuantityCorrecto1() {		
		assertNotNull(vendingMachineLineExtraPack4);
		assertEquals(0, vendingMachineLineExtraPack4.getQuantity());
	}
	
	@Test
	public void packTestgetQuantityCorrecto2() {		
		assertNotNull(vendingMachineLineExtraPack2);
		assertEquals(100000, vendingMachineLineExtraPack2.getQuantity());
	}
	
	
	
	
	
	@Test
	public void packTestsetQuantityCorrecto1() {
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(5, vendingMachineLineExtraPack1);
		vendingMachineLineActual.setQuantity(0);
		
		assertNotNull(vendingMachineLineActual);
		assertEquals(0, vendingMachineLineActual.getQuantity());
	}
	
	@Test
	public void packTestsetQuantityCorrecto2() {
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(5, vendingMachineLineExtraPack2);
		vendingMachineLineActual.setQuantity(100000);
		
		assertNotNull(vendingMachineLineActual);
		assertEquals(100000, vendingMachineLineActual.getQuantity());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void packTestsetQuantityNegativeIncorrecto() {
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(5, vendingMachineLineExtraPack1);
		vendingMachineLineActual.setQuantity(-1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void packTestsetQuantityExcesiveIncorrecto() {
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(5, vendingMachineLineExtraPack1);
		vendingMachineLineActual.setQuantity(101);
	}
	
	
	
	
	
	@Test
	public void packTestgetMaxQuantityCorrecto1() {		
		assertNotNull(vendingMachineLineExtraPack5);
		assertEquals(1, vendingMachineLineExtraPack5.getMaxQuantity());
	}
	
	@Test
	public void packTestgetMaxQuantityCorrecto2() {		
		assertNotNull(vendingMachineLineExtraPack2);
		assertEquals(100000, vendingMachineLineExtraPack2.getMaxQuantity());
	}
	
	
	
	
	
	@Test
	public void packTestgetVendibleCorrecto() {		
		assertNotNull(vendingMachineLineExtraPack5);		

		assertEquals(Pack.class, vendingMachineLineExtraPack5.getVendible().getClass());
		Pack packMaquina = (Pack) vendingMachineLineExtraPack5.getVendible();
		
		assertNotSame(pack, packMaquina);
		assertEquals(pack.getName(), packMaquina.getName());
		assertEquals(pack.getCadu(), packMaquina.getCadu());
		assertEquals(pack.getPrecio(), packMaquina.getPrecio(), 0);
		pack.equals(packMaquina);
	}
	
	
	
	
	
	@Test
	public void packTestsetVendibleCorrecto() {
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(5, vendingMachineLineExtraPack1);
		Product nuevoProducto = new Product("CocaCola", 1, "1-1-2022", "100000000007");
		Product nuevoProducto2 = new Product("CocaCola", 1, "2-1-2022", "112345678902");
		ArrayList<Product> tmp = new ArrayList<>();
		tmp.add(nuevoProducto);
		tmp.add(nuevoProducto2);
		Pack nuevoPack = new Pack("CocaColas", tmp, 1);
		vendingMachineLineActual.setVendible(nuevoPack);
		
		assertNotNull(vendingMachineLineActual);
		
		assertEquals(Pack.class, vendingMachineLineActual.getVendible().getClass());
		Pack packMaquina = (Pack) vendingMachineLineActual.getVendible();
		
		assertNotSame(nuevoPack, packMaquina);
		assertEquals(nuevoPack.getName(), packMaquina.getName());
		assertEquals(nuevoPack.getCadu(), packMaquina.getCadu());
		assertEquals(nuevoPack.getPrecio(), packMaquina.getPrecio(), 0);
		nuevoPack.equals(packMaquina);
	}
	
	
	@Test (expected = NullPointerException.class)
	public void packTestsetVendibleNullIncorrecto() {
		VendingMachineLine vendingMachineLineActual = new VendingMachineLine(5, vendingMachineLineExtraPack1);
		vendingMachineLineActual.setVendible(null);
	}
	
	// Fin de pruebas sobre getters y setters

	// Fin de pruebas sobre lineas con packs
	
	// Pruebas sobre los metodos restantes
	
	// Pruebas HashCode
	
	@Test
	public void testHashCodeProductosIguales() {
		VendingMachineLine vendingMachineLine1 = new VendingMachineLine(0, productoGalletas, 100, 50);
		VendingMachineLine vendingMachineLine2 = new VendingMachineLine(0, productoGalletas, 100, 50);
		int codigo1 = vendingMachineLine1.hashCode();
		int codigo2 = vendingMachineLine2.hashCode();
		
		assertNotNull(vendingMachineLine1);
		assertNotNull(vendingMachineLine2);
		assertNotSame(vendingMachineLine1, vendingMachineLine2);
		
		assertEquals(codigo1, codigo2, 0);
	}
	
	@Test
	public void testHashCodeProductosDistintosId() {
		VendingMachineLine vendingMachineLine1 = new VendingMachineLine(0, productoGalletas, 100, 50);
		VendingMachineLine vendingMachineLine2 = new VendingMachineLine(1, productoGalletas, 100, 50);
		int codigo1 = vendingMachineLine1.hashCode();
		int codigo2 = vendingMachineLine2.hashCode();
		
		assertNotNull(vendingMachineLine1);
		assertNotNull(vendingMachineLine2);
		assertNotSame(vendingMachineLine1, vendingMachineLine2);
		
		assertNotEquals(codigo1, codigo2, 0);
	}
	
	@Test
	public void testHashCodeProductosDistintosProductos() {
		VendingMachineLine vendingMachineLine1 = new VendingMachineLine(0, productoGalletas, 100, 50);
		VendingMachineLine vendingMachineLine2 = new VendingMachineLine(0, productoPatatas, 100, 50);
		int codigo1 = vendingMachineLine1.hashCode();
		int codigo2 = vendingMachineLine2.hashCode();
		
		assertNotNull(vendingMachineLine1);
		assertNotNull(vendingMachineLine2);
		assertNotSame(vendingMachineLine1, vendingMachineLine2);
		
		assertNotEquals(codigo1, codigo2, 0);
	}
	
	@Test
	public void testHashCodeProductosDistintosMaxQuantity() {
		VendingMachineLine vendingMachineLine1 = new VendingMachineLine(0, productoGalletas, 100, 50);
		VendingMachineLine vendingMachineLine2 = new VendingMachineLine(0, productoGalletas, 90, 50);
		int codigo1 = vendingMachineLine1.hashCode();
		int codigo2 = vendingMachineLine2.hashCode();
		
		assertNotNull(vendingMachineLine1);
		assertNotNull(vendingMachineLine2);
		assertNotSame(vendingMachineLine1, vendingMachineLine2);
		
		assertNotEquals(codigo1, codigo2, 0);
	}
	
	@Test
	public void testHashCodeProductosDistintosQuantity() {
		VendingMachineLine vendingMachineLine1 = new VendingMachineLine(0, productoGalletas, 100, 50);
		VendingMachineLine vendingMachineLine2 = new VendingMachineLine(0, productoGalletas, 100, 90);
		int codigo1 = vendingMachineLine1.hashCode();
		int codigo2 = vendingMachineLine2.hashCode();
		
		assertNotNull(vendingMachineLine1);
		assertNotNull(vendingMachineLine2);
		assertNotSame(vendingMachineLine1, vendingMachineLine2);
		
		assertNotEquals(codigo1, codigo2, 0);
	}
	
	
	
	@Test
	public void testHashCodePacksIguales() {
		VendingMachineLine vendingMachineLine1 = new VendingMachineLine(100000, pack, 5, 4);
		VendingMachineLine vendingMachineLine2 = new VendingMachineLine(100000, pack, 5, 4);
		int codigo1 = vendingMachineLine1.hashCode();
		int codigo2 = vendingMachineLine2.hashCode();
		
		assertNotNull(vendingMachineLine1);
		assertNotNull(vendingMachineLine2);
		assertNotSame(vendingMachineLine1, vendingMachineLine2);
		
		assertEquals(codigo1, codigo2, 0);
	}
	
	@Test
	public void testHashCodePacksDistintosId() {
		VendingMachineLine vendingMachineLine1 = new VendingMachineLine(100000, pack, 5, 4);
		VendingMachineLine vendingMachineLine2 = new VendingMachineLine(100001, pack, 5, 4);
		int codigo1 = vendingMachineLine1.hashCode();
		int codigo2 = vendingMachineLine2.hashCode();
		
		assertNotNull(vendingMachineLine1);
		assertNotNull(vendingMachineLine2);
		assertNotSame(vendingMachineLine1, vendingMachineLine2);
		
		assertNotEquals(codigo1, codigo2, 0);
	}
	
	@Test
	public void testHashCodePacksDistintosPack() {
		VendingMachineLine vendingMachineLine1 = new VendingMachineLine(100000, pack, 5, 4);
		Product nuevoProducto = new Product("CocaCola", 1, "1-1-2022", "100000000007");
		Product nuevoProducto2 = new Product("CocaCola", 1, "2-1-2022", "112345678902");
		ArrayList<Product> tmp = new ArrayList<>();
		tmp.add(nuevoProducto);
		tmp.add(nuevoProducto2);
		Pack nuevoPack = new Pack("CocaColas", tmp, 1);
		VendingMachineLine vendingMachineLine2 = new VendingMachineLine(100000, nuevoPack, 5, 4);
		int codigo1 = vendingMachineLine1.hashCode();
		int codigo2 = vendingMachineLine2.hashCode();
		
		assertNotNull(vendingMachineLine1);
		assertNotNull(vendingMachineLine2);
		assertNotSame(vendingMachineLine1, vendingMachineLine2);
		
		assertNotEquals(codigo1, codigo2, 0);
	}
	
	@Test
	public void testHashCodePacksDistintosMaxQuantity() {
		VendingMachineLine vendingMachineLine1 = new VendingMachineLine(100000, pack, 5, 4);
		VendingMachineLine vendingMachineLine2 = new VendingMachineLine(100000, pack, 6, 4);
		int codigo1 = vendingMachineLine1.hashCode();
		int codigo2 = vendingMachineLine2.hashCode();
		
		assertNotNull(vendingMachineLine1);
		assertNotNull(vendingMachineLine2);
		assertNotSame(vendingMachineLine1, vendingMachineLine2);
		
		assertNotEquals(codigo1, codigo2, 0);
	}
	
	@Test
	public void testHashCodePacksDistintosQuantity() {
		VendingMachineLine vendingMachineLine1 = new VendingMachineLine(100000, pack, 5, 4);
		VendingMachineLine vendingMachineLine2 = new VendingMachineLine(100000, pack, 5, 1);
		int codigo1 = vendingMachineLine1.hashCode();
		int codigo2 = vendingMachineLine2.hashCode();
		
		assertNotNull(vendingMachineLine1);
		assertNotNull(vendingMachineLine2);
		assertNotSame(vendingMachineLine1, vendingMachineLine2);
		
		assertNotEquals(codigo1, codigo2, 0);
	}
	

	@Test
	public void testHashCodeProductoPack() {
		VendingMachineLine vendingMachineLine1 = new VendingMachineLine(0, productoGalletas, 100, 50);
		VendingMachineLine vendingMachineLine2 = new VendingMachineLine(0, pack, 100, 50);
		int codigo1 = vendingMachineLine1.hashCode();
		int codigo2 = vendingMachineLine2.hashCode();
		
		assertNotNull(vendingMachineLine1);
		assertNotNull(vendingMachineLine2);
		assertNotSame(vendingMachineLine1, vendingMachineLine2);
		
		assertNotEquals(codigo1, codigo2, 0);
	}
	// Fin test HashCode

	
	// Pruebas Equals
	@Test
	public void testEqualsLineObjectProduct() {
		Object obj = new Object();
		VendingMachineLine vendingMachineLine1 = new VendingMachineLine(0, productoGalletas, 100, 50);
		
		assertNotNull(vendingMachineLine1);
		assertFalse(vendingMachineLine1.equals(obj));
	}
	
	@Test
	public void testEqualsLineNullProduct() {
		VendingMachineLine vendingMachineLine1 = new VendingMachineLine(0, productoGalletas, 100, 50);

		assertNotNull(vendingMachineLine1);
		assertFalse(vendingMachineLine1.equals(null));
	}
	

	@Test
	public void testEqualsMismaLineaProduct() {
		VendingMachineLine vendingMachineLine1 = new VendingMachineLine(0, productoGalletas, 100, 50);

		assertNotNull(vendingMachineLine1);
		assertTrue(vendingMachineLine1.equals(vendingMachineLine1));
	}
	

	
	@Test
	public void testEqualsLineasIgualesProduct() {
		VendingMachineLine vendingMachineLine1 = new VendingMachineLine(0, productoGalletas, 100, 50);
		VendingMachineLine vendingMachineLine2 = new VendingMachineLine(0, productoGalletas, 100, 50);

		assertNotNull(vendingMachineLine1);
		assertTrue(vendingMachineLine1.equals(vendingMachineLine2));
	}
	
	@Test
	public void testEqualsLineasDistntintoIdProduct() {
		VendingMachineLine vendingMachineLine1 = new VendingMachineLine(0, productoGalletas, 100, 50);
		VendingMachineLine vendingMachineLine2 = new VendingMachineLine(1, productoGalletas, 100, 50);

		assertNotNull(vendingMachineLine1);
		assertFalse(vendingMachineLine1.equals(vendingMachineLine2));
	}
	
	@Test
	public void testEqualsLineasDistntintoProductoProduct() {
		VendingMachineLine vendingMachineLine1 = new VendingMachineLine(0, productoGalletas, 100, 50);
		VendingMachineLine vendingMachineLine2 = new VendingMachineLine(1, productoPatatas, 100, 50);

		assertNotNull(vendingMachineLine1);
		assertFalse(vendingMachineLine1.equals(vendingMachineLine2));
	}
	
	@Test
	public void testEqualsLineasDistntintoMaxQuantityProduct() {
		VendingMachineLine vendingMachineLine1 = new VendingMachineLine(0, productoGalletas, 100, 50);
		VendingMachineLine vendingMachineLine2 = new VendingMachineLine(0, productoGalletas, 90, 50);

		assertNotNull(vendingMachineLine1);
		assertFalse(vendingMachineLine1.equals(vendingMachineLine2));
	}
	

	@Test
	public void testEqualsLineasDistntintoQuantityProduct() {
		VendingMachineLine vendingMachineLine1 = new VendingMachineLine(0, productoGalletas, 100, 50);
		VendingMachineLine vendingMachineLine2 = new VendingMachineLine(0, productoGalletas, 100, 90);

		assertNotNull(vendingMachineLine1);
		assertFalse(vendingMachineLine1.equals(vendingMachineLine2));
	}
	
	
	@Test
	public void testEqualsLineObjectPack() {
		Object obj = new Object();
		VendingMachineLine vendingMachineLine1 = new VendingMachineLine(0, pack, 100, 50);
		
		assertNotNull(vendingMachineLine1);
		assertFalse(vendingMachineLine1.equals(obj));
	}
	
	@Test
	public void testEqualsLineNullPack() {
		VendingMachineLine vendingMachineLine1 = new VendingMachineLine(0, pack, 100, 50);

		assertNotNull(vendingMachineLine1);
		assertFalse(vendingMachineLine1.equals(null));
	}
	

	@Test
	public void testEqualsMismaLineaPack() {
		VendingMachineLine vendingMachineLine1 = new VendingMachineLine(0, pack, 100, 50);

		assertNotNull(vendingMachineLine1);
		assertTrue(vendingMachineLine1.equals(vendingMachineLine1));
	}


	@Test
	public void testEqualsLineasIgualesPack() {
		VendingMachineLine vendingMachineLine1 = new VendingMachineLine(100000, pack, 5, 4);
		VendingMachineLine vendingMachineLine2 = new VendingMachineLine(100000, pack, 5, 4);

		assertNotNull(vendingMachineLine1);
		assertTrue(vendingMachineLine1.equals(vendingMachineLine2));
	}
	
	@Test
	public void testEqualsLineasDistintoIdPack() {
		VendingMachineLine vendingMachineLine1 = new VendingMachineLine(100000, pack, 5, 4);
		VendingMachineLine vendingMachineLine2 = new VendingMachineLine(100001, pack, 5, 4);

		assertNotNull(vendingMachineLine1);
		assertFalse(vendingMachineLine1.equals(vendingMachineLine2));
	}
	
	@Test
	public void testEqualsLineasDistintoPackPack() {
		VendingMachineLine vendingMachineLine1 = new VendingMachineLine(100000, pack, 5, 4);
		Product nuevoProducto = new Product("CocaCola", 1, "1-1-2022", "100000000007");
		Product nuevoProducto2 = new Product("CocaCola", 1, "2-1-2022", "112345678902");
		ArrayList<Product> tmp = new ArrayList<Product>();
		tmp.add(nuevoProducto);
		tmp.add(nuevoProducto2);
		Pack nuevoPack = new Pack("CocaColas", tmp, 1);
		VendingMachineLine vendingMachineLine2 = new VendingMachineLine(100000, nuevoPack, 5, 4);

		assertNotNull(vendingMachineLine1);
		assertFalse(vendingMachineLine1.equals(vendingMachineLine2));
	}
	
	@Test
	public void testEqualsLineasDistintoMaxQuantityPack() {
		VendingMachineLine vendingMachineLine1 = new VendingMachineLine(100000, pack, 6, 4);
		VendingMachineLine vendingMachineLine2 = new VendingMachineLine(100000, pack, 5, 4);

		assertNotNull(vendingMachineLine1);
		assertFalse(vendingMachineLine1.equals(vendingMachineLine2));
	}
	

	@Test
	public void testEqualsLineasDistintoQuantityPack() {
		VendingMachineLine vendingMachineLine1 = new VendingMachineLine(100000, pack, 5, 4);
		VendingMachineLine vendingMachineLine2 = new VendingMachineLine(100000, pack, 5, 3);

		assertNotNull(vendingMachineLine1);
		assertFalse(vendingMachineLine1.equals(vendingMachineLine2));
	}
	
	
	@Test
	public void testEqualsProductPack() {
		VendingMachineLine vendingMachineLine1 = new VendingMachineLine(0, productoGalletas, 100, 50);
		VendingMachineLine vendingMachineLine2 = new VendingMachineLine(0, pack, 100, 50);
			
		assertNotNull(vendingMachineLine1);
		assertFalse(vendingMachineLine1.equals(vendingMachineLine2));
	}
	// Fin pruebas equals

	
	// Fin pruebas sobre los metodos restantes
}
