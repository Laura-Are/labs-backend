package culturemedia.service;

import java.util.List;

import culturemedia.exception.DurationNotValidException;
import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.View;
import culturemedia.model.Video;

public interface CulturemediaService {
    List<Video> findAll() throws VideoNotFoundException;
    Video save(Video save) throws DurationNotValidException;
    View save(View save) throws VideoNotFoundException;

    List<Video> find(String title) throws VideoNotFoundException;

    List<Video> find(Double fromDuration, Double toDuration) throws VideoNotFoundException;
}
