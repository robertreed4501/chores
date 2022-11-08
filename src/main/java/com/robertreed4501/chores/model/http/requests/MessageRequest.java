package com.robertreed4501.chores.model.http.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MessageRequest {

    private Long userIdTo;
    private Long userIdFrom;
    private String subject;
    private String body;

}
