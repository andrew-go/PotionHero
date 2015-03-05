package andrii.goncharenko.potionhero.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import andrii.goncharenko.potionhero.R;

/**
 * Created by Andrey on 02.03.2015.
 */
public class GameView extends BaseView {

    private Drawable backgroundImage;

    public GameView(Context context) {
        super(context);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackGround(canvas);
    }

    private void drawBackGround(Canvas canvas) {
        if (backgroundImage == null) {
            backgroundImage = context.getResources().getDrawable(R.drawable.game_background);
            backgroundImage.setBounds(canvas.getClipBounds());
        }
        backgroundImage.draw(canvas);
    }

}
