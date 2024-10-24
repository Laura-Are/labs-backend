package culturemedia.service.impl;

import java.util.List;

import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.View;
import culturemedia.model.Video;
import culturemedia.repository.VideoRepository;
import culturemedia.repository.ViewsRepository;
import culturemedia.service.CulturemediaService;

public class CulturemediaServiceImpl implements CulturemediaService {
    private VideoRepository videoRepository;
    private ViewsRepository views;

    public CulturemediaServiceImpl(VideoRepository videoRepository, ViewsRepository viewsRepository) {
        this.videoRepository = videoRepository;
        this.views = viewsRepository;
    }
    @Override
    public List<Video> findAll() throws VideoNotFoundException {
        List<Video> videos = VideoRepository.findAll();
        if (videos.isEmpty()) {
            throw new VideoNotFoundException("No videos found.");
        }
        return videos;
    }

    @Override
    public Video save(Video save){
        videoRepository.save(save);
        return save;
    }

    @Override
    public View save(View save){
        views.save(save);
        return save;
    }
}

