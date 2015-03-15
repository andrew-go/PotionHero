package andrii.goncharenko.potionhero.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import andrii.goncharenko.potionhero.Controllers.Animation;
import andrii.goncharenko.potionhero.R;

/**
 * Created by Andrey on 02.03.2015.
 */
public class MenuView extends BaseView {

    private Drawable backgroundImage;

    private Animation lightAnimation;

    public MenuView(Context context) {
        super(context);
    }

    public MenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MenuView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackGround(canvas);
        drawLights(canvas);
    }

    private void drawBackGround(Canvas canvas) {
        if (backgroundImage == null) {
            backgroundImage = context.getResources().getDrawable(R.drawable.menu_background);
            backgroundImage.setBounds(canvas.getClipBounds());
        }
        backgroundImage.draw(canvas);
    }

    private void drawLights(Canvas canvas) {
        if (lightAnimation == null) {
            lightAnimation = new Animation(context, new Point(300, 220));
        }
        lightAnimation.draw(canvas);
    }

}
