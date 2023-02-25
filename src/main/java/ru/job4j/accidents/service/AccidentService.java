package ru.job4j.accidents.service;

import ru.job4j.accidents.model.Accident;

import java.util.List;

public interface AccidentService {
    void addAccident(Accident accident);
    List<Accident> getAllAccidents();
}