package me.zhoubl.zfinal.web.common;

import java.util.Date;

import me.zhoubl.zfinal.common.web.WebCommonConfig;
import me.zhoubl.zfinal.synthesis.entity.SysUser;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.shiro.SecurityUtils;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;

/**
 * Created by zhoubl
 */
public class CommonMetaObjectHandler extends MetaObjectHandler {

	@Override
	public void insertFill(MetaObject metaObject) {

		SysUser user = null;
		try {
			user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute(WebCommonConfig.USERINFO);
		} catch (Exception e) {
		}

		try {
			Object version = getFieldValByName("version", metaObject);
			if (null == version) {
				setFieldValByName("version", 0, metaObject);
			}
		} catch (Exception e) {
		}

		try {
			Object createTime = getFieldValByName("createTime", metaObject);
			if (null == createTime) {
				setFieldValByName("createTime", new Date(), metaObject);
			}
		} catch (Exception e) {
		}

		try {
			Object updateTime = getFieldValByName("updateTime", metaObject);
			if (null == updateTime) {
				setFieldValByName("updateTime", new Date(), metaObject);
			}
		} catch (Exception e) {
		}

		try {
			Object status = getFieldValByName("status", metaObject);
			if (null == status) {
				setFieldValByName("status", 1, metaObject);
			}
		} catch (Exception e) {
		}

		try {
			Object createrId = getFieldValByName("createrId", metaObject);
			if (createrId == null && user != null) {
				setFieldValByName("createrId", user.getId(), metaObject);
			}
		} catch (Exception e) {
		}

		try {
			Object updaterId = getFieldValByName("updaterId", metaObject);
			if (updaterId == null && user != null) {
				setFieldValByName("updaterId", user.getId(), metaObject);
			}
		} catch (Exception e) {
		}

	}

	@Override
	public void updateFill(MetaObject metaObject) {

		SysUser user = null;
		try {
			user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute(WebCommonConfig.USERINFO);
		} catch (Exception e) {
		}

		try {
			Object updaterId = getFieldValByName("updaterId", metaObject);
			if (updaterId == null && user != null) {
				setFieldValByName("updaterId", user.getId(), metaObject);
			}
		} catch (Exception e) {
		}

		try {
			setFieldValByName("updateTime", new Date(), metaObject);
		} catch (Exception e) {
		}

	}
}
