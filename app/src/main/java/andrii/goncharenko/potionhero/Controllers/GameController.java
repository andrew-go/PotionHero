package andrii.goncharenko.potionhero.Controllers;

import android.view.MotionEvent;
import android.view.View;

import andrii.goncharenko.potionhero.Threads.DrawThread;
import andrii.goncharenko.potionhero.Threads.GameStatusThread;
import andrii.goncharenko.potionhero.Views.GameView;

/**
 * Created by Andrey on 02.03.2015.
 */
public class GameController {

    private static GameController instance;

    public static GameController Instance() {
        return instance == null ? instance = new GameController() : instance;
    }

    public GameView view;

    public DrawThread drawThread;

    public GameStatusThread statusThread;

    public GameStatusThread.eGameStatus gameStatus = GameStatusThread.eGameStatus.noAction;

    public GameController() {

    }

    public void initThreads() {
        initDrawThread();
        initGameStatusThread();
    }

    private void initDrawThread() {
        drawThread = new DrawThread(view, 150);
        drawThread.start();
    }

    private void initGameStatusThread() {
        statusThread = new GameStatusThread(150);
        statusThread.start();
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

    private boolean actionDown(MotionEvent event) {
        switch (gameStatus) {
            case combinationCreated:
                if (!BoardController.Instance().isTouchOnBoard((int) event.getX(), (int) event.getY()))
                    return false;
                BoardController.Instance().currentIngredient = BoardController.Instance().getIngredient((int)event.getX(), (int)event.getY());
                if (BoardController.Instance().currentIngredient != CombinationController.Instance().getCombination()[0])
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
                if (BoardController.Instance().currentIngredient != BoardController.Instance().getIngredient((int)event.getX(), (int)event.getY())) {
                    BoardController.Instance().currentIngredient = BoardController.Instance().getIngredient((int)event.getX(), (int)event.getY());
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

    public void newGame() {
        BoardController.Instance().refillBoard();
        gameStatus = GameStatusThread.eGameStatus.creatingCombination;
    }

}
