package uniandes.dpoo.hamburguesas.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ComboTest {
	
	
	 private static Combo combo1;
	 private static ProductoMenu hamburguesa;
	 private static ProductoMenu papas;
	 private static ArrayList<ProductoMenu> productos;
	 

	 	@BeforeAll
	 	static void init( ) throws Exception
	    {
	 		 hamburguesa = new ProductoMenu("Hamburguesa", 20000);
	 		 papas = new ProductoMenu("Papas", 5000);
	 		 productos = new ArrayList<ProductoMenu>();
	 		 productos.add(hamburguesa);
	 		 productos.add(papas);
	 		 
	    }
	 	
	    @BeforeEach
	    void setUp( ) throws Exception
	    {
	        combo1 = new Combo ("Combo hamburguesa y papas", 0.15, productos);
	    }

	    @AfterEach
	    void tearDown( ) throws Exception
	    {
	    }

	    @Test
	    void testGetNombre( )
	    {
	        assertEquals( "Combo hamburguesa y papas", combo1.getNombre( ), "El nombre del combo no es el esperado." );
	    }

	    @Test
	    void testGetPrecio( )
	    {
	        assertEquals( 21250, combo1.getPrecio( ), "El descuento del combo no es el esperado." );
	    }
	    
	    @Test
	    void testGenerarTextoFactura( )
	    {
	    	String factura = combo1.generarTextoFactura(); 
	    	assertTrue("No contiene el precio total correcto", factura.contains(String.valueOf(21250)));
	    	assertTrue("No contiene el valor del descuento correcto", factura.contains(String.valueOf(0.15)));
	    	assertTrue("No contiene el nombre del combo correcto", factura.contains("Combo hamburguesa y papas"));
	    }

	

}
