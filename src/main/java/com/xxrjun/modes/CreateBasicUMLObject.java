package com.xxrjun.modes;

import com.xxrjun.components.uml.basics.UMLBasicObject;
import com.xxrjun.enums.UMLObjectTypes;
import com.xxrjun.factories.UMLObjectFactory;

import java.awt.event.MouseEvent;

/**
 * The type Create basic uml object.
 */
public class CreateBasicUMLObject extends UMLMode {

    private final UMLObjectTypes objectType;

    /**
     * Instantiates a new Create basic uml object.
     *
     * @param objectType the object type
     */
    public CreateBasicUMLObject(UMLObjectTypes objectType) {
        this.objectType = objectType;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        UMLBasicObject newUMLBasicObject = UMLObjectFactory.createUMLBasicObject(objectType, e.getPoint());
        canvas.addUMLObject(newUMLBasicObject);
        canvas.repaint();
    }
}
