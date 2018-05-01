package kr.co.krace.util;

import java.io.IOException;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsoupUtil {

	public static Document post(String connUrl, Map<String, String> param) throws IOException {
		// 2. HTML 가져오기
        Connection conn = Jsoup
                .connect(connUrl)
                .header("Content-Type", "application/json;charset=UTF-8")
                .method(Connection.Method.POST)
                .data(param)
                .ignoreContentType(true);
        
        Document doc = conn.get();
        
        return doc;	
	}
	
	public static Document get(String connUrl, Map<String, String> param) throws IOException {
		// 2. HTML 가져오기
        Connection conn = Jsoup
                .connect(connUrl)
                .header("Content-Type", "application/json;charset=UTF-8")
                .method(Connection.Method.GET)
                .data(param)
                .ignoreContentType(true);
        
        Document doc = conn.get();
        
        return doc;	
	}
	
	
}
