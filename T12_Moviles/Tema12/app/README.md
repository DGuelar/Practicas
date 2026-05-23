# 🎮 BreakoutGame — Tarea Final Tema 12

Juego Breakout (Arkanoid) desarrollado en Android nativo (Java) como tarea final del Tema 12 — Los Juegos en Android, del módulo de Programación Multimedia y Dispositivos Móviles del ciclo DAM.

## 📋 Descripción

Clon del juego clásico Breakout en el que el jugador controla una paleta deslizando el dedo por la pantalla para hacer rebotar una bola y destruir todos los ladrillos. El juego incluye:

- 5 filas de ladrillos con puntuaciones distintas (10–50 puntos)
- Sistema de 3 vidas representadas con corazones
- Efectos de sonido con SoundPool
- Guardado del récord entre sesiones con SharedPreferences
- Bola con animación de color cíclica
- Rebote físicamente correcto basado en solapamiento de ejes

## 🛠️ Tecnologías usadas

- Android Studio Panda 4
- Java
- SurfaceView + Game Loop manual (Thread + Runnable)
- SoundPool para efectos de sonido
- SharedPreferences para persistencia del récord
- Canvas y Paint para todos los gráficos

## 📱 Requisitos

- Android 7.0 (API 24) o superior
- Pantalla táctil

## 🚀 Instrucciones de instalación

### Desde el APK

1. Descarga el archivo `BreakoutGame.zip` desde la sección **Releases** de este repositorio
2. Descomprime y localiza el archivo `app-debug.apk`
3. Activa en tu móvil: **Ajustes → Seguridad → Instalar aplicaciones desconocidas**
4. Abre el `.apk` en tu móvil e instálalo

## 🎮 Cómo jugar

- **Inicio**: toca la pantalla para comenzar
- **Juego**: desliza el dedo horizontalmente para mover la paleta
- **Objetivo**: destruye todos los ladrillos sin que la bola caiga
- **Vidas**: tienes 3 vidas (corazones). Pierdes una cada vez que la bola sale por abajo
- **Puntuación**: cada ladrillo vale entre 10 y 50 puntos según su fila

## 👤 Autor

David Guelar  
Ciclo Formativo de Grado Superior — DAM  
Programación Multimedia y Dispositivos Móviles