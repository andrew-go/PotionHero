package andrii.goncharenko.potionhero.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Andrey on 02.03.2015.
 */
public class BaseView  extends View {

    /**Members**/

    protected Context context;

    /**Constructors**/

    public BaseView(Context context) {
        super(context);
        this.context = context;
    }

    public BaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public BaseView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
    }

}
