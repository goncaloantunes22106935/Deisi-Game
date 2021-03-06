package pt.ulusofona.lp2.deisiGreatGame;

public enum ProgrammerColor {

    PURPLE("Purple"), BLUE("Blue"), GREEN("Green"), BROWN("Brown"), NONE("");
    String color;

    ProgrammerColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color;
    }
}
