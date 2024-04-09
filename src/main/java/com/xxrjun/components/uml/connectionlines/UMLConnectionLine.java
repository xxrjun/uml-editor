package com.xxrjun.components.uml.connectionlines;

import com.xxrjun.components.uml.UMLPort;
import com.xxrjun.components.uml.UMLObject;
import com.xxrjun.enums.UMLObjectTypes;

import java.awt.*;
import java.awt.geom.Line2D;

/**
 * The type Uml connection line.
 */
public abstract class UMLConnectionLine extends UMLObject {
    // source and destination Port of the line, which is paired
    private final UMLPort[] endpointUMLPorts;

    /**
     * The enum End point type.
     */
    public enum EndPointType {
        /**
         * Source end point type.
         */
        SOURCE,
        /**
         * Destination end point type.
         */
        DESTINATION,
        /**
         * None end point type.
         */
        NONE
    }

    private EndPointType currentEndPointType = EndPointType.NONE;

    /**
     * Instantiates a new Uml connection line.
     *
     * @param x1          the x 1
     * @param y1          the y 1
     * @param x2          the x 2
     * @param y2          the y 2
     * @param objectTypes the object types
     */
    protected UMLConnectionLine(int x1, int y1, int x2, int y2, UMLObjectTypes objectTypes) {
        super(x1, y1, x2, y2, objectTypes);
        this.endpointUMLPorts = new UMLPort[2];
    }


    @Override
    public void initializePorts() {

    }

    @Override
    public boolean contains(Point p) {
        return isNearLine(p);
    }

    @Override
    public void highlightSelection(Graphics g) {
        g.setColor(Color.red);
        this.draw(g);
    }

    /**
     * Reset location.
     */
    public void resetLocation() {
        this.setX1((int) endpointUMLPorts[0].getCenterX());
        this.setY1((int) endpointUMLPorts[0].getCenterY());
        this.setX2((int) endpointUMLPorts[1].getCenterX());
        this.setY2((int) endpointUMLPorts[1].getCenterY());
    }

    /**
     * Reset end point.
     *
     * @param p the p
     */
    public void resetEndPoint(Point p) {
        if (currentEndPointType == EndPointType.SOURCE) {
            this.setX1(p.x);
            this.setY1(p.y);
        } else if (currentEndPointType == EndPointType.DESTINATION) {
            this.setX2(p.x);
            this.setY2(p.y);
        }
    }

    /**
     * Sets source port.
     *
     * @param umlPort the uml port
     */
    public void setSourcePort(UMLPort umlPort) {
        this.endpointUMLPorts[0] = umlPort;
    }

    /**
     * Sets destination port.
     *
     * @param umlPort the uml port
     */
    public void setDestinationPort(UMLPort umlPort) {
        this.endpointUMLPorts[1] = umlPort;
    }

    /**
     * Gets current end point type.
     *
     * @return the current end point type
     */
    public EndPointType getCurrentEndPointType() {
        return currentEndPointType;
    }

    /**
     * Sets end point type.
     *
     * @param endPointType the end point type
     */
    public void setEndPointType(EndPointType endPointType) {
        currentEndPointType = endPointType;
    }


    private boolean isNearLine(Point p) {
        final int tolerance = 5;
        Line2D line = new Line2D.Double(this.getX1(), this.getY1(), this.getX2(), this.getY2());
        return line.ptSegDist(p) < tolerance;
    }

}
