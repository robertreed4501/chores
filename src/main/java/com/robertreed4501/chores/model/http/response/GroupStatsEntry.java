package com.robertreed4501.chores.model.http.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class GroupStatsEntry {

    private String name;
    private int percentDoneThisWeek;
    private int percentDoneAllTime;
}