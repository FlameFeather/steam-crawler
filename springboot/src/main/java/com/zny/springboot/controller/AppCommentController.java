package com.zny.springboot.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zny.springboot.common.Result;
import com.zny.springboot.entity.App;
import com.zny.springboot.entity.App_comment;
import com.zny.springboot.mapper.AppCommentMapper;
import com.zny.springboot.mapper.AppMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/app_comment")
public class AppCommentController {
	@Resource
	AppCommentMapper appCommentMapper;

	@PostMapping
	public Result<?> save(@RequestBody App_comment app) {
		appCommentMapper.insert(app);
		return Result.success();
	}

	@PutMapping
	public Result<?> update(@RequestBody App_comment app) {
		appCommentMapper.updateById(app);
		return Result.success();
	}

	@DeleteMapping("/{id}")
	public Result<?> update(@PathVariable Integer id) {
		appCommentMapper.deleteById(id);
		return Result.success();
	}

	@GetMapping
	public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
							  @RequestParam(defaultValue = "10") Integer pageSize,
							  @RequestParam(defaultValue = "") String search) {
//		search="'%"+search+"%'";
		Page<App_comment> userPage = appCommentMapper.innerSelect(new Page<>(pageNum, pageSize), search);

		return Result.success(userPage);
	}
}
