package com.suma.controller;

import com.suma.pojo.AdvMaterial;
import com.suma.pojo.AdvMaterialExample;
import com.suma.pojo.InfoMaterial;
import com.suma.pojo.InfoMaterialExample;
import com.suma.service.AdvMaterialService;
import com.suma.service.InfoMaterialService;
import com.suma.utils.Result;
import com.suma.vo.InfoMaterialVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther: luxinzong
 * @date: 2018/10/16
 */

@RestController
@RequestMapping(value = "infoMaterial", produces = "application/json;charset=utf-8")
public class InfoMaterialController {

    @Autowired
    InfoMaterialService infoMaterialService;

    @Autowired
    AdvMaterialService advMaterialService;

    /**
     * 添加广告信息对应资源
     * @param infoMaterial
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Result addInfoMaterial(@RequestBody InfoMaterial infoMaterial) {
        Result result = new Result();
        try {
            if (infoMaterial != null) {
                if (infoMaterial.getAdvInfoId() == null || infoMaterial.getMaterialId() == null
                        || infoMaterial.getDuration() == null || infoMaterial.getSequence() == null) {
                    result.setResultCode(1);
                    result.setResultDesc("缺少参数");
                    result.setResultData(null);
                    return result;
                }
                infoMaterialService.save(infoMaterial);
                result.setResultCode(0);
                result.setResultDesc("添加成功");
                result.setResultData(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Result.error("添加失败");
        }
        return result;
    }

    /**
     * 查询广告信息对应资源
     * @param advInfoId
     * @return
     */
    @RequestMapping(value = "query", method = RequestMethod.POST)
    public Result queryInfoMaterial(@RequestBody Long advInfoId) {
        Result result = new Result();
        try {
            InfoMaterialExample example = new InfoMaterialExample();
            if (advInfoId != null) {
                example.createCriteria().andAdvInfoIdEqualTo(advInfoId);
            } else {
                result.setResultDesc("缺少广告参数");
                result.setResultCode(1);
                return result;
            }
            List<InfoMaterial> infoMaterials = infoMaterialService.selectByExample(example);

            AdvMaterialExample example1 = new AdvMaterialExample();
            List<InfoMaterialVO> infoMaterialVOList = new ArrayList<>();
            for (InfoMaterial infoMaterial:infoMaterials) {
                example1.createCriteria().andIdEqualTo(infoMaterial.getMaterialId());
                List<AdvMaterial> advMateriallist = advMaterialService.selectByExample(example1);
                InfoMaterialVO infoMaterialVO = new InfoMaterialVO();
                infoMaterialVO.setFileName(advMateriallist.get(0).getFileName());
                infoMaterialVO.setAdvInfoId(infoMaterial.getAdvInfoId());
                infoMaterialVO.setDuration(infoMaterial.getDuration());
                infoMaterialVO.setMaterialId(infoMaterial.getMaterialId());
                infoMaterialVO.setSequence(infoMaterial.getSequence());
                infoMaterialVOList.add(infoMaterialVO);
            }
            result.setResultData(infoMaterialVOList);
            result.setResultDesc("查询成功");
            result.setResultCode(0);
        } catch (Exception e) {
            e.printStackTrace();
            Result.error("查询失败");
        }
        return result;
    }

    /**
     * 删除广告信息对应资源
     * @param infoMaterial
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Result deleteInfoMaterial(@RequestBody InfoMaterial infoMaterial) {
        Result result = new Result();
        try {
            InfoMaterialExample example = new InfoMaterialExample();
            if (infoMaterial != null) {
                if (infoMaterial.getAdvInfoId() == null || infoMaterial.getMaterialId() == null) {
                    result.setResultCode(1);
                    result.setResultDesc("缺少参数");
                    result.setResultData(null);
                    return result;
                }
                example.createCriteria().andAdvInfoIdEqualTo(infoMaterial.getAdvInfoId())
                        .andMaterialIdEqualTo(infoMaterial.getMaterialId());
            }
            infoMaterialService.deleteByExample(example);
            result.setResultData(null);
            result.setResultDesc("删除广告信息对应资源成功");
            result.setResultCode(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 编辑广告信息对应资源
     * @param infoMaterial
     * @return
     */
    @RequestMapping(value = "update")
    public Result updateInfoMaterial(@RequestBody InfoMaterial infoMaterial) {
        Result result = new Result();
        try {
            InfoMaterialExample example = new InfoMaterialExample();
            if (infoMaterial != null) {
                example.createCriteria().andAdvInfoIdEqualTo(infoMaterial.getAdvInfoId())
                        .andMaterialIdEqualTo(infoMaterial.getMaterialId());
            }
            infoMaterialService.updateByExample(infoMaterial, example);
            result.setResultCode(0);
            result.setResultDesc("广告信息对应资源编辑成功");
            result.setResultData(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}














