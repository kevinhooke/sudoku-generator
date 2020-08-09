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

    public PuzzleResults generate(int candidatesToRemove) {

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
        PuzzleResults results = solver.generateNewPuzzle(givenSolutionsShorthand, 1);

        // initial givens to remove (up to 64, leaving min of 17)
        boolean stillValid = true;
        boolean continueRemovingCells = true;
        int candidatesRemoved = 0;
        PuzzleResults results2 = null;
        List<String> result1 = results.getResults().get(0);

        while (stillValid && continueRemovingCells && candidatesRemoved < candidatesToRemove) {
            int row = new Random().nextInt(9);
            int col = new Random().nextInt(9);
            // is there a number still in this post?
            boolean cellRemoved = this.removeCandidateFromPosition(row, col, result1);
            candidatesRemoved++;
            // check the puzzle is valid, if still valid, continue
            solver = new SudokuSolverWithDLX();
            results2 = solver.run(result1, 1);
            stillValid = results2.isValidPuzzle();
            
            // replace 1 candidate, pass back to solver
            result1 = results.getResults().get(0);
            // String row1 = result1.get(0);
            // row1 = row1.substring(1);
            // row1 = "." + row1;
            // result1.set(0, row1);
            // results.getResults().set(0, result1);
        }

        //TODO: if not valid, need to backtrack or start again
        
        return results2;
    }

    private boolean removeCandidateFromPosition(int row, int col, List<String> puzzle) {
        // TODO Auto-generated method stub
        return false;
    }

}
