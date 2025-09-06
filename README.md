# RitmoFit (Android – Frontend)

App móvil (Android) para socios de la cadena de gimnasios **RitmoFit**.  
Este repo contiene **solo el frontend** en **Java + Gradle**, listo para conectar luego con el backend **Spring** (OTP por email, reservas, check‑in por QR, etc.).

> **Estado actual:** estructura base con `BottomNavigation` + `Navigation Component` y 5 pantallas vacías: **Home**, **ScanQR**, **Reservations**, **News**, **Profile**.  
> Compila y corre “out of the box”.

---

## Requisitos

- **Android Studio 2024.x o superior** (Koala/Ladybug o más nuevo)
- **JDK 17** (Android Studio trae uno embebido)
- **Gradle/AGP:** usando el *wrapper* del proyecto (no hace falta instalar nada)
- **SDK**
  - `minSdk` = 24
  - `targetSdk` / `compileSdk` = 35

> No se necesitan claves ni servicios externos para correr esta versión.

---

## Tecnologías & librerías

- **Lenguaje:** Java 17  
- **Build:** Gradle (KTS)  
- **UI/UX:** Material Components  
- **Navegación:** AndroidX Navigation Component  
- *(A agregar luego)* Retrofit/OkHttp, ZXing/CameraX, Biometric, WorkManager, FCM.

---

## Estructura del proyecto

```
app/
 ├─ src/main/
 │   ├─ AndroidManifest.xml
 │   ├─ java/com/ritmofit/app/
 │   │   └─ ui/
 │   │      ├─ MainActivity.java
 │   │      ├─ home/HomeFragment.java
 │   │      ├─ scanqr/ScanQrFragment.java
 │   │      ├─ reservations/ReservationsFragment.java
 │   │      ├─ news/NewsFragment.java
 │   │      └─ profile/ProfileFragment.java
 │   └─ res/
 │       ├─ layout/activity_main.xml
 │       ├─ layout/f_home.xml
 │       ├─ layout/f_scan_qr.xml
 │       ├─ layout/f_reservations.xml
 │       ├─ layout/f_news.xml
 │       ├─ layout/f_profile.xml
 │       ├─ menu/bottom_nav_menu.xml
 │       └─ navigation/nav_graph.xml
 ├─ build.gradle.kts
 ├─ settings.gradle.kts
 └─ ...
```

---

## Cómo correr la app (rápido)

1. **Clonar**
   ```bash
   git clone https://github.com/<tu-org>/ritmofit-android.git
   cd ritmofit-android
   ```
2. **Abrir en Android Studio** (abrir el proyecto por la carpeta raíz).
3. **Sync Gradle** (Android Studio lo hace solo).
4. **Seleccionar dispositivo:**
   - Emulador: `Tools > Device Manager` → crear AVD → *Cold Boot* si hace falta.
   - Dispositivo físico con **USB debugging** activado.
5. **Run ▶ app**. Se abre en **Home** y podés navegar entre las tabs.

> Si el emulador abre el Play Store en vez de la app: `Device Manager > Cold Boot Now` o `Wipe Data`, y volver a **Run ▶**.

---

## Detalles técnicos actuales

- **Activity de arranque:** `.ui.MainActivity`
- **Nav graph:** `res/navigation/nav_graph.xml`
- **BottomNavigation:** `res/menu/bottom_nav_menu.xml`
- **StartDestination:** `homeFragment`
- **Permisos declarados:** `INTERNET`, `CAMERA`, `POST_NOTIFICATIONS`, `USE_BIOMETRIC`, `RECEIVE_BOOT_COMPLETED`  
  > *Nota:* algunos permisos aún **no** se usan hasta implementar QR/Push/Biometría.

---

## Roadmap (próximos pasos)

1. **Autenticación por OTP (email)**
   - Pantallas: ingresar email → ingresar código OTP.
   - Retrofit hacia backend Spring (`/auth/otp/request`, `/auth/otp/verify`).
   - Persistencia de token (SharedPreferences / DataStore).
   - Desbloqueo con biometría opcional.

2. **QR Check‑in**
   - ZXing/CameraX para lectura.
   - Validación con backend, confirmación de ingreso.

3. **Catálogo de clases y reservas**
   - Listado paginado/filtrado (sede, disciplina, fecha).
   - Crear/cancelar reserva, ver próximas.

4. **Notificaciones**
   - Push (recordatorio 1h antes / cambios).
   - Reprogramación tras reinicio (BootReceiver + WorkManager).

5. **Historial + Rating**
   - Asistencias con filtros.
   - Calificación post‑clase (1–5 + comentario).

6. **Mapa / Cómo llegar**
   - Deep link a Google Maps con la sede.

7. **Noticias y promociones**
   - Feed simple provisto por servicio externo.

---

## Convenciones

- **Paquetes por feature** dentro de `ui/` y `data/` (cuando agreguemos red).
- **JavaDoc** breve en clases públicas.
- **Nombres de layouts**: `activity_*`, `f_*` (fragment), `i_*` (item).
- **IDs de menú** y **destinos de Navigation** deben coincidir.

---

## Troubleshooting

- **No aparece la configuración “app”**  
  `Run > Edit Configurations… > + > Android App` → *Module:* `:app` → *Launch:* Default Activity.

- **Se abre Play Store en vez de la app**  
  `Device Manager > Cold Boot Now` o `Wipe Data` y Run ▶.

- **Crash al abrir**  
  Revisar Logcat → buscar **“Caused by:”**:
  - `IllegalStateException: NavHost not found` → `activity_main.xml` debe tener `@+id/nav_host`.
  - `ClassNotFoundException …Fragment` → revisar `android:name` en `nav_graph.xml` y el package/clase reales.
  - `Resources$NotFoundException` → nombre de layout/id mal escrito.

- **`R` en rojo**  
  `Build > Rebuild Project` o `File > Invalidate Caches / Restart…`.

---
