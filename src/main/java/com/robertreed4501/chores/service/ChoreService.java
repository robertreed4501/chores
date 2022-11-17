package com.robertreed4501.chores.service;

import com.robertreed4501.chores.model.db.Chore;
import com.robertreed4501.chores.model.db.UserGroup;
import com.robertreed4501.chores.model.http.requests.UpdateChoreRequest;
import com.robertreed4501.chores.repository.AssignmentRepository;
import com.robertreed4501.chores.repository.ChoreRepository;
import com.robertreed4501.chores.repository.UserGroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class ChoreService {

    private final ChoreRepository choreRepository;
    private final UserGroupRepository userGroupRepository;
    private final AssignmentService assignmentService;

    public void AddChore(Chore chore){
        choreRepository.save(chore);
    }

    public String getAllChores() {
        return choreRepository.findAll().toString();
    }

    public List<Chore> getChoresByGroupId(Long groupId) {
        try {
            UserGroup group = userGroupRepository.findById(groupId).orElseThrow();
            return choreRepository.getChoresByUserGroup(group).stream()
                    .filter(chore -> chore.isEnabled())
                    .collect(Collectors.toList());
        }catch(NoSuchElementException e){
            return null;
        }
    }

    public Long deleteChore(Long id) {
        Chore chore = choreRepository.getReferenceById(id);
        Long groupId = chore.getUserGroup().getId();
        chore.setEnabled(false);
        assignmentService.deactivateAssignmentsByChoreId(chore);
        return groupId;
    }

    @Modifying
    public void updateChore(UpdateChoreRequest request) {
        Chore chore = choreRepository.findById(request.getId()).get();
        chore.setName(request.getName());
        chore.setDescription(request.getDescription());
        chore.setMultiplier(request.getMultiplier());

    }

    public void loadSampleChores (Long groupId) {
        UserGroup userGroup = userGroupRepository.findById(groupId).get();
        List<Chore> sampleChores = new ArrayList<>();
        sampleChores.add(new Chore("Sweep", "Sweep hardwood and tile floors in common area",3, userGroup));
        sampleChores.add(new Chore("Mop", "Mop hardwood and tile floors in common area",1, userGroup));
        sampleChores.add(new Chore("Laundry", "Wash, dry, and fold your personal laundry",1, userGroup));
        sampleChores.add(new Chore("Clean Room", "Pick up all clothes, toys, trash, make bed, sweep/vacuum",1, userGroup));
        sampleChores.add(new Chore("Collect Dishes", "Gather dishes from around the house and bring to sink",5, userGroup));
        sampleChores.add(new Chore("Wash Dishes", "Wash all dishes in kitchen",5, userGroup));
        sampleChores.add(new Chore("Feed Pets", "Feed and water pets",5, userGroup));
        sampleChores.add(new Chore("Clean Bathroom", "Wipe mirror, counters, sink, toilet, and sweep floor",1, userGroup));
        sampleChores.add(new Chore("Clean Doors", "Wipe dirt off of doors",1, userGroup));
        sampleChores.add(new Chore("Clean Outside", "Pick up trash and put up things left outside",1, userGroup));
        sampleChores.add(new Chore("Vacuum", "Vacuum carpets in common area",5, userGroup));
        sampleChores.add(new Chore("Clean Out Fridge", "Remove contents and wipe all shelves, clean all drawers.  Throw away old food",1, userGroup));
        sampleChores.add(new Chore("Clean Cat Box", "Scoop clumps out of cat litter",3, userGroup));
        sampleChores.add(new Chore("Scoop Poop Outside", "Collect all dog poop from backyard",1, userGroup));
        sampleChores.add(new Chore("Unload Dishwasher", "Put away clean dishes",1, userGroup));
        sampleChores.add(new Chore("Wash Windows", "Wash all windows in common area, inside and out",1, userGroup));
        sampleChores.add(new Chore("Clean Out Car", "Clean out trash from car",1, userGroup));
        sampleChores.add(new Chore("Empty Trash Cans", "Empty all small trash cans into main trash, take out if needed",1, userGroup));
        sampleChores.add(new Chore("Wipe Down Table", "Clean dining room table with wood cleaner",5, userGroup));
        sampleChores.add(new Chore("Pick Up Living Room", "Pick up trash and tidy up in common area",5, userGroup));
        choreRepository.saveAll(sampleChores);
    }
}
