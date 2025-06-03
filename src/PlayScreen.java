import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PlayScreen extends JPanel implements MouseListener {

    private Engine engine;
    private BufferedImage backgroundImage;
    private Rectangle pick1, pick2, pick3;
    private Rectangle finishButton;
    private boolean gameStart;
    private int activePlayer;
    private String questionState;
    private Rectangle answer1, answer2, answer3;
    private Question currentQuestion;
    private boolean attackSuccess, defenseSuccess;
    private Territory selectedTerritory;
    private boolean gameOver;
    private boolean overview;
    private int attackRoll, defenseRoll;

    public PlayScreen(Engine engine) {
        addMouseListener(this);
        setFocusable(true);
        gameStart = false;
        this.engine = engine;
        activePlayer = 0;
        questionState = "none";
        gameOver = false;
        overview = false;
        pick1 = new Rectangle(300, 400, 200, 100);
        pick2 = new Rectangle(600, 400, 200, 100);
        pick3 = new Rectangle(900, 400, 200, 100);
        answer1 = new Rectangle(300, 400, 200, 100);
        answer2 = new Rectangle(600, 400, 200, 100);
        answer3 = new Rectangle(900, 400, 200, 100);
        finishButton = new Rectangle(50, 650, 113,50);
        try {
            backgroundImage = ImageIO.read(new File("Assets/Risk_game_map.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void update() {
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(new Font("Consolas", Font.PLAIN, 25));

        String playerName = "";
        switch (activePlayer) {
            case 0:
                playerName = "Blue";
                break;
            case 1:
                playerName = "Green";
                break;
            case 2:
                playerName = "Red";
                break;
            case 3:
                playerName = "Pink";
        }

        if (gameOver) {
            g2.drawString(playerName + " Won", 700, 50);

        }
        else if (!gameStart) {
            g.drawString("2 Players", pick1.x + 40, pick1.y + 55);
            g.drawString("3 Players", pick2.x + 40, pick2.y + 55);
            g.drawString("4 Players", pick3.x + 40, pick3.y + 55);
            g2.drawRect(pick1.x, pick1.y, pick1.width, pick1.height);
            g2.drawRect(pick2.x, pick2.y, pick1.width, pick1.height);
            g2.drawRect(pick3.x, pick3.y, pick1.width, pick1.height);
        }
        else if (questionState.equals("dice check")) {
            int winnerID = 0;
            String statement = "";
            if (attackRoll > defenseRoll) {
                winnerID = activePlayer;
                statement = " takes the territory";
            }
            else {
                winnerID = selectedTerritory.getPlayerOwner().getPlayerID();
                statement = " keeps the territory";
            }

            switch (winnerID) {
                case 0:
                    playerName = "Blue";
                    break;
                case 1:
                    playerName = "Green";
                    break;
                case 2:
                    playerName = "Red";
                    break;
                case 3:
                    playerName = "Pink";
            }

            g2.setFont(new Font("Consolas", Font.PLAIN, 25));
            g2.drawString(playerName + statement, 500, 100);


            String attacker = "";
            String defender = "";

            switch (activePlayer) {
                case 0:
                    attacker = "Blue";
                    break;
                case 1:
                    attacker = "Green";
                    break;
                case 2:
                    attacker = "Red";
                    break;
                case 3:
                    attacker = "Pink";
            }

            switch (selectedTerritory.getPlayerOwner().getPlayerID()) {
                case 0:
                    defender = "Blue";
                    break;
                case 1:
                    defender = "Green";
                    break;
                case 2:
                    defender = "Red";
                    break;
                case 3:
                    defender = "Pink";
            }


            g2.setFont(new Font("Consolas", Font.PLAIN, 15));
            g2.drawString(attacker + " Rolled: " + attackRoll, 300, 400);
            g2.drawString(defender + " Rolled: " + defenseRoll, 900, 400);
        }
        else if (!questionState.equals("none")) {
            String attacker = "";
            String defender = "";

            switch (activePlayer) {
                case 0:
                    attacker = "Blue";
                    break;
                case 1:
                    attacker = "Green";
                    break;
                case 2:
                    attacker = "Red";
                    break;
                case 3:
                    attacker = "Pink";
            }

            switch (selectedTerritory.getPlayerOwner().getPlayerID()) {
                case 0:
                    defender = "Blue";
                    break;
                case 1:
                    defender = "Green";
                    break;
                case 2:
                    defender = "Red";
                    break;
                case 3:
                    defender = "Pink";
            }

            g2.setFont(new Font("Consolas", Font.PLAIN, 15));
            g2.drawString(currentQuestion.getQuestionText(), 500, 100);

            int x = answer1.x;
            int y = answer1.y + 10;
            String text = currentQuestion.getChoices().get(0);
            for (int i = 0; i < text.length(); i++) {
                g2.drawString(text.substring(i, i+1), x, y);
                x += 10;
                if (x >= answer1.x + answer1.width) {
                    x = answer1.x;
                    y += 13;
                }
            }
            x = answer2.x;
            y = answer2.y + 10;
            text = currentQuestion.getChoices().get(1);
            for (int i = 0; i < text.length(); i++) {
                g2.drawString(text.substring(i, i+1), x, y);
                x += 10;
                if (x >= answer2.x + answer2.width) {
                    x = answer2.x;
                    y += 13;
                }
            }
            x = answer3.x;
            y = answer3.y + 10;
            text = currentQuestion.getChoices().get(2);
            for (int i = 0; i < text.length(); i++) {
                g2.drawString(text.substring(i, i+1), x, y);
                x += 10;
                if (x >= answer3.x + answer3.width) {
                    x = answer3.x;
                    y += 13;
                }
            }
            g2.drawRect(answer1.x, answer1.y, answer1.width, answer1.height);
            g2.drawRect(answer2.x, answer2.y, answer2.width, answer2.height);
            g2.drawRect(answer3.x, answer3.y, answer3.width, answer3.height);

            g2.setFont(new Font("Consolas", Font.PLAIN, 25));

            if (questionState.equals("attack")) {
                g2.drawString(attacker + " Attacks", 600, 60);
            }
            else {
                g2.drawString(defender + " Defends", 600, 60);
            }

            if (questionState.equals("attack") && overview) {
                if (attackSuccess) {
                    g2.setColor(Color.GREEN);
                    g2.drawString("CORRECT", 650, 700);
                }
                else {
                    g2.setColor(Color.RED);
                    g2.drawString("INCORRECT", 650, 700);
                }
            }
            else if (questionState.equals("defense") & overview) {
                g2.setFont(new Font("Consolas", Font.PLAIN, 25));
                if (defenseSuccess) {
                    g2.setColor(Color.GREEN);
                    g2.drawString("CORRECT", 650, 700);
                }
                else {
                    g2.setColor(Color.RED);
                    g2.drawString("INCORRECT", 650, 700);
                }
            }
        }
        else {

            g2.drawImage(backgroundImage, 0, 0, 1500, 900, null);
            g2.drawString(playerName + "'s Turn", 700, 50);
            g2.drawRect(finishButton.x, finishButton.y, finishButton.width, finishButton.height);
            g2.drawString("End Turn", finishButton.x, finishButton.y + 30);
            for (Territory territory: engine.getTerritories()) {
                territory.draw(g2);
            }
        }
    }

    public void checkAttack() {
        do {
            defenseRoll = (int) (Math.random() * 6) + 1;
            attackRoll = (int) (Math.random() * 6) + 1;

            if (defenseSuccess) {
                defenseRoll += (int) (Math.random() * 6) + 1;
            }
            if (attackSuccess) {
                attackRoll += (int) (Math.random() * 6) + 1;
            }
        } while (defenseRoll == attackRoll);
    }

    private boolean checkGameEnd() {
        for (Territory territory: engine.getTerritories()) {
            if (territory.getPlayerOwner() != engine.getPlayers().get(activePlayer)) {
                return false;
            }
        }
        return true;
    }

    public boolean isGameStart() {
        return gameStart;
    }

    public int getActivePlayer() {
        return activePlayer;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point clicked = e.getPoint();

        if (e.getButton() == 1) {
            System.out.println(overview);
            System.out.println(questionState);
            if (!gameOver) {
                if (!gameStart) {
                    if (pick1.contains(clicked)) {
                        gameStart = true;
                        engine.createPlayers(2);
                        System.out.println("2");
                        System.out.println(engine.getPlayers());
                    } else if (pick2.contains(clicked)) {
                        gameStart = true;
                        engine.createPlayers(3);
                        System.out.println("3");
                        System.out.println(engine.getPlayers());
                    } else if (pick3.contains(clicked)) {
                        gameStart = true;
                        engine.createPlayers(4);
                        System.out.println("4");
                        System.out.println(engine.getPlayers());
                    }
                }
                else if (overview) {
                    switch (questionState) {
                        case "attack":
                            overview = false;
                            questionState = "defense";
                            currentQuestion = engine.getQuestions().getRandomQuestion(Questions.QuestionType.DEFENSE);
                            break;

                        case "defense":
                            questionState = "dice check";
                            checkAttack();
                            break;

                        case "dice check":
                            overview = false;
                            questionState = "none";
                            if (attackRoll > defenseRoll) {
                                selectedTerritory.changeOwner();
                                if (checkGameEnd()) {
                                    gameOver = true;
                                }
                            } else {
                                activePlayer++;
                                if (activePlayer == engine.getPlayers().size()) {
                                    activePlayer = 0;
                                }
                            }
                            break;

                    }
                } else if (questionState.equals("attack")) {
                    if (answer1.contains(clicked)) {
                        overview = true;
                        if (currentQuestion.isCorrect(0)) {
                            attackSuccess = true;
                        } else {
                            attackSuccess = false;
                        }
                    } else if (answer2.contains(clicked)) {
                        overview = true;
                        if (currentQuestion.isCorrect(1)) {
                            attackSuccess = true;
                        } else {
                            attackSuccess = false;
                        }
                    } else if (answer3.contains(clicked)) {
                        overview = true;
                        if (currentQuestion.isCorrect(2)) {
                            attackSuccess = true;
                        } else {
                            attackSuccess = false;
                        }
                    }
                } else if (questionState.equals("defense")) {
                    if (answer1.contains(clicked)) {
                        overview = true;
                        if (currentQuestion.isCorrect(0)) {
                            defenseSuccess = true;
                        } else {
                            defenseSuccess = false;
                        }
                    } else if (answer2.contains(clicked)) {
                        overview = true;
                        if (currentQuestion.isCorrect(1)) {
                            defenseSuccess = true;
                        } else {
                            defenseSuccess = false;
                        }

                    } else if (answer3.contains(clicked)) {
                        overview = true;
                        if (currentQuestion.isCorrect(2)) {
                            defenseSuccess = true;
                        } else {
                            defenseSuccess = false;
                        }

                    }
                } else {
                    if (finishButton.contains(clicked)) {
                        activePlayer++;
                        if (activePlayer == engine.getPlayers().size()) {
                            activePlayer = 0;
                        }
                    } else {
                        for (Territory territory : engine.getTerritories()) {
                            if (territory.checkClick(clicked)) {
                                questionState = "attack";
                                currentQuestion = engine.getQuestions().getRandomQuestion(Questions.QuestionType.ATTACK);
                                selectedTerritory = territory;
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
