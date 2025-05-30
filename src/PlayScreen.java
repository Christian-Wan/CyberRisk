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
    private Rectangle territories;
    private BufferedImage backgroundImage;
    private Rectangle pick1, pick2, pick3;
    private Rectangle finishButton;
    private boolean gameStart;
    private int activePlayer;

    public PlayScreen(Engine engine) {
        addMouseListener(this);
        setFocusable(true);
        gameStart = false;
        this.engine = engine;
        activePlayer = 0;
        pick1 = new Rectangle(0, 0, 15, 15);
        pick2 = new Rectangle(15, 0, 15, 15);
        pick3 = new Rectangle(30, 0, 15, 15);
        finishButton = new Rectangle(50, 50, 100,50);
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
        g.drawImage(backgroundImage, 0, 0, 1500, 900, null);
        if (!gameStart) {
            g.drawRect(pick1.x, pick1.y, pick1.width, pick1.height);
            g.drawRect(pick2.x, pick2.y, pick1.width, pick1.height);
            g.drawRect(pick3.x, pick3.y, pick1.width, pick1.height);
        }
        else {
            g.drawString("Player " + activePlayer + "'s Turn", 500, 50);
            g.drawRect(finishButton.x, finishButton.y, finishButton.width, finishButton.height);
            for (Territory territory: engine.getTerritories()) {
                territory.draw(g2);
            }
        }
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
            if (!gameStart) {
                if (pick1.contains(clicked)) {
                    gameStart = true;
                    engine.createPlayers(2);
                    System.out.println("2");
                    System.out.println(engine.getPlayers());
                }
                else if (pick2.contains(clicked)) {
                    gameStart = true;
                    engine.createPlayers(3);
                    System.out.println("3");
                    System.out.println(engine.getPlayers());
                }
                else if (pick3.contains(clicked)) {
                    gameStart = true;
                    engine.createPlayers(4);
                    System.out.println("4");
                    System.out.println(engine.getPlayers());
                }
            }
            else {
                if (finishButton.contains(clicked)) {
                    activePlayer++;
                    if (activePlayer == engine.getPlayers().size()) {
                        activePlayer = 0;
                    }
                }
                else {
                    for (Territory territory: engine.getTerritories()) {
                        territory.checkClick(clicked);
                    }
                }
                System.out.println(activePlayer);
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
