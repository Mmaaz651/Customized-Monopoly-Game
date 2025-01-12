package upei.project;

import java.util.ArrayList;

/**
 * This class simulates the game experiment, initializes players, and starts the game.
 * It is responsible for running the game and displaying the result.
 */
public class SimulationExperiment {
    public static void main(String[] args){
        ArrayList<String> players = new ArrayList<>();
        players.add("Maaz");
        players.add("Mubashir");
        players.add("Ahmed");
        players.add("Bilal");
        players.add("Irtiza");


        MonopolyGame game = new MonopolyGame(players);
        game.playGame();
    }
}
