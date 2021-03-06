package com.suma.controller;
import com.suma.constants.ExceptionConstants;
import com.suma.exception.InfoMaterialException;
import com.suma.pojo.AdvMaterial;
import com.suma.pojo.AdvMaterialExample;
import com.suma.pojo.InfoMaterial;
import com.suma.pojo.InfoMaterialExample;
import com.suma.service.AdvMaterialService;
import com.suma.service.InfoMaterialService;
import com.suma.utils.Result;
import com.suma.vo.InfoMaterialVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/** 广告信息与广告资源对应信息增删改查
 * @auther: luxinzong
 * @date: 2018/10/16
 */

@RestController
@RequestMapping(value = "infoMaterial", produces = "application/json;charset=utf-8")
public class InfoMaterialController extends BaseController{

    @Autowired
    InfoMaterialService infoMaterialService;

    @Autowired
    AdvMaterialService advMaterialService;

    /**
     * 批量添加广告信息对应资源
     * @param infoMaterialList
     * @return
     */
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public Result addInfoMaterials(List<InfoMaterial> infoMaterialList) {
        //判断是否缺少对应资源信息
        if (infoMaterialList.size() == 0) {
            throw new InfoMaterialException(ExceptionConstants.INFO_MATERIAL_INFO_MATERIAL_IS_NULL);
        }
        //已有对应资源，判断是否具有必须参数
        for (InfoMaterial infoMaterial : infoMaterialList) {
            if (infoMaterial.getAdvInfoId() == null || infoMaterial.getMaterialId() == null
                    || infoMaterial.getDuration() == null || infoMaterial.getSequence() == null) {
                throw new InfoMaterialException(ExceptionConstants.INFO_MATERIAL_REQUESTPARAMS_IS_NULL);
            }
            infoMaterialService.save(infoMaterial);
        }
        return Result.success();
    }


    /**
     * 单个添加广告信息与资源对应关系
     * @param advInfoId
     * @param materialId
     * @param duration
     * @param sequence
     * @return
     */
    @RequestMapping(value = "insertOne", method = RequestMethod.POST)
    public Result addInfoMaterial(Long advInfoId, Long materialId,Integer duration,Integer sequence) {
        if (advInfoId == null || materialId == null || duration == null ||sequence == null) {
            throw new InfoMaterialException(ExceptionConstants.INFO_MATERIAL_REQUESTPARAMS_IS_NULL);
        }
        AdvMaterialExample example = new AdvMaterialExample();
        example.createCriteria().andIdEqualTo(materialId);
        if (advMaterialService.selectByExample(example).size() == 0) {
            throw new InfoMaterialException(ExceptionConstants.INFO_MATERIAL_INFO_MATERIAL_IS_NULL);
        }
        InfoMaterial infoMaterial = new InfoMaterial();
        infoMaterial.setMaterialId(materialId);
        infoMaterial.setSequence(sequence);
        infoMaterial.setDuration(duration);
        infoMaterial.setAdvInfoId(advInfoId);
       return toResult(infoMaterialService.save(infoMaterial));
    }


    /**
     * 查询广告信息对应所有资源
     * @param advInfoId
     * @return
     */
    @RequestMapping(value = "query", method = RequestMethod.GET)
    public Result queryInfoMaterial(Long advInfoId) {
        InfoMaterialExample example = new InfoMaterialExample();
        if (advInfoId != null) {
            //查询广告信息与资源关系
            example.createCriteria().andAdvInfoIdEqualTo(advInfoId);
            List<InfoMaterial> infoMaterials = infoMaterialService.selectByExample(example);
            AdvMaterialExample example1 = new AdvMaterialExample();
            //判断广告资源是否存在
            if (infoMaterials.size() == 0) {
                throw new InfoMaterialException(ExceptionConstants.INFO_MATERIAL_INFO_MATERIAL_IS_NULL);
            }
            //存储返回页面数据
            List<InfoMaterialVO> infoMaterialVOList = new ArrayList<>();
            for (InfoMaterial infoMaterial:infoMaterials) {
                InfoMaterialVO infoMaterialVO = new InfoMaterialVO();
                infoMaterialVO.setFileName(advMaterialService.findByPK(infoMaterial.getMaterialId()).getFileName());
                BeanUtils.copyProperties(infoMaterial, infoMaterialVO);
                infoMaterialVOList.add(infoMaterialVO);
            }
            return Result.success(infoMaterialVOList);
        } else {
            throw new InfoMaterialException(ExceptionConstants.INFO_MATERIAL_REQUESTPARAMS_IS_NULL);
        }

    }

