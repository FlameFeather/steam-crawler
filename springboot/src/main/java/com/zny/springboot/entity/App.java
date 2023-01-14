package com.zny.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("app")
@Data
public class App {
	@TableId(type = IdType.AUTO)
	private Integer id;
	private Integer steamAppid;
	private String type;
	private String name;
	private String detailedDescription;
	private String aboutTheGame;
	private String shortDescription;
	private String supportedLanguages;
	private String headerImage;
	private String website;
	private String developers;
	private String publishers;
	private String releaseDate;
	private Integer isFree;
	private Double price;
	@TableField(value = "`pc_requirements.minimum`")
	private String pcRequirementsMinimum;
	@TableField(value = "`pc_requirements.recommended`")
	private String pcRequirementsRecommended;
}
