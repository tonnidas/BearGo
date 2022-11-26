package edu.baylor.cs.beargo.controller.address;

import edu.baylor.cs.beargo.model.address.State;
import edu.baylor.cs.beargo.service.address.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/state")
public class StateController {

    @Autowired
    StateService stateService;

    @GetMapping("/findAllState")
    public List<State> findAllState() {
        return stateService.findAllState();
    }
}
