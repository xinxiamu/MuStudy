package com.ymu.dao.base;

import java.io.Serializable;
import java.util.List;

/**
 * 公共接口
 * @param <M>   实体类型
 * @param <ID>  id类型
 */
public interface ICommonDao<M extends Serializable,ID extends Serializable> {

    /**
     * 根据id查询记录所有字段。
     * @param id
     * @return M
     */
    M getById(ID id) throws Exception;

    List<M> getAll() throws Exception;

}
