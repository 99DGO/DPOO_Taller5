package uniandes.dpoo.hamburguesas.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoMenuTest {

	private ProductoMenu hamburguesa;

    @BeforeEach
    void setUp( ) throws Exception
    {
        hamburguesa = new ProductoMenu( "Hamburguesa", 20000 );
    }

    @AfterEach
    void tearDown( ) throws Exception
    {
    }

    @Test
    void testGetNombre( )
    {
        assertEquals( "Hamburguesa", hamburguesa.getNombre( ), "El nombre del producto no es el esperado." );
    }

    @Test
    void testGetPrecio( )
    {
        assertEquals( 20000, hamburguesa.getPrecio( ), "El costo del producto no es el esperado." );
    }
    
    @Test
    void testGenerarTestoFactura( )
    {
    	String factura = hamburguesa.generarTextoFactura();
    	assertTrue("No contiene el precio", factura.contains(String.valueOf(20000)));
    	assertTrue("No contiene el nombre correcto", factura.contains("Hamburguesa"));
    }

}
