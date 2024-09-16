package giuseppelongo.u5d10progetto.payloads;


import java.time.LocalDateTime;


public record ErrorResponseDTO(String message, LocalDateTime timestamp) {
}

