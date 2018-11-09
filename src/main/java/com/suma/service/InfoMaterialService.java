package com.suma.service;

import com.suma.pojo.AdvMaterial;
import com.suma.pojo.InfoMaterial;
import com.suma.pojo.InfoMaterialExample;
import com.suma.vo.InfoMaterialVO;

import java.util.List;

/**
 * @auther: luxinzong
 * @date: 2018/10/15
 */
public interface InfoMaterialService extends BaseService<InfoMaterial,InfoMaterialExample,Long> {
    int updateByDoubleId(InfoMaterial infoMaterial);

    List<AdvMaterial> findMaterialByAdv(Long id);

    List<InfoMaterial> findByAdv(Long id);

    void deleteByAdvInfoId(Long advInfoId);

    public AdvMaterial getAdvMaterialByName(String fileName);

    void saveInfoMaterial(List<InfoMaterialVO> infoMaterialVOS, Long advInfoId);
    void deleteByAdvInfoIds(List<Long> list);

    List<InfoMaterialVO> getInfoMaterialVOS(List<InfoMaterial> infoMaterials);
}
