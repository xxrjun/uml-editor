# The Spec

- [Requirement and Spec](#requirement-and-spec)
  - [Definitions](#definitions)
- [Use Cases](#use-cases)
  - [A.1 Creating a UML Object](#a1-creating-a-uml-object)
  - [B.1 Creating a UML Connection Line](#b1-creating-a-uml-connection-line)
  - [C.1 Select/Unselect a Single Object](#c1-selectunselect-a-single-object)
  - [D.1 Group Objects](#d1-group-objects)
  - [D.2 UnGroup Objects](#d2-ungroup-objects)
  - [E.1 Move Objects](#e1-move-objects)
  - [F.1 Change Object Name](#f1-change-object-name)

This requirement document is also the basic functionality that your project must accomplish by the end of this course—a UML editor.

The UML editor is divided into three parts:

- The first part is the editing area known as the Canvas area.
- To the left of the editing area is a row of buttons. You must complete at least the following six buttons as shown in the diagram:
  - `select`
  - `association`
  - `generalization`
  - `composition`
  - `class`
  - `use case`

Besides the `select` button, all other buttons are used to create a UML object within the editing area. This UML editor should be used similarly to the general drawing software you are familiar with, although slight operational differences (for more user-friendliness) may lead to complex implementation differences. The specification is written specifically to reduce implementation confusion; please follow it to complete the described functionalities.

## Requirement and Spec

- **Use Case Format**: The requirement document uses UML's Use Case to describe system requirements and specifications.

### Definitions

- **Basic Object**: Such as class or use case objects.
- **Connection Object**: Such as various association lines.
- **Composite Object**: A composite object is made up of multiple basic objects combined using a group function. A composite object is a tree-like container, meaning it can contain other composite objects. The scope of a composite object is defined as the minimum square area that completely contains all its constituent objects.
- **Object Depth (0-99)**: Each object has a depth value relative to other objects. An object with a lower depth value should cover other objects and first receive and intercept mouse events falling on it. When two objects overlap, the top-most object receives the mouse event.

## Use Cases

### A.1 Creating a UML Object

- **Precondition**: This use case applies to the `class` and `use case` buttons.
- **Steps**:
  1. After pressing the button, its color turns black to inform the user of the current object creation mode.
  2. The user moves the cursor to the editing area.
  3. At coordinates (x,y), the user presses the left mouse button to create the selected object at the top left corner (a blank object is drawn in the editing area).
  4. The user can repeatedly perform steps 2 and 3 to continue creating the same object in the editing area until the mode is changed.
- **Alternatives**:
  1. If the user presses another button, the mode switches to that of the other button.

### B.1 Creating a UML Connection Line

- **Precondition**: This use case applies to `association line`, `generalization line`, and `composition line`.
- **Steps**:
  1. The user presses the left mouse button within the boundary of a class or use case object in the editing area but does not release it (mouse pressed).
  2. The user drags without releasing the left button.
  3. The user releases the mouse button over another class or use case object area (mouse released).
  4. A line object is created in the editing area, connecting the two objects. Depending on the type of connection line, various arrows are drawn at the endpoint object.
- **Alternatives**:
  1. If the mouse-pressed coordinates are not on any class or use case object, then from mouse pressed to mouse drag to mouse released, no action occurs.
  2. If the mouse-released coordinates are not on any class or use case object, then no connection line object is created.

### C.1 Select/Unselect a Single Object

- **Precondition**: This use case applies when the `select` button is pressed.
- **Steps**:
  1. The user clicks on a basic object.
  2. If other objects are in a selected state, their selected state is canceled.
  3. The four connection ports of this basic object are explicitly displayed.
- **Alternatives**:
  1. If the user clicks on coordinates not inside any basic object, then perform steps 2 as described above.

### D.1 Group Objects

- **Precondition**: More than one basic object is selected.
- **Steps**:
  1. The user goes to the Edit Menu and selects the "Group" option.
  2. The selected basic objects are merged into one composite object.

### D.2 UnGroup Objects

- **Precondition**: Only one composite object is selected.
- **Steps**:
  1. The user goes to the Edit Menu and selects the "UnGroup" option.
  2. The composite object is decomposed one layer.

### E.1 Move Objects

- **Precondition**: The 'select' button is pressed.
- **Steps**:
  1. The user presses and holds the mouse left button over a basic object (including composite objects) within the editing area.
  2. The user drags without releasing the left mouse button.
  3. The user releases the mouse button at another coordinate (x, y).
  4. The basic object is moved to the new coordinate (x, y).
  5. All connection lines linked to this basic object are redrawn.
- **Note**: If the (x, y) coordinates overlap with another object's area, the basic object at (x, y) will overlap other objects. The objects should be drawn according to their depth order.

### F.1 Change Object Name

- **Precondition**: A basic object is in a selected state.
- **Steps**:
  1. The user goes to the Edit Menu and selects the "Change Object Name" option.
  2. A small window appears with a text area and OK and Cancel buttons.
  3. The user can enter a new name in the text area and press OK to change the name of the basic object. The window then disappears.
- **Alternatives**:
  1. If the user presses the Cancel button, the window disappears and the name change is canceled.
