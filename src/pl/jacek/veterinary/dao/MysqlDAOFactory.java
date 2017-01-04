package pl.jacek.veterinary.dao;




public class MysqlDAOFactory extends DAOFactory {

    @Override
    public AnimalDAO getAnimalDAO() {
        return new AnimalDAOImpl();
    }

    @Override
    public UserDAO getUserDAO() {
        return new UserDAOImpl();
    }

    @Override
    public NotificationDAO getNotificationDAO() {
        return new NotificationDAOImpl();
    }


    }

