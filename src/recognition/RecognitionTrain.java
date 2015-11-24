package recognition;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import svmTrain.ProcessData;
import svmTrain.TrainData;

public class RecognitionTrain 
{
	private double[] paraG;
	private double[] paraC;
	
	
	public RecognitionTrain(double[] paraG, double[] paraC )
	{
		this.paraC = paraC;
		this.paraG = paraG;
	}
	
	
	public void train() throws Exception
	{	
		List<String[]> para = new ArrayList<String[]>();
		
		File svmData = new File("data"+File.separator+"SVMResult.txt");
		if(!svmData.exists())
			processData();
		
		for(int i=0;i<paraC.length;i++)
		{
			for(int j=0;j<paraG.length;j++)
			{
				String[] temp = new String[8];
				temp[0] = "-v";
				temp[1] = "10";
				temp[2] = "-g";
				temp[3] = String.valueOf(paraG[j]);
				temp[4] = "-c";
				temp[5] = String.valueOf(paraC[i]);
				temp[6] = svmData.getAbsolutePath();
				temp[7] = "data"+File.separator+"output.txt.model";
				para.add(temp);
			}
		}
		

		TrainData td = new TrainData();
		td.TrainModel(para);

		
	}
	
	private boolean processData() throws Exception
	{
		ProcessData pd = new ProcessData();
		pd.scaleTraindata();
		return true;
	}

	public double[] getParaG() {
		return paraG;
	}

	public void setParaG(double[] paraG) {
		this.paraG = paraG;
	}

	public double[] getParaC() {
		return paraC;
	}

	public void setParaC(double[] paraC) {
		this.paraC = paraC;
	}


}
