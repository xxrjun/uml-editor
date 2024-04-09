package com.xxrjun.components.uml;

import java.awt.*;

/**
 * The type Uml port.
 */
public class UMLPort extends Rectangle {
    /**
     * Instantiates a new Uml port.
     *
     * @param centerX the center x
     * @param centerY the center y
     * @param offset  the offset
     */
    public UMLPort(int centerX, int centerY, int offset){
        super(centerX - offset, centerY - offset, offset * 2, offset * 2);
    }

    /**
     * Set location.
     *
     * @param centerX the center x
     * @param centerY the center y
     * @param offset  the offset
     */
    public void setLocation(int centerX, int centerY, int offset){
        setBounds(centerX - offset, centerY - offset, offset * 2, offset * 2);
    }
}
