package kr.co.krace.controller;


import java.util.Iterator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import kr.co.krace.service.EventService;
import kr.co.krace.util.ImageFile;
import kr.co.krace.util.ImageView;

@Controller
public class EventController {
	@Resource(name="imageView") ImageView imageView;
	
	@Autowired
	private EventService eventService;

	@RequestMapping(value="/event", method=RequestMethod.GET) 
	public ModelAndView store() throws Exception {
		return new ModelAndView("event");		
	}
	
//	/** * 이미지 업로드 페이지의 폼에서 전송 시 받게 되는 메서드 */ 
//	@RequestMapping(value="/addEvent", method=RequestMethod.POST) 
//	private String addEvent(@RequestParam MultipartFile imageFile, ModelMap modelMap) { 
////		ImageFileVO fileInfo = imageService.save(imageFile); 
////		modelMap.put("imageFile", fileInfo); 
//		return "uploadComplete";
//	}
	
	@RequestMapping(value="/addEvent", method=RequestMethod.POST)
	public ModelAndView addEvent(HttpServletRequest request) throws Exception{
		System.out.println("test");
		
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;

	    Iterator<String> iterator = multipartHttpServletRequest.getFileNames();

	    MultipartFile multipartFile = null;

	    while(iterator.hasNext()){

	        multipartFile = multipartHttpServletRequest.getFile(iterator.next());

	        if(multipartFile.isEmpty() == false){

	            System.out.println("------------- file start -------------");

	            System.out.println("name : "+multipartFile.getName());

	            System.out.println("filename : "+multipartFile.getOriginalFilename());

	            System.out.println("size : "+multipartFile.getSize());

	            System.out.println("-------------- file end --------------\n");
	            
	            ImageFile fileInfo = eventService.save(multipartFile); 

	        }

	    }


		
//		ImageFile fileInfo = eventService.save(imageFile); 
	     

	    return new ModelAndView("main");		
	}


}
