package com.zny.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("app_comment")
@Data
public class App_comment {
	@TableId(type = IdType.AUTO)
	private Integer id;
	private Integer steamAppid;
	private String name;
	private Integer isExist;
	private Integer success;
	@TableField(value = "RE")
	private String RecentEvaluation;
	@TableField(value = "OE")
	private String OverallEvaluation;

	@TableField(value = "US")
	private String authorSteamid;
	@TableField(value = "PF")
	private Integer authorPlaytimeForever;
	private String timestampCreated;
	private String timestampUpdated;
	private String language;
	private String review;

}
