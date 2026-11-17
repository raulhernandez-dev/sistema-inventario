import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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

    /**
     * Registra los productos de un archivo CSV (codigo,nombre,precio,existencia).
     * La primera linea es el encabezado.
     */
    public void cargarDesdeArchivo(Path archivo) throws IOException {
        List<String> lineas = Files.readAllLines(archivo);
        for (String linea : lineas.subList(1, lineas.size())) {
            String[] c = linea.split(",");
            agregar(new Producto(c[0], c[1], Double.parseDouble(c[2]), Integer.parseInt(c[3])));
        }
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

    public static void main(String[] args) throws IOException {
        BuscadorProductos buscador = new BuscadorProductos();
        // v1.1.0: el catalogo inicial se registra desde datos/productos.csv
        buscador.cargarDesdeArchivo(Path.of("datos", "productos.csv"));
        System.out.println("Productos registrados: " + buscador.productos.size());

        System.out.println();
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
