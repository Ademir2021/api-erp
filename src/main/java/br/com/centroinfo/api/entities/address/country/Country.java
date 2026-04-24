package br.com.centroinfo.api.entities.address.country;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.centroinfo.api.entities.address.city.City;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Table(name = "countries")
@Entity
@Getter
@Setter
public class Country { // Pais

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String acronym; // Ex: BR, US, PT
    private String ddi; // Ex: 55 para Brasil, 1 para Estados Unidos
    @Column(name = "code_country")
    private String codeCountry;
    @Column(name = "code_revenue")
    private String codeRevenue; // codigo da receita federal do país
    @JsonIgnore
    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private List<City> cities;
}
