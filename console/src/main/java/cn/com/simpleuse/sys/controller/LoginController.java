package cn.com.simpleuse.sys.controller;

import cn.com.simpleuse.sys.bean.LoginUser;
import cn.com.simpleuse.sys.exception.SysControllerException;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);


    @RequestMapping(value = {"login","/"}, method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("CURRENT_PAGE_TITLE", "login");
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(LoginUser loginUser, BindingResult bindingResult, Model model) {
        try {
            String encUsername = Hashing.md5().hashString(loginUser.getUsername(), Charsets.UTF_8).toString();
            String encPassword = Hashing.md5().hashString(loginUser.getPassword(), Charsets.UTF_8).toString();
            encPassword = Hashing.md5().hashString("{".concat(encUsername).concat(":").concat(encPassword).concat("}"),Charsets.UTF_8).toString();

            SecurityUtils.getSubject().login(new UsernamePasswordToken(loginUser.getUsername(), encPassword, loginUser.isRememberMe()));
            return "redirect:/console";
        } catch (Exception e) {
            logger.error("LoginController.login", e);
            // IncorrectCredentialsException
            // UnknownAccountException
            model.addAttribute("err", e.getClass().getSimpleName());
            return "login";
        }
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout() {
        try {
            SecurityUtils.getSubject().logout();
            return "redirect:/login";
        } catch (Exception e) {
            logger.error("LoginController.logout", e);
            throw new SysControllerException();
        }
    }

}
