package culturemedia.repository;
import culturemedia.model.Video;

import java.util.List;

public interface VideoRepository {
    static List<Video> findAll() {return null;}
    Video save(Video save);
    List<Video> find(String title);
    List<Video> find(Double fromDuration, Double toDuration);
}
