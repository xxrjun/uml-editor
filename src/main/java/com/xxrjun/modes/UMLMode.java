package com.xxrjun.modes;

import java.awt.event.MouseAdapter;
import com.xxrjun.components.Canvas;

/**
 * The type Uml mode.
 */
public abstract class UMLMode extends MouseAdapter {
    /**
     * The Canvas.
     */
    protected Canvas canvas = Canvas.getInstance();
}
