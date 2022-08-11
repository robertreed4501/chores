package com.robertreed4501.chores.repository;

import com.robertreed4501.chores.model.http.response.Dashboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DashboardRepository extends JpaRepository<Dashboard, Long> {

    List<Dashboard> findAllByUserId(Long userId);
    List<Dashboard> findAllByGroupId(Long groupId);
}
