package com.ljunggren.neuralNetwork;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.junit.Test;

public class MatrixTest {

    @Test
    public void constructorMatrixTest() {
        Matrix matrixFromArray = new Matrix(new double[] {1, 2, 3});
        Matrix matrixFromMatrix = new Matrix(matrixFromArray);
        for (int row = 0; row < matrixFromArray.numRows(); row++) {
            for (int column = 0; column < matrixFromArray.numCols(); column++) {
                assertEquals(matrixFromArray.get(row, column), matrixFromMatrix.get(row, column), 0);
            }
        }
    }
    
    @Test
    public void constructorSingleArrayTest() {
        Matrix matrix = new Matrix(new double[] {1, 2, 3});
        assertEquals(3, matrix.numRows());
        assertEquals(1, matrix.numCols());
        assertEquals(1, matrix.get(0, 0), 0);
        assertEquals(2, matrix.get(1, 0), 0);
        assertEquals(3, matrix.get(2, 0), 0);
    }
    
    @Test
    public void constructorArrayArrayTest() {
        Matrix matrix = new Matrix(new double[][] {
                {1, 2, 3},
                {4, 5, 6}
        });
        assertEquals(2, matrix.numRows());
        assertEquals(3, matrix.numCols());
        assertEquals(1, matrix.get(0, 0), 0);
        assertEquals(2, matrix.get(0, 1), 0);
        assertEquals(3, matrix.get(0, 2), 0);
        assertEquals(4, matrix.get(1, 0), 0);
        assertEquals(5, matrix.get(1, 1), 0);
        assertEquals(6, matrix.get(1, 2), 0);
    }
    
    @Test
    public void randomizeTest() {
        Matrix matrix = new Matrix(new double[2][2]).randomize();
        assertTrue(matrix.get(0, 0) != 0.0);
        assertTrue(matrix.get(0, 1) != 0.0);
        assertTrue(matrix.get(1, 0) != 0.0);
        assertTrue(matrix.get(1, 1) != 0.0);
    }
    
    @Test
    public void functionTest() {
        Matrix matrix = new Matrix(new double[][] {
            {1, 2},
            {3, 4}
        });
        Function<Double, Double> timesTwo = z -> z * 2;
        Matrix doubledMatrix = matrix.function(timesTwo);
        assertEquals(doubledMatrix.get(0, 0), 2, 0);
        assertEquals(doubledMatrix.get(0, 1), 4, 0);
        assertEquals(doubledMatrix.get(1, 0), 6, 0);
        assertEquals(doubledMatrix.get(1, 1), 8, 0);
    }
    
    @Test
    public void toListTest() {
        Matrix matrix = new Matrix(new double[][] {
            {1, 2, 3},
            {4, 5, 6}
        });
        List<Double> values = Arrays.asList(new Double[] {1.0, 2.0, 3.0, 4.0, 5.0, 6.0});
        assertEquals(values, matrix.toList());
    }
    
    @Test
    public void toArrayTest() {
        double[][] arrays = new double[][] {
            {1, 2, 3},
            {4, 5, 6}
        };
        Matrix matrix = new Matrix(arrays);
        assertArrayEquals(arrays, matrix.toArray());
    }

}
