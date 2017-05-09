/**
 * 
 */
package service;

import java.util.List;

import forms.UserRoles;

/**
 * @author lenovo
 *
 */
public interface UserRolesService {

	public void ekle(UserRoles roles);

	public List<UserRoles> roleList();

}
