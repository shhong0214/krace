package kr.co.krace.util;


import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;


public class ImageFile {
	
	 @Value("#{kraceProperties['upload.path']}")
	 private String uploadPath;

	
//	/**
//	 * 업로드한 이미지 파일이 저장될 경로
//	 */
//	public static final String IMAGE_DIR = uploadPath;

	private String id;
	private String contentType;
	private int contentLength;
	private String fileName;

	public ImageFile(String id, String contentType, int contentLength,
			String fileName) {
		this.id = id;
		this.contentType = contentType;
		this.contentLength = contentLength;
		this.fileName = fileName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public int getContentLength() {
		return contentLength;
	}

	public void setContentLength(int contentLength) {
		this.contentLength = contentLength;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public static void getImageFromUrl(String imgUrl, String imgFilePath, String imgFormat)
	{
        try
        {
            // Image 가져오기
            BufferedImage image = ImageIO.read(new URL(imgUrl));
                        
            // Image 저장할 파일
            File imgFile = new File(imgFilePath);
            
            // Image 저장
            ImageIO.write(image, imgFormat, imgFile);
        }
        catch (Exception e)
        {
        }
	}

	
}

