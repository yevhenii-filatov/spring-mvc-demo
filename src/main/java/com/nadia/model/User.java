package com.nadia.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author Yevhenii Filatov
 * @since 5/30/23
 */

@Data
@RequiredArgsConstructor
public class User {
    private String name;
    private String email;
    private String password;
    private Gender gender;
    private String note;
    private Boolean married;
    private Profession profession;
}
