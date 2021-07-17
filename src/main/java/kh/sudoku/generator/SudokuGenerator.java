package kh.sudoku.generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kh.sudoku.PuzzleResults;
import kh.sudoku.SudokuSolverWithDLX;
import kh.sudokugrader.InvalidPuzzleException;
import kh.sudokugrader.PuzzleDifficulty;
import kh.sudokugrader.SudokuGraderApp;

/**
 * Sudoku puzzle generator. Uses kh.sudoku.SudokuSolverWithDLX to check for
 * valid puzzles and kh.sudokugrader.SudokuGraderApp to check puzzle difficulty.
 * 
 * @author kevinhooke
 *
 */
public class SudokuGenerator {

    private static final Logger LOGGER = LogManager.getLogger();
    
    public String generateRandomSeedString() {
        List<String> startingList = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
        Collections.shuffle(startingList);

        StringBuilder sb = new StringBuilder();
        for (String nextNumber : startingList) {
            sb.append(nextNumber);
        }

        return sb.toString();
    }

    public List<GeneratedPuzzleWithDifficulty> generateGradedPuzzles(int targetGivens, int puzzlesToGenerate){
        List<GeneratedPuzzleWithDifficulty> puzzles = new ArrayList<>();

        SudokuGenerator generator = new SudokuGenerator();
        List<PuzzleResults> results = generator.generate(81 - targetGivens, puzzlesToGenerate);
        for(PuzzleResults puzzle : results) {
            List<List<String>> generatedPuzzles = puzzle.getResults();
    
            //TODO: convert to if check
            //assertTrue(results.get(0).isValidPuzzle());
    
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
                LOGGER.error("Error: Invalid puzzle");
            }
    
            puzzles.add(new GeneratedPuzzleWithDifficulty(puzzle, difficulty));
        }
        
        return puzzles;
    }
    
    /**
     * Generate new puzzle with specified number of givens removed.
     * 
     * @param candidatesToRemove the number of givens to remove
     * 
     * @return generated puzzle
     */
    //TODO this should really change to number of givens because thats what we're interested in, not how many where removed
    public List<PuzzleResults> generate(int candidatesToRemove, int puzzlesToGenerate) {

        if(candidatesToRemove < 1 || candidatesToRemove > 64) {
            throw new InvalidCandiatesToRemoveException();
        }
        List<PuzzleResults> generatedPuzzles = new ArrayList<>();
        
        SudokuSolverWithDLX solver = new SudokuSolverWithDLX();
        
        for(int count = 0; count < puzzlesToGenerate; count++) {
            // create seed for starting final solution
            List<String> givenSolutionsShorthand = new ArrayList<>();
            givenSolutionsShorthand.add(this.generateRandomSeedString());
            givenSolutionsShorthand.add(".........");
            givenSolutionsShorthand.add(".........");
            givenSolutionsShorthand.add(".........");
            givenSolutionsShorthand.add(".........");
            givenSolutionsShorthand.add(".........");
            givenSolutionsShorthand.add(".........");
            givenSolutionsShorthand.add(".........");
            givenSolutionsShorthand.add(".........");
            
            PuzzleResults puzzle = solver.run(givenSolutionsShorthand, 1);
            
            boolean stillValid = true;
            boolean continueRemovingCells = true;
            int candidatesRemoved = 0;
            
            //if puzzle is valid, removal attempts is 0
            int candidateRemovalAttempts = 0;
            
            //tracks the maximum number of attempts to remove a value before a valid puzzle was generated
            int maxCandidateRemovalAttempts = 0;
            
            PuzzleResults checkPuzzleResults = null;
            List<String> generatedPuzzle = puzzle.getResults().get(0);
    
            //stillValid
            while (continueRemovingCells && candidatesRemoved < candidatesToRemove 
                        && candidateRemovalAttempts < 1000) {
                int row = new Random().nextInt(9);
                int col = new Random().nextInt(9);
                // is there a number still in this position?
                char candidateValueToRemove = this.getCandidateFromPosition(row, col, generatedPuzzle);
                if (candidateValueToRemove != '.') {
                    generatedPuzzle = this.removeCandidateFromPosition(row, col, generatedPuzzle);
    
                    // check the puzzle is valid, if still valid, continue
                    solver = new SudokuSolverWithDLX();
                    
                    //need to look for more than 1 solution because we need to check there is only 1 to be valid
                    checkPuzzleResults = solver.run(generatedPuzzle, 2);
                    if(checkPuzzleResults.getResults().size() == 1) {
                        LOGGER.debug("*** Only one solution, puzzle is valid");
    
                        try {
                            stillValid = checkPuzzleResults.isValidPuzzle();
                        }
                        catch(Exception ipe) {
                            stillValid = false;
                        }
                    }
                    else {
                        stillValid = false;
                    }
    
    
                    // increment the count if the puzzle is still valid and we can
                    // try the next removal, otherwise put the value back
                    if (stillValid) {
                        candidatesRemoved++;
                        candidateRemovalAttempts = 0;
                    } else {
                        candidateRemovalAttempts++;
                        maxCandidateRemovalAttempts++;
                        
                        //put back original into cell
                        String updatedRow = this.setCandidateInPosition(row, col, candidateValueToRemove, generatedPuzzle);
                        generatedPuzzle.set(row, updatedRow);
                    }
                }
            }
    
            LOGGER.debug("*** Removal attempts: " + candidateRemovalAttempts);
            
            //TODO: if not valid, need to backtrack or start again
            puzzle.setMaxCandidateRemovalAttempts(maxCandidateRemovalAttempts);
            puzzle.getResults().set(0, generatedPuzzle);
            
            generatedPuzzles.add(puzzle);
        }
        return generatedPuzzles;
    }

    private String setCandidateInPosition(int row, int col, char candidateValueToReplace, List<String> puzzle) {

        String currentRow = puzzle.get(row);
        char[] values = currentRow.toCharArray();
        values[col] = candidateValueToReplace;
        
        //TODO set back into list
        String updatedRow = new String(values);
        return updatedRow;
    }

    private char getCandidateFromPosition(int row, int col, List<String> puzzle) {
        //get row
        char candidateToReturn;
        String currentRow = puzzle.get(row);
        char[] values = currentRow.toCharArray();
        candidateToReturn = values[col];
        return candidateToReturn;
    }

    List<String> removeCandidateFromPosition(int row, int col, List<String> puzzle) {

        //get row
        String currentRow = puzzle.get(row);
        char[] values = currentRow.toCharArray();
        values[col] = '.';
        String newRow = new String(values);
        
        //put updated row back in List
        puzzle.set(row, newRow);
        return puzzle;
    }

}
