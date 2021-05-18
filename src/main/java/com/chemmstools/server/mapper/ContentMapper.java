package com.chemmstools.server.mapper;

import com.chemmstools.server.beans.Content;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ContentMapper {

    @Select("SELECT * from tb_content where id=#{id} limit 1")
    Content getContentById(Integer id);
}
