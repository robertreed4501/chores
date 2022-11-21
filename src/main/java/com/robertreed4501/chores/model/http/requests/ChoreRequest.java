package com.robertreed4501.chores.model.http.requests;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ChoreRequest {
    private final String name;
    private final String description;
    private final int multiplier;
    private final Long userGroupId;
}
