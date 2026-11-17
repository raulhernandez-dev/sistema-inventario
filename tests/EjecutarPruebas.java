import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

/**
 * Ejecuta las pruebas de ProductoTest con JUnit y muestra un reporte
 * con el resultado de cada caso de prueba (CP01 - CP07).
 * Termina con codigo 1 si alguna prueba falla (para GitHub Actions).
 */
public class EjecutarPruebas {

    private static final Map<String, String> CASOS = new LinkedHashMap<>();
    static {
        CASOS.put("cp01_crearProducto", "CP01 - Crear producto");
        CASOS.put("cp02_mostrarCodigo", "CP02 - Mostrar codigo");
        CASOS.put("cp03_mostrarNombre", "CP03 - Mostrar nombre");
        CASOS.put("cp04_mostrarPrecio", "CP04 - Mostrar precio");
        CASOS.put("cp05_mostrarExistencia", "CP05 - Mostrar existencia");
        CASOS.put("cp06_mostrarInformacionCompleta", "CP06 - Mostrar informacion");
        CASOS.put("cp07_crearSegundoProducto", "CP07 - Crear segundo producto");
    }

    public static void main(String[] args) {
        JUnitCore junit = new JUnitCore();
        Map<String, String> estado = new LinkedHashMap<>();
        Map<String, String> mensajes = new LinkedHashMap<>();

        junit.addListener(new RunListener() {
            @Override
            public void testFinished(Description d) {
                estado.putIfAbsent(d.getMethodName(), "CORRECTO");
            }

            @Override
            public void testFailure(Failure f) {
                estado.put(f.getDescription().getMethodName(), "FALLO");
                String m = f.getMessage() != null ? f.getMessage() : f.getException().toString();
                mensajes.put(f.getDescription().getMethodName(), m);
            }
        });

        Result r = junit.run(ProductoTest.class);

        System.out.println("RESULTADOS DE LAS PRUEBAS");
        System.out.println("-------------------------------------------");
        for (Map.Entry<String, String> caso : CASOS.entrySet()) {
            String e = estado.getOrDefault(caso.getKey(), "NO EJECUTADA");
            System.out.printf("%-32s %s%n", caso.getValue(), e);
            if (mensajes.containsKey(caso.getKey())) {
                System.out.println("    -> " + mensajes.get(caso.getKey()).replace("\n", "\n       "));
            }
        }
        int correctas = r.getRunCount() - r.getFailureCount();
        System.out.println("-------------------------------------------");
        System.out.println("Resultado: " + correctas + " de " + r.getRunCount() + " pruebas satisfactorias.");

        System.exit(r.wasSuccessful() ? 0 : 1);
    }
}
