package com.robertreed4501.chores.model.http.response;

import jdk.jfr.Percentage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@AllArgsConstructor
@Getter
@Setter
public class UserStatsEntry {

    private Long userId;
    private String firstName;
    private String lastName;
    private Double percentDone;
    private LocalDate start;
    private LocalDate end;
}
