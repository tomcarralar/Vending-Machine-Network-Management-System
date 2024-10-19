## Descripción
Este proyecto es parte de la segunda entrega de prácticas del curso 2021/22 de Programación Orientada a Objetos en la Universidad de Valladolid. El objetivo de esta práctica es ampliar y mejorar el sistema de gestión para la red de máquinas vending de la empresa VendingCo, integrando nuevas funcionalidades y optimizaciones.

## Supuesto Práctico
La empresa VendingCo ha decidido aplicar la funcionalidad desarrollada previamente a todo su negocio en España, con ligeros cambios y ampliaciones, incluyendo la gestión por provincias y la venta de packs de productos con descuentos.

## Funcionalidad Requerida
Clase VendingSystem
- Añadir una nueva sede.
- Eliminar una sede.
- Conocer el número de máquinas vending en una sede a partir de su identificador.
- Obtener la lista de máquinas vending de una sede.
- Conocer el número de provincias gestionadas.
- Obtener los nombres de todas las provincias con una sede.
- Obtener una lista con la cantidad de máquinas vending gestionadas en cada provincia.

Clase VendingCity
  - Identificación por un código de provincia.
  - Almacenar el nombre de la provincia y las máquinas vending de dicha provincia.
  - Adaptación de la funcionalidad de la clase VendingSystem de la práctica 1.

Clase VendingMachine
- Almacenar objetos de tipo Vendible, una abstracción de Pack y Product.
- Funcionalidad similar a la práctica 1, adaptada para manejar Vendible.

Clase Vendible
- Abstracción común a Product y Pack.
- Atributos mínimos: nombre, precio e identificador único (UPC en el caso de Product).

Clase Pack
- Inicialización basada en un array de Producto (Producto[]).
  
## Estructura del Proyecto
El proyecto debe contener las siguientes clases y sus correspondientes clases de prueba (JUnit 4):

- VendingSystem
- VendingCity
- VendingMachine
- Clase abstracta Vendible
- Pack
- Product
- VendingSystemTest
- VendingMachineTest
- VendibleTest
- PackTest
