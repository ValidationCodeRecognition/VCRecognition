package svmTrain;

import svm.svm_train;

import java.io.*;
import java.util.*;

public class CrossValidation {
	
	public CrossValidation(){
		
	}
	
	public String[] getBestParam(List<String[]> para) throws IOException{
		double max = 0;
		int tmp = -1;
		for(int i=0;i<para.size();i++){
			String[] crossValidationTrainArgs = para.get(i);
			svm_train.main(crossValidationTrainArgs);
		}
		
		List<Double> CVResult = svm_train.CVResult;
		for(int i=0;i<CVResult.size();i++){
			System.out.println("crossvalidation result"+ CVResult.get(i));
			if(max < CVResult.get(i)){
				max = CVResult.get(i);
				tmp = i;
			}
		}
		
		if(tmp == -1){
			return null;
		}
		
		return para.get(tmp);
	}
}
