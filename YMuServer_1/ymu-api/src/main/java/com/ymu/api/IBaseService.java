package com.ymu.api;

import java.io.Serializable;

/**
 * Created by mutou on 16-12-14.
 */
public interface IBaseService<M,ID extends Serializable> {

    M save(M entity) throws Exception;

}
