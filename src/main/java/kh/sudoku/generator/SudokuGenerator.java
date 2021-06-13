package kh.sudoku.generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import kh.sudoku.PuzzleResults;
import kh.sudoku.SudokuSolverWithDLX;
import kh.sudokugrader.InvalidPuzzleException;

/**
 * Sudoku puzzle generator. Uses kh.sudoku.SudokuSolverWithDLX to check for
 * valid puzzles and kh.sudokugrader.SudokuGraderApp to check puzzle difficulty.
 * 
 * @author kevinhooke
 *
 */
public class SudokuGenerator {

    public String generateRandomSeedString() {
        List<String> startingList = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
        Collections.shuffle(startingList);

        StringBuilder sb = new StringBuilder();
        for (String nextNumber : startingList) {
            sb.append(nextNumber);
        }

        return sb.toString();
    }

    /**
     * Generate new puzzle with specified number of givens removed.
     * 
     * @param candidatesToRemove the number of givens to remove
     * 
     * @return generated puzzle
     */
    //TODO this should really change to number of givens because thats what we're interested in, not how many where removed
    public PuzzleResults generate(int candidatesToRemove) {

        if(candidatesToRemove < 1 || candidatesToRemove > 64) {
            throw new InvalidCandiatesToRemoveException();
        }
        
        SudokuSolverWithDLX solver = new SudokuSolverWithDLX();
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
        
        //TODO: this was a prior approach, but switch to generating a completed grid then remove candidates
        PuzzleResults results = solver.run(givenSolutionsShorthand, 1);
        
        // initial givens to remove (up to 64, leaving min of 17)
        boolean stillValid = true;
        boolean continueRemovingCells = true;
        int candidatesRemoved = 0;
        
        //if puzzle is valid, removal attempts is 0
        int candidateRemovalAttempts = 0;
        
        //tracks the maximum number of attempts to remove a value before a valid puzzle was generated
        int maxCandidateRemovalAttempts = 0;
        
        PuzzleResults checkPuzzleResults = null;
        List<String> generatedPuzzle = results.getResults().get(0);

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
                    System.out.println("*** Only one solution, puzzle is valid");

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

        System.out.println("*** Removal attempts: " + candidateRemovalAttempts);
        
        //TODO: if not valid, need to backtrack or start again
        results.setMaxCandidateRemovalAttempts(maxCandidateRemovalAttempts);
        results.getResults().set(0, generatedPuzzle);
        return results;
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
