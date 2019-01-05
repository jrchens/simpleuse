package cn.com.simpleuse.sys.controller;

import cn.com.simpleuse.sys.domain.Config;
import cn.com.simpleuse.sys.exception.SysControllerException;
import cn.com.simpleuse.sys.service.ConfigService;
import com.github.pagehelper.Page;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
//@RequestMapping(value = "system")
public class ConfigController {

    private static final Logger logger = LoggerFactory.getLogger(ConfigController.class);

    @Autowired
    private ConfigService configService;

    @RequestMapping(value = "config/index", method = RequestMethod.GET)
    public String index(Model model) {
        try {
            model.addAttribute("CURRENT_PAGE_TITLE", "config/index");
            return "config/index";
        } catch (Exception e) {
            logger.error("ConfigController.index", e);
            throw new SysControllerException();
        }
    }

    @RequestMapping(value = "config/query", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Page<Config>> query(String cfgName, Integer pageNum, Integer pageSize) {
        try {
            return ResponseEntity.ok(configService.selectByCfgName(cfgName, pageNum, pageSize));
        } catch (Exception e) {
            logger.error("ConfigController.query", e);
            throw new SysControllerException();
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
        } catch (Exception e) {
            logger.error("ConfigController.update", e);
            throw new SysControllerException();
        }
    }

    @RequestMapping(value = "config/remove", method = RequestMethod.POST)
    public String remove(Long id) {
        try {
            configService.removeByPrimaryKey(id);
            return "redirect:/config/index";
        } catch (Exception e) {
            logger.error("ConfigController.remove", e);
            throw new SysControllerException();
        }
    }

    @RequestMapping(value = "config/batch-remove", method = RequestMethod.POST)
    public String batchRemove(List<Long> ids) {
        try {
            configService.batchRemoveByPrimaryKey(ids);
            return "redirect:/config/index";
        } catch (Exception e) {
            logger.error("ConfigController.batchRemove", e);
            throw new SysControllerException();
        }
    }
}
