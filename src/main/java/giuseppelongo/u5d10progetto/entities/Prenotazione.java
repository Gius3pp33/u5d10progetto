package giuseppelongo.u5d10progetto.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "prenotazioni")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Prenotazione {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;
    private LocalDate dataRichiesta;
    private String note;
    private String preferenze;

    @ManyToOne
    private Dipendente dipendente;

    @ManyToOne
    private Viaggio viaggio;

    public Prenotazione(LocalDate dataRichiesta, String note, String preferenze, Dipendente dipendente, Viaggio viaggio) {
        this.dataRichiesta = dataRichiesta;
        this.note = note;
        this.preferenze = preferenze;
        this.dipendente = dipendente;
        this.viaggio = viaggio;
    }
}