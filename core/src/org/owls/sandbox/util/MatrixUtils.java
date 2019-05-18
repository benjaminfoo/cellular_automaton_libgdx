package org.owls.sandbox.util;

import org.owls.sandbox.model.CellStates;

public class MatrixUtils {

    /**
     * Gibt die jeweils übergebene Matrix aus
     */
    public static void printMatrix(int[][] matrix) {
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                System.out.print(matrix[y][x] == CellStates.ALIVE ? "█" : "░");
            }
            System.out.println();
        }

        System.out.println();
    }


    /**
     * Helfer-Methode welche eine neue Array-Instanz mit den Inhalten des
     * übergebenen Arrays erzeugt. (Neue Generation) Hinweis: Der alte Array
     * kann dadurch verworfen werden
     */

    /**
     * Utility-Method
     * @param cell
     * @return
     */
    public static  int[][] cloneArray(int[][] cell) {
        // create identical array based on cell parameter
        int[][] result = new int[cell.length][cell[0].length];

        // transfer content from cell array to new result array
        for (int y = 0; y < result.length; y++) {
            for (int x = 0; x < result.length; x++) {
                result[y][x] = cell[y][x];
            }
        }

        return result;
    }


}
