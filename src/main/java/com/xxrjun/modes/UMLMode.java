package com.xxrjun.modes;

import java.awt.event.MouseAdapter;
import com.xxrjun.components.Canvas;

public abstract class UMLMode extends MouseAdapter {
    protected Canvas canvas = Canvas.getInstance();
}
