package com.xxrjun.modes;

import com.xxrjun.components.uml.Port;
import com.xxrjun.components.uml.UMLObject;
import com.xxrjun.components.uml.basics.UMLBasicObject;
import com.xxrjun.components.uml.connectionlines.UMLConnectionLine;
import com.xxrjun.enums.UMLObjectTypes;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;

public class CreateUMLConnectionLine extends UMLMode {
    private List<UMLObject> allUMLObjects;
    private Point startPoint = null;
    private Point endPoint = null;
    private final UMLObjectTypes lineType;
    private UMLBasicObject sourceObject = null;
    private UMLBasicObject destinationObject = null;
    private Port sourcNearestPort = null;
    private Port destinationNearestPort = null;

    public CreateUMLConnectionLine(UMLObjectTypes lineType) {
        this.lineType = lineType;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Find the source object and set it as the source of the connection line
        allUMLObjects = canvas.getUMLObjects();
        for (UMLObject umlObject : allUMLObjects) {
            // Check if the mouse click is inside the object
            if (umlObject instanceof UMLBasicObject UMLBasicObject && (UMLBasicObject.contains(e.getPoint()))) {
                sourceObject = UMLBasicObject;
                sourcNearestPort = UMLBasicObject.findNearestPort(e.getPoint());
                startPoint = new Point((int) sourcNearestPort.getX(), (int) sourcNearestPort.getY());
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Find the destination object and set it as the destination of the connection line
        for (UMLObject umlObject : allUMLObjects) {
            // Check if the mouse click is inside the object
            if (umlObject instanceof UMLBasicObject UMLBasicObject && (UMLBasicObject.contains(e.getPoint()))) {
                destinationObject = UMLBasicObject;
                destinationNearestPort = UMLBasicObject.findNearestPort(e.getPoint());
                endPoint = new Point((int) destinationNearestPort.getX(), (int) destinationNearestPort.getY());
                break;
            }
        }

        if (endPoint != null && startPoint != null) {
            // Create the connection line
            UMLConnectionLine newConnectionLine = UMLFactory.createUMLConnectionLine(lineType, startPoint, endPoint);
            canvas.addUMLObject(newConnectionLine);

            // Set port for line
            assert newConnectionLine != null;
            newConnectionLine.setSourcePort(sourcNearestPort);
            newConnectionLine.setDestinationPort(destinationNearestPort);

            sourceObject.addConnectionLine(newConnectionLine, UMLConnectionLine.EndPointType.SOURCE);
            destinationObject.addConnectionLine(newConnectionLine, UMLConnectionLine.EndPointType.DESTINATION);
        }

        // clear temporary connection line and repaint
        canvas.clearTmpConnectionLine();
        canvas.repaint();
        startPoint = null;
        endPoint = null;
        sourcNearestPort = null;
        destinationNearestPort = null;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Display temporary dragged line
        if (startPoint != null) {
            UMLConnectionLine tmpConnectionLine = UMLFactory.createUMLConnectionLine(lineType, startPoint, e.getPoint());
            canvas.setTmpConnectionLine(tmpConnectionLine);
            canvas.repaint();
        }
    }
}
