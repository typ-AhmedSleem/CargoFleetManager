# Cargo FleetManager

🚀 **Cargo FleetManager** is a **Kotlin Multiplatform** application designed for controlling and monitoring Autonomous Mobile Robots (AMRs). The software provides a comprehensive **desktop dashboard (Windows & macOS)** and a minimal **mobile version (Android & iOS)** for real-time fleet monitoring.

## 🌟 Features

- **Real-time AMR Monitoring**
    - Live data visualization from AMRs.
    - Real-time camera streaming from AMRs.
    - Sensor data updates.

- **Control & Management**
    - Manual driving mode for AMRs.
    - Arm control with inverse/forward kinematics.
    - Speed control & mode switching.

- **Reporting & Logging**
    - Logs operational data for analysis.
    - Generates reports from historical data.

- **Communication System**
    - Uses WebSocket & TCP sockets for AMR communication.
    - Efficient data serialization and transmission.

## 🛠️ Tech Stack

- **Kotlin Multiplatform** (KMP) for shared business logic.
- **Compose Multiplatform** for UI (Desktop & Mobile).
- **Python** for AMR-side integration & video streaming.
- **Sockets/WebSockets** for real-time communication.
- **Serialization** (JSON, CBOR) for efficient data transfer.

## 📌 Modules Overview

### 1. Communication Module
Handles socket connections for sending and receiving data between AMRs and the WCU.

### 2. GUI Module
The front-end layer for interacting with users, displaying AMR data, and executing commands.

### 3. Data Logging & Reporting Module
Stores operational data and enables historical reporting.

### 4. Car Control Module
Provides functions for AMR navigation, speed control, and manual driving.

### 5. Arm Control Module
Includes joint control, inverse kinematics, and forward kinematics for robotic arms.

### 6. Realtime Monitoring Module
Manages real-time sensor data and video streaming from AMRs.

## 📦 Setup & Installation

### Prerequisites
- **JDK 17+**
- **Kotlin 2.0+**
- **Gradle** (latest recommended)
- **Python 3.9+** (for AMR-side components)

### Steps
1. Clone the repository:
   ```sh
   git clone https://github.com/typ-ahmedsleem/CargoFleetManager.git
   cd CargoFleetManager
   ```
2. Sync and build the project:
   ```sh
   ./gradlew build
   ```
3. Run the desktop version:
   ```sh
   ./gradlew :desktop:run
   ```
4. Run the android version:
   ```sh
   ./gradlew :android:installDebug
   ```