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
    void when_FindAll_does_not_find_any_video_an_VideoNotFoundException_should_be_thrown_successfully() {
        assertThrows(VideoNotFoundException.class, () -> culturemediaService.findAll());
    }

    @Test
    void when_FindAll_all_videos_should_be_returned_successfully() throws VideoNotFoundException, DurationNotValidException {
        saveVideos();  // Simula la inserción de videos en el repositorio

        List<Video> allVideos = culturemediaService.findAll();  // Llama al método findAll

        assertFalse(allVideos.isEmpty(), "La lista de videos no debería estar vacía.");
        assertEquals(6, allVideos.size(), "Debería haber exactamente 6 videos en el repositorio.");
    }

    @Test
    void should_Return_Videos_By_Title_Containing_Specific_Word() throws VideoNotFoundException, DurationNotValidException {
        saveVideos();

        List<Video> filteredVideos = culturemediaService.find("Click");
        assertEquals(2, filteredVideos.size());
    }

    @Test
    void should_Throw_VideoNotFoundException_When_No_Video_Title_Matches() {
        assertThrows(VideoNotFoundException.class, () -> culturemediaService.find("Click"));
    }

    @Test
    void should_Return_Videos_Within_Specified_Duration_Range() throws VideoNotFoundException, DurationNotValidException {
        saveVideos();

        List<Video> videosInRange = culturemediaService.find(4.5, 5.5);
        assertEquals(3, videosInRange.size());
    }

    @Test
    void should_Throw_VideoNotFoundException_When_No_Videos_Within_Duration_Range() {
        assertThrows(VideoNotFoundException.class, () -> culturemediaService.find(4.5, 5.5));
    }
}
