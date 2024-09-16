package giuseppelongo.u5d10progetto.payloads;

public record NewDipendenteDTO(
        String username,
        String name,
        String surname,
        String email,
        String avatar,
        String password
) {

}
