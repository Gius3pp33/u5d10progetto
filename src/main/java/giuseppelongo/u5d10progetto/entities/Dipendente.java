package giuseppelongo.u5d10progetto.entities;

import giuseppelongo.u5d10progetto.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "dipendenti")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Dipendente implements UserDetails {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String username;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    private String avatarURL;
    @Enumerated(EnumType.STRING)
    private Role role;

    public Dipendente(String username, String nome, String cognome, String email, String password, String avatarURL) {
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.avatarURL = avatarURL;
        this.role = Role.DIPENDENTE;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Questo metodo deve restituire una lista di ruoli dell'utente (SimpleGrantedAuthority, classe che in Spring rappresenta i ruoli degli utenti)
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
