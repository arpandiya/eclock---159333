package com.eclock.controller;

import com.eclock.Dto.TimesheetDto;
import com.eclock.model.UserEntity;

import com.eclock.repository.UserRepository;
import com.eclock.service.ITimesheetService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller

public class TimesheetController {

    private final UserRepository userRepository;

    private final ITimesheetService timesheetService;

    public TimesheetController(UserRepository userRepository, ITimesheetService timesheetService) {
        this.userRepository = userRepository;
        this.timesheetService = timesheetService;
    }

    @GetMapping("/timesheet")
    public String  diplayTimesheetForm(Model model) {
        TimesheetDto timesheetDto = new TimesheetDto();
       String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();

        UserEntity user = userRepository.findByUsername(currentUser);
        timesheetDto.setUsers(user);

        model.addAttribute("timesheetDto", timesheetDto);

        return "timesheet_view/create_timesheet";
    }

    @PostMapping("/timesheet")
    public String submitTimesheet(@Valid @ModelAttribute("timesheetDto") TimesheetDto timesheetDto,
                                  BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "timesheet_view/create_timesheet";
        }


        timesheetService.createTimesheet(timesheetDto);

        return "Timesheet created successfully.";

    }

    @GetMapping("/timesheets")
    public String getAllTimesheet(Model model) {

         List<TimesheetDto> timesheets = timesheetService.getAllTimesheets();

         model.addAttribute("timesheets", timesheets);
        return "timesheet_view/timesheet_all";
    }
}
