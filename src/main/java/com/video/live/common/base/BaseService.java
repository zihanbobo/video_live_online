package com.video.live.common.base;


import java.util.List;
import java.util.Optional;

/**
 * @Author: Deng Yunhu
 * @Date: 2019/12/13 16:04
 */
public interface BaseService<T, ID> {

    BaseDao<T, ID> getRepository();

    default Optional<T> save(T entity) {
        return Optional.of(getRepository().save(entity));
    }

    default Optional<List<T>> save(List<T> entities) {
        return Optional.of(getRepository().saveAll(entities));
    }

    default Optional<List<T>> findAll() {
        return Optional.of(getRepository().findAll());
    }

    default Optional<List<T>> findById(List<ID> ids) {
        return Optional.of(getRepository().findAllById(ids));
    }

    default Optional<T> findById(ID id) {
        return (Optional<T>) Optional.of(getRepository().findById(id));
    }
}
