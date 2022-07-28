package com.robertreed4501.chores.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TrialView {

    private String name;
    private String chore;
    private boolean done;
}
