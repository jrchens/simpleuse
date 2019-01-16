package cn.com.simpleuse.sys.controller;

import cn.com.simpleuse.sys.domain.Group;
import cn.com.simpleuse.sys.exception.SysControllerException;
import cn.com.simpleuse.sys.exception.SysServiceException;
import cn.com.simpleuse.sys.service.GroupService;
import cn.com.simpleuse.validator.group.Save;
import cn.com.simpleuse.validator.group.Update;
import cn.com.simpleuse.web.annotation.CsrfToken;
import com.github.pagehelper.Page;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class GroupController {

    private static final Logger logger = LoggerFactory.getLogger(GroupController.class);

    @Autowired
    private GroupService groupService;

    @CsrfToken
    @RequestMapping(value = "sys/group/index", method = RequestMethod.GET)
    public String index(Group record,Model model) {
        try {
            model.addAttribute("CURRENT_PAGE_TITLE", "sys/group/index");
            model.addAttribute(record);
            return "sys/group/index";
        } catch (SysServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error("GroupController.index", e);
            throw new SysControllerException(e);
        }
    }

    @RequestMapping(value = "sys/group/query.json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> query(String viewname, Integer pageNum, Integer pageSize,String sort,String order) {
        Map<String, Object> result = Maps.newHashMap();
        try {
            Page<Group> page = groupService.selectByViewname(viewname, pageNum, pageSize,sort,order);
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
            logger.error("GroupController.query", e);
            result.put("code", 500);
            result.put("msg", e.getMessage());
            result.put("count", 0);
            result.put("data", Lists.newArrayList());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);

        }
    }

    @CsrfToken
    @RequestMapping(value = "sys/group/create", method = RequestMethod.GET)
    public String create(Group record, Model model/*, HttpServletRequest request*/) {
        try {
//            String[] names = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext()).getBeanDefinitionNames();
//            for (String name: names
//                 ) {
//                logger.info("{}",name);
//            }

            model.addAttribute("CURRENT_PAGE_TITLE", "sys/group/create");
            model.addAttribute(record);
            return "sys/group/create";
        } catch (SysServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error("GroupController.create", e);
            throw new SysControllerException();
        }
    }

    @CsrfToken(verify = true)
    @RequestMapping(value = "sys/group/save", method = RequestMethod.POST)
    public String save(@Validated(value = Save.class) Group record, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                return "sys/group/create";
            }
            groupService.insertSelective(record);
            return "redirect:/sys/group/index";
        } catch (SysServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error("GroupController.save", e);
            throw new SysControllerException();
        }
    }

    @CsrfToken
    @RequestMapping(value = "sys/group/edit", method = RequestMethod.GET)
    public String edit(Group record, Model model) {
        try {
            model.addAttribute("CURRENT_PAGE_TITLE", "sys/group/edit");
            model.addAttribute(groupService.selectByPrimaryKey(record.getId()));
            return "sys/group/edit";
        } catch (SysServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error("GroupController.edit", e);
            throw new SysControllerException();
        }
    }

    @CsrfToken(verify = true)
    @RequestMapping(value = "sys/group/update", method = RequestMethod.POST)
    public String update(@Validated(value = Update.class) Group record, BindingResult bindingResult) {
        try {
            if (bindingResult.hasFieldErrors()) {
                return "sys/group/edit";
            }
            groupService.updateByPrimaryKeySelective(record);
            return "redirect:/sys/group/index";
        } catch (SysServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error("GroupController.update", e);
            throw new SysControllerException();
        }
    }

    @RequestMapping(value = "sys/group/remove", method = RequestMethod.POST)
    public String remove(@RequestParam(value = "id",required = false,defaultValue = "") String id) {
        try {
            Gson gson = new Gson();
            Group record = gson.fromJson(id,Group.class);
            groupService.removeByPrimaryKey(record);
            return "redirect:/sys/group/index";
        } catch (SysServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error("GroupController.remove", e);
            throw new SysControllerException();
        }
    }

    @RequestMapping(value = "sys/group/batch-remove", method = RequestMethod.POST)
    public String batchRemove(@RequestParam(value = "ids") String ids) {
        try {
            Gson gson = new Gson();
            List<Group> list = gson.fromJson(ids,new TypeToken<ArrayList<Group>>(){}.getType());
            groupService.batchRemoveByPrimaryKey(list);
            return "redirect:/sys/group/index";
        } catch (SysServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error("GroupController.batch-remove", e);
            throw new SysControllerException();
        }
    }
}
