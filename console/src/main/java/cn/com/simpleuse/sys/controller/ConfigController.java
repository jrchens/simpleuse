package cn.com.simpleuse.sys.controller;

import cn.com.simpleuse.sys.domain.Config;
import cn.com.simpleuse.sys.exception.SysControllerException;
import cn.com.simpleuse.sys.exception.SysServiceException;
import cn.com.simpleuse.sys.service.ConfigService;
import com.github.pagehelper.Page;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "sys")
public class ConfigController {

    private static final Logger logger = LoggerFactory.getLogger(ConfigController.class);

    @Autowired
    private ConfigService configService;

    @RequestMapping(value = "config/index", method = RequestMethod.GET)
    public String index(Model model) {
        try {
            model.addAttribute("CURRENT_PAGE_TITLE", "config/index");
            return "config/index";
        } catch (SysServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error("ConfigController.index", e);
            throw new SysControllerException(e);
        }
    }

    @RequestMapping(value = "config/query.json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String,Object>> query(String cfgName, Integer pageNum, Integer pageSize) {
        Map<String,Object> result = Maps.newHashMap();
        try {
            Page<Config> page = configService.selectByCfgName(cfgName, pageNum, pageSize);
            result.put("code",0);
            result.put("msg","");
            result.put("count",page.getTotal());
            result.put("data",page.getResult());
            return ResponseEntity.ok(result);
        } catch (SysServiceException e) {
            result.put("code",500);
            result.put("msg",e.getMessage());
            result.put("count",0);
            result.put("data", Lists.newArrayList());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        } catch (Exception e) {
            logger.error("ConfigController.query", e);
            result.put("code",500);
            result.put("msg",e.getMessage());
            result.put("count",0);
            result.put("data", Lists.newArrayList());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);

        }
    }

    @RequestMapping(value = "config/create", method = RequestMethod.GET)
    public String create(Config config, Model model) {
        try {
            model.addAttribute("CURRENT_PAGE_TITLE", "config/create");
            config.setCruser("system");
            config.setCrtime(DateTime.now().toDate());
            model.addAttribute(config);
            return "config/create";
        } catch (SysServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error("ConfigController.create", e);
            throw new SysControllerException();
        }
    }

    @RequestMapping(value = "config/save", method = RequestMethod.POST)
    public String save(Config record) {
        try {
            configService.insertSelective(record);
            return "redirect:/config/index";
        } catch (SysServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error("ConfigController.save", e);
            throw new SysControllerException();
        }
    }

    @RequestMapping(value = "config/edit", method = RequestMethod.GET)
    public String edit(Long id, Model model) {
        try {
            model.addAttribute("CURRENT_PAGE_TITLE", "config/edit");
            model.addAttribute(configService.selectByPrimaryKey(id));
            return "config/edit";
        } catch (SysServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error("ConfigController.edit", e);
            throw new SysControllerException();
        }
    }

    @RequestMapping(value = "config/update", method = RequestMethod.POST)
    public String update(Config record) {
        try {
            configService.updateByPrimaryKeySelective(record);
            return "redirect:/config/index";
        } catch (SysServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error("ConfigController.update", e);
            throw new SysControllerException();
        }
    }

    @RequestMapping(value = "config/remove.json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String,Object>>  remove(Long id) {
        Map<String,Object> result = Maps.newHashMap();
        try {
            int aff = configService.removeByPrimaryKey(id);

            result.put("code",0);
            result.put("msg","");
            result.put("data",aff);
            return ResponseEntity.ok(result);
        } catch (SysServiceException e) {
            result.put("code",500);
            result.put("msg",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        } catch (Exception e) {
            logger.error("ConfigController.remove", e);
            result.put("code",500);
            result.put("msg",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

    @RequestMapping(value = "config/batch-remove.json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String,Object>>  batchRemove(@RequestBody List<Long> ids) {
        Map<String,Object> result = Maps.newHashMap();
        try {
            logger.info("sys/config/batch-remove ids:{}", Joiner.on(',').join(ids));
            int aff = configService.batchRemoveByPrimaryKey(ids);
            result.put("code",0);
            result.put("msg","");
            result.put("data",aff);
            return ResponseEntity.ok(result);
        } catch (SysServiceException e) {
            result.put("code",500);
            result.put("msg",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        } catch (Exception e) {
            logger.error("ConfigController.batchRemove", e);
            result.put("code",500);
            result.put("msg",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }
}
