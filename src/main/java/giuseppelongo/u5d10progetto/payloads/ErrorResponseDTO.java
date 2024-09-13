package giuseppelongo.u5d10progetto.payloads;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorResponseDTO {

    // Getters
    private String message;
    private LocalDateTime timestamp;

    public ErrorResponseDTO(String message, LocalDateTime timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

}
