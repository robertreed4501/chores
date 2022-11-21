package com.robertreed4501.chores.repository;

import com.robertreed4501.chores.model.http.response.LastWeeksDashboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LastWeeksDashboardRepository extends JpaRepository<LastWeeksDashboard, Long> {

    List<LastWeeksDashboard> findAllByGroupId (Long groupId);
    List<LastWeeksDashboard> findByUserId (Long userId);
}
