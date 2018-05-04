package kr.co.krace.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import kr.co.krace.common.KRaceConstants;
import kr.co.krace.util.ImageFile;
import kr.co.krace.util.JsoupUtil;
import kr.co.krace.vo.HorseOwnerOwnVO;
import kr.co.krace.vo.HorseOwnerVO;
import kr.co.krace.vo.HorseOwnerVictoryVO;
import kr.co.krace.vo.TrainerVO;

@Service
public class AdminService {

	@Value("#{kraceProperties['img.path']}")
	private String imgPath;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public ArrayList<TrainerVO> getTrainerList(String meet) throws Exception {
		ArrayList<TrainerVO> list = new ArrayList<TrainerVO>();
		
		Map<String, String> param = new HashMap<String, String>();
        param.put("meet", meet);
		
        Document doc = JsoupUtil.post(KRaceConstants.UPDATE_TRAINER_LIST_URL, param);
		
        Element table = doc.select("table tbody").first();
        Elements trList = table.select("tr");
        
        for (Element tr : trList) {
        	Elements tdList = tr.select("td");
        	
        	TrainerVO vo = new TrainerVO();
        	
        	for (int i=1; i<tdList.size(); i++) {
        		if (i == 1) {
        			Element td1 = tdList.get(i).select("a").first();
        			
        			// ID
        			String id = td1.attr("onclick");
        			id = id.substring(id.indexOf("'")+1);
        			id = id.substring(0, id.indexOf("'"));
        			
        			vo.setId(id);
        			
        			// 조교사명
        			vo.setName(td1.ownText());
        		}
         		
        		// 소속조
				if (i == 2)	vo.setJo(tdList.get(i).ownText());
				
				// 데뷔일자
				if (i == 3) {
					if (tdList.get(i).ownText() != "")
						vo.setDebutDate(sdf.parse(tdList.get(i).ownText()));
				}
				
				// 최근1년전적
				if (i == 4)	vo.setNewRecord(tdList.get(i).ownText());
				
				// 통산전적
				if (i == 5) vo.setTotalRecord(tdList.get(i).ownText());
				
				// 위탁관리두수
				if (i == 6) vo.setHorseCnt(Integer.parseInt(tdList.get(i).ownText()));
        		
        	}
        	
        	// 조교사 상세 정보
        	TrainerVO details = getTrainerDetail(meet, vo.getId());
        	
        	vo.setTotalRecord2(details.getTotalRecord2());
        	vo.setTotalS(details.getTotalS());
        	vo.setTotalBs(details.getTotalBs());
        	vo.setTotalYs(details.getTotalYs());
        	vo.setNewRecord2(details.getNewRecord2());
            vo.setNewS(details.getNewS());
            vo.setNewBs(details.getNewBs());
            vo.setNewYs(details.getNewYs());
        	vo.setMeet(meet);
            
        	list.add(vo);
        }
        
		return list;
	}
	
	
	public TrainerVO getTrainerDetail(String meet, String trainerId) throws Exception {
		TrainerVO trainer = new TrainerVO();
		
		Map<String, String> param = new HashMap<String, String>();
        param.put("meet", meet);
        param.put("trNo", trainerId);
        
        Document doc = JsoupUtil.post(KRaceConstants.UPDATE_TRAINER_DETAIL_URL, param);
		
        Element contents = doc.select("#contents").first();
        
        // 사진 가져오기
        if (contents.select(".profile img") != null) {
        	String imgSrc = contents.select(".profile img").first().attr("src");
            
            
            // 이미지 저장 후 경로 변경
           	String imgUrl = KRaceConstants.KRA_URL + imgSrc;
           	
           	// 이미지 확장자
           	String imgFormat = imgSrc.substring(imgSrc.indexOf(".")+1);
           	
           	String imgFilePath = imgPath + File.separator + "trainer" + File.separator + imgSrc.substring(imgSrc.lastIndexOf("/")+1);
           	
           	ImageFile.getImageFromUrl(imgUrl, imgFilePath, imgFormat);
           	
           	String photo = "/images/trainer/" + imgSrc.substring(imgSrc.lastIndexOf("/")+1);
           	
           	trainer.setPhoto(photo);
        }
        
        // 통산전적 (승률, 복승률, 연승률)
        Elements result = contents.select("table").get(0).select("tr");
        
        trainer.setTotalRecord2(result.get(0).select("td").get(0).ownText());
        trainer.setTotalS(result.get(0).select("td").get(1).ownText());
        trainer.setTotalBs(result.get(0).select("td").get(2).ownText());
        trainer.setTotalYs(result.get(0).select("td").get(3).ownText());
        
        // 최근1년 전적 (승률, 복승률, 연승률)
        trainer.setNewRecord2(result.get(1).select("td").get(0).ownText());
        trainer.setNewS(result.get(1).select("td").get(1).ownText());
        trainer.setNewBs(result.get(1).select("td").get(2).ownText());
        trainer.setNewYs(result.get(1).select("td").get(3).ownText());
     
		return trainer;
	}
	
