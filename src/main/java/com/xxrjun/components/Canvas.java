package com.xxrjun.components;

import com.xxrjun.components.uml.Group;
import com.xxrjun.components.uml.connectionlines.UMLConnectionLine;
import com.xxrjun.modes.UMLMode;
import com.xxrjun.components.uml.UMLObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Canvas extends JPanel {
    private static Canvas instance = null;
    private final ArrayList<UMLObject> umlObjects;
    private final ArrayList<UMLObject> selectedObjects;
    private UMLObject selection = null;
    private Rectangle SelectedArea;
    private UMLMode currentMode;
    private UMLConnectionLine tmpConnectionLine = null;

    private Logger logger = LoggerFactory.getLogger(Canvas.class);

    private Canvas() {
        umlObjects = new ArrayList<>();
        selectedObjects = new ArrayList<>();
        SelectedArea = new Rectangle();
        //        // ref: https://docs.oracle.com/javase/tutorial/uiswing/components/layeredpane.html
        //        this.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        //        this.setLayout(null);
        //        this.setOpaque(true);
        //        this.setBackground(Color.WHITE);
    }

    public static Canvas getInstance() {
        if (instance == null) {
            instance = new Canvas();
        }
        return instance;
    }

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

    public void addUMLObject(UMLObject umlObject) {
        umlObjects.add(umlObject);
    }

    public void removeUMLObject(UMLObject umlObject) {
        umlObjects.remove(umlObject);
    }

    public void clearSelectedObjects() {
        selectedObjects.clear();
    }

    public List<UMLObject> getUMLObjects() {
        return new ArrayList<>(umlObjects);
    }

    public void setSelectedObjects(List<UMLObject> selectedObjects) {
        this.selectedObjects.clear();
        this.selectedObjects.addAll(selectedObjects);
    }

    public void addSelectedObject(UMLObject umlObject) {
        selectedObjects.add(umlObject);
    }

    public void removeSelectedObject(UMLObject umlObject) {
        selectedObjects.remove(umlObject);
    }

    public List<UMLObject> getSelectedObjects() {
        return selectedObjects;
    }

    public void setSelection(UMLObject selection) {
        this.selection = selection;
    }

    public void setTmpConnectionLine(UMLConnectionLine tmpConnectionLine) {
        this.tmpConnectionLine = tmpConnectionLine;
    }

    public void clearTmpConnectionLine() {
        this.tmpConnectionLine = null;
    }


    public void resetSelection() {
        if (selection instanceof Group group) {
            group.resetSelection();
            selection = null;
        } else if (selection != null) {
            // Handle the selection reset for other UMLObject types if needed
            selection = null;
        }
        SelectedArea.setBounds(0, 0, 0, 0);
    }

    public UMLObject getSelection() {
        return selection;
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Set paint color
        g.setColor(Color.BLACK);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));

        // Draw the UML objects
        for (int i = umlObjects.size() - 1; i >= 0; i--) {
            UMLObject umlObject = umlObjects.get(i);
            umlObject.draw(g);
        }

        // Draw the selected objects
        // Performance issue
        if(this.selection != null) {
            this.selection.highlightSelection(g);
        }

        // Draw temporary connection line
        if (tmpConnectionLine != null) {
            tmpConnectionLine.draw(g);
        }
    }
}
