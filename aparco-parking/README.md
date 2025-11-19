# AparcoCLI – Sistema de Gestión de Parqueaderos

### Proyecto Final – Estructuras de Datos

**Autores:** *Dennis Franco – Nicolas Arenas*
**Lenguaje:** Java 17
**Interfaz:** Swing (GUI)
**Paradigma:** Programación Orientada a Objetos + Estructuras de Datos

---

## Descripción general

**AparcoCLI** es un sistema de escritorio desarrollado en **Java** que permite gestionar un parqueadero mediante una interfaz gráfica moderna construida con **Swing**.

Este proyecto fue diseñado para demostrar el dominio de las **estructuras de datos vistas en clase**, incluyendo:

* Arreglos estáticos
* Listas dinámicas
* Pilas
* Colas
* Árboles (BST)
* Montículos (Min-Heap)
* Búsqueda lineal y binaria
* Ordenamientos: Burbuja, Inserción, QuickSort, MergeSort
* Recursividad

La aplicación permite:

* Registrar vehículos en cola de entrada
* Asignar plazas de forma automática o directa
* Registrar salidas
* Generar reportes ordenados
* Buscar tickets mediante búsqueda binaria
* Exportar e importar historial en CSV
* Configurar tarifas
* Ver próximos en salir (heap)
* Buscar vehículo dentro (BST)
* Deshacer acciones (stack)

---

## 🎯 Objetivo del proyecto

Crear un sistema de administración de parqueaderos que aplique **todas las estructuras de datos del curso**, usando una interfaz gráfica funcional que permita demostrar su uso real en un contexto práctico.

---

## 🚀 Características principales

### ✔️ Interfaz gráfica moderna en Swing

* Nada funciona por consola.
* Todo es visual, con ventanas, botones, listas y paneles interactivos.

### ✔️ Registro de vehículos

* Llegada → Cola FIFO
* Asignación automática → Toma el primero en la cola
* Asignación directa → Puedes elegir una plaza libre

### ✔️ Gestión de plazas

* Array estático `Plaza[]`
* Cada plaza muestra: LIBRE / OCUPADA + placa

### ✔️ Tickets con fecha formateada

Formato usado en toda la UI:
`Mar, 16 Nov 13:00 pm`

### ✔️ Árbol BST

* Búsqueda rápida de vehículos dentro por placa
* Inserción y eliminación cuando entran/salen

### ✔️ Min-Heap

* Identifica los **próximos vehículos en salir**

### ✔️ Pilas (Stack) – UNDO

* Deshacer última acción de entrada o salida

### ✔️ Colas (Queue)

* Cola de entrada de vehículos
* Cola de tickets cerrados

### ✔️ Reportes avanzados

Puedes ordenar por:

* Placa
* Valor pagado
* Hora de entrada

Usando:

* Burbuja
* Inserción
* QuickSort
* MergeSort

Incluye búsqueda binaria.

### ✔️ Exportación e importación CSV

Respaldar e importar historial de tickets.

---

## 🧩 Arquitectura del proyecto

```
src/
  app/
    gui/                     <- Interfaz gráfica (Swing)
      SwingApp.java
  domain/                    <- Entidades principales
      Plaza.java
      Vehiculo.java
      Ticket.java
  services/                  <- Lógica de negocio
      ParkingService.java
      TicketExporter.java
      TicketImporter.java
  datastructures/           <- Implementación de estructuras
      SimpleQueue.java
      SimpleStack.java
      LinkedList.java
      BST.java
      MinHeap.java
```

---

## 📚 Estructuras de datos utilizadas

| Estructura           | Ubicación      | Uso en el sistema                      |
| -------------------- | -------------- | -------------------------------------- |
| **Arreglo estático** | Plaza[]        | Estado del parqueadero                 |
| **Cola (Queue)**     | SimpleQueue    | Vehículos en espera + tickets cerrados |
| **Pila (Stack)**     | SimpleStack    | Funcionalidad UNDO                     |
| **Lista dinámica**   | LinkedList     | Historial, reportes                    |
| **BST**              | BST.java       | Vehículos dentro (búsqueda rápida)     |
| **Min-Heap**         | MinHeap.java   | Próximos en salir                      |
| **Recursividad**     | BST, MergeSort | Recorridos y ordenamientos             |
| **Ordenamientos**    | services       | Reportes avanzados                     |
| **Búsqueda binaria** | services       | Buscar ticket en reporte ordenado      |

---

## 🧠 Requerimientos funcionales (resumen)

* RF01 – Registrar llegada
* RF02 – Asignar plaza (auto)
* RF03 – Asignación directa
* RF04 – Registrar salida
* RF05 – Buscar vehículo dentro
* RF06 – Ver próximos en salir
* RF07 – Ver estado de plazas
* RF08 – Ver cola de entrada
* RF09 – Deshacer acción (undo)
* RF10 – Configurar tarifa
* RF11 – Exportar CSV
* RF12 – Importar CSV
* RF13 – Reporte ordenado
* RF14 – Búsqueda binaria en reportes

(Completo en la Wiki)

---

## 🧠 Requerimientos no funcionales

* Portabilidad con Java 17
* Uso obligatorio de estructuras del curso
* Interfaz visual amigable
* Código estructurado en paquetes
* Sin bases de datos (solo CSV)
* Documentación en Wiki
* Control de versiones con GitHub

(Completo en la Wiki)

---

## 👤 Historias de usuario

Las 10 historias de usuario están documentadas en la Wiki:

* Registrar llegada
* Asignar plaza auto/directa
* Registrar salida
* Buscar dentro
* Ver próximos
* Cambiar tarifa
* Generar reporte
* Búsqueda binaria
* Import/export CSV
* UNDO

---

## 📐 Diagrama UML (ER)

```
Vehiculo (placa PK, tipo)
  1 ---- N
         Ticket (idTicket PK, placa FK, idPlaza FK, entrada, salida, valor, abierto)
  1 ---- N
Plaza (idPlaza PK, estado)

Configuracion (idConfig PK, tarifaHora)
  1 ---- N Ticket
```

---

## 💻 Capturas de ejemplo (simuladas para README)

> Estado de plazas
> Cola de entrada
> Datos del ticket
> Reportes ordenados

*(Puedes agregar imágenes reales una vez ejecutes la app.)*

---

## Cómo ejecutar el proyecto

### 1. Compilar:

```bash
javac -d out $(find src -name "*.java")
```

### 2. Ejecutar:

```bash
java -cp out app.gui.SwingApp
```

---

## Pruebas manuales sugeridas

* Ingresar placas varias veces en cola
* Asignar automáticamente
* Asignar directamente
* Registrar salida con cálculo correcto
* Exportar e importar CSV
* Ver próximos en salir
* Buscar dentro
* Probar UNDO
* Generar reporte con QuickSort vs Burbuja
* Realizar búsqueda binaria

---

## Créditos

Proyecto desarrollado como entrega final para la asignatura **Estructuras de Datos**, demostrando la aplicación práctica de múltiples estructuras y algoritmos en un sistema real.
