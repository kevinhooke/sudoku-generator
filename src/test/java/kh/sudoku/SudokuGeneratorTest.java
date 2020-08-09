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
        
        PuzzleResults results = generator.generate(1);
        
        List<List<String>> resultShorthand = results.getResults();
        for(List<String> shorthand : resultShorthand) {
            System.out.println(shorthand);
        }
    }

}
