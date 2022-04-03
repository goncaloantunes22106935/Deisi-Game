package pt.ulusofona.lp2.deisiGreatGame;

import org.junit.Assert;
import org.junit.Test;

public class TestProgrammerAbyss {
    @Test
    public void testProgrammerAbyss() throws InvalidInitialBoardException {
        TestCreateMatrix m1 = new TestCreateMatrix();
        GameManager gameManager = new GameManager();
        gameManager.createInitialBoard(m1.createMatrix2Players(), 10,m1.createEffectsMatrix3());

        gameManager.moveCurrentPlayer(1);
        gameManager.reactToAbyssOrTool();
        System.out.println(gameManager.getAbysses().get(0).name);
        gameManager.moveCurrentPlayer(2);
        gameManager.reactToAbyssOrTool();

        Assert.assertEquals("Duplicated Code\n" +
                "Duplicated Code","Duplicated Code\n" +
                "Duplicated Code");
        Assert.assertEquals(1,gameManager.getPlayers().get(0).getPosition());
    }

    @Test
    public void testProgrammerAbyss2() throws InvalidInitialBoardException {
        TestCreateMatrix m1 = new TestCreateMatrix();
        GameManager gameManager = new GameManager();
        gameManager.createInitialBoard(m1.createMatrix2Players(), 10,m1.createEffectsMatrix4());

        gameManager.moveCurrentPlayer(6);
        gameManager.reactToAbyssOrTool();
        System.out.println(gameManager.getAbysses().get(0).name);
        gameManager.moveCurrentPlayer(6);
        gameManager.reactToAbyssOrTool();
        Assert.assertEquals(4,gameManager.getPlayers().get(0).getPosition());
        Assert.assertEquals(4,gameManager.getPlayers().get(1).getPosition());
        gameManager.moveCurrentPlayer(3);
        gameManager.reactToAbyssOrTool();
        Assert.assertEquals(7,gameManager.getPlayers().get(0).getPosition());
    }

    @Test
    public void testProgrammerAbyss3() throws InvalidInitialBoardException {
        TestCreateMatrix m1 = new TestCreateMatrix();
        GameManager gameManager = new GameManager();
        gameManager.createInitialBoard(m1.createMatrix2Players(), 10,m1.createEffectsMatrix4());

        gameManager.moveCurrentPlayer(2);
        gameManager.reactToAbyssOrTool();
        System.out.println(gameManager.getAbysses().get(1).name);
        Assert.assertEquals(2,gameManager.getPlayers().get(0).getPosition());

    }

    @Test
    public void testProgrammerAbyss4() throws InvalidInitialBoardException {
        TestCreateMatrix m1 = new TestCreateMatrix();
        GameManager gameManager = new GameManager();
        gameManager.createInitialBoard(m1.createMatrix2Players(), 10,m1.createEffectsMatrix5());

        gameManager.moveCurrentPlayer(2);
        gameManager.reactToAbyssOrTool();
        System.out.println(gameManager.getAbysses().get(0).name);
        gameManager.moveCurrentPlayer(2);
        gameManager.reactToAbyssOrTool();

        gameManager.moveCurrentPlayer(5);
        gameManager.reactToAbyssOrTool();
        Assert.assertEquals(3,gameManager.getPlayers().get(0).getPosition());

    }

    @Test
    public void testProgrammerAbyss5() throws InvalidInitialBoardException {
        TestCreateMatrix m1 = new TestCreateMatrix();
        GameManager gameManager = new GameManager();
        gameManager.createInitialBoard(m1.createMatrix2Players(), 10,m1.createEffectsMatrix7());

        Assert.assertEquals("gongas : No tools | Joao : No tools",gameManager.getProgrammersInfo());

        gameManager.moveCurrentPlayer(2);
        gameManager.reactToAbyssOrTool();

        gameManager.moveCurrentPlayer(1);
        gameManager.reactToAbyssOrTool();

        System.out.println(gameManager.getCurrentPlayerID());

        Assert.assertEquals("gongas : No tools",gameManager.getProgrammersInfo());

    }
}
