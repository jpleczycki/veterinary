package pl.jacek.veterinary.service;


import pl.jacek.veterinary.dao.DAOFactory;
import pl.jacek.veterinary.dao.UserDAO;
import pl.jacek.veterinary.model.User;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Comparator;
import java.util.List;

//klasa serwisowa, wykonuje dodatkowe informacje przed dodaniem obiektów do bazy oraz
//pozwala połączyć operacje wymagające kilku obiektów DAO
//pozwala na nieumieszczanie logiki biznesowej w klasie kontrolera
public class UserService {
//    metoda tworzy obiekt klasy User i zapisuje go w bazie danych. Argumenty pochodzą  z żądania przesłanego z odpowiedniego kontolera(servletu)
    public void addUser(String username, String email, String password) {
        User user = new User();
        user.setUsername(username);
        String md5Pass = encryptPassword(password);
        user.setPassword(md5Pass);
        user.setEmail(email);
        user.setActive(true);
        DAOFactory factory = DAOFactory.getDAOFactory();
        UserDAO userDao = factory.getUserDAO();
        userDao.create(user);
    }

    private String encryptPassword(String password) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        digest.update(password.getBytes());
        String md5Password = new BigInteger(1, digest.digest()).toString(16);
        return md5Password;
    }

    public User getUserById(long userId) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        UserDAO userDao = factory.getUserDAO();
        User user = userDao.read(userId);
        return user;
    }

    public User getUserByUsername(String username) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        UserDAO userDao = factory.getUserDAO();
        User user = userDao.getUserByUsername(username);
        return user;
    }
    public List<User> getAllUsers() {
        return getAllUsers(null);
    }

    public List<User> getAllUsers(Comparator<User> comparator) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        UserDAO userDao = factory.getUserDAO();
        List<User> users = userDao.getAll();
        if (comparator != null && users != null) {
            users.sort(comparator);
        }
        return users;
    }
}