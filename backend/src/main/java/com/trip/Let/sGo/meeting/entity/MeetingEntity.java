package com.trip.Let.sGo.meeting.entity;

import com.trip.Let.sGo.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MeetingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private UserEntity user;

    private String title;
    private String startDate;
    private String endDate;
    private String color;
}
