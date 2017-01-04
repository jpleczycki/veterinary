package pl.jacek.veterinary.service;


import pl.jacek.veterinary.dao.AnimalDAO;
import pl.jacek.veterinary.dao.DAOFactory;
import pl.jacek.veterinary.model.Animal;
import pl.jacek.veterinary.model.User;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

//klasa serwisowa, wykonuje dodatkowe informacje przed dodaniem obiektów do bazy oraz
//pozwala połączyć operacje wymagające kilku obiektów DAO
//pozwala na nieumieszczanie logiki biznesowej w klasie kontrolera

public class AnimalService {

    public void addAnimal(String name, String desc, String url, User user) {
        Animal animal = createAnimalObject(name, desc, url, user);
        DAOFactory factory = DAOFactory.getDAOFactory();
        AnimalDAO animalDao = factory.getAnimalDAO();
        animalDao.create(animal);
    }

    private Animal createAnimalObject(String name, String desc, String url, User user) {
        Animal animal = new Animal();
        animal.setName(name);
        animal.setDescription(desc);
        animal.setUrl(url);
        User userCopy = new User(user);
        animal.setUser(userCopy);
        animal.setTimestamp(new Timestamp(new Date().getTime()));
        return animal;
    }
    public List<Animal> getAnimalByID() {
        return getAnimalByID();
    }

    public Animal getAnimalByID(long animalId) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        AnimalDAO animalDao = factory.getAnimalDAO();
        Animal animal = animalDao.read(animalId);
//        List<Animal> getAnimalByID = animalDao.read(long);
        return animal;
    }


    public boolean updateAnimal(Animal animal) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        AnimalDAO animalDao = factory.getAnimalDAO();
        boolean result = animalDao.update(animal);
        return result;
    }
    public List<Animal> getAllAnimals() {
        return getAllAnimals(null);
    }

    public List<Animal> getAllAnimals(Comparator<Animal> comparator) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        AnimalDAO animalDao = factory.getAnimalDAO();
        List<Animal> animals = animalDao.getAll();
        if (comparator != null && animals != null) {
            animals.sort(comparator);
        }
        return animals;
    }
}


