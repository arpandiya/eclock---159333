package com.eclock.service;

import com.eclock.Dto.TimesheetDto;
import com.eclock.model.TimesheetEntity;

import java.util.List;


public interface ITimesheetService {
    TimesheetEntity createTimesheet(TimesheetDto timesheetDto);

    List<TimesheetDto> getAllTimesheets();

}
