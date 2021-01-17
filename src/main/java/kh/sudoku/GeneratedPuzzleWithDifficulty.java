package kh.sudoku;

import kh.sudokugrader.PuzzleDifficulty;

public class GeneratedPuzzleWithDifficulty {

    private PuzzleResults results;
    private PuzzleDifficulty difficulty;
    
    public GeneratedPuzzleWithDifficulty(PuzzleResults results, PuzzleDifficulty difficulty) {
        this.results = results;
        this.difficulty = difficulty;
    }

    public PuzzleResults getResults() {
        return results;
    }

    public void setResults(PuzzleResults results) {
        this.results = results;
    }

    public PuzzleDifficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(PuzzleDifficulty difficulty) {
        this.difficulty = difficulty;
    }    
}
