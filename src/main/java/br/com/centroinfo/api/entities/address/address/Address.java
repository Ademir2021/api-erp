package br.com.centroinfo.api.entities.address.address;
import br.com.centroinfo.api.entities.address.zipcode.ZipCode;
import br.com.centroinfo.api.entities.persons.Person;
// import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="address")
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String number;
    private String neighborhood;
    private String complement;
    @ManyToOne()
    @JoinColumn(name = "zipcode_id")
    private ZipCode zipCode;
    @ManyToOne(optional = true)
    @JoinColumn(name = "person_id")
    private Person person;
}