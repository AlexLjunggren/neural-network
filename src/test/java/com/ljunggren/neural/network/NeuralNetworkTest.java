package com.ljunggren.neural.network;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ljunggren.neural.network.activation.Activation;
import com.ljunggren.neural.network.activation.Relu;
import com.ljunggren.neural.network.activation.Sigmoid;
import com.ljunggren.neural.network.data.Color;

public class NeuralNetworkTest {
    
    private NeuralNetwork neuralNetwork = new NeuralNetwork(3, 20, 10)
            .activation(new Sigmoid())
            .learnRate(0.01)
            .labels(Arrays.stream(Color.values())
                    .map(color -> color.name())
                    .collect(Collectors.toList()))
            .train(getColorRGBs(), getColorValues(), 100000);


    private double[][] getColorRGBs() {
        return Arrays.stream(Color.values())
                .map(color -> color.getRGB())
                .toArray(double[][]::new);
    }
    
    private double[][] getColorValues() {
        return Arrays.stream(Color.values())
                .map(color -> {
                    double[] array = new double[Color.values().length];
                    array[color.ordinal()] = 1;
                    return array;
                }).toArray(double[][]::new);
    }
    
    private List<String> getColorLabels() {
        return Arrays.stream(Color.values())
                .map(color -> color.name())
                .collect(Collectors.toList());
    }
    
    @Test
    public void instantiationTest() {
        NeuralNetwork neuralNetwork = new NeuralNetwork(3, 20, 10);
        assertEquals(20, neuralNetwork.getInputToHiddenWeights().numRows());
        assertEquals(10, neuralNetwork.getHiddenToOutputWeights().numRows());
        assertEquals(20, neuralNetwork.getHiddenBias().numRows());
        assertEquals(10, neuralNetwork.getOutputBias().numRows());
        assertTrue(neuralNetwork.getLearnRate() > 0);
    }
    
    @Test
    public void activationTest() {
        Activation activation = new Relu();
        NeuralNetwork neuralNetwork = new NeuralNetwork(3, 20, 10).activation(activation);
        assertEquals(activation, neuralNetwork.getActivation());
    }
    
    @Test
    public void learnRateTest() {
        double learnRate = 0.25;
        NeuralNetwork neuralNetwork = new NeuralNetwork(3, 20, 10).learnRate(learnRate);
        assertEquals(learnRate, neuralNetwork.getLearnRate(), 0);
    }
    
    @Test
    public void labelsTest() {
        List<String> labels = getColorLabels();
        NeuralNetwork neuralNetwork = new NeuralNetwork(3, 20, 10).labels(labels);
        assertEquals(labels, neuralNetwork.getLabels());
    }

    @Test
    public void trainTest() {
        // Not sure how to test this
    }
    
    @Test
    public void predictTest() {
        assertEquals(Color.RED, getPredictedColor(neuralNetwork.predict(Color.RED.getRGB())));
        assertEquals(Color.GREEN, getPredictedColor(neuralNetwork.predict(Color.GREEN.getRGB())));
        assertEquals(Color.BLUE, getPredictedColor(neuralNetwork.predict(Color.BLUE.getRGB())));
    }
    
    private Color getPredictedColor(List<Double> predictions) {
        return Color.values()[predictions.indexOf(Collections.max(predictions))];
    }
        
    @Test
    public void toNetworkTest() {
        Network network = neuralNetwork.toNetwork();
        assertArrayEquals(neuralNetwork.getInputToHiddenWeights().toArray(), network.getInputToHiddenWeights());
        assertArrayEquals(neuralNetwork.getHiddenToOutputWeights().toArray(), network.getHiddenToOutputWeights());
        assertArrayEquals(neuralNetwork.getHiddenBias().toArray(), network.getHiddenBias());
        assertArrayEquals(neuralNetwork.getOutputBias().toArray(), network.getOutputBias());
        assertEquals(neuralNetwork.getActivation().getClass().getName(), network.getActivationClass());
        assertEquals(neuralNetwork.getLearnRate(), network.getLearnRate(), 0);
        assertEquals(neuralNetwork.getLabels(), network.getLabels());
    }
    
}

