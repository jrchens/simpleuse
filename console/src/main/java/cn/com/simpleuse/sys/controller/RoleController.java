package cn.com.simpleuse.sys.controller;

import cn.com.simpleuse.sys.domain.Config;
import cn.com.simpleuse.sys.domain.Role;
import cn.com.simpleuse.sys.exception.SysControllerException;
import cn.com.simpleuse.sys.exception.SysServiceException;
import cn.com.simpleuse.sys.service.ConfigService;
import cn.com.simpleuse.sys.service.RoleService;
import cn.com.simpleuse.validator.group.Save;
import cn.com.simpleuse.validator.group.Update;
import com.github.pagehelper.Page;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class RoleController {

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "sys/role/index", method = RequestMethod.GET)
    public String index(Role record,Model model) {
        try {
            model.addAttribute("CURRENT_PAGE_TITLE", "sys/role/index");
            model.addAttribute(record);
            return "sys/role/index";
        } catch (SysServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error("RoleController.index", e);
            throw new SysControllerException(e);
        }
    }

    @RequestMapping(value = "sys/role/query.json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> query(String viewname, Integer pageNum, Integer pageSize) {
        Map<String, Object> result = Maps.newHashMap();
        try {
            Page<Role> page = roleService.selectByViewname(viewname, pageNum, pageSize);
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
            logger.error("RoleController.query", e);
            result.put("code", 500);
            result.put("msg", e.getMessage());
            result.put("count", 0);
            result.put("data", Lists.newArrayList());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);

        }
    }

    @RequestMapping(value = "sys/role/create", method = RequestMethod.GET)
    public String create(Role record, Model model) {
        try {
            model.addAttribute("CURRENT_PAGE_TITLE", "sys/role/create");
            model.addAttribute(record);
            return "sys/role/create";
        } catch (SysServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error("RoleController.create", e);
            throw new SysControllerException();
        }
    }

    @RequestMapping(value = "sys/role/save", method = RequestMethod.POST)
    public String save(@Validated(value = Save.class) Role record, BindingResult bindingResult) {
        try {
            if (bindingResult.hasFieldErrors()) {
                return "sys/role/create";
            }
            roleService.insertSelective(record);
            return "redirect:/sys/role/index";
        } catch (SysServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error("RoleController.save", e);
            throw new SysControllerException();
        }
    }

    @RequestMapping(value = "sys/role/edit", method = RequestMethod.GET)
    public String edit(Long id, Model model) {
        try {
            model.addAttribute("CURRENT_PAGE_TITLE", "sys/role/edit");
            model.addAttribute(roleService.selectByPrimaryKey(id));
            return "sys/role/edit";
        } catch (SysServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error("RoleController.edit", e);
            throw new SysControllerException();
        }
    }

    @RequestMapping(value = "sys/role/update", method = RequestMethod.POST)
    public String update(@Validated(value = Update.class) Role record, BindingResult bindingResult) {
        try {
            if (bindingResult.hasFieldErrors()) {
                return "sys/role/edit";
            }
            roleService.updateByPrimaryKeySelective(record);
            return "redirect:/sys/role/index";
        } catch (SysServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error("RoleController.update", e);
            throw new SysControllerException();
        }
    }

    @RequestMapping(value = "sys/role/remove", method = RequestMethod.POST)
    public String remove(@RequestParam(value = "id",required = false,defaultValue = "") String id) {
        try {
            Gson gson = new Gson();
            Role record = gson.fromJson(id,Role.class);
            roleService.removeByPrimaryKey(record);
            return "redirect:/sys/role/index";
        } catch (SysServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error("RoleController.remove", e);
            throw new SysControllerException();
        }
    }

    @RequestMapping(value = "sys/role/batch-remove", method = RequestMethod.POST)
    public String batchRemove(@RequestParam(value = "ids") String ids) {
        try {
            Gson gson = new Gson();
            List<Role> list = gson.fromJson(ids,new TypeToken<ArrayList<Role>>(){}.getType());
            roleService.batchRemoveByPrimaryKey(list);
            return "redirect:/sys/role/index";
        } catch (SysServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error("RoleController.batch-remove", e);
            throw new SysControllerException();
        }
    }
}
