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

import uniandes.dpoo.hamburguesas.excepciones.IngredienteRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.NoHayPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.excepciones.ProductoFaltanteException;
import uniandes.dpoo.hamburguesas.excepciones.ProductoRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.YaHayUnPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.Restaurante;

public class RestauranteTest 
{
	private static Pedido pedido1;  
	private static Pedido pedido2;  
	private static ProductoMenu hamburguesa;
	private static ProductoMenu papas;
	private static Combo combo1;
	private static ArrayList<ProductoMenu> productos;
	private static Restaurante restaurant;
	
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
		 restaurant=new Restaurante();

    }

    @AfterEach
    void tearDown( ) throws Exception
    {
    }

    @Test
    void iniciarPedido( )
    {
    	try 
    	{
    		restaurant.iniciarPedido("Trey Clover", "Heatslabyul");

    	}
    	catch (Exception e)
    	{
    		fail("Deberia poder iniciar pedido");
    	}
    	
    	try
    	{
    		restaurant.iniciarPedido("Leona Kingscholar", "Savanaclaw");
    		fail("No deberia permitir otro pedido porque ya hay uno en curso");
    	}
    	catch (Exception e)
    	{
    		assertTrue("No saco la exception correcta", e instanceof YaHayUnPedidoEnCursoException);
    	}
    }

    @Test
    void testCerrarYGuardarPedido( )
    {
    	try
    	{
    		restaurant.cerrarYGuardarPedido();
    		fail("No deberia permitir cerrar pedido porque no hay uno en curso");
    	}
    	catch (Exception e)
    	{
    		assertTrue("No saco la exception correcta", e instanceof NoHayPedidoEnCursoException);
    	}
    	try  
    	{
    		restaurant.iniciarPedido("Trey Clover", "Heatslabyul");
    		restaurant.cerrarYGuardarPedido();
    	}
    	catch (Exception e)
    	{
    		System.out.println(e.getMessage());
    		fail("Deberia poder cerrar pedido");
    	}
    }
   
    @Test
    void testGetPedidoEnCurso( )
    {
        assertEquals( null, restaurant.getPedidoEnCurso( ), "No hay pedido en curso deberia retornar null" );

        
        try
        {
        	restaurant.iniciarPedido("Trey Clover", "Heatslabyul");
            assertEquals( "Trey Clover", restaurant.getPedidoEnCurso( ).getNombreCliente(), "No se inicio el pedido en curso correctamente" );
        }
        catch (Exception e)
        {
    		System.out.println(e.getMessage());
        	fail("No deberia lanzar error");
        }

       
    }
    
    @Test
    void testGetPedidos( )
    {
        
        try
        {
        	restaurant.iniciarPedido("Trey Clover", "Heatslabyul");
        	restaurant.cerrarYGuardarPedido();
        	restaurant.iniciarPedido("Leona Kingscholar", "Savanaclaw");
        	restaurant.cerrarYGuardarPedido();
        	
            assertEquals( 2, restaurant.getPedidos( ).size(), "Los pedidos cerrados no se guardaron bien" );

        }
        catch (Exception e)
        {
    		System.out.println(e.getMessage());
        	fail("No deberia lanzar error");
        }
        

    }
    
 
    @Test
    void testCargarInformacionRestaurante( )
    {
    	File archIngr =new File("data/ingreTest.txt"); 
    	File archComb =new File("data/combosTest.txt");
    	File archMenu =new File("data/menuTest.txt");

    	try 
    	{
            restaurant.cargarInformacionRestaurante(archIngr, archMenu, archComb);
            assertEquals( 4, restaurant.getMenuCombos( ).size(), "Los combos no se guardaron bien" );
            assertEquals( 15, restaurant.getIngredientes( ).size(), "Los ingredientes no se guardaron bien" );
            assertEquals( 22, restaurant.getMenuBase( ).size(), "Los productos del menu base no se guardaron bien" );
            

    	}
    	catch (Exception e)
    	{
    		System.out.println(e.getMessage());
        	fail("No deberia lanzar error");

    	}

    }
    
    @Test
    void testCargarInformacionRestauranteProductoFaltante( )
    {
    	File archIngr =new File("data/ingreTest.txt"); 
    	File archComb =new File("data/combosTestError.txt");
    	File archMenu =new File("data/menuTest.txt");

    	try 
    	{
            restaurant.cargarInformacionRestaurante(archIngr, archMenu, archComb);
            fail("Deberia sacar error por producto faltante");

    	}
    	catch (Exception e)
    	{
    		assertTrue("No saco la exception correcta", e instanceof ProductoFaltanteException);

    	}

    }
    
    @Test
    void testCargarInformacionRestauranteProductoRepetido( )
    {
    	File archIngr =new File("data/ingreTest.txt"); 
    	File archComb =new File("data/combosTest.txt");
    	File archMenu =new File("data/menuTestError.txt");

    	try 
    	{
            restaurant.cargarInformacionRestaurante(archIngr, archMenu, archComb);
            fail("Deberia sacar error por producto repetido");

    	}
    	catch (Exception e)
    	{
    		assertTrue("No saco la exception correcta", e instanceof ProductoRepetidoException);

    	}

    }
 
    @Test
    void testCargarInformacionRestauranteIngredienteRepetido( )
    {
    	File archIngr =new File("data/ingreTestError.txt"); 
    	File archComb =new File("data/combosTest.txt");
    	File archMenu =new File("data/menuTest.txt");

    	try 
    	{
            restaurant.cargarInformacionRestaurante(archIngr, archMenu, archComb);
            fail("Deberia sacar error por producto repetido");

    	}
    	catch (Exception e)
    	{
    		assertTrue("No saco la exception correcta", e instanceof IngredienteRepetidoException);

    	}

    }
 
 
    @Test
    void testGetMenuBase( )
    {
        assertEquals( 0, restaurant.getMenuBase( ).size(), "No deberían haber productos en el menu" );
    }
    
    @Test
    void testGetMenuCombos( )
    {
        assertEquals( 0, restaurant.getMenuCombos().size(), "No deberían haber combos en el menu" );
       
    }

    @Test
    void testGetIngredientes( )
    {
        assertEquals( 0, restaurant.getIngredientes().size(), "No deberían haber ingredientes en el menu" );
    }

}
