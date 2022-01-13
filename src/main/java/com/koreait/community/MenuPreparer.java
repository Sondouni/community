package com.koreait.community;

import com.koreait.community.model.BoardCategoryEntity;
import com.koreait.community.model.SubMenuModelVO;
import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component("MenuPreparer")
public class MenuPreparer implements ViewPreparer {
    @Autowired
    private CommonMapper mapper;

    private List<BoardCategoryEntity> menuList;
    private List<SubMenuModelVO> subMenuList;

    @Override
    public void execute(Request request, AttributeContext attributeContext) {
        if (menuList==null){
            menuList = mapper.selMenuCategoryList();
        }
        attributeContext.putAttribute(Const.MENU_LIST,new Attribute(menuList),true);

        if(subMenuList==null){
            subMenuList=new ArrayList();
            subMenuList.add(new SubMenuModelVO("profile","Profile"));
            subMenuList.add(new SubMenuModelVO("modpassword","Change Password"));
        }
        attributeContext.putAttribute(Const.SUB_MENU_LIST,new Attribute(subMenuList),true);
    }
}
