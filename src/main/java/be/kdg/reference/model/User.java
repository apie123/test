package be.kdg.reference.model;

import java.util.HashSet;
import java.util.Set;

public class User {
    private String username;
    private String password;
    private Set<Favorite> favorites;

    public static final User INVALID_USER = new User("INVALID USER", "SDFGSDFGSDFGZVVQ");

    private User() {
        this("INVALID USER", "SDFGSDFGSDFGZVVQ");
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.favorites = new HashSet<Favorite>();
    }

    public User(User other) {
        if (this == other) return;
        this.username = other.username;
        this.password = other.password;
        this.favorites = new HashSet<Favorite>(other.favorites.size());
        for (Favorite favorite : other.favorites) {
            this.favorites.add(new Favorite(favorite));
        }
    }

    public String getUsername() {
        return username;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    private String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    public boolean isPasswordEqualTo(String password) {
        return this.password.equals(password);
    }

    public void addFavorite(String favorite) {
        this.favorites.add(new Favorite(favorite));
    }

    public Set<Favorite> getFavorites() {
        return favorites;
    }

    private void setFavorites(Set<Favorite> favorites) {
        this.favorites = favorites;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return username;
    }
}
