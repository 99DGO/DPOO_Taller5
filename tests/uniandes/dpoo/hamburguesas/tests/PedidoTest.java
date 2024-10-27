package uniandes.dpoo.hamburguesas.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class PedidoTest {
	
	private static Pedido pedido1;  
	private static ProductoMenu hamburguesa;
	private static ProductoMenu papas;
	private static Combo combo1;
	private static ArrayList<ProductoMenu> productos;
	private static File fileMalo;
	 
	@BeforeAll
 	static void init( ) throws Exception
    {
 		 hamburguesa = new ProductoMenu("Hamburguesa", 20000);
 		 papas = new ProductoMenu("Papas", 5000);
 		 
 		 productos = new ArrayList<ProductoMenu>();
 		 productos.add(hamburguesa);
 		 productos.add(papas);
 		 combo1=new Combo("Hamburguesa y papas", 0.1, productos);
 		 
    }
 	
    @BeforeEach
    void setUp( ) throws Exception
    {
		 pedido1 = new Pedido ("Pepito Perez", "Carerra 99 #999");

    }

    @AfterEach
    void tearDown( ) throws Exception
    {
    }

    @Test
    void testGetIdPedido( )
    {
    	int id = pedido1.getNumeroPedidos()-1;
        assertEquals( id, pedido1.getIdPedido( ), "El id del pedido no es el esperado." );
    }

    @Test
    void testGetNombreCliente( )
    {
        assertEquals( "Pepito Perez", pedido1.getNombreCliente( ), "El nombre del cliente no es el esperado." );
    }
    
    @Test
    void testAgregarProducto( )
    {
        pedido1.agregarProducto(combo1);
        pedido1.agregarProducto(hamburguesa);
        assertTrue("No contiene uno de los productos", pedido1.getProductos( ).contains(combo1) );
        assertTrue("No contiene uno de los productos", pedido1.getProductos( ).contains(hamburguesa) );

    }
    
    @Test
    void testGetPrecioTotalPedido( )
    {
        assertEquals( 0, pedido1.getPrecioTotalPedido( ), "El precio del combo no es el esperado." );
        
        pedido1.agregarProducto(combo1);
        pedido1.agregarProducto(hamburguesa);
        

        assertEquals( 50575, pedido1.getPrecioTotalPedido( ), "El precio del combo no es el esperado." );
    }
    
    @Test
    void testGenerarTextoFactura( )
    {
    	pedido1.agregarProducto(combo1);
        pedido1.agregarProducto(hamburguesa);
         
    	String factura = pedido1.generarTextoFactura(); 
    	assertTrue("No contiene el precio total correcto", factura.contains("Precio Total: 50575"));
    	assertTrue("No contiene el valor de IVA correcto", factura.contains("IVA:          8075"));
    	assertTrue("No contiene el valor neto correcto", factura.contains("Precio Neto:  42500"));
    }
    
    @Test
    void testGuardarFactura( )
    {
    	String nombreArchivo = "data/facturas/" + pedido1.getIdPedido( ) + "pruebaJunit"+".txt";
    	File file =new File( nombreArchivo );
    	
    	try
    	{
    		pedido1.guardarFactura( file );
    	}
    	catch (Exception e)
    	{
    		fail("No se creo el objeto del archivo"); 
    	}
    	
    	assertTrue("No existe el archivo (no quedo bien guardado)", file.exists());
    }
    
    @Test
    void testGuardarFacturaExceptions()
    {
    	try
    	{
    		pedido1.guardarFactura( null ); 
    		fail("Debio haber lanzado exception");
    	}
    	catch (Exception e)
    	{

    	}
        
    }
    

}
