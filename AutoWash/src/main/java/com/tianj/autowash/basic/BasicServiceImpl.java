package com.tianj.autowash.basic;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * @author Administrator
 * @version v1.0
 * @update 2018-12-20 10:37
 */
public class BasicServiceImpl<T extends BaseEntity, D extends BaseDao<T>> extends BaseSupport
        implements BasicService<T> {
        protected final static Logger log = LoggerFactory.getLogger(BasicServiceImpl.class);


    @Autowired
    protected D dao;
    /**
     * json解析对象
     */
    @Autowired
    protected ObjectMapper json;

    /**
     * 新增或更新一条数据
     *
     * @param t 数据实体
     * @return 执行结果数
     */
    @Override
    public void insertOrUpdate(T t) {
        // 对新数据或更新数据分别设置数据并处理
        dataProcess(t, dao);
    }


    /**
     * 删除一条数据
     *
     * @param id 数据id
     * @return 执行结果数
     */
    @Override
    public void delete(String id) {
        dao.delete(id);
    }

    /**
     * 获取一条数据
     *
     * @param id 数据id
     * @return 数据实体
     */
    @Override
    public T findById(String id) {
        return dao.findById(id);
    }
}
