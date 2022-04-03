package pt.ulusofona.lp2.deisiGreatGame;

import java.io.Serializable;

public class Position implements Serializable {
    private int position;
    private String abyss = "";

    public Position(int position) {
        this.position = position;
    }

    public Position(int position, String abyss) {
        this.position = position;
        this.abyss = abyss;
    }

    public int getPosition() {
        return position;
    }

    public String getAbyss() {
        return abyss;
    }
}
