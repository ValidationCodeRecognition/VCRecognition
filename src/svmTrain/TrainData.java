package svmTrain;

import java.io.File;
import java.util.List;

import svm.svm_train;

public class TrainData {
	
	public TrainData(){
		
	}
	
	public void TrainModel(List<String[]> para) throws Exception{
		ProcessData pd = new ProcessData();
		pd.scaleTraindata();
		pd.getSVMData("data"+File.separator+"SVMResult.txt");
		CrossValidation cv = new CrossValidation();
		String[] tmp = cv.getBestParam(para);
		String [] bestPara = new String[tmp.length - 2];
		for(int i=0;i<tmp.length-2;i++){
			bestPara[i] = tmp[i+2];
		}
		svm_train.main(bestPara);
	}
}
