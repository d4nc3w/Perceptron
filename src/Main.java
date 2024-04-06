import java.io.IOException;
import java.util.List;
import java.util.Scanner;

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

            simpleUI(perceptron);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void simpleUI(Perceptron perceptron){
        System.out.println("--------Perceptron Program--------");
        System.out.println("Do you want to classify your own vector? (y/n): ");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();
        if (choice.equals("y")){
            System.out.println("Enter values of your vector format=(x.x,y.y,w.w,z.z): ");
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();

            String[] inputValues = input.split(",");
            double[] vector = new double[inputValues.length];
            for (int i = 0; i < inputValues.length; i++) {
                vector[i] = Double.parseDouble(inputValues[i].trim());
            }

            double prediction = perceptron.predict(vector);
            System.out.println("Predicted class: " + prediction);
            String className = prediction == 1 ? "Iris-versicolor" : "Iris-virginica";
            System.out.println("For perceptron.test.data: " + className);

        }
        else if (choice.equals("n")) {
            System.out.println("Goodbye!");
        }
        else {
            System.out.println("Wrong input.");
        }
    }
}
