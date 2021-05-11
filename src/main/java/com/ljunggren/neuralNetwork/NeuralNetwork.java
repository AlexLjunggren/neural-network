package com.ljunggren.neuralNetwork;

import java.util.ArrayList;
import java.util.List;

import com.ljunggren.neuralNetwork.activation.Activation;
import com.ljunggren.neuralNetwork.activation.ActivationFactory;
import com.ljunggren.neuralNetwork.activation.Sigmoid;

import lombok.Getter;

@Getter
public class NeuralNetwork {
    
    private Matrix inputToHiddenWeights;
    private Matrix hiddenToOutputWeights;
    private Matrix hiddenBias;
    private Matrix outputBias;
    private Activation activation;
    private double learnRate;
    private List<String> labels;
    
    public NeuralNetwork(Network network) {
        this.inputToHiddenWeights = new Matrix(network.getInputToHiddenWeights());
        this.hiddenToOutputWeights = new Matrix(network.getHiddenToOutputWeights());
        this.hiddenBias = new Matrix(network.getHiddenBias());
        this.outputBias = new Matrix(network.getOutputBias());
        this.activation = ActivationFactory.create(network.getActivationClass());
        this.learnRate = network.getLearnRate();
        this.labels = network.getLabels();
    }
    
    public NeuralNetwork(int inputSize, int hiddenSize, int outputSize) {
        this.inputToHiddenWeights = new Matrix(new double[hiddenSize][inputSize]).randomize();
        this.hiddenToOutputWeights = new Matrix(new double[outputSize][hiddenSize]).randomize();
        this.hiddenBias = new Matrix(new double[hiddenSize]).randomize();
        this.outputBias = new Matrix(new double[outputSize]).randomize();
        this.activation = new Sigmoid();
        this.learnRate = 0.01;
        this.labels = new ArrayList<>();
    }
    
    public NeuralNetwork activation(Activation activation) {
        this.activation = activation;
        return this;
    }
    
    public NeuralNetwork learnRate(double learnRate) {
        this.learnRate = learnRate;
        return this;
    }
    
    public NeuralNetwork labels(List<String> labels) {
        this.labels = labels;
        return this;
    }
    
    public NeuralNetwork train(double[][] inputs, double[][] targets, int epochs) {
        for (int i = 0; i < epochs; i++) {
            int random = (int) (Math.random() * inputs.length);
            train(inputs[random], targets[random]);
        }
        return this;
    }

    private void train(double[] inputs, double[] targets) {
        // Forward Propagation
        Matrix input = new Matrix(inputs);
        Matrix hidden = generateHiddenMatix(input);
        Matrix output = generateOutputMatrix(hidden);
        Matrix target = new Matrix(targets);
        Matrix error = target.minus(output);
        Matrix gradient = generateGradientMatrix(output, error);
        
        // Back Propagation
        Matrix transposedHidden = hidden.transpose();
        Matrix hiddenToOuputWeightDelta = gradient.mult(transposedHidden);
        hiddenToOutputWeights = hiddenToOutputWeights.plus(hiddenToOuputWeightDelta);
        outputBias = outputBias.plus(gradient);
        
        Matrix transposedHiddenToOutputWeights = hiddenToOutputWeights.transpose();
        Matrix hiddenErrors = transposedHiddenToOutputWeights.mult(error);
        
        Matrix hiddenGradient = generateHiddenGradientMatrix(hidden, hiddenErrors);
        Matrix transposedInput = input.transpose();
        Matrix inputToHiddenWeightDelta = hiddenGradient.mult(transposedInput);
        
        inputToHiddenWeights = inputToHiddenWeights.plus(inputToHiddenWeightDelta);
        hiddenBias = hiddenBias.plus(hiddenGradient);
    }
    
    public List<Double> predict(double[] data) {
        Matrix input = new Matrix(data);
        Matrix hidden = inputToHiddenWeights.mult(input);
        hidden = hidden.plus(hiddenBias);
        hidden = hidden.function(activation.calculate());
        
        Matrix output = hiddenToOutputWeights.mult(hidden);
        output = output.plus(outputBias);
        output = output.function(activation.calculate());
        
        return output.toList();
    }
    
    public Network toNetwork() {
        return Network.builder()
                .inputToHiddenWeights(inputToHiddenWeights.toArray())
                .hiddenToOutputWeights(hiddenToOutputWeights.toArray())
                .hiddenBias(hiddenBias.toArray())
                .outputBias(outputBias.toArray())
                .activationClass(activation.getClass().getName())
                .learnRate(learnRate)
                .labels(labels)
                .build();
    }
    
    private Matrix generateHiddenMatix(Matrix input) {
        Matrix hidden = inputToHiddenWeights.mult(input);
        hidden = hidden.plus(hiddenBias);
        hidden = hidden.function(activation.calculate());
        return hidden;
    }
    
    private Matrix generateOutputMatrix(Matrix hidden) {
        Matrix output = hiddenToOutputWeights.mult(hidden);
        output = output.plus(outputBias);
        output = output.function(activation.calculate());
        return output;
    }
    
    private Matrix generateGradientMatrix(Matrix output, Matrix error) {
        Matrix gradient = output.function(activation.derivative());
        gradient = gradient.elementMult(error);
        gradient = gradient.scale(learnRate);
        return gradient;
    }
    
    private Matrix generateHiddenGradientMatrix(Matrix hidden, Matrix hiddenErrors) {
        Matrix hiddenGradient = hidden.function(activation.derivative());
        hiddenGradient = hiddenGradient.elementMult(hiddenErrors);
        hiddenGradient = hiddenGradient.scale(learnRate);
        return hiddenGradient;
    }
    
}
