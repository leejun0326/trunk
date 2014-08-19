package com.zhc.jpa.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhc.jpa.domain.SysUser;
import com.zhc.jpa.repository.SysUserRepository;
import com.zhc.jpa.repository.SysUserRepositoryImpl;
import com.zhc.jpa.service.SysUserService;
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserRepository sysUserRepository;
	
	@Autowired private SysUserRepositoryImpl sysUserRepositoryImpl;
	@Override
	@Transactional
	public void saveSysUser(SysUser user) {
		sysUserRepository.save(user);

	}

	@Override
	public SysUser findSysUserById(Integer id) {
		return sysUserRepository.findOne(id);
	}

	@Override
	@Transactional
	public void updateSysUser(SysUser user) {
		sysUserRepository.save(user);

	}

	@Override
	@Transactional
	public void deleteSysUserById(Integer id) {
		sysUserRepository.delete(id);

	}

	@Override
	public List<SysUser> findAll() {
		return sysUserRepository.findAll();
	}

	@Override
	@Transactional
	public void saveBatchSysUser(List<SysUser> userList) {
		sysUserRepository.save(userList);
		
	}

	@Override
	public boolean isExitsSysUserById(Integer id) {
		return sysUserRepository.exists(id);
	}

	@Override
	public long count() {
		return sysUserRepository.count();
	}

	@Override
	@Transactional
	public void deleteSysUser(SysUser user) {
		sysUserRepository.delete(user);
	}

	@Override
	@Transactional
	public void deleteAll() {
		sysUserRepository.deleteAll();
	}

	@Override
	@Transactional
	public void deleteBatch(List<SysUser> userList) {
		//sysUserRepository.deleteInBatch(userList);
		sysUserRepository.delete(userList);
	}

	@Override
	public Iterable<SysUser> findAll(Sort sort) {
		//id 倒叙
		Iterable<SysUser> iterable=sysUserRepository.findAll(new Sort(Direction.DESC, "id"));
		
		//id ,account倒叙
		Iterable<SysUser> iterable1=sysUserRepository.findAll(new Sort(Direction.DESC, Arrays.asList("id","account")));
		
		//id倒叙 ,account升序
		Iterable<SysUser> iterable2=sysUserRepository.findAll(new Sort(Arrays.asList(new Order(Direction.DESC,"id"),new Order(Direction.ASC,"account"))));
		return iterable;
	}

	@Override
	public Page<SysUser> findPage() {
		int page=0;
		int size=5;
		//分页不排序
		Page<SysUser> pagedata= sysUserRepository.findAll(new PageRequest(page,size));
		
		//分页指定排序
		Page<SysUser> pagedata1= sysUserRepository.findAll(new PageRequest(page,size,new Sort(Direction.DESC, "id")));
		return pagedata;
	}

	@Override
	public SysUser findByAccount(String account) {
		return sysUserRepository.findByAccount(account);
	}

	@Override
	public SysUser findByAccountLike(String account) {
		return sysUserRepository.findByAccountLike(account);
	}

	@Override
	public Page<SysUser> findByAccount(String account, Pageable pageable) {
		int page=0;
		int size=5;
		return sysUserRepository.findByAccount(account, new PageRequest(page,size,new Sort(Direction.DESC, "id")));
	}

	@Override
	public List<SysUser> findByAccountIn(List<String> accounts) {
		return sysUserRepository.findByAccountIn(accounts);
	}

	@Override
	public SysUser findByAccountAndPassword(String account, String password) {
		return sysUserRepository.findByAccountAndPassword(account, password);
	}

	@Override
	public SysUser findUserByQuery(String account) {
		return sysUserRepository.findUserByQuery(account);
	}

	@Override
	public SysUser findUserByQueryParam(String account) {
		return sysUserRepository.findUserByQueryParam(account);
	}

	@Override
	public Page<SysUser> getByCondition(SysUser sysUser) {
		return sysUserRepositoryImpl.getByCondition(sysUser);
	}

	@Override
	public List<SysUser> queryByMutiCondition( final SysUser user) {
		return sysUserRepository.findAll(new Specification<SysUser>() {
			//CriteriaBuilder也作为Predicate实例的工厂，通过调用CriteriaBuilder 的条件方法
			//（ equal，notEqual， gt， ge，lt， le，between，like等）创建Predicate对象
			@Override
			public Predicate toPredicate(Root<SysUser> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				
				List<Predicate> list = new ArrayList<Predicate>();  
				String account=user.getAccount();
				if(StringUtils.isNotBlank(account)){
					//构建简单的Predicate
					Predicate pdAccount=cb.like(root.get("account").as(String.class), "%"+account+"%");
					list.add(pdAccount);
				}
				//.......more conditions
				
				Predicate[] p = new Predicate[list.size()];  
			    return cb.and(list.toArray(p));  
			}
		},new Sort(Direction.DESC, "id"));
	}

	@Override
	public Page<SysUser> queryPageByMutiCondition( final SysUser user) {
		return sysUserRepository.findAll(new Specification<SysUser>() {

			@Override
			public Predicate toPredicate(Root<SysUser> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				List<Predicate> list = new ArrayList<Predicate>();  
				String account=user.getAccount();
				if(StringUtils.isNotBlank(account)){
					//构建简单的Predicate
					Predicate pdAccount=cb.like(root.get("account").as(String.class), "%"+account+"%");
					list.add(pdAccount);
				}
				//.......more conditions
				
				Predicate[] p = new Predicate[list.size()];  
			    return cb.and(list.toArray(p));  
			}
		}, new PageRequest(0, 4, new Sort(Direction.DESC, "id")));
	}

}
