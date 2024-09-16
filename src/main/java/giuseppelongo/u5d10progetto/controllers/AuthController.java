package giuseppelongo.u5d10progetto.controllers;

import giuseppelongo.u5d10progetto.exceptions.BadRequestException;
import giuseppelongo.u5d10progetto.payloads.DipendenteLoginDTO;
import giuseppelongo.u5d10progetto.payloads.DipendenteLoginRespDTO;
import giuseppelongo.u5d10progetto.payloads.NewDipendenteDTO;
import giuseppelongo.u5d10progetto.payloads.NewDipendenteRespDTO;
import giuseppelongo.u5d10progetto.services.AuthService;
import giuseppelongo.u5d10progetto.services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private DipendenteService dipendenteService;

    @PostMapping("/login")
    public DipendenteLoginRespDTO login(@RequestBody DipendenteLoginDTO payload) {
        return new DipendenteLoginRespDTO(this.authService.checkCredentialsAndGenerateToken(payload));
    }

    //Post
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewDipendenteRespDTO save(@RequestBody @Validated NewDipendenteDTO body, BindingResult validationResult) {
        // @Validated serve per 'attivare' le regole di validazione descritte nel DTO
        // BindingResult mi permette di capire se ci sono stati errori e quali errori ci sono stati

        if (validationResult.hasErrors()) {
            // Se ci sono stati errori lanciamo un'eccezione custom
            String messages = validationResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));

            throw new BadRequestException("Ci sono stati errori nel payload. " + messages);
        } else {
            // Se non ci sono stati salviamo l'utente

            return new NewDipendenteRespDTO(this.dipendenteService.save(body).getId());
        }

    }
}
