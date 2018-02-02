package me.zhoubl.zfinal.web.common.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.zhoubl.zfinal.common.utils.UtilServlet;
import me.zhoubl.zfinal.common.utils.serialization.FastJsonSerializer;
import me.zhoubl.zfinal.common.web.dto.ReturnJson;
import me.zhoubl.zfinal.synthesis.api.SysPermissionService;
import me.zhoubl.zfinal.web.common.Constant;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.base.Strings;

/**
 * Created by zhoubl on 2017/6/25.
 */
public class UrlAccessControlFilter extends AccessControlFilter {

    protected static final Logger logger = LoggerFactory.getLogger(UrlAccessControlFilter.class);

    @Autowired
    private SysPermissionService sysPermissionService;

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        String path = getPathWithinApplication(servletRequest);
        if (!Strings.isNullOrEmpty(path)) {
            path = path.replace("/admin/", "");
            String[] paths = path.split("/");
            String permission = null;
            if (paths.length > 1) {
                permission = "PERMISSION_" + paths[0] + "_" + paths[1];
            } else {
                permission = "PERMISSION_" + paths[0];
            }
            if (sysPermissionService.getCountByCode(permission) > 0) {
                if (SecurityUtils.getSubject().isPermitted(permission) || SecurityUtils.getSubject().hasRole(Constant.ROLE_SUPPERADMIN)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        if (UtilServlet.isAjaxRequest((HttpServletRequest) servletRequest)) {
            sendJson((HttpServletResponse) servletResponse, FastJsonSerializer.toJSONString(new ReturnJson(false, "该用户角色没有操作权限", null)));
        } else {
            servletRequest.getRequestDispatcher("/WEB-INF/views/error/unauthorized.jsp").forward(servletRequest, servletResponse);
        }
        return false;

    }

    private void sendJson(HttpServletResponse response, String json) {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.print(json);
            out.flush();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                out.close();
            } catch (Exception e) {
                out = null;
            }
        }
    }
}
