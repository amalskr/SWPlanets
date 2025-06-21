# Star Wars Planets(SWPlanets) – Jetpack Compose App

This is a simple yet modern Android app that displays a list of planets fetched from the [Star Wars API (SWAPI)](https://swapi.dev). The project demonstrates the use of Jetpack Compose, MVVM with Clean Architecture, and modern Android development practices.

---

## Features

- Fetches and displays a scrollable list of planets from SWAPI.
- Each planet card shows:
  - Name
  - Climate
  - Placeholder image from [Picsum Photos](https://picsum.photos)
- Tap on a planet to view detailed information:
  - Name
  - Orbital Period
  - Gravity
  - Larger image

---

## Architecture & Tech Stack

- **Jetpack Compose** for UI
- **MVVM with Clean Architecture**
- **Kotlinx Serialization** for JSON parsing
- **Retrofit** for network calls (with optional unsafe client fallback for SSL)
- **Koin** for Dependency Injection
- **StateFlow** for reactive UI state management
- **Navigation Compose** for screen transitions
- **Kotlin Coroutines**
- **Room DB**
- **Material 3**

---

## Trade-offs & Considerations

- Used `@Serializable` and `Json.encodeToString` to pass `Planet` objects between screens, avoiding `Parcelable`.
- ⚠️ `UnsafeOkHttpClient` was temporarily used to bypass SSL issues for development against `http://swapi.dev`. **Not recommended for production**.
- Images are fetched deterministically using `planet.name.hashCode()` as a seed to Picsum.

---

## How to Run

1. Clone the repo:
   ```bash
   git clone https://github.com/your-username/star-wars-planets.git
   ```
2. Open the project in Android Studio.
3. Build and run on an Android device or emulator.

---

## Offline Support & Network Awareness - V2

- **Offline Data Caching**  
  Planets are cached locally using **Room database** on the first successful network call.

- **Offline Mode UI**  
  When the app is offline:
  - A **semi-transparent red banner** is displayed at the bottom.
  - Data is served from the local database.
  - The banner updates to **green with “Online”** once connection is restored, then auto-dismisses after 2 seconds.

- **Auto Reload on Reconnection**  
  If the app was offline and network becomes available, it **automatically re-fetches the latest planet list** to keep the data fresh.

- **No Internet & No Data**  
  A graceful error is shown if there’s no internet and no previously cached data.

---

## CI/CD pipeline with GitHub Actions

This project uses **GitHub Actions** to automate the build process for the Android application. The pipeline is triggered **on every push to the `development` branch** and performs the following tasks:

1. **Checkout the Code**: Retrieves the latest code from the repository.
2. **Build Debug APK**: Uses Gradle to build the debug APK with `assembleDebug`.
3. **Rename APK Based on Branch**: Renames the generated APK to include the branch name (`main`, `dev`, or `feature`) for clarity.
4. **Upload APK as an Artifact**: The renamed APK is uploaded and made available as a downloadable artifact from the GitHub Actions run.

---

## Release 

- v1 - Core feature (Splash Screen, List Screen and Details Screen) - DONE
- v2 - Offline Support (DONE)
- v3 - Pagination (PENDING)
