# MovieDB

A modern Android application demonstrating Clean Architecture, Jetpack Compose, and advanced state management while showcasing movies from The Movie Database (TMDB).

## Features

- **Categorized Dashboard**: Browse movies across multiple categories: Now Playing, Popular, Top Rated, and Upcoming.
- **Detailed Movie Insights**: View comprehensive information including high-quality backdrops, runtime, budget, revenue, and production companies.
- **Robust State Management**: Explicit handling of Loading, Success, and Error states using a centralized `DataState` pattern.
- **Fault-Tolerant Networking**: Graceful error handling and retry mechanisms built into the repository layer using `NetworkResult`.
- **Navigation State Persistence**: The app maintains its navigation state (including nested screens) across device rotations and configuration changes.
- **Optimized Data Mapping**: High-performance UI mapping with cached formatters and efficient string building.
- **Modern Navigation**: Utilizes Navigation 3 for a declarative, state-first navigation experience.
- **Adaptive UI**: Built with Material 3 for a fluid, responsive look across different screen sizes.

## Architecture

The project follows **Clean Architecture** principles, strictly separating concerns across three layers:

- **Data Layer**: Handles API communication via Retrofit and implements repositories. Features a `safeApiCall` wrapper for resilient networking.
- **Domain Layer**: Contains business logic encapsulated in **Use Cases** (`GetDashboardMoviesUseCase`, `GetMovieDetailUseCase`).
- **UI Layer**: Follows the MVVM pattern. ViewModels interact only with Use Cases and expose a single source of truth via `StateFlow` to the Compose UI.

## Tech Stack

- **UI**: [Jetpack Compose](https://developer.android.com/jetpack/compose) with Material 3.
- **Navigation**: [Navigation 3](https://developer.android.com/guide/navigation/navigation-3) - Next-generation declarative navigation.
- **Dependency Injection**: [Hilt](https://developer.android.com/training/dependency-injection/hilt-android).
- **Networking**: [Retrofit](https://square.github.io/retrofit/) & [OkHttp](https://square.github.io/okhttp/).
- **Serialization**: [Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization).
- **Date/Time**: [Kotlinx Datetime](https://github.com/Kotlin/kotlinx-datetime).
- **Image Loading**: [Coil 3](https://coil-kt.github.io/coil/).
- **State Collection**: `collectAsStateWithLifecycle` for efficient resource management.

## Getting Started

### Prerequisites

- **Android Studio Ladybug (2024.2.1)** or newer.
- **JDK 25** (Project is configured for JVM 25).
- **Android SDK 37** (Compile SDK).
- A **TMDB API Key** (v3 or v4 Auth Token). Get one at [themoviedb.org](https://www.themoviedb.org/).

### Setup

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/MovieDB.git
   ```
2. **Open the project** in Android Studio.
3. **Configure the API Key**:
   Add your TMDB API Key to `local.properties` in the root directory:
   ```properties
   apiKey=YOUR_API_KEY_HERE
   ```
4. **Sync and Run**:
   Sync Gradle and run the `app` module.

## Project Structure

- `data/`: API services, DTOs, Repository implementations, and DI modules.
- `domain/`: Business logic and Use Cases.
- `ui/`: Compose screens, ViewModels, and UI models.
- `util/`: Core utilities like `NetworkResult`, `DataState`, and `StringProvider`.
- `gradle/libs.versions.toml`: Centralized dependency management.

## Contributing

Contributions are welcome! Please follow standard fork-and-pull-request workflows.
