package com.xxrjun.components;

import com.xxrjun.modes.UMLMode;
import com.xxrjun.enums.ToolButtonConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.net.URL;
import java.util.ArrayList;

/**
 * The type Tool panel.
 */
public class ToolPanel {
    private static ToolPanel instance = null;

    private final JPanel panel;
    private final ArrayList<JButton> toolButtons;
    private final Logger logger = LoggerFactory.getLogger(ToolPanel.class);

    /**
     * The constant TOOL_PANEL_WIDTH.
     */
    protected static final int TOOL_PANEL_WIDTH = 200;
    /**
     * The constant TOOL_PANEL_HEIGHT.
     */
    protected static final int TOOL_PANEL_HEIGHT = 768;
    /**
     * The constant TOOL_PANEL_PADDING.
     */
    protected static final int TOOL_PANEL_PADDING = 10;
    /**
     * The constant TOOL_BUTTON_VERTICAL_PADDING.
     */
    protected static final int TOOL_BUTTON_VERTICAL_PADDING = 10;

    private ToolPanel() {
        toolButtons = new ArrayList<>();

        // Load buttons to buttons list
        loadButtons();

        // Create a panel with GridLayout and add buttons to it
        panel = new JPanel(new GridLayout(toolButtons.size(), 1, 0, TOOL_BUTTON_VERTICAL_PADDING));

        initializePanel();
        for (JButton button : toolButtons) {
            panel.add(button);
        }
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ToolPanel getInstance() {
        if (instance == null) {
            instance = new ToolPanel();
        }
        return instance;
    }

    /**
     * Initialize panel.
     */
    public void initializePanel() {
        panel.setBounds(0, 0, TOOL_PANEL_WIDTH, TOOL_PANEL_HEIGHT);
        panel.setBorder(BorderFactory.createEmptyBorder(TOOL_PANEL_PADDING, TOOL_PANEL_PADDING, TOOL_PANEL_PADDING, TOOL_PANEL_PADDING));
        panel.setBackground(Color.GRAY);

        logger.info("Tool panel initialized: width={}, height={}", TOOL_PANEL_WIDTH, TOOL_PANEL_HEIGHT);
    }

    /**
     * Load buttons.
     */
    public void loadButtons() {
        for (ToolButtonConfig config : ToolButtonConfig.values()) {
            URL imageUrl = getClass().getResource("/images/" + config.getImageName());
            if (imageUrl != null) {
                ToolButton toolButton = new ToolButton(config.getActionType(), config.getToolName(), imageUrl);
                toolButtons.add(toolButton.button);
            } else {
                logger.error("Image resource not found: {}", config.getImageName());
            }
        }

        logger.info("Tool buttons loaded: {}", toolButtons.size());
    }

    /**
     * Reset panel buttons.
     *
     * @param toolIndex the tool index
     */
    public void resetPanelButtons(int toolIndex) {
        for (JButton button : toolButtons) {
            button.setBackground(Color.WHITE);
        }
        toolButtons.get(toolIndex).setBackground(new Color(225, 151, 25));
    }

    /**
     * Gets total panel height.
     *
     * @return the total panel height
     */
    public int getTotalPanelHeight() {
        return TOOL_PANEL_HEIGHT + toolButtons.size() * TOOL_BUTTON_VERTICAL_PADDING;
    }

    /**
     * Gets tool panel width.
     *
     * @return the tool panel width
     */
    public int getToolPanelWidth() {
        return TOOL_PANEL_WIDTH + 2 * TOOL_PANEL_PADDING;
    }

    /**
     * Gets panel.
     *
     * @return the panel
     */
    public JPanel getPanel() {
        return panel;
    }

    private class ToolButton {
        // ref: https://docs.oracle.com/javase/tutorial/uiswing/components/button.html#jbutton
        private final JButton button;

        /**
         * Instantiates a new Tool button.
         *
         * @param action   the action
         * @param toolName the tool name
         * @param imageUrl the image url
         */
        public ToolButton(UMLMode action, String toolName, URL imageUrl) {
            ImageIcon icon = new ImageIcon(imageUrl);
            this.button = new JButton(icon);
            this.button.setToolTipText(toolName);
            this.button.setFocusable(false);
            this.button.setBackground(Color.WHITE);
            this.button.setBorderPainted(false);
            button.setOpaque(true);
            button.setContentAreaFilled(true);
            this.button.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    ToolPanel.getInstance().resetPanelButtons(toolButtons.indexOf(button));
                    Canvas.getInstance().setCanvasCurrentAction(action);
                    Canvas.getInstance().resetSelection();
                    Canvas.getInstance().repaint();
                }
            });
        }
    }
}
