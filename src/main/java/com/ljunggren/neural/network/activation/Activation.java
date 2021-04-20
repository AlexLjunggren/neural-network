package com.ljunggren.neural.network.activation;

import java.util.function.Function;

public interface Activation {

    Function<Double, Double> calculate();
    Function<Double, Double> derivative();
    
}
