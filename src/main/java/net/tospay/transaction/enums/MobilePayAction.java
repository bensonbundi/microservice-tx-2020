package net.tospay.transaction.enums;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum MobilePayAction
{
    SOURCE("SOURCE"),
    DESTINATION("DESTINATION");

    private static final Map<String, MobilePayAction> LABEL = new HashMap<>();

    static {
        for (MobilePayAction e : values()) {
            LABEL.put(e.type, e);
        }
    }

    private String type;

    // ... fields, constructor, methods

     MobilePayAction(String type)
    {
        this.type = type;
    }

    @JsonCreator
    public static MobilePayAction valueOfType(String label)
    {
        label = label.toUpperCase();
        return LABEL.get(label);
    }
}
