package pl.jacek.veterinary.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import pl.jacek.veterinary.model.User;
import pl.jacek.veterinary.util.ConnectionProvider;


public class UserDAOImpl implements UserDAO {

    private static final String CREATE_USER =
            "INSERT INTO user(username, email, password, is_active) VALUES(:username, :email, :password, :active);";
    private static final String READ_USER =
            "SELECT user_id, username, email, password, is_active FROM user WHERE user_id = :id";
    private static final String READ_USER_BY_USERNAME =
            "SELECT user_id, username, email, password, is_active FROM user WHERE username = :username";
    private static final String READ_ALL_USERS =
            "SELECT user_id, username, email, is_active, password"
            + " FROM user WHERE user_id= :id";

//                "SELECT user.user_id, username, email, is_active, password, animal_id, name, description, url, date"
//                        + " FROM animal LEFT JOIN user ON animal.user_id=user.user_id;";


//

    private NamedParameterJdbcTemplate template;

    public UserDAOImpl() {
        template = new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());
    }

//    metoda pozwalająca na utworzenie nowego użytkownika w bazie danych
//    przyjmuje jako argument obiekt klasy User. Wewnątrz metody utowrzona jest defensywna kopia obiektu, co zabezpiecza przed
//    modyfikacjami pól obiektu wewnątrz tej metody
    @Override
    public User create(User user) {
        User resultUser = new User(user);
//      Interfejs KeyHolder oraz klasa GeneratedKeyHolder() pozwalają przechwycić automatyczny klucz wygenerowany przez bazę danych
//        przy dodawaniu użytkownika
        KeyHolder holder = new GeneratedKeyHolder();
//        SqlParameterSqource uzyskuje wartość parametrów z obiektu JavaBeans
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(user);
        int update = template.update(CREATE_USER, paramSource, holder);
        if(update > 0) {
            resultUser.setId((Long)holder.getKey());
            setPrivigiles(resultUser);
        }
        return resultUser;
    }

    @Override
    public User read(Long primaryKey) {
        return null;
    }

    //metoda przypisująca odpowiednią rolę z tabeli user_role
    private void setPrivigiles(User user) {
        final String userRoleQuery = "INSERT INTO user_role(username,role_name) VALUES(:username, 'user')";
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(user);
        template.update(userRoleQuery, paramSource);
    }
//    private void setPrivigiles(User user, String privilege)
//    { final String userRoleQuery = "INSERT INTO user_role(username,role_name) VALUES(:username, privilege)";
//        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(user); template.update(userRoleQuery, paramSource);
//    }

    @Override
    public User read(long primaryKey) {
//        przypisanie id użytkownikowa wartości primaryKey do "id"
        User resultUser = null;
        SqlParameterSource paramSource = new MapSqlParameterSource("id", primaryKey);
        resultUser = template.queryForObject(READ_USER, paramSource, new UserRowMapper());
        return resultUser;
    }

    @Override
    public boolean update(User updateObject) {
        return false;
    }

    @Override
    public boolean delete(Long key) {
        return false;
    }

    @Override
    public User getUserByUsername(String username) {
        User resultUser = null;
        SqlParameterSource paramSource = new MapSqlParameterSource("username", username);
        resultUser = template.queryForObject(READ_USER_BY_USERNAME, paramSource, new UserRowMapper());
        return resultUser;
    }

    @Override
    public List<User> getAll() {
        List<User> users = template.query(READ_ALL_USERS, new UserRowMapper());
        return users;
    }

    private class UserRowMapper implements RowMapper<User> {
//metoda mapRow, tworzy obiekt User i ustawiamy jego pola przez settery. Pobieramy wartości przypisane za pomocą
//       obiektu resultSet
        @Override
        public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            User user = new User();
            user.setId(resultSet.getLong("user_id"));
            user.setUsername(resultSet.getString("username"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            return user;
        }
    }
}