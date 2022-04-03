package pt.ulusofona.lp2.deisiGreatGame;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class TestGetProgrammers {

    @Test
    public void TestCreateInitialBoard1() throws InvalidInitialBoardException {
        TestCreateMatrix m1 = new TestCreateMatrix();
        GameManager gameManager = new GameManager();
        gameManager.createInitialBoard(m1.createMatrix2Players(), 79);
        Assert.assertEquals("[1 | gongas | 1 | No tools | java; python | Em Jogo, 12 | Joao | 1 | No tools | java | Em Jogo]"
                , gameManager.getPlayers().toString());
    }

    @Test
    public void TestGetProgrammers2
            () throws InvalidInitialBoardException {
        TestCreateMatrix m1 = new TestCreateMatrix();
        GameManager gameManager = new GameManager();
        gameManager.createInitialBoard(m1.createMatrix2Players(),15,m1.createEffectsMatrix3());
        gameManager.moveCurrentPlayer(2);
        gameManager.reactToAbyssOrTool();
        System.out.println(gameManager.getProgrammers(1));
        Assert.assertEquals("[12 | Joao | 1 | No tools | java | Em Jogo]"
                , gameManager.getProgrammers(1).toString());
    }

    @Test
    public void TestGetProgrammersInfo() throws InvalidInitialBoardException {
        TestCreateMatrix m1 = new TestCreateMatrix();
        GameManager gameManager = new GameManager();
        gameManager.createInitialBoard(m1.createMatrix2Players(), 79);
        System.out.println(gameManager.getProgrammers(1));
        Assert.assertEquals("gongas : No tools | Joao : No tools"
                , gameManager.getProgrammersInfo());
    }

    @Test
    public void TestGetProgrammersInfo2() throws InvalidInitialBoardException {
        TestCreateMatrix m1 = new TestCreateMatrix();
        GameManager gameManager = new GameManager();
        gameManager.createInitialBoard(m1.createMatrix2Players(),15,m1.createEffectsMatrix9());
        System.out.println(gameManager.getPlayers().get(0).getPosition());
        gameManager.moveCurrentPlayer(1);
        gameManager.reactToAbyssOrTool();
        System.out.println(gameManager.getPlayers().get(0).getPosition());

        gameManager.moveCurrentPlayer(2);
        gameManager.reactToAbyssOrTool();

        Assert.assertEquals("gongas : IDE | Joao : Testes unitários"
                , gameManager.getProgrammersInfo());
    }

    @Test
    public void TestGetProgrammersInfo3() throws InvalidInitialBoardException {
        TestCreateMatrix m1 = new TestCreateMatrix();
        GameManager gameManager = new GameManager();
        gameManager.createInitialBoard(m1.createMatrix2Players(),15,m1.createEffectsMatrix10());

        gameManager.moveCurrentPlayer(1);
        gameManager.reactToAbyssOrTool();

        gameManager.moveCurrentPlayer(2);
        gameManager.reactToAbyssOrTool();

        gameManager.moveCurrentPlayer(1);
        gameManager.reactToAbyssOrTool();

        Assert.assertEquals("gongas : No tools | Joao : No tools"
                , gameManager.getProgrammersInfo());
    }

    @Test
    public void TestGetProgrammersInfo4() throws InvalidInitialBoardException {
        TestCreateMatrix m1 = new TestCreateMatrix();
        GameManager gameManager = new GameManager();
        gameManager.createInitialBoard(m1.createMatrix2Players(),15,m1.createEffectsMatrix10());

        gameManager.moveCurrentPlayer(1);
        gameManager.reactToAbyssOrTool();

        gameManager.moveCurrentPlayer(2);
        gameManager.reactToAbyssOrTool();

        gameManager.moveCurrentPlayer(1);
        gameManager.reactToAbyssOrTool();

        Assert.assertEquals("gongas : No tools | Joao : No tools"
                , gameManager.getProgrammersInfo());
    }


}
