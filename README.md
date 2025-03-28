The Wireless Control Unit (WCU) is a software component of the Cargo AMR system, designed to serve as the control dashboard for the robot.
It runs on a separate PC and provides an interface for operators to control and monitor the robot’s functions wirelessly through a local network.
The WCU is equipped with several key features and modules that enable seamless interaction with the Cargo AMR system.

**Key Features:**
- Wireless communication with the Cargo AMR software via socket channels.
- Real-time monitoring and control of the robot's movement and robotic arm.
- Data logging and report generation for operational tracking and analysis.
- Intuitive graphical user interface (GUI) for ease of use by operators.

**Modules:**
1. **Communication Module:** Manages socket connections for data exchange, encoding, and decoding between the WCU and Cargo AMR.
2. **GUI Module:** Provides a user-friendly interface that displays real-time data, robot status, and control options.
3. **Data Logging and Reporting Module:** Logs operational data, tracks history, and supports generating reports for analysis.
4. **Car Control Module:** Allows manual driving and speed adjustment, as well as switching between control modes.
5. **Arm Control Module:** Controls the robotic arm, including joint movement and inverse kinematics calculations.
6. **Realtime Monitoring Module:** Receives and displays real-time sensor data and video streams from the robot.
7. **Path Planning Module:** Supports autonomous navigation (yet to be implemented).

**Platforms:**
The WCU software is built for PC platforms, running on Windows and Linux environments, and interfaces with the Cargo AMR over a local network.
The system’s modular design allows for flexibility and future enhancements as required by the project.

The WCU’s primary goal is to provide a reliable and responsive control system, ensuring that operators can effectively manage the Cargo AMR’s movements and tasks in real-time.
