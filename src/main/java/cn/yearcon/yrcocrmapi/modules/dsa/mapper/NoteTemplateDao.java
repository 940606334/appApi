package cn.yearcon.yrcocrmapi.modules.dsa.mapper;

import cn.yearcon.yrcocrmapi.modules.dsa.entity.NoteTemplate;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ayong
 * @create 2018-03-29 9:33
 **/
@Repository
public interface NoteTemplateDao {
    @Select("select * from app_note_template " +
            "where webid=#{webid}")
    List<NoteTemplate> findByWebid(@Param("webid") String webid);
}
