package pt.ulusofona.lp2.deisiGreatGame;

import java.util.List;

public class CrashError extends Abyss {
    CrashError(int id, int position) {
        super(id, position);
        name = "Crash (aka Rebentanço)";
    }

    @Override
    String effect(Programmer programmer, List<Programmer> programmers) {
        /*
                Crash (aka Rebentanço) - O programador volta à primeira casa do jogo.
         */

        programmer.setInitialPosition();

        return "Ahhhh *bolas* Here We Go Again\n";
    }

    @Override
    String getPng() {
        return "crash.png";
    }
}
