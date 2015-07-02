package andrii.goncharenko.potionhero.Activities;

import android.media.MediaPlayer;
import android.os.Bundle;

import andrii.goncharenko.potionhero.Managers.GameManager;
import andrii.goncharenko.potionhero.R;
import andrii.goncharenko.potionhero.Views.GameView;

public class GameActivity extends BaseActivity {

    /**Virtual methods**/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initComponents();
    }

    @Override
    public void initComponents() {
        super.initComponents();
        GameManager.Instance().view = (GameView) findViewById(R.id.gameView);
        GameManager.Instance().initTouchListener();
        GameManager.Instance().initThreads();
        GameManager.Instance().newGame();
    }

    @Override
    public void initMusic() {
        backgroundMusic = MediaPlayer.create(this, R.raw.game_music);
    }

}
