package culturemedia.controllers;

import java.util.List;

import culturemedia.exception.DurationNotValidException;
import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.service.CulturemediaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class CultureMediaController {

    private final CulturemediaService cultureMediaService;
    
    public CultureMediaController(CulturemediaService cultureMediaService) {
        this.cultureMediaService = cultureMediaService;
    }

    @GetMapping("/videos")
    public List<Video> findAllVideos() throws VideoNotFoundException {
        List<Video> videos;
        videos = cultureMediaService.findAll();
        return videos;
    }

    @PostMapping("/videos")
    public ResponseEntity<Video> save(@RequestBody Video video) throws DurationNotValidException {
        cultureMediaService.save(video);
        return ResponseEntity.status(HttpStatus.OK).body(video);
    }
}
