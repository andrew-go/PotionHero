package andrii.goncharenko.potionhero.Controllers;

import java.util.Random;

import andrii.goncharenko.potionhero.Managers.GameManager;
import andrii.goncharenko.potionhero.Models.Board;
import andrii.goncharenko.potionhero.Views.GameView;

/**
 * Created by Andrey on 02.03.2015.
 */
public class BoardController {

    /**Constants**/
    public static final int INGREDIENTS_COUNT = 19;
    private final int EMPTY = -1;

    /**Members**/

    private static BoardController instance;

    Board board = new Board();
    Random random = new Random();

    public int currentIngredient = EMPTY;

    /**Instances**/

    public static BoardController Instance() {
        return instance == null ? instance = new BoardController() : instance;
    }

    /**Get/Set methods**/

    public Board getBoard() {
        return board;
    }

    /**Public methods**/

    public void refillBoard() {
        for (int rowIndex = 0; rowIndex < board.getBoardArr().length; rowIndex++)
            for (int columnIndex = 0; columnIndex < board.getBoardArr()[rowIndex].length; columnIndex++) {
                board.getBoardArr()[rowIndex][columnIndex] = generateIngredientIndex();
            }
    }

    public int getIngredientFromBoard(int x, int y) {
        int columnIndex = (int)(((double)x - (GameView.GAME_BOARD_MARGIN
                + GameManager.Instance().view.getBoardImage().getBounds().left))
                / (double) GameView.INGREDIENT_ACTION_SIZE);
        int rowIndex = (int)(((double)y - (GameView.GAME_BOARD_MARGIN
                + GameManager.Instance().view.getBoardImage().getBounds().top))
                / (double) GameView.INGREDIENT_ACTION_SIZE);
        return board.getBoardArr()[rowIndex][columnIndex];
    }

    public boolean isTouchOnBoard(int x, int y) {
        if (x < GameManager.Instance().view.getBoardImage().getBounds().left + GameView.GAME_BOARD_MARGIN ||
                y < GameManager.Instance().view.getBoardImage().getBounds().top + GameView.GAME_BOARD_MARGIN ||
                x > GameManager.Instance().view.getBoardImage().getBounds().right - GameView.GAME_BOARD_MARGIN ||
                y > GameManager.Instance().view.getBoardImage().getBounds().bottom - GameView.GAME_BOARD_MARGIN)
            return false;
        return true;
    }

    public void setCurrentIngredient(int x, int y) {
        currentIngredient = getIngredientFromBoard(x, y);
    }

    public int generateIngredientIndex() {
        return (random.nextInt(INGREDIENTS_COUNT));
    }

    public int getRandomBoardIngredient() {
        return board.getBoardArr()[random.nextInt(Board.BOARD_SIZE)][random.nextInt(Board.BOARD_SIZE)];
    }

}
