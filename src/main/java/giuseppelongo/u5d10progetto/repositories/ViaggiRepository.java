package giuseppelongo.u5d10progetto.repositories;


import giuseppelongo.u5d10progetto.entities.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ViaggiRepository extends JpaRepository<Viaggio, UUID> {

}
