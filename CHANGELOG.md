# Historial de versiones

Todas las versiones liberadas del Sistema de Inventario.

## v1.1.0 - 2026-11-17

- Registro del catalogo de productos desde `datos/productos.csv` en `BuscadorProductos`.
- Pruebas CP01 - CP07 satisfactorias.

## v1.0.2 - 2026-11-17

- Correccion del error en el precio mostrado por `mostrarInformacion()`.
- Validacion satisfactoria de la funcionalidad Producto (7 de 7 pruebas).
- Paquete `sistema-inventario-v1.0.2.zip` generado y publicado automaticamente.

## v1.0.1 - 2026-11-17 (no publicada)

- Version con un error intencional en el precio mostrado.
- Las pruebas CP04, CP06 y CP07 fallaron durante la liberacion.
- El workflow se detuvo y no se publico la version.

## v1.0.0 - 2026-11-17

- Primera version del Sistema de Inventario.
- Incorporacion de la funcionalidad Producto (codigo, nombre, precio y existencia).
- Busqueda de productos (`BuscadorProductos`).
- Implementacion de pruebas automatizadas con JUnit 4 (CP01 - CP07).
- Workflows de build, pruebas y liberacion con GitHub Actions.
