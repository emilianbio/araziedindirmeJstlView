/**
 * 
 */
package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.UserRolesDAO;
import forms.UserRoles;

/**
 * @author lenovo
 *
 */
@Service
public class UserRolesServiceImpl implements UserRolesService {

	@Autowired
	UserRolesDAO userRolesDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.UserRolesService#ekle(forms.UserRoles)
	 */
	@Override
	public void ekle(UserRoles roles) {
		// TODO Auto-generated method stub
		userRolesDAO.ekle(roles);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.UserRolesService#roleList()
	 */
	@Override
	public List<UserRoles> roleList() {
		// TODO Auto-generated method stub
		return userRolesDAO.roleList();
	}
}
