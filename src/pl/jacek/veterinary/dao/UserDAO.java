package pl.jacek.veterinary.dao;

import java.util.List;

import pl.jacek.veterinary.model.User;

public interface UserDAO extends GenericDAO<User, Long> {

    User read(long primaryKey);

    List<User> getAll();
    User getUserByUsername(String username);


}