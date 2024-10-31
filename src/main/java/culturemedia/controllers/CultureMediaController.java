package culturemedia.controllers;

import java.util.List;

import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.service.CulturemediaService;

public class CultureMediaController {

    private final CulturemediaService cultureMediaService;
    
    public CultureMediaController(CulturemediaService cultureMediaService) {
        this.cultureMediaService = cultureMediaService;
    }

    public List<Video> findAllVideos() throws VideoNotFoundException {
        List<Video> videos;
        videos = cultureMediaService.findAll();
        return videos;
    }
}
