package culturemedia.service;

import java.util.ArrayList;
import java.util.List;

import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.View;
import culturemedia.model.Video;
import culturemedia.exception.DurationNotValidException;
import culturemedia.repository.VideoRepository;
import culturemedia.repository.ViewsRepository;

public class CulturemediaServiceImpl implements CulturemediaService{
    private List<Video> videos = new ArrayList<>();
    private List<View> views = new ArrayList<>();


    @Override
    public List<Video> listAll() {
        return videos;
    }

    @Override
    public Video save(Video video) throws DurationNotValidException {
        if (video.duration() <= 0) {
            throw new DurationNotValidException(video.title(), video.duration());
        }
        videos.add(video); // Cambié de videos.save(video) a videos.add(video)
        return video;
    }

    @Override
    public View save(View view) throws VideoNotFoundException {
        Video video = view.video(); // Asegúrate de que este método existe en el modelo View
        boolean videoExists = false;

        // Busca si el video existe en la lista
        for (Video v : videos) {
            if (v.title().equals(video.title())) { // Verifica si el título coincide
                videoExists = true;
                break;
            }
        }

        if (!videoExists) {
            throw new VideoNotFoundException(video.title());
        }

        views.add(view);
        return view;
    }

}
