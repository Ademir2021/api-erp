package br.com.centroinfo.api.services.state;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.centroinfo.api.dtos.stateDTO.StateDTO;
import br.com.centroinfo.api.entities.address.state.State;
import br.com.centroinfo.api.repository.state.StateRepository;

@Service
public class StateServices {

    @Autowired
    StateRepository stateRepository;

    public State create(StateDTO stateDTO) {
        State state = new State();
        state.setName(stateDTO.getName());
        state.setAcronym(stateDTO.getAcronym());
        stateRepository.save(state);
        return state;
    }

    public List<State> list() {
        return stateRepository.findAll();
    }

    public State update(StateDTO stateDTO) {
        State state = new State();
        state.setId(stateDTO.getId());
        state.setName(stateDTO.getName());
        state.setAcronym(stateDTO.getAcronym());
        stateRepository.save(state);
        return state;
    }

}
