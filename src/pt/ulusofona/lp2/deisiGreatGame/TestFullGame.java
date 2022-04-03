package pt.ulusofona.lp2.deisiGreatGame;

import org.junit.Assert;
import org.junit.Test;

public class TestFullGame {
    @Test
    public void testProgrammerAbyss5() throws InvalidInitialBoardException {
        TestCreateMatrix m1 = new TestCreateMatrix();
        GameManager gameManager = new GameManager();
        gameManager.createInitialBoard(m1.createMatrix2Players2(), 10,m1.createEffectsMatrix3());


        gameManager.moveCurrentPlayer(5);
        gameManager.reactToAbyssOrTool();

        gameManager.moveCurrentPlayer(3);
        gameManager.reactToAbyssOrTool();

        gameManager.moveCurrentPlayer(4);
        gameManager.reactToAbyssOrTool();


        System.out.println(gameManager.getPlayers().get(0).getPosition());
        System.out.println(gameManager.getPlayers().get(0).getName());
        System.out.println(gameManager.getPlayers().get(1).getPosition());

        Assert.assertEquals("[O GRANDE JOGO DO DEISI, , NR. DE TURNOS, 4, , VENCEDOR, Bongas, , RESTANTES, Aoao 4]"
                ,gameManager.getGameResults().toString());

    }

    @Test
    public void testProgrammerAbyss6() throws InvalidInitialBoardException {
        TestCreateMatrix m1 = new TestCreateMatrix();
        GameManager gameManager = new GameManager();
        gameManager.createInitialBoard(m1.createMatrix4Players2(), 10,m1.createEffectsMatrix3());


        gameManager.moveCurrentPlayer(5);
        gameManager.reactToAbyssOrTool();

        gameManager.moveCurrentPlayer(1);
        gameManager.reactToAbyssOrTool();

        gameManager.moveCurrentPlayer(4);
        gameManager.reactToAbyssOrTool();

        gameManager.moveCurrentPlayer(4);
        gameManager.reactToAbyssOrTool();

        gameManager.moveCurrentPlayer(3);
        gameManager.reactToAbyssOrTool();

        gameManager.moveCurrentPlayer(4);
        gameManager.reactToAbyssOrTool();


        Assert.assertEquals("[O GRANDE JOGO DO DEISI, , NR. DE TURNOS, 7, , VENCEDOR, gongas, , RESTANTES, Alaudio 5, Aoao 5, Covane 5]"
                ,gameManager.getGameResults().toString());

    }

    @Test
    public void testProgrammerAbyss7() throws InvalidInitialBoardException {
        TestCreateMatrix m1 = new TestCreateMatrix();
        GameManager gameManager = new GameManager();
        gameManager.createInitialBoard(m1.createMatrix4Players2(), 10,m1.createEffectsMatrix8());

        System.out.println(gameManager.getPlayers().get(gameManager.getCurrentPlayer()).getPosition());
        gameManager.moveCurrentPlayer(5);
        gameManager.reactToAbyssOrTool();

        gameManager.moveCurrentPlayer(1);
        gameManager.reactToAbyssOrTool();

        gameManager.moveCurrentPlayer(6);
        gameManager.reactToAbyssOrTool();

        gameManager.moveCurrentPlayer(4);
        gameManager.reactToAbyssOrTool();

        gameManager.moveCurrentPlayer(2);
        gameManager.reactToAbyssOrTool();

        System.out.println(gameManager.getPlayers().get(gameManager.getCurrentPlayer()).getPosition());


        Assert.assertEquals("[1 | gongas | 6 | No tools | java; python | Em Jogo, 12 | Aoao | 2 | No tools | java | Em Jogo, 28 | Covane | 4 | No tools | GoLang; Lua; Rust | Em Jogo, 69 | Alaudio | 5 | No tools | C; C#; C++ | Em Jogo]"
                ,gameManager.getPlayers().toString());

    }
}
