package andrii.goncharenko.potionhero.Controllers;

/**
 * Created by Andrey on 06.03.2015.
 */
public class CombinationController {

    private static CombinationController instance;

    public static CombinationController Instance() {
        return instance == null ? instance = new CombinationController() : instance;
    }

    private int combinationLength = 3; /**TODO while no cauldron connection**/

    private int[] combination;

    public int[] getCombination() {
        return combination;
    }

    private int[] collectingCombination;

    public int[] getCollectingCombination() {
        return collectingCombination;
    }

    public boolean addIngredientToCollectingCombination(int ingredient) {
        for (int ingredientIndex = 0; ingredientIndex < combinationLength; ingredientIndex++) {
            if (collectingCombination[ingredientIndex] != -1)
                continue;
            else if (combination[ingredientIndex] != ingredient)
                return false;
            collectingCombination[ingredientIndex] = ingredient;
            return true;
        }
        return false;
    }

    public void clearCollectingCombination() {
        collectingCombination = new int[combinationLength];
        for (int ingredientIndex = 0; ingredientIndex < combinationLength; ingredientIndex++)
            collectingCombination[ingredientIndex] = -1;
    }

    public void generateNewCombination() {
        combination = new int[combinationLength];
        for (int ingredientIndex = 0; ingredientIndex < combinationLength; ingredientIndex++)
            combination[ingredientIndex] = IngredientsController.Instance().getRandomBoardIngredient();
    }

    public boolean checkCombination() {
        for (int ingredientIndex = 0; ingredientIndex < combinationLength; ingredientIndex++)
            if (combination[ingredientIndex] != collectingCombination[ingredientIndex])
                return false;
        return true;
    }



}
