package andrii.goncharenko.potionhero.Activities;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

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
        initeMenuView();
        MenuController.Instance().initDrawThread();
        MenuController.Instance().initMenuThread();
        backgroundMusic = MediaPlayer.create(this, R.raw.menu_music);
        btClickSound  = MediaPlayer.create(this, R.raw.menu_bt_click);
    }

    AnimationDrawable frameAnimation;

    public void initeMenuView() {
        MenuController.Instance().menuView = (MenuView) findViewById(R.id.menuView);
        MenuController.Instance().menuView.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
//				Intent intent = new Intent(getBaseContext(), GameActivity.class);
//				startActivity(intent);
//				btClickSound.start();
//				stopMusic();
//				MenuController.Instance().drawThread.stopThread();
//				MenuController.Instance().statusThread.stopThread();
                return true;
            }
        });
    }
}
