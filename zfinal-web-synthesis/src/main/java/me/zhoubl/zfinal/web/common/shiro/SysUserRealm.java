package me.zhoubl.zfinal.web.common.shiro;

import java.util.ArrayList;
import java.util.List;

import me.zhoubl.zfinal.common.web.WebCommonConfig;
import me.zhoubl.zfinal.synthesis.api.CommonFilesService;
import me.zhoubl.zfinal.synthesis.api.SysRolePermissionService;
import me.zhoubl.zfinal.synthesis.api.SysUserRoleService;
import me.zhoubl.zfinal.synthesis.api.SysUserService;
import me.zhoubl.zfinal.synthesis.entity.CommonFiles;
import me.zhoubl.zfinal.synthesis.entity.SysRolePermission;
import me.zhoubl.zfinal.synthesis.entity.SysUser;
import me.zhoubl.zfinal.synthesis.entity.SysUserRole;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.base.Strings;

/**
 * Created by zhoubl on 2017/2/17.
 */
public class SysUserRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysRolePermissionService sysRolePermissionService;

    @Autowired
    private CommonFilesService commonFilesService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getSession().getAttribute(WebCommonConfig.USERINFO);
        List<SysUserRole> sysUserRoles = sysUserRoleService.getAllByRelevanceId(sysUser.getId());
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        List<String> roles = new ArrayList<String>();
        List<String> permissions = new ArrayList<String>();
        StringBuilder roleNames = new StringBuilder();
        for (SysUserRole sysUserRole : sysUserRoles) {
            roles.add(sysUserRole.getSysRoleCode());
            roleNames.append("," + sysUserRole.getSysRoleName());
            List<SysRolePermission> sysRolePermissions = sysRolePermissionService.getAllByRelevanceId(sysUserRole.getSysRoleId());
            for (SysRolePermission sysRolePermission : sysRolePermissions) {
                permissions.add(sysRolePermission.getSysPermissionCode());
            }
        }
        authorizationInfo.addRoles(roles);
        authorizationInfo.addStringPermissions(permissions);

        if (roleNames.length() > 0) {
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession();
            session.setAttribute(WebCommonConfig.ROLEINFO, roleNames.substring(1));
        }

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        SysUser user = sysUserService.getByCode(token.getUsername());
        if (user == null) {
            throw new UnknownAccountException();
        }
        if (user.getStatus() != 1) {
            throw new LockedAccountException();
        }

        if (!Strings.isNullOrEmpty(user.getFileUuid())) {
            CommonFiles cf = commonFilesService.getOneByUUID(user.getFileUuid(), "headPortrait");
            if (cf != null) {
                user.setHeadPortraitFilePath(cf.getFilePath());
            }
        }

        AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getCode(),
                user.getPwd(), this.getName());

        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute(WebCommonConfig.USERINFO, user);

        return authcInfo;
    }

    public void clearCache(String principal) {
        getAuthenticationCache().remove(principal);
        getAuthorizationCache().remove(principal);
    }


}
