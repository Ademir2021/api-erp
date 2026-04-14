package br.com.centroinfo.api.repository.person;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.centroinfo.api.dtos.personDTO.PersonResponseDTO;
import br.com.centroinfo.api.entities.persons.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

    String PERSON_RESPONSE_QUERY = """
            SELECT new br.com.centroinfo.api.dtos.personDTO.PersonResponseDTO(
            p.id, p.createdAt, p.updatedAt, p.name, p.age, p.dateOfBirth, p.cpf, p.rg, p.email, p.phone, p.cnpj, p.inscricState,
            b.id, b.name,
            u.id, u.login,
            p.gender,
            p.typePerson,
            g.id, g.name,
            addr.id, addr.street, addr.number, addr.neighborhood, addr.complement,
            z.id, z.code,
            c.id, c.name,
            s.id, s.name, s.acronym,
            co.id, co.name, co.acronym, co.ddi, co.codeCountry, co.codeRevenue
            )
            FROM Person p
            JOIN p.branch b
            JOIN p.user u
            JOIN p.groupPerson g
            JOIN p.address addr
            JOIN addr.zipCode z
            JOIN z.city c
            JOIN c.state s
            JOIN c.country co
            """;

    @Query(PERSON_RESPONSE_QUERY + " WHERE p.name LIKE %:name%")
    List<PersonResponseDTO> findPersonsByName(@Param("name") String name);

    @Query(PERSON_RESPONSE_QUERY + " ORDER BY p.id")
    List<PersonResponseDTO> findPersonsAll();

    @Query(PERSON_RESPONSE_QUERY + " WHERE (:userId IS NULL OR u.id = :userId)")
    List<PersonResponseDTO> listAllPersonsByUsers(@Param("userId") Long userId);
}
