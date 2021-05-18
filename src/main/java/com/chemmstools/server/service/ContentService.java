package com.chemmstools.server.service;

import com.chemmstools.server.beans.Content;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

public interface ContentService {

    Content getContentById(Integer id);
}
