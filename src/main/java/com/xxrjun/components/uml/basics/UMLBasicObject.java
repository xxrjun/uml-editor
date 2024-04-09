package com.xxrjun.components.uml.basics;

import com.xxrjun.components.uml.UMLPort;
import com.xxrjun.components.uml.UMLObject;
import com.xxrjun.components.uml.connectionlines.UMLConnectionLine;
import com.xxrjun.enums.UMLObjectTypes;

import java.awt.*;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Uml basic object.
 */
public abstract class UMLBasicObject extends UMLObject {
    private static final int PORT_OFFSET = 5; // for the size of the port
    private static final Font DEFAULT_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 14); // Default font, to be used in all objects

    private String objectName; // Default name
    private final Map<PortPosition, UMLPort> ports = new EnumMap<>(PortPosition.class);

    private enum PortPosition {
        /**
         * Top port position.
         */
        TOP,
        /**
         * Bottom port position.
         */
        BOTTOM,
        /**
         * Left port position.
         */
        LEFT,
        /**
         * Right port position.
         */
        RIGHT}

    private final Map<UMLConnectionLine, UMLConnectionLine.EndPointType> connectionLines = new HashMap<>();

    /**
     * Instantiates a new Uml basic object.
     *
     * @param x          the x
     * @param y          the y
     * @param width      the width
     * @param height     the height
     * @param objectName the object name
     * @param objectType the object type
     */
    protected UMLBasicObject(int x, int y, int width, int height, String objectName, UMLObjectTypes objectType) {
        super(x, y, x + width, y + height, objectType);
        super.setNameChangeable(true);
        this.objectName = objectName != null ? objectName : "Object Name";
        initializePorts();
    }

    @Override
    public void initializePorts() { // also update the port locations
        ports.put(PortPosition.TOP, new UMLPort(getCenterX(), getY1() - PORT_OFFSET, PORT_OFFSET));
        ports.put(PortPosition.BOTTOM, new UMLPort(getCenterX(), getY2() + PORT_OFFSET, PORT_OFFSET));
        ports.put(PortPosition.LEFT, new UMLPort(getX1() - PORT_OFFSET, getCenterY(), PORT_OFFSET));
        ports.put(PortPosition.RIGHT, new UMLPort(getX2() + PORT_OFFSET, getCenterY(), PORT_OFFSET));
    }


    @Override
    public void highlightSelection(Graphics g) {
        ports.values().forEach(umlPort -> g.fillRect((int) umlPort.getX(), (int) umlPort.getY(), (int) umlPort.getWidth(), (int) umlPort.getHeight()));
    }

    @Override
    public boolean contains(Point p) {
        // TODO: This method is not accurate for UseCase
        return p.x >= getX1() && p.x <= getX2() && p.y >= getY1() && p.y <= getY2();
    }

    /**
     * Find nearest port uml port.
     *
     * @param point the point
     * @return the uml port
     */
    public UMLPort findNearestPort(Point point) {
        UMLPort nearestUMLPort = null;
        double minDistance = Double.MAX_VALUE;
        for (Map.Entry<PortPosition, UMLPort> entry : ports.entrySet()) {
            UMLPort umlPort = entry.getValue();
            double distance = point.distance(umlPort.getCenterX(), umlPort.getCenterY());

            if (distance < minDistance) {
                minDistance = distance;
                nearestUMLPort = umlPort;
            }
        }
        return nearestUMLPort;
    }

    /**
     * Update connection line.
     */
    public void updateConnectionLine() {
        for (Map.Entry<UMLConnectionLine, UMLConnectionLine.EndPointType> entry : connectionLines.entrySet()) {
            // if source => calculate source Nearest port => set source port
            // if destination => calculate destination Nearest port => set destination port
            UMLConnectionLine connectionLine = entry.getKey();
            UMLConnectionLine.EndPointType endPointType = entry.getValue();


            // TODO: BUG: UMLBasicObject and UMLConnectionLine overlapping still exists after updating to nearest port
            if (endPointType == UMLConnectionLine.EndPointType.SOURCE) {
                connectionLine.setSourcePort(findNearestPort(new Point(connectionLine.getX1(), connectionLine.getY1())));
            } else if (endPointType == UMLConnectionLine.EndPointType.DESTINATION) {
                connectionLine.setDestinationPort(findNearestPort(new Point(connectionLine.getX2(), connectionLine.getY2())));
            }
            // reset the location of the connection line
            connectionLine.resetLocation();
        }
    }

    @Override
    public void updateLocation(int deltaX, int deltaY) {
        setX1(getX1() + deltaX);
        setY1(getY1() + deltaY);
        setX2(getX2() + deltaX);
        setY2(getY2() + deltaY);
        initializePorts(); // reset the port locations
        updateConnectionLine(); // reset the connection line locations
    }

    /**
     * Add connection line.
     *
     * @param connectionLine the connection line
     * @param endPointType   the end point type
     */
    public void addConnectionLine(UMLConnectionLine connectionLine, UMLConnectionLine.EndPointType endPointType) {
        connectionLines.put(connectionLine, endPointType);
    }

    /**
     * Sets object name.
     *
     * @param objectName the object name
     */
    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    /**
     * Gets object name.
     *
     * @return the object name
     */
    public String getObjectName() {
        return objectName;
    }

    /**
     * Draw object name.
     *
     * @param width      the width
     * @param textOffset the text offset
     * @param g          the g
     */
    public void drawObjectName(int width, int textOffset, Graphics g) {
        g.setFont(DEFAULT_FONT);
        int stringWidth = g.getFontMetrics().stringWidth(getObjectName());
        int textStartX = getX1() + (width - stringWidth) / 2;
        g.drawString(getObjectName(), textStartX, getY1() + textOffset);
    }

    private int getCenterX() {
        return (getX1() + getX2()) / 2;
    }

    private int getCenterY() {
        return (getY1() + getY2()) / 2;
    }
}
