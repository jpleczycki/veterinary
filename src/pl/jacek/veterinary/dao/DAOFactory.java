package pl.jacek.veterinary.dao;


import pl.jacek.veterinary.exception.NoSuchDbTypeException;

//klasa abstrakcyjna, pozwalająca w łatwy sposób zmienić silnik bazy danych poprzez zmianę we wpisach metody getDaoFactory
public abstract class DAOFactory {

    public static final int MYSQL_DAO_FACTORY = 1;

    public abstract AnimalDAO getAnimalDAO();

    public abstract UserDAO getUserDAO();

    public abstract NotificationDAO getNotificationDAO();


    public static DAOFactory getDAOFactory() {
        DAOFactory factory = null;
        try {
            factory = getDAOFactory(MYSQL_DAO_FACTORY);
        } catch (NoSuchDbTypeException e) {
            e.printStackTrace();
        }
        return factory;
    }

    private static DAOFactory getDAOFactory(int type) throws NoSuchDbTypeException {
        switch (type) {
            case MYSQL_DAO_FACTORY:
                return new MysqlDAOFactory();
            default:
                throw new NoSuchDbTypeException();
        }
    }
}


