package cn.com.simpleuse.sys.controller;

import cn.com.simpleuse.sys.exception.SysControllerException;
import cn.com.simpleuse.sys.service.ConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
//@RequestMapping(value = "system")
public class ConsoleController {

    private static final Logger logger = LoggerFactory.getLogger(ConsoleController.class);

    @Autowired
    private ConfigService configService;

    @RequestMapping(value = "console", method = RequestMethod.GET)
    public String index(Model model) {
        try {

            model.addAttribute("CURRENT_PAGE_TITLE", "console");
            return "console";
        } catch (Exception e) {
            logger.error("ConsoleController.index", e);
            throw new SysControllerException();
        }
    }
}
