package com.chemmstools.server.service.imp;


import com.chemmstools.server.beans.Menu;
import com.chemmstools.server.mapper.MenuMapper;
import com.chemmstools.server.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImp implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getAll() {
        return menuMapper.getAll();
    }

    @Override
    public Menu getMenuById(Integer id) {
        return menuMapper.getMenuById(id);
    }

    @Override
    public Menu getMenuByLang(String language) {
        return menuMapper.getMenuByLang(language);
    }

    @Override
    public List<Menu> getLangList() {
        return menuMapper.getLangList();
    }
}
