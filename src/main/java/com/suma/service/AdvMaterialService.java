package com.suma.service;

import com.suma.pojo.AdvMaterial;
import com.suma.pojo.AdvMaterialExample;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/10/15
 * @description:
 */
public interface AdvMaterialService extends BaseService<AdvMaterial, AdvMaterialExample, Long> {
    void save(AdvMaterial material, String typeIds);

    void saveMaterialType(AdvMaterial material, String typeIds);

    void uploadMeterial(String typeIds, String fileName, MultipartFile file, String href, Integer materialType);

    void update(AdvMaterial materialPojo, String typeIds);

    void cascadeDelete(Long id);

    List<AdvMaterial> findListByMaterialType(Long[] ids, Integer materialType);
}