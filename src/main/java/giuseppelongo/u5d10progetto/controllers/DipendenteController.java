package giuseppelongo.u5d10progetto.controllers;

import giuseppelongo.u5d10progetto.entities.Dipendente;
import giuseppelongo.u5d10progetto.payloads.NewDipendenteDTO;
import giuseppelongo.u5d10progetto.services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {

    @Autowired
    private DipendenteService dipendenteService;

    // GET
    @GetMapping
    public ResponseEntity<Page<Dipendente>> getAllDipendenti(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nome") String sortBy) {
        Page<Dipendente> dipendenti = dipendenteService.findAll(page, size, sortBy);
        return new ResponseEntity<>(dipendenti, HttpStatus.OK);
    }

    // GET + id
    @GetMapping("/{id}")
    public ResponseEntity<Dipendente> getDipendenteById(@PathVariable UUID id) {
        Dipendente dipendente = dipendenteService.findById(id);
        return new ResponseEntity<>(dipendente, HttpStatus.OK);
    }

    // POST
    @PostMapping
    public ResponseEntity<Dipendente> createDipendente(@RequestBody NewDipendenteDTO dipendenteDTO) {
        Dipendente newDipendente = dipendenteService.save(dipendenteDTO);
        return new ResponseEntity<>(newDipendente, HttpStatus.CREATED);
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<Dipendente> updateDipendente(
            @PathVariable UUID id, @RequestBody Dipendente updatedDipendente) {
        Dipendente updated = dipendenteService.findByIdAndUpdate(id, updatedDipendente);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDipendente(@PathVariable UUID id) {
        dipendenteService.findByIdAndDelete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // POST: immagine
    @PostMapping("/{id}/avatar")
    public ResponseEntity<String> uploadAvatar(@PathVariable UUID id, @RequestParam("file") MultipartFile file) {
        try {
            String url = dipendenteService.uploadImage(file);
            Dipendente dipendente = dipendenteService.findById(id);
            dipendente.setAvatarURL(url);
            dipendenteService.findByIdAndUpdate(id, dipendente);
            return new ResponseEntity<>("Avatar caricato con successo: " + url, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Errore durante l'upload dell'avatar", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
