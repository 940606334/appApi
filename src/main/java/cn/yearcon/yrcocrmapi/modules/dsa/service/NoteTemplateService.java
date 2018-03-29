package cn.yearcon.yrcocrmapi.modules.dsa.service;

import cn.yearcon.yrcocrmapi.modules.dsa.entity.NoteTemplate;
import cn.yearcon.yrcocrmapi.modules.dsa.mapper.EmpDao;
import cn.yearcon.yrcocrmapi.modules.dsa.mapper.NoteTemplateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ayong
 * @create 2018-03-29 9:39
 **/
@Service
public class NoteTemplateService {
    @Autowired
    private NoteTemplateDao noteTemplateDao;
    @Autowired
    private EmpDao empDao;

    public List<NoteTemplate> findByUsername(String username){
        Integer webid=empDao.findWebidByUsername(username);
        List<NoteTemplate> list = noteTemplateDao.findByWebid(webid.toString());
        return list;
    }
}
