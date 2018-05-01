package kr.co.krace.util;


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

@Component("imageView")
public class ImageView extends AbstractView{
	@Value("#{kraceProperties['upload.path']}")
	 private String uploadPath;
	
	@Override
	protected void renderMergedOutputModel(Map model,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		ImageFile imageFile = (ImageFile)model.get("imageFile");
		
		// 응답 메시지에 파일의 길이를 넘겨줍니다.
		res.setContentLength(imageFile.getContentLength());

		// 응답의 타입이 이미지임을 알려줍니다.
		res.setContentType(imageFile.getContentType());
		
		// 파일로부터 byte를 읽어옵니다.
		byte[] bytes = readFile(imageFile.getFileName());
		write(res, bytes);
	}

	/**
	 * 파일로부터 byte 배열 읽어오기 
	 */
	private byte[] readFile(String fileName) throws IOException {
		String path = uploadPath + fileName;
		
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path));
		int length = bis.available();
		byte[] bytes = new byte[length];
		bis.read(bytes);
		bis.close();
		
		return bytes;
	}

	/**
	 * 응답 OutputStream에 파일 내용 쓰기
	 */
	private void write(HttpServletResponse res, byte[] bytes) throws IOException {
		OutputStream output = res.getOutputStream();
		output.write(bytes);
		output.flush();
	}
}



