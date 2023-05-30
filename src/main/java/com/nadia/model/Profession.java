package com.nadia.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Yevhenii Filatov
 * @since 5/30/23
 */

@RequiredArgsConstructor
public enum Profession {
    DEVELOPER("Developer"),
    MANAGER("Manager"),
    ASSISTANT("Assistant"),
    DIRECTOR("Director");

    @Getter
    private final String name;

    @Override
    public String toString() {
        return this.name;
    }
}
