package kh.sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
        int candidateRemovalAttempts = 0;
        
        PuzzleResults checkPuzzleResults = null;
        List<String> generatedPuzzle = results.getResults().get(0);

        while (stillValid && continueRemovingCells && candidatesRemoved < candidatesToRemove 
                    && candidateRemovalAttempts < 10) {
            int row = new Random().nextInt(9);
            int col = new Random().nextInt(9);
            // is there a number still in this position?
            char candidateValueToRemove = this.getCandidateFromPosition(row, col, generatedPuzzle);
            if (candidateValueToRemove != '.') {
                generatedPuzzle = this.removeCandidateFromPosition(row, col, generatedPuzzle);

                // check the puzzle is valid, if still valid, continue
                solver = new SudokuSolverWithDLX();
                checkPuzzleResults = solver.run(generatedPuzzle, 1);
                stillValid = checkPuzzleResults.isValidPuzzle();

                // increment the count if the puzzle is still valid and we can
                // try the next removal, otherwise put the value vack
                if (stillValid) {
                    candidatesRemoved++;
                    candidateRemovalAttempts = 0;
                } else {
                    candidateRemovalAttempts++;
                    
                    //put back original into cell
                    this.setCandidateInPosition(row, col, candidateValueToRemove);
                }
            }
        }

        //TODO: if not valid, need to backtrack or start again
        results.getResults().set(0, generatedPuzzle);
        return results;
    }

    private void setCandidateInPosition(int row, int col, char candidateValueToRemove) {
        // TODO Auto-generated method stub
        
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
