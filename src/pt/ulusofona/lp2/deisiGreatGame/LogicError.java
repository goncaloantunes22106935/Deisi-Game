package pt.ulusofona.lp2.deisiGreatGame;

import java.util.List;

public class LogicError extends Abyss {
    LogicError(int id, int position) {
        super(id, position);
        name = "Erro de lógica";
    }

    @Override
    String effect(Programmer programmer, List<Programmer> programmers) {
        /*
                Erro de lógica - O programador recua N casas, sendo N metade do
                valor que tiver saído no dado, arredondado para baixo.
                Por exemplo, se o dado deu 6, o programador recua 3
                casas. Se o dado deu 3, o programador recua 1 casa.
         */

        if (programmer.verifyTool(2) || programmer.verifyTool(5)){
            return "HEHEHEHEH Não sei o que dizer aqui :/ boa acho eu";
        }

        int moves = programmer.getDice() / 2;
        programmer.move(-moves);
        return "Benfica > Barcelona ou seja Portimonense > Barcelona\n";
    }

    @Override
    String getPng() {
        return "logic.png";
    }
}
