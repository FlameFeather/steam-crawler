package com.zny.springboot.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zny.springboot.common.Result;
import com.zny.springboot.entity.App;
import com.zny.springboot.mapper.AppMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/app")
public class AppController {
	@Resource
	AppMapper appMapper;

	@PostMapping
	public Result<?> save(@RequestBody App app) {
		appMapper.insert(app);
		return Result.success();
	}

	@PutMapping
	public Result<?> update(@RequestBody App app) {
		appMapper.updateById(app);
		return Result.success();
	}

	@DeleteMapping("/{id}")
	public Result<?> update(@PathVariable Integer id) {
		appMapper.deleteById(id);
		return Result.success();
	}

	@GetMapping
	public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
							  @RequestParam(defaultValue = "10") Integer pageSize,
							  @RequestParam(defaultValue = "") String search) {
		LambdaQueryWrapper<App> wrapper = Wrappers.<App>lambdaQuery();
		if (StrUtil.isNotBlank(search)) {
			wrapper.like(App::getName, search);
		}
		Page<App> userPage = appMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
		return Result.success(userPage);
	}
}
