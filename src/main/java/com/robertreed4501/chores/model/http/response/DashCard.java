package com.robertreed4501.chores.model.http.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DashCard {

    private Long userId;
    private String name;
    private List<List<Dashboard>> chores;

    public DashCard(String name) {
        this.name = name;
    }

    public DashCard(Long userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public void addChore(Dashboard chore){

        List<Dashboard> matchingList = this.chores.stream()
                .filter(choreList ->
                        choreList.stream().anyMatch(currentChore ->
                                currentChore.getName().equals(chore.getName())))
                .findFirst()
                .orElse(null);

        if (matchingList == null){
            List<Dashboard> tempList = new ArrayList<>();
            tempList.add(chore);
            this.chores.add(tempList);
        }
        else{
            matchingList.add(chore);
        }
    }
}
