
package com.suma.service;

import com.suma.pojo.TsInfo;
import com.suma.pojo.TsInfoExample;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/10/18
 * @description:
 */
public interface TsService extends BaseService<TsInfo, TsInfoExample, Long> {
    void checkExist(TsInfo tsInfo);

    void deleteTsInfos(Long[] ids);
}
