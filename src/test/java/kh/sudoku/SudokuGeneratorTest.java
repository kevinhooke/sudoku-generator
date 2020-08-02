package kh.sudoku;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * 
 * TODO: instead of picking candidates with lowest counts first, change to randomized
 * 
 * @author kevinhooke
 *
 */
public class SudokuGeneratorTest {
    
    private String easySolution = 
            "+-------+-------+-------+\n" + 
            "| 3 4 9 | 8 1 2 | 6 7 5 | \n" + 
            "| 5 1 7 | 4 9 6 | 2 3 8 | \n" + 
            "| 2 6 8 | 3 5 7 | 1 9 4 | \n" + 
            "+-------+-------+-------+\n" + 
            "| 1 8 5 | 7 2 3 | 9 4 6 | \n" + 
            "| 4 9 3 | 6 8 1 | 5 2 7 | \n" + 
            "| 7 2 6 | 9 4 5 | 8 1 3 | \n" + 
            "+-------+-------+-------+\n" + 
            "| 9 7 2 | 5 3 8 | 4 6 1 | \n" + 
            "| 6 5 1 | 2 7 4 | 3 8 9 | \n" + 
            "| 8 3 4 | 1 6 9 | 7 5 2 | \n" + 
            "+-------+-------+-------+\n";
    
    private static final List<String> easySolutionShorthand = new ArrayList<>();
    
    @BeforeClass
    public static void init(){
        easySolutionShorthand.add("349812675");
        easySolutionShorthand.add("517496238");
        easySolutionShorthand.add("268357194");
        easySolutionShorthand.add("185723946");
        easySolutionShorthand.add("493681527");
        easySolutionShorthand.add("726945813");
        easySolutionShorthand.add("972538461");
        easySolutionShorthand.add("651274389");
        easySolutionShorthand.add("834169752");
    }
    
    @Test
    public void testShuffledStartingList() {
        String startingSeed = "123456789";
        SudokuGenerator generator = new SudokuGenerator();
        String generatedSeed1 = generator.generateRandomSeedString();
        System.out.println("generated seed: " + generatedSeed1);
        assertFalse(generatedSeed1.equals(startingSeed));
    }
    
    @Test
    public void testSubsequentShufflesAreDifferent() {
        SudokuGenerator generator = new SudokuGenerator();
        String generatedSeed1 = generator.generateRandomSeedString();
        System.out.println("generated seed1: " + generatedSeed1);
        String generatedSeed2 = generator.generateRandomSeedString();
        System.out.println("generated seed2: " + generatedSeed2); 
        String generatedSeed3 = generator.generateRandomSeedString();
        System.out.println("generated seed3: " + generatedSeed3);
        assertFalse(generatedSeed1.equals(generatedSeed2));
        assertFalse(generatedSeed2.equals(generatedSeed3));
        assertFalse(generatedSeed3.equals(generatedSeed1));
    }
    
    /**
     * Tests generating a completed grid starting with an empty grid.
     * Will result in multiple solutions, this approach is limited by the 
     * max solutions parameter in the solver.
     * 
     */
    @Test
    public void testGenerate1() {
        SudokuSolverWithDLX solver = new SudokuSolverWithDLX();
        SudokuGenerator generator = new SudokuGenerator();
        String generatedSeed1 = generator.generateRandomSeedString();
        
        List<String> givenSolutionsShorthand = new ArrayList<>();
        givenSolutionsShorthand.add(generatedSeed1);
        givenSolutionsShorthand.add(".........");
        givenSolutionsShorthand.add(".........");
        givenSolutionsShorthand.add(".........");
        givenSolutionsShorthand.add(".........");
        givenSolutionsShorthand.add(".........");
        givenSolutionsShorthand.add(".........");
        givenSolutionsShorthand.add(".........");
        givenSolutionsShorthand.add(".........");
        PuzzleResults results = solver.run(givenSolutionsShorthand, 10);
        
        List<List<String>> resultShorthand = results.getResults();
        for(List<String> shorthand : resultShorthand) {
            System.out.println(shorthand);
        }
    }

}
