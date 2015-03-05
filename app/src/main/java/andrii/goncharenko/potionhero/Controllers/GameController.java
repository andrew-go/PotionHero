package andrii.goncharenko.potionhero.Controllers;

import andrii.goncharenko.potionhero.Threads.DrawThread;
import andrii.goncharenko.potionhero.Threads.StatusThread;
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

    public StatusThread statusThread;

    public void initThreads() {
        initDrawThread();
        initStatusThread();
    }

    private void initDrawThread() {
        drawThread = new DrawThread(view, 150);
        drawThread.start();
    }

    private void initStatusThread() {
        statusThread = new StatusThread(150);
        statusThread.start();
    }

}
