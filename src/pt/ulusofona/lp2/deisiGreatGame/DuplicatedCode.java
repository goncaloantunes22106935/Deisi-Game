package pt.ulusofona.lp2.deisiGreatGame;

import java.util.List;

public class DuplicatedCode extends Abyss {
    DuplicatedCode(int id, int position) {
        super(id, position);
        name = "Duplicated Code";
    }

    @Override
    String effect(Programmer programmer, List<Programmer> programmers) {
        /*
                Duplicated Code - O programador recua até à casa onde estava antes de chegar a esta casa.
        */

        if (programmer.verifyTool(0)){
            return ":) Boa poupaste umas boas linhas";
        }

        programmer.goBackXTurns(1);
        return "Duplicated Code\nDuplicated Code\n";
    }

    @Override
    String getPng() {
        return "duplicated-code.png";
    }
}