    /**
     * 根据素材Id查询素材相关信息
     * @param materialIds
     * @return
     */
    @RequestMapping(value = "queryByIds", method = RequestMethod.GET)
    public Result queryMaterialName(Long[] materialIds) {
        if (materialIds == null) {
            throw new InfoMaterialException(ExceptionConstants.INFO_MATERIAL_REQUESTPARAMS_IS_NULL);
        }
        List<AdvMaterial> advMaterials = new ArrayList<>();
        for (Long materialId : materialIds) {
            advMaterials.add(advMaterialService.findByPK(materialId));
        }
        return Result.success(advMaterials);
    }

    /**
     * 根据json格式删除广告信息对应资源
     * @param
     * @return
     */
  /*  @RequestMapping(value = "delete", method = RequestMethod.POST)
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
    }*/

    /**
     * 根据广告ID和资源ID删除对应信息
     * @param advInfoId
     * @param materialId
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Result deleteInfoMaterial(Long advInfoId,Long materialId) {
        int count = 0;
        try {
            //获取请求参数
            //将查询出来的数据删除
            InfoMaterialExample example = new InfoMaterialExample();
               example.createCriteria().andAdvInfoIdEqualTo(advInfoId).
                       andMaterialIdEqualTo(materialId);
               infoMaterialService.deleteByExample(example);
               count++;
        } catch (NullPointerException E) {
            throw new InfoMaterialException(ExceptionConstants.INFO_MATERIAL_REQUESTPARAMS_IS_NULL);
        }
        return toResult(count);
    }



    /**
     * 批量更新广告信息对应资源关系
     * @param
     * @return
     */
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public Result getUpdateInfoMaterials(List<InfoMaterial> infoMaterialList) {
        InfoMaterialExample example = new InfoMaterialExample();
        int count = 0;
        for (InfoMaterial infoMaterial : infoMaterialList) {
            example.createCriteria().andAdvInfoIdEqualTo(infoMaterial.getAdvInfoId())
                    .andMaterialIdEqualTo(infoMaterial.getMaterialId());
            //判断对应资源是否存在
            List<InfoMaterial> infoMaterials = infoMaterialService.selectByExample(example);
            if (infoMaterials.size() == 0) {
                System.out.println("大家好");
                continue;
            }
            infoMaterialService.updateByExample(infoMaterial, example);
            count++;
        }
        if (count == infoMaterialList.size()){
            return Result.success();
        } else {
            throw new InfoMaterialException("有"+(infoMaterialList.size()-count)+"更新失败");
        }
    }


    /**
     * 单个更新广告信息与资源对应关系
     * @param infoMaterial
     * @return
     */
    @RequestMapping(value = "updateOne", method = RequestMethod.POST)
    public Result updateInfoMaterial(InfoMaterial infoMaterial) {
        InfoMaterialExample example = new InfoMaterialExample();
        example.createCriteria().andAdvInfoIdEqualTo(infoMaterial.getAdvInfoId())
                .andMaterialIdEqualTo(infoMaterial.getMaterialId());
        //判断对应资源是否存在
        List<InfoMaterial> infoMaterials = infoMaterialService.selectByExample(example);
        if (infoMaterials.size() == 0) {
            System.out.println("大家好");
        }
        return toResult(infoMaterialService.updateByExample(infoMaterial, example));
    }

}














