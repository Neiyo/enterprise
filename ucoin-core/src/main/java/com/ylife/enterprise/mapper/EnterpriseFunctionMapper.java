package com.ylife.enterprise.mapper;


import com.ylife.enterprise.model.EnterpriseFunction;

import java.util.List;
import java.util.Set;

public interface EnterpriseFunctionMapper {

    int deleteByPrimaryKey(Long id);

    int insert(EnterpriseFunction record);

    int insertSelective(EnterpriseFunction record);

    EnterpriseFunction selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EnterpriseFunction record);

    int updateByPrimaryKey(EnterpriseFunction record);

    Long selectMaxCatalogByParentId(Long parentId);

    List<EnterpriseFunction> selectByParentIdForUpdate(Long parentId);

    List<EnterpriseFunction> selectByParentId(Long parentId);

    Set<Long> selectCatalogsByParentId(Long parentId);

}