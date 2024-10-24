package culturemedia.service.impl;

import culturemedia.exception.DurationNotValidException;
import culturemedia.model.Video;
import culturemedia.exception.VideoNotFoundException;
import culturemedia.repository.ViewsRepository;
import culturemedia.repository.impl.VideoRepositoryImpl;
import culturemedia.repository.VideoRepository;
import culturemedia.repository.impl.ViewsRepositoryImpl;
import culturemedia.service.CulturemediaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CulturemediaServiceImplTest {
    private CulturemediaService culturemediaService;

    @BeforeEach
    void setUp() {
        VideoRepository videoRepository = new VideoRepositoryImpl();
        ViewsRepository viewsRepository = new ViewsRepositoryImpl();
        culturemediaService = new CulturemediaServiceImpl(videoRepository, viewsRepository);
    }

    void saveVideos() throws DurationNotValidException {

        List<Video> mockVideos = List.of(
                new Video("01", "Título 1", "----", 4.5),
                new Video("02", "Título 2", "----", 5.5),
                new Video("03", "Título 3", "----", 4.4),
                new Video("04", "Título 4", "----", 3.5),
                new Video("05", "Clic 5", "----", 5.7),
                new Video("06", "Clic 6", "----", 5.1)
        );

        for ( Video video : mockVideos ) {
            culturemediaService.save( video );
        }
    }
    @Test
    void when_no_videos_are_found_findAll_throws_VideoNotFoundException_successfully() {
        assertThrows(VideoNotFoundException.class, () -> culturemediaService.findAll());
    }

    @Test
    void when_findAll_finds_videos_then_return_all_videos_successfully() throws VideoNotFoundException, DurationNotValidException {
        saveVideos();  // Simula la inserción de videos en el repositorio

        List<Video> allVideos = culturemediaService.findAll();  // Llama al método findAll

        assertFalse(allVideos.isEmpty(), "La lista de videos no debería estar vacía.");
        assertEquals(6, allVideos.size(), "Debería haber exactamente 6 videos en el repositorio.");
    }

    @Test
    void when_searching_by_title_should_return_videos_containing_specific_word() throws VideoNotFoundException, DurationNotValidException {
        saveVideos();

        List<Video> filteredVideos = culturemediaService.find("Click");
        assertEquals(2, filteredVideos.size());
    }

    @Test
    void when_no_video_title_matches_should_throw_VideoNotFoundException() {
        assertThrows(VideoNotFoundException.class, () -> culturemediaService.find("Click"));
    }

    @Test
    void given_duration_range_should_return_videos_within_that_range() throws VideoNotFoundException, DurationNotValidException {
        saveVideos();

        List<Video> videosInRange = culturemediaService.find(3.5, 4.5);
        assertEquals(3, videosInRange.size());
    }

    @Test
    void when_no_videos_are_within_duration_range_should_throw_VideoNotFoundException() {
        assertThrows(VideoNotFoundException.class, () -> culturemediaService.find(3.5, 4.5));
    }
}
