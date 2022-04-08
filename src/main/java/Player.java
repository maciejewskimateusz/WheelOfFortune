public class Player {
    private String name;


    public Player(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Player's name can not be null or empty");
        } else {
            this.name = name;
        }
    }

    @Override
    public String toString() {
        return "[<" + name + ">]";
    }
}
