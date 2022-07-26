package com.robertreed4501.chores.service;

import com.robertreed4501.chores.model.db.Chore;
import com.robertreed4501.chores.model.db.UserGroup;
import com.robertreed4501.chores.repository.ChoreRepository;
import com.robertreed4501.chores.repository.UserGroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ChoreService {

    private final ChoreRepository choreRepository;
    private final UserGroupRepository userGroupRepository;

    public void AddChore(Chore chore){
        choreRepository.save(chore);
    }

    public String getAllChores() {
        return choreRepository.findAll().toString();
    }

    public List<Chore> getChoresByGroupId(Long groupId) {
        try {
            UserGroup group = userGroupRepository.findById(groupId).orElseThrow();
            return choreRepository.getChoresByUserGroup(group);
        }catch(NoSuchElementException e){
            return null;
        }
    }
}
