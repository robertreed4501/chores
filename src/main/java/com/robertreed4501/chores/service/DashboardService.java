package com.robertreed4501.chores.service;

import com.robertreed4501.chores.model.http.response.DashCard;
import com.robertreed4501.chores.model.http.response.Dashboard;
import com.robertreed4501.chores.repository.DashboardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DashboardService {

    private final DashboardRepository dashboardRepository;

    public List<DashCard> getDashboard(Long groupId){
        List<Dashboard> fullDashboard = dashboardRepository.findAllByGroupId(groupId);
        List<DashCard> cardList = new ArrayList<>();
        fullDashboard.forEach( row -> {
            String name = row.getFirstName();
            if(cardList.stream().anyMatch((card) -> card.getName().equals(row.getFirstName()))){
                cardList.stream().forEach(card -> {
                    if(card.getName().equals(row.getFirstName())){
                        card.addChore(row);
                    }
                });
            }
            else{
                List<Dashboard> choreList = new ArrayList<>();
                choreList.add(row);
                cardList.add(new DashCard(row.getId(), row.getFirstName(),choreList));
            }
        });
        return cardList;
    }

}
