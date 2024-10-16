package culturemedia.exception;

public class VideoNotFoundException extends CulturemediaException {

    // Constructor sin parámetros
    public VideoNotFoundException() {
        super("Video no encontrado"); // Mensaje por defecto
    }

    // Constructor con parámetro
    public VideoNotFoundException(String title) {
        super("Video no encontrado: " + title); // Mensaje por personalizado
    }
}
