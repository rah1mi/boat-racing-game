import java.util.Scanner;
import java.util.Random;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Game {
    River river = new River();
    Player player1 = new Player();
    Boat[] boats;
    private ArrayList<ScoreEntry> topScores = new ArrayList<>();
    
    public void start() {
        Scanner scan = new Scanner(System.in);
        boolean Continue = true;
        
        readTopScores();
        displayTopScores();
        
        while (Continue) {
            river = new River(); // Reset the river
	        player1 = new Player(); 
	        System.out.println("Welcome to Boat Race Jumanji Game created by Group 19! ");
	        System.out.println("Please enter player name to start: ");
	        player1.setName(scan.nextLine());
	
	        river.boat1.setName(player1.getName());
	        river.boat2.setName("Bot");
	
	        river.printRiver(player1);
        


	        while (!river.boat1.isWinner() && !river.boat2.isWinner()) {
	            System.out.println("Please enter r to roll the dice or 'exit' to quit.");
	            String input = scan.nextLine();
	            if (input.equalsIgnoreCase("exit")) {
	                Continue = false;
	                break; // Break out of the inner loop
	            } else if (input.equalsIgnoreCase("r")) {
	                processTurn(river.boat1, player1.getName());
	                if (!river.boat1.isWinner()) {
	                    processTurn(river.boat2, "Bot");
	                }
	            }
	        }

	        if (!Continue) {
	            break; 
	        }
	         
	        System.out.println("Game over. Enter 'y' to play again or anything else to quit.");
	        String restart = scan.nextLine();
	        if (!restart.equalsIgnoreCase("y")) {
	            Continue = false;
            }
	        int totalTurns = river.getBoat("Player").getTotalTurns();
        }
        
        addNewScore(player1.getName(), river.getBoat(player1.getName()).getTotalTurns());
        scan.close();
        System.out.println("Thanks for playing! We are Group 19!");
    }
    

    public void processTurn(Boat boat, String playerName) {
        boat.rollDice();
        boat.increaseTotalTurns();
        String obstacle = river.getObstacle(boat.getRow(), boat.getCol());
        if (obstacle.equals("[" + river.current + "]")) {
            Encounter encounter = new Current();
            encounter.encounter(playerName, river, boat.getRow(), boat.getCol());
        } else if (obstacle.equals("[" + river.trap + "]")) {
            Random r = new Random();
            Encounter encounter;
            if (r.nextBoolean()) {
                encounter = new Monster();
            } else {
                encounter = new Squidward();
            }
            encounter.encounter(playerName, river, boat.getRow(), boat.getCol());
        }
        river.printRiver(player1);
    }
    
    public void readTopScores() {
        try (BufferedReader reader = new BufferedReader(new FileReader("TopScore.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String initials = parts[0];
                    int score = Integer.parseInt(parts[1]);
                    topScores.add(new ScoreEntry(initials, score));
                }
            }
        } catch (IOException e) {
            // Handle the exception if the file does not exist or cannot be read
        }

        // Sort the topScores list in ascending order of score
        Collections.sort(topScores, Comparator.comparingInt(ScoreEntry::getScore));
    }

    public void displayTopScores() {
        System.out.println("Top 5 Scores:");
        for (int i = 0; i < Math.min(5, topScores.size()); i++) {
            ScoreEntry entry = topScores.get(i);
            System.out.println((i + 1) + ". " + entry.getName() + ": " + entry.getScore());
        }
        System.out.println();
    }

    public boolean isTopScore(int totalTurns) {
        return topScores.size() < 5 || totalTurns < topScores.get(topScores.size() - 1).getScore();
    }

    public void addNewScore(String initials, int score) {
        topScores.add(new ScoreEntry(initials, score));
        // Sort the topScores list again
        Collections.sort(topScores, Comparator.comparingInt(ScoreEntry::getScore));
        // Remove any entries beyond the top 5
        if (topScores.size() > 5) {
            topScores.remove(topScores.size() - 1);
        }
        // Save the topScores list to the 'TopScore.txt' file
        saveTopScoresToFile();
    }

    private void saveTopScoresToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("TopScore.txt"))) {
            for (ScoreEntry entry : topScores) {
                writer.write(entry.getName() + "," + entry.getScore());
                writer.newLine();
            }
        } catch (IOException e) {
        	System.out.println("IOExeption error occured");
        }
    }
}

