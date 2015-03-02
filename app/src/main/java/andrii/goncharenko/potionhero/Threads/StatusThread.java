package andrii.goncharenko.potionhero.Threads;

/**
 * Created by Andrey on 02.03.2015.
 */
public class StatusThread extends Thread {

    /**Members**/

    boolean run = true;
    int sleepDuration = 100;

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
