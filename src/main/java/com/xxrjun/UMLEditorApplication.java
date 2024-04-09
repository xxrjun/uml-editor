package com.xxrjun;

import com.xxrjun.components.Canvas;
import com.xxrjun.components.MenuBar;
import com.xxrjun.components.ToolPanel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

/**
 * The type Uml editor application.
 */
public class UMLEditorApplication {
    // Window Configurations
    private static final String WINDOW_TITLE = "UML Editor";
    private static final int WINDOW_WIDTH = 1280;
    private static final int WINDOW_HEIGHT = ToolPanel.getInstance().getTotalPanelHeight();

    private static final JFrame frame = new JFrame();

    private final Logger logger = LoggerFactory.getLogger(UMLEditorApplication.class);


    /**
     * Create and show gui.
     *
     * @param title  The title of the window
     * @param width  The width of the window
     * @param height The height of the window               <p>               ref: <a href="https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/MenuLookDemoProject/src/components/MenuLookDemo.java">...</a>
     */
    public void createAndShowGUI(String title, int width, int height) {
        logger.info("Creating and showing GUI: title={}, width={}, height={}", title, width, height);
        /*
            Create and set up the window
         */
        frame.setTitle(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        /*
            Create and set up the content pane
         */
        // Add the MenuBar to the frame
        MenuBar menuBarInst = MenuBar.getInstance();
        frame.setJMenuBar(menuBarInst.getMenuBar());

        // Add the ToolPanel to the frame
        ToolPanel toolPanelInst = ToolPanel.getInstance();
        frame.add(toolPanelInst.getPanel(), BorderLayout.WEST);

        // Add the Canvas to the frame
        Canvas canvasInst = Canvas.getInstance();
        frame.add(canvasInst, BorderLayout.CENTER);
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        // Because swing components are not thread safe, they must be created and modified on the event-dispatching thread (EDT).
        // ref: https://docs.oracle.com/en/java/javase/17/docs/api/java.desktop/javax/swing/package-summary.html#threading
        // Schedule a job for the event-dispatching thread: creating and showing this application's GUI.
        SwingUtilities.invokeLater(() -> {
            UMLEditorApplication editor = new UMLEditorApplication();
            editor.createAndShowGUI(WINDOW_TITLE, WINDOW_WIDTH, WINDOW_HEIGHT);
        });

    }
}
