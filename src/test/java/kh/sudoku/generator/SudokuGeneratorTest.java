package kh.sudoku.generator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import kh.sudoku.PuzzleResults;
import kh.sudokugrader.InvalidPuzzleException;
import kh.sudokugrader.PuzzleDifficulty;
import kh.sudokugrader.SudokuGraderApp;

/**
 * 
 * TODO: instead of picking candidates with lowest counts first, change to
 * randomized
 * 
 * @author kevinhooke
 *
 */
public class SudokuGeneratorTest {

    @Test(expected = InvalidCandiatesToRemoveException.class)
    public void testInvalidArgument0() {
        SudokuGenerator generator = new SudokuGenerator();
        generator.generate(0, 1);
    }

    @Test(expected = InvalidCandiatesToRemoveException.class)
    public void testInvalidArgument65() {
        SudokuGenerator generator = new SudokuGenerator();
        generator.generate(65, 1);
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

        List<PuzzleResults> results = generator.generate(1, 1);

        assertEquals(1, results.size());
        
        List<List<String>> generatedPuzzles = results.get(0).getResults();
        for (List<String> shorthand : generatedPuzzles) {
            System.out.println(shorthand);
        }

        // grade the puzzle
        SudokuGraderApp grader = new SudokuGraderApp();

        // get generated puzzle
        List<String> generatedPuzzle1 = generatedPuzzles.get(0);
        grader.setSudokuGridWithSolutionShorthand(generatedPuzzle1);
        grader.populateSolutionGridWithStartingPosition();

        PuzzleDifficulty difficulty = grader.gradePuzzle();

        // asserts on the result
        assertNotNull(difficulty);

        // further asserts
        int expectedNumberOfGivens = (9 * 9) - 1;
        assertEquals(expectedNumberOfGivens, difficulty.getInitialGivens());

        assertTrue(results.get(0).isValidPuzzle());
    }

    /**
     * Tests generating a puzzle with 10 candidate removed.
     * 
     */
    @Test
    public void testGenerate10() {
        SudokuGenerator generator = new SudokuGenerator();

        List<PuzzleResults> results = generator.generate(10, 1);

        List<List<String>> generatedPuzzles = results.get(0).getResults();
        for (List<String> shorthand : generatedPuzzles) {
            System.out.println(shorthand);
        }

        // grade the puzzle
        SudokuGraderApp grader = new SudokuGraderApp();

        // get generated puzzle
        List<String> generatedPuzzle1 = generatedPuzzles.get(0);
        grader.setSudokuGridWithSolutionShorthand(generatedPuzzle1);
        grader.populateSolutionGridWithStartingPosition();

        PuzzleDifficulty difficulty = grader.gradePuzzle();

        // asserts on the result
        assertNotNull(difficulty);

        // further asserts
        int expectedNumberOfGivens = (9 * 9) - 10;
        assertEquals(expectedNumberOfGivens, difficulty.getInitialGivens());

        assertTrue(results.get(0).isValidPuzzle());
    }

    /**
     * Tests generating a puzzle with 30 candidate removed.
     * 
     */
    @Test
    public void testGenerate30() {
        SudokuGenerator generator = new SudokuGenerator();

        List<PuzzleResults> results = generator.generate(30, 1);

        List<List<String>> generatedPuzzles = results.get(0).getResults();
        for (List<String> shorthand : generatedPuzzles) {
            System.out.println(shorthand);
        }

        // grade the puzzle
        SudokuGraderApp grader = new SudokuGraderApp();

        // get generated puzzle
        List<String> generatedPuzzle1 = generatedPuzzles.get(0);
        grader.setSudokuGridWithSolutionShorthand(generatedPuzzle1);
        grader.populateSolutionGridWithStartingPosition();

        PuzzleDifficulty difficulty = grader.gradePuzzle();

        // asserts on the result
        assertNotNull(difficulty);

        // further asserts
        int expectedNumberOfGivens = (9 * 9) - 30;
        assertEquals(expectedNumberOfGivens, difficulty.getInitialGivens());

        assertTrue(results.get(0).isValidPuzzle());
    }

