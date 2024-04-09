package com.xxrjun.components.uml.connectionlines;

import com.xxrjun.enums.UMLObjectTypes;

import java.awt.*;

public class CompositionLine extends UMLConnectionLine {
    private static final int DIAMOND_SIZE = 12;

    public CompositionLine(int x1, int y1, int x2, int y2, UMLObjectTypes objectType) {
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

        double D = Math.sqrt((double) dx * dx + dy * dy);

        double arrowLeftX = D - 16;
        double arrowRightX = arrowLeftX;
        double arrowLeftY = 8;
        double arrowRightY = -8;
        double x;

        double sin = dy / D;
        double cos = dx / D;

        x = arrowLeftX * cos - arrowLeftY * sin + sourceX;
        arrowLeftY = arrowLeftX * sin + arrowLeftY * cos + sourceY;
        arrowLeftX = x;

        x = arrowRightX * cos - arrowRightY * sin + sourceX;
        arrowRightY = arrowRightX * sin + arrowRightY * cos + sourceY;
        arrowRightX = x;

        double arrowBackX = arrowLeftX + (arrowRightX - destX);
        double arrowBackY = arrowLeftY + (arrowRightY - destY);

        int[] xPoints = {destX, (int) arrowLeftX, (int) arrowBackX, (int) arrowRightX};
        int[] yPoints = {destY, (int) arrowLeftY, (int) arrowBackY, (int) arrowRightY};


        graphics2D.setStroke(new BasicStroke(2));
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawLine(sourceX, sourceY, (int) arrowBackX, (int) arrowBackY);
        graphics2D.drawPolygon(xPoints, yPoints, 4);
    }
}
