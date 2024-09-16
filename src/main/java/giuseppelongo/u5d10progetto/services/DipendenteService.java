package giuseppelongo.u5d10progetto.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import giuseppelongo.u5d10progetto.entities.Dipendente;
import giuseppelongo.u5d10progetto.exceptions.BadRequestException;
import giuseppelongo.u5d10progetto.exceptions.NotFoundException;
import giuseppelongo.u5d10progetto.payloads.NewDipendenteDTO;
import giuseppelongo.u5d10progetto.repositories.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class DipendenteService {
    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Autowired
    private Cloudinary cloudinaryUploader;


    public Dipendente save(NewDipendenteDTO dipendenteDTO) {
        this.dipendenteRepository.findByEmail(dipendenteDTO.email()).ifPresent(
                dipendente -> {
                    throw new BadRequestException("L'email " + dipendenteDTO.email() + " è già in uso!");
                }
        );


        Dipendente dipendente = new Dipendente(
                dipendenteDTO.username(),
                dipendenteDTO.name(),
                dipendenteDTO.surname(),
                dipendenteDTO.email(),
                dipendenteDTO.password(),
                "https://ui-avatars.com/api/?name=" + dipendenteDTO.name() + "+" + dipendenteDTO.surname()
        );


        return dipendenteRepository.save(dipendente);
    }


    public Page<Dipendente> findAll(int page, int size, String sortBy) {
        if (page > 100) page = 100;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.dipendenteRepository.findAll(pageable);
    }


    public Dipendente findById(UUID id) {
        return dipendenteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }


    public void findByIdAndDelete(UUID id) {
        Dipendente dipendente = this.findById(id);
        this.dipendenteRepository.delete(dipendente);
    }


    public Dipendente findByIdAndUpdate(UUID id, Dipendente newDipendenteDetails) {
        this.dipendenteRepository.findByEmail(newDipendenteDetails.getEmail()).ifPresent(
                dipendente -> {
                    throw new BadRequestException("L'email " + newDipendenteDetails.getEmail() + " è già in uso!");
                }
        );


        Dipendente dipendente = this.findById(id);


        dipendente.setUsername(newDipendenteDetails.getUsername());
        dipendente.setNome(newDipendenteDetails.getNome());
        dipendente.setCognome(newDipendenteDetails.getCognome());
        dipendente.setEmail(newDipendenteDetails.getEmail());
        dipendente.setAvatarURL("https://ui-avatars.com/api/?name=" + newDipendenteDetails.getNome() + "+" + newDipendenteDetails.getCognome());

        return dipendenteRepository.save(dipendente);
    }


    public String uploadImage(MultipartFile file) throws IOException {
        String url = (String) cloudinaryUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        System.out.println("URL Avatar Caricato: " + url);

        return url;
    }

    public Dipendente findByEmail(String email) {
        return dipendenteRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Il'dipendente con l'email" + email + " non è stato trovato!"));
    }
}