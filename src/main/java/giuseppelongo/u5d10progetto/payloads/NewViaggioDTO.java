package giuseppelongo.u5d10progetto.payloads;

import giuseppelongo.u5d10progetto.enums.StatoViaggio;

import java.time.LocalDate;

public record NewViaggioDTO(
        String destinazione,
        LocalDate data,
        StatoViaggio stato
) {
}
