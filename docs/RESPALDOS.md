# Respaldar y restaurar el mundo

## Crear una copia

En una partida local, usar Guardar y salir y esperar a volver al menú. En un servidor, detenerlo normalmente y esperar a que termine de guardar. Copiar después la carpeta completa del mundo a otra ubicación y añadir la fecha a su nombre.

Conservar el respaldo antes de actualizar los mods o modificar paradas. Es preferible copiar la carpeta completa porque sus archivos dependen unos de otros:

| Archivo o carpeta | Contenido |
| --- | --- |
| `level.dat` | Configuración general de la partida. |
| `region/` | Bloques y entidades de bloque, incluidos textos y datos de paradas. |
| `entities/` | Entidades guardadas, como el guía y las texturas aplicadas. |
| `data/` | Índices y datos persistentes del mundo; contiene `universityguide_stops.dat`. |
| `playerdata/` | Datos individuales de los jugadores. |
| `datapacks/`, `DIM-1/`, `DIM1/` y otras dimensiones | Contenido adicional y datos de cada dimensión, cuando existan. |
| `icon.png` | Icono mostrado en la lista de mundos. |

`session.lock` es un archivo de bloqueo de la sesión y no es necesario incluirlo en un ZIP de distribución. No borrarlo para intentar editar una partida que sigue abierta.

## Restaurar una copia

1. Cerrar la partida o detener el servidor.
2. Conservar la carpeta actual con otro nombre, para poder volver a ella.
3. Descomprimir el respaldo como una carpeta independiente. Comprobar que `level.dat` esté directamente dentro de esa carpeta.
4. Colocarla en `saves` o en la ubicación configurada del servidor, sin mezclar sus regiones con las de otra copia.
5. Abrir con las versiones de los mods utilizadas por ese respaldo y comprobar el guía, los destinos y los textos.

Si Windows informa que el mundo está en uso, cerrar la instancia que lo abrió antes de copiar o reemplazar archivos.

[Mundo publicado](../mundos/) · [Volver al inicio](../README.md)
