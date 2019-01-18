package cn.com.simpleuse.sys.controller;

import cn.com.simpleuse.sys.domain.User;
import cn.com.simpleuse.sys.exception.SysControllerException;
import cn.com.simpleuse.sys.exception.SysServiceException;
import cn.com.simpleuse.sys.service.UserService;
import cn.com.simpleuse.validator.group.Save;
import cn.com.simpleuse.validator.group.Update;
import cn.com.simpleuse.web.annotation.CsrfToken;
import com.github.pagehelper.Page;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @CsrfToken
    @RequestMapping(value = "sys/user/index", method = RequestMethod.GET)
    public String index(User record, Model model) {
        try {
            model.addAttribute("CURRENT_PAGE_TITLE", "sys/user/index");
            model.addAttribute(record);
            return "sys/user/index";
        } catch (SysServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error("UserController.index", e);
            throw new SysControllerException(e);
        }
    }

    @RequestMapping(value = "sys/user/query.json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> query(String username, String viewname, Integer pageNum, Integer pageSize, String sort, String order) {
        Map<String, Object> result = Maps.newHashMap();
        try {
            Page<User> page = userService.selectByUsernameAndViewname(username, viewname, pageNum, pageSize, sort, order);
            result.put("code", 0);
            result.put("msg", "");
            result.put("count", page.getTotal());
            result.put("data", page.getResult());
            return ResponseEntity.ok(result);
        } catch (SysServiceException e) {
            result.put("code", 500);
            result.put("msg", e.getMessage());
            result.put("count", 0);
            result.put("data", Lists.newArrayList());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        } catch (Exception e) {
            logger.error("UserController.query", e);
            result.put("code", 500);
            result.put("msg", e.getMessage());
            result.put("count", 0);
            result.put("data", Lists.newArrayList());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);

        }
    }

    @CsrfToken
    @RequestMapping(value = "sys/user/create", method = RequestMethod.GET)
    public String create(User record, Model model/*, HttpServletRequest request*/) {
        try {
            model.addAttribute("CURRENT_PAGE_TITLE", "sys/user/create");
            model.addAttribute(record);
            return "sys/user/create";
        } catch (SysServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error("UserController.create", e);
            throw new SysControllerException();
        }
    }

    @CsrfToken(verify = true)
    @RequestMapping(value = "sys/user/save", method = RequestMethod.POST)
    public String save(@Validated(value = Save.class) User record, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                return "sys/user/create";
            }
            userService.insertSelective(record);
            return "redirect:/sys/user/index";
        } catch (SysServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error("UserController.save", e);
            throw new SysControllerException();
        }
    }

    @CsrfToken
    @RequestMapping(value = "sys/user/edit", method = RequestMethod.GET)
    public String edit(User record, Model model) {
        try {
            model.addAttribute("CURRENT_PAGE_TITLE", "sys/user/edit");

            record = userService.selectByPrimaryKey(record.getId());
            record.setPassword(null);

            model.addAttribute(record);
            return "sys/user/edit";
        } catch (SysServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error("UserController.edit", e);
            throw new SysControllerException();
        }
    }

    @CsrfToken(verify = true)
    @RequestMapping(value = "sys/user/update", method = RequestMethod.POST)
    public String update(@Validated(value = Update.class) User record, BindingResult bindingResult) {
        try {
            if (bindingResult.hasFieldErrors()) {
                return "sys/user/edit";
            }
            userService.updateByPrimaryKeySelective(record);
            return "redirect:/sys/user/index";
        } catch (SysServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error("UserController.update", e);
            throw new SysControllerException();
        }
    }

    @RequestMapping(value = "sys/user/remove", method = RequestMethod.POST)
    public String remove(@RequestParam(value = "id", required = false, defaultValue = "") String id) {
        try {
//            Gson gson = new Gson();
//            User record = gson.fromJson(id, User.class);
            List<String> list = Splitter.on(',').splitToList(id);
            List<Long> idList = Lists.newArrayList();
            for (String e : list
            ) {
                idList.add(Long.parseLong(e));
            }
            userService.removeByPrimaryKey(idList);
            return "redirect:/sys/user/index";
        } catch (SysServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error("UserController.remove", e);
            throw new SysControllerException();
        }
    }

//    @RequestMapping(value = "sys/user/batch-remove", method = RequestMethod.POST)
//    public String batchRemove(@RequestParam(value = "ids") String ids) {
//        try {
//            Gson gson = new Gson();
//            List<User> list = gson.fromJson(ids, new TypeToken<ArrayList<User>>() {
//            }.getType());
//            userService.batchRemoveByPrimaryKey(list);
//            return "redirect:/sys/user/index";
//        } catch (SysServiceException e) {
//            throw e;
//        } catch (Exception e) {
//            logger.error("UserController.batch-remove", e);
//            throw new SysControllerException();
//        }
//    }
}
