import java.io.IOException;
import java.util.List;

public class Main {
    private double[] weights;
    private double bias;
    private double learningRate;
    private List<double[]> trainingSet;

    public static void main(String[] args) {
        String trainSetFile = "perceptron.data";
        String testSetFile = "perceptron.test.data";
        double learningRate = 0.01;

        FileHandler fh = new FileHandler();
        try {
            List<double[]> trainSet = fh.loadTrainSet(trainSetFile);
            List<double[]> testSet = fh.loadTestSet(testSetFile);

            Perceptron perceptron = new Perceptron(FileHandler.getNumInputs(trainSetFile), learningRate);
            perceptron.train(trainSet);

        } catch (IOException e) {
            System.out.println("There is an error while loading file");
        }
    }
}
