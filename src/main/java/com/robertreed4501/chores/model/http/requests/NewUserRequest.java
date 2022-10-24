package com.robertreed4501.chores.model.http.requests;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

    @Getter
    @AllArgsConstructor
    @EqualsAndHashCode
    @ToString
    public class NewUserRequest {

        private final String firstName;
        private final String lastName;
        private final String email;
        private final String password;
        private final String dob;
        private final Long groupId;


    }
