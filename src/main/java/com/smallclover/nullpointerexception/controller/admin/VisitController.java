package com.smallclover.nullpointerexception.controller.admin;

import com.smallclover.nullpointerexception.dto.VisitDTO;
import com.smallclover.nullpointerexception.dto.VisitReqDTO;
import com.smallclover.nullpointerexception.service.visit.VisitService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @Author: Amadeus
 * @Date: 2020/6/3 21:30
 */
@Controller
@RequestMapping("/test")
@AllArgsConstructor
public class VisitController {

    VisitService visitService;

    @RequestMapping("/visit")
    public @ResponseBody VisitDTO visit(VisitReqDTO reqDTO){
        VisitDTO visitDTO = new VisitDTO();
        visitService.build("blog");
        visitDTO.setTime(visitService.getArticleAccessRecord());
        visitDTO.setAritcleCnt(visitService.getIpAccessRecordForArticleAll());
        return visitDTO;
    }
}
