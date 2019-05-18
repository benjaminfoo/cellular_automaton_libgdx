package org.owls.sandbox.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.owls.sandbox.util.MatrixUtils.cloneArray;
import static org.owls.sandbox.util.MatrixUtils.printMatrix;

/**
 *
 */
public class GameOfLifeMatrix {

    //
    public String id;

    //
    private Matrix dataMatrix;

    // the amount of iterations within this matrix
    private int iteration = 0;

    public RuleSet currentRuleset = RuleSet.CONWAY;

    public GameOfLifeMatrix() {
        this.dataMatrix = new Matrix(256, 256);
    }

    /**
     * @param data
     */
    public GameOfLifeMatrix(int[][] data) {
        this.dataMatrix = new Matrix(data);
    }


    /**
     * @param width
     * @param height
     */
    public GameOfLifeMatrix(int width, int height) {
        this.dataMatrix = new Matrix(width, height);
    }

    /**
     * @param newId
     * @param newMatrixData
     */
    public GameOfLifeMatrix(String newId, int[][] newMatrixData) {
        this(newMatrixData);
        this.id = newId;
    }

    /**
     * @param maxIteration
     */
    public void startSimulation(int maxIteration) {
        // prints original matrix
        printMatrix(dataMatrix.getMatrixData());

        // prints first iteration
        int[][] nextGeneration = executeIteration((dataMatrix.getMatrixData()));
        printMatrix(nextGeneration);
        // prints second ... n iteration
        for (int iteration = 1; iteration < maxIteration; iteration++) {
            nextGeneration = executeIteration(nextGeneration);
            printMatrix(nextGeneration);

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

    private int[][] applyConyway(int[][] golMatrix) {
        // neue Generation des ursprünglichen Arrays
        int[][] nextGen = cloneArray(golMatrix);

        // iteriere über den alten Array und wende Regeln dementsprechend an
        for (int y = 0; y < golMatrix.length; y++) {
            for (int x = 0; x < golMatrix[y].length; x++) {

                // aktuelle Zelle der x und y Position
                int currentCell = golMatrix[y][x];
                int height = golMatrix.length - 1;
                int width = golMatrix[y].length - 1;

                // Ermittlung der Nachbar-Zellen
                // Berücksichtigt auch, dass Nachbarzellen auf der
                // gegenüberliegenden Seite verwendet werden.
                int north = golMatrix[y - 1 < 0 ? height : y - 1][x];
                int northEast = golMatrix[y - 1 < 0 ? height : y - 1][x + 1 > width ? 0 : x + 1];
                int east = golMatrix[y][x + 1 > width ? 0 : x + 1];
                int southEast = golMatrix[y + 1 > height ? 0 : y + 1][x + 1 > width ? 0 : x + 1];
                int south = golMatrix[y + 1 > height ? 0 : y + 1][x];
                int southWest = golMatrix[y + 1 > height ? 0 : y + 1][x - 1 < 0 ? width : x - 1];
                int west = golMatrix[y][x - 1 < 0 ? width : x - 1];
                int northWest = golMatrix[y - 1 < 0 ? height : y - 1][x - 1 < 0 ? width : x - 1];

                List<Integer> neighbours = new ArrayList<Integer>();
                neighbours.add(north);
                neighbours.add(northEast);
                neighbours.add(east);
                neighbours.add(southEast);
                neighbours.add(south);
                neighbours.add(southWest);
                neighbours.add(west);
                neighbours.add(northWest);

                // Rule 1
                // A dead cell with three neighbours well be respawned in the next generation
                if (currentCell == CellStates.DEAD) {
                    if (north + northEast + east + southEast + south + southWest + west + northWest == 3) {
                        nextGen[y][x] = CellStates.ALIVE;
                    }
                }

                // Rule 2
                // Living cells with less than two neighbours die in the nxt generation
                if (currentCell == CellStates.ALIVE) {
                    if (north + northEast + east + southEast + south + southWest + west + northWest < 2) {
                        nextGen[y][x] = CellStates.DEAD;
                    }
                }

                // Rule 3
                // Living cells with less than two neighbours die in the next generation due to loneliness
                if (currentCell == CellStates.ALIVE) {
                    if (north + northEast + east + southEast + south + southWest + west + northWest == 2
                            || north + northEast + east + southEast + south + southWest + west + northWest == 3) {
                        nextGen[y][x] = CellStates.ALIVE;
                    }
                }

                // Rule 4
                // Living cells with more than three neighbours die due overpopulation
                if (currentCell == CellStates.ALIVE) {
                    if (north + northEast + east + southEast + south + southWest + west + northWest > 3) {
                        nextGen[y][x] = CellStates.DEAD;
                    }
                }

            }
        }

        return nextGen;
    }


    private int[][] applyWireWorld(int[][] golMatrix) {
        // neue Generation des ursprünglichen Arrays
        int[][] nextGen = cloneArray(golMatrix);

        // iteriere über den alten Array und wende Regeln dementsprechend an
        for (int y = 0; y < golMatrix.length; y++) {
            for (int x = 0; x < golMatrix[y].length; x++) {

                // aktuelle Zelle der x und y Position
                int currentCell = golMatrix[y][x];
                int height = golMatrix.length - 1;
                int width = golMatrix[y].length - 1;

                // Ermittlung der Nachbar-Zellen
                // Berücksichtigt auch, dass Nachbarzellen auf der
                // gegenüberliegenden Seite verwendet werden.
                int north = golMatrix[y - 1 < 0 ? height : y - 1][x];
                int northEast = golMatrix[y - 1 < 0 ? height : y - 1][x + 1 > width ? 0 : x + 1];
                int east = golMatrix[y][x + 1 > width ? 0 : x + 1];
                int southEast = golMatrix[y + 1 > height ? 0 : y + 1][x + 1 > width ? 0 : x + 1];
                int south = golMatrix[y + 1 > height ? 0 : y + 1][x];
                int southWest = golMatrix[y + 1 > height ? 0 : y + 1][x - 1 < 0 ? width : x - 1];
                int west = golMatrix[y][x - 1 < 0 ? width : x - 1];
                int northWest = golMatrix[y - 1 < 0 ? height : y - 1][x - 1 < 0 ? width : x - 1];

                List<Integer> neighbours = new ArrayList<Integer>();
                neighbours.add(north);
                neighbours.add(northEast);
                neighbours.add(east);
                neighbours.add(southEast);
                neighbours.add(south);
                neighbours.add(southWest);
                neighbours.add(west);
                neighbours.add(northWest);

                // rule 1 - a blank square always stays blank
                // check!

                // rule 2 - an electron head always becomes an electron tail
                if (currentCell == CellStates.ELECTRON_HEAD) {
                    nextGen[y][x] = CellStates.ELECTRON_TAIL;
                }

                // rule 3 -  an electron tail always becomes copper
                if (currentCell == CellStates.ELECTRON_TAIL) {
                    nextGen[y][x] = CellStates.CONDUCTOR;
                }

                // rule 4 - electron head if exactly one or two of the neighbouring cells are electron heads,
                // otherwise remains conductor.
                if (currentCell == CellStates.CONDUCTOR) {

                    int headCount = 0;
                    for (Integer neighbour : neighbours) {
                        headCount = headCount + (neighbour == CellStates.ELECTRON_HEAD ? 1 : 0);
                    }

                    if (headCount == 1 || headCount == 2) {
                        nextGen[y][x] = CellStates.ELECTRON_HEAD;
                    }
                }

            }
        }

        return nextGen;
    }


    public int[][] executeIteration(int[][] golMatrix) {
        int[][] nextGen = new int[0][0];

        if(currentRuleset == RuleSet.CONWAY){
            nextGen = applyConyway(golMatrix);
        } else if(currentRuleset == RuleSet.WIREWORLD){
            nextGen = applyWireWorld(golMatrix);
        }

        iteration++;

        return nextGen;
    }


    public int getIteration() {
        return iteration;
    }

    public int[][] getMap() {
        return dataMatrix.getMatrixData();
    }

    public void setMatrixData(int[][] mapPreset) {
        this.dataMatrix = new Matrix(mapPreset);
    }

    public void setCellAt(int value, int cellX, int cellY) {
        int[][] matrixData = this.dataMatrix.getMatrixData();

        if (cellX <= 0) return;
        if (cellY <= 0f) return;
        if (cellY > matrixData.length - 1) return;
        if (cellX > matrixData[cellY].length - 1) return;

        matrixData[cellY][cellX] = value;
        this.dataMatrix.setMatrixData(matrixData);
    }

    public void reset(int i, int height) {
        setMatrixData(new int[i][height]);
        iteration = 0;
    }

    @Override
    public String toString() {
        return id;
    }

    public GameOfLifeMatrix(String id, Matrix dataMatrix, int iteration) {
        this.id = id;
        this.dataMatrix = dataMatrix;
        this.iteration = iteration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameOfLifeMatrix that = (GameOfLifeMatrix) o;
        return iteration == that.iteration &&
                Objects.equals(id, that.id) &&
                Objects.equals(dataMatrix, that.dataMatrix);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, dataMatrix, iteration);
    }
}