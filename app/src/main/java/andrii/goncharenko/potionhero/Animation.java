package andrii.goncharenko.potionhero;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey on 02.03.2015.
 */
public class Animation {

    private Context context;

    private Drawable image;

    private List<Drawable> images = new ArrayList<Drawable>();

    private int currentFrame = 0;

    private Point startPoint;

    public Animation(Context context, Point startPoint) {
        this.context = context;
        this.startPoint = startPoint;
        initImages();
    }

    private void initImages() {
        TypedArray arr = context.getResources().obtainTypedArray(R.array.lights);
        for (int i = 0; i < arr.length(); i++)
            images.add(context.getResources().getDrawable(arr.getResourceId(i, -1)));
        arr.recycle();
    }

    public void draw(Canvas canvas) {
        if (currentFrame == images.size())
            currentFrame = 0;
        image = images.get(currentFrame);
        image.setBounds(startPoint.x, startPoint.y, startPoint.x + image.getMinimumWidth(), startPoint.y + image.getMinimumHeight());
        image.draw(canvas);
        currentFrame++;
    }

}
