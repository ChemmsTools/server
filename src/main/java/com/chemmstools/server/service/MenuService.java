package com.chemmstools.server.service;


import com.chemmstools.server.beans.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MenuService {
    List<Menu> getAll();

    Menu getMenuById(Integer id);

    Menu getMenuByLang(String language);

    List<Menu> getLangList();
}
