package giuseppelongo.u5d10progetto.controllers;

import giuseppelongo.u5d10progetto.entities.Prenotazione;
import giuseppelongo.u5d10progetto.services.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @GetMapping
    public List<Prenotazione> getAllPrenotazioni() {
        return prenotazioneService.getPrenotazioni();
    }

    @GetMapping("/{id}")
    public Prenotazione getPrenotazioneById(@PathVariable UUID id) {
        return prenotazioneService.getPrenotazioneById(id);
    }

    @PostMapping
    public ResponseEntity<Prenotazione> creaPrenotazione(@RequestBody Prenotazione prenotazione) {
        Prenotazione nuovaPrenotazione = prenotazioneService.creaPrenotazione(prenotazione);
        return ResponseEntity.ok(nuovaPrenotazione);
    }

    @PutMapping("/{id}")
    public Prenotazione aggiornaPrenotazione(@PathVariable UUID id, @RequestBody Prenotazione prenotazione) {
        return prenotazioneService.aggiornaPrenotazione(id, prenotazione);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancellaPrenotazione(@PathVariable UUID id) {
        prenotazioneService.cancellaPrenotazione(id);
        return ResponseEntity.ok("Prenotazione cancellata con successo");
    }
}
