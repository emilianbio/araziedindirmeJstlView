package dao;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import forms.OpenLayer;

@Repository
public class OpenLayerDAOImpl implements OpenLayerDAO {

	@Autowired
	SessionFactory session;

	@Override
	@Transactional
	public void ekle(OpenLayer openLayer) {

		session.getCurrentSession().saveOrUpdate(openLayer);

	}

}
