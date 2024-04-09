package com.xxrjun.modes;

import com.xxrjun.components.uml.UMLObject;
import com.xxrjun.components.uml.basics.UMLBasicObject;
import com.xxrjun.components.uml.connectionlines.UMLConnectionLine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;

public class Select extends UMLMode {
    // TODO: could be improved? Other modes might have this field as well
    private List<UMLObject> allUMLObjects; // should be same as canvas.getUMLObjects()
    private Point startPoint = null;
    private UMLConnectionLine selectedConnectionLine = null;

    private final Logger logger = LoggerFactory.getLogger(Select.class);

    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = e.getPoint();
        allUMLObjects = canvas.getUMLObjects();

        canvas.resetSelection();

        logger.info("Trying to selecting object at point: {}", startPoint);

        int size = allUMLObjects.size();
        for (int i = size - 1; i >= 0; i--) {
            UMLObject umlObject = allUMLObjects.get(i);
            if (umlObject.contains(e.getPoint())) {
                logger.info("Selected object: {}", umlObject);
                canvas.setSelection(umlObject);
                break;
            }
        }

        canvas.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int moveX = e.getX() - startPoint.x;
        int moveY = e.getY() - startPoint.y;

        if (canvas.getSelection() != null) { // UMLObject selected
//            if(canvas.getSelection() instanceof UMLConnectionLine) {
//                selectedConnectionLine = (UMLConnectionLine) canvas.getSelection();
//                selectedConnectionLine.resetEndpoint
//            }
            if (canvas.getSelection() instanceof UMLBasicObject umlBasicObject) {
                umlBasicObject.moveLocationBy(moveX, moveY);
                startPoint.x = e.getX();
                startPoint.y = e.getY();
            } else {
                // TODO: // Selecting area
            }
        }

        canvas.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO: deal with moving connection line and group selection

        canvas.repaint();
    }
}