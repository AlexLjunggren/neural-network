package com.ljunggren.neuralNetwork.activation;

public class ActivationFactory {

    public static Activation create(String className) {
        try {
            return (Activation) Class.forName(className).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            return new Sigmoid();
        }
    }
    
}
