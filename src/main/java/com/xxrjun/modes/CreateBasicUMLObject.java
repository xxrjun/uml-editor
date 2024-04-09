package com.xxrjun.modes;

import com.xxrjun.components.uml.basics.UMLBasicObject;
import com.xxrjun.enums.UMLObjectTypes;

import java.awt.event.MouseEvent;

public class CreateBasicUMLObject extends UMLMode {

    private final UMLObjectTypes objectType;

    public CreateBasicUMLObject(UMLObjectTypes objectType) {
        this.objectType = objectType;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        UMLBasicObject newUMLBasicObject = UMLFactory.createUMLBasicObject(objectType, e.getPoint());
        canvas.addUMLObject(newUMLBasicObject);
        canvas.repaint();
    }
}
