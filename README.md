# UVG Minecraft Mods

Proyecto para Minecraft Java 1.21.1 y NeoForge 21.1.248 o superior. También se verificó 21.1.250.

- `mods/university-guide/`: University Guide 1.1.5, navegación, paradas y barreras.
- `mods/posted-signage/`: Posted Signage 1.7.3, señalización, construcción y objetos de interiores.
- `modelos/posted-signage/`: fuentes editables de Blockbench y texturas.
- `entrega-5/`: informe Word/PDF, resultados y pendientes de cierre.

## Compilar

Requiere Java 21. Ejecutar `powershell -NoProfile -ExecutionPolicy Bypass -File herramientas/compilar.ps1`.

Las pruebas locales verificadas incluyen 27 casos del guía y 65 de Posted en cada versión de NeoForge, y 32 comprobaciones del cliente. Los casos del guía que requieren puertas CIT necesitan ambos mods; sin Posted se verifican 23 casos generales y se omiten 4 de compatibilidad.

## Estado de la quinta entrega

La muestra acordada con el profesor es de 4 usuarios. El equipo informa 3 pruebas realizadas; faltan la cuarta, las opiniones y los enlaces de YouTube. Se mantienen tres puntos del alcance original por cerrar: lobby con instrucciones, descripción breve en el menú y creación del guía al confirmar la selección. La versión actual reutiliza guías persistentes. La escalera concreta del campus y los FPS necesitan aceptación en el servidor actualizado.
