package com.xxrjun.components.uml;

import com.xxrjun.enums.UMLObjectTypes;

import java.awt.*;

public abstract class UMLObject {
    private int x1, y1, x2, y2;
    private UMLObjectTypes objectType;
    protected Port[] ports;
    private boolean isGroupSelected = false;

    protected UMLObject() {
    }

    protected UMLObject(int x1, int y1, int x2, int y2, UMLObjectTypes objectType) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.objectType = objectType;
    }

    public abstract void draw(Graphics g);

    public abstract void initializePorts();

    public abstract boolean contains(Point p);

    // Method to highlight the selection of the shape.
    // e.g. When the Object is selected, it will show a different color with ports
    public abstract void highlightSelection(Graphics g);

    // Setters and Getters
    public int getX1() { return x1; }

    public void setX1(int x1) { this.x1 = x1; }

    public int getY1() { return y1; }

    public void setY1(int y1) { this.y1 = y1; }

    public int getX2() { return x2; }

    public void setX2(int x2) { this.x2 = x2; }

    public int getY2() { return y2; }

    public void setY2(int y2) { this.y2 = y2; }

    public UMLObjectTypes getObjectType() { return objectType; }

    public void setObjectType(UMLObjectTypes objectType) { this.objectType = objectType; }

    public boolean isGroupSelected() { return isGroupSelected; }

    public void setGroupSelected(boolean groupSelected) { isGroupSelected = groupSelected; }
}
