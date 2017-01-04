package pl.jacek.veterinary.service;


import pl.jacek.veterinary.dao.DAOFactory;
import pl.jacek.veterinary.dao.NotificationDAO;
import pl.jacek.veterinary.model.Animal;
import pl.jacek.veterinary.model.Notification;

import java.util.Date;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;

public class NotificationService {
    public void addNotification(String name, String description, Animal animal) {
        Notification notification = createNotificationObject(name,description,animal);
        DAOFactory factory = DAOFactory.getDAOFactory();
        NotificationDAO notificationDao = factory.getNotificationDAO();
        notificationDao.create(notification);
    }


    private Notification createNotificationObject(String name, String descrition, Animal animal) {
        Notification notification = new Notification();
        notification.setName(name);
        notification.setDescription(descrition);
        Animal animalCopy = new Animal(animal);
        notification.setAnimal(animalCopy);
        notification.setTimestamp(new Timestamp(new Date().getTime()));
        return notification;
    }
    public Notification getNotificationById(long notificationId) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        NotificationDAO notificationDao = factory.getNotificationDAO();
        Notification notification = notificationDao.read(notificationId);
        return notification;
    }

    public boolean updateNotification(Notification notification) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        NotificationDAO notificationDao = factory.getNotificationDAO();
        boolean result = notificationDao.update(notification);
        return result;
    }
    public List<Notification> getAllNotifications() {
        return getAllNotifications();
    }

    public List<Notification> getAllNotifaciont(Comparator<Notification> comparator) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        NotificationDAO notificationDao = factory.getNotificationDAO();
        List<Notification> notifications = notificationDao.getAll();
        if(comparator != null && notifications != null) {
            notifications.sort(comparator);
        }
        return notifications;
    }

}

