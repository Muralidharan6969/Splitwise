package com.example.splitwise.Services.SettleUpService;

import com.example.splitwise.Models.Expense;
import com.example.splitwise.Models.GroupSplitwise;
import com.example.splitwise.Repositories.GroupRepository;
import com.example.splitwise.Services.SettleUpService.Strategy.SettleUpStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupSettleUpService{
    private GroupRepository groupRepository;
    private SettleUpStrategy settleUpStrategy;

    @Autowired
    public GroupSettleUpService(GroupRepository groupRepository, SettleUpStrategy settleUpStrategy){
        this.groupRepository = groupRepository;
        this.settleUpStrategy = settleUpStrategy;
    }

    public List<Expense> settleUpGroup(Long groupId) throws Exception {
        Optional<GroupSplitwise> group = groupRepository.findById(groupId);
        if(group.isEmpty()){
            throw new Exception();
        }
        return settleUpStrategy.settleUpGroupHelper(group.get().getGroupExpense(),
                group.get().getGroupMembers());
    }
}
