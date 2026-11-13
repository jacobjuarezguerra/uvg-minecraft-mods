# Instalación y problemas frecuentes

La versión del proyecto utiliza Minecraft Java 1.21.1, Java 21, NeoForge 21.1.248 como mínimo, University Guide 1.1.8 y Posted Signage 1.7.5.

## Preparar la instancia

1. Seleccionar en el launcher la instancia de Minecraft 1.21.1 con NeoForge y Java 21.
2. Abrir la carpeta de esa instancia desde el launcher. Cada perfil puede tener su propia carpeta `mods` y sus propios mundos.
3. Cerrar el juego. Guardar aparte los JAR anteriores y colocar una sola versión de cada mod en `mods`.
4. En multijugador, instalar esas mismas versiones en el servidor y en los clientes.
5. Iniciar el juego y comprobar que ambos mods aparecen en la lista.

## Si el juego no inicia

| Mensaje o síntoma | Qué revisar |
| --- | --- |
| Se requiere otra versión de NeoForge | Leer la versión actual indicada en el mensaje y comprobar el perfil elegido en el launcher. |
| Mod duplicado | Retirar de `mods` el JAR anterior del mismo mod. Conservar una copia fuera de esa carpeta. |
| Versión de Java incompatible | Configurar Java 21 en la instancia, no solo instalarlo en Windows. |
| Incompatibilidad al entrar al servidor | Comparar Minecraft, NeoForge y versiones de ambos mods entre cliente y servidor. |
| El cambio no aparece | Comprobar que se actualizó la instancia que realmente se está iniciando y reiniciar el cliente. |

Si el error continúa, conservar `logs/latest.log` y el informe más reciente de `crash-reports`, si existe. Anotar qué se estaba haciendo y las versiones utilizadas para poder reproducirlo.

[Descarga e instalación del mundo](../mundos/) · [Volver al inicio](../README.md)
