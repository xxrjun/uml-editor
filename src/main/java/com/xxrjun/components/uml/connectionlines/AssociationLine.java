package com.xxrjun.components.uml.connectionlines;

import com.xxrjun.enums.UMLObjectTypes;

import java.awt.*;

/**
 * The type Association line.
 */
public class AssociationLine extends UMLConnectionLine {
    /**
     * Instantiates a new Association line.
     *
     * @param x1         the x 1
     * @param y1         the y 1
     * @param x2         the x 2
     * @param y2         the y 2
     * @param objectType the object type
     */
    public AssociationLine(int x1, int y1, int x2, int y2, UMLObjectTypes objectType) {
        super(x1, y1, x2, y2, objectType);
    }

    // ref: https://stackoverflow.com/questions/2027613/how-to-draw-a-directed-arrow-line-in-java
    @Override
    public void draw(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;

        int sourceX = this.getX1();
        int sourceY = this.getY1();
        int destX = this.getX2();
        int destY = this.getY2();

        int dx = destX - sourceX;
        int dy = destY - sourceY;

        double distance = Math.sqrt((double) dx * dx + dy * dy);

        double arrowLeftX = distance - 12;
        double arrowRightX = arrowLeftX;
        double arrowLeftY = 12;
        double arrowRightY = -12;
        double x;

        double sin = dy / distance;
        double cos = dx / distance;

        x = arrowLeftX * cos - arrowLeftY * sin + sourceX;
        arrowLeftY = arrowLeftX * sin + arrowLeftY * cos + sourceY;
        arrowLeftX = x;

        x = arrowRightX * cos - arrowRightY * sin + sourceX;
        arrowRightY = arrowRightX * sin + arrowRightY * cos + sourceY;
        arrowRightX = x;

        graphics2D.drawLine(sourceX, sourceY, destX, destY);
        graphics2D.drawLine(destX, destY, (int) arrowLeftX, (int) arrowLeftY);
        graphics2D.drawLine(destX, destY, (int) arrowRightX, (int) arrowRightY);
    }
}