    /**
     * Tests generating a puzzle with 40 candidate removed.
     * 
     */
    @Test
    public void testGenerate40() {
        SudokuGenerator generator = new SudokuGenerator();

        List<PuzzleResults> results = generator.generate(40, 1);

        List<List<String>> generatedPuzzles = results.get(0).getResults();
        for (List<String> shorthand : generatedPuzzles) {
            System.out.println(shorthand);
        }

        // grade the puzzle
        SudokuGraderApp grader = new SudokuGraderApp();

        // get generated puzzle
        List<String> generatedPuzzle1 = generatedPuzzles.get(0);
        grader.setSudokuGridWithSolutionShorthand(generatedPuzzle1);
        grader.populateSolutionGridWithStartingPosition();

        PuzzleDifficulty difficulty = grader.gradePuzzle();

        // asserts on the result
        assertNotNull(difficulty);

        // further asserts
        int expectedNumberOfGivens = (9 * 9) - 40;
        assertEquals(expectedNumberOfGivens, difficulty.getInitialGivens());

        assertTrue(results.get(0).isValidPuzzle());
    }

    /**
     * Tests generating a puzzle with 50 candidate removed.
     * 
     */
    @Test
    public void testGenerate50() {
        SudokuGenerator generator = new SudokuGenerator();

        List<PuzzleResults> results = generator.generate(50, 1);

        List<List<String>> generatedPuzzles = results.get(0).getResults();
        for (List<String> shorthand : generatedPuzzles) {
            System.out.println(shorthand);
        }

        // grade the puzzle
        SudokuGraderApp grader = new SudokuGraderApp();

        // get generated puzzle
        List<String> generatedPuzzle1 = generatedPuzzles.get(0);
        grader.setSudokuGridWithSolutionShorthand(generatedPuzzle1);
        grader.populateSolutionGridWithStartingPosition();

        PuzzleDifficulty difficulty = grader.gradePuzzle();

        // asserts on the result
        assertNotNull(difficulty);

        // further asserts
        int expectedNumberOfGivens = (9 * 9) - 50;
        assertEquals(expectedNumberOfGivens, difficulty.getInitialGivens());

        assertTrue(results.get(0).isValidPuzzle());
    }

    /**
     * Tests generating a puzzle with 60 candidates removed.
     * 
     */
    @Test
    public void testGenerate60() {
        SudokuGenerator generator = new SudokuGenerator();

        List<PuzzleResults> results = generator.generate(60, 1);

        List<List<String>> generatedPuzzles = results.get(0).getResults();
        for (List<String> shorthand : generatedPuzzles) {
            System.out.println(shorthand);
        }

        // grade the puzzle
        SudokuGraderApp grader = new SudokuGraderApp();

        // get generated puzzle
        List<String> generatedPuzzle1 = generatedPuzzles.get(0);
        grader.setSudokuGridWithSolutionShorthand(generatedPuzzle1);
        grader.populateSolutionGridWithStartingPosition();

        PuzzleDifficulty difficulty = grader.gradePuzzle();

        // asserts on the result
        assertNotNull(difficulty);

        assertTrue(results.get(0).isValidPuzzle());
        
        // further asserts
        int expectedNumberOfGivens = (9 * 9) - 60;
        assertEquals(expectedNumberOfGivens, difficulty.getInitialGivens());
    }

