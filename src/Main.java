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
            List<Double> classLabels = fh.getClassLabels();
            List<Double> classTrainLabels = fh.getClassTrainLabels();

            Perceptron perceptron = new Perceptron(FileHandler.getNumInputs(trainSetFile), learningRate);
            perceptron.train(trainSet, classTrainLabels,500);
            double accuracy = perceptron.accuracy(testSet, classLabels);

            System.out.println("Accuracy for this model: " + accuracy);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
