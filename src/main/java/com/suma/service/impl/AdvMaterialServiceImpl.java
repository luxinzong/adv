package com.suma.service.impl;

import com.suma.constants.CommonConstants;
import com.suma.constants.ExceptionConstants;
import com.suma.dao.AdvMaterialMapper;
import com.suma.exception.AdvMaterialException;
import com.suma.exception.MaterialException;
import com.suma.pojo.AdvMaterial;
import com.suma.pojo.AdvMaterialExample;
import com.suma.pojo.MaterialType;
import com.suma.service.AdvMaterialService;
import com.suma.service.InfoMaterialService;
import com.suma.service.MaterialTypeService;
import com.suma.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/10/15
 * @description:
 */
@Service
public class AdvMaterialServiceImpl extends BaseServiceImpl<AdvMaterial, AdvMaterialExample, Long> implements AdvMaterialService {

    private Logger logger = LoggerFactory.getLogger(AdvMaterialServiceImpl.class);

    @Resource
    public void setBaseDao(AdvMaterialMapper baseDao) {
        // TODO Auto-generated method stub
        super.setBaseDao(baseDao);
    }

    @Autowired
    private MaterialTypeService materialTypeService;
    @Autowired
    private InfoMaterialService infoMaterialService;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(AdvMaterial material, String typeIds) {
        super.save(material);
        saveMaterialType(material, typeIds);
    }

    @Override
    public void saveMaterialType(AdvMaterial material, String typeIds) {
        String[] typeIdSplit = typeIds.split(",");
        for (String typeId : typeIdSplit) {
            MaterialType materialType = new MaterialType();
            materialType.setMaterialId(material.getId());
            materialType.setAdvTypeId(Long.valueOf(typeId));
            materialTypeService.save(materialType);
        }
    }

    @Override
    public void uploadMeterial(String typeIds, String fileName, MultipartFile file, String href, Integer materialType) {
        try {
            //获取文件后缀
            String originalFilename = file.getOriginalFilename();
            String[] originalFilenameSplit = originalFilename.split("\\.");
            String suffix = "";
            if (originalFilenameSplit.length > 0) {
                suffix = "." + originalFilenameSplit[originalFilenameSplit.length - 1];
            }
            if (fileName == null)
                fileName = file.getOriginalFilename();
            else
                fileName = fileName + suffix;

            AdvMaterial material = new AdvMaterial();
            String fileSaveName = CommonUtils.getFileSaveName() + suffix;
            material.setFileName(fileName);
            material.setFileType(file.getContentType());
            material.setFileSaveName(fileSaveName);
            material.setFileLength(file.getSize());
            material.setFilePath(CommonConstants.MATERIAL_FILE_PATH + fileSaveName);
            material.setFileUrl(CommonConstants.MATERIAL_FILE_URL + fileSaveName);
            material.setHref(href);
            material.setMaterialType(materialType);

            save(material, typeIds);

            byte[] bytes;
            bytes = file.getBytes();
            Path filePath = Paths.get(material.getFilePath());
            Path directoryPath = Paths.get(CommonConstants.MATERIAL_FILE_PATH);
            if (!Files.exists(directoryPath)) {
                Files.createDirectory(directoryPath);
            }
            Files.write(filePath, bytes);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new MaterialException(ExceptionConstants.MATERIAL_EXCEPTION_UPLOAD_FAIL);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(AdvMaterial materialPojo, String typeIds) {
        try {
            update(materialPojo);
            materialTypeService.deleteByMaterialId(materialPojo.getId());
            saveMaterialType(materialPojo, typeIds);
        } catch (Exception e) {
            throw new MaterialException(ExceptionConstants.MATERIAL_EXCEPTION_UPDATE_FAIL);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void cascadeDelete(Long id) {
        try {
            materialTypeService.deleteByMaterialId(id);
            super.deleteByPK(id);
        } catch (Exception e) {
            throw new MaterialException(ExceptionConstants.MATERIAL_EXCEPTION_DELETE_FAIL);
        }
    }

    @Override
    public List<AdvMaterial> findListByMaterialType(Long[] ids, Integer materialType) {
        List<AdvMaterial> advMaterials = new ArrayList<>();
        if (ids.length > 0) {
            AdvMaterialExample example = new AdvMaterialExample();
            example.createCriteria().andIdIn(Arrays.asList(ids)).andMaterialTypeEqualTo(materialType);
            advMaterials = selectByExample(example);
        }
        return advMaterials;
    }

}
