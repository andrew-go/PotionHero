package andrii.goncharenko.potionhero.Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

import andrii.goncharenko.potionhero.Controllers.BoardController;
import andrii.goncharenko.potionhero.Managers.GameManager;
import andrii.goncharenko.potionhero.Controllers.CombinationController;
import andrii.goncharenko.potionhero.R;
import andrii.goncharenko.potionhero.Services.DeviceSettingsService;

/**
 * Created by Andrey on 02.03.2015.
 */
public class GameView extends BaseView {

    /**Constants**/

    public static final int GAME_BOARD_MARGIN = 90;
    public static final int INGREDIENT_ACTION_SIZE = 130;
    public static final int MAGIC_CLOUD_TOP_MARGIN = 175;
    public static final int MANUSCRIPT_TOP_MARGIN = 275;

    /**Members**/

    private Drawable backgroundImage;
    private Drawable boardImage;
    private Drawable magicCloud;
    private Drawable manuscript;
    private Drawable ingredientImage;
    private List<Drawable> ingredients;

    /**Constructors**/

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

    /**Get/Set methods**/

    public Drawable getBoardImage() {
        return boardImage;
    }

    /**Virtual methods**/

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (GameManager.Instance().gameStatus) {
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

    /**Private methods**/

    private void drawBackGround(Canvas canvas) {
        backgroundImage.draw(canvas);
    }

    private void drawBoard(Canvas canvas) {
        boardImage.draw(canvas);
    }

    private void drawIngredients(Canvas canvas) {
        for (int rowIndex = 0; rowIndex < BoardController.Instance().getBoard().getBoardArr().length; rowIndex++)
            for (int columnIndex = 0; columnIndex < BoardController.Instance().getBoard().getBoardArr()[rowIndex].length; columnIndex++) {
                ingredientImage = ingredients.get(BoardController.Instance().getBoard().getBoardArr()[rowIndex][columnIndex]);
                ingredientImage.setBounds(
                        boardImage.getBounds().left + GAME_BOARD_MARGIN + columnIndex * INGREDIENT_ACTION_SIZE,
                        boardImage.getBounds().top + GAME_BOARD_MARGIN + rowIndex * INGREDIENT_ACTION_SIZE,
                        (boardImage.getBounds().left + GAME_BOARD_MARGIN + columnIndex * INGREDIENT_ACTION_SIZE) + ingredientImage.getMinimumWidth(),
                        (boardImage.getBounds().top + GAME_BOARD_MARGIN + rowIndex * INGREDIENT_ACTION_SIZE) + ingredientImage.getMinimumHeight());
                ingredientImage.draw(canvas);
            }
    }

    private void drawCombination(Canvas canvas) {
        for (int index = 0; index < CombinationController.Instance().getCombination().getCombinationArr().length; index++) {
            ingredientImage = ingredients.get(CombinationController.Instance().getCombination().getCombinationArr()[index]);
            ingredientImage.setBounds(
                    ((DeviceSettingsService.width / 2) - (CombinationController.Instance().getCombination().getCombinationArr().length
                            * INGREDIENT_ACTION_SIZE / 2)) + index * INGREDIENT_ACTION_SIZE,
                            100 + GAME_BOARD_MARGIN + INGREDIENT_ACTION_SIZE,
                    ((DeviceSettingsService.width / 2) - (CombinationController.Instance().getCombination().getCombinationArr().length
                            * INGREDIENT_ACTION_SIZE / 2)) + index * INGREDIENT_ACTION_SIZE + ingredientImage.getMinimumWidth(),
                            100 + GAME_BOARD_MARGIN + INGREDIENT_ACTION_SIZE + ingredientImage.getMinimumHeight());
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
            backgroundImage.setBounds(0, 0, DeviceSettingsService.width, DeviceSettingsService.height);
        }
        if (boardImage == null) {
            boardImage = context.getResources().getDrawable(R.drawable.game_board_1);
            boardImage.setBounds(
                    0,
                    DeviceSettingsService.height -  boardImage.getMinimumHeight(),
                    boardImage.getMinimumWidth(),
                    DeviceSettingsService.height);
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
                    ((DeviceSettingsService.width / 2) - (magicCloud.getMinimumWidth() / 2)),
                    MAGIC_CLOUD_TOP_MARGIN,
                    ((DeviceSettingsService.width / 2) - (magicCloud.getMinimumWidth() / 2)) + magicCloud.getMinimumWidth(),
                    MAGIC_CLOUD_TOP_MARGIN + magicCloud.getMinimumHeight());
        }
        if (manuscript == null) {
            manuscript = context.getResources().getDrawable(R.drawable.manuscript2);
            manuscript.setBounds(
                    ((DeviceSettingsService.width / 2) - (manuscript.getMinimumWidth() / 2)),
                    MANUSCRIPT_TOP_MARGIN,
                    ((DeviceSettingsService.width / 2) - (manuscript.getMinimumWidth() / 2)) + manuscript.getMinimumWidth(),
                    MANUSCRIPT_TOP_MARGIN + manuscript.getMinimumHeight());
        }

    }

}
