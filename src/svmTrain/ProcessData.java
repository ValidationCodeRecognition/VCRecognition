package svmTrain;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
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
	
	public void scaleTraindata() throws Exception {
		File dir = new File("testData");
		File[] files = dir.listFiles();
		for (File file : files) {
			if(!file.getName().endsWith(".jpg"))
				continue;
			BufferedImage img = ImageIO.read(file);
			ScaleFilter sf = new ScaleFilter(16, 16);
			BufferedImage imgdest = new BufferedImage(16, 16,ImageIO.read(file).getType());
			imgdest = sf.filter(img, imgdest);
			ImageIO.write(imgdest, "JPG", new File("data/" + file.getName()));
		}
	}
	
	public void getSVMData(String name) throws Exception{
		File dir = new File("data");
		File data = new File(name);
		FileOutputStream fs = new FileOutputStream(data);
		File[] files = dir.listFiles();
		for(File file:files){
			if(!file.getName().endsWith(".jpg")){
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
