package recognition;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import svmTrain.TrainData;

public class test {

	public static void main(String[] args) throws Exception {
		String input_file = new File("data/SVMResult.txt").getAbsolutePath();
		String output_file = new File("data/output.txt.model").getAbsolutePath();
		String kfold = "10";
		String cvMode = "-v";
		String[] crossValidationTrainArgs = {cvMode, kfold, "-g", "0.002", "-c", "0.04", input_file, output_file};
		List<String[]> list = new ArrayList<>();
		list.add(crossValidationTrainArgs);
		list.add(new String[] {cvMode, kfold, "-g", "0.0002", "-c", "0.04", input_file, output_file});
		TrainData tm = new TrainData();
		tm.TrainModel(list);
	}
	
}
