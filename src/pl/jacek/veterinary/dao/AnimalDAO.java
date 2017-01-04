package pl.jacek.veterinary.dao;

import java.util.List;
import pl.jacek.veterinary.model.Animal;

public interface AnimalDAO extends GenericDAO<Animal, Long>{


    //metoda zwracająca pojedynczy obiekt z tabeli animal

    @Override
    List<Animal> getAll();
    List<Animal> getAnimalObject();
    Animal read(long primaryKey);
}
