package andrii.goncharenko.potionhero.Controllers;

import andrii.goncharenko.potionhero.Threads.DrawThread;
import andrii.goncharenko.potionhero.Threads.StatusThread;
import andrii.goncharenko.potionhero.Views.MenuView;

/**
 * Created by Andrey on 02.03.2015.
 */
public class MenuController {

    private static MenuController instance;

    public static MenuController Instance() {
        return instance == null ? instance = new MenuController() : instance;
    }

    public MenuView menuView;

    public DrawThread drawThread;

    public StatusThread statusThread;

    public void initDrawThread() {
        MenuController.Instance().drawThread = new DrawThread(menuView, 150);
        MenuController.Instance().drawThread.start();
    }

    public void initMenuThread() {
        MenuController.Instance().statusThread = new StatusThread(150);
        MenuController.Instance().statusThread.start();
    }

}
