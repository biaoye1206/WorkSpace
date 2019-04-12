package com.tianj.autowash.basic;

import com.tianj.autowash.utils.DataTools;

/**
 * @author Zhangxq
 * @version v1.0
 * @CreateDate 2019-01-25 16:37
 */
public abstract class BaseSupport {
    /**
     * 独立出来方便在要注册其它Service时使用
     *
     * @param <F> 实体类对象
     * @param <A> Dao对象
     * @param t   数据实体类
     * @param d   数据实体对应Dao
     */
    protected <F extends BaseEntity, A extends BaseDao<F>> void dataProcess(F t, A d) {
        // 新数据或更新数据都需要设置更新时间
        t.setUpdateDate(DataTools.newDate());
        // 对新数据或更新数据分别设置数据并处理
        if (t.getId() == null || "".equals(t.getId())) {
            t.setId(DataTools.generateId());
            t.setCreateDate(DataTools.newDate());
            d.insert(t);
        } else {
            d.update(t);
        }
    }

    protected <E extends BaseEntity> void dataProcess(E e) {
        // 新数据或更新数据都需要设置更新时间
        e.setUpdateDate(DataTools.newDate());
        // 对新数据或更新数据分别设置数据并处理
        if (e.getId() == null || "".equals(e.getId())) {
            e.setId(DataTools.generateId());
            e.setCreateDate(DataTools.newDate());
        }
    }
}
