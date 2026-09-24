# University Guide

Mod de guía turística para Minecraft Java 1.21.1 y NeoForge.

El NPC guía camina mediante pathfinding hacia paradas invisibles configurables y presenta información al grupo cuando llega.

## Desarrollo

Requiere Java 21. En Windows:

```powershell
.\validate_assets.ps1
.\gradlew.bat build
.\gradlew.bat runClient
```

El validador comprueba JSON, referencias de modelos y paridad entre las traducciones en inglés y español.

El JAR compilado se genera en `build/libs/`.
