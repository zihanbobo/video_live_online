package com.video.live.common.base;

import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author: Deng Yunhu
 * @Date: 2019/12/13 15:59
 */
public interface BaseDao<T> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

}
