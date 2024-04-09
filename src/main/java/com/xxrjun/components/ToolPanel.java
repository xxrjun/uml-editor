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

public class ToolPanel {
    private static ToolPanel instance = null;

    private final JPanel panel;
    private final ArrayList<JButton> toolButtons;
    private final Logger logger = LoggerFactory.getLogger(ToolPanel.class);

    protected static final int TOOL_PANEL_WIDTH = 200;
    protected static final int TOOL_PANEL_HEIGHT = 768;
    protected static final int TOOL_PANEL_PADDING = 10;
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

    public static ToolPanel getInstance() {
        if (instance == null) {
            instance = new ToolPanel();
        }
        return instance;
    }

    public void initializePanel() {
        panel.setBounds(0, 0, TOOL_PANEL_WIDTH, TOOL_PANEL_HEIGHT);
        panel.setBorder(BorderFactory.createEmptyBorder(TOOL_PANEL_PADDING, TOOL_PANEL_PADDING, TOOL_PANEL_PADDING, TOOL_PANEL_PADDING));
        panel.setBackground(Color.GRAY);

        logger.info("Tool panel initialized: width={}, height={}", TOOL_PANEL_WIDTH, TOOL_PANEL_HEIGHT);
    }

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

    public void resetPanelButtons(int toolIndex) {
        for (JButton button : toolButtons) {
            button.setBackground(Color.WHITE);
        }
        toolButtons.get(toolIndex).setBackground(Color.DARK_GRAY);
    }

    public int getTotalPanelHeight() {
        return TOOL_PANEL_HEIGHT + toolButtons.size() * TOOL_BUTTON_VERTICAL_PADDING;
    }

    public int getToolPanelWidth() {
        return TOOL_PANEL_WIDTH + 2 * TOOL_PANEL_PADDING;
    }

    public JPanel getPanel() {
        return panel;
    }

    private class ToolButton {
        // ref: https://docs.oracle.com/javase/tutorial/uiswing/components/button.html#jbutton
        private final JButton button;

        public ToolButton(UMLMode action, String toolName, URL imageUrl) {
            ImageIcon icon = new ImageIcon(imageUrl);
            this.button = new JButton(icon);
            this.button.setToolTipText(toolName);
            this.button.setFocusable(false);
            this.button.setBackground(Color.WHITE);
            this.button.setBorderPainted(false);
            this.button.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    ToolPanel.getInstance().resetPanelButtons(toolButtons.indexOf(button));
                    Canvas.getInstance().setCanvasCurrentAction(action);
                }
            });
        }
    }
}
