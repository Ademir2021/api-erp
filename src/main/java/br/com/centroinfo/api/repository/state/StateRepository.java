package br.com.centroinfo.api.repository.state;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.centroinfo.api.entities.address.state.State;

public interface StateRepository extends JpaRepository<State, Long> {

}
