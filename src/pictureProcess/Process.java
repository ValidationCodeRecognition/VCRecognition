package pictureProcess;

import java.awt.Color;
import java.awt.image.BufferedImage;

public abstract class Process 
{
	String FilePath;
	BufferedImage pic;
	
	public String getFilePath() 
	{
		return FilePath;
	}
	
	public Process(BufferedImage pic)
	{
		this.pic = pic;
	}
	
	protected boolean isBlack(int colorInt)
	{
		Color color = new Color(colorInt);
		if (color.getRed()<=60 && color.getGreen()<=60 && color.getBlue()<=60) 
		{
			return true;
		}
		else
			return false;
	}
	
	protected BufferedImage copyImg(BufferedImage i1)
	{
		int width = i1.getWidth();
		int height = i1.getHeight();
		BufferedImage i2 = new BufferedImage(width,height,i1.getType());

		for (int x = 0; x < width; x++) 
		{
			for (int y = 0; y < height; y++) 
			{
				i2.setRGB(x, y, i1.getRGB(x, y));
			}
		}
		return i2;
	}


	public void setFilePath(String filePath) {
		FilePath = filePath;
	}

	public Process(String FilePath)
	{
		this.FilePath = FilePath;
	}
	

}
