package com.example.splitwise.Controllers;

import com.example.splitwise.DTO.SettleUpGroupRequestDTO;
import com.example.splitwise.DTO.SettleUpGroupResponseDTO;
import com.example.splitwise.DTO.SettleUpUserRequestDTO;
import com.example.splitwise.DTO.SettleUpUserResponseDTO;
import com.example.splitwise.Models.Expense;
import com.example.splitwise.Services.SettleUpService.GroupSettleUpService;
import com.example.splitwise.Services.SettleUpService.UserSettleUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SettleUpController {
    private UserSettleUpService userSettleUpService;
    private GroupSettleUpService groupSettleUpService;

    @Autowired
    public SettleUpController(UserSettleUpService userSettleUpService, GroupSettleUpService groupSettleUpService) {
        this.userSettleUpService = userSettleUpService;
        this.groupSettleUpService = groupSettleUpService;
    }

    public SettleUpUserResponseDTO settleUpUser(SettleUpUserRequestDTO userReqDto) throws Exception {
        List<Expense> expenseSuggestion = userSettleUpService.settleUpUser(userReqDto.getUserId());
        return new SettleUpUserResponseDTO(expenseSuggestion);
    }

    public SettleUpGroupResponseDTO settleUpGroup(SettleUpGroupRequestDTO groupRequestDTO) throws Exception {
        List<Expense> expenseSuggestion = groupSettleUpService.settleUpGroup(groupRequestDTO.getGroupId());
        return new SettleUpGroupResponseDTO(expenseSuggestion);
    }
}
