package andrii.goncharenko.potionhero.Activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import andrii.goncharenko.potionhero.Managers.MenuManager;
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
    }

    @Override
    public void initComponents() {
        super.initComponents();
        MenuManager.Instance().view = (MenuView) findViewById(R.id.menu_view);
        MenuManager.Instance().initThreads();
    }

    @Override
    public void initSounds() {
        backgroundMusic = MediaPlayer.create(this, R.raw.menu_music);
        btClickSound  = MediaPlayer.create(this, R.raw.menu_bt_click);
    }

    /**Events**/

    public void btNewGameClicked(View view) {
        Intent intent = new Intent(getBaseContext(), GameActivity.class);
        startActivity(intent);
        playSound(btClickSound);
        MenuManager.Instance().destroy();
    }

}
