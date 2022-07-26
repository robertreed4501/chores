package com.robertreed4501.chores.model.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AssignmentRequest {
    private Long userId;
    private Long choreId;
}
