package andrii.goncharenko.potionhero.Controllers;

import java.util.Random;

/**
 * Created by Andrey on 06.03.2015.
 */
public class IngredientsController {

    private static IngredientsController instance;

    public static IngredientsController Instance() {
        return instance == null ? instance = new IngredientsController() : instance;
    }

    Random random = new Random();

    int ingredientsCount = 19;

    public int generateIngredient() {
        return (random.nextInt(ingredientsCount));
    }

    public int getRandomBoardIngredient() {
        return BoardController.Instance().getBoardIngredient(random.nextInt(7), random.nextInt(7));
    }

}
