package andrii.goncharenko.potionhero.Threads;

import andrii.goncharenko.potionhero.Managers.GameManager;
import andrii.goncharenko.potionhero.Controllers.CombinationController;

/**
 * Created by Andrey on 02.03.2015.
 */
public class GameStatusThread extends Thread {

    /**Constants**/
    private final int STANDARD_DELAY = 100;
    private final int COMBINED_DELAY = 1000;

    /**Enums**/
    public enum eGameStatus
    {
        noAction,
        creatingCombination,
        combinationCreated,
        combining,
        afterCombining,
        combined
    };

    /**Members**/

    boolean run = true;
    int sleepDuration = STANDARD_DELAY;

    /**Constructors**/

    public GameStatusThread() {

    }

    public GameStatusThread(int sleepDuration) {
        this.sleepDuration = sleepDuration;
    }

    /** Public Methods **/

    public void stopThread() {
        run = false;
    }

    public void run() {
        while(run) {
            try {
                switch (GameManager.Instance().gameStatus) {
                    case noAction:
                        break;
                    case creatingCombination:
                        createCombination();
                        break;
                    case combinationCreated:
                        break;
                    case combining:
                        break;
                    case combined:
                        sleep(COMBINED_DELAY);
                        onCombined();
                        break;
                    case afterCombining:
                        break;
                }
                sleep(sleepDuration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**Private methods**/

    private void createCombination() {
        CombinationController.Instance().generateNewCombination();
        GameManager.Instance().gameStatus = eGameStatus.combinationCreated;
    }

    private void onCombined() {
        CombinationController.Instance().generateNewCombination();
        GameManager.Instance().gameStatus = eGameStatus.combinationCreated;
    }

}
