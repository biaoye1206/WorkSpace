package com.tianj.autowash.basic;

/**
 * @author Administrator
 * @version v1.0
 * @update 2018-12-20 10:50
 */

public interface BaseDao<T extends BaseEntity> {
    /**
     * 添加一条数据
     * @param t 数据实体
     * @return 执行结果数
     */
    void insert(T t);

    /**
     * 更新一条数据
     * @param t 数据实体
     * @return 执行结果数
     */
    void update(T t);

    /**
     * 删除一条数据
     * @param id 数据id
     * @return 执行结果数
     */
    void delete(String id);

    /**
     * 获取一条数据
     * @param id 数据id
     * @return 数据实体
     */
    T findById(String id);
}
