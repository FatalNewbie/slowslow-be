package com.elice.slowslow.user.controller;

import com.elice.slowslow.user.User;
import com.elice.slowslow.user.dto.MembershipDTO;
import com.elice.slowslow.user.dto.MypageResponseDTO;
import com.elice.slowslow.user.dto.UserDTO;
import com.elice.slowslow.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.security.Principal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static io.jsonwebtoken.Jwts.header;

@Slf4j
@RestController
@ResponseBody
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    /*백엔드 프론트 연동 확인 테스트 컨트롤러입니다. 곧 지워져요.........*/
    @GetMapping("/api/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(String.format("백엔드에서 보내는 메시지다! %s!", name));
    }

    public static class Greeting {
        private String message;

        public Greeting(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    /*백엔드 프론트 연동 테스트 코드 끝-----------*/


    @PostMapping("/api/v1/membership")
    public ResponseEntity<String> membershipProcess(@RequestBody @Valid MembershipDTO membershipDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = userService.membershipProcess(membershipDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    //관리자 페이지
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/api/v1/admin")
    public String adminP() {

        return "admin Controller";
    }


    //SecurityContextHolder를 통해 현재 로그인된 사용자 이름, role 받기
    //myPage
    @GetMapping("/mypage")
    public ResponseEntity<MypageResponseDTO> mypage(@AuthenticationPrincipal UserDetails userDetails){
        String name = userDetails.getUsername();

        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority();

        MypageResponseDTO mypageDto = userService.findByNameProc(name);

        return ResponseEntity.ok().header("Content-Type", "application/json").body(mypageDto);
    }

    @PostMapping("/api/v1/checkPassword")
    public String checkPassword(@RequestParam("password") String password, @AuthenticationPrincipal UserDetails userDetails) {
        String name = userDetails.getUsername();

        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority();

        UserDTO user = userService.findByName(name);

        if (user == null) {
            return "사용자 정보를 찾을 수 없습니다.";
        }

        // 사용자가 입력한 비밀번호와 저장된 비밀번호 비교
        boolean passwordsMatch = userService.checkPassword(user, password);

        if (passwordsMatch) {
            // 비밀번호가 일치할 경우 업데이트 로직 실행
            // 여기에 실제로 업데이트하는 로직을 추가하면 됩니다.
            return "정보 수정 폼으로 이동";
        } else {
            // 비밀번호가 일치하지 않을 경우 처리
            return "불일치";
        }
    }

    @PostMapping("/api/v1/update")
    public String update(@RequestBody UserDTO userDTO, @AuthenticationPrincipal UserDetails userDetails) {
        String name = userDetails.getUsername();

        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority();

        UserDTO user = userService.findByName(name);

        // 입력받은 정보로 사용자 정보 업데이트
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        user.setPhoneNumber(userDTO.getPhoneNumber());

        // 사용자 정보 업데이트
        UserDTO updatedUser = userService.update(user);

        return "update";
    }


    @GetMapping("/api/v1/delete")
    @PreAuthorize("principal.username == #username")
    public String deleteByName(@RequestParam("username") String username, Principal principal) {
        // principal.username과 요청 파라미터의 username이 일치하는 경우에만 삭제 허용
        userService.deletedByName(username);
        return "삭제완료";
    }

    @GetMapping("/api/v1/restoration")
    @PreAuthorize("hasRole('ADMIN')")
    public String restorationByName(@RequestParam("username") String username) {
        userService.restorationByName(username);
        return "복구완료";
    }
}