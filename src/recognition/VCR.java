package recognition;

import java.awt.image.BufferedImage;
import java.util.List;

import pictureProcess.RecPicProcess;
import svm.svm_predict;

public class VCR 
{
	BufferedImage code;
	
	public VCR(BufferedImage code)
	{	
		this.code = code;
	}
	
	public String recognate() throws Exception
	{
		RecPicProcess rpp = new RecPicProcess(code);
		List<BufferedImage> result = rpp.process();
		svm_predict.main(null);
		
		return ""	;
	}
	

}
