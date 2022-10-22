package com.robertreed4501.chores.service;

import com.robertreed4501.chores.model.db.User;
import com.robertreed4501.chores.model.http.response.LoginResponse;
import com.robertreed4501.chores.model.http.response.UserResponse;
import com.robertreed4501.chores.model.http.response.UsersInGroupResponse;
import com.robertreed4501.chores.repository.UserGroupRepository;
import com.robertreed4501.chores.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserGroupRepository userGroupRepository;

    public String addUser(User user){
        userRepository.save(user);
        return "user saved";
    }

    public List<UserResponse> getUsersByGroupId(Long id) {
        /*try{
            List<UsersInGroupResponse> userList = new ArrayList<>();
            userGroupRepository.findById(id).get().getUsers().stream().forEach(user -> {
                userList.add(new UsersInGroupResponse(user.getId(), user.getFirstName()));
            });
            return userList;
        }catch(NoSuchElementException e){
            return null;
        }*/
        return userGroupRepository.findById(id).get().getUsers().stream().map(user ->
                user.getUserResponse()
                ).collect(Collectors.toList());
    }

    public User getUserByUsername(String username) {
        User user = userRepository.findUserByEmail(username).orElse(null);
        return user;
    }

    public User getUserByApiKey(String key) {
        return userRepository.findUserByApiKey(key);
                /*findAll().
                stream().
                filter(user -> user.getApiKey()!=null).collect(Collectors.toList()).
                stream().
                filter(user -> user.getApiKey().equals(key)).findFirst().orElse(null);*/

    }

    public LoginResponse getUserLoginResponseByApiKey(String key) {
        User user = userRepository.findUserByApiKey(key);

        return new LoginResponse(
                new UserResponse(
                        user.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        user.getAppUserRole().toString(),
                        user.getApiKey(),
                        user.getUserGroup().getId()
                ),
                null);
    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).get();
    }
}
