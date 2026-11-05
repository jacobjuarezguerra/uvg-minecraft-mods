# UVG Minecraft Mods

## Entrega actual

- [Informe completo en PDF](5ta%20entrega/Informe%20del%20proyecto.pdf)
- [Cuatro videos y retroalimentación](5ta%20entrega/VIDEOS.md)

La carpeta `5ta entrega` contiene únicamente el PDF y el Markdown de videos. `entrega-5` conserva material de la versión anterior y el registro de pruebas.

## Versiones y uso

Minecraft Java 1.21.1, Java 21 y NeoForge 21.1.248 o superior. También se verifica 21.1.250.

- University Guide **1.1.6**: guía permanente, paradas, barreras, libro de instrucciones, descripciones en el menú y pantalla inicial UVG.
- Posted Signage **1.7.3**: señalización, vidrio, puertas, herramientas de construcción y objetos de interiores.

El libro se entrega una vez por jugador. `/universityguide book` permite recuperarlo sin permisos de operador. El menú del guía muestra nombre, distancia y una descripción breve. La nueva pantalla conserva las acciones de Minecraft y ofrece el menú clásico.

Actualizar University Guide tanto en cliente como servidor: el protocolo de destinos ahora incluye descripciones. El equipo confirmó el recorrido/mapa preparados y definió el guía permanente como flujo definitivo.

## Compilar y verificar

Ejecutar `powershell -NoProfile -ExecutionPolicy Bypass -File herramientas/compilar.ps1`.

Pruebas locales: **33 GameTests de University Guide y 65 de Posted Signage por versión**, 98 en total, con NeoForge .248 y .250. Los JAR de Guide resultan idénticos entre ambas compilaciones. Las pruebas de puertas CIT necesitan ambos mods. No se atribuyen mejoras concretas de FPS sin una medición comparable.

Código en `mods/`; fuentes de Blockbench y texturas en `modelos/posted-signage/`.
