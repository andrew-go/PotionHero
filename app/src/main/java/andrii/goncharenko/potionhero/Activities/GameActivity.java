package andrii.goncharenko.potionhero.Activities;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;

import andrii.goncharenko.potionhero.Controllers.GameController;
import andrii.goncharenko.potionhero.Controllers.MenuController;
import andrii.goncharenko.potionhero.R;
import andrii.goncharenko.potionhero.Views.MenuView;

public class GameActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    @Override
    public void initComponents() {
        initMenuView();
        GameController.Instance().initThreads();
        backgroundMusic = MediaPlayer.create(this, R.raw.game_music);
    }

    AnimationDrawable frameAnimation;

    public void initMenuView() {
        MenuController.Instance().view = (MenuView) findViewById(R.id.menuView);
    }

}
