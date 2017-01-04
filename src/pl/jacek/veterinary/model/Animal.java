package pl.jacek.veterinary.model;

//klasa typu java beans
import java.sql.Timestamp;

public class Animal {

    private long id;
    private String name;
    private String description;
    private String url;
    private Timestamp timestamp;
    private User user;

    public Animal(){}
    //    konstruktory w wersji przeciążonej
    public Animal(Animal animal){
        this.id = animal.id;
        this.name = animal.name;
        this.description = animal.description;
        this.url = animal.url;
        this.timestamp = new Timestamp(animal.timestamp.getTime());
        this.user = new User(animal.user);
    }
//    settery i gettery
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    metoda toString() drukująca informacje o obiektach
    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", timestamp=" + timestamp +
                ", user=" + user +
                '}';
    }

//    metoda equals sprawdzająca równość obiektów
//    jeśli obiekt przekazany jako argument jest pustą referencją (null), to wynikiem metody powinno być false
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Animal animal = (Animal) o;

        if (id != animal.id) return false;
        if (name != null ? !name.equals(animal.name) : animal.name != null) return false;
        if (!description.equals(animal.description)) return false;
        if (url != null ? !url.equals(animal.url) : animal.url != null) return false;
        if (timestamp != null ? !timestamp.equals(animal.timestamp) : animal.timestamp != null) return false;
        return user != null ? user.equals(animal.user) : animal.user == null;

    }
//metoda hashCode() w wyniku daje unikalny identyfikator obiektu w postaci int
    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name == null ? name.hashCode() : 0);
        result = 31 * result + (description == description ? name.hashCode() : 0);
        result = 31 * result + (url == null ? url.hashCode() : 0);
        result = 31 * result + (timestamp == null ? timestamp.hashCode() : 0);
        result = 31 * result + (user == null ? user.hashCode() : 0);
        return result;
    }
}
