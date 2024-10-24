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

public class CulturemediaServiceImplTest {
    private CulturemediaService culturemediaService;

    @BeforeEach
    void setUp() {
        VideoRepository videoRepository = new VideoRepositoryImpl();
        ViewsRepository viewsRepository = new ViewsRepositoryImpl();
        culturemediaService = new CulturemediaServiceImpl(videoRepository, viewsRepository);
    }

    @Test
    void when_FindAll_finds_all_videos_successfully() throws VideoNotFoundException, DurationNotValidException {

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

        List<Video> videos = culturemediaService.findAll();
        assertEquals(6, videos.size());
    }
    @Test
    void when_FindAll_does_not_find_any_video_an_VideoNotFoundException_should_be_thrown_successfully() {
        assertThrows(VideoNotFoundException.class, () -> culturemediaService.findAll());
    }
}
