package andrii.goncharenko.potionhero.Activities;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import andrii.goncharenko.potionhero.Controllers.MenuController;
import andrii.goncharenko.potionhero.R;
import andrii.goncharenko.potionhero.Views.MenuView;


public class MenuActivity extends BaseActivity {

    /**Members**/

    MediaPlayer btClickSound;

    /**Virtual methods**/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initComponents();
        startMusic();
    }

    @Override
    public void initComponents() {
        initMenuView();
        MenuController.Instance().initThreads();
        backgroundMusic = MediaPlayer.create(this, R.raw.menu_music);
        btClickSound  = MediaPlayer.create(this, R.raw.menu_bt_click);
    }

    AnimationDrawable frameAnimation;

    public void initMenuView() {
        MenuController.Instance().view = (MenuView) findViewById(R.id.menuView);
    }

    public void btNewGameClick(View view) {
        Intent intent = new Intent(getBaseContext(), GameActivity.class);
        startActivity(intent);
        btClickSound.start();
        stopMusic();
        MenuController.Instance().drawThread.stopThread();
        MenuController.Instance().statusThread.stopThread();
    }

}
