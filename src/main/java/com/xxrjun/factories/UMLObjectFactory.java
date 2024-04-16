package com.xxrjun.factories;

import com.xxrjun.components.uml.basics.UMLBasicObject;
import com.xxrjun.components.uml.basics.ClassBasicObject;
import com.xxrjun.components.uml.basics.UseCaseBasicObject;
import com.xxrjun.components.uml.connectionlines.AssociationLine;
import com.xxrjun.components.uml.connectionlines.CompositionLine;
import com.xxrjun.components.uml.connectionlines.GeneralizationLine;
import com.xxrjun.components.uml.connectionlines.UMLConnectionLine;
import com.xxrjun.enums.UMLObjectTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

/**
 * The type Uml factory.
 */
// TODO: Could be improved, but now I don't have time QQ
// ref: https://java-design-patterns.com/patterns/abstract-factory/#applicability
public abstract class UMLObjectFactory {

    private static final Logger logger = LoggerFactory.getLogger(UMLObjectFactory.class);

    private UMLObjectFactory() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Create uml basic object uml basic object.
     *
     * @param objectType the object type
     * @param p          the p
     * @return the uml basic object
     */
    public static UMLBasicObject createUMLBasicObject(UMLObjectTypes objectType, Point p) {
        return switch (objectType) {
            case CLASS -> new ClassBasicObject(p.x, p.y, objectType);
            case USE_CASE -> new UseCaseBasicObject(p.x, p.y, objectType);
            default -> {
                logger.error("Creating basic UML object failed: Invalid UML object type: {}", objectType);
                yield null;
            }
        };
    }

    /**
     * Create uml connection line uml connection line.
     *
     * @param objectType       the object type
     * @param sourcePoint      the source point
     * @param destinationPoint the destination point
     * @return the uml connection line
     */
    public static UMLConnectionLine createUMLConnectionLine(UMLObjectTypes objectType, Point sourcePoint, Point destinationPoint) {
        return switch (objectType) {
            case ASSOCIATION_LINE -> new AssociationLine(sourcePoint.x, sourcePoint.y, destinationPoint.x, destinationPoint.y, objectType);
            case GENERALIZATION_LINE -> new GeneralizationLine(sourcePoint.x, sourcePoint.y, destinationPoint.x, destinationPoint.y, objectType);
            case COMPOSITION_LINE -> new CompositionLine(sourcePoint.x, sourcePoint.y, destinationPoint.x, destinationPoint.y, objectType);
            default -> {
                logger.error("Creating UML connection line failed: Invalid UML object type: {}", objectType);
                yield null;
            }
        };
    }
}
