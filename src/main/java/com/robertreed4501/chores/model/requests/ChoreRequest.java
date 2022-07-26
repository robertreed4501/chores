package com.robertreed4501.chores.model.requests;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ChoreRequest {
    private final String name;
    private final String scope;
    private final Long userGroupId;
}
