package andrii.goncharenko.potionhero.Managers;

import android.view.MotionEvent;
import android.view.View;

import andrii.goncharenko.potionhero.Controllers.BoardController;
import andrii.goncharenko.potionhero.Controllers.CombinationController;
import andrii.goncharenko.potionhero.Threads.DrawThread;
import andrii.goncharenko.potionhero.Threads.GameStatusThread;
import andrii.goncharenko.potionhero.Views.GameView;

/**
 * Created by Andrey on 02.03.2015.
 */
public class GameManager {

    /**Members**/

    private static GameManager instance;
    public GameView view;
    public DrawThread drawThread;
    public GameStatusThread statusThread;
    public GameStatusThread.eGameStatus gameStatus = GameStatusThread.eGameStatus.noAction;

    /**Instances**/

    public static GameManager Instance() {
        return instance == null ? instance = new GameManager() : instance;
    }

    /**Constructors**/

    public GameManager() {
    }

    /**Public methods**/

    public void initThreads() {
        initDrawThread();
        initGameStatusThread();
    }

    public void initTouchListener() {
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        return actionDown(event);
                    case MotionEvent.ACTION_MOVE:
                        return actionMove(event);
                    case MotionEvent.ACTION_UP:
                        return actionUp(event);
                }
                return false;
            }
        });
    }

    public void newGame() {
        BoardController.Instance().refillBoard();
        gameStatus = GameStatusThread.eGameStatus.creatingCombination;
    }

    /**Private methods**/

    private void initDrawThread() {
        drawThread = new DrawThread(view, 150);
        drawThread.start();
    }

    private void initGameStatusThread() {
        statusThread = new GameStatusThread(150);
        statusThread.start();
    }

    private boolean actionDown(MotionEvent event) {
        switch (gameStatus) {
            case combinationCreated:
                if (!BoardController.Instance().isTouchOnBoard((int) event.getX(), (int) event.getY()))
                    return false;
                BoardController.Instance().setCurrentIngredient((int) event.getX(), (int) event.getY());
                if (BoardController.Instance().currentIngredient != CombinationController.Instance().getCombination().getFirstElement())
                    return false;
                CombinationController.Instance().clearCollectingCombination();
                CombinationController.Instance().addIngredientToCollectingCombination(BoardController.Instance().currentIngredient);
                gameStatus = GameStatusThread.eGameStatus.combining;
            break;
        }
        return true;
    }

    private boolean actionMove(MotionEvent event) {
        switch (gameStatus) {
            case combining:
                if (!BoardController.Instance().isTouchOnBoard((int) event.getX(), (int) event.getY()))
                    return false;
                if (BoardController.Instance().currentIngredient != BoardController.Instance().getIngredientFromBoard((int) event.getX(), (int) event.getY())) {
                    BoardController.Instance().currentIngredient = BoardController.Instance().getIngredientFromBoard((int) event.getX(), (int) event.getY());
                    return CombinationController.Instance().addIngredientToCollectingCombination(BoardController.Instance().currentIngredient);
                }
            break;
        }
        return true;
    }

    private boolean actionUp(MotionEvent event) {
        switch (gameStatus) {
            case combining:
                if (!CombinationController.Instance().checkCombination()) {
                    gameStatus = GameStatusThread.eGameStatus.combinationCreated;
                    return false;
                }
                else {
                    gameStatus = GameStatusThread.eGameStatus.combined;
                    return false;
                }
        }
        return false;
    }

}
