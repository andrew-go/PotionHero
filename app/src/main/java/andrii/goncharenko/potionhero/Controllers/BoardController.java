package andrii.goncharenko.potionhero.Controllers;

/**
 * Created by Andrey on 06.03.2015.
 */
public class BoardController {

    private static BoardController instance;

    public static BoardController Instance() {
        return instance == null ? instance = new BoardController() : instance;
    }

    private int[][] board = new int[7][7];

    public int[][] getBoard() {
        return board;
    }

    public int getBoardIngredient(int rowIndex, int columnIndex) {
        return board[rowIndex][columnIndex];
    }

    public int currentIngredient = -1;

    public int gameBoardActiveMargin = 90;

    public void refillBoard() {
        for (int rowIndex = 0; rowIndex < board.length; rowIndex++)
            for (int columnIndex = 0; columnIndex < board[rowIndex].length; columnIndex++) {
                board[rowIndex][columnIndex] = IngredientsController.Instance().generateIngredient();
            }
    }

    public int getIngredient(int x, int y) {
        int columnIndex = (int)(((double)x - (gameBoardActiveMargin + GameController.Instance().view.getBoardImage().getBounds().left)) / (double)130);
        int rowIndex = (int)(((double)y - (gameBoardActiveMargin + GameController.Instance().view.getBoardImage().getBounds().top)) / (double)130);
        return board[rowIndex][columnIndex];
    }

    public boolean isTouchOnBoard(int x, int y) {
        if (x < GameController.Instance().view.getBoardImage().getBounds().left + gameBoardActiveMargin ||
                y < GameController.Instance().view.getBoardImage().getBounds().top + gameBoardActiveMargin ||
                x > GameController.Instance().view.getBoardImage().getBounds().right - gameBoardActiveMargin ||
                y > GameController.Instance().view.getBoardImage().getBounds().bottom - gameBoardActiveMargin)
            return false;
        return true;
    }

}
