package giuseppelongo.u5d10progetto.repositories;

import giuseppelongo.u5d10progetto.entities.Dipendente;
import giuseppelongo.u5d10progetto.entities.Prenotazione;
import giuseppelongo.u5d10progetto.entities.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.UUID;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, UUID> {

    boolean existsByViaggioAndDipendenteAndDataRichiesta(Viaggio viaggio, Dipendente dipendente, LocalDate dataRichiesta);
}