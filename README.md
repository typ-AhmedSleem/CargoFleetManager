# 🚛 Cargo Fleet Management (CFM)

**CFM** is a modern, cross-platform mobile control and management system designed for **autonomous & semi-autonomous mobile robots** (AMRs) in the construction and logistics sector. It blends powerful robotics control with a beautiful, Apple-quality user experience.

---

## 🎯 Vision

To provide **intelligent, intuitive and real-time** control of AMR vehicles through:

- 📡 Seamless remote connectivity
- 🎮 Gamepad-driven manual control with custom mappings
- 🤖 Autonomous monitoring and feedback
- 📊 Actionable telemetry and real-time dashboard
- 🧠 Future-proof AI integration

---

## 🧱 Architecture Overview

CFM is structured using **Kotlin Multiplatform (KMP)** and follows the **MVVM + Clean Architecture** principles.

---

## 🔧 Features

### ✅ Gamepad Control Engine
- 🔌 USB 2.4G Gamepad detection & calibration
- 🧭 Car control (accelerate, steer, rotate, emergency stop)
- 🦾 Arm control (switch modes via Start button)
- 🔂 Logarithmic sensitivity tuning
- 🌀 Haptic feedback engine (in-progress)

### ✅ Real-time Dashboard
- ⚡️ Live status indicators: battery, speed, signal
- 🧠 Health & diagnostics summary
- 🧾 Logs and alerts

### ✅ Connection System
- 🔄 USB/Wi-Fi/Bluetooth handshake
- 📲 Smooth UI states: idle, connecting, connected, failed
- 💻 Mock data source for testing

---

## 🧪 Current Development Phase

| Module             | Status          |
|--------------------|-----------------|
| Connection System  | ✅ Complete (Mock + UI) |
| Gamepad Integration | 🧪 Prototyping |
| Dashboard UI       | 🚧 In Progress |
| Haptic Feedback    | 🔜 Planned |
| AMR Protocol Comm. | 🔜 Planned |
| UI Polish (Cupertino) | 🧑‍🎨 Ongoing |

---

## 💻 Tech Stack

- **Kotlin Multiplatform**
- **Compose Multiplatform** (Desktop, Android, iOS)
- **Compose Cupertino** – iOS-styled widgets
- **Koin** – Dependency Injection
- **Jamepad** – Gamepad input handling (Desktop)
- **Coroutines + Flow** – Reactive patterns
- **Clean Architecture** – Scalable codebase

---

## 📱 Screens Preview

> ✨ Coming Soon — Beautiful dashboards, gamepad calibration UI, real-time monitoring…

---

## 🚀 Getting Started

```bash
git clone https://github.com/typ-AhmedSleem/CargoFleetManager.git
cd CargoFleetManager
```

---

## 🤝 Contributing

We’re building something futuristic and practical — feel free to fork, suggest ideas, or fix bugs.

---

Crafted with 💚 by Ahmed Sleem
