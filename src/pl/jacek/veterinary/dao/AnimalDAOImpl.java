package pl.jacek.veterinary.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import pl.jacek.veterinary.model.Animal;
import pl.jacek.veterinary.model.User;
import pl.jacek.veterinary.util.ConnectionProvider;

public class AnimalDAOImpl implements AnimalDAO {

    private static final String CREATE_ANIMAL =
            "INSERT INTO animal(name, description, url, user_id, date) "
                    + "VALUES(:name, :description, :url, :user_id, :date);";
//    pobieranie danych, także informacji o użytkowniku z tabeli
    private static final String READ_ALL_ANIMALS =
            "SELECT user.user_id, username, email, is_active, password, animal_id, name, description, url, date"
                    + " FROM animal LEFT JOIN user ON animal.user_id=user.user_id;";
    private static final String READ_ANIMAL =
            "SELECT user.user_id, username, email, is_active, password, animal_id, name, description, url, date"
                    + " FROM animal LEFT JOIN user ON animal.user_id=user.user_id WHERE animal_id=:animal_id;";

    private static final String UPDATE_ANIMAL =
            "UPDATE animal SET name=:name, description=:description, url=:url, user_id=:user_id, date=:date "
                    + "WHERE animal_id=:animal_id;";

    private NamedParameterJdbcTemplate template;

    public AnimalDAOImpl() {
        template = new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());
    }

    @Override
    public Animal create(Animal animal) {
        Animal resultAnimal = new Animal(animal);
        KeyHolder holder = new GeneratedKeyHolder();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("name", animal.getName());
        paramMap.put("description", animal.getDescription());
        paramMap.put("url", animal.getUrl());
        paramMap.put("user_id", animal.getUser().getId());
        paramMap.put("date", animal.getTimestamp());

        SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
        int update = template.update(CREATE_ANIMAL, paramSource, holder);
        if (update > 0) {
            resultAnimal.setId((Long) holder.getKey());
        }
        return resultAnimal;
    }

    @Override
    public Animal read(Long primaryKey) {
        Animal resultAnimal = null;
        SqlParameterSource paramSource = new MapSqlParameterSource("animal_id", primaryKey);
        resultAnimal = template.queryForObject(READ_ANIMAL, paramSource, new AnimalRowMapper());
        return resultAnimal;
    }

    @Override
    public boolean update(Animal animal) {
        boolean result = false;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("animal_id", animal.getId());
        paramMap.put("name", animal.getName());
        paramMap.put("description", animal.getDescription());
        paramMap.put("url", animal.getUrl());
        paramMap.put("user_id", animal.getUser().getId());
        paramMap.put("date", animal.getTimestamp());
        SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
        int update = template.update(UPDATE_ANIMAL, paramSource);
        if(update > 0) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(Long key) {
        return false;
    }
//metoda zwracająca pojedynczy obiekt z tabeli animal
    @Override
    public List<Animal> getAnimalObject() {
        List<Animal> animals = template.query(READ_ANIMAL, new AnimalRowMapper());
        return animals;
    }

//metoda zwracająca wszystkie obiekty z tabeli animal
    @Override
    public List<Animal> getAll() {
        List<Animal> animals = template.query(READ_ALL_ANIMALS, new AnimalRowMapper());
            return animals;
        }

    @Override
    public Animal read(long primaryKey) {
        SqlParameterSource paramSource = new MapSqlParameterSource("animal_id", primaryKey);
        Animal animal = template.queryForObject(READ_ANIMAL, paramSource, new AnimalRowMapper());
        return animal;
    }

    // rzutowanie danych z bazy danych na obiekt typu Animal z uwzględnieniem utworzenia obiektu typu User
        private class AnimalRowMapper implements RowMapper<Animal> {
            @Override
            public Animal mapRow(ResultSet resultSet, int row) throws SQLException {
                Animal animal = new Animal();
                animal.setId(resultSet.getLong("animal_id"));
                animal.setName(resultSet.getString("name"));
                animal.setDescription(resultSet.getString("description"));
                animal.setUrl(resultSet.getString("url"));
                animal.setTimestamp(resultSet.getTimestamp("date"));
                User user = new User();
                user.setId(resultSet.getLong("user_id"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                animal.setUser(user);
                return animal;
            }
        }
    }
