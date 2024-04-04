import java.util.List;
import java.util.Random;

public class Perceptron {
    private double bias;
    private double[] weights;
    private double learnRate;

    public Perceptron(int InputNum, double learnRate){
        this.learnRate = learnRate;
        this.weights = new double[InputNum];
        initializeWeightsAndBias();
    }

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

    public double predict(double[] input){
        double net = bias;
        for(int i = 0; i < input.length - 1; i++){
            net += input[i] * weights[i];
        }

        net += bias;

        //activation function (step fun.)
        return net >= 0 ? 1 : 0;

        //activation function (sign function)
        //double activation =  sum > 0 ? 1 : -1;

        //activation function (sigmoid function)
        //double activation = Math.signum(sum);
    }

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

    private void initializeWeightsAndBias(){
        Random random = new Random();
        for(int i = 0; i < weights.length; i++){
            weights[i] = random.nextDouble();
        }
        bias = random.nextDouble();
    }
}
