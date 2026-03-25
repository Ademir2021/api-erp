package br.com.centroinfo.api.repository.address;
import br.com.centroinfo.api.dtos.addressDTO.AddressResponseDTO;
import br.com.centroinfo.api.entities.address.address.Address;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query("SELECT new br.com.centroinfo.api.dtos.addressDTO.AddressResponseDTO(" +
            "a.id, a.street, a.number, a.neighborhood, a.complement, " +
            "p.id, p.name, z.id, z.code, c.id, c.name, c.codeIbge,  " +
            "co.id, co.name, co.acronym, s.id, s.name, s.acronym)" +
            "FROM Address a " +
            "JOIN a.person p " +
            "JOIN a.zipCode z " +
            "JOIN z.city c " +
            "JOIN c.state s " +
            "JOIN c.country co ORDER BY a.id ASC")
    List<AddressResponseDTO> findAllAddresses();
}
