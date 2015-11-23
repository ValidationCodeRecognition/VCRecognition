package svmTrain;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.jhlabs.image.ScaleFilter;
import javax.imageio.ImageIO;

public class ProcessData {
	
	public ProcessData(){
		
	}
	
	private int isBlack(int colorInt){
		Color color = new Color(colorInt);
		if(color.getRed()<=60 && color.getGreen()<=60 && color.getBlue()<=60){
			return 1;
		}else{
			return 0;
		}
	}
	
	public void scalePredictBufferedImage(BufferedImage img) throws IOException{
		ScaleFilter sf = new ScaleFilter(10, 10);
		BufferedImage imgdest = new BufferedImage(10, 10,img.getType());
		imgdest = sf.filter(img, imgdest);
		File data = new File("data"+File.separator+"predictSVMData");
		FileOutputStream fs = new FileOutputStream(data);
		fs.write(("predictData").getBytes());
		int index = 1;
		for(int i=0;i<imgdest.getWidth();i++){
			for(int j=0;j<imgdest.getHeight();j++){
				fs.write((index++ + ":" + isBlack(imgdest.getRGB(i, j)) + " ").getBytes());
			}
		}
		fs.write("\r\n".getBytes());
		fs.close();
	}
	
	public void scaleTraindata() throws Exception {
		File dataFile = new File("data"+File.separator+"TestData");
		File[] dirs = dataFile.listFiles();
		for(File dir : dirs){
			if(dir.getName().charAt(0) == '.')
				continue;
			System.out.println(dir.getName());
			File[] files = dir.listFiles();
			for (File file : files) {
				if(!file.getName().endsWith(".JPG"))
					continue;
				BufferedImage img = ImageIO.read(file);
				ScaleFilter sf = new ScaleFilter(10, 10);
				BufferedImage imgdest = new BufferedImage(10, 10,ImageIO.read(file).getType());
				imgdest = sf.filter(img, imgdest);
				ImageIO.write(imgdest, "JPG", new File("scaleData" + File.separator + dir.getName() + "-" + file.getName()));
			}
		}
		
	}
	
	public void getSVMData(String name) throws Exception{
		File dir = new File("scaleData");
		File data = new File(name);
		FileOutputStream fs = new FileOutputStream(data);
		File[] files = dir.listFiles();
		for(File file:files){
			if(!file.getName().endsWith(".JPG")){
				continue;
			}else{
				BufferedImage img = ImageIO.read(file);
				fs.write((file.getName().charAt(0)+" ").getBytes());
				int index = 1;
				for(int i=0;i<img.getWidth();i++){
					for(int j=0;j<img.getHeight();j++){
						fs.write((index++ + ":" + isBlack(img.getRGB(i, j)) + " ").getBytes());
					}
				}
			}
			fs.write("\r\n".getBytes());
		}
		fs.close();
	}
}
