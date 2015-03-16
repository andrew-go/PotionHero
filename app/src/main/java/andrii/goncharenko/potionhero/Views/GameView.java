package andrii.goncharenko.potionhero.Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

import andrii.goncharenko.potionhero.Controllers.BoardController;
import andrii.goncharenko.potionhero.Controllers.CombinationController;
import andrii.goncharenko.potionhero.Controllers.GameController;
import andrii.goncharenko.potionhero.R;
import andrii.goncharenko.potionhero.Settings.DeviceSettings;

/**
 * Created by Andrey on 02.03.2015.
 */
public class GameView extends BaseView {

    private Drawable backgroundImage;
    private Drawable boardImage;
    private Drawable magicCloud;
    private Drawable manuscript;

    public Drawable getBoardImage() {
        return boardImage;
    }

    private List<Drawable> ingredients;
    private Drawable ingredientImage;

    public Drawable getIngredientImage() {
        return ingredientImage;
    }

    public GameView(Context context) {
        super(context);
        initImages();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initImages();
    }

    public GameView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initImages();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (GameController.Instance().gameStatus) {
            case noAction:
                drawBackGround(canvas);
                drawBoard(canvas);
                drawIngredients(canvas);
                break;
            case creatingCombination:
                drawBackGround(canvas);
                drawBoard(canvas);
                drawIngredients(canvas);
                break;
            case combinationCreated:
                drawBackGround(canvas);
                drawBoard(canvas);
                drawIngredients(canvas);
                drawManuscript(canvas);
                drawCombination(canvas);
                break;
            case combining:
                drawBackGround(canvas);
                drawBoard(canvas);
                drawIngredients(canvas);
                drawManuscript(canvas);
                drawCombination(canvas);
                break;
            case combined:
                drawBackGround(canvas);
                drawBoard(canvas);
                drawIngredients(canvas);
                drawMagicCloud(canvas);
                break;
            case afterCombining:
                break;
        }
    }

    private void drawBackGround(Canvas canvas) {
        backgroundImage.draw(canvas);
    }

    private void drawBoard(Canvas canvas) {
        boardImage.draw(canvas);
    }

    private void drawIngredients(Canvas canvas) {
        for (int rowIndex = 0; rowIndex < BoardController.Instance().getBoard().length; rowIndex++)
            for (int columnIndex = 0; columnIndex < BoardController.Instance().getBoard()[rowIndex].length; columnIndex++) {
                ingredientImage = ingredients.get(BoardController.Instance().getBoard()[rowIndex][columnIndex]);
                ingredientImage.setBounds(
                        boardImage.getBounds().left + BoardController.Instance().gameBoardActiveMargin + columnIndex * 130,
                        boardImage.getBounds().top + BoardController.Instance().gameBoardActiveMargin + rowIndex * 130,
                        (boardImage.getBounds().left + BoardController.Instance().gameBoardActiveMargin + columnIndex * 130) + ingredientImage.getMinimumWidth(),
                        (boardImage.getBounds().top + BoardController.Instance().gameBoardActiveMargin + rowIndex * 130) + ingredientImage.getMinimumHeight());
                ingredientImage.draw(canvas);
            }
    }

    private void drawCombination(Canvas canvas) {
        for (int index = 0; index < CombinationController.Instance().getCombination().length; index++) {
            ingredientImage = ingredients.get(CombinationController.Instance().getCombination()[index]);
            ingredientImage.setBounds(
                    ((DeviceSettings.width / 2) - (CombinationController.Instance().getCombination().length * 130 / 2)) + index * 130,
                    100 + BoardController.Instance().gameBoardActiveMargin + 1 * 130,
                    ((DeviceSettings.width / 2) - (CombinationController.Instance().getCombination().length * 130 / 2)) + index * 130 + ingredientImage.getMinimumWidth(),
                    100 + BoardController.Instance().gameBoardActiveMargin + 1 * 130 + ingredientImage.getMinimumHeight());
            ingredientImage.draw(canvas);
        }
    }

    private void drawMagicCloud(Canvas canvas) {
        magicCloud.draw(canvas);
    }

    private void drawManuscript(Canvas canvas) {
        manuscript.draw(canvas);
    }

    private void initImages() {
        if (backgroundImage == null) {
            backgroundImage = context.getResources().getDrawable(R.drawable.game_background);
            backgroundImage.setBounds(0, 0, DeviceSettings.width, DeviceSettings.height);
        }
        if (boardImage == null) {
            boardImage = context.getResources().getDrawable(R.drawable.game_board_1);
            boardImage.setBounds(
                    0,
                    DeviceSettings.height -  boardImage.getMinimumHeight(),
                    boardImage.getMinimumWidth(),
                    DeviceSettings.height);
        }
        if (ingredients == null) {
            ingredients = new ArrayList<>();
            TypedArray arr = context.getResources().obtainTypedArray(R.array.ingredients);
            for (int i = 0; i < arr.length(); i++)
                ingredients.add(context.getResources().getDrawable(arr.getResourceId(i, -1)));
            arr.recycle();
        }
        if (magicCloud == null) {
            magicCloud = context.getResources().getDrawable(R.drawable.magic_cloud);
            magicCloud.setBounds(
                    ((DeviceSettings.width / 2) - (magicCloud.getMinimumWidth() / 2)),
                    175,
                    ((DeviceSettings.width / 2) - (magicCloud.getMinimumWidth() / 2)) + magicCloud.getMinimumWidth(),
                    175 + magicCloud.getMinimumHeight());
        }
        if (manuscript == null) {
            manuscript = context.getResources().getDrawable(R.drawable.manuscript2);
            manuscript.setBounds(
                    ((DeviceSettings.width / 2) - (manuscript.getMinimumWidth() / 2)),
                    275,
                    ((DeviceSettings.width / 2) - (manuscript.getMinimumWidth() / 2)) + manuscript.getMinimumWidth(),
                    275 + manuscript.getMinimumHeight());
        }

    }

}
