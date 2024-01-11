package com.example.splitwise.Services;

import com.example.splitwise.Models.Expense;
import com.example.splitwise.Models.GroupSplitwise;
import com.example.splitwise.Models.UserProfile;
import com.example.splitwise.Repositories.GroupRepository;
import com.example.splitwise.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    private UserRepository userRepository;
    private GroupRepository groupRepository;

    public GroupService(UserRepository userRepository, GroupRepository groupRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }
    public GroupSplitwise createGroup(String groupName, Long groupAdmin, List<Long> groupMembers) throws Exception {
        Optional<UserProfile> groupAdminProfile = userRepository.findById(groupAdmin);
        if(groupAdminProfile.isEmpty()){
            throw new Exception();
        }
        List<UserProfile> groupMembersProfile = userRepository.findAllById(groupMembers);
        GroupSplitwise groupSplitwise = new GroupSplitwise(groupName, groupMembersProfile, groupAdminProfile.get());
        return groupRepository.save(groupSplitwise);
    }

    public GroupSplitwise updateGroupExpense(GroupSplitwise groupSplitwise, Expense expense) {
        groupSplitwise.getGroupExpense().add(expense);
        return groupRepository.save(groupSplitwise);
    }
}
