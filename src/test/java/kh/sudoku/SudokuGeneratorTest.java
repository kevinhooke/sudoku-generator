package kh.sudoku;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import kh.sudokugrader.PuzzleDifficulty;
import kh.sudokugrader.SudokuGraderApp;

/**
 * 
 * TODO: instead of picking candidates with lowest counts first, change to randomized
 * 
 * @author kevinhooke
 *
 */
public class SudokuGeneratorTest {
    
    @Test(expected = InvalidCandiatesToRemoveException.class)
    public void testInvalidArgument0() {
        SudokuGenerator generator = new SudokuGenerator();
        generator.generate(0);
    }

    @Test(expected = InvalidCandiatesToRemoveException.class)
    public void testInvalidArgument65() {
        SudokuGenerator generator = new SudokuGenerator();
        generator.generate(65);
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
    
    @Test
    public void testremoveCandidateFromPosition_row0col0() {
        List<String> givenSolutionsShorthand = new ArrayList<>();
        givenSolutionsShorthand.add("123456789");
        givenSolutionsShorthand.add(".........");
        givenSolutionsShorthand.add(".........");
        givenSolutionsShorthand.add(".........");
        givenSolutionsShorthand.add(".........");
        givenSolutionsShorthand.add(".........");
        givenSolutionsShorthand.add(".........");
        givenSolutionsShorthand.add(".........");
        givenSolutionsShorthand.add(".........");
        
        SudokuGenerator generator = new SudokuGenerator();
        List<String> result = generator.removeCandidateFromPosition(0, 0, givenSolutionsShorthand);
        
        assertEquals(".23456789", result.get(0));
    }
    
    /**
     * Tests generating a puzzle with 1 candidate removed.
     * 
     */
    @Test
    public void testGenerate1() {
        SudokuGenerator generator = new SudokuGenerator();
        
        PuzzleResults results = generator.generate(1);
        
        List<List<String>> resultShorthand = results.getResults();
        for(List<String> shorthand : resultShorthand) {
            System.out.println(shorthand);
        }
        
        assertTrue(results.isValidPuzzle());
        
        //TODO assert number of values removed
        
    }

    /**
     * Tests generating a puzzle with 10 candidate removed.
     * 
     */
    @Test
    public void testGenerate10() {
        SudokuGenerator generator = new SudokuGenerator();
        
        PuzzleResults results = generator.generate(10);
        
        List<List<String>> resultShorthand = results.getResults();
        for(List<String> shorthand : resultShorthand) {
            System.out.println(shorthand);
        }
        
        assertTrue(results.isValidPuzzle());
    }

    /**
     * Tests generating a puzzle with 30 candidate removed.
     * 
     */
    @Test
    public void testGenerate30() {
        SudokuGenerator generator = new SudokuGenerator();
        
        PuzzleResults results = generator.generate(30);
        
        List<List<String>> resultShorthand = results.getResults();
        for(List<String> shorthand : resultShorthand) {
            System.out.println(shorthand);
        }
        
        assertTrue(results.isValidPuzzle());
    }

    /**
     * Tests generating a puzzle with 40 candidate removed.
     * 
     */
    @Test
    public void testGenerate40() {
        SudokuGenerator generator = new SudokuGenerator();
        
        PuzzleResults results = generator.generate(40);
        
        List<List<String>> resultShorthand = results.getResults();
        for(List<String> shorthand : resultShorthand) {
            System.out.println(shorthand);
        }
        
        assertTrue(results.isValidPuzzle());
    }

    /**
     * Tests generating a puzzle with 50 candidate removed.
     * 
     */
    @Test
    public void testGenerate50() {
        SudokuGenerator generator = new SudokuGenerator();
        
        PuzzleResults results = generator.generate(50);
        
        List<List<String>> resultShorthand = results.getResults();
        for(List<String> shorthand : resultShorthand) {
            System.out.println(shorthand);
        }
        
        assertTrue(results.isValidPuzzle());
    }
    
    /**
     * Tests generating a puzzle with 60 candidates removed.
     * 
     */
    @Test
    public void testGenerate60() {
        SudokuGenerator generator = new SudokuGenerator();
        
        PuzzleResults results = generator.generate(60);
        
        List<List<String>> resultShorthand = results.getResults();
        for(List<String> shorthand : resultShorthand) {
            System.out.println(shorthand);
        }
        
        assertTrue(results.isValidPuzzle());
    }
    
    /**
     * Tests generating a puzzle with 64 candidate removed.
     * 
     */
    @Test
    public void testGenerate64() {
        SudokuGenerator generator = new SudokuGenerator();
        
        PuzzleResults results = generator.generate(64);
        
        List<List<String>> generatedPuzzles = results.getResults();
        for(List<String> shorthand : generatedPuzzles) {
            System.out.println(shorthand);
        }
        
        assertTrue(results.isValidPuzzle());
        
        //grade the puzzle
        SudokuGraderApp grader = new SudokuGraderApp();
        
        //get generated puzzle 
        List<String> generatedPuzzle1 = generatedPuzzles.get(0);
        grader.setSudokuGridWithSolutionShorthand(generatedPuzzle1);
        grader.populateSolutionGridWithStartingPosition();
        
        PuzzleDifficulty difficulty = grader.gradePuzzle();
        
        //TODO asserts on the result
        assertNotNull(difficulty);
        
        //TODO further asserts
        int expectedNumberOfGivens = (9*9) - 64;
        assertEquals(expectedNumberOfGivens, difficulty.getInitialGivens());
    }
}
