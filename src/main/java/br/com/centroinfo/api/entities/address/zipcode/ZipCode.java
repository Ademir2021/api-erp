package br.com.centroinfo.api.entities.address.zipcode;


import br.com.centroinfo.api.entities.address.city.City;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Table(name="zip_codes")
@Entity
@Getter
@Setter
public class ZipCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
}
