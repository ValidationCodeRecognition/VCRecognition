package recognition;

import java.util.ArrayList;
import java.util.List;

import svmTrain.TrainData;

public class RecognitionTest 
{
	private double[] paraG;
	private double[] paraC;
	
	
	public RecognitionTest(double[] paraG, double[] paraC )
	{
		this.paraC = paraC;
		this.paraG = paraG;
	}
	
	public boolean train()
	{	
		List<String[]> para = new ArrayList<String[]>();
		
		for(int i=0;i<paraC.length;i++)
		{
			for(int j=0;j<paraG.length;j++)
			{
				String[] temp = new String[4];
				temp[0] = "-g";
				temp[1] = String.valueOf(paraG[j]);
				temp[2] = "-c";
				temp[4] = String.valueOf(paraC[i]);
				para.add(temp);
			}
		}
		
		try
		{
			
			TrainData td = new TrainData();
			td.TrainModel(para);
			
		}catch(Exception e)
		{
			return false;
		}
		
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
