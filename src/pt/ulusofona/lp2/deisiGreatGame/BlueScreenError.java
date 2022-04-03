package pt.ulusofona.lp2.deisiGreatGame;

import java.util.List;

public class BlueScreenError extends Abyss {
    BlueScreenError(int id, int position) {
        super(id, position);
        name = "Blue Screen of Death";
    }

    @Override
    String effect(Programmer programmer, List<Programmer> programmers) {
         /*
                Blue Screen of Death O programador perde imediatamente o jogo.
          */
        name = "Blue Screen of Death";
        if(programmer.getStatusBool()){
            programmer.lose();
        }

        return "Perdeu PlayBoy";
    }

    @Override
    String getPng() {
        return "bsod.png";
    }


}
