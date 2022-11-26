package edu.baylor.cs.beargo.service.address;

import edu.baylor.cs.beargo.model.address.State;
import edu.baylor.cs.beargo.repository.address.StateRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Transactional
public class StateService {

    @Autowired
    StateRepository stateRepository;

    public State save(State state) {
        return stateRepository.save(state);
    }

    public List<State> findAllState() {
        return stateRepository.findAll();
    }

    public State findById(Long id) {
        return stateRepository.findById(id).orElse(null);
    }

    public State findByState(String state) {
        return stateRepository.findByState(state);
    }
}
