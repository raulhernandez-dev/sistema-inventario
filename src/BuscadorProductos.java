import java.util.ArrayList;
import java.util.List;

/**
 * Busqueda de productos del Sistema de Inventario.
 * Modulo: Busqueda de productos.
 */
public class BuscadorProductos {

    private final List<Producto> productos = new ArrayList<>();

    public void agregar(Producto producto) {
        productos.add(producto);
    }

    /** Busca un producto por su codigo exacto. Regresa null si no existe. */
    public Producto buscarPorCodigo(String codigo) {
        for (Producto p : productos) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                return p;
            }
        }
        return null;
    }

    /** Busca los productos cuyo nombre contenga el texto indicado. */
    public List<Producto> buscarPorNombre(String texto) {
        List<Producto> resultados = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getNombre().toLowerCase().contains(texto.toLowerCase())) {
                resultados.add(p);
            }
        }
        return resultados;
    }

    public static void main(String[] args) {
        BuscadorProductos buscador = new BuscadorProductos();
        buscador.agregar(new Producto("P001", "Teclado mecanico", 899.50, 25));
        buscador.agregar(new Producto("P002", "Mouse inalambrico", 349.00, 40));
        buscador.agregar(new Producto("P003", "Teclado inalambrico", 599.00, 12));

        System.out.println("Buscar por codigo P002:");
        Producto encontrado = buscador.buscarPorCodigo("P002");
        if (encontrado != null) {
            encontrado.mostrarInformacion();
        }

        System.out.println();
        System.out.println("Buscar por nombre \"teclado\":");
        for (Producto p : buscador.buscarPorNombre("teclado")) {
            p.mostrarInformacion();
        }
    }
}
