package com.eclock.service.impl;

import com.eclock.Dto.TimesheetDto;
import com.eclock.mapper.TimesheetMapper;
import com.eclock.model.TimesheetEntity;
import com.eclock.repository.TimesheetRepository;
import com.eclock.service.ITimesheetService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimesheetService implements ITimesheetService {

   private final TimesheetRepository timesheetRepository;
   private final TimesheetMapper timesheetMapper;

    public TimesheetService(TimesheetRepository timesheetRepository, TimesheetMapper timesheetMapper) {
        this.timesheetRepository = timesheetRepository;
        this.timesheetMapper = timesheetMapper;
    }

    @Override
    public TimesheetEntity createTimesheet(TimesheetDto timesheetDto) {
         return timesheetRepository.save(timesheetMapper.mapToTimesheetEntity(timesheetDto));
    }

    @Override
    public List<TimesheetDto> getAllTimesheets() {
        return timesheetRepository.findAll().stream().map(timesheetMapper::mapToTimesheetDto).toList();

    }


}
