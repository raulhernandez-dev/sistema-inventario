import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Pruebas de la funcionalidad Producto (fase TEST).
 * Cada metodo corresponde a un caso de prueba CP01 - CP07.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductoTest {

    private Producto producto;
    private final ByteArrayOutputStream salida = new ByteArrayOutputStream();
    private PrintStream salidaOriginal;

    @Before
    public void prepararDatos() {
        producto = new Producto("P001", "Teclado", 350, 10);
        salidaOriginal = System.out;
        System.setOut(new PrintStream(salida));
    }

    @After
    public void restaurarSalida() {
        System.setOut(salidaOriginal);
    }

    /** Ejecuta mostrarInformacion() y regresa lo que se imprimio en pantalla. */
    private String textoMostrado(Producto p) {
        salida.reset();
        p.mostrarInformacion();
        return salida.toString();
    }

    @Test
    public void cp01_crearProducto() {
        assertNotNull("El producto no se creo", producto);
        assertEquals("P001", producto.getCodigo());
        assertEquals("Teclado", producto.getNombre());
        assertEquals(350.0, producto.getPrecio(), 0.001);
        assertEquals(10, producto.getExistencia());
    }

    @Test
    public void cp02_mostrarCodigo() {
        assertEquals("P001", producto.getCodigo());
        assertTrue("No se muestra el codigo P001", textoMostrado(producto).contains("P001"));
    }

    @Test
    public void cp03_mostrarNombre() {
        assertEquals("Teclado", producto.getNombre());
        assertTrue("No se muestra el nombre Teclado", textoMostrado(producto).contains("Teclado"));
    }

    @Test
    public void cp04_mostrarPrecio() {
        assertEquals(350.0, producto.getPrecio(), 0.001);
        String texto = textoMostrado(producto);
        assertTrue("No se muestra el precio 350.00. Se mostro:\n" + texto, texto.contains("$350.00"));
    }

    @Test
    public void cp05_mostrarExistencia() {
        assertEquals(10, producto.getExistencia());
        assertTrue("No se muestra la existencia 10", textoMostrado(producto).contains("10 unidades"));
    }

    @Test
    public void cp06_mostrarInformacionCompleta() {
        String texto = textoMostrado(producto);
        assertTrue("Falta el codigo", texto.contains("Codigo:     P001"));
        assertTrue("Falta el nombre", texto.contains("Nombre:     Teclado"));
        assertTrue("Falta el precio", texto.contains("Precio:     $350.00"));
        assertTrue("Falta la existencia", texto.contains("Existencia: 10 unidades"));
    }

    @Test
    public void cp07_crearSegundoProducto() {
        Producto mouse = new Producto("P002", "Mouse", 180, 5);
        assertEquals("P002", mouse.getCodigo());
        assertEquals("Mouse", mouse.getNombre());
        assertEquals(180.0, mouse.getPrecio(), 0.001);
        assertEquals(5, mouse.getExistencia());
        // El primer producto no debe cambiar al crear el segundo
        assertEquals("P001", producto.getCodigo());
        assertEquals("Teclado", producto.getNombre());
        String texto = textoMostrado(mouse);
        assertTrue("Datos del segundo producto mal mostrados. Se mostro:\n" + texto,
                texto.contains("P002") && texto.contains("Mouse") && texto.contains("$180.00"));
        assertTrue("Se mezclaron los datos de los productos", !texto.contains("Teclado"));
    }
}
