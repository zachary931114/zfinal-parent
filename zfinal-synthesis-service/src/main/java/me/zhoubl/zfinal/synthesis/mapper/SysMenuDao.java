package me.zhoubl.zfinal.synthesis.mapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import me.zhoubl.zfinal.synthesis.entity.SysMenu;
import me.zhoubl.zfinal.synthesis.entity.SysUser;
import org.apache.ibatis.annotations.Param;


/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zhoubl
 * @since 2017-05-17
 */
public interface SysMenuDao extends BaseMapper<SysMenu> {
    public List<SysMenu> getAllByUser(@Param("user") SysUser user);
}