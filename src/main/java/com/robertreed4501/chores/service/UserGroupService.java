package com.robertreed4501.chores.service;

import com.robertreed4501.chores.model.UserGroup;
import com.robertreed4501.chores.repository.UserGroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserGroupService {

    private final UserGroupRepository userGroupRepository;

    public String save(UserGroup userGroup){
        userGroupRepository.save(userGroup);
        return "group saved";
    }
}
