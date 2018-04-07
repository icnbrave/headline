package com.headline.demo.mapper.entity;

import java.util.Date;

public class Headline {
    private Integer headlinePk;

    private String name;

    private String description;

    private Short deleteFlag;

    private Short selectFlag;

    private Date createTime;

    private Date updateTime;

    private Short flag;

    private String author;

    private String title;

    public Integer getHeadlinePk() {
        return headlinePk;
    }

    public void setHeadlinePk(Integer headlinePk) {
        this.headlinePk = headlinePk;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Short getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Short deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Short getSelectFlag() {
        return selectFlag;
    }

    public void setSelectFlag(Short selectFlag) {
        this.selectFlag = selectFlag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Short getFlag() {
        return flag;
    }

    public void setFlag(Short flag) {
        this.flag = flag;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Headline other = (Headline) that;
        return (this.getHeadlinePk() == null ? other.getHeadlinePk() == null : this.getHeadlinePk().equals(other.getHeadlinePk()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getDeleteFlag() == null ? other.getDeleteFlag() == null : this.getDeleteFlag().equals(other.getDeleteFlag()))
            && (this.getSelectFlag() == null ? other.getSelectFlag() == null : this.getSelectFlag().equals(other.getSelectFlag()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getFlag() == null ? other.getFlag() == null : this.getFlag().equals(other.getFlag()))
            && (this.getAuthor() == null ? other.getAuthor() == null : this.getAuthor().equals(other.getAuthor()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getHeadlinePk() == null) ? 0 : getHeadlinePk().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        result = prime * result + ((getSelectFlag() == null) ? 0 : getSelectFlag().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getFlag() == null) ? 0 : getFlag().hashCode());
        result = prime * result + ((getAuthor() == null) ? 0 : getAuthor().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", headlinePk=").append(headlinePk);
        sb.append(", name=").append(name);
        sb.append(", description=").append(description);
        sb.append(", deleteFlag=").append(deleteFlag);
        sb.append(", selectFlag=").append(selectFlag);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", flag=").append(flag);
        sb.append(", author=").append(author);
        sb.append(", title=").append(title);
        sb.append("]");
        return sb.toString();
    }
}