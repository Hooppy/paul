package com.paul.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.paul.dao.BoardDao;
import com.paul.model.BoardModel;
import com.paul.model.PageMaker;
import com.paul.model.PagingCriteria;

@Controller
@RequestMapping("board")
public class BoardController {
	
	@Autowired
	BoardDao boardDao;
	
	@Autowired
	BoardModel boardModel;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String List(PagingCriteria cri, ModelMap model) {
		
		model.addAttribute("List", "List");
		
		List<BoardModel> boardList = boardDao.getBoardList(cri);
		
		int total = boardDao.getTotalCnt();
		
		// Model 정보 저장
		model.addAttribute("boardList",boardList);
		model.addAttribute("paging",new PageMaker(cri,total));
		
		return "board/board_list";
	}
	
	@RequestMapping("/write")
	public String Write(ModelMap model) {
		
		model.addAttribute("Write", "Write");
		
		return "board/board_write";
	}
	
	@RequestMapping("/writeComplete")
	public String WriteComp(ModelMap model,
		@RequestParam(value = "writer") String writer,
		@RequestParam(value = "title") String title,
		@RequestParam(value = "content") String content) {
		
		boardModel.setWriter(writer);
		boardModel.setTitle(title);
		boardModel.setContent(content);
		
		model.addAttribute("WriteComplete", "WriteComplete");
		
		try {
			boardDao.Insert(boardModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "board/board_list";
	}
}
