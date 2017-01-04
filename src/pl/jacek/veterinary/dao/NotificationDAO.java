package pl.jacek.veterinary.dao;


import pl.jacek.veterinary.model.Notification;

import java.util.List;

public interface NotificationDAO extends GenericDAO<Notification, Long>{

    Notification read(long primaryKey);

    List<Notification> getAll();

}