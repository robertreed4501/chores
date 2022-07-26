package com.robertreed4501.chores.service;

import com.robertreed4501.chores.model.db.User;
import com.robertreed4501.chores.model.db.UserGroup;
import com.robertreed4501.chores.repository.UserGroupRepository;
import com.robertreed4501.chores.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserGroupRepository userGroupRepository;

    public String save(User user){
        userRepository.save(user);
        return "user saved";
    }

    public List<User> getUsersByGroupId(Long id) {
        try{
            return userGroupRepository.findById(id).get().getUsers();
        }catch(NoSuchElementException e){
            return null;
        }

    }
}
