package cn.com.simpleuse.sys.controller;

import cn.com.simpleuse.sys.domain.Config;
import cn.com.simpleuse.sys.exception.SysControllerException;
import cn.com.simpleuse.sys.exception.SysServiceException;
import cn.com.simpleuse.sys.service.ConfigService;
import com.github.pagehelper.Page;
import com.google.common.base.Joiner;
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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class ConfigController {

    private static final Logger logger = LoggerFactory.getLogger(ConfigController.class);

    @Autowired
    private ConfigService configService;

    @RequestMapping(value = "sys_config/index", method = RequestMethod.GET)
    public String index(Model model) {
        try {
            model.addAttribute("CURRENT_PAGE_TITLE", "sys_config/index");
            return "config/index";
        } catch (SysServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error("ConfigController.index", e);
            throw new SysControllerException(e);
        }
    }

    @RequestMapping(value = "sys_config/query.json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> query(String cfgName, Integer pageNum, Integer pageSize) {
        Map<String, Object> result = Maps.newHashMap();
        try {
            Page<Config> page = configService.selectByCfgName(cfgName, pageNum, pageSize);
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
            logger.error("ConfigController.query", e);
            result.put("code", 500);
            result.put("msg", e.getMessage());
            result.put("count", 0);
            result.put("data", Lists.newArrayList());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);

        }
    }

    @RequestMapping(value = "sys_config/create", method = RequestMethod.GET)
//    @Token(add = true)
    public String create(Config config, Model model) {
        try {
            model.addAttribute("CURRENT_PAGE_TITLE", "sys_config/create");
            model.addAttribute(config);
            return "config/create";
        } catch (SysServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error("ConfigController.create", e);
            throw new SysControllerException();
        }
    }

    @RequestMapping(value = "sys_config/save", method = RequestMethod.POST)
//    @Token(remove = false)
    public String save(@Validated Config record, BindingResult bindingResult) {
        try {
            if (bindingResult.hasFieldErrors()) {
                return "config/create";
            }
            configService.insertSelective(record);
            return "redirect:/sys_config/index";
        } catch (SysServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error("ConfigController.save", e);
            throw new SysControllerException();
        }
    }

    @RequestMapping(value = "sys_config/edit", method = RequestMethod.GET)
    public String edit(Long id, Model model) {
        try {
            model.addAttribute("CURRENT_PAGE_TITLE", "sys_config/edit");
            model.addAttribute(configService.selectByPrimaryKey(id));
            return "config/edit";
        } catch (SysServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error("ConfigController.edit", e);
            throw new SysControllerException();
        }
    }

    @RequestMapping(value = "sys_config/update", method = RequestMethod.POST)
    public String update(@Validated Config record, BindingResult bindingResult) {
        try {
            if (bindingResult.hasFieldErrors()) {
                return "config/edit";
            }
            configService.updateByPrimaryKeySelective(record);
            return "redirect:/sys_config/index";
        } catch (SysServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error("ConfigController.update", e);
            throw new SysControllerException();
        }
    }



    @RequestMapping(value = "sys_config/remove", method = RequestMethod.POST)
    public String remove(Long id) {
        try {
            configService.removeByPrimaryKey(id);
            return "redirect:/sys_config/index";
        } catch (SysServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error("ConfigController.remove", e);
            throw new SysControllerException();
        }
    }

    @RequestMapping(value = "sys_config/batch-remove", method = RequestMethod.POST)
    public String batchRemove(@RequestParam(value = "ids[]") String ids) {
        try {
            Gson gson = new Gson();
            List<Long> o = gson.fromJson(ids,new TypeToken<ArrayList<Long>>(){}.getType());
            configService.batchRemoveByPrimaryKey(o);
            return "redirect:/sys_config/index";
        } catch (SysServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error("ConfigController.batch-remove", e);
            throw new SysControllerException();
        }
    }

    @RequestMapping(value = "sys_config/batch-disable-enable", method = RequestMethod.POST)
    public String batchRemoveDisableEnable(@RequestParam(value = "list[]") String list) {
        try {
            Gson gson = new Gson();
            List<Config> o = gson.fromJson(list, new TypeToken<ArrayList<Config>>() {}.getType());

            configService.batchDisableEnableByPrimaryKey(o);
            return "redirect:/sys_config/index";
        } catch (SysServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error("ConfigController.batch-disable-enable", e);
            throw new SysControllerException();
        }
    }

//    @RequestMapping(value = "sys_config/remove.json", method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseEntity<Map<String, Object>> remove(Long id) {
//        Map<String, Object> result = Maps.newHashMap();
//        try {
//            int aff = configService.removeByPrimaryKey(id);
//
//            result.put("code", 0);
//            result.put("msg", "");
//            result.put("data", aff);
//            return ResponseEntity.ok(result);
//        } catch (SysServiceException e) {
//            result.put("code", 500);
//            result.put("msg", e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
//        } catch (Exception e) {
//            logger.error("ConfigController.remove", e);
//            result.put("code", 500);
//            result.put("msg", e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
//        }
//    }

//    @RequestMapping(value = "sys_config/batch-remove.json", method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseEntity<Map<String, Object>> remove(@RequestBody List<Long> ids) {
//        Map<String, Object> result = Maps.newHashMap();
//        try {
//            logger.info("sys_config/batch-remove ids:{}", Joiner.on(',').join(ids));
//            int aff = configService.batchRemoveByPrimaryKey(ids);
//            result.put("code", 0);
//            result.put("msg", "");
//            result.put("data", aff);
//            return ResponseEntity.ok(result);
//        } catch (SysServiceException e) {
//            result.put("code", 500);
//            result.put("msg", e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
//        } catch (Exception e) {
//            logger.error("ConfigController.batch-remove", e);
//            result.put("code", 500);
//            result.put("msg", e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
//        }
//    }

//    @RequestMapping(value = "sys_config/disable-enable.json", method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseEntity<Map<String, Object>> batchDisableEnable(@RequestBody List<Config> configs) {
//        Map<String, Object> result = Maps.newHashMap();
//        try {
//            int aff = configService.batchDisableEnableByPrimaryKey(configs);
//            result.put("code", 0);
//            result.put("msg", "");
//            result.put("data", aff);
//            return ResponseEntity.ok(result);
//        } catch (SysServiceException e) {
//            result.put("code", 500);
//            result.put("msg", e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
//        } catch (Exception e) {
//            logger.error("ConfigController.batchDisableEnable", e);
//            result.put("code", 500);
//            result.put("msg", e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
//        }
//    }


}
