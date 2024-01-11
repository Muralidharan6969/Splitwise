package com.example.splitwise.Services;

import com.example.splitwise.Models.ENUMS.ExpenseType;
import com.example.splitwise.Models.ENUMS.ExpenseUserType;
import com.example.splitwise.Models.Expense;
import com.example.splitwise.Models.ExpenseUser;
import com.example.splitwise.Models.GroupSplitwise;
import com.example.splitwise.Models.UserProfile;
import com.example.splitwise.Repositories.ExpenseRepository;
import com.example.splitwise.Repositories.GroupRepository;
import com.example.splitwise.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class ExpenseService {
    private UserRepository userRepository;
    private ExpenseRepository expenseRepository;
    private ExpenseUserService expenseUserService;
    private GroupRepository groupRepository;
    private GroupService groupService;
    private Scanner scn = new Scanner(System.in);

    @Autowired
    public ExpenseService(UserRepository userRepository, ExpenseUserService expenseUserService,
                          ExpenseRepository expenseRepository, GroupRepository groupRepository, GroupService groupService) {
        this.userRepository = userRepository;
        this.expenseUserService = expenseUserService;
        this.expenseRepository = expenseRepository;
        this.groupRepository = groupRepository;
        this.groupService = groupService;
    }
    public Expense createExpense(String expenseDescription, int totalAmount, ExpenseType expenseType, Long userIdExpense) throws Exception {
        Expense expense = createExpenseHelper(expenseDescription, totalAmount, expenseType, userIdExpense, null);
        return expenseRepository.save(expense);
    }
    public UserProfile retriveUserProfile(Long userId) throws Exception {
        Optional<UserProfile> userProfile = userRepository.findById(userId);
        if(userProfile.isEmpty()){
            throw new Exception();
        }
        return userProfile.get();
    }

    public GroupSplitwise retriveGroup(Long groupId) throws Exception {
        Optional<GroupSplitwise> groupSplitwise = groupRepository.findById(groupId);
        if(groupSplitwise.isEmpty()){
            throw new Exception();
        }
        return groupSplitwise.get();
    }

    public Expense createDummyExpense(int amount, UserProfile owedBy, UserProfile paidBy){
        Expense expense = new Expense("SettleUp Transaction", amount,
                ExpenseType.DUMMY, owedBy, null);
        Expense dummyExpense = expenseRepository.save(expense);
        dummyExpense.getExpenseUsers().add(expenseUserService.createExpenseUser(dummyExpense,
                owedBy, ExpenseUserType.HAD_TO_PAY, amount));
        dummyExpense.getExpenseUsers().add(expenseUserService.createExpenseUser(dummyExpense,
                paidBy, ExpenseUserType.PAID, amount));
        return expenseRepository.save(dummyExpense);
    }

    public Expense createGroupExpense(String expenseDescription, int totalAmount, Long userIdExpense, Long groupId) throws Exception {
        GroupSplitwise groupSplitwise = retriveGroup(groupId);
        Expense expense = createExpenseHelper(expenseDescription, totalAmount, ExpenseType.REAL, userIdExpense, groupSplitwise);
        GroupSplitwise dummyAssign = groupService.updateGroupExpense(groupSplitwise, expense);
        return expenseRepository.save(expense);
    }

    public Expense createExpenseHelper(String expenseDescription, int totalAmount,
                                    ExpenseType expenseType, Long userIdExpense, GroupSplitwise groupId) throws Exception {
        Expense expense = new Expense(expenseDescription, totalAmount, expenseType,
                retriveUserProfile(userIdExpense), groupId);
        Expense intermediary = expenseRepository.save(expense);
        System.out.println("Specify the number of users who paid for this Expense");
        int paidUserCount = scn.nextInt();
        for(int i=1; i<=paidUserCount; i++){
            System.out.println("Specify the User who paid for this Expense");
            Long userId = scn.nextLong();
            UserProfile userProfile = retriveUserProfile(userId);
            System.out.println("Specify the amount paid by this user");
            int amount = scn.nextInt();
            expense.getExpenseUsers().add(expenseUserService.createExpenseUser(intermediary,
                    userProfile, ExpenseUserType.PAID, amount));
        }
        System.out.println("Specify the number of users who owe for this Expense");
        int oweUserCount = scn.nextInt();
        for(int i=1; i<=oweUserCount; i++){
            System.out.println("Specify the User who owe for this Expense");
            Long userId = scn.nextLong();
            UserProfile userProfile = retriveUserProfile(userId);
            System.out.println("Specify the amount owed by this user");
            int amount = scn.nextInt();
            expense.getExpenseUsers().add(expenseUserService.createExpenseUser(intermediary,
                    userProfile, ExpenseUserType.HAD_TO_PAY, amount));
        }
        return intermediary;
    }

    public static void printExpenseDetails(Expense expense){
        System.out.println("Expense Id: " + expense.getId());
        System.out.println("Expense Description: " + expense.getExpenseDescription());
        System.out.println("Expense Total Amount: " + expense.getExpenseTotalAmount());
        System.out.println("Expense Created By: " + expense.getCreatedBy().getUserName());
        if(expense.getGroupSplitwise()!=null){
            System.out.println("Expense Group: " + expense.getGroupSplitwise().getGroupName());
        }
        else{
            System.out.println("Expense Group: " + "Individual Expense");
        }
        System.out.println("Users and Amounts involved in this expense: ");
        for(ExpenseUser eu : expense.getExpenseUsers()){
            if(eu.getExpenseUserType().equals(ExpenseUserType.PAID)){
                System.out.println(eu.getUserProfile().getUserName()+" paid "+eu.getAmountInvolved());
            }
            else{
                System.out.println(eu.getUserProfile().getUserName()+" owes "+eu.getAmountInvolved());
            }
        }
    }

    public void deleteExpenseByDescription(String expenseDescription) {
        expenseRepository.deleteByExpenseDescription(expenseDescription);
    }

    public void deleteExpenseById(Long expenseId) {
        expenseRepository.deleteById(expenseId);
    }
    public void printExpenseById(Long expenseId) throws Exception {
        Optional<Expense> expense = expenseRepository.findById(expenseId);
        if(expense.isEmpty()){
            throw new Exception();
        }
        printExpenseDetails(expense.get());
    }

    public void printExpenseByDescription(String expenseDescription) throws Exception {
        List<Expense> expenses = expenseRepository.findAllByExpenseDescriptionContains(expenseDescription);
        if(expenses.isEmpty()){
            System.out.println("No Expense with this description exists");
        }
        else{
            System.out.println("Expenses with this description are: ");
            for (Expense expense : expenses) {
                printExpenseDetails(expense);
                System.out.println();
            }
        }
    }
}
