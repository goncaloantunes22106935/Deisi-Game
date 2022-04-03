package pt.ulusofona.lp2.deisiGreatGame;

import java.util.List;

public class SecundaryEffects extends Abyss{
    SecundaryEffects(int id, int position) {
        super(id, position);
        name = "Efeitos secundários";
    }

    @Override
    String effect(Programmer programmer, List<Programmer> programmers) {
        /*
                Efeitos secundários O programador recua para a posição onde estava há 2 movimentos atrás.
        */

        if (programmer.verifyTool(1)){
            return "Toma uns comprimidos anti radiação :)";
        }
        programmer.goBackXTurns(2);//deu bigode
        return "Chernobyl\n";
    }

    @Override
    String getPng() {
        return "secondary-effects.png";
    }
}
