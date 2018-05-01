package kr.co.krace.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import kr.co.krace.common.KRaceConstants;
import kr.co.krace.util.JsoupUtil;
import kr.co.krace.vo.HorseOwnerVO;

@Service
public class AdminService {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	
	public ArrayList<HorseOwnerVO> getHorseOwnerList(String meet) throws IOException, ParseException {
		ArrayList<HorseOwnerVO> list = new ArrayList<HorseOwnerVO>();
		
		Map<String, String> param = new HashMap<String, String>();
        param.put("meet", meet);
		
        Document doc = JsoupUtil.post(KRaceConstants.UPDATE_HORSEOWNERLIST_LIST, param);
		
        Element table = doc.select("table tbody").first();
        Elements ownerList = table.select("tr");
        
        for (int i=0; i<ownerList.size()-1; i++) {
        	if (ownerList.get(i).select("td").size() == 10) {
        		HorseOwnerVO vo = new HorseOwnerVO();
        		
        		Elements tdList = ownerList.get(i).select("td");
        		
        		Element td1 = tdList.get(1).select("a").first();
    			
    			// ID
    			String id = td1.attr("onclick");
    			id = id.substring(id.indexOf("'")+1);
    			id = id.substring(0, id.indexOf("'"));
    			
    			vo.setId(id);
    			
    			// 조교사명
    			vo.setName(td1.ownText());
        		
    			// 총등록
    			String addCnt = tdList.get(2).ownText();
    			if (addCnt.lastIndexOf("두") > 0) {
    				addCnt = addCnt.substring(0, addCnt.length()-1);
    				vo.setAddHorseCnt(Integer.parseInt(addCnt));
    			}
    			
    			// 총취소
    			String delCnt = tdList.get(3).ownText();
    			if (delCnt.lastIndexOf("두") > 0) {
    				delCnt = delCnt.substring(0, delCnt.length()-1);
    				vo.setDelHorseCnt(Integer.parseInt(delCnt));
    			}
    			
    			// 현소유
    			String totalHorseCnt = tdList.get(4).ownText();
    			if (totalHorseCnt.lastIndexOf("두") > 0) {
    				totalHorseCnt = totalHorseCnt.substring(0, totalHorseCnt.length()-1);
    				vo.setTotalHorseCnt(Integer.parseInt(totalHorseCnt));
    			}
    			
    			// 마주등록일
    			if (tdList.get(5).ownText() != "")
    				vo.setRegDate(sdf.parse(tdList.get(5).ownText()));
    			
    			// 최근1년 전적
    			vo.setNewRecord(tdList.get(6).ownText());
    			
    			// 최근1년 상금
    			vo.setNewMoney(tdList.get(7).ownText());
    			
    			// 통산 전적
    			vo.setTotalRecord(tdList.get(8).ownText());
    			
    			// 통산 상금
    			vo.setTotlaMoney(tdList.get(9).ownText());
    			
    			list.add(vo);
        	}
        }
		
		return list;
		
	}
	
	
	
	
}
