import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private List<Double> classLabels;
    private List<Double> classTrainLabels;


    public FileHandler() {
        classLabels = new ArrayList<>();
        classTrainLabels = new ArrayList<>();
    }

    public List<double[]> loadTrainSet(String file) throws IOException {
        List<double[]> trainSet = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] values = line.split(",");
            double[] input = new double[values.length - 1];
            for (int i = 0; i < values.length - 1; i++) {
                input[i] = Double.parseDouble(values[i]);
            }
            trainSet.add(input);
            double classNameDouble = values[values.length - 1].equals("Iris-versicolor") ? 1.0 : 0.0;
            classTrainLabels.add(classNameDouble);
        }
        reader.close();
        return trainSet;
    }


    public List<double[]> loadTestSet(String file) throws IOException {
        List<double[]> testSet = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] values = line.split(",");
            double[] input = new double[values.length - 1];
            for (int i = 0; i < values.length - 1; i++) {
                input[i] = Double.parseDouble(values[i]);
            }
            testSet.add(input);
            double classNameDouble = values[values.length - 1].equals("Iris-versicolor") ? 1.0 : 0.0;
            classLabels.add(classNameDouble);
        }
        reader.close();
        return testSet;
    }

    public static int getNumInputs(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line = reader.readLine();
        reader.close();
        return line.split(",").length - 1;
    }

    public List<Double> getClassLabels() {
        return classLabels;
    }

    public List<Double> getClassTrainLabels(){
        return classTrainLabels;
    }
}
