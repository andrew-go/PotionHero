package andrii.goncharenko.potionhero.Activities;

import android.media.MediaPlayer;
import android.os.Bundle;

import andrii.goncharenko.potionhero.Controllers.GameController;
import andrii.goncharenko.potionhero.R;
import andrii.goncharenko.potionhero.Views.GameView;

public class GameActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initComponents();
    }

    @Override
    public void initComponents() {
        GameController.Instance().view = (GameView) findViewById(R.id.gameView);
        GameController.Instance().initTouchListener();
        GameController.Instance().initThreads();
        GameController.Instance().newGame();
        backgroundMusic = MediaPlayer.create(this, R.raw.game_music);
    }

}
