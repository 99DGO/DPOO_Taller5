package uniandes.dpoo.hamburguesas.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoAjustadoTest {
	
	  private ProductoAjustado hamburguesaAjustada; 
	  private ProductoMenu hamburguesa= new ProductoMenu("Hamburguesa", 20000);
	  Ingrediente tomate = new Ingrediente("tomate", 1000);
	  Ingrediente cebolla = new Ingrediente ("cebolla", 900);

	    @BeforeEach
	    void setUp( ) throws Exception 
	    {
	    	hamburguesaAjustada = new ProductoAjustado(hamburguesa);
	    	hamburguesaAjustada.addIngrediente(tomate);
	    	hamburguesaAjustada.eliminarIngrediente(cebolla); 
	    }

	    @AfterEach
	    void tearDown( ) throws Exception 
	    {
	    } 
	    

	    @Test
	    void testGetNombre( )
	    {
	        assertEquals( "Hamburguesa", hamburguesaAjustada.getNombre( ), "El nombre del producto no es el esperado." );
	    }

	    @Test
	    void testGetPrecio( )
	    {
	        assertEquals( 21000, hamburguesaAjustada.getPrecio( ), "El costo del producto no es el esperado." );
	    }
	    
	    @Test
	    void testAddIngrediente( )
	    {
	    	ArrayList<Ingrediente> listaAgregada = new ArrayList();
	    	listaAgregada.add(tomate);
	    	assertTrue("No se añadio los ingredientes correctos adicionales", listaAgregada.equals(hamburguesaAjustada.getAgregados()));
	    }
	    
	    @Test
	    void testEliminarIngrediente( )
	    {
	    	ArrayList<Ingrediente> listaAgregada = new ArrayList();
	    	listaAgregada.add(cebolla);
	    	assertTrue("No se añadio los ingredientes correctos a la lista de eliminados", listaAgregada.equals(hamburguesaAjustada.getEliminados()));
	    }
	    
	    @Test
	    void testGenerarTextoFactura( )
	    {
	    	String factura = hamburguesaAjustada.generarTextoFactura();
	    	assertTrue("No contiene el precio total correcto", factura.contains(String.valueOf(21000)));
	    	assertTrue("No contiene el precio del ingrediente añadido", factura.contains(String.valueOf(1000)));
	    	assertTrue("No contiene el nombre del ingrediente añadido", factura.contains("tomate"));
	    	assertTrue("No contiene el nombre del ingrediente elminado", factura.contains("cebolla"));
	    }

}


