package com.trip.Let.sGo.meeting;

import com.trip.Let.sGo.meeting.dto.CreateMeetingDTO;
import com.trip.Let.sGo.meeting.dto.MeetingDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/meeting")
public class MeetingController {
    private final MeetingService meetingService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public MeetingDTO createMeeting(@Valid @RequestBody CreateMeetingDTO meeting, BindingResult bindingResult, Principal principal) {
        return this.meetingService.createMeeting(meeting, principal.getName());
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping()
    public List<MeetingDTO> myMeetings(Principal principal) {
        return this.meetingService.myMeetings(principal.getName());
    }

}
