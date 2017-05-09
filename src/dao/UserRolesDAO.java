/**
 * 
 */
package dao;

import java.util.List;

import forms.UserRoles;

/**
 * @author lenovo
 *
 */
public interface UserRolesDAO {

	public void ekle(UserRoles roles);
	 public List<UserRoles> roleList();

}
