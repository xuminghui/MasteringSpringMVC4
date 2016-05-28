package com.example.springmvcStudy.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;



@Controller
public class HelloWorldController {
	@RequestMapping("/say/{path}")
	@ResponseBody
	public String sayHello(HttpServletRequest request, @PathVariable String path, @RequestHeader("Host") String host,
			@CookieValue("JSESSIONID") String cookie,@ModelAttribute("test") User user) {
		System.out.println(request.getAttributeNames().toString());
		System.out.println(cookie);
		return path; 
	}
	/*@ModelAttribute("test")
	public User addAccount(@RequestParam String number) {
		System.out.println(number);
		return new User();

	}*/
	public static class User{
		
	}
	
	@RequestMapping("/flash")
	public String flashTest(HttpServletRequest request,final RedirectAttributes redirectAttributes){
		WebApplicationContext  context=RequestContextUtils.findWebApplicationContext(request);
		redirectAttributes.addFlashAttribute("message", "Successfully added..");//
		return "redirect:home";
	}
	@RequestMapping("/home")
	public String home(){
		return "studyMVC/resultPage";
	}
	
	@RequestMapping(path="/getAttributes",method=RequestMethod.GET)
	public String getRequestAttributes(HttpServletRequest request,final RedirectAttributes redirectAttributes){
		Enumeration<String> enumeration=request.getAttributeNames();
		List<String> attributes=new ArrayList<String>();  
		while(enumeration.hasMoreElements()){
			attributes.add(enumeration.nextElement());
		}
		redirectAttributes.addFlashAttribute("attributes", attributes);
		return "redirect:home";
	}
	
	@ResponseBody
	@RequestMapping("/json")
	public List<String> showJson(){
		List<String> list=new ArrayList<String>();
		list.add("1");
		list.add("2");
		return list;
	}
	@RequestMapping(path="/asynRequest",method=RequestMethod.GET)
	@ResponseBody
	public Callable<String> processUpload() {
	    return new Callable<String>() {
	        public String call() throws Exception {
	            Thread.sleep(10000l);
	            return "redirect:home";
	        }
	    };
	}

	@RequestMapping("/asynRequestD")
	@ResponseBody
	public DeferredResult<String> quotes() {
	    final DeferredResult<String> deferredResult = new DeferredResult<String>();
	    new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				deferredResult.setResult("1111111");
			}
		}).start();
	    return deferredResult;
	}
	


}
