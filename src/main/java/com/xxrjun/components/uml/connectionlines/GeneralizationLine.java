package com.xxrjun.components.uml.connectionlines;

import com.xxrjun.enums.UMLObjectTypes;

import java.awt.*;

/**
 * The type Generalization line.
 */
public class GeneralizationLine extends UMLConnectionLine {
    /**
     * Instantiates a new Generalization line.
     *
     * @param x1         the x 1
     * @param y1         the y 1
     * @param x2         the x 2
     * @param y2         the y 2
     * @param objectType the object type
     */
    public GeneralizationLine(int x1, int y1, int x2, int y2, UMLObjectTypes objectType) {
        super(x1, y1, x2, y2, objectType);
    }

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

        double arrowLeftX = distance - 16;
        double arrowRightX = arrowLeftX;
        double arrowLeftY = 8;
        double arrowRightY = -8;
        double x;

        double sin = dy / distance;
        double cos = dx / distance;

        x = arrowLeftX * cos - arrowLeftY * sin + sourceX;
        arrowLeftY = arrowLeftX * sin + arrowLeftY * cos + sourceY;
        arrowLeftX = x;

        x = arrowRightX * cos - arrowRightY * sin + sourceX;
        arrowRightY = arrowRightX * sin + arrowRightY * cos + sourceY;
        arrowRightX = x;

        int[] xPoints = {destX, (int) arrowLeftX, (int) arrowRightX};
        int[] yPoints = {destY, (int) arrowLeftY, (int) arrowRightY};

        graphics2D.setStroke(new BasicStroke(2));
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawLine(sourceX, sourceY, (int) (arrowRightX + arrowLeftX) / 2, (int) (arrowRightY + arrowLeftY) / 2);
        graphics2D.drawPolygon(xPoints, yPoints, 3);

    }
}
