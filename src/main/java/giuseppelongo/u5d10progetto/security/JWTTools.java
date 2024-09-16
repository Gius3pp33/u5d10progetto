package giuseppelongo.u5d10progetto.security;

import giuseppelongo.u5d10progetto.entities.Dipendente;
import giuseppelongo.u5d10progetto.exceptions.UnauthorizedException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTTools {

    @Value("${jwt.secret}")
    private String secret;

    public String createToken(Dipendente dipendente) {
        return Jwts.builder()
                .issuedAt(new Date(System.currentTimeMillis())) // Data di emissione del Token (IAT - Issued At), va messa in millisecondi
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 14))//Data scadenza sempre in millisecondi
                .subject(String.valueOf(dipendente.getId()))//subject,a chi appartiene il token
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))//Con questo firmo il token, per poterlo fare devo utilizzare un algoritmo apposito e il SEGRETO
                .compact();
    }

    public void verifyToken(String token) {
        try {
            Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build().parse(token);
            // Dato un token verificami se è valido (non manipolato e non scaduto)
            // Per fare le verifiche useremo ancora una volta la libreria jsonwebtoken, la quale lancerà delle eccezioni in caso di problemi col token
            // Queste eccezioni dovranno "trasformarsi" in un 401
        } catch (Exception ex) {
            // Non importa se l'eccezione lanciata da .parse() sia dovuta a token scaduto oppure manipolato oppure malformato, per noi dovranno tutte
            // risultare in un 401
            System.out.println(ex.getMessage());
            throw new UnauthorizedException("Problemi col token! Per favore effettua di nuovo il login!");
        }
    }
}