    /**
     * 40 removed gives about 50/50 valid vs invalid
     */
    //TODO bug majority of 50 to 60 removed are invalid puzzles
    //TODO approach for removing is obviously nto resulting in enough valid puzzles
    @Test
    public void testGenerateMultiple_checkHowManySolved() {

        List<GeneratedPuzzleWithDifficulty> puzzles = new ArrayList<>();

        SudokuGenerator generator = new SudokuGenerator();
        List<PuzzleResults> results = generator.generate(81-17, 2);
        for(PuzzleResults puzzle : results) {
            List<List<String>> generatedPuzzles = puzzle.getResults();
    
            assertTrue(results.get(0).isValidPuzzle());
    
            // grade the puzzle
            SudokuGraderApp grader = new SudokuGraderApp();
    
            // get generated puzzle
            List<String> generatedPuzzle1 = generatedPuzzles.get(0);
            grader.setSudokuGridWithSolutionShorthand(generatedPuzzle1);
            grader.populateSolutionGridWithStartingPosition();
    
            PuzzleDifficulty difficulty = null;
            try {
                difficulty = grader.gradePuzzle();
            }
            catch(InvalidPuzzleException ive) {
                System.out.println("Error: Invalid puzzle");
            }
    
            // further asserts
            //int expectedNumberOfGivens = (9 * 9) - 60;
            //assertEquals(expectedNumberOfGivens, difficulty.getInitialGivens());
    
            puzzles.add(new GeneratedPuzzleWithDifficulty(puzzle, difficulty));
        }

        for(GeneratedPuzzleWithDifficulty puzzle : puzzles) {
            if(puzzle.getDifficulty() != null) {
                System.out.println(puzzle.getResults().getResults().get(0));
                System.out.println("Puzzle valid: " + puzzle.getResults().isValidPuzzle()
                        + ", solved?: " + puzzle.getDifficulty().isPuzzleSolved());
                
                System.out.println("Difficulty: " + puzzle.getDifficulty().getDifficulty());
                System.out.println("Initial givens: " + puzzle.getDifficulty().getInitialGivens());
                System.out.println("max candidate removal attemps: " + puzzle.getResults().getMaxCandidateRemovalAttempts());
                System.out.println("Naked singles found: " + puzzle.getDifficulty().getNakedSingleCount());
                System.out.println("Hidden singles found: " + puzzle.getDifficulty().getHiddenSingleCount());
                System.out.println("Naked pairs found: " + puzzle.getDifficulty().getNakedPairsCount());
                
                System.out.println();
            }
            else{
                System.out.println("Puzzle valid: No, invalid puzzle");

            }
        }
        
    }

    /**
     * Tests generating a puzzle with 64 candidate removed, 17 givens (the least
     * number of givens for a typical valid puzzle).
     * 
     */
    @Test
    public void testGenerate64() {
        SudokuGenerator generator = new SudokuGenerator();

        List<PuzzleResults> results = generator.generate(64, 1);

        List<List<String>> generatedPuzzles = results.get(0).getResults();
        for (List<String> shorthand : generatedPuzzles) {
            System.out.println(shorthand);
        }

        // grade the puzzle
        SudokuGraderApp grader = new SudokuGraderApp();

        // get generated puzzle
        List<String> generatedPuzzle1 = generatedPuzzles.get(0);
        grader.setSudokuGridWithSolutionShorthand(generatedPuzzle1);
        grader.populateSolutionGridWithStartingPosition();

        PuzzleDifficulty difficulty = grader.gradePuzzle();

        assertNotNull(difficulty);

        // further asserts
        //TODO bug why is this 24?
        //int expectedNumberOfGivens = (9 * 9) - 64;
        //assertEquals(expectedNumberOfGivens, difficulty.getInitialGivens());

        assertTrue(results.get(0).isValidPuzzle());
        
        if(difficulty != null) {
            System.out.println(results.get(0));
            System.out.println("Puzzle valid: " + results.get(0).isValidPuzzle()
                    + ", solved?: " + difficulty.isPuzzleSolved());
            
            System.out.println("Difficulty: " + difficulty.getDifficulty());
            System.out.println("Initial givens: " + difficulty.getInitialGivens());
            System.out.println("max candidate removal attemps: " + results.get(0).getMaxCandidateRemovalAttempts());
            System.out.println("Naked singles found: " + difficulty.getNakedSingleCount());
            System.out.println("Hidden singles found: " + difficulty.getHiddenSingleCount());
            System.out.println("Naked pairs found: " + difficulty.getNakedPairsCount());
            
            System.out.println();
        }
        else{
            System.out.println("Puzzle valid: No, invalid puzzle");

        }
    }
    
    @Test
    public void testGenerateGradedPuzzles_17Givens_1(){
        SudokuGenerator generator = new SudokuGenerator();
        List<GeneratedPuzzleWithDifficulty> puzzles = generator.generateGradedPuzzles(17, 1);
        
        assertEquals(1, puzzles.size());
        
        this.printGeneratedResults(puzzles);
    }

