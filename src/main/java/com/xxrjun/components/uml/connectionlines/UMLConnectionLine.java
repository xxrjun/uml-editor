package com.xxrjun.components.uml.connectionlines;

import com.xxrjun.components.uml.Port;
import com.xxrjun.components.uml.UMLObject;
import com.xxrjun.enums.UMLObjectTypes;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public abstract class UMLConnectionLine extends UMLObject {
    // source and destination Port of the line, which is paired
    private final Port[] endpointPorts;

    public enum EndPointType {
        SOURCE,
        DESTINATION,
        NONE
    }

    private EndPointType currentEndPointType = EndPointType.NONE;

    protected UMLConnectionLine(int x1, int y1, int x2, int y2, UMLObjectTypes objectTypes) {
        super(x1, y1, x2, y2, objectTypes);
        this.endpointPorts = new Port[2];
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
        this.setX1((int) endpointPorts[0].getCenterX());
        this.setY1((int) endpointPorts[0].getCenterY());
        this.setX2((int) endpointPorts[1].getCenterX());
        this.setY2((int) endpointPorts[1].getCenterY());
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

    public void setSourcePort(Port port) {
        this.endpointPorts[0] = port;
    }

    public void setDestinationPort(Port port) {
        this.endpointPorts[1] = port;
    }

    public EndPointType getCurrentEndPointType() {
        return currentEndPointType;
    }

    public void setEndPointType(EndPointType endPointType) {
        currentEndPointType = endPointType;
    }

//    public EndPointType getEndPointType(Point p) {
//        if (isNearLine(p)) {
//            currentEndPointType = determineEndPointType(p);
//            return currentEndPointType;
//        }
//        return EndPointType.NONE;
//    }

    private boolean isNearLine(Point p) {
        final int tolerance = 5;
        Line2D line = new Line2D.Double(this.getX1(), this.getY1(), this.getX2(), this.getY2());
        return line.ptSegDist(p) < tolerance;
    }

//    private EndPointType determineEndPointType(Point p) {
//        double distToSource = Point2D.distance(p.x, p.y, this.getX1(), this.getY1());
//        double distToDestination = Point2D.distance(p.x, p.y, this.getX2(), this.getY2());
//        return distToSource < distToDestination ? EndPointType.SOURCE : EndPointType.DESTINATION;
//    }

}
