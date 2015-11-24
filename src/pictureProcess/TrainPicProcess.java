package pictureProcess;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

public class TrainPicProcess extends Process
{

	public TrainPicProcess(String FilePath) 
	{
		super(FilePath);
	}
	
	public boolean process() throws Exception
	{
		File dir = new File(FilePath);
		File dataFile = new File("data"+File.separator+"TrainData");
		int notProcess = 0;
		if(dir.exists() && dir.isDirectory())
		{			
			if(!dataFile.exists())
				dataFile.mkdir();
			File[] files = dir.listFiles();
			for (File img : files)
			{
				RecPicProcess temp = new RecPicProcess(img.getAbsolutePath());
				String name = img.getName();
				if(name.endsWith(".jpg"))
				{	
					name = name.substring(0,name.lastIndexOf("."));
					List<BufferedImage> chars = temp.process();
					if(chars.size()==name.length())
					{
						for(int i=0;i<chars.size();i++)
						{
							File tempDir = new File(dataFile.getAbsoluteFile()+File.separator+charToClass(name.charAt(i)));
							if(!tempDir.exists())
								tempDir.mkdir();
							int num = tempDir.listFiles().length;
							File tempImg = new File(tempDir+File.separator+num+".JPG");
							ImageIO.write(chars.get(i), "JPG", tempImg);
						}
					}
					else
					{
						notProcess++;
						System.out.println(notProcess+" "+name);
					}
				}
			}
		}
		else
		{
			return false;
		}
		System.out.println(notProcess);
		return true;
	}
	
	private int charToClass(char i)
	{
		if(Character.isDigit(i))
			return i-47;
		else if(Character.isUpperCase(i))
		{
			return (i-'A'+11);
		}
		else
		{
			return (i-'a'+11);
		}
					
	}
	

}
