package giuseppelongo.u5d10progetto.payloads;

import java.time.LocalDateTime;

public class ErrorResponseDTO {

    private String message;
    private LocalDateTime timestamp;

    public ErrorResponseDTO(String message, LocalDateTime timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    // Getters
    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
