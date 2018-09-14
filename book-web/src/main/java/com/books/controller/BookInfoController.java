package com.books.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.books.service.BookInfoService;
/**
 * 版本号 v1
 * @author chenghui
 *
 */
@RequestMapping("/v1/book")
@Controller
public class BookInfoController {
		
	
	@Autowired
	private  BookInfoService bookInfoService;
	
	@RequestMapping("/v1/book")
	@ResponseBody
	public  String  queryBookInfoList(String s121){
		
		return "";
	}
	
}
