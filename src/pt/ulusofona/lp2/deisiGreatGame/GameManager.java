package pt.ulusofona.lp2.deisiGreatGame;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class GameManager implements Serializable{
    private int numberOfPlayers;
    private int boardSize;
    private int plays;
    private int currentPlayer;
    private List<Position> positions;
    private List<Position> abyssesPositions = new ArrayList<>();
    private  List<Tool> tools = new ArrayList<>();
    private  List<Abyss> abysses = new ArrayList<>();
    private List<Programmer> players = new ArrayList<>();
    private List<String> gameResults = new ArrayList<>();


    public GameManager(int boardSize, List<Programmer> players, int numberOfPlayer) {
        this.players = players;
        this.numberOfPlayers = numberOfPlayer;
        this.boardSize = boardSize;
    }

    /*
    setter usado para os testes unitarios
     */
    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /*
    mostra os jogadores a jogar
     */
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    /*
    mostra o tamanho do tabuleiro
     */
    public int getBoardSize() {
        return boardSize;
    }

    /*
    numero de jogadas
     */
    public int getPlays() {
        return plays;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public List<Tool> getTools() {
        return tools;
    }

    public List<Abyss> getAbysses() {
        return abysses;
    }

    public List<Programmer> getPlayers() {
        return players;
    }

    public GameManager() {

    }

    /*
    A função createInitialBoard vai ler a matriz que contem a informação toda acerta dos jogadores que vão ser criados
     */

    public void createInitialBoard(String[][] playerInfo, int worldSize) throws
            InvalidInitialBoardException {
        players.clear();
        this.boardSize = worldSize;
        ArrayList<Integer> usedInts = new ArrayList<>();
        numberOfPlayers = playerInfo.length;
        ArrayList<String> usedColor = new ArrayList<>();
        plays = 1;
        currentPlayer = 0;
        gameResults.clear();
        positions = new ArrayList<Position>(Collections.nCopies(worldSize+10, new Position(0)));

        //Resets feitos
        try{
            if (worldSize < 0 || worldSize < 2 * numberOfPlayers || numberOfPlayers<=1) {
                throw new InvalidInitialBoardException("Invalid WorldSize or Invalid Number of Players",true,false,false,false);
            }
            for (int row = 0; row < numberOfPlayers; row++) {
                String name;
                ArrayList<String> favoriteLanguages = new ArrayList<>();
                int id;
                ProgrammerColor avatarColor;

            /*
            Verificar se o Id é valido
             */
                if (Integer.parseInt(playerInfo[row][0].trim()) < 0 ||
                        usedInts.contains(Integer.parseInt(playerInfo[row][0].trim()))) {
                    throw new InvalidInitialBoardException("Invalid Programmer ID",false,false,false,true);
                }
                id = Integer.parseInt(playerInfo[row][0].trim());
                usedInts.add(Integer.parseInt(playerInfo[row][0].trim()));

            /*
            Verificar se o nome está vazio ou é null
             */
                if (playerInfo[row][1] == null || playerInfo[row][1].isEmpty()) {
                    throw new InvalidInitialBoardException("Invalid Programmer Name",false,true,false,false);
                }
                name = playerInfo[row][1];

            /*
            Guardar as linguagens favoritas
             */
                String[] save = playerInfo[row][2].split(";");
                Collections.addAll(favoriteLanguages, save);

            /*
            Ver se a cor do jogador já foi utilizada e associar cor
             */
                if (usedColor.contains(playerInfo[row][3])) {
                    throw new InvalidInitialBoardException("Invalid Programmer Color",false,false,true,false);
                }

                switch (playerInfo[row][3]) {
                    case "Blue":
                        avatarColor = ProgrammerColor.BLUE;
                        break;
                    case "Brown":
                        avatarColor = ProgrammerColor.BROWN;
                        break;
                    case "Green":
                        avatarColor = ProgrammerColor.GREEN;
                        break;
                    case "Purple":
                        avatarColor = ProgrammerColor.PURPLE;
                        break;
                    default:
                        avatarColor = ProgrammerColor.NONE;
                }
                usedColor.add(playerInfo[row][3]);

                Programmer player = new Programmer(name, id, favoriteLanguages, avatarColor);
                Collections.sort(player.getProgrammerFavLanList());
                players.add(player);
                players.sort(new Programmer.IDComparator());
                Collections.reverse(players);
                currentPlayer = 0;
            }
        }catch (InvalidInitialBoardException e){
            System.out.println(e.getMessage());
        }
    }

    public void createInitialBoard(String[][] playerInfo, int worldSize, String[][] abyssesAndTools) throws
            InvalidInitialBoardException{
        tools.clear();
        int effectId;
        Abyss abyss = null;
        String type;
        int effectPosition;
        abyssesPositions = new ArrayList<Position>(Collections.nCopies(worldSize + 10, new Position(0)));

        createInitialBoard(playerInfo,worldSize);
        try {
            if (abyssesAndTools == null){
                throw new InvalidInitialBoardException("Invalid AbyssesAndTools",false,false,false,false,"");
            }
            for (int row = 0; row < abyssesAndTools.length; row++) {
                type = abyssesAndTools[row][0];
                effectId = Integer.parseInt(abyssesAndTools[row][1]);
                effectPosition = Integer.parseInt(abyssesAndTools[row][2]);

                if(type == null || !type.equals("1") && !type.equals("0")){
                    throw new InvalidInitialBoardException("Invalid EffectType", false, false,true,false,"");
                }

                if(effectId < 0 || (effectId > 9 && type.equals("0") || (effectId > 5 && type.equals("1")))){
                    throw new InvalidInitialBoardException("Invalid EffectId",false,false,false,true,"");
                }

                if (effectPosition < 0 || effectPosition > worldSize) {
                    throw new InvalidInitialBoardException("Invalid EffectPosition",false,false,false,false,"");
                }

                if (type.equals("1")){
                    tools.add(new Tool(effectId,effectPosition));
                }

                if(type.equals("0")){
                    switch (effectId){
                        case 0:
                            abyss = new SyntaxError(effectId,effectPosition);
                            break;
                        case 1:
                            abyss = new LogicError(effectId,effectPosition);
                            break;
                        case 2:
                            abyss = new ExceptionError(effectId, effectPosition);
                            break;
                        case 3:
                            abyss = new FileNotFoundError(effectId,effectPosition);
                            break;
                        case 4:
                            abyss = new CrashError(effectId,effectPosition);
                            break;
                        case 5:
                            abyss = new DuplicatedCode(effectId,effectPosition);
                            break;
                        case 6:
                            abyss = new SecundaryEffects(effectId,effectPosition);
                            break;
                        case 7:
                            abyss = new BlueScreenError(effectId,effectPosition);
                            break;
                        case 8:
                            abyss = new InfiniteCicle(effectId,effectPosition);
                            break;
                        case 9:
                            abyss = new SegmentationFault(effectId,effectPosition);
                            break;
                        default:
                            throw new InvalidInitialBoardException("Invalid Abyss",false,false,false,false,"");
                    }
                    abysses.add(abyss);
                }
            }
        }catch (InvalidInitialBoardException e){
            System.out.println(e.getMessage());
        }
    }

    public String getImagePng(int position) {
        if (position == boardSize) {
            return "glory.png";
        }

        for (Abyss abyss: abysses) {
            if(abyss.position == position){
                return abyss.getPng();
            }
        }

        for (Tool tool: tools) {
            if(tool.position == position){
                return tool.getPng();
            }
        }

        return null;
    }

    public String getTitle(int position){
        for (Abyss abyss: abysses) {
            if(abyss.position == position){
                return abyss.getName() + " : " + position;
            }
        }

        for (Tool tool : tools){
            if (tool.position == position){
                return tool.getName();
            }
        }

        return null;
    }

    /*
    vai filtrar os programadores todos se incluir os que perderam não precisa de filtro
     */
    public List<Programmer> getProgrammers(boolean includeDefeated) {
        List<Programmer> playersAlive = new ArrayList<Programmer>();
        if(includeDefeated){
            return players;
        }

        for (Programmer player: players) {
            if (player.getStatusBool()){
                playersAlive.add(player);
            }
        }

        return playersAlive;
    }

    /*
    com a pesquisa linear vai a todas as posições dos jogadores se for igual adiciona a uma lista com jogadores na mesma
    posição
     */
    public List<Programmer> getProgrammers(int position) {
        List<Programmer> playerOnPosition = new ArrayList<Programmer>();

        for (Programmer player : players) {
            if (player.getPosition() == position) {
                playerOnPosition.add(player);
            }
        }

        return playerOnPosition;
    }

    /*
    Mostra informação dos jogadores e das suas ferramentas
     */
    public String getProgrammersInfo(){
        StringBuilder info = new StringBuilder();
        int count = 0;
        for (Programmer programmer : players) {
            if(programmer.getStatusBool()){
                info.append(programmer.programmerTools()).append(" | ");
                count++;
            }
        }
        if (count == 0){
            return "No Players";
        }
        return info.substring(0,info.length()-3);
    }

    /*
    Se o jogador estiver fora passa o turno e dá o id seguinte
     */
    public int getCurrentPlayerID() {
        if(players.get(currentPlayer).getStatusBool()){
            return players.get(currentPlayer).getId();
        }
        reactToAbyssOrTool();
        return players.get(currentPlayer).getId();
    }

    /*
    Faz com que o jogador se mova caso ele tenha perdido ou num loop infinitio não joga
    quando passa da ultima posição vai x para trás
     */
    public boolean moveCurrentPlayer(int nrSpaces) {
        Programmer programmer = players.get(currentPlayer);
        programmer.setDice(nrSpaces);

        if (nrSpaces < 1 || nrSpaces > 6 || !programmer.getStatusBool() || programmer.isStuck()) {
            return false;
        } else {
            //A posição não pode ser maior que o tamanho do tabuleiro se for faz esta conta e vai esse numero para trás
            if (nrSpaces + programmer.getPosition() > boardSize) {
                nrSpaces = boardSize - programmer.getPosition() - nrSpaces;
            }

            positions.set(nrSpaces+programmer.getPosition(),new Position(positions.get(nrSpaces+programmer.getPosition()).getPosition()+1));
            if (programmer.getColor() == ProgrammerColor.GREEN || programmer.getColor() == ProgrammerColor.PURPLE){
                if ((programmer.getPosition() + nrSpaces) % 3 == 0){
                    return false;
                }
            }
            programmer.move(nrSpaces);
            return true;
        }
    }
    /*
    Dá update ao numero de jogadores pois se 1 sair não vai dar current player
     */
    public void updateNumberOfPlayers(){
        int count = 0;
        for (Programmer programmer:players){
            if (programmer.getStatusBool()){
                count++;
            }
        }
        numberOfPlayers = count;
    }

    /*
    Função que passa o turno
     */
    public void skipTurn(){
        currentPlayer = (currentPlayer + 1) % numberOfPlayers;
        plays++;
    }

    /*
    Reage aos abismos e às ferramentas e passa o turno caso não exista abismo ou ferramenta dá return de null
     */
    public String reactToAbyssOrTool(){
        //updateNumberOfPlayers();
        int staticCurrent = currentPlayer;
        Programmer programmer = players.get(staticCurrent);
        skipTurn();
        for (Abyss abyss: abysses) {
            if(programmer.getPosition() == abyss.getPosition() && programmer.getStatusBool()){
                abyssesPositions.set(abyss.position, new Position(abyssesPositions.get(abyss.position).getPosition() + 1,abyss.name));
                return abyss.effect(programmer,players);
            }
        }

        for (Tool tool: tools) {
            if(programmer.getPosition() == tool.getPosition() && programmer.getStatusBool()){
                tool.giveTool(programmer);
                return tool.getName();
            }
        }
        return null;
    }

    public List<Position> getPositions(){
        return positions;
    }


    /*
    verifica se o jogo terminou
     */
    public boolean gameIsOver() {
        int contador = 0;

        for (Programmer programmer : players) {
            if (programmer.getPosition() > boardSize / 2) {
                return true;
            }

            if (!programmer.getStatusBool()){
                contador++;
            }
        }
        if(contador == numberOfPlayers - 1){
            return true;
        }

        return false;
    }

    /*
    Função um pouco nojenta mas devido a termos feito o trabalho em cima da hora
    não conseguimos fazer um painel melhor mas a ideia é esta
     */
    public JPanel getAuthorsPanel() {
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 300, 300);
        panel.setBackground(Color.gray);

        JButton button1 = new JButton("Créditos");
        button1.setBounds(50, 100, 80, 30);
        button1.setBackground(Color.white);

        JButton button2 = new JButton("Inspiração");
        button2.setBounds(100, 100, 80, 30);
        button2.setBackground(Color.white);

        panel.add(button1);
        panel.add(button2);

        button1.addActionListener(event -> {
            JLabel label = new JLabel("<html>Realizado por:<br/>Cláudio Costa<br/>Gonçalo Antunes</html>", SwingConstants.CENTER);
            label.setFont(new Font("Verdana", Font.BOLD, 20));
            JLabel imageLabel = new JLabel();
            JLabel imageLabel2 = new JLabel();
            imageLabel.setIcon(new ImageIcon("src\\pt\\ulusofona\\lp2\\deisiGreatGame\\resources\\Cláudio.png"));
            imageLabel2.setIcon(new ImageIcon("src\\pt\\ulusofona\\lp2\\deisiGreatGame\\resources\\Gonçalo.png"));
            panel.add(label);
            panel.add(imageLabel);
            panel.add(imageLabel2);
            panel.setBorder(new LineBorder(Color.BLACK));
        });

        button2.addActionListener(event -> {
            JLabel imageLabel = new JLabel();
            imageLabel.setIcon(new ImageIcon("src\\pt\\ulusofona\\lp2\\deisiGreatGame\\resources\\Ric.png"));
            panel.add(imageLabel);
            panel.setBorder(new LineBorder(Color.BLACK));
        });

        return panel;
    }


    public List<String> getGameResults() {
        players.sort(new Programmer.PositionComparator());
        String vencedor = players.get(0).getName();
        gameResults.add("O GRANDE JOGO DO DEISI");
        gameResults.add("");
        gameResults.add("NR. DE TURNOS");
        gameResults.add(plays+"");
        gameResults.add("");
        gameResults.add("VENCEDOR");
        gameResults.add(vencedor);
        gameResults.add("");
        gameResults.add("RESTANTES");
        if(samePosition()){
            players.sort(new Programmer.NameComparator());
            for (Programmer programmer : players) {
                if (vencedor.equals(programmer.getName())) {
                }else{
                    gameResults.add(programmer.getName() + " " + programmer.getPosition());
                }
            }
        }else {
            restOfPlayers();
        }
        return gameResults;
    }

    /*
    Desculpa professor
     */

    public void restOfPlayers(){
        for (Programmer programmer : players) {
            if (programmer == players.get(0)) {
            }else{
                gameResults.add(programmer.getName() + " " + programmer.getPosition());
            }
        }
    }

    public boolean samePosition(){
        int pos = players.get(1).getPosition();
        int count = 0;
        for (Programmer player:players) {
            if(pos == player.getPosition()){
                count++;
            }
        }
        if (count == numberOfPlayers-1){
            return true;
        }
        return false;
    }

    public List<Position> getAbyssesPositions() {
        return abyssesPositions;
    }

    public boolean saveGame(File file){
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file.getName()));
            out.writeObject(this);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public boolean loadGame(File file){
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file.getName()));
            GameManager p1 =(GameManager) in.readObject();
            //clear();
            numberOfPlayers = p1.numberOfPlayers;
            boardSize = p1.boardSize;
            plays = p1.plays;
            currentPlayer = p1.currentPlayer;
            positions = p1.positions;
            abyssesPositions = p1.abyssesPositions;
            tools = p1.tools;
            abysses = p1.abysses;
            players = p1.players;
            gameResults = p1.gameResults;
        } catch (IOException | ClassNotFoundException e) {
            return false;
        }
        return true;
    }

    public void clear(){
        positions.clear();
        abyssesPositions.clear();
        tools.clear();
        abysses.clear();
        players.clear();
        gameResults.clear();
    }

    public List<String> getProgrammersBetweenPositions(int p1, int p2) {
        List<String> listaParaRetornar = new ArrayList<>();

        for (Programmer programmer : players){
            if (programmer.getPosition() >= p1 && programmer.getPosition() <= p2){
                listaParaRetornar.add(programmer.getName() + " : " + programmer.getPosition());
            }
        }

        return listaParaRetornar;
    }
}

