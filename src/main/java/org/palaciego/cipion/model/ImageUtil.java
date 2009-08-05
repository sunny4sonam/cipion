package org.palaciego.cipion.model;

import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.PixelGrabber;
import java.awt.image.ReplicateScaleFilter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImageUtil {

	/**
	 * Escala el tamaño de la imagen a un tamaño aecuado para guardar en base de
	 * datos
	 * 
	 * @param image
	 * @param maxWidth
	 * @param maxHeight
	 */
	public static byte[] resizeImage(byte[] image, int maxWidth,
			int maxHeight) {
		try {
			ByteArrayInputStream bais=new ByteArrayInputStream(image);
			BufferedImage bi = ImageIO.read(bais);
			if(bi!=null)
			{
				int width = bi.getWidth();
				int height = bi.getHeight();
				if (width > maxWidth || height > maxHeight) {
					int newWidth = 0;
					int newHeight = 0;
					if (width > height) {
						// dejamos el width al máximo
						newWidth = maxWidth;
						newHeight = ((height * newWidth) / width);
					} else {
						// dejamos el height al máximo
						newHeight = maxHeight;
						newWidth = ((width * newHeight) / height);
					}

					ImageFilter replicate = new ReplicateScaleFilter(newWidth,
							newHeight);
					ImageProducer prod = new FilteredImageSource(bi.getSource(),
							replicate);

					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					BufferedImage biresult=createImage(prod);
					ImageIO.write(biresult, "png", baos);
					return baos.toByteArray();
				}
			}
			else
			{
				//no es una imagen
				return null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
		
		return image;
	}

	/**
	 * Cretae a BufferedImage from an ImageProducer.
	 * 
	 * @param producer
	 *            the ImageProducer
	 * @return a new TYPE_INT_ARGB BufferedImage
	 */
	public static BufferedImage createImage(ImageProducer producer) {
		PixelGrabber pg = new PixelGrabber(producer, 0, 0, -1, -1, null, 0, 0);
		try {
			pg.grabPixels();
		} catch (InterruptedException e) {
			throw new RuntimeException("Image fetch interrupted");
		}
		if ((pg.status() & ImageObserver.ABORT) != 0)
			throw new RuntimeException("Image fetch aborted");
		if ((pg.status() & ImageObserver.ERROR) != 0)
			throw new RuntimeException("Image fetch error");
		BufferedImage p = new BufferedImage(pg.getWidth(), pg.getHeight(),
				BufferedImage.TYPE_INT_ARGB);
		p.setRGB(0, 0, pg.getWidth(), pg.getHeight(), (int[]) pg.getPixels(),
				0, pg.getWidth());
		return p;
	}
}
