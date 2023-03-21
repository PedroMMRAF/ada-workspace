package Bridges;

public class City {
    private String name;
    private String os;
    private int value;

    private int hash;

    public City(String name, String os, int value) {
        this.name = name;
        this.os = os;
        this.value = value;

        this.hash = this.os.hashCode();
    }

    public String getName() {
        return name;
    }

    public String getOs() {
        return os;
    }

    public int getValue() {
        return value;
    }

    public int getHash() {
        return hash;
    }

    public boolean matches(City other) {
        return this.hash == other.getHash();
    }

    public int bridgeValue(City city) {
        return this.os.equals(city.getOs()) ? city.getValue() + this.value : 0;
    }
}
