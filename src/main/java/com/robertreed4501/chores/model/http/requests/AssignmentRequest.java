package com.robertreed4501.chores.model.http.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AssignmentRequest {
    private Long userId;
    private Long choreId;
}
