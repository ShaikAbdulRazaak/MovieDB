# MovieDB

A simple Android application that displays a movie dashboard with multiple categories using The Movie Database (TMDB) API.

## Features

- **Movie Dashboard**: Displays multiple categories including Now Playing, Upcoming, Top Rated, and Popular movies in a responsive, scrollable layout.
- **Movie Details**: Provides a comprehensive view of movie information, including backdrop images, taglines, overviews, and production company logos.
- **Loading & Error Handling**: Graceful UI states for data fetching, including loading indicators and retry mechanisms for network failures.
- **Real-time Data**: Fetches up-to-date information from the TMDB API.
- **Dynamic Image Loading**: Efficiently loads movie posters and backdrops with support for different TMDB configurations.
- **Modern UI**: Built entirely with Jetpack Compose and Material 3 for a fluid user experience.
- **Custom Typography**: Utilizes Google Fonts (Montserrat, Ubuntu, Josefin Sans, Didact Gothic) for a polished look.
- **Adaptive Navigation**: Implements the latest Navigation 3 library for robust screen transitions.
- **Localization Ready**: Strings are externalized to `strings.xml` to support future localization.

## Tech Stack

- **UI**: [Jetpack Compose](https://developer.android.com/jetpack/compose) - Modern declarative UI toolkit.
- **Dependency Injection**: [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - Simplified dependency injection for Android.
- **Networking**: [Retrofit](https://square.github.io/retrofit/) & [OkHttp](https://square.github.io/okhttp/) - Industry-standard networking libraries.
- **Serialization**: [Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization) - Modern Kotlin-first serialization.
- **Image Loading**: [Coil 3](https://coil-kt.github.io/coil/) - Fast, lightweight, and modern image loading.
- **Navigation**: [Navigation 3](https://developer.android.com/guide/navigation/navigation-3) - The next generation of Compose-based navigation.
- **Architecture**: MVVM (Model-View-ViewModel) following Clean Architecture principles.

## Getting Started

### Prerequisites

- Android Studio Ladybug (2024.2.1) or newer.
- JDK 22.
- Android SDK 37 (for compilation).
- A TMDB API Key (v3 or v4 Auth Token). Get one at [themoviedb.org](https://www.themoviedb.org/).

### Setup

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/MovieDB.git
   ```
2. **Open the project** in Android Studio.
3. **Configure the API Key**:
   Create or open `local.properties` in the root directory and add your TMDB API Key:
   ```properties
   apiKey=YOUR_API_KEY_HERE
   ```
   *Note: Do not wrap the key in extra quotes unless your token literally contains them.*
4. **Sync and Run**:
   Sync the project with Gradle files and run the `app` module on an emulator or physical device.

## Project Structure

- `com.razzaaq.moviedb.api`: Networking layer, Retrofit services, DTOs (Data Transfer Objects), and Hilt modules.
- `com.razzaaq.moviedb.ui`: UI layer including screens (Dashboard, Movie Details), ViewModels, and theme (Material 3, Google Fonts).
- `gradle/libs.versions.toml`: Version catalog for centralized dependency management.
- `MovieDBApp.kt`: Application entry point and Hilt configuration.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request. For major changes, please open an issue first to discuss what you would like to change.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## Troubleshooting

If you encounter a `<identifier> expected` error during build, ensure your `apiKey` in `local.properties` is correctly formatted and doesn't have mismatched or nested quotes.
