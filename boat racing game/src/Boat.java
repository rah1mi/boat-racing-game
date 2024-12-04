import java.util.Random;

public class Boat {
    private String name;
    private int row = 0;
    private int col = 0;
    private int score;
    private boolean isWinner;
    private int roll;
    private Encounter encounter;
    private int totalTurns;

    public void rollDice() {
        Random r = new Random();
        roll = r.nextInt(6) + 1;

        // Calculate the new position after rolling the dice
        int newPosition = (row * 10 + col) + roll;
        row = newPosition / 10; // Calculate the new row
        col = newPosition % 10; // Calculate the new column

        System.out.printf("%s rolls a %d\n", name, roll);
    }

    
    public void increaseTotalTurns() {
        totalTurns++;
    }


    public boolean isWinner() {
    	if ((row * 10 + col) >= 99) { // This will correspond to the 100th position
            System.out.printf("%s Wins!\n", name);
            score++;
            return true;
        } else {
            return false;
        }
    }
    
    
    public int getPosition() {
        return row * 10 + col + 1;
    }


    public void setRow(int row) {
            this.row = row;
     }

     public void setCol(int col) {
            this.col = col;
        }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
    
    public int getTotalTurns() {
        return totalTurns;
    }
}