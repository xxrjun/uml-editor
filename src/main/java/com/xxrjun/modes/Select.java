package com.xxrjun.modes;

import com.xxrjun.components.uml.UMLGroup;
import com.xxrjun.components.uml.UMLObject;
import com.xxrjun.components.uml.basics.UMLBasicObject;
import com.xxrjun.components.uml.connectionlines.UMLConnectionLine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * The type Select.
 */
public class Select extends UMLMode {
    private static final int GROUP_BOUND_OFFSET = 10;
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
                canvas.addSelectedObject(umlObject);
                break;
            }
        }

        canvas.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int moveX = e.getX() - startPoint.x;
        int moveY = e.getY() - startPoint.y;

        if (canvas.getSelection() != null) {
            if (canvas.getSelection() instanceof UMLBasicObject umlBasicObject) {
                // UMLObject selected
                umlBasicObject.updateLocation(moveX, moveY);
                startPoint.x = e.getX();
                startPoint.y = e.getY();
            } else if (canvas.getSelection() instanceof UMLGroup umlGroup) {
                // UMLGroup selected
                umlGroup.updateLocation(moveX, moveY);
                startPoint.x = e.getX();
                startPoint.y = e.getY();
            }
        } else {
            // Update selected area
            int upperLeftX = Math.min(startPoint.x, e.getX());
            int upperLeftY = Math.min(startPoint.y, e.getY());
            int width = Math.abs(this.startPoint.x - e.getX());
            int height = Math.abs(this.startPoint.y - e.getY());
            canvas.setSelectedArea(new Rectangle(upperLeftX, upperLeftY, width, height));
        }

        canvas.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (!canvas.getSelectedArea().isEmpty()) {
            // Check if any object is inside the selected area
            Rectangle selectedArea = canvas.getSelectedArea();
            for (UMLObject umlObject : allUMLObjects) {
                if (selectedArea.contains(umlObject.getBounds())) {
                    // Add object to selected objects
                    canvas.addSelectedObject(umlObject);
                }
            }

            if (canvas.getSelectedObjects().isEmpty()) {
                // Select but no object inside
                canvas.clearSelectedArea();
            } else {
                // resize selected area
                int upperLeftX = Integer.MAX_VALUE;
                int upperLeftY = Integer.MAX_VALUE;
                int width = 0;
                int height = 0;

                List<UMLObject> selectedObjects = canvas.getSelectedObjects();
                for (UMLObject umlObject : selectedObjects) {
                    upperLeftX = Math.min(upperLeftX, umlObject.getBounds().x);
                    upperLeftY = Math.min(upperLeftY, umlObject.getBounds().y);
                    width = Math.max(width, umlObject.getBounds().x + umlObject.getBounds().width);
                    height = Math.max(height, umlObject.getBounds().y + umlObject.getBounds().height);
                }


                canvas.getSelectedArea().setBounds(upperLeftX - GROUP_BOUND_OFFSET, upperLeftY - GROUP_BOUND_OFFSET, width - upperLeftX + 2 * GROUP_BOUND_OFFSET, height - upperLeftY + 2 * GROUP_BOUND_OFFSET);
            }
        } else {
            // No selected area
            canvas.clearSelectedArea();
        }
        logger.info("Number of selected objects: {}", canvas.getSelectedObjects().size());

        canvas.repaint();

    }
}