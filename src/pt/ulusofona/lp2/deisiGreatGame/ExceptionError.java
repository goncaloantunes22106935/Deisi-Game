package pt.ulusofona.lp2.deisiGreatGame;

import java.util.List;

public class ExceptionError extends Abyss {
    ExceptionError(int id, int position) {
        super(id, position);
        name = "Exception";
    }

    @Override
    String effect(Programmer programmer, List<Programmer> programmers) {
        /*
                File Not Found Exception - O programador recua 2 casas.
        */

        if (programmer.verifyTool(3) || programmer.verifyTool(5) || programmer.verifyTool(100)){
            return "UFAA SAFASTE TE DESSA agora não vais recuar";
        }

        programmer.move(-2);
        return "Vais ter de recuar :C\n";
    }

    @Override
    String getPng() {
        return "exception.png";
    }
}
