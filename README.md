## Neural Network ##

Overview

```java
NeuralNetwork neuralNetwork = new NeuralNetwork(3, 20, 10)
    .activation(new Sigmoid())
    .learnRate(0.01)
    .labels(Arrays.stream(Color.values())
            .map(color -> color.name())
            .collect(Collectors.toList()))
    .train(getColorRGBs(), getColorValues(), 100000);
```

## Instantiate Neural Network ##

Neural Networks consist of 3 layers.
1. Input Layer
1. Hidden Layer
1. Output Layer

On instantiation, the number of nodes for each layer must be defined.

```java
NeuralNetwork neuralNetwork = new NeuralNetwork(3, 4, 2);
```

## Activation ##

Different activation functions may be used to normalize the output of a node. Activation options:
1. Sigmoid
1. Relu

```java
neuralNetwork.activation(new Sigmoid());
```

A custom activation may be created by implementing Activation.

```java
public class MyActivation implements Activation {}
```

## Learn rate ## 
```java
neuralNetwork.learnRate(0.25);
```

## Labels ##

```java
List<String> labels = Arrays.asList(new String[] {
	"Yes",
	"No"
});
neuralNetwork.labels(labels);
```

## Train the neural network ##

```java
neuralNetwork.train(inputs, expectedOutputs, epochs);
```

## Use / make predictions ##

```java
neuralNetwork.predict(input);
```

## Save learned neural network ##

```java
Network savedNetwork = neuralNetwork.toNetwork();
```
