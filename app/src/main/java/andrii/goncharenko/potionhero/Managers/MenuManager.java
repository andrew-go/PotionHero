package andrii.goncharenko.potionhero.Managers;

import andrii.goncharenko.potionhero.Threads.DrawThread;
import andrii.goncharenko.potionhero.Threads.StatusThread;
import andrii.goncharenko.potionhero.Views.MenuView;

/**
 * Created by Andrey on 02.03.2015.
 */
public class MenuManager {

    /**Members**/

    private static MenuManager instance;

    public MenuView view;

    public DrawThread drawThread;

    public StatusThread statusThread;

    /**Instances**/

    public static MenuManager Instance() {
        return instance == null ? instance = new MenuManager() : instance;
    }

    /**Public methods**/

    public void initThreads() {
        initDrawThread();
        initStatusThread();
    }

    public void stopThreads() {
        drawThread.stopThread();
        statusThread.stopThread();
    }

    public void destroy() {
        stopThreads();
        instance = null;
    }

    /**Private methods**/

    private void initDrawThread() {
        drawThread = new DrawThread(view);
        drawThread.start();
    }

    private void initStatusThread() {
        statusThread = new StatusThread();
        statusThread.start();
    }

}
