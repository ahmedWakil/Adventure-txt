package adventuregame.world.Generators;

public enum Level {
    CLASSIC("/data/prototypes/basic/basic_prototypes.json");

    public final String address;
    Level(String s) {
        this.address = s;
    }
}
