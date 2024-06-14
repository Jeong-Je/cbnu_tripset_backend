package com.trip.Let.sGo.meeting;

import com.trip.Let.sGo.meeting.dto.CreateMeetingDTO;
import com.trip.Let.sGo.meeting.dto.MeetingDTO;
import com.trip.Let.sGo.meeting.entity.MeetingEntity;
import com.trip.Let.sGo.meeting.repository.MeetingRepository;
import com.trip.Let.sGo.user.entity.UserEntity;
import com.trip.Let.sGo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MeetingService {
    private final MeetingRepository meetingRepository;
    private final UserRepository userRepository;
    public MeetingDTO createMeeting(CreateMeetingDTO meetingDTO, String username) {
        UserEntity user = this.userRepository.findByUsername(username);
        MeetingEntity meeting = new MeetingEntity();

        meeting.setTitle(meetingDTO.getTitle());
        meeting.setUser(user);
        meeting.setStartDate(meetingDTO.getStartDate());
        meeting.setEndDate(meetingDTO.getEndDate());
        meeting.setColor(meetingDTO.getColor());
        meetingRepository.save(meeting);

        return new MeetingDTO(meeting);
    }


    public List<MeetingDTO> myMeetings(String username) {
        UserEntity user = this.userRepository.findByUsername(username);
        List<MeetingDTO> myMeetings = this.meetingRepository.findByUser(user).stream().map((meeting) -> new MeetingDTO(meeting)).collect(Collectors.toList());

        return myMeetings;
    }
}
