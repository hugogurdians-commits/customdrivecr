# CustomDrive CR

CustomDrive CR es una aplicación web desarrollada en Spring Boot para la gestión y venta de accesorios automotrices compatibles con diferentes vehículos. El sistema permite seleccionar un vehículo, visualizar accesorios compatibles, agregar productos al carrito, confirmar pedidos y administrar la información desde un panel interno.

## Descripción del proyecto

El proyecto tiene como objetivo resolver una necesidad común en tiendas de accesorios para vehículos: ayudar al cliente a encontrar productos compatibles con su carro específico.

El sistema permite administrar marcas, modelos, vehículos, accesorios, compatibilidades, usuarios, pedidos y reportes de ventas. Además, cuenta con una interfaz cliente donde el usuario puede seleccionar su vehículo, ver productos compatibles, revisar detalles, agregar productos al carrito y generar un pedido.

## Tecnologías utilizadas

- Java 17
- Spring Boot
- Spring MVC
- Spring Data JPA
- Thymeleaf
- MySQL
- Tailwind CSS
- Maven
- Visual Studio Code

## Módulos principales

### Cliente

- Página de inicio.
- Selección de vehículo.
- Catálogo de accesorios compatibles.
- Buscador de productos.
- Detalle de producto.
- Carrito de compras.
- Confirmación de pedido.
- Factura.
- Historial de pedidos del cliente.

### Administración

- Inicio de sesión.
- Panel administrativo.
- Gestión de marcas.
- Gestión de modelos.
- Gestión de vehículos.
- Gestión de accesorios.
- Gestión de compatibilidades.
- Gestión de usuarios.
- Gestión de pedidos.
- Reporte de ventas.

## Funcionalidades destacadas

- Login con roles de usuario.
- Protección de rutas administrativas.
- Protección de rutas de compra.
- Visualización de productos por compatibilidad con vehículo.
- Carrito de compras con validación de stock.
- Registro de pedidos.
- Reducción automática de inventario al confirmar una compra.
- Reporte de ventas.
- Formato visual de precios y fechas.
- Subida de imágenes locales para vehículos.
- Interfaz clara, moderna y responsiva.

## Base de datos

La base de datos utilizada es MySQL.

El script SQL se encuentra en la carpeta:

```text
database/