package com.example.mareu.usescases;

import com.example.mareu.factory.MeetingRepository;
import com.example.mareu.model.Meeting;
import com.example.mareu.useCases.meetings.GetMeetingByIdUseCase;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetMeetingByIdUseCaseTest {

    GetMeetingByIdUseCase useCase;
    MeetingRepository repository;

    @Test
    public void call() {
        // GIVEN
        Meeting meeting = new Meeting(99,"Sujet",5,20,10);
        when(repository.getMeeting(anyInt())).thenReturn(meeting);

        // WHEN
        Meeting result = useCase.call(99);

        // THEN
        assertEquals(result.getId(), 99);
        assertEquals(result.getSujet(), "Sujet");
    }


    @Before
    public void init() {
        repository = mock(MeetingRepository.class);
        useCase = new GetMeetingByIdUseCaseFake();
    }

    public class GetMeetingByIdUseCaseFake extends GetMeetingByIdUseCase {
        @Override
        protected MeetingRepository getMeetingRepository() {
            return repository;
        }
    }
}
