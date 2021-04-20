package com.ljunggren.neural.network.activation;

import java.util.function.Function;

public class Relu implements Activation {

    @Override
    public Function<Double, Double> calculate() {
        return z -> Math.max(0, z);
    }

    @Override
    public Function<Double, Double> derivative() {
        return z -> z <= 0.0 ? 0.0 : 1;
    }

}
