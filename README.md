# Firefighter Robot Simulation Project

A Java-based simulation system for modeling and coordinating a team of firefighting robots. This project simulates different types of robots working together to extinguish fires across various terrain types.

## Project Overview

The simulation models a team of firefighting robots operating on a grid-based map, where each robot has unique capabilities and constraints. The system includes path-finding algorithms, event handling, and strategic fire-fighting coordination.

## Features

- Multiple robot types (Drone, Wheeled, Tracked, Legged)
- Terrain-based movement constraints
- Pathfinding using Dijkstra's algorithm
- Event-based simulation system
- Water management for fire extinction
- Strategic fire-fighting coordination

## Project Structure
```bash
project/
├── src/
│   ├── carte/
│   │   ├── Carte.java         # Map representation
│   │   ├── Case.java          # Individual cell representation
│   │   └── LecteurDonnees.java # Data file reader
│   ├── robots/
│   │   ├── Robot.java         # Abstract robot class
│   │   ├── Drone.java
│   │   ├── RobotRoues.java
│   │   ├── RobotChenilles.java
│   │   └── RobotPattes.java
│   └── simulation/
│       ├── Simulateur.java
│       └── DonneesSimulation.java
├── cartes/                     # Map files for scenarios
└── bin/                        # Compiled class files
```


## Requirements

- Java Development Kit (JDK) 8 or higher
- IntelliJ IDEA (recommended) or another Java IDE
- Make utility (for command-line compilation)

## Installation

1. Clone the repository or download the source code
2. Open the project in IntelliJ IDEA, or
3. Use command line compilation with make:
```bash
  make all
```

## Running the Simulation

### Using IntelliJ IDEA

- Open the project in IntelliJ IDEA
- Locate the main class containing the main method
- Click the "Run" button or press Shift+F10

### Using Command Line

- Run specific scenarios using:

```bash
make exeScenario1    # Run first scenario
make exeScenario2    # Run second scenario
make exeScenario3    # Run third scenario
```
## Key Components

### Map System

- Grid-based representation
- Multiple terrain types (water, forest, rock, habitat)
- Support for different cell sizes

### Robot Types

1. Drone

- Can fly over any terrain
- Limited water capacity
- Constant movement speed


2. Wheeled Robot

- Restricted to certain terrain types
- Higher water capacity
- Fast on suitable terrain

3. Tracked Robot

- Can handle more terrain types
- Medium water capacity
- Variable speed based on terrain

4. Legged Robot

- Can access most terrain types
- Limited water capacity
- Consistent speed across terrains

### Pathfinding System

- Implementation of Dijkstra's algorithm
- Accounts for robot-specific terrain constraints
- Optimizes paths based on robot movement capabilities

### Event System

Handles various event types:

- Movement events
- Fire extinction events
- Water refill events
- Status update messages

### Fire-Fighting Strategy

The simulation implements an elementary strategy that :

1. Assigns available robots to unattended fires
2. Manages robot states (FREE, MOVING, FIREFIGHTING, RECHARGING)
3. Coordinates water refill operations
4. Handles multiple fires simultaneously

### Development Notes

#### Compilation

- The project uses automatic compilation in IntelliJ IDEA
- Manual compilation available through Makefile
- Output files are generated in the __bin/__ directory

#### Adding New Features

To extend the simulation:

1. Create new event types by extending the __Evenement__ class
2. Add new robot types by extending the __Robot__ class
3. Implement new strategies by modifying the __strategieElementaire__ method

#### Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

#### Authors

- Er-rabhi Imane
- Mohammed Reda Belfaida
- Taha Aftiss

#### Academic Context

- This project was developed as part of the Object-Oriented Programming course at Grenoble INP UGA - Ensimag for the academic year 2024 - 2025.