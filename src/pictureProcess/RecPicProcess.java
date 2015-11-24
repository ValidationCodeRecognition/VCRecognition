package pictureProcess;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.imageio.ImageIO;


public class RecPicProcess extends Process
{
	
	public RecPicProcess(String FilePath)
	{
		super(FilePath);
	}
	
	public RecPicProcess(BufferedImage pic)
	{
		super(pic);
	}
	
	public List<BufferedImage> process() throws Exception
	{
		List<BufferedImage> result = new ArrayList<BufferedImage>();
		BufferedImage code = null;
		if(this.FilePath!=null && !this.FilePath.isEmpty())
			code = importImage();
		else
			code = this.pic;
		if(code!=null)
		{
			code = backgroundProcess(code);
			//ImageIO.write(code, "JPG", new File("data"+File.separator+"code.JPG"));
			for(int x=0;x<code.getWidth();x++)
			{
				for(int y=0;y<code.getHeight();y++)
				{
					if(isBlack(code.getRGB(x, y)))
					{
						BufferedImage tempImage = charSearch(x,y,code);
						if(tempImage!=null)
							result.add(tempImage);
					}
				}
			}
		}
		return result;
	}
	
	private BufferedImage charSearch(int x,int y,BufferedImage img) throws IOException
	{
		int hx=x;
		int lx=x;
		int hy=y;
		int ly=y;
		int width = img.getWidth();
		int height = img.getHeight();
		BufferedImage originalImg = copyImg(img);
		Queue<int[]> queue = new LinkedList<int[]>();
		int[] firstPoint = {x,y};
		queue.offer(firstPoint);
		while(!queue.isEmpty())
		{
			int[] tempPoint = queue.poll();
			int tempX = tempPoint[0];
			int tempY = tempPoint[1];
			img.setRGB(tempX, tempY, Color.WHITE.getRGB());
			
			if(tempX-1>=0 && isBlack(img.getRGB(tempX-1, tempY)))
				queue.offer(new int[]{tempX-1,tempY});
			if(tempX+1<width && isBlack(img.getRGB(tempX+1, tempY)))
				queue.offer(new int[]{tempX+1,tempY});
			if(tempY-1>=0 && isBlack(img.getRGB(tempX, tempY-1)))
				queue.offer(new int[]{tempX,tempY-1});
			if(tempY+1<height && isBlack(img.getRGB(tempX, tempY+1)))
				queue.offer(new int[]{tempX,tempY+1});
			if(tempX-1>=0 && tempY-1>=0 && isBlack(img.getRGB(tempX-1, tempY-1)))
				queue.offer(new int[]{tempX-1,tempY-1});
			if(tempX+1<width && tempY+1<height && isBlack(img.getRGB(tempX+1, tempY+1)))
				queue.offer(new int[]{tempX+1,tempY+1});
			if(tempX+1<width && tempY-1>=0 && isBlack(img.getRGB(tempX+1, tempY-1)))
				queue.offer(new int[]{tempX+1,tempY-1});
			if(tempX-1>=0 && tempY+1<height && isBlack(img.getRGB(tempX-1, tempY+1)))
				queue.offer(new int[]{tempX-1,tempY+1});
			
			hx = Math.max(hx,tempX);
			lx = Math.min(lx, tempX);
			hy = Math.max(hy, tempY);
			ly = Math.min(ly, tempY);
		}
		if((hx-lx)>3 && (hy-ly)>3)
			return originalImg.getSubimage(lx, ly, hx-lx+1, hy-ly+1);
		else
			return null;
	}
	
	
	public BufferedImage backgroundProcess(BufferedImage img)
	{
		int width = img.getWidth();
		int height = img.getHeight();
		for (int x = 0; x < width; x++) 
		{
			for (int y = 0; y < height; y++) 
			{
				int tempColor = img.getRGB(x, y);
				if(!isBlack(tempColor))
				{
					img.setRGB(x, y, Color.WHITE.getRGB());	
				}
			}
		}
		return img;
	}
	

	private BufferedImage importImage() throws Exception
	{
		File img = new File(FilePath);
		if(img.exists() && img.isFile())
			return ImageIO.read(new File(FilePath));
		else
			return null;
	}
}
