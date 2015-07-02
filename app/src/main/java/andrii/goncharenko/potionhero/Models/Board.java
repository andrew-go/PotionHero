package andrii.goncharenko.potionhero.Models;

/**
 * Created by Andrey on 02.03.2015.
 */
public class Board {

    /**Constants**/

    public static final int BOARD_SIZE = 7;

    /**Members**/

    private int[][] board = new int[BOARD_SIZE][BOARD_SIZE];

    /**Get/Set methods**/

    public int[][] getBoardArr() {
        return board;
    }

}
