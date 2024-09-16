package giuseppelongo.u5d10progetto.services;

import giuseppelongo.u5d10progetto.entities.Dipendente;
import giuseppelongo.u5d10progetto.exceptions.UnauthorizedException;
import giuseppelongo.u5d10progetto.payloads.DipendenteLoginDTO;
import giuseppelongo.u5d10progetto.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private DipendenteService dipendenteService;

    @Autowired
    private JWTTools jwtTools;

    public String checkCredentialsAndGenerateToken(DipendenteLoginDTO body) {
        // 1. Controllo le credenziali
        // 1.1 Cerco nel db tramite email se esiste l'utente
        Dipendente found = this.dipendenteService.findByEmail(body.email());
        if (found.getPassword().equals(body.password())) {
            // 1.2 Se lo trovo verifico se la pw trovata combacia con quella passataci tramite body
            // 2. Se è tutto ok --> genero un access token e lo restituisco
            return jwtTools.createToken(found);
        } else {
            // 3. Se le credenziali sono errate --> 401 (Unauthorized)
            throw new UnauthorizedException("Credenziali errate!");
        }


    }

}
