import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String trainSetFile = "perceptron.data";
        String testSetFile = "perceptron.test.data";

        FileHandler fh = new FileHandler();
        try {
            List<double[]> trainSet = fh.loadTrainSet(trainSetFile);
        } catch (IOException e) {
            System.out.println("There is an error while loading file");
        }
    }
}
