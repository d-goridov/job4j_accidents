package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccidentMem implements AccidentRepository {

    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

    public AccidentMem() {
        addAccident(new Accident(1, "Превышение скорости", "Превышение скорости свыше чем на 20км/ч",
                "ул. Зорге 22"));
        addAccident(new Accident(2, "ДТП", "ДТП с участием двух и более ТС",
                "ул. Шеболдаева 1"));
        addAccident(new Accident(3, "Разворот в неположенном месте",
                "Разворот с пересечением двойной сплошной разметки", "ул. Вятская 89"));
    }

    @Override
    public void addAccident(Accident accident) {
        accidents.putIfAbsent(accident.getId(), accident);
    }

    @Override
    public List<Accident> getAllAccidents() {
        return accidents.values().stream().toList();
    }

    @Override
    public Optional<Accident> findById(int id) {
        return Optional.ofNullable(accidents.get(id));
    }

    @Override
    public boolean update(Accident accident) {
        return accidents.computeIfPresent(accident.getId(), (key, value) -> value = accident) != null;
    }
}
