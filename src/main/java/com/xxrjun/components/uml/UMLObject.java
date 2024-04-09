package com.xxrjun.components.uml;

import com.xxrjun.enums.UMLObjectTypes;

import java.awt.*;

/**
 * The type Uml object.
 */
public abstract class UMLObject{
    private int x1, y1, x2, y2;
    private UMLObjectTypes objectType;
    /**
     * The Uml ports.
     */
    protected UMLPort[] umlPorts;
    private boolean isGroupSelected = false;
    private boolean isNameChangeable = false;

    /**
     * Instantiates a new Uml object.
     */
    protected UMLObject() {
    }

    /**
     * Instantiates a new Uml object.
     *
     * @param x1         the x 1
     * @param y1         the y 1
     * @param x2         the x 2
     * @param y2         the y 2
     * @param objectType the object type
     */
    protected UMLObject(int x1, int y1, int x2, int y2, UMLObjectTypes objectType) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.objectType = objectType;
    }

    /**
     * Draw.
     *
     * @param g the g
     */
    public abstract void draw(Graphics g);

    /**
     * Initialize ports.
     */
    public abstract void initializePorts();

    /**
     * Contains boolean.
     *
     * @param p the p
     * @return the boolean
     */
    public abstract boolean contains(Point p);

    /**
     * Highlight selection.
     *
     * @param g the g
     */
    // Method to highlight the selection of the shape.
    // e.g. When the Object is selected, it will show a different color with ports
    public abstract void highlightSelection(Graphics g);

    /**
     * Update location.
     *
     * @param moveX the move x
     * @param moveY the move y
     */
    // TODO: Percolating up: Only BasicObject and GroupObject will override this method
    public void updateLocation(int moveX, int moveY) {}

    /**
     * Gets x 1.
     *
     * @return the x 1
     */
    // Setters and Getters
    public int getX1() { return x1; }

    /**
     * Sets x 1.
     *
     * @param x1 the x 1
     */
    public void setX1(int x1) { this.x1 = x1; }

    /**
     * Gets y 1.
     *
     * @return the y 1
     */
    public int getY1() { return y1; }

    /**
     * Sets y 1.
     *
     * @param y1 the y 1
     */
    public void setY1(int y1) { this.y1 = y1; }

    /**
     * Gets x 2.
     *
     * @return the x 2
     */
    public int getX2() { return x2; }

    /**
     * Sets x 2.
     *
     * @param x2 the x 2
     */
    public void setX2(int x2) { this.x2 = x2; }

    /**
     * Gets y 2.
     *
     * @return the y 2
     */
    public int getY2() { return y2; }

    /**
     * Sets y 2.
     *
     * @param y2 the y 2
     */
    public void setY2(int y2) { this.y2 = y2; }

    /**
     * Gets object type.
     *
     * @return the object type
     */
    public UMLObjectTypes getObjectType() { return objectType; }

    /**
     * Sets object type.
     *
     * @param objectType the object type
     */
    public void     setObjectType(UMLObjectTypes objectType) { this.objectType = objectType; }

    /**
     * Is group selected boolean.
     *
     * @return the boolean
     */
    public boolean isGroupSelected() { return isGroupSelected; }

    /**
     * Sets group selected.
     *
     * @param groupSelected the group selected
     */
    public void setGroupSelected(boolean groupSelected) { isGroupSelected = groupSelected; }

    /**
     * Is name changeable boolean.
     *
     * @return the boolean
     */
    public boolean isNameChangeable() { return isNameChangeable; }

    /**
     * Sets name changeable.
     *
     * @param nameChangeable the name changeable
     */
    public void setNameChangeable(boolean nameChangeable) { isNameChangeable = nameChangeable; }

    /**
     * Gets bounds.
     *
     * @return the bounds
     */
    public Rectangle getBounds() {
        return new Rectangle(x1, y1, x2 - x1, y2 - y1);
    }
}
