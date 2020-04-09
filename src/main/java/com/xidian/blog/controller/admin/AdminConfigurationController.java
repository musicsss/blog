package com.xidian.blog.controller.admin;

import com.xidian.blog.service.BlogConfigService;
import com.xidian.blog.utils.Result;
import com.xidian.blog.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 米
 * @date 2020/4/3
 */
@Controller
@RequestMapping("/admin")
public class AdminConfigurationController {
    @Autowired
    BlogConfigService blogConfigService;

    @GetMapping("/configurations")
    public String list(HttpServletRequest request){
        request.setAttribute("path", "configurations");
        request.setAttribute("configurations", blogConfigService.getAllConfigs());
        return "admin/configuration";
    }

    @PostMapping("/configurations/website")
    @ResponseBody
    public Result website(@RequestParam(value = "websiteName", required = false) String websiteName,
                          @RequestParam(value = "websiteDescription", required = false) String websiteDescription,
                          @RequestParam(value = "websiteLogo", required = false) String websiteLogo,
                          @RequestParam(value = "websiteIcon", required = false) String websiteIcon) {
        int updateResult = 0;
        if (!StringUtils.isEmpty(websiteName)) {
            updateResult += blogConfigService.updateConfig("websiteName", websiteName);
        }
        if (!StringUtils.isEmpty(websiteDescription)) {
            updateResult += blogConfigService.updateConfig("websiteDescription", websiteDescription);
        }
        if (!StringUtils.isEmpty(websiteLogo)) {
            updateResult += blogConfigService.updateConfig("websiteLogo", websiteLogo);
        }
        if (!StringUtils.isEmpty(websiteIcon)) {
            updateResult += blogConfigService.updateConfig("websiteIcon", websiteIcon);
        }
        return ResultGenerator.genSuccessResult(updateResult > 0);
    }

    @PostMapping("/configurations/userInfo")
    @ResponseBody
    public Result userInfo(@RequestParam(value = "yourAvatar", required = false) String yourAvatar,
                           @RequestParam(value = "yourName", required = false) String yourName,
                           @RequestParam(value = "yourEmail", required = false) String yourEmail) {
        int updateResult = 0;
        if (!StringUtils.isEmpty(yourAvatar)) {
            updateResult += blogConfigService.updateConfig("yourAvatar", yourAvatar);
        }
        if (!StringUtils.isEmpty(yourName)) {
            updateResult += blogConfigService.updateConfig("yourName", yourName);
        }
        if (!StringUtils.isEmpty(yourEmail)) {
            updateResult += blogConfigService.updateConfig("yourEmail", yourEmail);
        }
        return ResultGenerator.genSuccessResult(updateResult > 0);
    }

    @PostMapping("/configurations/footer")
    @ResponseBody
    public Result footer(@RequestParam(value = "footerAbout", required = false) String footerAbout,
                         @RequestParam(value = "footerICP", required = false) String footerICP,
                         @RequestParam(value = "footerCopyRight", required = false) String footerCopyRight,
                         @RequestParam(value = "footerPoweredBy", required = false) String footerPoweredBy,
                         @RequestParam(value = "footerPoweredByURL", required = false) String footerPoweredByURL) {
        int updateResult = 0;
        if (!StringUtils.isEmpty(footerAbout)) {
            updateResult += blogConfigService.updateConfig("footerAbout", footerAbout);
        }
        if (!StringUtils.isEmpty(footerICP)) {
            updateResult += blogConfigService.updateConfig("footerICP", footerICP);
        }
        if (!StringUtils.isEmpty(footerCopyRight)) {
            updateResult += blogConfigService.updateConfig("footerCopyRight", footerCopyRight);
        }
        if (!StringUtils.isEmpty(footerPoweredBy)) {
            updateResult += blogConfigService.updateConfig("footerPoweredBy", footerPoweredBy);
        }
        if (!StringUtils.isEmpty(footerPoweredByURL)) {
            updateResult += blogConfigService.updateConfig("footerPoweredByURL", footerPoweredByURL);
        }
        return ResultGenerator.genSuccessResult(updateResult > 0);
    }
}
