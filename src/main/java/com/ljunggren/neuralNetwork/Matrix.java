package com.ljunggren.neuralNetwork;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.ejml.data.CMatrixRMaj;
import org.ejml.data.DMatrixRMaj;
import org.ejml.data.DMatrixSparseCSC;
import org.ejml.data.FMatrixRMaj;
import org.ejml.data.FMatrixSparseCSC;
import org.ejml.data.MatrixType;
import org.ejml.data.ZMatrixRMaj;
import org.ejml.simple.SimpleBase;

public class Matrix extends SimpleBase<Matrix> {

    private static final long serialVersionUID = 3769489645561483159L;
    
    public Matrix(Matrix orig) {
        setMatrix(orig.mat.copy());
    }
    
    public Matrix(double[] data) {
        setMatrix(new DMatrixRMaj(data));
    }
    
    public Matrix(double[][] data) {
        setMatrix(new DMatrixRMaj(data));
    }
    
    public Matrix randomize() {
        return function(z -> Math.random() * 2 - 1);
    }
    
    public Matrix function(Function<Double, Double> function) {
        Matrix copy = new Matrix(this);
        for (int row = 0; row < copy.numRows(); row++) {
            for (int column = 0; column < copy.numCols(); column++) {
                double value = function.apply(copy.get(row, column));
                copy.set(row, column, value);
            }
        }
        return copy;
    }
    
    public List<Double> toList() {
        List<Double> values = new ArrayList<>();
        for (int row = 0; row < this.numRows(); row++) {
            for (int column = 0; column < this.numCols(); column++) {
                values.add(this.get(row, column));
            }
        }
        return values;
    }
    
    public double[][] toArray() {
        double[][] array = new double[this.numRows()][this.numCols()];
        for (int row = 0; row < this.numRows(); row++) {
            for (int column = 0; column < this.numCols(); column++) {
                array[row][column] = this.get(row, column);
            }
        }
        return array;
    }
    
    @Override
    protected Matrix createMatrix(int numRows, int numCols, MatrixType type) {
        return new Matrix(numRows, numCols, type);
    }

    @Override
    protected Matrix wrapMatrix(org.ejml.data.Matrix m) {
        return null;
    }
    
    public Matrix(int numRows, int numCols, MatrixType type) {
        switch (type) {
        case DDRM:
            setMatrix(new DMatrixRMaj(numRows, numCols));
            break;
        case FDRM:
            setMatrix(new FMatrixRMaj(numRows, numCols));
            break;
        case ZDRM:
            setMatrix(new ZMatrixRMaj(numRows, numCols));
            break;
        case CDRM:
            setMatrix(new CMatrixRMaj(numRows, numCols));
            break;
        case DSCC:
            setMatrix(new DMatrixSparseCSC(numRows, numCols));
            break;
        case FSCC:
            setMatrix(new FMatrixSparseCSC(numRows, numCols));
            break;
        default:
            throw new RuntimeException("Unknown matrix type");
        }
    }

}