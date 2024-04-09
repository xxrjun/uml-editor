package com.xxrjun.components.uml;

import java.awt.*;

public class UMLPort extends Rectangle {
    public UMLPort(int centerX, int centerY, int offset){
        super(centerX - offset, centerY - offset, offset * 2, offset * 2);
    }

    public void setLocation(int centerX, int centerY, int offset){
        setBounds(centerX - offset, centerY - offset, offset * 2, offset * 2);
    }
}
