# SatelliteTracker Component

## Project Overview
`SatelliteTracker` is a robust Java software component designed to model, simulate, and analyze satellite trajectories. Developed using **Design-by-Contract** principles, this project bridges the gap between raw data analytics and software engineering by providing a layered architecture for managing orbital positions and velocities.

The component utilizes dual-array representations to store position and velocity data, facilitating real-time collision detection and orbital simulation to help minimize space traffic.

## Key Features
* **Orbital Mechanics Engine:** Implementation of algorithms for orbital period estimation and velocity adjustment using force vectors.
* **Collision Detection:** Integrated `CollisionTracker` to provide real-time alerts based on proximity thresholds.
* **Layered Architecture:** Clear separation between Kernel and Abstract layers, adhering to strict OSU CSE disciplines (avoiding object instantiation in abstract classes).
* **Data Integrity:** Rigorous implementation of `.equals()` and `.hashCode()` alongside `IllegalArgumentException` handling for robust pre-condition checking.
* **Full Test Suite:** 100% method-level coverage using JUnit, testing edge/boundary, empty, and complex routine cases.

## Technical Skills Gained
* **Software Design:** Developed and documented a satellite tracking component in Java using **Design-by-Contract** principles.
* **Quality Assurance:** Implemented full **JUnit** test coverage for kernel, secondary, and standard methods (100% method-level coverage).
* **Scientific Computing:** Designed and applied orbital mechanics algorithms including orbital period estimation, velocity adjustment using force vectors, and collision detection.
* **Version Control:** Managed source control using **Git** and published final project to GitHub with semantic versioning and structured pull requests.
* **Simulation & Modeling:** Demonstrated functionality with structured use case simulations for orbital movement and real-time collision alerts.

## How to Use
1.  **Clone the Repository:** `git clone [your-repository-url]`
2.  **Explore Simulations:** See the tracker in action by reviewing `OrbitalSimulation.java` and `CollisionTracker.java`.
3.  **Run Tests:** Execute the JUnit test suite to verify kernel and secondary method integrity.

## Changelog
All notable changes to this project are documented in the [CHANGELOG.md](./CHANGELOG.md) file, adhering to [Calendar Versioning](https://calver.org/) and [Keep a Changelog](https://keepachangelog.com/en/1.1.0/) standards.

## Credits & Acknowledgments
* **Framework & Template:** This project was developed using the [Portfolio Project Template](https://github.com/jrg94/portfolio-project) created by **Jeremy Grifski** (@jrg94).
* **Author:** Amrithaa Ashok Kumar (Honors Data Analytics, The Ohio State University).
