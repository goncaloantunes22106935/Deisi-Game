package pt.ulusofona.lp2.deisiGreatGame;

import java.util.List;

public class InfiniteCicle extends Abyss{
    InfiniteCicle(int id, int position) {
        super(id, position);
        name = "Ciclo infinito";
    }

    @Override
    String effect(Programmer programmer, List<Programmer> programmers) {
        /*
                Ciclo infinito O programador fica preso na casa onde está até que
                lá apareça outro programador para o ajudar.
                O programador que aparecer para ajudar, fica ele
                próprio preso (mas liberta o que já lá estava).
                Caso o programador que aparece tenha uma
                ferramenta que permita livrar-se do abismo, ele não
                fica preso mas também já não liberta o programador
                que lá estava.
        */
        if(programmer.verifyTool(1)){
            return "Usaste a tool e agora estás safo hehehe";
        }

        for (Programmer programmer1: programmers) {
            if(programmer1.getPosition() == programmer.getPosition() && programmer != programmer1){
                programmer1.save();
                programmer.stuck();
                return "foste Salvo hehehehe obrigado Idiota";
            }
        }


        programmer.stuck();

        return "sadasdasd\nsdsdfsdf\nasdasdasd\nfsdfsdfdfg\netc...\n";
    }

    @Override
    String getPng() {
        return "infinite-loop.png";
    }
}
