package com.xxrjun.components.uml.connectionlines;

import com.xxrjun.components.uml.UMLPort;
import com.xxrjun.components.uml.UMLObject;
import com.xxrjun.enums.UMLObjectTypes;

import java.awt.*;
import java.awt.geom.Line2D;

public abstract class UMLConnectionLine extends UMLObject {
    // source and destination Port of the line, which is paired
    private final UMLPort[] endpointUMLPorts;

    public enum EndPointType {
        SOURCE,
        DESTINATION,
        NONE
    }

    private EndPointType currentEndPointType = EndPointType.NONE;

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

    public void resetLocation() {
        this.setX1((int) endpointUMLPorts[0].getCenterX());
        this.setY1((int) endpointUMLPorts[0].getCenterY());
        this.setX2((int) endpointUMLPorts[1].getCenterX());
        this.setY2((int) endpointUMLPorts[1].getCenterY());
    }

    public void resetEndPoint(Point p) {
        if (currentEndPointType == EndPointType.SOURCE) {
            this.setX1(p.x);
            this.setY1(p.y);
        } else if (currentEndPointType == EndPointType.DESTINATION) {
            this.setX2(p.x);
            this.setY2(p.y);
        }
    }

    public void setSourcePort(UMLPort umlPort) {
        this.endpointUMLPorts[0] = umlPort;
    }

    public void setDestinationPort(UMLPort umlPort) {
        this.endpointUMLPorts[1] = umlPort;
    }

    public EndPointType getCurrentEndPointType() {
        return currentEndPointType;
    }

    public void setEndPointType(EndPointType endPointType) {
        currentEndPointType = endPointType;
    }


    private boolean isNearLine(Point p) {
        final int tolerance = 5;
        Line2D line = new Line2D.Double(this.getX1(), this.getY1(), this.getX2(), this.getY2());
        return line.ptSegDist(p) < tolerance;
    }

}
