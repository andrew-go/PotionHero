package andrii.goncharenko.potionhero.Models;

/**
 * Created by Andrey on 02.03.2015.
 */
public class Combination {

    /**Members**/

    private int[] combinationArr;

    /**Get/Set methods**/

    public int[] getCombinationArr() {
        return combinationArr;
    }

    /**Constructors**/

    public Combination() {}

    public Combination(int combinationLength) {
        combinationArr = new int[combinationLength];
    }

    /**Public methods**/

    public int getFirstElement() {
        return combinationArr[0];
    }

}
