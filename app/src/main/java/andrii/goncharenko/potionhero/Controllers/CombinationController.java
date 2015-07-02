package andrii.goncharenko.potionhero.Controllers;

import andrii.goncharenko.potionhero.Models.Combination;

/**
 * Created by Andrey on 02.03.2015.
 */
public class CombinationController {

    /**Constants**/

    private final int MAX_COMBINATION_LENGTH = 3;

    /**Members**/

    private static CombinationController instance;

    Combination combination = new Combination(MAX_COMBINATION_LENGTH);

    private int[] collectingCombination;

    /**Instances**/

    public static CombinationController Instance() {
        return instance == null ? instance = new CombinationController() : instance;
    }

    /**Get/Set methods**/

    public Combination getCombination() {
        return combination;
    }

    /**Public methods**/

    public boolean addIngredientToCollectingCombination(int ingredient) {
        for (int ingredientIndex = 0; ingredientIndex < combination.getCombinationArr().length; ingredientIndex++) {
            if (collectingCombination[ingredientIndex] != -1)
                continue;
            else if (combination.getCombinationArr()[ingredientIndex] != ingredient)
                return false;
            collectingCombination[ingredientIndex] = ingredient;
            return true;
        }
        return false;
    }

    public void clearCollectingCombination() {
        collectingCombination = new int[combination.getCombinationArr().length];
        for (int ingredientIndex = 0; ingredientIndex < combination.getCombinationArr().length; ingredientIndex++)
            collectingCombination[ingredientIndex] = -1;
    }

    public void generateNewCombination() {
        combination = new Combination(MAX_COMBINATION_LENGTH);
        for (int ingredientIndex = 0; ingredientIndex < combination.getCombinationArr().length; ingredientIndex++)
            combination.getCombinationArr()[ingredientIndex] = BoardController.Instance().getRandomBoardIngredient();
    }

    public boolean checkCombination() {
        for (int ingredientIndex = 0; ingredientIndex < combination.getCombinationArr().length; ingredientIndex++)
            if (combination.getCombinationArr()[ingredientIndex] != collectingCombination[ingredientIndex])
                return false;
        return true;
    }



}
