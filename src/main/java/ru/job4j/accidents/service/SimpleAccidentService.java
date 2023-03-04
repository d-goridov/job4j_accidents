package ru.job4j.accidents.service;

import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SimpleAccidentService implements AccidentService {

    private final AccidentRepository accidentRepository;
    private final AccidentTypeService accidentTypeService;

    public SimpleAccidentService(AccidentRepository accidentRepository, AccidentTypeService accidentTypeService) {
        this.accidentRepository = accidentRepository;
        this.accidentTypeService = accidentTypeService;
    }


    @Override
    public void addAccident(Accident accident) {
        Optional<AccidentType> accidentTypeOptional = accidentTypeService.findById(accident.getType().getId());
        if (accidentTypeOptional.isEmpty()) {
            throw new NoSuchElementException("Тип инцидента не найден");
        }
        accident.setType(accidentTypeOptional.get());
        accidentRepository.addAccident(accident);
    }

    @Override
    public List<Accident> getAllAccidents() {
        List<Accident> accidents = accidentRepository.getAllAccidents();

        for (Accident accident: accidents) {
            Optional<AccidentType> accidentTypeOptional = accidentTypeService.findById(accident.getType().getId());
            if (accidentTypeOptional.isEmpty()) {
                throw new NoSuchElementException("Тип инцидента не найден");
            }
            accident.setType(accidentTypeOptional.get());
        }
        return accidents;
    }

    @Override
    public Optional<Accident> findById(int id) {
        return accidentRepository.findById(id);
    }

    @Override
    public boolean update(Accident accident) {
        Optional<AccidentType> accidentTypeOptional = accidentTypeService.findById(accident.getType().getId());
        if (accidentTypeOptional.isEmpty()) {
            throw new NoSuchElementException("Тип инцидента не найден");
        }
        return accidentRepository.update(accident);
    }
}
