import java.util.ArrayList;

public class PlayerInfo {

    private Engine engine;
    private int playerID;
    private ArrayList<Territory> ownedTerritories = new ArrayList<Territory>();
    private int numberOfPlayers;


    public PlayerInfo(Engine engine, int ID, int numberOfPlayers) {
        this.engine = engine;
        playerID = ID;
        this.numberOfPlayers = numberOfPlayers;
    }

    public void assignRandomTerritories() {
        int maxTerritories = 42 / numberOfPlayers;
        if (42 % numberOfPlayers > playerID) { maxTerritories++; }
        while (ownedTerritories.size() < maxTerritories) {
            int random =  (int) (Math.random() * 42);
            if (engine.getTerritories().get(random).getPlayerOwner() == null) {
                engine.getTerritories().get(random).setPlayerOwner(this);
                ownedTerritories.add(engine.getTerritories().get(random));
            }
        }
    }

    public void removeTerritory(Territory territory) {
        ownedTerritories.remove(territory);
    }

    public void addTerritory(Territory territory) {
        ownedTerritories.add(territory);
    }

    public int getPlayerID() {
        return playerID;
    }
}
