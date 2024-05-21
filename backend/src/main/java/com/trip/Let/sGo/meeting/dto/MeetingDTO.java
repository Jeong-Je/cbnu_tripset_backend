package com.trip.Let.sGo.meeting.dto;

import com.trip.Let.sGo.meeting.entity.MeetingEntity;
import com.trip.Let.sGo.user.entity.UserEntity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MeetingDTO {
    private int id;

    private UserEntity user;

    private String title;
    private String startDate;
    private String endDate;
    private String color;

    public MeetingDTO(MeetingEntity meeting) {
        this.id  = meeting.getId();
        this.user = meeting.getUser();
        this.title = meeting.getTitle();
        this.startDate = meeting.getStartDate();
        this.endDate = meeting.getEndDate();
        this.color = meeting.getColor();
    }
}
