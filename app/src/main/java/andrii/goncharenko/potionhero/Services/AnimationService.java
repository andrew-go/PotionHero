package andrii.goncharenko.potionhero.Services;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

import andrii.goncharenko.potionhero.R;

/**
 * Created by Andrey on 02.03.2015.
 */
public class AnimationService {

    /**Constants**/
    private final int START_FRAME_INDEX = 0;

    /**Members**/

    private Context context;

    private Drawable image;

    private List<Drawable> images = new ArrayList<>();

    private int currentFrame = START_FRAME_INDEX;

    private Point startPoint;

    /**Constructors**/

    public AnimationService(Context context, Point startPoint) {
        this.context = context;
        this.startPoint = startPoint;
        initImages();
    }


    /**Public methods**/

    public void draw(Canvas canvas) {
        if (currentFrame == images.size())
            currentFrame = START_FRAME_INDEX;
        image = images.get(currentFrame);
        image.setBounds(startPoint.x, startPoint.y, startPoint.x + image.getMinimumWidth(), startPoint.y + image.getMinimumHeight());
        image.draw(canvas);
        currentFrame++;
    }

    /**Private methods**/

    private void initImages() {
        TypedArray arr = context.getResources().obtainTypedArray(R.array.lights);
        for (int i = 0; i < arr.length(); i++)
            images.add(context.getResources().getDrawable(arr.getResourceId(i, -1)));
        arr.recycle();
    }

}
