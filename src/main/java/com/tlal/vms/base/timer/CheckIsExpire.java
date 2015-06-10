package com.tlal.vms.base.timer;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.tlal.vms.base.enums.enumc.ISEmpireEnum;
import com.tlal.vms.vms.func.user.sbook.dao.SBookDAO;
import com.tlal.vms.vms.func.user.sbook.entity.SBook;

@Component
public class CheckIsExpire {
	@Resource
	private SBookDAO sBookDAO;
    
    public void checkIsExpire() {
    	Date now = new Date();
    	List<SBook> list = sBookDAO.findAllUnExpire();
    	if(list!=null&&list.size()!=0){
    		for(SBook book:list){
        		if(book.getEnddate().before(now)){
        			book.setIsexpire(ISEmpireEnum.Y.getEnValue());
        			sBookDAO.setExpire(book.getIdsbook().toString().trim());
        		}
        	}
    	}
    }
}
