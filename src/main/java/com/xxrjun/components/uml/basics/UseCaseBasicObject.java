package com.xxrjun.components.uml.basics;

import com.xxrjun.enums.UMLObjectTypes;

import java.awt.*;

public class UseCaseBasicObject extends UMLBasicObject {
    private static final int WIDTH = 110;
    private static final int HEIGHT = 96;
    private static final int TEXT_OFFSET = HEIGHT / 2 + 5; // To center the text
    public UseCaseBasicObject(int x, int y, UMLObjectTypes objectType) {
        super(x, y, WIDTH, HEIGHT, "Use Case Name", objectType);
    }

    @Override
    public void draw(Graphics g) {
        drawUseCaseEllipse(g);
        drawObjectName(WIDTH, TEXT_OFFSET, g);
    }

    private void drawUseCaseEllipse(Graphics g) {
        g.drawOval(getX1(), getY1(), WIDTH, HEIGHT);
    }
}
