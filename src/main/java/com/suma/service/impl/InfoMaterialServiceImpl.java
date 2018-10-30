package com.suma.service.impl;

import com.suma.dao.AdvInfoMapper;
import com.suma.dao.InfoMaterialMapper;
import com.suma.pojo.AdvMaterial;
import com.suma.pojo.InfoMaterial;
import com.suma.pojo.InfoMaterialExample;
import com.suma.service.AdvMaterialService;
import com.suma.service.InfoMaterialService;
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
        infoMaterialExample.createCriteria().andAdvInfoIdEqualTo(id);
        List<InfoMaterial> infoMaterials = selectByExample(infoMaterialExample);
        return infoMaterials;
    }


}
