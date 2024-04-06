import java.io.IOException;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Random;
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
            System.out.println("----------Training----------");
            double accuracy = perceptron.accuracy(testSet, classLabels);

            System.out.println("-------------------------------");
            System.out.println("Accuracy for this model: " + accuracy);

            simpleUI(perceptron, trainSetFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void simpleUI(Perceptron perceptron, String trainSetFile) throws IOException {
        System.out.println("------------------MENU------------------");
        System.out.println("(1) Classify another vector");
        System.out.println("(2) Exit");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        if (choice == 1) {
            System.out.println("(1) Generate random");
            System.out.println("(2) Type data yourself");
            Scanner c = new Scanner(System.in);
            int input = c.nextInt();

            if (input == 1) { //Random generation of data
                Random random = new Random();
                int numInputs = FileHandler.getNumInputs(trainSetFile);
                double[] vector = new double[numInputs];
                for (int i = 0; i < numInputs; i++) {
                    double randomValue = Math.round(random.nextDouble() * 100) / 100.0;
                    vector[i] = randomValue;
                }
                System.out.println("Generated vector: ");
                for(int i = 0; i < vector.length; i++){
                    System.out.print("[" + vector[i] + "] ");
                }
                System.out.println();

                double prediction = perceptron.predict(vector);
                System.out.println("Predicted class: " + prediction);
                String className = prediction == 1.0 ? "Iris-versicolor" : "Iris-virginica";
                System.out.println("For perceptron.test.data: " + className);
                simpleUI(perceptron, trainSetFile);

            } else if (input == 2) { //Typing data yourself
                System.out.println("Enter values of your vector format=(x.x,y.y,w.w,z.z): ");
                Scanner sc = new Scanner(System.in);
                String insert = sc.nextLine();

                String[] inputValues = insert.split(",");
                double[] vector = new double[inputValues.length];
                for (int i = 0; i < inputValues.length; i++) {
                    vector[i] = Double.parseDouble(inputValues[i].trim());
                }
                double prediction = perceptron.predict(vector);
                System.out.println("Predicted class: " + prediction);
                String className = prediction == 1.0 ? "Iris-versicolor" : "Iris-virginica";
                System.out.println("For perceptron.test.data: " + className);
                simpleUI(perceptron, trainSetFile);
            } else {
                System.out.println("Invalid choice.");
            }
        } else if (choice == 2) {
            System.out.println("Closing the program...");
            System.out.println("----------------------------------------");
        } else {
            System.out.println("Wrong input.");
        }
    }
}
