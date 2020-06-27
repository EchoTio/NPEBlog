package com.smallclover.nullpointerexception.controller.admin;

import com.smallclover.nullpointerexception.model.Setting;
import com.smallclover.nullpointerexception.service.setting.SettingService;
import com.smallclover.nullpointerexception.service.site.SiteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 系统设置
 * @author Amadeus
 * @date 2020-05-05
 */
@Controller
@RequestMapping("/admin/setting")
@Slf4j
public class SettingController {

    @Autowired
    SettingService settingService;

    @GetMapping("")
    public String index(Model model){
        Setting setting = settingService.getAllSetting();
        model.addAttribute("setting", setting);
        return "/admin/setting";
    }

    @PostMapping("/update")
    public @ResponseBody ResponseEntity update(@RequestParam("id") String id,
                                               @RequestParam("modifiedSiteName") String siteName,
                                               @RequestParam("modifiedSiteDesc") String siteDesc,
                                               @RequestParam("modifiedAppVersion") String appVersion,
                                               @RequestParam("modifiedGithub") String github){
        Setting setting = new Setting();
        setting.setId(Long.parseLong(id));
        setting.setAppVersion(appVersion);
        setting.setSiteName(siteName);
        setting.setSiteDesc(siteDesc);
        setting.setGithub(github);
        boolean result = settingService.updateSystemSetting(setting);
        if(result){
            return ResponseEntity.ok().body("success");
        }
        return ResponseEntity.badRequest().body("error");
    }
}
