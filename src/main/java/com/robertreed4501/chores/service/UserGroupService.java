package com.robertreed4501.chores.service;

import com.robertreed4501.chores.model.db.User;
import com.robertreed4501.chores.model.db.UserGroup;
import com.robertreed4501.chores.repository.UserGroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserGroupService {

    private final UserGroupRepository userGroupRepository;

    public UserGroup findByUser(User user) {
        return userGroupRepository.findByUser(user);
    }

    public String save(UserGroup userGroup){
        userGroupRepository.save(userGroup);
        return "group saved";
    }

    public UserGroup findById(Long userGroupId) {
        return userGroupRepository.findById(userGroupId).orElseThrow();
    }
}
