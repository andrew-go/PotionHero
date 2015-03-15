package andrii.goncharenko.potionhero.Threads;

import andrii.goncharenko.potionhero.Controllers.CombinationController;
import andrii.goncharenko.potionhero.Controllers.GameController;

/**
 * Created by Andrey on 02.03.2015.
 */
public class GameStatusThread extends Thread {

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
    int sleepDuration = 100;

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
                switch (GameController.Instance().gameStatus) {
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
                        sleep(1000);
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

    private void createCombination() {
        CombinationController.Instance().generateNewCombination();
        GameController.Instance().gameStatus = eGameStatus.combinationCreated;
    }

    private void onCombined() {
        CombinationController.Instance().generateNewCombination();
        GameController.Instance().gameStatus = eGameStatus.combinationCreated;
    }

}
