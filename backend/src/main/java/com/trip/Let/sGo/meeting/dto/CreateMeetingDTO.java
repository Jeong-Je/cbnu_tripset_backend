package com.trip.Let.sGo.meeting.dto;

import com.trip.Let.sGo.user.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMeetingDTO {
    private String title;
    private String startDate;
    private String endDate;
    private String color;
}
