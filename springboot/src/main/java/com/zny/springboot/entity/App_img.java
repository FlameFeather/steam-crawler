package com.zny.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@TableName("app_img")
@Data
public class App_img {
	@TableId(type = IdType.AUTO)
	private Integer id;
	private Integer steamAppid;
	private Integer success;
	private String picture;
//	@TableField(exist = false)
//	public List<String> pic_list;
}
