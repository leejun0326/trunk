package com.zhc.jpa.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.zhc.jpa.domain.SysUser;

/**
 * 系统用户service
 * @author zhouhuachang@myhexin.com
 * time:2014-1-10
 */
public interface SysUserService {

	//==================================**********************===============================================//
    //JpaRepository的基本操作:实际开发中这些方法不用写出来，serviceimpl直接注入实现JpaRepository的接口即可，在此只是演示功能//
	//==================================**********************===============================================//
    
	/**
     * 根据id查找用户
     * @param id
     * @return
     */
    SysUser findSysUserById(Integer id);
    
    /**
     * 根据id判断用户是否存在
     * @return
     */
    boolean isExitsSysUserById(Integer id);
   
    /**
     * 查询实体数量 
     * @return
     */
    long count();
    
    /**
     * 查询所有
     * @return
     */
    List<SysUser>findAll();
    
    /**
     * 查询所有并排序
     * @param sort
     * @return
     */
    Iterable<SysUser>findAll(Sort sort);
    
    /**
     * 基本分页查询
     * desc:Pageable接口的实现类是PageRequest，Page接口的实现类是PageImpl
     * page对象含有List<T> getContent()方法，可以去除对应数据
     * @param sort
     * @return
     */
    Page<SysUser>findPage();
    
    /**
     * 更新用户
     * @param user
     */
    void updateSysUser(SysUser user);
    
	/**
     * 保存用户
     * @param user
     */
    void saveSysUser(SysUser user);
    
    /**
     * 批量保存用户
     * @param user
     */
    void saveBatchSysUser(List<SysUser> userList);
    
    /**
     * 根据ID删除用户
     * @param id
     */
    void deleteSysUserById(Integer id);
    
    /**
     * 删除用户(删除一个实体 )
     * @param id
     */
    void deleteSysUser(SysUser user);
    
    /**
     * 删除所有实体(慎用)
     */
    void deleteAll();
    
    /**
     * 删除一个实体的集合 
     * @param userList
     */
    void deleteBatch(List<SysUser>userList);
    
	//==================================**********************===============================================//
    //JpaRepository自定义查询：直接在接口(实现JpaRepository的接口dao)中定义查询方法，如果是符合规范的，可以不用写实现(相对dao),
    //这部分实际开发中也不用写出来,serviceimpl直接注入实现JpaRepository的接口即可，在此只是演示功能//
	//==================================**********************===============================================//
    
    /**
     * 实体account属性查询(条件属性以首字母大写)，作为查询条件的属性一定要遵守驼峰命名规范
     * JPQL:from SysUser u where u.account=?1
     */
    SysUser findByAccount(String account);
    
    /**
     * 实体account属性模糊查询(条件属性以首字母大写)，作为查询条件的属性一定要遵守驼峰命名规范
     * JPQL:from SysUser u where u.account like ?1
     */
    SysUser findByAccountLike(String account);
    
    /**
     * 实体account属性分页查询(条件属性以首字母大写)，作为查询条件的属性一定要遵守驼峰命名规范
     * page对象含有List<T> getContent()方法，可以去除对应数据
     */
    Page<SysUser>findByAccount(String account,Pageable pageable);
    
    /**
     * 实体account属性查询(条件属性以首字母大写)，作为查询条件的属性一定要遵守驼峰命名规范
     * JPQL:from SysUser u where u.account in ?1
     */
    List<SysUser> findByAccountIn(List<String> accounts);
    
    /**
     * 实体account,password属性查询(条件属性以首字母大写)，作为查询条件的属性一定要遵守驼峰命名规范
     * JPQL:from SysUser u where u.account=?1 and u.password=?2
     */
    SysUser findByAccountAndPassword(String account,String password);
    
    
    /**
     * 方法的参数个数必须和@Query里面需要的参数个数一致 
     */
    SysUser findUserByQuery(String account);
    
    /**
     * 方法的参数个数必须和@Query里面需要的参数个数一致(指定别名) 
     */
    SysUser findUserByQueryParam(String account);
    
    
    //=====================自定义个性化查询，这个需要根据api自己写实现了=======================//
    /**
     * 自定义impl方法实现query
     * @param sysUser
     * @return
     */
    Page<SysUser> getByCondition(SysUser sysUser);
    
    /**
     * data jpa 原生api实现多条件查询
     * 不建议使用，太繁琐，此为功能演示(实际开发中建议使用自定义JPQL或是结合使用springjdbc)
     * @param user
     * @return
     */
    public List<SysUser> queryByMutiCondition(final SysUser user);
    
    /**
     * data jpa 原生api实现多条件查询(分页)
     * 不建议使用，太繁琐，此为功能演示(实际开发中建议使用自定义JPQL或是结合使用springjdbc)
     * @param user
     * @return
     */
    public Page<SysUser> queryPageByMutiCondition(final SysUser user);
    
    
}
