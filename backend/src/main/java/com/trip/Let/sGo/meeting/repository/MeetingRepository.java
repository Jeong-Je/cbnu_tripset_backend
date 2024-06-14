package com.trip.Let.sGo.meeting.repository;

import com.trip.Let.sGo.meeting.entity.MeetingEntity;
import com.trip.Let.sGo.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeetingRepository extends JpaRepository<MeetingEntity, Integer> {
    List<MeetingEntity> findByUser(UserEntity user);
}
