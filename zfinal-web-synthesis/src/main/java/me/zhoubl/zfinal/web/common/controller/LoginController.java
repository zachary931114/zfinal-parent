package me.zhoubl.zfinal.web.common.controller;

import javax.servlet.http.HttpServletRequest;

import me.zhoubl.zfinal.common.CommonConfig;
import me.zhoubl.zfinal.common.ex.BizEx;
import me.zhoubl.zfinal.common.web.WebCommonConfig;
import me.zhoubl.zfinal.synthesis.entity.SysUser;
import me.zhoubl.zfinal.web.common.message.MessageEcho;
import me.zhoubl.zfinal.web.common.shiro.SysUserRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.base.Strings;

/**
 * Created by zhoubl on 2017/4/21.
 */
@Controller
@RequestMapping("/admin")
public class LoginController {

	@Autowired
	private MessageEcho messageEcho;

	@Autowired
	private SysUserRealm sysUserRealm;

	@RequestMapping("/login")
	public String login(ModelMap map) {
		map.put("projectUrl", CommonConfig.sysMap.get("projectUrl"));
		return "/login";
	}

	@RequestMapping("/goLogin")
	public String goLogin(HttpServletRequest request, @RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("yzCode") String yzCode, ModelMap map) {
		try {

			String kaptchaCode = (String) request.getSession()
					.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
			if (kaptchaCode == null || !kaptchaCode.equalsIgnoreCase(yzCode)) {
				throw new BizEx("验证码错误");
			}

			Subject subject = SecurityUtils.getSubject();
			if (!Strings.isNullOrEmpty(username)) {
				sysUserRealm.clearCache(username);
			}
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			token.setRememberMe(false);
			subject.login(token);
			SysUser user = (SysUser) subject.getSession().getAttribute(WebCommonConfig.USERINFO);
			if (user != null) {
				messageEcho.logout(user.getId(), "该用户已在别处登录!");
			}
			return "redirect:/admin/main";
		} catch (BizEx e) {
			map.put("errorInfo", e.getMessage());
			map.put("projectUrl", CommonConfig.sysMap.get("projectUrl"));
			return "/login";
		} catch (UnknownAccountException e) {
			map.put("errorInfo", "用户名不存在");
			map.put("projectUrl", CommonConfig.sysMap.get("projectUrl"));
			return "/login";
		} catch (LockedAccountException e) {
			map.put("errorInfo", "用户已禁用");
			map.put("projectUrl", CommonConfig.sysMap.get("projectUrl"));
			return "/login";
		} catch (IncorrectCredentialsException e) {
			map.put("errorInfo", "密码不正确");
			map.put("projectUrl", CommonConfig.sysMap.get("projectUrl"));
			return "/login";
		} catch (Exception e) {
			map.put("errorInfo", "账号异常");
			map.put("projectUrl", CommonConfig.sysMap.get("projectUrl"));
			return "/login";
		}
	}
}
