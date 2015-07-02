package andrii.goncharenko.potionhero.Threads;

/**
 * Created by Andrey on 02.03.2015.
 */
public class StatusThread extends Thread {

    /**Constants**/
    private final int STANDARD_DELAY = 100;

    /**Members**/

    boolean run = true;
    int sleepDuration = STANDARD_DELAY;

    /**Constructors**/

    public StatusThread() {

    }

    public StatusThread(int sleepDuration) {
        this.sleepDuration = sleepDuration;
    }

    /** Public Methods **/

    public void stopThread() {
        run = false;
    }

    public void run() {
        while(run) {
            try {
                sleep(sleepDuration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
