import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Engine {

    private Frame frame;
    private ArrayList<Territory> territories = new ArrayList<Territory>();
    private ArrayList<PlayerInfo> players = new ArrayList<PlayerInfo>();
    private PlayScreen playScreen;
    private Questions questions;

    public Engine(Frame frame) {
        this.frame = frame;
        this.playScreen = new PlayScreen(this);
        getTerritoryData();
        for (Territory territory: territories) {
            territory.assignNeighboringTerritories();
            System.out.println(territory.getName());
            System.out.println(territory.getNeighboringTerritories());
        }
        questions = new Questions();
    }

    public void getTerritoryData() {
        File f = new File("src/Territory_Connections");
        Scanner s = null;

        try {
            s = new Scanner(f);
            String line = "";
            while (s.hasNext()) {
                ArrayList<String> territoryInfo = new ArrayList<String>();
                line = s.nextLine();
                while (!Objects.equals(line, "") && !Objects.equals(line, "end")) {
                    territoryInfo.add(line);
                    line = s.nextLine();
                }
                territories.add(new Territory(this, territoryInfo));
            }
        } catch (FileNotFoundException e) {}
    }


    public void createPlayers(int amountOfPlayers) {
        for (int i = 0; i < amountOfPlayers; i++) {
            players.add(new PlayerInfo(this, i, amountOfPlayers));
        }
        for (PlayerInfo player: players) {
            player.assignRandomTerritories();
        }
    }

    public ArrayList<Territory> getTerritories() {
        return territories;
    }

    public PlayScreen getPlayScreen() {
        return playScreen;
    }

    public ArrayList<PlayerInfo> getPlayers() {
        return players;
    }

    public Questions getQuestions() {
        return questions;
    }
}
