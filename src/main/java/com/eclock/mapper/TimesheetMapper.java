package com.eclock.mapper;

import com.eclock.Dto.TimesheetDto;
import com.eclock.model.TimesheetEntity;
import org.springframework.stereotype.Component;

@Component
public class TimesheetMapper {

    public TimesheetDto mapToTimesheetDto(TimesheetEntity timesheetEntity) {
        TimesheetDto timesheetDto = new TimesheetDto();
        timesheetDto.setDate(timesheetEntity.getDate());
        timesheetDto.setClockIn(timesheetEntity.getClockIn());
        timesheetDto.setClockOut(timesheetEntity.getClockOut());
        timesheetDto.setBreakIn(timesheetEntity.getBreakIn());
        timesheetDto.setBreakOut(timesheetEntity.getBreakOut());
        timesheetDto.setNote(timesheetEntity.getNote());
        timesheetDto.setUsers(timesheetEntity.getUsers());
        return timesheetDto;
    }


    public TimesheetEntity mapToTimesheetEntity(TimesheetDto timesheetDto) {
        TimesheetEntity timesheetEntity = new TimesheetEntity();
        timesheetEntity.setDate(timesheetDto.getDate());
        timesheetEntity.setClockIn(timesheetDto.getClockIn());
        timesheetEntity.setClockOut(timesheetDto.getClockOut());
        timesheetEntity.setBreakIn(timesheetDto.getBreakIn());
        timesheetEntity.setBreakOut(timesheetDto.getBreakOut());
        timesheetEntity.setTotalHours(timesheetDto.getTotalHours());
        timesheetEntity.setTotalBreak(timesheetDto.getTotalBreak());
        timesheetEntity.setNote(timesheetDto.getNote());
        timesheetEntity.setUsers(timesheetDto.getUsers());
        return timesheetEntity;
    }
}
