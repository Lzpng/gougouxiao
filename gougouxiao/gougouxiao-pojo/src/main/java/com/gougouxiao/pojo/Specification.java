package com.gougouxiao.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 规格实体类
 */
@Table(name="tb_specification")
public class Specification implements Serializable{
   
	private static final long serialVersionUID = -972374525762485421L;
	/** 主键  规格编号 */
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	/** 规格名称 */
	@Column(name="spec_name")
    private String specName;
    /** 规格属性集合 */
	@Transient // 告诉通用Mapper该属性不是表中的列
    private List<SpecificationOption> specificationOption;
    
    /** setter and getter method */
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSpecName() {
        return specName;
    }
    public void setSpecName(String specName) {
        this.specName = specName == null ? null : specName.trim();
    }
	public List<SpecificationOption> getSpecificationOption() {
		return specificationOption;
	}
	public void setSpecificationOption(List<SpecificationOption> specificationOption) {
		this.specificationOption = specificationOption;
	}
}