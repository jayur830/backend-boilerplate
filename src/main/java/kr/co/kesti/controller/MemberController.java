package kr.co.kesti.controller;

import kr.co.kesti.domain.embed.MemberInfo;
import kr.co.kesti.domain.entity.Member;
import kr.co.kesti.domain.entity.MemberAuth;
import kr.co.kesti.model.response.ApiResponse;
import kr.co.kesti.service.MemberService;
import kr.co.kesti.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/member")
public class MemberController {
    @Resource(name = "memberService")
    private MemberService memberService;

    @PostMapping("/findByUsername")
    public ApiResponse<?> findByUsername(@RequestParam("username") final String username) {
        MemberAuth memberAuth = this.memberService.findByUsername(username);
        return ApiResponse.ok(memberAuth.toObject());
    }

    @PostMapping("/checkUsername")
    public ApiResponse<?> checkUsername(@RequestParam("username") final String username) {
        Map<String, Object> response = new HashMap<>();
        response.put("valid", !this.memberService.isExistUser(username));

        return ApiResponse.ok(response);
    }

    @PostMapping("/signUp")
    public ApiResponse<?> signUp(MemberVO memberVO) {
        this.memberService.signUp(memberVO);
        return ApiResponse.ok();
    }

    @PostMapping("/findUsername")
    public ApiResponse<?> findUsername(MemberInfo memberInfo) {
        Map<String, Object> response = new HashMap<>();

        Member member = this.memberService.findUsername(memberInfo);
        response.put("isExist", member != null);

        if (member != null) {
            StringBuilder s = new StringBuilder();
            final String username = member.getUsername();
            for (int i = 0; i < username.length(); ++i)
                s.append(i < 3 ? username.charAt(i) : '*');
            response.put("username", s.toString());
        }

        return ApiResponse.ok(response);
    }

    @PostMapping("/findPassword")
    public ApiResponse<?> findPassword(
            @RequestParam("username") final String username,
            @RequestParam("email") final String email) {
        Map<String, Object> response = new HashMap<>();

        if (this.memberService.isExistUser(username)) {
            this.memberService.resetPassword(username, email);
            response.put("isExist", true);
        } else response.put("isExist", false);

        return ApiResponse.ok(response);
    }
}