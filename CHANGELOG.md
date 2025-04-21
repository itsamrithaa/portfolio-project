# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Calendar Versioning](https://calver.org/) of
the following form: YYYY.0M.0D.

## [Unreleased]

## [2025.04.21]

### Added

- Designed test suite for `SatelliteTracker` component, including test cases for kernel, secondary, constructor and overriden standard methods.
- Designed `CollisionTracker` and `OrbitalSimulation` to show `SatelliteTracker` in action
- Introduced new updatePosition() implementation in concrete `Satellite1` class, overriding abstract class implementation

### Updated

- Changed design to include new constant `SECONDS_IN_DAY` for easier computations
- Updated threshold values for accurate mathematical operation
- Declared final double values for non-changing velocity values
- Included test cases that cover edge/boundary, empty/zero, large, routine, challenging, and complex test cases
- Refactored `equals` implementation to ensure the method accurately checks for object equality
- Added illegalArgumentException to strictly check for pre-condition
- Cleaned Javadoc CheckStyle errors


## [2025.04.14]

### Added

- Designed kernel implementation for SatelliteTracker component
- Declared new final constants for easier computations
- Documented convention (representation i.e. 2 Arrays) and correspondence (regulations i.e. length of Array and units)
- Created two types of constructors (1) no-argument which initializes to 0 for all params and (2) Constructor from double which allows users to set the params
- Wrote two methods for createNewRep(), allowing users to create a Satellite in either ways (1 - no argument, 2 - double arguments)

### Updated

- Changed design to include a velocity and position component
- Removed individual components in order to represent Satellite as two arrays
- Updated kernel and enhanced contracts to match the new attributes of this
- Extended Standard<SatelliteTracker> interface instead of Satellite1 to ensure proper hierarchy
- Implemented kernel and overriden standard methods

## [2025.04.14]

### Added

- Designed an abstract class for a SatelliteTracker object i.e. a satellite
- Implemented enhanced and standard methods of SatelliteTracker using Kernel
- Included assert statements to check pre-conditions of both enhanced and kernel methods used in implementation of enhanced or standard methods

### Updated

- Changed design to include implementation for getting velocity at the kernel as it is a primary feature of any satellite
- Changed kernel design to include a orbitsEarth method as satellite must go around Earth and not other planetary bodies
- Modified contract to ensure precision of expectations and guarantees of a method

## [2025.03.09]

### Added

- Designed kernel and enhanced interfaces for SatelliteTracker component

### Updated

- Changed design to include implementation for calculating orbital period
- Updated implementation details for willCollide method
- Introduced three final constants which is required for data manipulation in enhanced and kernel methods

## [2025.02.20]

### Added

- Designed a proof of concept for SatelliteTracker component

### Updated

- No updates were made to the nature of the design. Just implemented existing methods.

## [2025.02.06]

### Added

- Designed a SatelliteTracker component
- Designed a MovieRecommender component
- Designed a MusicRecap component

## [2024.12.30]

- Added table-based rubrics to all 6 parts of the project
- Updated gitignore to exclude more files
- Fixed image markdown in the interfaces document

## 2025.02.06

### Added

- Designed a SatelliteTracker component
- Designed a MovieRecommender
- Designed a MusicRecap component


[unreleased]: https://github.com/jrg94/portfolio-project/compare/v2024.08.07...HEAD
[2024.08.07]: https://github.com/jrg94/portfolio-project/compare/v2024.01.07...v2024.08.07
[2024.01.07]: https://github.com/jrg94/portfolio-project/releases/tag/v2024.01.07
