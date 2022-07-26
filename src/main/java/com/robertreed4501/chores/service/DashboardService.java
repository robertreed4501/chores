package com.robertreed4501.chores.service;

import com.robertreed4501.chores.model.http.response.DashCard;
import com.robertreed4501.chores.model.http.response.Dashboard;
import com.robertreed4501.chores.model.http.response.LastWeeksDashboard;
import com.robertreed4501.chores.repository.DashboardRepository;
import com.robertreed4501.chores.repository.LastWeeksDashboardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class DashboardService {

    private final DashboardRepository dashboardRepository;
    private final LastWeeksDashboardRepository lastWeeksDashboardRepository;

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
                cardList.add(new DashCard(row.getUserId(), row.getFirstName(),choreListList));
            }
        });
        cardList.stream().forEach(card -> {
            Collections.sort(card.getChores(), (o1, o2) -> o1.size() - o2.size());
        });
        return cardList;
    }

    public DashCard getUserDashCard(Long userId) {
        List<Dashboard> fullDashboard = dashboardRepository.findAllByUserId(userId);
        if (fullDashboard.isEmpty()) return null;
        System.out.println(fullDashboard.size() + " - error incoming?  is dashboard.size 0?");
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
                cardList.add(new DashCard(row.getUserId(), row.getFirstName(),choreListList));
            }
        });
        cardList.stream().forEach(card -> {
            Collections.sort(card.getChores(), (o1, o2) -> o1.size() - o2.size());
        });
        return cardList.get(0);
    }

    public DashCard getLastWeeksUserDashCard (Long userId) {
        List<LastWeeksDashboard> fullDashboard = lastWeeksDashboardRepository.findByUserId(userId);
        if (fullDashboard.isEmpty()) return null;
        System.out.println(fullDashboard.size() + " - error incoming?  is dashboard.size 0?");
        List<DashCard> cardList = new ArrayList<>();
        //go through dashboard view results row by row and create a card for each new name
        //or add row to existing card with matching name.
        fullDashboard.forEach( row -> {
            String name = row.getFirstName();

            DashCard optCard = cardList.stream()
                    .filter((card) -> card.getName().equals(row.getFirstName()))
                    .findFirst()
                    .orElse(null);

            if (optCard != null) optCard.addChore(new Dashboard(
                    row.getId(),
                    row.getFirstName(),
                    row.getUserId(),
                    row.getGroupId(),
                    row.getAssignmentId(),
                    row.getName(),
                    row.getDescription(),
                    row.getMultiplier(),
                    row.getDone()
                    ));
            else {
                List<List<Dashboard>> choreListList = new ArrayList<>();
                List<Dashboard> choreList = new ArrayList<>();
                choreList.add(new Dashboard(
                        row.getId(),
                        row.getFirstName(),
                        row.getUserId(),
                        row.getGroupId(),
                        row.getAssignmentId(),
                        row.getName(),
                        row.getDescription(),
                        row.getMultiplier(),
                        row.getDone()
                ));
                choreListList.add(choreList);
                cardList.add(new DashCard(row.getUserId(), row.getFirstName(),choreListList));
            }
        });
        cardList.stream().forEach(card -> {
            Collections.sort(card.getChores(), (o1, o2) -> o1.size() - o2.size());
        });
        return cardList.get(0);
    }
}
