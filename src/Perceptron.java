import java.util.List;
import java.util.Random;

public class Perceptron {
    private double bias;
    private double[] weights;
    private List<double[]> trainingSet;
    private double learnRate;

    public Perceptron(int InputNum, double learnRate){
        this.learnRate = learnRate;
        this.weights = new double[InputNum];
        this.bias = 0;
        initializeWeights();
    }

    public void train(List<double[]> trainSet, int repeat) {
        this.trainingSet = trainSet;
        for (int r = 0; r < repeat; r++) {
            for (double[] input : trainingSet) {
                double prediction = predict(input);
                double real = input[input.length - 1];
                double error = real - prediction;

                for (int i = 0; i < weights.length; i++) {
                    weights[i] += learnRate * error * input[i];
                }
                bias += learnRate * error;
            }
        }
    }

    public double predict(double[] input){
        double sum = 0;
        for(int i = 0; i < input.length - 1; i++){
            sum += input[i] * weights[i];
        }
        sum += bias;

        //activation function (step fun.)
        //return sum > 0 ? 1 : 0;

        //activation function (sign function)
        //return sum > 0 ? 1 : -1;

        //activation function (sigmoid function)
        return Math.signum(sum);
    }

    public double accuracy(List<double[]> testSet){
        int correct = 0;
        int total = 0;
        for(double[] input : testSet){
            double prediction = predict(input);
            double real = input[input.length - 1];

            System.out.println("Prediction: " + prediction + ", Real: " + real);

            if(prediction == real){
                correct++;
            }
            total++;
        }
        double acc = (double) correct/total;
        return acc;
    }

    private void initializeWeights(){
        Random random = new Random();
        for(int i = 0; i < weights.length; i++){
            weights[i] = random.nextDouble();
        }
    }
}
