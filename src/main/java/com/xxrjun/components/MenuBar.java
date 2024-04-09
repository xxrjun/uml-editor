package com.xxrjun.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuBar {

    // Singleton design pattern
    // ref: https://www.baeldung.com/java-singleton
    private static MenuBar instance = null;

    private static final JMenuBar myMenuBar = new JMenuBar();

    private final Logger logger = LoggerFactory.getLogger(MenuBar.class);

    private MenuBar() {
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu aboutMenu = new JMenu("About");

        JMenuItem aboutItem = new JMenuItem("GitHub");
        JMenuItem changeObjectNameItem = new JMenuItem("Change Object Name");
        JMenuItem groupObjectsItem = new JMenuItem("Group Objects");
        JMenuItem unGroupObjectsItem = new JMenuItem("UnGroup Objects");


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
                changeObjectName();
            }
        });
        editMenu.add(changeObjectNameItem);

        groupObjectsItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                groupObjects();
            }
        });
        editMenu.add(groupObjectsItem);

        unGroupObjectsItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                unGroupObjects();
            }
        });
        editMenu.add(unGroupObjectsItem);

        myMenuBar.add(fileMenu);
        myMenuBar.add(editMenu);
        myMenuBar.add(aboutMenu);
    }

    public static MenuBar getInstance() {
        if (instance == null) {
            instance = new MenuBar();
        }

        return instance;
    }

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

    private void changeObjectName() {
        logger.info("Change object name");

        // TODO
    }

    private void groupObjects() {
        logger.info("Group objects");

        // TODO
    }

    private void unGroupObjects() {
        logger.info("Ungroup objects");

        // TODO
    }
}
