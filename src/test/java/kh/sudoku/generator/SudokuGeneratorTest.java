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

        List<List<String>> generatedPuzzles = results.getResults();
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

        assertTrue(results.isValidPuzzle());
    }

    /**
     * Tests generating a puzzle with 10 candidate removed.
     * 
     */
    @Test
    public void testGenerate10() {
        SudokuGenerator generator = new SudokuGenerator();

        PuzzleResults results = generator.generate(10);

        List<List<String>> generatedPuzzles = results.getResults();
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

        List<List<String>> generatedPuzzles = results.getResults();
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

        List<List<String>> generatedPuzzles = results.getResults();
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

        List<List<String>> generatedPuzzles = results.getResults();
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

        List<List<String>> generatedPuzzles = results.getResults();
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

        assertTrue(results.isValidPuzzle());
        
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

        for (int i = 0; i < 2; i++) {

            SudokuGenerator generator = new SudokuGenerator();

            PuzzleResults results = generator.generate(81-17);

            List<List<String>> generatedPuzzles = results.getResults();
            //for (List<String> shorthand : generatedPuzzles) {
            //    System.out.println(shorthand);
            //}

            assertTrue(results.isValidPuzzle());

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

            puzzles.add(new GeneratedPuzzleWithDifficulty(results, difficulty));
        }

        //TODO bug - puzzle showing as invalid but output during run showing solutions=1
        //I think isValidPuzzle is showing a different result that difficulty.isValid
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

        PuzzleResults results = generator.generate(64);

        List<List<String>> generatedPuzzles = results.getResults();
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

        assertTrue(results.isValidPuzzle());
    }
}