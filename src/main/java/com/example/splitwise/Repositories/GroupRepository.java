package com.example.splitwise.Repositories;

import com.example.splitwise.Models.GroupSplitwise;
import com.example.splitwise.Models.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<GroupSplitwise, Long> {
    @Override
    Optional<GroupSplitwise> findById(Long groupId);
    GroupSplitwise save(GroupSplitwise groupSplitwise);
}
