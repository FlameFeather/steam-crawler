package com.zny.springboot.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zny.springboot.common.Result;
import com.zny.springboot.entity.App;
import com.zny.springboot.entity.App_img;
import com.zny.springboot.mapper.AppImgMapper;
import com.zny.springboot.mapper.AppMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/app_img")
public class AppImgController {
	@Resource
	AppImgMapper appImgMapper;

	@GetMapping
	public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
							  @RequestParam(defaultValue = "10") Integer pageSize,
							  @RequestParam(defaultValue = "") String search) {
		LambdaQueryWrapper<App_img> wrapper = Wrappers.<App_img>lambdaQuery();
		if (StrUtil.isNotBlank(search)) {
			wrapper.like(App_img::getSteamAppid, search);
		}
		Page<App_img> userPage = appImgMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);

		return Result.success(userPage);
	}
}
