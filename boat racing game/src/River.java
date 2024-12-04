import java.util.Random;

public class River {
    private int size = 10; // size of the board
    private String empty = "[ ]";
    char current = 'C';
    char trap = '#';
    String[][] riverBoard = new String[size][size];
    Boat boat1 = new Boat();
    Boat boat2 = new Boat();

    public River() {
    	 for (int i = 0; i < size; i++) {
    	        for (int j = 0; j < size; j++) {
    	            riverBoard[i][j] = empty;
    	        }
    	    }

    	    Random random = new Random();
    	    int numberOfEncounters = 0;
    	    while (numberOfEncounters < 30) {
    	        int row = random.nextInt(size-1);
    	        int col = random.nextInt(size); 
    	        if (riverBoard[row][col].equals(empty)) {
    	            int randNum = random.nextInt(2); // Randomly choose between current and trap
    	            if (randNum == 0) {
    	                riverBoard[row][col] = "[" + current + "]";
    	            } else {
    	                riverBoard[row][col] = "[" + trap + "]";
    	            }
    	            numberOfEncounters++;
    	        }
    	    }
    }

	    public String getObstacle(int row, int col) {
	        if (row >= 0 && row < size && col >= 0 && col < size) {
	            return riverBoard[row][col];
	        } else {
	            return ""; // Return an empty string for out-of-bounds positions
	        }
	    }

        public void printRiver(Player player1) {
            System.out.println();
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (i == boat1.getRow() && j == boat1.getCol() && i == boat2.getRow() && j == boat2.getCol()) {
                        System.out.print("[*" + player1.getName() + " & B*]");
                    } else if (i == boat1.getRow() && j == boat1.getCol()) {
                        System.out.print("[*" + player1.getName() + "*]");
                    } else if (i == boat2.getRow() && j == boat2.getCol()) {
                        System.out.print("[*B*]|");
                    } else {
                        System.out.print(riverBoard[i][j]+ "-");
                    }
                }
                System.out.println();
                System.out.println("----------------------------------------------");
            }
            
            System.out.println();
            System.out.printf("Position of %s: (%d)\n", player1.getName(), boat1.getRow()*size + boat1.getCol()+1);
            System.out.printf("Position of Bot: (%d)\n", boat2.getRow()*size + boat2.getCol()+1);
            System.out.println();
        }

        public int getSize() {
            return size;
        }

        public Boat getBoat(String name) {
            return name.equals(boat1.getName()) ? boat1 : boat2;
        }
    }
	
