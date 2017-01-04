package pl.jacek.veterinary.dao;


import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import pl.jacek.veterinary.model.Animal;
import pl.jacek.veterinary.model.Notification;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationDAOImpl implements NotificationDAO {

    private static final String CREATE_NOTIFICATION =
            "INSERT INTO notification(name, description, animal_id, date) "
                    + "VALUES(:name, :description, :animal_id, :date);";
    private static final String READ_ALL_NOTIFICATIONS =
            "SELECT animal.animal_id, name, description, url, date, notification_id, name, description, animal_id, date "
                    + " FROM animal LEFT JOIN animal ON notification.animal_id=animal.animal_id;";
    private static final String READ_NOTIFICATION =
        "SELECT animal.animal_id, name, description, url, date, notification_id, name, description, animal_id, date "
                    + " FROM animal LEFT JOIN user ON animal.animal_id=animal.animal_id WHERE animal_id=:animal_id;";
    private static final String UPDATE_NOTIFICATION =
            "UPDATE notification SET name=:name, description=:description, url=:url, date=:date, animal_id=:animal_id, "
                    + "WHERE notification_id=:notification_id;";

    private NamedParameterJdbcTemplate template;


    @Override
    public Notification create(Notification notification) {
        Notification resultNotification = new Notification(notification);
        KeyHolder holder = new GeneratedKeyHolder();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("name", notification.getName());
        paramMap.put("description", notification.getDescription());
        paramMap.put("user_id", notification.getAnimal().getId());
        paramMap.put("date", notification.getTimestamp());

        SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
        int update = template.update(CREATE_NOTIFICATION, paramSource, holder);
        if (update > 0) {
            resultNotification.setId((Long) holder.getKey());
        }
        return resultNotification;
    }

    @Override
    public Notification read(Long primaryKey) {
        return null;
    }

    @Override
    public Notification read(long primaryKey) {
        SqlParameterSource paramSource = new MapSqlParameterSource("notification_id", primaryKey);
        Notification notification = template.queryForObject(READ_NOTIFICATION, paramSource, new NotificationRowMapper());
        return notification;
    }

    @Override
    public boolean update(Notification notification) {
        boolean result = false;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("notification_id", notification.getId());
        paramMap.put("name", notification.getName());
        paramMap.put("description", notification.getDescription());
        paramMap.put("user_id", notification.getAnimal().getId());
        paramMap.put("date", notification.getTimestamp());
        SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
        int update = template.update(UPDATE_NOTIFICATION, paramSource);
        if(update > 0) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(Long key) {
        return false;
    }

    @Override
    public List<Notification> getAll() {
        return null;
    }
    private class NotificationRowMapper implements RowMapper<Notification> {
        @Override
        public Notification mapRow(ResultSet resultSet, int row) throws SQLException {
            Notification notification = new Notification();
            notification.setId(resultSet.getLong("animal_id"));
            notification.setName(resultSet.getString("name"));
            notification.setDescription(resultSet.getString("description"));
            notification.setTimestamp(resultSet.getTimestamp("date"));
            Animal animal = new Animal();
            animal.setId(resultSet.getLong("animal_id"));
            animal.setName(resultSet.getString("name"));
            notification.setAnimal(animal);
            return notification;
        }
    }
}
