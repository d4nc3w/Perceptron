import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String trainSetFile = "./res/perceptron.data";
        String testSetFile = "./res/perceptron.test.data";
        double learningRate = 0.01;

        FileHandler fh = new FileHandler();

        try {
            List<double[]> trainSet = fh.loadTrainSet(trainSetFile);
            List<double[]> testSet = fh.loadTestSet(testSetFile);

            Perceptron perceptron = new Perceptron(FileHandler.getNumInputs(trainSetFile), learningRate);
            perceptron.train(trainSet, 10);
            double accuracy = perceptron.accuracy(testSet, fh.getClassNames());

            System.out.println("Accuracy for this model: " + accuracy);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
