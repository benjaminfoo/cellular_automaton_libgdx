package org.owls.sandbox.model;

import java.util.Arrays;
import java.util.Objects;

public class Matrix {

    /**
     * The amount of row-vectors / the height of the matrix
     */
    private int height;

    /**
     * The amount of column-vectors / the width of the matrix
     */
    private int width;

    /**
     * The data (array) of this matrix
     */
    private int[][] matrixData = {{}};

    public Matrix() {
    }

    public Matrix(int width, int height) {
        this.height = height;
        this.width = width;

        this.matrixData = new int[height][width];
    }

    public Matrix(int[][] rawData) {
        this.matrixData = rawData;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int[][] getMatrixData() {
        return matrixData;
    }

    public void setMatrixData(int[][] matrixData) {
        this.matrixData = matrixData;
    }


    public Matrix(int height, int width, int[][] matrixData) {
        this.height = height;
        this.width = width;
        this.matrixData = matrixData;
    }

    @Override
    public String toString() {
        return "Matrix{" +
                "height=" + height +
                ", width=" + width +
                ", matrixData=" + Arrays.toString(matrixData) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix matrix = (Matrix) o;
        return height == matrix.height &&
                width == matrix.width &&
                Arrays.equals(matrixData, matrix.matrixData);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(height, width);
        result = 31 * result + Arrays.hashCode(matrixData);
        return result;
    }
}
