import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    public List<double[]> loadTrainSet(String file) throws IOException {
        List<double[]> trainSet = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;

        while((line = reader.readLine()) != null){
            String[] values = line.split(",");
            double[] input = new double[values.length];
            for(int i = 0; i < values.length; i++){
                input[i] = Double.parseDouble(values[i]);
            }
            trainSet.add(input);
        }
        reader.close();
        return trainSet;
    }

    public List<double[]> loadTestSet(String file) throws IOException{
        List<double[]> testSet = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] values = line.split(",");
            double[] input = new double[values.length];
            for (int i = 0; i < values.length; i++) {
                input[i] = Double.parseDouble(values[i]);
            }
            testSet.add(input);
        }
        reader.close();
        return testSet;
    }
}
