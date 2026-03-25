package br.com.centroinfo.api.dtos.addressDTO;

import lombok.Getter;

@Getter
public class AddressResponseDTO {
  private Long id;
  private String street;
  private String number;
  private String neighborhood;
  private String complement;
  private PersonResponseDTO person;
  private ZipcodeResponseDTO zipCode;
  private CityResponseDTO city;
  private CountryResponseDTO country;
  private StateResponseDTO state;

  public AddressResponseDTO(
      Long id,
      String street,
      String number,
      String neighborhood,
      String complement,
      Long pId,
      String pName,
      Long zId,
      String zCode,
      Long cId,
      String cName,
      String cIbge,
      Long coId,
      String coName,
      String CoAcronym,
      Long sId,
      String sName,
      String sAcronym) {
    this.id = id;
    this.street = street;
    this.number = number;
    this.neighborhood = neighborhood;
    this.complement = complement;
    this.person = new PersonResponseDTO(pId, pName);
    this.zipCode = new ZipcodeResponseDTO(zId, zCode);
    this.city = new CityResponseDTO(cId, cName, cIbge);
    this.country = new CountryResponseDTO(coId, coName, CoAcronym);
    this.state = new StateResponseDTO(sId, sName, sAcronym);
  };

  /** Person */
  @Getter
  public class PersonResponseDTO {
    private Long id;
    private String name;

    public PersonResponseDTO(Long id, String name) {
      this.id = id;
      this.name = name;
    }
  };

  /** City */
  @Getter
  public class CityResponseDTO {
    private Long id;
    private String name;
    private String codeIbge;

    public CityResponseDTO(Long id, String name, String codeIbge) {
      this.id = id;
      this.name = name;
      this.codeIbge = codeIbge;
    }
  };

  /** State */
  @Getter
  public class StateResponseDTO {
    private Long id;
    private String name;
    private String acronym;

    public StateResponseDTO(Long id, String name, String acronym) {
      this.id = id;
      this.name = name;
      this.acronym = acronym;
    }
  };

  /** Country */
  @Getter
  public class CountryResponseDTO {
    private Long id;
    private String name;
    private String acronym; // Ex: BR, US, PT

    public CountryResponseDTO(Long id, String name, String acronym) {
      this.id = id;
      this.name = name;
      this.acronym = acronym;
    }
  };

  /** Zipcode */
  @Getter
  public class ZipcodeResponseDTO {
    private Long id;
    private String code;

    ZipcodeResponseDTO(Long id, String code) {
      this.id = id;
      this.code = code;
    }
  }
}