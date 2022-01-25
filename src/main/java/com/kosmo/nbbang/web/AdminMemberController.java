package com.kosmo.nbbang.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kosmo.nbbang.service.AdminChatDTO;
import com.kosmo.nbbang.service.MemberDTO;
import com.kosmo.nbbang.service.MemberListPaging;
import com.kosmo.nbbang.service.impl.MemberServiceImpl;

@Controller
public class AdminMemberController {

	@Autowired
	private MemberServiceImpl memberService;
	
	@RequestMapping("/adminmember.do")
	public String adminMember(
			@RequestParam Map map,//검색 파라미터 및 페이징용 키값 저장용
			@RequestParam(required = false,defaultValue = "1") int nowPage,
	        HttpServletRequest req,//페이징에 사용할 컨텍스트 루트 경로 얻기용
			Model model) {
		MemberListPaging<MemberDTO> listPagingData = memberService.selectAllMember(map, req, nowPage);
		/*for(AdminMemberDTO dto : list) {
			System.out.println(dto.getEmail());
			System.out.println(dto.getAuthority());
			System.out.println(dto.getNickname());
			System.out.println(dto.getName());
			System.out.println(dto.getRegistdate());
			System.out.println(dto.getTel());
		}*/
		model.addAttribute("listPagingData", listPagingData);
		return "admin/AdminMember";
	}
	
	//admin진입시 메인에 닉네임뿌리기(admin각 페이지마다 로직 추가해야 함)
	@RequestMapping("/admin.do")
	public String adminMain(
							@RequestParam Map map,//검색 파라미터 및 페이징용 키값 저장용
							@RequestParam(required = false,defaultValue = "1") int nowPage,
					        HttpServletRequest req,//페이징에 사용할 컨텍스트 루트 경로 얻기용
							Model model) {
		MemberListPaging<MemberDTO> listPagingData = memberService.selectAllMember(map, req, nowPage);
		/*for(AdminMemberDTO dto : list) {
			System.out.println(dto.getEmail());
			System.out.println(dto.getAuthority());
			System.out.println(dto.getNickname());
			System.out.println(dto.getName());
			System.out.println(dto.getRegistdate());
			System.out.println(dto.getTel());
		}*/
		model.addAttribute("listPagingData", listPagingData);
		return "admin/AdminMain";
	}
	
	@RequestMapping("/adminChat.do")
	public String adminChat(
			@RequestParam Map map,//검색 파라미터 및 페이징용 키값 저장용
			@RequestParam(required = false,defaultValue = "1") int nowPage,
	        HttpServletRequest req,//페이징에 사용할 컨텍스트 루트 경로 얻기용
			Model model) {
		MemberListPaging<AdminChatDTO> listPagingData = memberService.selectAllChat(map, req, nowPage);
		model.addAttribute("listPagingData", listPagingData);
		return "admin/AdminChat";
	}
}
