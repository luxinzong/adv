package com.suma.controller;

import com.suma.pojo.AdvType;
import com.suma.pojo.AdvTypeExample;
import com.suma.service.AdvTypeService;
import com.suma.utils.Result;
import com.suma.vo.AdvTypeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/10/15
 * @description:
 */

@RestController
@RequestMapping("type")
public class AdvTypeController {

    @Autowired
    private AdvTypeService advTypeService;

    /**
     * @return 获取广告类型，包装成一个VO，方便前端级联显示
     */
    @RequestMapping("getType")
    public Result getAdvType() {
        Result result = new Result();

        Set<AdvTypeVO> vos = new HashSet<>();
        List<AdvType> advTypes = advTypeService.findALL();
        if (advTypes != null) {
            //获取父类型
            for (AdvType advType : advTypes) {
                AdvTypeVO vo = new AdvTypeVO();
                vo.setLabel(advType.getAdvtypename());
                vo.setValue(advType.getAdvtype());
                vos.add(vo);
            }
        }

        //添加子类型
        for (AdvTypeVO vo : vos) {
            Set<AdvTypeVO> subVos = new HashSet<>();
            //获取子类型
            AdvTypeExample example = new AdvTypeExample();
            example.createCriteria().andAdvtypeEqualTo(vo.getValue());
            List<AdvType> subTypes = advTypeService.selectByExample(example);

            //添加到父类型的children属性中
            if (subTypes != null) {
                for (AdvType subType : subTypes) {
                    AdvTypeVO subVO = new AdvTypeVO();
                    subVO.setLabel(subType.getAdvsubtypename());
                    subVO.setValue(subType.getAdvsubtype());
                    subVO.setId(subType.getId());
                    subVos.add(subVO);
                }
            }
            vo.setChildren(subVos);
        }


        result.setResultCode(0);
        result.setResultData(vos);
        return result;
    }
}
