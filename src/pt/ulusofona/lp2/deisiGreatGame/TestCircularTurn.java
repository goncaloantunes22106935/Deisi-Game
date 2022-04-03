package pt.ulusofona.lp2.deisiGreatGame;

import org.junit.Assert;
import org.junit.Test;

public class TestCircularTurn {

    /*
    O primeiro turno é do jogador que está na primeira linha da matriz
     */
    @Test
    public void testCurrentId() throws InvalidInitialBoardException {
        TestCreateMatrix m1 = new TestCreateMatrix();
        GameManager gameManager = new GameManager();
        gameManager.createInitialBoard(m1.createMatrix2Players(), 79);
        Assert.assertEquals(1, gameManager.getCurrentPlayerID());
    }

    /*
    Vai jogar 1 turno e quando 1 jogardor joga o turno passa
     */
    @Test
    public void testCurrentId2() throws InvalidInitialBoardException {
        TestCreateMatrix m1 = new TestCreateMatrix();
        GameManager gameManager = new GameManager();
        gameManager.createInitialBoard(m1.createMatrix2Players(), 79);
        long startTime = System.currentTimeMillis();
        gameManager.moveCurrentPlayer(2);
        gameManager.reactToAbyssOrTool();
        long elapsedTime =System.currentTimeMillis() - startTime;
        Assert.assertEquals(12, gameManager.getCurrentPlayerID());
        System.out.println(elapsedTime);
    }

    @Test
    public void testCurrentId3() throws InvalidInitialBoardException {
        TestCreateMatrix m1 = new TestCreateMatrix();
        GameManager gameManager = new GameManager();
        gameManager.createInitialBoard(m1.createMatrix4Players(), 79);
        gameManager.moveCurrentPlayer(2);//ID 1 vai para ID 12
        gameManager.reactToAbyssOrTool();
        gameManager.moveCurrentPlayer(5);//ID 12 vai para ID 28
        gameManager.reactToAbyssOrTool();
        gameManager.moveCurrentPlayer(3);//ID 28 vai para ID 69
        gameManager.reactToAbyssOrTool();
        Assert.assertEquals(69, gameManager.getCurrentPlayerID());
    }
    @Test
    public void testCurrentPosition() throws InvalidInitialBoardException {
        TestCreateMatrix m1 = new TestCreateMatrix();
        GameManager gameManager = new GameManager();
        gameManager.createInitialBoard(m1.createMatrix4Players(), 79);
        long startTime = System.currentTimeMillis();
        gameManager.moveCurrentPlayer(2);
        gameManager.reactToAbyssOrTool();
        gameManager.moveCurrentPlayer(5);
        gameManager.reactToAbyssOrTool();
        gameManager.moveCurrentPlayer(3);
        gameManager.reactToAbyssOrTool();
        gameManager.moveCurrentPlayer(4);
        gameManager.reactToAbyssOrTool();
        gameManager.moveCurrentPlayer(4);
        gameManager.reactToAbyssOrTool();
        long elapsedTime =System.currentTimeMillis() - startTime;
        Assert.assertEquals(1, gameManager.getProgrammers(7).get(0).getId());
        System.out.println(elapsedTime);
    }
    @Test
    public void testCurrentPosition2() throws InvalidInitialBoardException {
        TestCreateMatrix m1 = new TestCreateMatrix();
        GameManager gameManager = new GameManager();
        gameManager.createInitialBoard(m1.createMatrix4Players(), 79);
        gameManager.moveCurrentPlayer(1);
        gameManager.reactToAbyssOrTool();
        gameManager.moveCurrentPlayer(2);
        gameManager.reactToAbyssOrTool();
        gameManager.moveCurrentPlayer(2);
        gameManager.reactToAbyssOrTool();
        gameManager.moveCurrentPlayer(2);
        gameManager.reactToAbyssOrTool();
        gameManager.moveCurrentPlayer(1);
        gameManager.reactToAbyssOrTool();
        Assert.assertEquals(gameManager.getPlayers(), gameManager.getProgrammers(3));
    }
}
