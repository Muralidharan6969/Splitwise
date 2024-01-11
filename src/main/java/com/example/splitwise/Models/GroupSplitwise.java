package com.example.splitwise.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class GroupSplitwise extends BaseModel{
    private String groupName;
    @ManyToMany (fetch = FetchType.EAGER)
    private List<UserProfile> groupMembers;
    @OneToMany (mappedBy = "groupSplitwise", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Expense> groupExpense;
    @ManyToOne
    private UserProfile groupAdmin;

    public GroupSplitwise(String groupName, List<UserProfile> groupMembers, UserProfile groupAdmin) {
        this.groupName = groupName;
        this.groupMembers = groupMembers;
        this.groupAdmin = groupAdmin;
        this.groupExpense = new ArrayList<>();
    }

    public GroupSplitwise() {

    }
}
