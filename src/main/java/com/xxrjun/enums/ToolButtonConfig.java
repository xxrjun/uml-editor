package com.xxrjun.enums;


import com.xxrjun.modes.CreateBasicUMLObject;
import com.xxrjun.modes.CreateUMLConnectionLine;
import com.xxrjun.modes.Select;
import com.xxrjun.modes.UMLMode;

/**
 * The enum Tool button config.
 */
public enum ToolButtonConfig {
    /**
     * The Select.
     */
    SELECT(new Select(), "Select", "select.png"),
    /**
     * The Association.
     */
    ASSOCIATION(new CreateUMLConnectionLine(UMLObjectTypes.ASSOCIATION_LINE), "Association", "association-line.png"),
    /**
     * The Generalization.
     */
    GENERALIZATION(new CreateUMLConnectionLine(UMLObjectTypes.GENERALIZATION_LINE), "Generalization", "generalization-line.png"),
    /**
     * The Composition.
     */
    COMPOSITION(new CreateUMLConnectionLine(UMLObjectTypes.COMPOSITION_LINE), "Composition", "composition-line.png"),
    /**
     * The Class.
     */
    CLASS(new CreateBasicUMLObject(UMLObjectTypes.CLASS), "Class", "class.png"),
    /**
     * The Use case.
     */
    USE_CASE(new CreateBasicUMLObject(UMLObjectTypes.USE_CASE), "Use Case", "use-case.png");

    private final UMLMode actionType;
    private final String toolName;
    private final String imageName;

    ToolButtonConfig(UMLMode actionType, String toolName, String imageName) {
        this.actionType = actionType;
        this.toolName = toolName;
        this.imageName = imageName;
    }

    /**
     * Gets action type.
     *
     * @return the action type
     */
    public UMLMode getActionType() {
        return actionType;
    }

    /**
     * Gets tool name.
     *
     * @return the tool name
     */
    public String getToolName() {
        return toolName;
    }

    /**
     * Gets image name.
     *
     * @return the image name
     */
    public String getImageName() {
        return imageName;
    }
}
