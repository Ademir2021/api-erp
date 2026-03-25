package br.com.centroinfo.api.dtos.personDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.centroinfo.api.entities.persons.Gender;
import br.com.centroinfo.api.entities.persons.TypePerson;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonResponseDTO {
        private Long id;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private String name;
        private int age;
        private LocalDate dateOfBirth;
        private Gender gender;
        private String cpf;
        private String rg;
        private String email;
        private String phone;
        private String cnpj;
        private String inscricState;
        private BranchResponseDTO branch;
        private UserResponseDTO user;
        private TypePerson typePerson;
        private GroupPersonResponseDTO groupPerson;
        private AddressResponseDTO address;
        
        public PersonResponseDTO(
                        Long id,
                        LocalDateTime createdAt,
                        LocalDateTime updatedAt,
                        String name,
                        int age,
                        LocalDate dateOfBirth,
                        String cpf,
                        String rg,
                        String email,
                        String phone,
                        String cnpj,
                        String inscricState,
                        Long bId,
                        String bName,
                        Long uId,
                        String uLogin,
                        Gender gender,
                        TypePerson typePerson,
                        long gId,
                        String gName,
                        Long AddrId,
                        String AddrStreet,
                        String AddrNumber,
                        String AddrNeighborhood,
                        String AddrComplemet,
                        Long zipId,
                        String zipCode,
                        Long cId,
                        String cName,
                        Long sId,
                        String sName,
                        String sAcronym,
                        Long coId,
                        String coName,
                        String coAcronym,
                        String coDdi,
                        String coCodeCountry,
                        String coCodeRevenue) {
                this.id = id;
                this.createdAt = createdAt;
                this.updatedAt = updatedAt;
                this.name = name;
                this.age = age;
                this.dateOfBirth = dateOfBirth;
                this.cpf = cpf;
                this.rg = rg;
                this.email = email;
                this.phone = phone;
                this.cnpj = cnpj;
                this.inscricState = inscricState;
                this.branch = new BranchResponseDTO(bId, bName);
                this.user = new UserResponseDTO(uId, uLogin);
                this.gender = gender;
                this.typePerson = typePerson;
                this.groupPerson = new GroupPersonResponseDTO(gId, gName);
                this.address = new AddressResponseDTO(
                                AddrId, AddrStreet, AddrNumber,
                                AddrNeighborhood, AddrComplemet,
                                new ZipCodeResponseDTO(zipId, zipCode,
                                                new CityResponseDTO(cId, cName,
                                                                new StateResponseDTO(sId, sName, sAcronym),
                                                                new CountryResponseDTO(coId, coName, coAcronym, coDdi,
                                                                                coCodeCountry, coCodeRevenue))));
        };

        private final record UserResponseDTO(Long id, String login) {
        }

        private final record BranchResponseDTO(Long id, String name) {
        }

        private final record GroupPersonResponseDTO(Long id, String name) {
        }

        private final record AddressResponseDTO(
                        Long id,
                        String street,
                        String number,
                        String neighborhood,
                        String complement,
                        ZipCodeResponseDTO zipCode) {
        }

        private final record ZipCodeResponseDTO(
                        Long id,
                        String code,
                        CityResponseDTO city) {
        }

        private final record CityResponseDTO(
                        Long id,
                        String name,
                        StateResponseDTO state,
                        CountryResponseDTO country) {
        }

        private final record StateResponseDTO(Long id, String name, String acronym) {
        }

        private final record CountryResponseDTO(
                        Long id,
                        String name,
                        String acronym,
                        String ddi,
                        String codeCountry,
                        String CodeRevenue) {
        }
}
