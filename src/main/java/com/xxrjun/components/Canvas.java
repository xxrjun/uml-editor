package com.xxrjun.components;

import com.xxrjun.components.uml.UMLGroup;
import com.xxrjun.components.uml.basics.UMLBasicObject;
import com.xxrjun.components.uml.connectionlines.UMLConnectionLine;
import com.xxrjun.enums.EditFunctionTypes;
import com.xxrjun.modes.UMLMode;
import com.xxrjun.components.uml.UMLObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Canvas.
 */
public class Canvas extends JPanel {

    private static Canvas instance = null;
    private final ArrayList<UMLObject> umlObjects;
    private final ArrayList<UMLObject> selectedObjects;
    private UMLObject selection = null;
    private Rectangle selectedArea = null;
    private UMLMode currentMode;
    private UMLConnectionLine tmpConnectionLine = null;

    private Logger logger = LoggerFactory.getLogger(Canvas.class);

    private Canvas() {
        umlObjects = new ArrayList<>();
        selectedObjects = new ArrayList<>();
        selectedArea = new Rectangle();
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static Canvas getInstance() {
        if (instance == null) {
            instance = new Canvas();
        }
        return instance;
    }

    /**
     * Sets canvas current action.
     *
     * @param action the action
     */
    public void setCanvasCurrentAction(UMLMode action) {
        if (currentMode != null) {
            // ref: https://docs.oracle.com/javase/tutorial/uiswing/events/mouselistener.html
            this.removeMouseListener(currentMode);
            this.removeMouseMotionListener(currentMode);
        }
        this.currentMode = action;
        this.addMouseListener(currentMode);
        this.addMouseMotionListener(currentMode);
    }

    /**
     * Add uml object.
     *
     * @param umlObject the uml object
     */
    public void addUMLObject(UMLObject umlObject) {
        umlObjects.add(umlObject);
    }

    /**
     * Remove uml object.
     *
     * @param umlObject the uml object
     */
    public void removeUMLObject(UMLObject umlObject) {
        umlObjects.remove(umlObject);
    }

    /**
     * Clear selected objects.
     */
    public void clearSelectedObjects() {
        selectedObjects.clear();
    }

    /**
     * Gets uml objects.
     *
     * @return the uml objects
     */
    public List<UMLObject> getUMLObjects() {
        return new ArrayList<>(umlObjects);
    }

    /**
     * Sets selected objects.
     *
     * @param selectedObjects the selected objects
     */
    public void setSelectedObjects(List<UMLObject> selectedObjects) {
        this.selectedObjects.clear();
        this.selectedObjects.addAll(selectedObjects);
    }

    /**
     * Add selected object.
     *
     * @param umlObject the uml object
     */
    public void addSelectedObject(UMLObject umlObject) {
        selectedObjects.add(umlObject);
    }

    /**
     * Remove selected object.
     *
     * @param umlObject the uml object
     */
    public void removeSelectedObject(UMLObject umlObject) {
        selectedObjects.remove(umlObject);
    }

    /**
     * Gets selected objects.
     *
     * @return the selected objects
     */
    public List<UMLObject> getSelectedObjects() {
        return selectedObjects;
    }

    /**
     * Sets selection.
     *
     * @param selection the selection
     */
    public void setSelection(UMLObject selection) {
        this.selection = selection;
    }

    /*
        Temporary connection line
     */

    /**
     * Sets tmp connection line.
     *
     * @param tmpConnectionLine the tmp connection line
     */
    public void setTmpConnectionLine(UMLConnectionLine tmpConnectionLine) {
        this.tmpConnectionLine = tmpConnectionLine;
    }

    /**
     * Clear tmp connection line.
     */
    public void clearTmpConnectionLine() {
        this.tmpConnectionLine = null;
    }

    /**
     * Sets selected area.
     *
     * @param selectedArea the selected area
     */
/*
        Selected Area
     */
    public void setSelectedArea(Rectangle selectedArea) {
        this.selectedArea = selectedArea;
    }

    /**
     * Gets selected area.
     *
     * @return the selected area
     */
    public Rectangle getSelectedArea() {
        return selectedArea;
    }

    /**
     * Clear selected area.
     */
    public void clearSelectedArea() {
        selectedArea.setBounds(0, 0, 0, 0);
    }

    /**
     * Reset selection.
     */
    public void resetSelection() {
        if (selection instanceof UMLGroup umlGroup) {
            umlGroup.resetSelection();
            selection = null;
        } else if (selection != null) {
            // Handle the selection reset for other UMLObject types if needed
            selection = null;
        }
        clearSelectedArea();
        clearSelectedObjects();
    }

    /**
     * Gets selection.
     *
     * @return the selection
     */
    public UMLObject getSelection() {
        return selection;
    }

    /*
        Menu Function
     */

    /**
     * Sets edit function enable.
     */
    public void setEditFunctionEnable() {
        MenuBar.setEditFunctionEnable(EditFunctionTypes.CHANGE_OBJECT_NAME, selection != null && selection.isNameChangeable());
        MenuBar.setEditFunctionEnable(EditFunctionTypes.GROUP_OBJECTS, selectedObjects.size() > 1);
        MenuBar.setEditFunctionEnable(EditFunctionTypes.UNGROUP_OBJECTS, selectedObjects.size() == 1 && selection instanceof UMLGroup);
    }

    /**
     * Change object name.
     */
    public void changeObjectName() {
        if (MenuBar.changeObjectNameItem.isEnabled()) {
            // Show dialog to change object name
            String name = JOptionPane.showInputDialog("Please enter new object name: ");
            if (name != null && !name.isEmpty()) {
                logger.info("Change object name to: {}", name);
                if (this.getSelection() instanceof UMLBasicObject umlObject) {
                    umlObject.setObjectName(name);
                    this.repaint();
                    logger.info("Object name changed to: {}", umlObject.getObjectName());
                }
            } else {
                logger.warn("Invalid object name: {}", name);
            }
        } else {
            logger.warn("Change object name is disabled");
        }

    }

    /*
        Group
     */

    /**
     * Group selected objects.
     */
    public void groupSelectedObjects() {
        if (selectedObjects.size() > 1) { // Group should have at least 2 UMLObjects
            UMLGroup umlGroup = new UMLGroup();
            for (UMLObject umlObject : selectedObjects) {
                umlGroup.addGroupMember(umlObject);
                umlObjects.remove(umlObject); // Remove the selected objects from the canvas, as they are now part of the group
            }
            umlGroup.setGroupBounds();
            umlObjects.add(umlGroup);
            selectedObjects.clear();
            selection = umlGroup;
        }
    }

    /**
     * Ungroup selected objects.
     */
    public void ungroupSelectedObjects() {
        if (selectedObjects.size() == 1 && selection instanceof UMLGroup umlGroup) {
            List<UMLObject> groupMembers = umlGroup.getGroupMembers();
            umlObjects.addAll(groupMembers);
            umlObjects.remove(umlGroup);
            selectedObjects.clear();
            selection = null;
        }
        this.repaint();
    }

    /*
        Paint
     */

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Set menu enable
        setEditFunctionEnable();

        // Set paint color
        g.setColor(Color.BLACK);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));

        // Draw the UML objects
        for (int i = umlObjects.size() - 1; i >= 0; i--) {
            UMLObject umlObject = umlObjects.get(i);
            umlObject.draw(g);

            // Reset the group selection
            umlObject.setGroupSelected(false);

            // Check group selection and highlight
            if (!selectedArea.isEmpty() && selectedArea.contains(umlObject.getBounds())) {
                umlObject.highlightSelection(g);
                umlObject.setGroupSelected(true);
            }
        }

        // Draw the selected objects
        // Performance issue
        if (this.selection != null) {
            this.selection.highlightSelection(g);
        }

        // Draw temporary connection line
        if (tmpConnectionLine != null) {
            tmpConnectionLine.draw(g);
        }

        // Draw the selected area
        if (!selectedArea.isEmpty()) {
            g.setColor(new Color(41, 86, 212, 80));
            g.fillRect(selectedArea.x, selectedArea.y, selectedArea.width, selectedArea.height);
            g.setColor(new Color(8, 37, 57));
            g.drawRect(selectedArea.x, selectedArea.y, selectedArea.width, selectedArea.height);
        }
    }

}
