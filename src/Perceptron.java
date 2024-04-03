import java.util.Arrays;
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

    public void train(List<double[]> trainSet, int numIterations) {
        this.trainingSet = trainSet;
        for (int iteration = 0; iteration < numIterations; iteration++) {
            for (double[] input : trainingSet) {
                String prediction = predict(input);
                double predictionValue =  prediction.equals("Iris-versicolor") ? 0 : 1;
                double real = input[input.length - 1];
                double error = real - predictionValue;

                for (int i = 0; i < weights.length; i++) {
                    weights[i] += learnRate * error * input[i];
                }
                bias += learnRate * error;
            }
        }
    }


    public String predict(double[] input){
        double sum = bias;
        for(int i = 0; i < input.length - 1; i++){
            sum += input[i] * weights[i];
        }
        sum += bias;

        //activation function (step fun.)
        double activation =  sum >= 0 ? 1 : 0;

        //activation function (sign function)
        //double activation =  sum > 0 ? 1 : -1;

        //activation function (sigmoid function)
        //double activation = Math.signum(sum);

        if (activation == 0)
            return "Iris-versicolor";
        else
            return "Iris-virginica";
    }

    public double accuracy(List<double[]> testSet, List<String> classNames){
        int correct = 0;
        int total = testSet.size();

        for (int i = 0; i < total; i++) {
            double[] input = testSet.get(i);
            String prediction = predict(input);
            String real = classNames.get(i);

            System.out.println("Prediction: " + prediction + " Real: " + real);

            if(prediction.equals(real)){
                correct++;
            }
        }
        double acc = (double) correct / total;
        return acc;
    }

    private void initializeWeights(){
        Random random = new Random();
        for(int i = 0; i < weights.length; i++){
            weights[i] = random.nextDouble();
        }
    }
}
