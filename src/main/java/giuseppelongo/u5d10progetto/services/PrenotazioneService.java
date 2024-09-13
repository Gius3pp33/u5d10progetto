package giuseppelongo.u5d10progetto.services;

import giuseppelongo.u5d10progetto.entities.Prenotazione;
import giuseppelongo.u5d10progetto.exceptions.BadRequestException;
import giuseppelongo.u5d10progetto.repositories.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    public Prenotazione creaPrenotazione(Prenotazione prenotazione) {

        if (prenotazioneRepository.existsByViaggioAndDipendenteAndDataRichiesta(prenotazione.getViaggio(), prenotazione.getDipendente(), prenotazione.getDataRichiesta())) {
            throw new BadRequestException("Il dipendente ha già una prenotazione per questa data.");
        }
        return prenotazioneRepository.save(prenotazione);
    }

    public List<Prenotazione> getPrenotazioni() {
        return prenotazioneRepository.findAll();
    }

    public Prenotazione getPrenotazioneById(UUID id) {
        return prenotazioneRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Prenotazione non trovata"));
    }

    public Prenotazione aggiornaPrenotazione(UUID id, Prenotazione prenotazioneAggiornata) {
        Prenotazione prenotazione = getPrenotazioneById(id);
        prenotazione.setNote(prenotazioneAggiornata.getNote());
        prenotazione.setPreferenze(prenotazioneAggiornata.getPreferenze());
        prenotazione.setDataRichiesta(prenotazioneAggiornata.getDataRichiesta());
        return prenotazioneRepository.save(prenotazione);
    }

    public void cancellaPrenotazione(UUID id) {
        Prenotazione prenotazione = getPrenotazioneById(id);
        prenotazioneRepository.delete(prenotazione);
    }
}
