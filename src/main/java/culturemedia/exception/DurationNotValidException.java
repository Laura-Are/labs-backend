package culturemedia.exception;

public class DurationNotValidException extends CulturemediaException {
    public DurationNotValidException(String title, Double duration) {
        super("Duracion no valida para el video: "+ title + ": " + duration);
    }
}
