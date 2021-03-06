import java.util.Objects;

public class Player implements Comparable<Player>{
    private String name;
    private int points;

    public Player(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Player's name can not be null or empty");
        } else {
            this.name = name;
        }
        this.points = 0;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int points){
        this.points += points;
    }

    @Override
    public String toString() {
        return "[<" + name + ">]";
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(Player p) {
        if (this.points > p.points)
            return -1;
        else if (this.points < p.points)
            return 1;
        return 0;
    }
}
