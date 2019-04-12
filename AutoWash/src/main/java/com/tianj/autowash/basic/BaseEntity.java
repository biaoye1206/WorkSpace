package com.tianj.autowash.basic;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * @author zhangxq
 * @version v1.0
 * @update 2018-12-08 12:00
 */

public class BaseEntity implements SuppertEntity, Serializable {
    private static final long serialVersionUID = -3042170897260897993L;
    /**
     * ID
     */
    @ApiModelProperty(hidden = true)
    private String id;
    /**
     * 是否删除
     */
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private Character delFlag;
    /**
     * 创建时间
     */
    @ApiModelProperty(hidden = true)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Timestamp createDate;
    /**
     * 更新时间
     */
    @ApiModelProperty(hidden = true)
    private Timestamp updateDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Character getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Character delFlag) {
        this.delFlag = delFlag;
    }


    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }
}
