package com.eclock.Dto;

import com.eclock.model.UserEntity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;


public class TimesheetDto {

   @NotNull(message = "Date is required")
    private LocalDate date;

    @NotNull(message = "Clock In is required")
    private LocalTime clockIn;

    @NotNull(message = "Clock Out is required")
    private LocalTime clockOut;


    private LocalTime breakIn;
    private LocalTime breakOut;

    private  String totalHours = formatTotalWorkHours();

    private  String totalBreak = formatTotalBreakHours();

    private String note;


    private UserEntity users;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getClockIn() {
        return clockIn;
    }

    public void setClockIn(LocalTime clockIn) {
        this.clockIn = clockIn;
    }

    public LocalTime getClockOut() {
        return clockOut;
    }

    public void setClockOut(LocalTime clockOut) {
        this.clockOut = clockOut;
    }

    public LocalTime getBreakIn() {
        return breakIn;
    }

    public void setBreakIn(LocalTime breakIn) {
        this.breakIn = breakIn;
    }

    public LocalTime getBreakOut() {
        return breakOut;
    }

    public void setBreakOut(LocalTime breakOut) {
        this.breakOut = breakOut;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public UserEntity getUsers() {
        return users;
    }

    public void setUsers(UserEntity users) {
        this.users = users;
    }


    public String getTotalHours() {
        return totalHours;
    }

    public String getTotalBreak() {
        return totalBreak;
    }

    public void setTotalHours(String totalHours) {
        this.totalHours = totalHours;
    }

    public void setTotalBreak(String totalBreak) {
        this.totalBreak = totalBreak;
    }



    //calculate total work hours
    public Duration calculateTotalWorkHours() {
        if(clockIn == null || clockOut == null) {
            return Duration.ZERO;
        }
        return Duration.between(clockIn, clockOut);
    }

    //calculate total break hours
    public Duration calculateTotalBreakHours() {
        if(breakIn == null || breakOut == null) {
            return Duration.ZERO;
        }
        return Duration.between(breakIn, breakOut);
    }

    //Format the work hours
    public String formatTotalWorkHours() {
        Duration duration = calculateTotalWorkHours();
        return String.format("%02d:%02d", duration.toHours(), duration.toMinutesPart());
    }

    //Format the break hours
    public String formatTotalBreakHours() {
        Duration duration = calculateTotalBreakHours();
        return String.format("%02d:%02d", duration.toHours(), duration.toMinutesPart());
    }
}
