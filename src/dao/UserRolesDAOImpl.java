/**
 * 
 */
package dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import forms.UserRoles;

/**
 * @author lenovo
 *
 */
@Repository
public class UserRolesDAOImpl implements UserRolesDAO {
	@Autowired
	SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.UserRolesDAO#ekle(forms.UserRoles)
	 */
	@Override
	@Transactional
	public void ekle(UserRoles roles) {

		sessionFactory.getCurrentSession().saveOrUpdate(roles);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.UserRolesDAO#roleList()
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<UserRoles> roleList() {
		Criteria criteriaRoleList = sessionFactory.getCurrentSession().createCriteria(UserRoles.class);

		return criteriaRoleList.list();
	}

}
