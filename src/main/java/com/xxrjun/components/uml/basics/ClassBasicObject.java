package com.xxrjun.components.uml.basics;

import com.xxrjun.enums.UMLObjectTypes;

import java.awt.*;

public class ClassBasicObject extends UMLBasicObject {
    private static final int WIDTH = 100;
    private static final int HEIGHT = 120;
    private static final int TITLE_HEIGHT = HEIGHT / 3;
    private static final int TEXT_OFFSET = 25;

    public ClassBasicObject(int x, int y, UMLObjectTypes objectType) {
        super(x, y, WIDTH, HEIGHT, "Class Name", objectType);
    }

    @Override
    public void draw(Graphics g) {
        drawClassRectangle(g);
        drawTitleSeparator(g);
        drawAttributeSeparator(g);
        drawObjectName(WIDTH, TEXT_OFFSET, g);
    }

    private void drawClassRectangle(Graphics g) {
        g.drawRect(getX1(), getY1(), WIDTH, HEIGHT);
    }

    private void drawTitleSeparator(Graphics g) {
        g.drawLine(getX1(), getY1() + TITLE_HEIGHT, getX2(), getY1() + TITLE_HEIGHT);
    }

    private void drawAttributeSeparator(Graphics g) {
        g.drawLine(getX1(), getY1() + TITLE_HEIGHT * 2, getX2(), getY1() + TITLE_HEIGHT * 2);
    }
}
