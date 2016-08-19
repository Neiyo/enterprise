package com.ylife.enterprise.mapper;


import com.ylife.enterprise.mapper.pojo.ManagerWithAuthNameResult;
import com.ylife.enterprise.model.EnterpriseManager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EnterpriseManagerMapper {

    int deleteByPrimaryKey(Long id);

    int deleteByEnterpriseId(Long enterpriseId);

    int insert(EnterpriseManager record);

    int insertSelective(EnterpriseManager record);

    EnterpriseManager selectByPrimaryKey(Long id);

    EnterpriseManager selectByUsername(String username);

    EnterpriseManager selectByEnterpriseIdAndIsPrimary(@Param("enterpriseId") Long enterpriseId, @Param("isPrimary") Boolean isPrimary);

    int updateByPrimaryKeySelective(EnterpriseManager record);

    int updateByPrimaryKey(EnterpriseManager record);

    /**
     * 获取所有管理员
     * @param enterpriseId
     * @return
     */
    List<EnterpriseManager> selectByEnterpriseId(Long enterpriseId);

    /**
     * 获取管理员manager的所有权限authoritys
     * @param enterpriseId
     * @return
     */
    List<ManagerWithAuthNameResult> selcetManagerResultByEnterpriseId(Long enterpriseId);

    /**
     * 修改管理员密码
     * @param id
     * @param password
     * @return
     */
    int updatePasswordByManagerId(@Param("id") Long id, @Param("password") String password);

    /**
     * 编辑管理员
     * @param id
     * @param staffname
     * @param cellphone
     * @param nonDisabled
     * @return
     */
    int updateManagerBuff(@Param("id") Long id,
                          @Param("staffname") String staffname,
                          @Param("cellphone") String cellphone,
                          @Param("nonDisabled") Boolean nonDisabled);
}