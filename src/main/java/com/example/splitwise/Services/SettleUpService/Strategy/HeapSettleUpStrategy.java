package com.example.splitwise.Services.SettleUpService.Strategy;

import com.example.splitwise.Models.ENUMS.ExpenseType;
import com.example.splitwise.Models.ENUMS.ExpenseUserType;
import com.example.splitwise.Models.Expense;
import com.example.splitwise.Models.ExpenseUser;
import com.example.splitwise.Models.UserProfile;
import com.example.splitwise.Services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class HeapSettleUpStrategy implements SettleUpStrategy{
    private ExpenseService expenseService;

    @Autowired
    public HeapSettleUpStrategy(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @Override
    public List<Expense> settleUpHelper(List<Expense> expenseList, List<UserProfile> userProfileList) {

        //For each user from the userProfileList we need to find totalAmount
        List<UserAmount> userAmountList = new ArrayList<>();
        for(UserProfile u : userProfileList) {
            UserAmount userAmount = getUserAmount(expenseList, u);
            userAmountList.add(userAmount);
        }

        //Now we need to separate the UserAmount objects from our list into min and max heaps
        PriorityQueue<UserAmount> minHeap = new PriorityQueue<>((a,b) -> a.getTotalAmount() - b.getTotalAmount());
        PriorityQueue<UserAmount> maxHeap = new PriorityQueue<>((a,b) -> b.getTotalAmount() - a.getTotalAmount());
        List<Expense> transactionExpense = new ArrayList<>();
        for(UserAmount uA : userAmountList){
            if(uA.getTotalAmount() > 0){
                maxHeap.add(uA);
            }
            else if(uA.getTotalAmount() < 0){
                minHeap.add(uA);
            }
        }
        while(!maxHeap.isEmpty() && !minHeap.isEmpty()){
            UserAmount x = maxHeap.poll();
            UserAmount y = minHeap.poll();
            int diff = x.getTotalAmount() + y.getTotalAmount();
            if(diff > 0){   //Y has to pay y.getTotalAmount() to X and is settled
                Expense dummyExpense = createDummyExpense(x.getUserProfile(), y.getUserProfile(),
                        Math.abs(y.getTotalAmount()));
                transactionExpense.add(dummyExpense);
                x.setTotalAmount(diff);
                maxHeap.add(x);
            }
            else if(diff < 0){ //Y has to pay y.getTotalAmount() to X and is not settled
                Expense dummyExpense = createDummyExpense(x.getUserProfile(), y.getUserProfile(),
                        Math.abs(x.getTotalAmount()));
                transactionExpense.add(dummyExpense);
                y.setTotalAmount(diff);
                minHeap.add(y);
            }
            else{
                Expense dummyExpense = createDummyExpense(x.getUserProfile(), y.getUserProfile(),
                        Math.abs(x.getTotalAmount()));
                transactionExpense.add(dummyExpense);
            }
        }
        return transactionExpense;
    }

    private Expense createDummyExpense(UserProfile userProfileX, UserProfile userProfileY, int amount) {
        return expenseService.createDummyExpense(amount, userProfileX, userProfileY);
    }

    private UserAmount getUserAmount(List<Expense> expenseList, UserProfile u) {
        UserAmount userAmount = new UserAmount();
        userAmount.setUserProfile(u);
        for (Expense e : expenseList) {
            for (ExpenseUser eu : e.getExpenseUsers()) {
                if (eu.getUserProfile().equals(u)) {
                    if (eu.getExpenseUserType().equals(ExpenseUserType.PAID)) {
                        userAmount.setTotalAmount(userAmount.getTotalAmount() + eu.getAmountInvolved());
                    } else {
                        userAmount.setTotalAmount(userAmount.getTotalAmount() - eu.getAmountInvolved());
                    }
                }
            }
        }
        return userAmount;
    }

    @Override
    public List<Expense> settleUpUserHelper(List<Expense> expenseList) {
        //We first need to find all the users involved in the input Expense List
        Set<UserProfile> userProfileSet = new HashSet<>();
        for(Expense e : expenseList) {
            for (ExpenseUser eu : e.getExpenseUsers()) {
                userProfileSet.add(eu.getUserProfile());
            }
        }
        List<UserProfile> userProfileList = userProfileSet.stream().toList();
        return settleUpHelper(expenseList, userProfileList);
    }

    @Override
    public List<Expense> settleUpGroupHelper(List<Expense> expenseList, List<UserProfile> groupMembers) {
        return settleUpHelper(expenseList, groupMembers);
    }
}
