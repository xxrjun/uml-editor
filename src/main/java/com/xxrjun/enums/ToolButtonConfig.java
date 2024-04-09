package com.xxrjun.enums;


import com.xxrjun.modes.CreateBasicUMLObject;
import com.xxrjun.modes.CreateUMLConnectionLine;
import com.xxrjun.modes.Select;
import com.xxrjun.modes.UMLMode;

public enum ToolButtonConfig {
    SELECT(new Select(), "Select", "select.png"),
    ASSOCIATION(new CreateUMLConnectionLine(UMLObjectTypes.ASSOCIATION_LINE), "Association", "association-line.png"),
    GENERALIZATION(new CreateUMLConnectionLine(UMLObjectTypes.GENERALIZATION_LINE), "Generalization", "generalization-line.png"),
    COMPOSITION(new CreateUMLConnectionLine(UMLObjectTypes.COMPOSITION_LINE), "Composition", "composition-line.png"),
    CLASS(new CreateBasicUMLObject(UMLObjectTypes.CLASS), "Class", "class.png"),
    USE_CASE(new CreateBasicUMLObject(UMLObjectTypes.USE_CASE), "Use Case", "use-case.png");

    private final UMLMode actionType;
    private final String toolName;
    private final String imageName;

    ToolButtonConfig(UMLMode actionType, String toolName, String imageName) {
        this.actionType = actionType;
        this.toolName = toolName;
        this.imageName = imageName;
    }

    public UMLMode getActionType() {
        return actionType;
    }

    public String getToolName() {
        return toolName;
    }

    public String getImageName() {
        return imageName;
    }
}
