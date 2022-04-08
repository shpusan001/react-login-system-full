package testserver.common.reacttestserver.controler;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import testserver.common.reacttestserver.dto.UserDto;
import testserver.common.reacttestserver.UserRepository;
import testserver.common.reacttestserver.dto.UserResDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;
import java.util.UUID;

@Controller
@Slf4j
public class TestController {

    private final UserRepository userRepository = UserRepository.instance;

    @PostMapping("/api/auth/register")
    @ResponseBody
    public UserResDto register(@RequestBody UserDto user, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(!userRepository.store.contains(user)) {
            userRepository.store.add(user);
            System.out.println("user.getUsername() = " + user.getUsername());
            System.out.println("user.getPassword() = " + user.getPassword());
        }else{
            response.sendError(500, "회원 중복 오류");
            return null;
        }

        HttpSession session = request.getSession();
        String sessionId = UUID.randomUUID().toString();
        if(session.getAttribute("sessionId") == null){
            session.setAttribute("sessionId",sessionId);
        }
        System.out.println(userRepository.store);
        log.info("회원가입 요청");
        
        return new UserResDto(sessionId, user.getUsername());
    }

    @PostMapping("/api/auth/login")
    @ResponseBody
    public UserResDto login(@RequestBody UserDto user, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println(userRepository.store);
        UserDto loginUser = null;

        Iterator<UserDto> iterator = userRepository.store.iterator();
        while(iterator.hasNext()){
            UserDto e = iterator.next();
            System.out.println("e.getUsername() = " + e.getUsername());
            System.out.println("e.getPassword() = " + e.getPassword());
            System.out.println("user.getUsername() = " + user.getUsername());
            System.out.println("user.getPassword() = " + user.getPassword());
            if(e.getUsername().equals(user.getUsername()) &&
                    e.getPassword().equals(user.getPassword())){
                loginUser = e;
            }
        }

        if(loginUser == null){
            log.info("로그인 실패");
            response.sendError(401, "로그인 오류");
            return null;
        }

        HttpSession session = request.getSession();
        String sessionId = UUID.randomUUID().toString();
        if(session.getAttribute("sessionId") == null){
            session.setAttribute("sessionId",sessionId);
        }

        log.info("로그인 요청");

        return new UserResDto(sessionId, user.getUsername());
    }
}
