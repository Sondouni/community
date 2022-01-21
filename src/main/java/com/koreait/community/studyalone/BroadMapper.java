package com.koreait.community.studyalone;

import com.koreait.community.studyalone.model.BroadMatVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BroadMapper {
    List<BroadMatVO> selBraodMatList();
}
