package kr.co.kesti.controller;

import kr.co.kesti.domain.entity.Member;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
public class MemberController {
    @PostMapping("/signUp")
    public void addMember() {

    }

    @PostMapping("/removeMember")
    public void removeMember() {

    }

    @PostMapping("/findUsername")
    public String findUsername(Member member) {

    }

    @PostMapping("/findPassword")
    public String findUsername(Member member) {

    }
}