package culturemedia.service.impl;

import culturemedia.exception.DurationNotValidException;
import culturemedia.model.Video;
import culturemedia.exception.VideoNotFoundException;
import culturemedia.repository.ViewsRepository;
import culturemedia.repository.VideoRepository;
import culturemedia.service.CulturemediaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CulturemediaServiceImplTest {
    private CulturemediaService culturemediaService;

    @Mock
    private VideoRepository videoRepository;

    @Mock
    private ViewsRepository viewsRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        culturemediaService = new CulturemediaServiceImpl(videoRepository, viewsRepository);
    }

    List<Video> mockVideos = List.of(
            new Video("01", "Título 1", "----", 4.5),
            new Video("02", "Título 2", "----", 5.5),
            new Video("03", "Título 3", "----", 4.4),
            new Video("04", "Título 4", "----", 3.5),
            new Video("05", "Clic 5", "----", 5.7),
            new Video("06", "Clic 6", "----", 5.1)
    );

    @Test
    void when_no_videos_are_found_findAll_throws_VideoNotFoundException_successfully() throws VideoNotFoundException {
        mock_findAll_videos_returned_successfully();
        List<Video> videos = culturemediaService.findAll();
        assertEquals(6, videos.size());
    }

    @Test
    void when_findAll_finds_videos_then_return_all_videos_successfully() throws VideoNotFoundException, DurationNotValidException {
        assertThrows(VideoNotFoundException.class, () -> culturemediaService.findAll());
    }

    @Test
    void when_searching_by_title_should_return_videos_containing_specific_word() throws VideoNotFoundException, DurationNotValidException {
        mock_find_by_title_videos_returned_successfully("Clic");
        List<Video> filteredVideos = culturemediaService.find("Clic");
        assertEquals(2, filteredVideos.size());
    }

    @Test
    void when_no_video_title_matches_should_throw_VideoNotFoundException() {
        assertThrows(VideoNotFoundException.class, () -> culturemediaService.find("Clic"));
    }

    @Test
    void given_duration_range_should_return_videos_within_that_range() throws VideoNotFoundException, DurationNotValidException {
        mock_fain_by_duration_videos_returned_successfully(3.5, 4.5);
        List<Video> videosInRange = culturemediaService.find(3.5, 4.5);
        assertEquals(3, videosInRange.size());
    }

    @Test
    void when_no_videos_are_within_duration_range_should_throw_VideoNotFoundException() {
        assertThrows(VideoNotFoundException.class, () -> culturemediaService.find(3.5, 4.5));
    }


    private void mock_findAll_videos_returned_successfully() {
        doReturn(mockVideos)
                .when(videoRepository)
                .findAll();
    }
    private void mock_find_by_title_videos_returned_successfully(String title) {
        List<Video> filteredVideos = new ArrayList<>();
        for ( Video video : mockVideos ) {
            if(video.title().contains(title)){
                filteredVideos.add(video);
            }
        }
        doReturn(filteredVideos)
                .when(videoRepository)
                .find(title);
    }
    private void mock_fain_by_duration_videos_returned_successfully(Double fromDuration, Double toDuration) {
        List<Video> filteredVideos = new ArrayList<>();
        for ( Video video : mockVideos ) {
            if(video.duration() >= fromDuration && video.duration() <= toDuration){
                filteredVideos.add(video);
            }
        }
        doReturn(filteredVideos)
                .when(videoRepository)
                .find(fromDuration, toDuration);
    }
}
