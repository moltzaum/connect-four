
package ConnectFour;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

/** 
 * Serializable board to JSON.
 *
 * The board class manages the state of a grid. The grid is a double
 * array of integers, and an instance of this class may be serialized
 * to json. <br>
 *
 * Currently, the json returned is: <br>
 * <pre>
 *  {                               
 *      "grid": TODO -- fill this in
 *  }
 * </pre>
 */
public class Board {

    /**
     * I believe more fields should be exposed, if not all.
     * However, for flexibility all fields must explicitly be
     * exposed and I won't include the row, column, or connect
     * number because the starting naive implementations do not
     * use these parts -- making me unsure if these are a part
     * of the API. The row and column may need to be renamed to
     * height and width. I can use 'SerializedName', a Gson
     * annotation to do that.
     * */ 
    private static final Gson jsonConverter = new GsonBuilder().
        excludeFieldsWithoutExposeAnnotation().create();

    private int row;
    private int column;

    @Expose
    private int[][] grid;

    private int connectNumber;

    /** Constructor
     * */
    Board(int row, int column, int connectNumber) {
        this.row = row;
        this.column = column;
        this.connectNumber = connectNumber;
        grid = new int[column][row];
        loadBoardEmpty();
    }

    private void loadBoardEmpty() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                grid[j][i] = 0;
            }
        }
    }

    /* Check all rows for that specific column
     * parameter column - the column to check
     * returns the row number to populate or -1 if the column is full
     * */
    private int determineRow(int column) {

        for (int i = row-1; i >= 0; i--) {
            if (grid[column][i] == 0) {
                return i;
            }
        }
        return -1;
    }

    /** Add Player's move to Game Board
     * @param player should only be 1 or 0. No checks are performed.
     * @param column the column to drop the token into.
     * */
    public void addPlayerMove(int player, int column) {
        int add = determineRow(column);
        if (add != -1) {
            grid[column][add] = player;
        }
    }

    /** Useful for debugging
     * */
    public void printBoard() {

        System.out.print("            Player Board");
        System.out.print('\n');
        System.out.print("-------------------------------------");
        System.out.print('\n');
        for (int i = row; i > 0; i--) {
            for (int j = 0; j < column ; j++) {
                System.out.print(grid[(i - 1)][(j)]);
                System.out.print("    ");
            }
            System.out.println('\n');
        }
        System.out.print("-------------------------------------");
        System.out.print('\n');
        for (int j = 0; j < column; j++) {
            System.out.print(j + 1);
            System.out.print("    ");
        }
        System.out.print('\n');
        System.out.print('\n');
    }

    private boolean checkVerticalWin(int whichPlayer) {

        int fromColumn = (connectNumber - 1); // 3

        for (int i = fromColumn; i < column; i++) {

            for (int j = 0; j < row; j++) {

                int counter = 0;
                for (int k = 0; k < connectNumber; k++) {
                    if (grid[i-k][j] == whichPlayer) {
                        counter++;
                    }
                }
                if (counter == connectNumber) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkHorizontalWin(int whichPlayer) {
        int toRow = (row - connectNumber); // 4

        for (int i = 0; i < column; i++) {

            for (int j = 0; j <= toRow; j++) {

                int counter = 0;
                for (int k = 0; k < connectNumber; k++) {
                    if (grid[i][j + k] == whichPlayer) {
                        counter++;
                    }
                }
                if (counter == connectNumber) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDiagnolLeftWin(int whichPlayer) {

        for (int i = connectNumber - 1; i < column; i++) {
            for (int j = connectNumber - 1; j < row; j++) {

                int counter = 0;
                for (int k = 0; k < connectNumber; k++) {
                    if (grid[i - k][j - k] == whichPlayer) {
                        counter++;
                    }
                }
                if (counter == connectNumber) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDiagnolRightWin(int whichPlayer) {

        int toRow = (row - connectNumber);

        for (int i = connectNumber -1; i < column; i++) {

            for (int j = 0; j <= toRow; j++) {

                int counter = 0;
                for (int k = 0; k < connectNumber; k++) {
                    if (grid[i - k][j + k] == whichPlayer) {
                        counter++;
                    }
                }
                if (counter == connectNumber) {
                    return true;
                }
            }
        }
        return false;
    }

    public String toString() {
        return jsonConverter.toJson(this);
    }

    /** Determines if a player has won the game
     * @param player check to see if this player has won
     * @return whether or not the player has connected the minimum
     * number of their pieces.
     * */
    public boolean playerWon(int player) {
        return (
            checkVerticalWin(player) ||
            checkHorizontalWin(player) ||
            checkDiagnolLeftWin(player) ||
            checkDiagnolRightWin(player)
        );
    } 
    
}
