package recognition;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

import pictureProcess.RecPicProcess;
import svm.svm_predict;
import svmTrain.ProcessData;

public class VCR 
{
	BufferedImage code;
	
	public VCR(BufferedImage code)
	{	
		this.code = code;
	}
	
	public String recoginate() throws Exception
	{
		RecPicProcess rpp = new RecPicProcess(code);
		List<BufferedImage> result = rpp.process();
		ProcessData pd = new ProcessData();
		String input = pd.scalePredictBufferedImage(result);
		File model = new File("data"+File.separator+"output.txt.model");
		File output = new File("data"+File.separator+"recogination.txt");
		svm_predict.main(new String[] {input,model.getAbsolutePath(),output.getAbsolutePath()});
		BufferedReader r = new BufferedReader(new FileReader(output.getAbsolutePath()));
		String recogination = "";
		StringBuilder sb = new StringBuilder();
		while((recogination = r.readLine())!=null)
		{
			int tempClass = Integer.parseInt(recogination.split("\\.")[0]);
			sb.append(transClass2Char(tempClass));
		}
		r.close();
		return sb.toString()	;
	}
	
	private char transClass2Char(int tempClass)
	{
		if(tempClass<11)
		{
			return (char) (tempClass+47);
		}
		else
		{
			return (char) (tempClass+'A'-11);
		}
	}

}
