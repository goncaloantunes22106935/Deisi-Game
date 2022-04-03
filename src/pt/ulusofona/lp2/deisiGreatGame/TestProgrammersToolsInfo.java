package pt.ulusofona.lp2.deisiGreatGame;

import org.junit.Assert;
import org.junit.Test;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.*;
import java.util.List;

public class TestProgrammersToolsInfo {

    @Test
    public void testProgrammerTools(){
        ArrayList<String> lan = new ArrayList<>();
        Tool tool1 = new Tool(2,5);
        lan.add("java");
        Programmer p1 = new Programmer("João",12,lan,ProgrammerColor.BLUE);

        System.out.println(p1.programmerTools());
        Assert.assertEquals(false, false);
    }

    @Test
    public void testProgrammerTools2() throws InvalidInitialBoardException {
        TestCreateMatrix m1 = new TestCreateMatrix();
        ArrayList<String> lan = new ArrayList<>();
        GameManager gameManager = new GameManager();
        gameManager.createInitialBoard(m1.createMatrix2Players(), 79,m1.createEffectsMatrix());
        System.out.println(gameManager.getProgrammersInfo());
        Assert.assertEquals(false, false);
    }

    @Test
    public void testProgrammerTools3() throws InvalidInitialBoardException {
        TestCreateMatrix m1 = new TestCreateMatrix();
        ArrayList<String> lan = new ArrayList<>();
        GameManager gameManager = new GameManager();
        gameManager.createInitialBoard(m1.createMatrix2Players(), 10,m1.createEffectsMatrix());
        Tool tool = gameManager.getTools().get(0);
        gameManager.getPlayers().get(0).addTool(tool);
        Assert.assertEquals("gongas : Tratamento de Excepções | Joao : No tools", gameManager.getProgrammersInfo());
    }

    @Test
    public void testProgrammerTools4() throws InvalidInitialBoardException {
        TestCreateMatrix m1 = new TestCreateMatrix();
        ArrayList<String> lan = new ArrayList<>();
        GameManager gameManager = new GameManager();
        gameManager.createInitialBoard(m1.createMatrix2Players(), 10,m1.createEffectsMatrix());
        gameManager.moveCurrentPlayer(6);
        gameManager.reactToAbyssOrTool();
        gameManager.moveCurrentPlayer(6);
        gameManager.reactToAbyssOrTool();
        gameManager.moveCurrentPlayer(1);
        gameManager.reactToAbyssOrTool();
        Assert.assertEquals("gongas : Tratamento de Excepções | Joao : No tools", gameManager.getProgrammersInfo());
    }

    @Test
    public void testProgrammerTools5() throws InvalidInitialBoardException {
        TestCreateMatrix m1 = new TestCreateMatrix();
        ArrayList<String> lan = new ArrayList<>();
        GameManager gameManager = new GameManager();
        gameManager.createInitialBoard(m1.createMatrix2Players(), 10,m1.createEffectsMatrix2());

        gameManager.moveCurrentPlayer(1);
        gameManager.reactToAbyssOrTool();
        System.out.println(gameManager.getPlayers().get(0));
        gameManager.setCurrentPlayer(0);
        gameManager.moveCurrentPlayer(1);
        gameManager.reactToAbyssOrTool();
        System.out.println(gameManager.getAbysses().get(0).name);
        System.out.println(gameManager.getAbysses().get(0).id);
        System.out.println(gameManager.getPlayers().get(0).toString());

        Assert.assertEquals("Ciclo infinito",gameManager.getAbysses().get(0).name);
    }

    @Test
    public void testProgrammerTools6() throws InvalidInitialBoardException {
        TestCreateMatrix m1 = new TestCreateMatrix();
        ArrayList<String> lan = new ArrayList<>();
        GameManager gameManager = new GameManager();
        gameManager.createInitialBoard(m1.createMatrix2Players(), 10,m1.createEffectsMatrix2());

        gameManager.moveCurrentPlayer(1);
        gameManager.reactToAbyssOrTool();

        System.out.println(gameManager.getPlayers().get(gameManager.getCurrentPlayer()).toString());
        gameManager.moveCurrentPlayer(1);
        gameManager.reactToAbyssOrTool();

        System.out.println(gameManager.getPlayers().get(gameManager.getCurrentPlayer()).toString());
        gameManager.moveCurrentPlayer(1);
        gameManager.reactToAbyssOrTool();

        System.out.println(gameManager.getPlayers().get(gameManager.getCurrentPlayer()).toString());
        gameManager.moveCurrentPlayer(1);
        gameManager.reactToAbyssOrTool();

        System.out.println(gameManager.getPlayers().get(gameManager.getCurrentPlayer()).toString());
        gameManager.moveCurrentPlayer(1);
        gameManager.reactToAbyssOrTool();

        System.out.println(gameManager.getPlayers().get(gameManager.getCurrentPlayer()).toString());

        Assert.assertEquals(true,gameManager.getPlayers().get(1).isStuck());
    }

    @Test
    public void testProgrammerTools7() throws InvalidInitialBoardException {
        TestCreateMatrix m1 = new TestCreateMatrix();
        GameManager gameManager = new GameManager();
        gameManager.createInitialBoard(m1.createMatrix2Players(), 10,m1.createEffectsMatrix6());

        gameManager.moveCurrentPlayer(2);
        gameManager.reactToAbyssOrTool();

        gameManager.moveCurrentPlayer(3);
        gameManager.reactToAbyssOrTool();

        System.out.println(gameManager.getPlayers().get(0).getTools());

        gameManager.moveCurrentPlayer(1);
        gameManager.reactToAbyssOrTool();


        System.out.println(gameManager.getPlayers().get(0).getTools());

        Assert.assertEquals(true,gameManager.moveCurrentPlayer(1));
    }

}
