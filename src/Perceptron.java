import java.util.List;

public class Perceptron {
    private double bias;
    private double[] weights;
    private List<double[]> trainingSet;
    private double learnRate;

    public Perceptron(int InputNum, double learnRate){
        this.learnRate = learnRate;
        this.weights = new double[InputNum];
        this.bias = 0;
    }

    public void train(List<double[]> trainSet){
        this.trainingSet = trainSet;
        for(double[] input : trainingSet){
            double prediction = predict(input);
            double real = input[input.length - 1];
            double error = real - prediction;

            for(int i = 0; i < weights.length; i++){
                weights[i] += learnRate * error * input[i];
            }
            bias += learnRate * error;
        }
    }

    public double predict(double[] input){
        double sum = 0;
        for(int i = 0; i < input.length - 1; i++){
            sum += input[i] * weights[i];
        }
        sum += bias;
        //activation function (step fun.)
        return sum > 0 ? 1 : 0;
    }

    public double accuracy(List<double[]> testSet){
        int correct = 0;
        int total = 0;
        for(double[] input : testSet){
            double prediction = predict(input);
            double real = input[input.length - 1];
            if(prediction == real){
                correct++;
            }
            total++;
        }
        double acc = (double) correct/total;
        return acc;
    }
}
