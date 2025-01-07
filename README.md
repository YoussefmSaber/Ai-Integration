# GeoGlimpse

GeoGlimpse is an innovative Android application that uses AI to recognize landmarks from images taken by the user. The app identifies the landmark, provides its title, and stores the image along with its title in a local database for easy access and management.

## Features

- **Landmark Detection**: Utilizes TensorFlow Lite for on-device AI inference to detect and identify landmarks from captured images.
- **Image and Title Storage**: Saves the captured image and its corresponding title in a Room database for offline access.
- **Camera Integration**: Implements CameraX for seamless image analysis and picture-taking functionality.
- **Onboarding Flow**: Guides users through the app's features using a shared ViewModel for managing the onboarding process.
- **Dependency Injection**: Koin is used for efficient dependency injection and modularization of the app.

## Tech Stack

- **Programming Language**: Kotlin
- **UI**: Jetpack Compose
- **Architecture**: MVVM (Model-View-ViewModel)
- **AI Library**: TensorFlow Lite
- **Dependency Injection**: Koin
- **Database**: Room
- **Camera Framework**: CameraX

## Getting Started

### Prerequisites

- Android Studio Flamingo or later
- Minimum SDK level: 26
- Kotlin 1.8 or later

### Installation

1. Clone this repository:
   ```bash
   git clone https://github.com/YoussefmSaber/GeoGlimpse.git
   ```
2. Open the project in Android Studio.
3. Sync the project to download dependencies.
4. Build and run the app on an emulator or a physical device.

## How It Works

1. **Landmark Detection**:
   - Users can capture an image of a landmark using the in-app camera.
   - The image is analyzed by a TensorFlow Lite model to identify the landmark.
2. **Data Storage**:
   - The app stores the captured image and its identified title in a Room database.
   - Users can access their saved images and titles in a dedicated gallery section.
3. **Onboarding**:
   - First-time users are guided through the app's features with an interactive onboarding screen.

## Dependencies

The following libraries and frameworks are used in GeoGlimpse:

- **TensorFlow Lite**: For AI-based landmark detection
- **Room**: For local database management
- **Koin**: For dependency injection
- **CameraX**: For camera functionality

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request with your changes. For major changes, open an issue to discuss your ideas first.

## License

This project is licensed under the MIT License. See the LICENSE file for details.

## Contact

For any inquiries or support, please contact:
- **Email**: youssef.mu.saber@gmail.com
- **GitHub**: [Profile](https://github.com/YoussefmSaber)

---

Thank you for using GeoGlimpse! Capture the world and discover landmarks effortlessly.

