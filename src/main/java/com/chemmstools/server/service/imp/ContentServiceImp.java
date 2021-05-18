package com.chemmstools.server.service.imp;


import com.chemmstools.server.beans.Content;
import com.chemmstools.server.mapper.ContentMapper;
import com.chemmstools.server.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentServiceImp implements ContentService {

    @Autowired
    private ContentMapper contentMapper;

    @Override
    public Content getContentById(Integer id) {
        return contentMapper.getContentById(id);
    }
}
