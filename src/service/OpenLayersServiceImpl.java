package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.OpenLayerDAO;
import forms.OpenLayer;

@Service
public class OpenLayersServiceImpl implements OpenLayersService {
	@Autowired
	OpenLayerDAO openLayerDAO;

	@Override
	public void ekle(OpenLayer openLayer) {
		openLayerDAO.ekle(openLayer);
	}

}
