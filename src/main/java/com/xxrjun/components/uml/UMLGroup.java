package com.xxrjun.components.uml;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Uml group.
 */
public class UMLGroup extends UMLObject {
    private static final int GROUP_BOUND_OFFSET = 10;

    private List<UMLObject> groupMembers;
    private Rectangle groupBounds;
    private UMLObject selection = null;

    /**
     * Instantiates a new Uml group.
     */
    public UMLGroup() {
        super();
        groupMembers = new ArrayList<>();
        groupBounds = new Rectangle();
    }

    @Override
    public void draw(Graphics g) {
        for (UMLObject umlObject : groupMembers) {
            umlObject.draw(g);
        }
        // draw group bounds
        g.setColor(new Color(8, 37, 57, 80));
        g.drawRect(groupBounds.x, groupBounds.y, groupBounds.width, groupBounds.height);
        g.setColor(Color.BLACK);
    }

    @Override
    public void initializePorts() {
        // PASS
    }

    @Override
    public boolean contains(Point p) {
        return groupBounds.contains(p);
    }

    @Override
    public void highlightSelection(Graphics g) {
        g.setColor(new Color(41, 86, 212, 80));
        g.fillRect(groupBounds.x, groupBounds.y, groupBounds.width, groupBounds.height);
        g.setColor(new Color(8, 37, 57));
        g.drawRect(groupBounds.x , groupBounds.y, groupBounds.width, groupBounds.height);
        // reset color
        g.setColor(Color.BLACK);
        if (selection != null) {
            selection.highlightSelection(g);
        }
    }

    /**
     * Add group member.
     *
     * @param umlObject the uml object
     */
    public void addGroupMember(UMLObject umlObject) {
        groupMembers.add(umlObject);
        setGroupBounds();
    }

    /**
     * Gets group members.
     *
     * @return the group members
     */
    public List<UMLObject> getGroupMembers() {
        return new ArrayList<>(groupMembers);
    }

    /**
     * Sets group bounds.
     */
    public void setGroupBounds() {
        int x = Integer.MAX_VALUE;
        int y = Integer.MAX_VALUE;
        int width = 0;
        int height = 0;
        for (UMLObject umlObject : groupMembers) {
            Rectangle bounds = umlObject.getBounds();
            x = Math.min(x, bounds.x);
            y = Math.min(y, bounds.y);
            width = Math.max(width, bounds.x + bounds.width);
            height = Math.max(height, bounds.y + bounds.height);
        }
        groupBounds = new Rectangle(x - GROUP_BOUND_OFFSET, y - GROUP_BOUND_OFFSET, width - x + 2 * GROUP_BOUND_OFFSET, height - y + 2 * GROUP_BOUND_OFFSET);
        this.setX1(x - GROUP_BOUND_OFFSET);
        this.setY1(y - GROUP_BOUND_OFFSET);
        this.setX2(width + GROUP_BOUND_OFFSET);
        this.setY2(height + GROUP_BOUND_OFFSET);
    }


    @Override
    public void updateLocation(int moveX, int moveY) {
        for (UMLObject umlObject : groupMembers) {
            umlObject.updateLocation(moveX, moveY);
        }
        updateGroupBounds(moveX, moveY);
    }

    private void updateGroupBounds(int moveX, int moveY) {
        groupBounds.setLocation(groupBounds.x + moveX, groupBounds.y + moveY);
        this.setX1(this.getX1() + moveX);
        this.setY1(this.getY1() + moveY);
        this.setX2(this.getX1() + groupBounds.width);
        this.setY2(this.getY1() + groupBounds.height);
    }

    /**
     * Reset selection.
     */
    public void resetSelection() {
        selection = null;
    }

    /**
     * Gets selection.
     *
     * @return the selection
     */
    public UMLObject getSelection() {
        return selection;
    }


}
