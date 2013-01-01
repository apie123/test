package be.kdg.reference.model;

public class Favorite {
    private int id;
    private String name;

    public Favorite() {
        this("");
    }

    public Favorite(String name) {
        this.id = 0;
        this.name = name;
    }

    public Favorite(Favorite other) {
        if (this == other) return;
        this.id = other.id;
        this.name = other.name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Favorite favorite = (Favorite) o;

        if (name != null ? !name.equals(favorite.name) : favorite.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return name;
    }
}
