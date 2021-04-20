## Neural Network ##

Instantiate Neural Network

```java
NeuralNetwork neuralNetwork = new NeuralNetwork(3, 4, 2);
```

Define activation

```java
neuralNetwork.activation(new Sigmoid());
```

Activation options:
1. Sigmoid
1. Relu

Define learn rate

```java
neuralNetwork.learnRate(0.25);
```

Define labels

```java
List<String> labels = Arrays.asList(new String[] {
	"Yes",
	"No"
});
neuralNetwork.labels(labels);
```

Train the neural network

```java
neuralNetwork.train(inputs, expectedOutputs, epochs);
```

Use / make predictions

```java
neuralNetwork.predict(input);
```

Save learned neural network

```java
Network savedNetwork = neuralNetwork.toNetwork();
```
