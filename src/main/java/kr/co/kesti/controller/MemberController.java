package kr.co.kesti.controller;

import kr.co.kesti.domain.entity.Member;
import kr.co.kesti.service.MemberService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/member")
public class MemberController {
    @Resource(name = "memberService")
    private MemberService memberService;

    @PostMapping("/signUp")
    public void addMember(Member member) {
        this.memberService.addMember(member);
    }

    @PostMapping("/removeMember")
    public void removeMember(@RequestParam("username") final String username) {
        this.memberService.removeMember(username);
    }

    @PostMapping("/findUsername")
    public String findUsername(Member member) {
        return this.memberService.findUsername(member);
    }

    @PostMapping("/findPassword")
    public void findPassword(Member member) {
        this.memberService.
    }
}