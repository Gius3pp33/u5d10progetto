package giuseppelongo.u5d10progetto.controllers;

import giuseppelongo.u5d10progetto.entities.Viaggio;
import giuseppelongo.u5d10progetto.enums.StatoViaggio;
import giuseppelongo.u5d10progetto.payloads.NewViaggioDTO;
import giuseppelongo.u5d10progetto.services.ViaggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/viaggi")
public class ViaggioController {

    @Autowired
    private ViaggioService viaggioService;

    // GET
    @GetMapping
    public ResponseEntity<Page<Viaggio>> getAllViaggi(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "data") String sortBy) {
        Page<Viaggio> viaggi = viaggioService.findAll(page, size, sortBy);
        return new ResponseEntity<>(viaggi, HttpStatus.OK);
    }

    // GET + id
    @GetMapping("/{id}")
    public ResponseEntity<Viaggio> getViaggioById(@PathVariable UUID id) {
        Viaggio viaggio = viaggioService.findById(id);
        return new ResponseEntity<>(viaggio, HttpStatus.OK);
    }

    // POST
    @PostMapping
    public ResponseEntity<Viaggio> createViaggio(@RequestBody NewViaggioDTO viaggioDTO) {
        Viaggio newViaggio = viaggioService.save(viaggioDTO);
        return new ResponseEntity<>(newViaggio, HttpStatus.CREATED);
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<Viaggio> updateViaggio(@PathVariable UUID id, @RequestBody NewViaggioDTO viaggioDTO) {
        Viaggio updatedViaggio = viaggioService.update(id, viaggioDTO);
        return new ResponseEntity<>(updatedViaggio, HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteViaggio(@PathVariable UUID id) {
        viaggioService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // PUT + stato
    @PutMapping("/{id}/stato")
    public ResponseEntity<Viaggio> updateStatoViaggio(@PathVariable UUID id, @RequestParam StatoViaggio stato) {
        Viaggio updatedViaggio = viaggioService.updateStato(id, stato);
        return new ResponseEntity<>(updatedViaggio, HttpStatus.OK);
    }
}
