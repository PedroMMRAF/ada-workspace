package Bridges;

public class City {

    private String name;
    private String os;
    private int value;

    public City(String name, String os, int value) {
        this.name = name;
        this.os = os;
        this.value = value;
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

    public int bridgeValue(City city) {
        return this.os.equals(city.getOs()) ? city.getValue() + this.value : 0;
    }
}
