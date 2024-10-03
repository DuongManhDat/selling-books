package com.example.selling_books.application.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum CoverType {
    HARD_COVER("Bìa cứng"),
    SOFT_COVER("Bìa mềm");

    private final String displayName;

    CoverType(String displayName) {
        this.displayName = displayName;
    }

    public static List<String> getDisplayNames() {
        return Arrays.stream(CoverType.values())
            .map(CoverType::toString)
            .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return displayName;
    }

    public static CoverType fromDisplayName(String displayName) {
        for (CoverType type : CoverType.values()) {
            if (type.toString().equalsIgnoreCase(displayName)) {
                return type;
            }
        }
        return null;
    }
}

