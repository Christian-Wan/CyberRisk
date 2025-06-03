import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Territory {

    private PlayerInfo playerOwner;
    private Engine engine;
    private ArrayList<Territory> neighboringTerritories = new ArrayList<Territory>();
    private ArrayList<String> neighboringTerritoriesString = new ArrayList<String>();
    private String name;
    private Rectangle hitBox;

    public Territory(Engine engine, ArrayList<String> data) {

        this.engine = engine;
        String[] firstLine = data.get(0).split(" ");
        name = firstLine[0];
        int x = Integer.parseInt(firstLine[1].split(",")[0]);
        int y = Integer.parseInt(firstLine[1].split(",")[1]);
        hitBox = new Rectangle(x, y, 30, 30);
        for (int i = 1; i < data.size(); i++) {
            neighboringTerritoriesString.add(data.get(i));
        }
    }


    public void assignNeighboringTerritories() {
        for (String neighbor: neighboringTerritoriesString) {
            for (Territory territory: engine.getTerritories()) {
                if (Objects.equals(territory.getName(), neighbor)) {
                    neighboringTerritories.add(territory);
                }
            }
        }
    }

    public boolean checkClick(Point clicked) {
        if (hitBox.contains(clicked)
                && engine.getPlayers().get(engine.getPlayScreen().getActivePlayer()) != playerOwner
                && engine.getPlayScreen().isGameStart()
                && checkNeighbors(engine.getPlayers().get(engine.getPlayScreen().getActivePlayer()))) {
            return true;
        }
        return false;
    }

    public void changeOwner() {
        engine.getPlayers().get(engine.getPlayScreen().getActivePlayer()).addTerritory(this);
        playerOwner.removeTerritory(this);
        playerOwner = engine.getPlayers().get(engine.getPlayScreen().getActivePlayer());
    }

    public void draw(Graphics2D g) {
        if (playerOwner != null) {
            switch (playerOwner.getPlayerID()) {
                case 0:
                    g.setColor(Color.BLUE);
                    break;
                case 1:
                    g.setColor(Color.GREEN);
                    break;
                case 2:
                    g.setColor(Color.RED);
                    break;
                case 3:
                    g.setColor(Color.PINK);
            }
            g.fillRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
            g.setColor(Color.BLACK);
            g.drawRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
//            g.drawString(name, hitBox.x, hitBox.y);
        }
    }

    private boolean checkNeighbors(PlayerInfo player) {
        for(Territory territory: neighboringTerritories) {
            if (territory.getPlayerOwner() == player) {
                return true;
            }
        }
        return false;
    }




    public String getName() {
        return name;
    }

    public PlayerInfo getPlayerOwner() {
        return playerOwner;
    }

    public void setPlayerOwner(PlayerInfo playerOwner) {
        this.playerOwner = playerOwner;
    }

    public ArrayList<Territory> getNeighboringTerritories() {
        return neighboringTerritories;
    }


}
