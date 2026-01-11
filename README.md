# MovieDB

A simple Android application that displays a list of currently playing movies using The Movie Database (TMDB) API.

## Features

- Displays "Now Playing" movies in a responsive staggered grid layout.
- Fetches real-time data from TMDB API.
- Dynamic image loading based on TMDB configuration.
- Modern UI built with Jetpack Compose and Material 3.
- Custom typography using Google Fonts (Montserrat, Ubuntu, Josefin Sans).

## Tech Stack

- **UI**: [Jetpack Compose](https://developer.android.com/jetpack/compose) - Android's modern toolkit for building native UI.
- **Dependency Injection**: [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - Standard library for DI in Android.
- **Networking**: [Retrofit](https://square.github.io/retrofit/) & [OkHttp](https://square.github.io/okhttp/) - Type-safe HTTP client for Android and Java.
- **Serialization**: [Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization) - Kotlin multiplatform / multi-format serialization.
- **Image Loading**: [Coil 3](https://coil-kt.github.io/coil/) - An image loading library for Android backed by Kotlin Coroutines.
- **Architecture**: MVVM (Model-View-ViewModel).
- **Dependency Management**: [Gradle Version Catalog](https://developer.android.com/build/migrate-to-catalogs) - Centralized way to manage dependencies.

## Getting Started

### Prerequisites

- Android Studio Ladybug or newer.
- JDK 22.
- A TMDB API Key. You can get one by signing up at [themoviedb.org](https://www.themoviedb.org/).

### Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/MovieDB.git
   ```
2. Open the project in Android Studio.
3. Add your TMDB API Key to `local.properties`:
   ```properties
   apiKey=YOUR_API_KEY_HERE
   ```
4. Sync the project with Gradle files.
5. Run the app on an emulator or a physical device.

## Project Structure

- `api/`: Contains Retrofit interfaces, Data Transfer Objects (DTOs), and Hilt modules for networking.
- `ui/`: Contains Compose screens, ViewModels, and theme configurations (Material 3, Google Fonts).
- `gradle/`: Contains the dependency version catalog (`libs.versions.toml`).
- `MovieDBApp.kt`: The Application class, used for Hilt initialization.
