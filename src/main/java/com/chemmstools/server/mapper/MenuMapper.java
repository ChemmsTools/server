package com.chemmstools.server.mapper;


import com.chemmstools.server.beans.Menu;
import com.chemmstools.server.beans.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MenuMapper {
    @Select("SELECT * from tb_menu")
    List<Menu> getAll();

    @Select("SELECT id,language from tb_menu")
    List<Menu> getLangList();

    @Select("SELECT * from tb_menu where id=#{id} limit 1")
    Menu getMenuById(Integer id);


    @Select("SELECT * from tb_menu where language=#{language} limit 1")
    Menu getMenuByLang(String language);
}
