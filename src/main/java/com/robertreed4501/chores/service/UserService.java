package com.robertreed4501.chores.service;

import com.robertreed4501.chores.model.db.User;
import com.robertreed4501.chores.model.enums.UserRole;
import com.robertreed4501.chores.model.http.requests.UpdateUserRequest;
import com.robertreed4501.chores.model.http.response.LoginResponse;
import com.robertreed4501.chores.model.http.response.UserResponse;
import com.robertreed4501.chores.model.http.response.UsersInGroupResponse;
import com.robertreed4501.chores.repository.UserGroupRepository;
import com.robertreed4501.chores.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NonUniqueResultException;
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

        return userGroupRepository.
                findById(id).
                get().
                getUsers().
                stream().
                filter(user -> user.getEnabled()).
                map(user -> user.getUserResponse()
                ).
                collect(Collectors.toList());
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
        try{
            return userRepository.findUserByEmail(email).get();
        }
        catch (NonUniqueResultException e) {
            System.out.println(e);
            return null;
        }
    }

    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    @Transactional
    public String deleteUser(Long id) {
        userRepository.getReferenceById(id).setEnabled(false);
        return "user deactivated";
    }

    @Transactional
    public String updateUser(UpdateUserRequest request) {
        User user = userRepository.getReferenceById(request.getId());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        if (request.getRole().toUpperCase().equals(UserRole.USER.name()))
            user.setAppUserRole(UserRole.USER);
        else if (request.getRole().toUpperCase().equals(UserRole.ADMIN.name()))
            user.setAppUserRole(UserRole.ADMIN);
        return "user updated";
    }

    public User getUserById(Long id){
        return userRepository.getReferenceById(id);
    }
}