	public ArrayList<HorseOwnerVO> getHorseOwnerList(String meet) throws Exception {
		ArrayList<HorseOwnerVO> list = new ArrayList<HorseOwnerVO>();
		
		Map<String, String> param = new HashMap<String, String>();
        param.put("meet", meet);
		
        Document doc = JsoupUtil.post(KRaceConstants.UPDATE_HORSEOWNER_LIST_URL, param);
		
        Element table = doc.select("table tbody").first();
        Elements ownerList = table.select("tr");
        
        for (int i=0; i<ownerList.size()-1; i++) {
        	if (ownerList.get(i).select("td").size() == 10) {
        		HorseOwnerVO vo = new HorseOwnerVO();
        		
        		vo.setMeet(meet);
        		
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
    			
    			logger.debug("[" + vo.getMeet() + "]마주: " + vo.getName() + " 상세 정보 가져오기");
    			System.out.println("[" + vo.getMeet() + "]마주: " + vo.getName() + " 상세 정보 가져오기");
    			
    			// 마주 상세 정보
    			HorseOwnerVO details = getHorseOwnerDetail(meet, vo.getId());
    			
    			vo.setClothColor(details.getClothColor());
    			vo.setOwnList(details.getOwnList());
    			vo.setVictoryList(details.getVictoryList());
    			
    			list.add(vo);
        	}
        }
		
		return list;
		
	}
	
	public HorseOwnerVO getHorseOwnerDetail(String meet, String owNo) throws Exception {
		HorseOwnerVO horseOwner = new HorseOwnerVO();
		
        Map<String, String> param = new HashMap<String, String>();
        param.put("meet", meet);
        param.put("owNo", owNo);
        
        Document doc = JsoupUtil.post(KRaceConstants.UPDATE_HORSEOWNER_DETAIL_URL, param);

        if (!meet.equals("2")) {
        	// 마주복색
            Element imgElement = doc.select("table").get(0);
            if (imgElement.selectFirst("img") != null) {
            	String imgSrc = imgElement.selectFirst("img").attr("src");
            	
            	// 이미지 저장 후 경로 변경
            	String imgUrl = KRaceConstants.KRA_URL + imgSrc;
            	
            	// 이미지 확장자
            	String imgFormat = imgSrc.substring(imgSrc.indexOf(".")+1);
            	
            	String imgFilePath = imgPath + File.separator + "owner_cloth" + File.separator + imgSrc.substring(imgSrc.lastIndexOf("/")+1);
            	
            	ImageFile.getImageFromUrl(imgUrl, imgFilePath, imgFormat);
            	
            	String clothColor = "/images/owner_cloth/" + imgSrc.substring(imgSrc.lastIndexOf("/")+1);
            	
            	horseOwner.setClothColor(clothColor);
            }
        }
        
        // 마적사항
        Element ownElement = doc.select("table").get(1).select("tbody").first();
        
        ArrayList<HorseOwnerOwnVO> ownList = new ArrayList<HorseOwnerOwnVO>();
        
        if (ownElement.select("tr").first().select("td").size() > 5) {
	        for (Element em : ownElement.select("tr")) {
	        	HorseOwnerOwnVO ownVO = new HorseOwnerOwnVO();
	
	        	Element td1 = em.select("td").get(1).select("a").first();
				
				// ID
				String id = td1.attr("onclick");
				id = id.substring(id.indexOf("'")+1);
				id = id.substring(0, id.indexOf("'"));
				
				ownVO.setHorseOwnerId(owNo);
				ownVO.setHorseId(id);
				ownVO.setHorseName(td1.ownText());
				ownVO.setGrade(em.select("td").get(2).ownText());
				
				 if (!meet.equals("2")) {
					ownVO.setBirthplace(em.select("td").get(3).ownText());
					ownVO.setSex(em.select("td").get(4).ownText());
					
					if (em.select("td").get(5).ownText() != "")
						ownVO.setAge(Integer.parseInt(em.select("td").get(5).ownText()));
					
					ownVO.setTrainerName(em.select("td").get(6).ownText());
					ownVO.setPeriod(em.select("td").get(8).ownText());
					ownVO.setEtc(em.select("td").get(9).ownText());
				}
				else {
					ownVO.setBirthplace(em.select("td").get(4).ownText());
					ownVO.setSex(em.select("td").get(5).ownText());
					
					if (em.select("td").get(6).ownText() != "")
						ownVO.setAge(Integer.parseInt(em.select("td").get(6).ownText()));
					
					ownVO.setTrainerName(em.select("td").get(7).ownText());
					ownVO.setPeriod(em.select("td").get(9).ownText());
					ownVO.setEtc(em.select("td").get(10).ownText());
				}
				
				ownList.add(ownVO);
	        }
        }
        
        horseOwner.setOwnList(ownList);
        
        // 대상경주 우승전적 상세보기
        Element victoryElement = doc.select("table").get(2).select("tbody").first();
        
        ArrayList<HorseOwnerVictoryVO> victoryList = new ArrayList<HorseOwnerVictoryVO>();
        
        if (victoryElement.select("tr").size() > 2) {
        	 for (Element em : victoryElement.select("tr")) {
        		 HorseOwnerVictoryVO vicVO = new HorseOwnerVictoryVO();
        		 
        		 String tmp = em.select("td").get(0).select("a").first().attr("href");
        		 tmp = tmp.substring(tmp.indexOf("(")+1, tmp.indexOf(")"));
        		 tmp = tmp.replaceAll("\"", "");
        		 
        		 String[] tmpArr = tmp.split(",");
        		 
        		 vicVO.setHorseOwnerId(owNo);
        		 
        		 vicVO.setRaceDate(tmpArr[1]);
        		 vicVO.setRound(tmpArr[2]);
        		 
        		 vicVO.setRaceName(em.select("td").get(1).ownText());
        		 vicVO.setHorseName(em.select("td").get(2).ownText());
        		 
        		 victoryList.add(vicVO);
        	 }
        }
        
        horseOwner.setVictoryList(victoryList);
		
		return horseOwner;
	}
	
 

}
