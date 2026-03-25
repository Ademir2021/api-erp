package br.com.centroinfo.api.entities.address.city;

import br.com.centroinfo.api.entities.address.country.Country;
import br.com.centroinfo.api.entities.address.state.State;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Table(name = "cities")
@Entity
@Getter
@Setter
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "code_ibge")
    private String codeIbge;
    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
}
