[![Javadoc](https://img.shields.io/badge/JavaDoc-Online-green)](https://xxrjun.github.io/uml-editor)
[![GitHub release (latest by date)](https://img.shields.io/github/v/release/xxrjun/uml-editor)](https://github.com/xxrjun/uml-editor/releases/tag/v1.0)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=xxrjun_uml-editor&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=xxrjun_uml-editor)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=xxrjun_uml-editor&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=xxrjun_uml-editor)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=xxrjun_uml-editor&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=xxrjun_uml-editor)

<br />
<div align="center">
  <a href="https://github.com/xxrjun/uml-editor">
    <img src="assets/banner.png" alt="Logo" width="800">
  </a>
<h3 align="center">UML Editor</h3>

  <p align="center">
     NCU OOAD | Spring 2024 | Final Project
    <br />
    <a href="https://xxrjun.github.io/uml-editor/"><strong>JavaDocs »</strong></a>
    <br />
    <a href="."><strong>DEMO »</strong></a>
    <br />
    <br />
    <!-- <a href="">View Demo</a> -->
  </p>
</div>

# UML Editor

- [Software Requirements](#software-requirements)
  - [Maven Dependencies](#maven-dependencies)
- [Features](#features)
  - [GUI Layout and Buttons](#gui-layout-and-buttons)
  - [Functions](#functions)
  - [Extra Features](#extra-features)
- [Program Flow Overview](#program-flow-overview)
- [Usage](#usage)
  - [Build](#build)
  - [Run](#run)
- [Class Diagram](#class-diagram)
- [Future Work](#future-work)
- [Project File Structure](#project-file-structure)
- [References](#references)
  - [Documentations](#documentations)
  - [Related Projects](#related-projects)

## Software Requirements

- IDE: IntelliJ IDEA
- Java JDK 17.0.1
- GUI Library: [Java Swing](https://docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/javax/swing/package-summary.html)
- [SonarLint Plugin](https://plugins.jetbrains.com/plugin/7973-sonarlint) - Code quality and security analysis tool
- [Maven](https://mvnrepository.com/) - Dependency Management
- [Figma](https://www.figma.com/) - UI and components design
- Documentation
  - [Generate JavaDoc](https://www.jetbrains.com/help/idea/javadocs.html#generate-javadoc) - IntelliJ IDEA Tool
  - [Deploy - Publish Javadoc](https://github.com/marketplace/actions/deploy-publish-javadoc) - GitHub Action

### Maven Dependencies

- Logger: [slf4j](http://www.slf4j.org/) with [logback](http://logback.qos.ch/)

## Features

### GUI Layout and Buttons

- [x] Select
- [x] Association
- [x] Generalization
- [x] Composition
- [x] Class
- [x] Use Case

### Functions

- [x] Create basic object: Class, UseCase
- [x] Select/Unselect/Move a single basic object: Class, UseCase
- [x] Create UMLConnectionLine: AssociationLine, CompositionLine, GeneralizationLine
- [x] Change Object Name
- [x] Select/Unselect/Move connection line
- [x] Select/Unselect a UMLGroup of objects
- [x] Group/UpGroup
- [x] Select and Move BaseUMLObject (include Group)

### Extra Features

- [ ] Delete UMLObject

## Program Flow Overview

> [!CAUTION]
> This part will be updated soon.

1. Click Tool Button
2. Mode
   1. Create UMLObject
      1. Create UMLConnectionLine
      2. Create UMLBasicObject
   2. Select
      1. If selection is UMLObjects
         1. can Move (UMLConnectionLine movement are not supported yet.)
      2. If selection is UMLBasicObject
         1. can change it’s ObjectName
      3. If selection is an Area including several UMLObjects
         1. can Group
      4. If selection is an UMLGroup
         1. can UnGroup
3. Canvas Repaint

```mermaid
graph TD
    A[Initialize UML Editor] --> B{Button Clicked}
    B -->|Select| C[Set Select Mode]
    B -->|Association| D[Set Association Mode]
    B -->|Generalization| E[Set Generalization Mode]
    B -->|Composition| F[Set Composition Mode]
    B -->|Class| G[Set Class Creation Mode]
    B -->|Use Case| H[Set Use Case Creation Mode]
    C --> I{Mouse Event on Canvas}
    D --> I
    E --> I
    F --> I
    G --> I
    H --> I
    I -->|Left Click on Object| J[Select/Unselect Object]
    I -->|Left Click on Canvas| K[Unselect All Objects]
    I -->|Left Press on Object| L[Start Line Creation]
    I -->|Mouse Drag| M[Update Line Endpoint]
    I -->|Left Release on Object| N[Create Connection Line]
    I -->|Left Press on Object| O[Start Object Movement]
    I -->|Mouse Drag| P[Move Object]
    I -->|Left Release| Q[Update Object Position]
    J --> R{Edit Menu}
    K --> R
    R -->|Group Selected| S[Merge Selected Objects]
    R -->|Ungroup Selected| T[Decompose Composite Object]
    R -->|Change Object Name| U[Open Name Change Window]
    U -->|OK with New Name| V[Update Object Name]
    U -->|Cancel| W[Close Window]
    V --> W
    W --> I
```

## Usage

You can just download [🌔 v1.0](https://github.com/xxrjun/uml-editor/releases/tag/v1.0) and run the jar file.

### Build

```bash
mvn clean package
```

### Run

```bash
java -jar target/uml-editor-1.0-jar-with-dependencies.jar
```

## Class Diagram

> [!TIP]
> Generated by [UML class diagrams](https://www.jetbrains.com/help/idea/class-diagram.html)

Class Diagram

![Class Diagram](./assets/class-diagram.png)

Class Diagram with Dependency

![Class Diagram with Dependency](./assets/class-diagram-with-dependency.png)

## Future Work

- Improve code quality. More OO.
- Documentations: Java API Docs, README.md

## Project File Structure

```bash
.
|
+---assets
+---docs
+---src
|   +---main
|   |   +---java
|   |   |   \---com
|   |   |       \---xxrjun
|   |   |           |   UMLEditorApplication.java
|   |   |           |
|   |   |           +---components
|   |   |           |   |   Canvas.java
|   |   |           |   |   MenuBar.java
|   |   |           |   |   ToolPanel.java
|   |   |           |   |
|   |   |           |   \---uml
|   |   |           |       |   UMLGroup.java
|   |   |           |       |   UMLObject.java
|   |   |           |       |   UMLPort.java
|   |   |           |       |
|   |   |           |       +---basics
|   |   |           |       |       ClassBasicObject.java
|   |   |           |       |       UMLBasicObject.java
|   |   |           |       |       UseCaseBasicObject.java
|   |   |           |       |
|   |   |           |       \---connectionlines
|   |   |           |               AssociationLine.java
|   |   |           |               CompositionLine.java
|   |   |           |               GeneralizationLine.java
|   |   |           |               UMLConnectionLine.java
|   |   |           |
|   |   |           +---enums
|   |   |           |       EditFunctionTypes.java
|   |   |           |       ToolButtonConfig.java
|   |   |           |       UMLObjectTypes.java
|   |   |           |
|   |   |           +---factories
|   |   |           |       UMLObjectFactory.java
|   |   |           |
|   |   |           \---modes
|   |   |                   CreateBasicUMLObject.java
|   |   |                   CreateUMLConnectionLine.java
|   |   |                   Select.java
|   |   |                   UMLFactory.java
|   |   |                   UMLMode.java
|   |   |
|   |   \---resources
|   |       \---images
...
```

## References

### Documentations

- [Requirement - Use Case Format](./docs/requirement%20-%20use%20case%20format.pdf)
- [Java Design Pattern](https://java-design-patterns.com/patterns/)
- [Package javax.swing](https://docs.oracle.com/en/java/javase/17/docs/api/java.desktop/javax/swing/package-summary.html)
- [Java Swing Tutorial](https://www.javatpoint.com/java-swing)
- [The Java™ Tutorials | Creating a GUI With Swing](https://docs.oracle.com/javase/tutorial/uiswing/index.html)

### Related Projects

> [!CAUTION]
> Code similarity will be checked and updated. Expecting to use [MOSS](https://theory.stanford.edu/~aiken/moss/)

- [haVincy/UML-Editor](https://github.com/haVincy/UML-Editor)
- [MU-PING/UML-editor](https://github.com/MU-PING/UML-editor)
- [wst24365888/XYZ-UML-Editor](https://github.com/wst24365888/XYZ-UML-Editor?tab=readme-ov-file)
