package com.xxrjun.components;

import com.xxrjun.enums.EditFunctionTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The type Menu bar.
 */
public class MenuBar {

    // Singleton design pattern
    // ref: https://www.baeldung.com/java-singleton
    private static MenuBar instance = null;

    private static final JMenuBar myMenuBar = new JMenuBar();
    /**
     * The constant changeObjectNameItem.
     */
    protected static final JMenuItem changeObjectNameItem = new JMenuItem("Change Object Name");
    /**
     * The constant groupObjectsItem.
     */
    protected static final JMenuItem groupObjectsItem = new JMenuItem("Group Objects");
    /**
     * The constant unGroupObjectsItem.
     */
    protected static final JMenuItem unGroupObjectsItem = new JMenuItem("UnGroup Objects");

    private final Logger logger = LoggerFactory.getLogger(MenuBar.class);
    private final Canvas canvas = Canvas.getInstance();

    private MenuBar() {
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu aboutMenu = new JMenu("About");

        JMenuItem aboutItem = new JMenuItem("GitHub");

        /*
            Disable menu items by default
         */
        aboutItem.setEnabled(true);
        changeObjectNameItem.setEnabled(false); // only enable when object is selected
        groupObjectsItem.setEnabled(false);     // only enable when multiple objects are selected
        unGroupObjectsItem.setEnabled(false);   // only enable when group is selected

        /*
            Add action listeners
         */
        aboutItem.addActionListener(e -> openGitHubLink());
        aboutMenu.add(aboutItem);


        changeObjectNameItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                canvas.changeObjectName();
            }
        });
        editMenu.add(changeObjectNameItem);

        groupObjectsItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                canvas.groupSelectedObjects();
            }
        });
        editMenu.add(groupObjectsItem);

        unGroupObjectsItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                canvas.ungroupSelectedObjects();
            }
        });
        editMenu.add(unGroupObjectsItem);

        myMenuBar.add(fileMenu);
        myMenuBar.add(editMenu);
        myMenuBar.add(aboutMenu);
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static MenuBar getInstance() {
        if (instance == null) {
            instance = new MenuBar();
        }

        return instance;
    }

    /**
     * Gets menu bar.
     *
     * @return the menu bar
     */
    public JMenuBar getMenuBar() {
        return myMenuBar;
    }

    private void openGitHubLink() {
        String url = "https://github.com/xxrjun";
        logger.info("Open GitHub link: {}", url);
        try {
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
        } catch (java.io.IOException e) {
            logger.error("Failed to open GitHub link: {}", e.getMessage());
        }
    }

    /**
     * Sets edit function enable.
     *
     * @param functionType the function type
     * @param enable       the enable
     */
    public static void setEditFunctionEnable(EditFunctionTypes functionType, boolean enable) {
        switch (functionType) {
            case CHANGE_OBJECT_NAME:
                changeObjectNameItem.setEnabled(enable);
                break;
            case GROUP_OBJECTS:
                groupObjectsItem.setEnabled(enable);
                break;
            case UNGROUP_OBJECTS:
                unGroupObjectsItem.setEnabled(enable);
                break;
            default:
                throw new IllegalArgumentException("Invalid function type: " + functionType);
        }
    }
}
