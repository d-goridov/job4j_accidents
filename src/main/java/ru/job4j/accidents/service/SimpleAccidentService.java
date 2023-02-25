package ru.job4j.accidents.service;

import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentRepository;

import java.util.List;

@Service
public class SimpleAccidentService implements AccidentService {

    private final AccidentRepository accidentRepository;

    public SimpleAccidentService(AccidentRepository accidentRepository) {
        this.accidentRepository = accidentRepository;
    }


    @Override
    public void addAccident(Accident accident) {
        accidentRepository.addAccident(accident);
    }

    @Override
    public List<Accident> getAllAccidents() {
        return accidentRepository.getAllAccidents();
    }
}
