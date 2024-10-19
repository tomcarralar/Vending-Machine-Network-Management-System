## Descripción
Este proyecto es parte de la primera entrega de prácticas del curso 2021/22 de Programación Orientada a Objetos en la Universidad de Valladolid. El objetivo de esta práctica es desarrollar un sistema de gestión para una red de máquinas vending de la empresa VendingCo.

## Supuesto Práctico
Las máquinas de vending necesitan una gestión eficiente para controlar productos, existencias y precios, además de aplicar estrategias de marketing basadas en la información de ventas. Por ello, se solicita programar las siguientes clases:
- VendingSystem: Gestión de todas las máquinas vending.
- VendingMachine: Funcionalidad de una máquina vending individual.
- Product: Representación de un producto.

## Funcionalidad Requerida

Clase VendingSystem
- Añadir una nueva máquina vending.
- Eliminar una máquina vending a partir de su identificador.
- Obtener una lista de todas las máquinas vending gestionadas.
- Saber el número de máquinas vending operativas.
- Obtener la lista de las máquinas vending con alguna línea vacía.

Clase VendingMachine
- Identificador único y estado (operativo o fuera de servicio).
- Cada máquina tiene varias líneas, cada una con un identificador único y productos del mismo tipo.
- Consultar el precio de un producto mediante el identificador de línea.
- Comprar un producto proporcionando el identificador de línea y una tarjeta monedero.

Clase Product
- Atributos: precio, fecha de consumo preferente, nombre y código UPC.
- Validación del UPC (número de 12 dígitos).

Clase TarjetaMonedero
- Métodos para consultar saldo, descontar saldo, crear una tarjeta con saldo inicial y cargar saldo en la tarjeta.
- Credenciales para modificar el saldo:
  - Saldo inicial, cargar saldo: A156Bv09_1zXo894
  - Descontar del saldo: 6Z1y00Nm31aA-571

## Estructura del Proyecto
El proyecto debe contener las siguientes clases y sus correspondientes clases de prueba (JUnit 4):

- VendingSystem
- VendingMachine
- Product
- VendingSystemTest
- VendingMachineTest
- ProductTest

