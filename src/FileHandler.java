import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private List<String> classNames;
    public FileHandler() {
        classNames = new ArrayList<>();
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
            classNames.add(values[values.length - 1]);
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

    public List<String> getClassNames() {
        return classNames;
    }
}
