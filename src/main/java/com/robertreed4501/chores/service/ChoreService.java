package com.robertreed4501.chores.service;

import com.robertreed4501.chores.model.Chore;
import com.robertreed4501.chores.repository.ChoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ChoreService {

    private final ChoreRepository choreRepository;

    public void AddChore(Chore chore){
        choreRepository.save(chore);
    }
}
