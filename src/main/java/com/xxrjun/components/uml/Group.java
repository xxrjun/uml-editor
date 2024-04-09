package com.xxrjun.components.uml;

import java.awt.*;
import java.util.List;

public class Group extends UMLObject {
    private List<UMLObject> groupMembers;
    private Rectangle groupBounds;
    private UMLObject selection = null;

    protected Group() {
        super();
    }

    @Override
    public void draw(Graphics g) {
        for (UMLObject umlObject : groupMembers) {
            umlObject.draw(g);
        }
    }

    @Override
    public void initializePorts() {
        // PASS
    }

    @Override
    public boolean contains(Point p) {
        for (UMLObject umlObject : groupMembers) {
            if (umlObject.contains(p)) {
                selection = umlObject;
                return true;
            }
        }
        return false;
    }

    @Override
    public void highlightSelection(Graphics g) {
        int alpha = 80; // transparent
        int offset = 10;
        g.setColor(new Color(41, 86, 212, alpha));
        g.fillRect(groupBounds.x - offset, groupBounds.y - offset, groupBounds.width + offset * 2, groupBounds.height + offset * 2);
        g.setColor(new Color(172, 158, 29));
        g.drawRect(groupBounds.x - offset, groupBounds.y - offset, groupBounds.width + offset * 2, groupBounds.height + offset * 2);
        g.setColor(Color.white);
        if (selection != null) {
            selection.highlightSelection(g);
        }
    }

    public void resetSelection(){
        selection = null;
    }


    public UMLObject getSelection() {
        return selection;
    }


}
