# UVG Minecraft Mods

Monorepo de mods para Minecraft Java 1.21.1 y NeoForge.

## Proyectos

- `mods/university-guide/`: NPC guía y paradas configurables para recorridos.
- `mods/posted-signage/`: señalización, letreros colgantes y elementos decorativos.

Cada proyecto incluye su propio Gradle Wrapper y sus instrucciones de compilación.

## Entrega 3

- Documento Word: [`entrega-3/Entrega 3 - UVG Minecraft Mods.docx`](entrega-3/Entrega%203%20-%20UVG%20Minecraft%20Mods.docx)
- Pendientes de cierre: [`entrega-3/PENDIENTES.md`](entrega-3/PENDIENTES.md)
- Guía oficial recibida: [`Recursos entrega 3/Guía de la Tercera Entrega del proyecto 2026.pdf`](Recursos%20entrega%203/Gu%C3%ADa%20de%20la%20Tercera%20Entrega%20del%20proyecto%202026.pdf)

El documento cubre requisitos priorizados, planificación, investigación de persistencia, implementación, control de versiones, gestión del tiempo y muestra a usuarios. Los formularios incluyen horarios estimados que cada integrante debe confirmar antes de entregar.

Para regenerar el Word:

```powershell
python -m pip install -r herramientas/requirements.txt
python herramientas/generar_entrega3.py
```

## Compilación

Para compilar ambos desde la raíz:

```powershell
powershell -NoProfile -ExecutionPolicy Bypass -File herramientas/compilar.ps1
```
