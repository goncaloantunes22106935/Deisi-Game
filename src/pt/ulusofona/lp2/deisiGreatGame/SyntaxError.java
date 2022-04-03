package pt.ulusofona.lp2.deisiGreatGame;

import java.util.List;

public class SyntaxError extends Abyss{
    SyntaxError(int id, int position) {
        super(id, position);
        name = "Erro de sintaxe";
    }

    @Override
    String effect(Programmer programmer, List<Programmer> programmers) {
         /*
                Erro de sintaxe - O programador recua 1 casa.
         */

        if(programmer.verifyTool(4) || programmer.verifyTool(5)){
            return "Foste ler a documentação";
        }
        programmer.move(-1);
        return "i'm not as think as you drunk i am\n";
    }

    @Override
    String getPng() {
        return "syntax.png";
    }

}
