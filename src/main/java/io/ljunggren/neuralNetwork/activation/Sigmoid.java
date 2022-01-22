package io.ljunggren.neuralNetwork.activation;

import java.util.function.Function;

public class Sigmoid implements Activation {

    @Override
    public Function<Double, Double> calculate() {
        return z -> 1 / (1 + Math.exp(-z));
    }
    
    @Override
    public Function<Double, Double> derivative() {
        return z -> z * (1 - z);
    }

}
