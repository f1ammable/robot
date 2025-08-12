# Legionnaire Robot

A comprehensive Java-based robot control system for LEGO EV3 that implements drawing and interactive behaviors using the LeJOS framework. This robot functions as an intelligent drawing machine capable of multiple interaction modes and autonomous behaviors.

## Features

### Drawing Modes
- **Morse Code Drawing**: Input Morse code sequences via button presses (short/long presses) and watch the robot translate and draw the corresponding dots and dashes on paper
- **Etch-A-Sketch Mode**: Control the robot like a classic Etch-A-Sketch using directional buttons for precise freehand drawing and creative expression
- **Interactive Drawing**: Real-time pen control with button-based movement commands

### Autonomous Behaviors
- **Auto-Centering System**: Automatically positions the pen and paper using integrated touch and light sensors for optimal drawing setup
- **Smart Calibration**: Pen position calibration system that syncs the physical pen state with the software state
- **Low Battery Monitoring**: Continuous battery level monitoring with alerts to prevent mid-drawing power loss
- **Emergency Stop**: Safety mechanisms to halt all operations when needed

### Sensor Integration
- **Touch Sensor**: Used for precise pen positioning and centering operations
- **Light Sensor**: Detects paper boundaries and ambient lighting for automatic positioning
- **Gyro Sensor**: Enables shake detection for mode switching and interaction
- **Button Interface**: Multi-button control system for mode selection and drawing commands

## Technical Architecture

### Behavior-Based Control System
The robot uses a subsumption architecture with prioritized behaviors:
- `MorseCode`: Handles Morse code input and translation to drawing commands
- `EtcherSketcher`: Provides manual drawing control via directional inputs
- `Center`: Manages automatic centering and calibration routines
- `LowBatteryCheck`: Monitors power levels and provides warnings

### Motor Management
- **Dual-Axis Movement**: Precise X/Y coordinate control for accurate drawing
- **Pen Control**: Automated pen lifting/lowering for drawing and positioning
- **Speed Control**: Variable movement speeds for different drawing requirements

## Hardware Requirements

- LEGO EV3 Mindstorms brick
- 2+ motors for X/Y movement control
- 1 motor for pen lifting mechanism
- Touch sensor for boundary detection
- Light sensor for paper/surface detection
- Gyro sensor for shake detection
- Physical pen attachment mechanism

## Getting Started

### Prerequisites
- Java 7 or higher
- Maven for dependency management
- LeJOS EV3 API (automatically managed via Maven)
- Python 3.x for deployment scripts

### Building the Project
```bash
mvn clean compile
mvn package
```

### Deployment
Use the included `c2.py` script to access the LeJOS interface for controlling file uploads and managing the robot connection:

```bash
python c2.py
```

This script provides a platform-agnostic replacement for ev3control and handles:
- File transfers to the EV3 brick
- Robot connection management
- LeJOS tool integration

### Operation
1. Power on the EV3 brick
2. Use `c2.py` to deploy the compiled JAR to the robot
3. Run the Legionnaire Robot program on the EV3
4. Follow on-screen instructions for calibration
5. Use button combinations to select desired drawing mode
6. Enjoy creating with your robotic drawing companion!

## Usage Modes

### Morse Code Mode
- Press RIGHT button 5+ times to activate
- Use short presses for dots, long presses (250ms+) for dashes
- Robot automatically draws the Morse sequence
- 3-second input window for each sequence

### Etch-A-Sketch Mode
- Shake the robot 3+ times to activate
- Use directional buttons (UP/DOWN/LEFT/RIGHT) for movement
- Press ENTER to toggle pen up/down
- Natural drawing experience with immediate response

## License

This project is developed as part of an educational robotics program.
