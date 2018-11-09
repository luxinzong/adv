package com.suma.service.impl;

import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.suma.constants.ExceptionConstants;
import com.suma.dao.AdvMaterialMapper;
import com.suma.dao.InfoMaterialMapper;
import com.suma.exception.AdvMaterialException;
import com.suma.pojo.AdvMaterial;
import com.suma.pojo.AdvMaterialExample;
import com.suma.pojo.InfoMaterial;
import com.suma.pojo.InfoMaterialExample;
import com.suma.service.AdvMaterialService;
import com.suma.service.InfoMaterialService;
import com.suma.vo.InfoMaterialVO;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther: luxinzong
 * @date: 2018/10/15
 */
@Service
public class InfoMaterialServiceImpl extends BaseServiceImpl<InfoMaterial, InfoMaterialExample, Long> implements InfoMaterialService {
    @Resource
    public void setBaseDao(InfoMaterialMapper baseDao) {
        super.setBaseDao(baseDao);
    }

    @Autowired
    InfoMaterialMapper infoMaterialMapper;

    @Autowired
    AdvMaterialMapper advMaterialMapper;
    @Autowired
    private AdvMaterialService advMaterialService;

    @Override
    public int updateByDoubleId(InfoMaterial infoMaterial) {
        return infoMaterialMapper.updateByDoubleId(infoMaterial);
    }

    @Override
    public List<AdvMaterial> findMaterialByAdv(Long id) {
        InfoMaterialExample infoMaterialExample = new InfoMaterialExample();
        infoMaterialExample.createCriteria().andAdvInfoIdEqualTo(id);
        List<InfoMaterial> infoMaterials = selectByExample(infoMaterialExample);
        List<AdvMaterial> materials = new ArrayList<>();
        if (!CollectionUtils.isEmpty(infoMaterials))
            for (InfoMaterial infoMaterial : infoMaterials) {
                AdvMaterial material = advMaterialService.findByPK(infoMaterial.getMaterialId());
                materials.add(material);
            }
        return materials;
    }

    @Override
    public List<InfoMaterial> findByAdv(Long id) {
        InfoMaterialExample infoMaterialExample = new InfoMaterialExample();
        InfoMaterialExample.Criteria criteria = infoMaterialExample.createCriteria().andAdvInfoIdEqualTo(id);
        infoMaterialExample.setOrderByClause("sequence asc");
        List<InfoMaterial> infoMaterials = selectByExample(infoMaterialExample);
        return infoMaterials;
    }

    /**
     * 删除对应关系列表
     * @param advInfoId
     */
    @Override
    public void deleteByAdvInfoId(Long advInfoId){
        InfoMaterialExample example = new InfoMaterialExample();
        example.createCriteria().andAdvInfoIdEqualTo(advInfoId);
        infoMaterialMapper.deleteByExample(example);
    }

    @Override
    public void deleteByAdvInfoIds(List<Long> list) {
        if (!CollectionUtils.isEmpty(list)) {
            InfoMaterialExample example = new InfoMaterialExample();
            example.createCriteria().andAdvInfoIdIn(list);
            if (infoMaterialMapper.selectByExample(example).size() != 0) {
                infoMaterialMapper.deleteByExample(example);
            }
        }

    }

    @Override
    public AdvMaterial getAdvMaterialByName(String fileName) {
        AdvMaterialExample example = new AdvMaterialExample();
        example.createCriteria().andFileNameEqualTo(fileName);
        List<AdvMaterial> list = advMaterialMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public void saveInfoMaterial(List<InfoMaterialVO> infoMaterialVOS,Long advInfoId) {
        if (!CollectionUtils.isEmpty(infoMaterialVOS)) {
            infoMaterialVOS.forEach(infoMaterialVO -> {
                InfoMaterial infoMaterial = new InfoMaterial();
                BeanUtils.copyProperties(infoMaterialVO, infoMaterial);
                infoMaterial.setAdvInfoId(advInfoId);
                infoMaterial.setMaterialId(getAdvMaterialByName(infoMaterialVO.getFileName()).getId());
                infoMaterialMapper.insert(infoMaterial);
            });
        }
    }

    @Override
    public List<InfoMaterialVO> getInfoMaterialVOS(List<InfoMaterial> infoMaterials) {
        List<InfoMaterialVO> infoMaterialVOS = new ArrayList<>();
        //将查询到的资源信息存储到前端资源信息VO对象中
        if (!CollectionUtils.isEmpty(infoMaterials)) {
            infoMaterials.forEach(infoMaterial -> {
                //判断资源是否存在，如果不存在提示用户资源不存在
                if (advMaterialService.findByPK(infoMaterial.getMaterialId()) == null) {
                    throw new AdvMaterialException(ExceptionConstants.ADV_MATERIAL_IS_NULL);
                }
                //查找对应资源文件名称
                String fileName = advMaterialService.findByPK(infoMaterial.getMaterialId()).getFileName();
                //创建资源信息VO对象，将资源信息存储到VO对象中
                //将前端资源信息VO对象存储到信息列表中
                InfoMaterialVO infoMaterialVO = new InfoMaterialVO();
                BeanUtils.copyProperties(infoMaterial, infoMaterialVO);
                infoMaterialVO.setFileName(fileName);
                infoMaterialVOS.add(infoMaterialVO);
            });
        }
        return infoMaterialVOS;
    }
}
