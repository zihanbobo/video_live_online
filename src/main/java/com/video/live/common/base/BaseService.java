package com.video.live.common.base;

import java.util.List;
import java.util.Optional;

/**
 * @Author: Deng Yunhu
 * @Date: 2019/12/13 16:04
 */
public interface BaseService<T, ID> {

    BaseDao<T> getRepository();

    default Optional<T> save(T entity) {
        return Optional.of(getRepository().save(entity));
    }

    default Optional<List<T>> findAll() {
        return Optional.of(getRepository().findAll());
    }
}
