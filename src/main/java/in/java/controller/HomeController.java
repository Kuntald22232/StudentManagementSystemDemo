package in.java.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class HomeController {

    @GetMapping("/home")
    public String homepage()
    {
        return "<h1>Hello Students Your data hase secured in my databases</h1>";
    }

    @GetMapping("/csrf")
    public CsrfToken getToken(HttpServletRequest request)
    {
        return (CsrfToken)  request.getAttribute("_csrf");
    }
}
