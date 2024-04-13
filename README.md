# Perceptron
Simple digital neuron called perceptron program that learns from a given data and then make predictions afterwards with accuracy at least at 90%

Documantation:
MiniProject: Perceptron 

* Generate bias and weights (random between 0 and 1)
* Iteration:
    * Keep training until iteration error is less than 5%
    * Calculation iterator error -> how many times we had to change vector 

BREAKDOWN: 

public class Perceptron {

public Perceptron(int InputNum, double learnRate){
    this.learnRate = learnRate;
    this.weights = new double[InputNum];
    initializeWeightsAndBias();
}
* This is the constructor of the Perceptron class. It initializes the learnRate and weights array with the specified number of inputs (InputNum) and calls initializeWeightsAndBias() method.

public void train(List<double[]> trainingSet, List<Double> classLabels, int iterations) {
    for (int i = 0; i < iterations; i++) {
        for (int j = 0; j < trainingSet.size(); j++) {
            double[] instance = trainingSet.get(j);
            double predicted = predict(instance);
            double target = classLabels.get(j);
            for (int k = 0; k < weights.length; k++) {
                weights[k] += learnRate * (target - predicted) * instance[k];
            }
            bias += learnRate * (target - predicted);
        }
    }
}
* This method is used to train the perceptron. It takes a List of input instances (trainingSet), a list of corresponding class labels (classLabels), and the number of iterations to train (iterations). It iterates through the training set, adjusts weights and bias based on the predicted output and actual class label.

public double predict(double[] input){
    double net = bias;
    for(int i = 0; i < input.length - 1; i++){
        net += input[i] * weights[i];
    }

    net += bias;

    return net >= 0 ? 1 : 0;
}

* This method predicts the output based on the input. It calculates the net input by summing the product of inputs and weights, adds the bias, and then applies an activation function (in this case, a step function) to determine the output.

public double accuracy(List<double[]> testSet, List<Double> classLabels) {
    int correct = 0;
    int total = testSet.size();

    for (int i = 0; i < total; i++) {
        double[] input = testSet.get(i);
        double prediction = predict(input);
        double real = classLabels.get(i);

        System.out.println("Prediction: " + prediction + " Real: " + real);

        if (prediction == real) {
            correct++;
        }
    }
    return (double) correct / total;
}

* This method calculates the accuracy of the perceptron on a test set. It compares the predicted output with the actual class labels and counts the number of correct predictions.

private void initializeWeightsAndBias(){
    Random random = new Random();
    for(int i = 0; i < weights.length; i++){
        weights[i] = random.nextDouble();
    }
    bias = random.nextDouble();
}

* This method initializes the weights and bias of the perceptron with random values. It uses Java's Random class to generate random numbers for weights and bias.
}
