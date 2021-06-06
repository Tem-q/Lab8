package dragon;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Deque;
import java.util.Objects;

/**
 * A class whose elements make up a collection
 */
public class Dragon implements Comparable<Dragon>, Serializable {
    private int id;
    private String name;
    private Coordinates coordinates;
    private java.time.LocalDate creationDate;
    private Long age;
    private String description;
    private int weight;
    private DragonType type;
    private Person killer;

    private long x_coordinate;
    private double y_coordinate;
    private String killer_name;
    private float killer_height;
    private long killer_weight;
    private String location_name;
    private float x;
    private int y;
    private float z;
    private String user;

    public Dragon(String name, Coordinates coordinates, Long age, String description, int weight, DragonType type, Person killer) {
        creationDate = LocalDate.now();
        this.name = name;
        this.coordinates = coordinates;
        this.age = age;
        this.description = description;
        this.weight = weight;
        this.type = type;
        this.killer = killer;

        this.x_coordinate = coordinates.getX();
        this.y_coordinate = coordinates.getY();
        this.killer_name = killer.getName();
        this.killer_weight = killer.getWeight();
        this.killer_height = killer.getHeight();
        this.location_name = killer.getLocation().getName();
        this.x = killer.getLocation().getX();
        this.y = killer.getLocation().getY();
        this.z = killer.getLocation().getZ();
    }

    public Dragon(int id, String name, Coordinates coordinates, Long age, String description, int weight, DragonType type, Person killer) {
        creationDate = LocalDate.now();
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.age = age;
        this.description = description;
        this.weight = weight;
        this.type = type;
        this.killer = killer;

        this.x_coordinate = coordinates.getX();
        this.y_coordinate = coordinates.getY();
        this.killer_name = killer.getName();
        this.killer_weight = killer.getWeight();
        this.killer_height = killer.getHeight();
        this.location_name = killer.getLocation().getName();
        this.x = killer.getLocation().getX();
        this.y = killer.getLocation().getY();
        this.z = killer.getLocation().getZ();
    }

    public Dragon(int id, String name, Coordinates coordinates, LocalDate creationDate, Long age, String description, int weight, DragonType type, Person killer) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.age = age;
        this.description = description;
        this.weight = weight;
        this.type = type;
        this.killer = killer;

        this.x_coordinate = coordinates.getX();
        this.y_coordinate = coordinates.getY();
        this.killer_name = killer.getName();
        this.killer_weight = killer.getWeight();
        this.killer_height = killer.getHeight();
        this.location_name = killer.getLocation().getName();
        this.x = killer.getLocation().getX();
        this.y = killer.getLocation().getY();
        this.z = killer.getLocation().getZ();
    }

    public int findMaxId(Deque<Dragon> dragons) {
        int maxId = 0;
        for (Dragon d: dragons) {
            if (d.getId()>maxId) {
                maxId = d.getId();
            }
        }
        maxId++;
        return maxId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Long getAge() {
        return age;
    }

    public String getDescription() {
        return description;
    }

    public int getWeight() {
        return weight;
    }

    public DragonType getType() {
        return type;
    }

    public Person getKiller() {
        return killer;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public int compareTo(Dragon dragon) {
        if (age > dragon.getAge())
            return 1;
        if (age < dragon.getAge())
            return -1;
        return name.compareTo(dragon.getName());
    }

    @Override
    public String toString() {
        return "Dragon{" +
                "id = " + id +
                ", name = '" + name + '\'' +
                //", coordinates = " + coordinates +
                ", creationDate = " + creationDate +
                ", age = " + age +
                ", description = '" + description + '\'' +
                ", weight = " + weight +
                ", type = " + type +
                //", killer = " + killer +
                '}';
    }

    public long getX_coordinate() {
        return x_coordinate;
    }

    public double getY_coordinate() {
        return y_coordinate;
    }

    public String getKiller_name() {
        return killer_name;
    }

    public float getKiller_height() {
        return killer_height;
    }

    public long getKiller_weight() {
        return killer_weight;
    }

    public String getLocation_name() {
        return location_name;
    }

    public float getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dragon dragon = (Dragon) o;
        return id == dragon.id &&
                weight == dragon.weight &&
                Objects.equals(name, dragon.name) &&
                Objects.equals(coordinates, dragon.coordinates) &&
                Objects.equals(creationDate, dragon.creationDate) &&
                Objects.equals(age, dragon.age) &&
                Objects.equals(description, dragon.description) &&
                type == dragon.type &&
                Objects.equals(killer, dragon.killer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, age, description, weight, type, killer);
    }
}