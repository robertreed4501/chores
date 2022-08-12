package com.robertreed4501.chores.service;

import com.robertreed4501.chores.model.http.response.DashCard;
import com.robertreed4501.chores.model.http.response.Dashboard;
import com.robertreed4501.chores.repository.DashboardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class DashboardService {

    private final DashboardRepository dashboardRepository;

    public List<DashCard> getDashboard(Long groupId){
        List<Dashboard> fullDashboard = dashboardRepository.findAllByGroupId(groupId);
        List<DashCard> cardList = new ArrayList<>();
        //go through dashboard view results row by row and create a card for each new name
        //or add row to existing card with matching name.
        fullDashboard.forEach( row -> {
            String name = row.getFirstName();

            DashCard optCard = cardList.stream()
                    .filter((card) -> card.getName().equals(row.getFirstName()))
                    .findFirst()
                    .orElse(null);

            if (optCard != null) optCard.addChore(row);
            else {
                List<List<Dashboard>> choreListList = new ArrayList<>();
                List<Dashboard> choreList = new ArrayList<>();
                choreList.add(row);
                choreListList.add(choreList);
                cardList.add(new DashCard(row.getId(), row.getFirstName(),choreListList));
            }
        });
        return cardList;
    }

}
