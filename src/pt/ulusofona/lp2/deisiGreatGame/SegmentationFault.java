package pt.ulusofona.lp2.deisiGreatGame;

import java.util.ArrayList;
import java.util.List;

public class SegmentationFault extends Abyss{
    SegmentationFault(int id, int position) {
        super(id, position);
        name = "Segmentation Fault";
    }

    @Override
    String effect(Programmer programmer, List<Programmer> programmers) {
        /*
                Segmentation Fault Este Abismo apenas é activado caso existam dois ou
                mais programadores na mesma casa.
                Todos os jogadores nessa casa recuam 3 casas.
                Caso apenas esteja um programador neste Abismo,
                então não existe nenhum efeito a aplicar.
         */
        boolean ativar = false;//Ativar um loop para recuarem todos os jogadores
        ArrayList<Programmer> sameTileProgrammers = new ArrayList<>();
        sameTileProgrammers.add(programmer);
        /*
        Vai fazer um loop em todos os programadores da lista
        se a posição for igual vai ativar o trigger para recuar
        e adiciona os jogadores
         */
        for (Programmer programmer1:programmers) {
            if (programmer.getColor() == programmer1.getColor()){
                continue;
            }
            if (programmer.getPosition() == programmer1.getPosition()){
                sameTileProgrammers.add(programmer1);
                ativar = true;
            }
        }
        if (ativar){
            for (Programmer programmer1:sameTileProgrammers) {
                programmer1.move(-3);
            }
            return "COVID SENHORES QUERO DISTANCIA";
        }
        return "FIXE ESTÁS SOZINHO";
    }

    @Override
    String getPng() {
        return "core-dumped.png";
    }
}
