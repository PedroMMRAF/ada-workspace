public class City {
    private final int preference;
    private final int value;


    public City(String name, String os, int value) {
        this.preference = os.hashCode();
        this.value = value;
    }

    public int getPreference() {
        return preference;
    }

    public int getValue() {
        return value;
    }

    public boolean matches(City other) {
        return this.preference == other.getPreference();
    }
}
