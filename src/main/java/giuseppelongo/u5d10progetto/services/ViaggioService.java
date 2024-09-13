package giuseppelongo.u5d10progetto.services;

import giuseppelongo.u5d10progetto.entities.Viaggio;
import giuseppelongo.u5d10progetto.enums.StatoViaggio;
import giuseppelongo.u5d10progetto.exceptions.NotFoundException;
import giuseppelongo.u5d10progetto.payloads.NewViaggioDTO;
import giuseppelongo.u5d10progetto.repositories.ViaggiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ViaggioService {

    @Autowired
    private ViaggiRepository viaggioRepository;


    public Viaggio save(NewViaggioDTO viaggioDTO) {
        Viaggio viaggio = new Viaggio(
                viaggioDTO.destinazione(),
                viaggioDTO.data(),
                StatoViaggio.IN_PROGRAMMA
        );
        return viaggioRepository.save(viaggio);
    }


    public Page<Viaggio> findAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return viaggioRepository.findAll(pageable);
    }


    public Viaggio findById(UUID id) {
        return viaggioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }


    public void deleteById(UUID id) {
        Viaggio viaggio = this.findById(id);
        viaggioRepository.delete(viaggio);
    }


    public Viaggio update(UUID id, NewViaggioDTO viaggioDTO) {
        Viaggio viaggio = this.findById(id);
        viaggio.setDestinazione(viaggioDTO.destinazione());
        viaggio.setData(viaggioDTO.data());
        viaggio.setStato(viaggioDTO.stato());

        return viaggioRepository.save(viaggio);
    }


    public Viaggio updateStato(UUID id, StatoViaggio nuovoStato) {
        Viaggio viaggio = this.findById(id);
        viaggio.setStato(nuovoStato);
        return viaggioRepository.save(viaggio);
    }
}
