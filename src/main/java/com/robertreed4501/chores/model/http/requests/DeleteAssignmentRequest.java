package com.robertreed4501.chores.model.http.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DeleteAssignmentRequest {

    private Long assignmentId;
    private Long groupId;
    private Long userId;
}
