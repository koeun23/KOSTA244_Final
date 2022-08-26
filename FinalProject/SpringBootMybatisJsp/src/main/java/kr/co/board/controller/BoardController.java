package kr.co.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.board.service.BoardService;
import kr.co.board.vo.BoardVo;

@Controller
public class BoardController {
	
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	BoardService boardService;
    
    /*게시판 목록*/
    @GetMapping("/board/list")
	public String boardList(Model model) throws Exception{

    	log.info("BoardController.java > boardList() 호출");
		
    	//jsp 화면에 뿌릴 데이터베이스 조회값을 model 객체에 "list"라는 key값으로 담는다.
    	model.addAttribute("list", boardService.boardList());
		
    	return "/board/list"; //생성한 jsp명 (list.jsp)
	}
    
    /*게시판 상세조회*/
    @GetMapping("/board/detail")
	public String boardDetail(Model model, BoardVo boardVo) throws Exception{

    	log.info("BoardController.java > boardDetail() 호출");

    	log.info("상세조회하려는 게시판 번호 : "+boardVo.getTbSeq());

    	//jsp 화면에 뿌릴 데이터베이스 조회값을 model 객체에 "boardVo"라는 key값으로 담는다.
    	model.addAttribute("boardVo", boardService.boardDetail(boardVo));
    	
    	return "/board/detail"; //생성한 jsp명 (list.jsp)
	}
    
    /*게시판 등록화면 이동*/
    @GetMapping("/board/write")
	public String boardWrite(Model model, BoardVo boardVo) throws Exception{
    	log.info("BoardController.java > boardWrite() 호출");
    	//vo객체안에 게시판번호가 있으면 수정모드
    	if(boardVo != null) {
        	log.info("게시판 수정모드");
    		//jsp 화면에 뿌릴 데이터베이스 조회값을 model 객체에 "boardVo"라는 key값으로 담는다.
    		model.addAttribute("boardVo", boardService.boardDetail(boardVo));
    	}
    	
    	return "/board/write"; //생성한 jsp명 (list.jsp)
	}
    
    /*게시판 저장*/    
    @PostMapping("/board/insert") 
    private String boardInsert(BoardVo boardVo) throws Exception{

    	log.info("BoardController.java > boardInsert() 호출");
    	
    	/*인서트 서비스 실행*/
    	int result = boardService.boardInsert(boardVo);
    	
    	/*마이바티스의 insert문은 성공시 1을 반환한다. */
    	if(result > 0) {
    		/*redirect란 무엇인지 구글검색*/
    		return "redirect:/board/list";     
    	}else {
    		return "redirect:/board/write";     
    	}
    }
    
    /*게시판 수정*/    
    @PostMapping("/board/update") 
    private String boardUpdate(BoardVo boardVo) throws Exception{

    	log.info("BoardController.java > update() 호출");
    	
    	/* 업데이트 서비스 실행*/
    	int result = boardService.boardUpdate(boardVo);
    	
    	/*마이바티스의 insert문은 성공시 1을 반환한다. */
    	if(result > 0) {
    		return "redirect:/board/list";     
    	}else {
    		return "redirect:/board/write";     
    	}
    }
    
    /*게시판 삭제*/    
    @GetMapping("/board/delete") 
    private String boardDelete(BoardVo boardVo) throws Exception{

    	log.info("BoardController.java > delete() 호출");
    	
    	/*딜리트 서비스 실행*/
    	int result = boardService.boardDelete(boardVo);
    	
    	/*마이바티스의 delete문은 성공시 1을 반환한다. */
    	if(result > 0) {
    		return "redirect:/board/list";     
    	}else {
    		return "redirect:/board/detail?tbSeq="+boardVo.getTbSeq();     
    	}
    }
}
