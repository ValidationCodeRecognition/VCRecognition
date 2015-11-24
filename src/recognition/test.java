package recognition;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;


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
		
//		int[] exp = {-4,-3,-2,-1,0,1,2,3,4,5};
//		double[] paraC = new double[10];
//		double[] paraG = new double[10];
//		
//		for(int i=0;i<exp.length;i++)
//		{
//			paraC[i] = Math.pow(2, exp[i]);
//			paraG[i] = Math.pow(2, exp[i]);
//		}
////		double[] paraG = {0.0625};
////		double[] paraC = {0.5};
//		RecognitionTrain train = new RecognitionTrain(paraG,paraC);
//		train.train();
//		
		int correct = 0;
		File testData = new File("data"+File.separator+"TestData");
		File[] codeList = testData.listFiles();
		for(File code : codeList)
		{
			BufferedImage test = ImageIO.read(code);
			VCR r = new VCR(test);
			String recogination = r.recoginate();
			if(code.getName().split("\\.")[0].equals(recogination))
				correct++;
			System.out.println(recogination);
		}
		System.out.println(correct);
		System.out.println(codeList.length);
		double rate = (double) correct / codeList.length;
		System.out.println(rate);
		
	}
	
}
