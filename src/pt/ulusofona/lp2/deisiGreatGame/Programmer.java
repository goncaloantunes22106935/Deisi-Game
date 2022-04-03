package pt.ulusofona.lp2.deisiGreatGame;

import java.awt.event.PaintEvent;
import java.io.Serializable;
import java.util.*;

public class Programmer implements Serializable {
    private final String name;
    private final int id;
    private final ProgrammerColor avatarColor;
    private final ArrayList<String> favoriteLanguages;
    private final ArrayList<Integer> percursoDeCasas = new ArrayList<>();
    private final HashMap<Integer, Tool> tools = new HashMap<>();
    private int position;
    private int dice;
    private boolean status; //Derrotado = false ; em jogo = true
    private boolean stuck; //preso = true ; em jogo false;

    Programmer(String name, int id, ArrayList<String> favoriteLanguages, ProgrammerColor avatarColor) {
        this.name = name;
        this.id = id;
        this.favoriteLanguages = favoriteLanguages;
        this.avatarColor = avatarColor;
        status = true;
        position = 1;
    }

    public String getProgrammerFavLan() {
        return ((favoriteLanguages.toString().replace(",", ";")).replace("[", "").replace("]", ""));
    }

    public ArrayList<String> getProgrammerFavLanList() {
        return favoriteLanguages;
    }

    public String getStatus() {
        if (status) {
            return "Em Jogo";
        }
        return "Derrotado";
    }

    boolean verifyTool(int id) {
        for (Tool tool : tools.values()) {
            if (tool.getId() == id) {
                tools.remove(id);
                return true;
            }
        }

        return false;
    }

    public int getDice() {
        return dice;
    }

    public void setDice(int dice) {
        this.dice = dice;
    }

    public boolean getStatusBool() {
        return status;
    }

    public int getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public ProgrammerColor getColor() {
        return avatarColor;
    }

    public void setInitialPosition() {
        position = 1;
    }

    public void move(int moves) {
        if (position + moves < 1) {
            this.position = 1;
        } else {
            if (avatarColor != ProgrammerColor.GREEN || avatarColor != ProgrammerColor.PURPLE){
                position += moves;
                percursoDeCasas.add(position);
            } else{
                if (position + moves % 3 != 0){
                    position += moves;
                    percursoDeCasas.add(position);
                }
            }
        }
    }

    public boolean isNameValid() {
        return name != null && !name.isEmpty();
    }

    public void lose() {
        status = false;
    }

    public void stuck() {
        stuck = true;
    }

    public void save() {
        stuck = false;
    }

    public boolean isStuck() {
        return stuck;
    }

    /*
    temos um historico de jogadas de todos os jogadores logo podemos voltar atrás nas jogadas
     */
    public void goBackXTurns(int numTurns) {
        if (percursoDeCasas.size() - 1 < numTurns) {
            position = 1;
            return;
        }
        position = percursoDeCasas.get((percursoDeCasas.size() - 1) - numTurns);
    }

    public void addTool(Tool tool) {
        tools.put(tool.getId(), tool);
    }

    public String programmerTools() {
        StringBuilder toolsString = new StringBuilder();
        if (tools.size() == 0) {
            return name + " : " + getTools();
        }
        for (Tool tool : tools.values()) {
            toolsString.append(tool.getName()).append(";");
        }
        toolsString.deleteCharAt(toolsString.toString().length() - 1);
        return name + " : " + getTools();
    }

    public String getTools() {
        StringBuilder toolsString = new StringBuilder();
        if (tools.size() == 0) {
            return "No tools";
        }
        for (Tool tool : tools.values()) {
            toolsString.append(tool.getName()).append(";");
        }
        toolsString.deleteCharAt(toolsString.toString().length() - 1);
        return toolsString.toString();
    }

    @Override
    public String toString() {


        return getId() + " | " + getName() + " | " + getPosition() + " | " + getTools() + " | " + getProgrammerFavLan()
                + " | " + getStatus();
    }

    public static class PositionComparator implements Comparator<Programmer> {

        @Override
        public int compare(Programmer prog1, Programmer prog2) {
            return prog2.getPosition() - prog1.getPosition();
        }
    }

    public static class IDComparator implements Comparator<Programmer> {

        @Override
        public int compare(Programmer prog1, Programmer prog2) {
            return prog2.getId() - prog1.getId();
        }
    }

    public static class NameComparator implements Comparator<Programmer> {

        @Override
        public int compare(Programmer prog1, Programmer prog2) {
            return prog1.getName().compareTo(prog2.getName());
        }
    }

    public static class PostionThenName implements Comparator<Programmer> {
        public int compare(Programmer prog1, Programmer prog2) {

            String x1 = prog1.getName();
            String x2 = prog2.getName();
            int sComp = x1.compareTo(x2);

            if (sComp != 0) {
                return sComp;
            }

            Integer y1 = prog1.getPosition();
            Integer y2 = prog2.getId();
            return y1.compareTo(y2);
        }
    }
}
