# Development Documentation

## Components

- Canvas Area
- Menu
  - File
  - Edit
    - Change Object Name
    - Group
    - Ungroup
  - About
- Toolbar
  - Select
  - Association
  - Generalization Line
  - Composition Line
  - Class
  - Use Case

## Features

### GUI Layout & Buttons

- [ ] Select
- [ ] Association
- [ ] Generalization
- [ ] Composition
- [ ] Class
- [ ] Use Case

### Functions

- [ ] Create objects
- [ ] Associate objects
- [ ] Select objects
- [ ] Group objects

> [!NOTE]
> Need to collect svg icons for the toolbar

## File Structure 

> Temporary Version

```plaintext
UmlEditor/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/
│   │   │   │   └── youreditor/
│   │   │   │       ├── UmlEditorApp.java  // Main application entry point
│   │   │   │       │
│   │   │   │       ├── controller/        // Controllers handle user interaction and application logic
│   │   │   │       │   ├── CanvasController.java
│   │   │   │       │   └── ToolbarController.java
│   │   │   │       │
│   │   │   │       ├── model/             // Business logic and data models
│   │   │   │       │   ├── UmlElement.java        // Abstract class or interface for UML elements
│   │   │   │       │   ├── ClassElement.java      // Represents UML Class elements
│   │   │   │       │   ├── UseCaseElement.java    // Represents UML Use Case elements
│   │   │   │       │   ├── Association.java       // Represents Association lines
│   │   │   │       │   └── UmlDiagram.java        // Manages a collection of UML elements
│   │   │   │       │
│   │   │   │       ├── view/              // UI components
│   │   │   │       │   ├── Canvas.java            // Drawing area for UML diagrams
│   │   │   │       │   ├── Toolbar.java           // Toolbar with action buttons
│   │   │   │       │   └── MainFrame.java         // Main application window
│   │   │   │       │
│   │   │   │       └── util/              // Utility classes and helpers
│   │   │   │           ├── FileUtils.java
│   │   │   │           └── DrawingUtils.java
│   │   │   │
│   │   ├── resources/     // Application resources like icons, configuration files, etc.
│   │   │   ├── images/
│   │   │   │   └── icons/
│   │   │   └── config/
│   │   │       ├── app.properties
│   │   │       └── logging.properties
│   │   │
│   ├── test/              // Unit and integration tests
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── youreditor/
│   │   │           ├── model/
│   │   │           ├── view/
│   │   │           └── controller/
│   │   └── resources/     // Test resources
│   │       └── testdata/
│   │
├── lib/                   // External libraries not managed by build tool
│
├── build/                 // Compiled bytecode and other build artifacts (ignored in VCS)
│
├── docs/                  // Documentation files
│
└── scripts/               // Build, installation, and other utility scripts

```