    @Test
    public void testGenerateGradedPuzzles_18Givens_2(){
        SudokuGenerator generator = new SudokuGenerator();
        List<GeneratedPuzzleWithDifficulty> puzzles = generator.generateGradedPuzzles(18, 2);
        
        assertEquals(2, puzzles.size());
        
        this.printGeneratedResults(puzzles);
    }

    @Test
    public void testGenerateGradedPuzzles_19Givens_2(){
        SudokuGenerator generator = new SudokuGenerator();
        List<GeneratedPuzzleWithDifficulty> puzzles = generator.generateGradedPuzzles(19, 2);
        
        assertEquals(2, puzzles.size());
        
        this.printGeneratedResults(puzzles);
    }

    /**
     * 20 givens gets some MEDIUM difficulty puzzles, but < 20 only gets HARDs
     */
    @Test
    public void testGenerateGradedPuzzles_20Givens_2(){
        SudokuGenerator generator = new SudokuGenerator();
        List<GeneratedPuzzleWithDifficulty> puzzles = generator.generateGradedPuzzles(20, 2);
        
        assertEquals(2, puzzles.size());
        
        this.printGeneratedResults(puzzles);
    }

    @Test
    public void testGenerateGradedPuzzles_21Givens_2(){
        SudokuGenerator generator = new SudokuGenerator();
        List<GeneratedPuzzleWithDifficulty> puzzles = generator.generateGradedPuzzles(21, 2);
        
        assertEquals(2, puzzles.size());
        
        this.printGeneratedResults(puzzles);
    }

    @Test
    public void testGenerateGradedPuzzles_22Givens_2(){
        SudokuGenerator generator = new SudokuGenerator();
        List<GeneratedPuzzleWithDifficulty> puzzles = generator.generateGradedPuzzles(22, 2);
        
        assertEquals(2, puzzles.size());
        
        this.printGeneratedResults(puzzles);
    }

    @Test
    public void testGenerateGradedPuzzles_23Givens_2(){
        SudokuGenerator generator = new SudokuGenerator();
        List<GeneratedPuzzleWithDifficulty> puzzles = generator.generateGradedPuzzles(23, 2);
        
        assertEquals(2, puzzles.size());
        
        this.printGeneratedResults(puzzles);
    }

    @Test
    public void testGenerateGradedPuzzles_24Givens_2(){
        SudokuGenerator generator = new SudokuGenerator();
        List<GeneratedPuzzleWithDifficulty> puzzles = generator.generateGradedPuzzles(24, 2);
        
        assertEquals(2, puzzles.size());
        
        this.printGeneratedResults(puzzles);
    }

    //TODO: bug - 25 givens for some reason goes into into a loop
    //@Test
    public void testGenerateGradedPuzzles_25Givens_1(){
        SudokuGenerator generator = new SudokuGenerator();
        List<GeneratedPuzzleWithDifficulty> puzzles = generator.generateGradedPuzzles(25, 2);
        
        assertEquals(2, puzzles.size());
        
        this.printGeneratedResults(puzzles);
    }

    
    void printGeneratedResults(List<GeneratedPuzzleWithDifficulty> puzzles) {
        for(GeneratedPuzzleWithDifficulty puzzle : puzzles) {
            if(puzzle.getDifficulty() != null) {
                System.out.println();
                System.out.println(puzzle.getResults().getResults().get(0));
                System.out.println("Puzzle valid: " + puzzle.getResults().isValidPuzzle()
                        + ", solved?: " + puzzle.getDifficulty().isPuzzleSolved());
                
                System.out.println("Difficulty: " + puzzle.getDifficulty().getDifficulty());
                System.out.println("Initial givens: " + puzzle.getDifficulty().getInitialGivens());
                System.out.println("max candidate removal attemps: " + puzzle.getResults().getMaxCandidateRemovalAttempts());
                System.out.println("Naked singles found: " + puzzle.getDifficulty().getNakedSingleCount());
                System.out.println("Hidden singles found: " + puzzle.getDifficulty().getHiddenSingleCount());
                System.out.println("Naked pairs found: " + puzzle.getDifficulty().getNakedPairsCount());
                
                System.out.println();
            }
            else{
                System.out.println("Puzzle valid: No, invalid puzzle");

            }
        }

    }
    
}
