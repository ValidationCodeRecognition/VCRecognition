package recognition;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import svmTrain.TrainData;

public class test {

	public static void main(String[] args) throws Exception {
//		String input_file = new File("data/SVMResult.txt").getAbsolutePath();
//		String output_file = new File("data/output.txt.model").getAbsolutePath();
//		String kfold = "10";
//		String cvMode = "-v";
//		String[] crossValidationTrainArgs = {cvMode, kfold, "-g", "0.002", "-c", "0.04", input_file, output_file};
//		List<String[]> list = new ArrayList<>();
//		list.add(crossValidationTrainArgs);
//		list.add(new String[] {cvMode, kfold, "-g", "0.0002", "-c", "0.04", input_file, output_file});
//		TrainData tm = new TrainData();
//		tm.TrainModel(list);
		int[] exp = {-4,-3,-2,-1,0,1,2,3,4,5};
		double[] paraC = new double[10];
		double[] paraG = new double[10];
		for(int i=0;i<exp.length;i++)
		{
			paraC[i] = Math.pow(2, exp[i]);
			paraG[i] = Math.pow(2, exp[i]);
		}
		
		RecognitionTest train = new RecognitionTest(paraG,paraC);
		train.train();
	}
	
}
