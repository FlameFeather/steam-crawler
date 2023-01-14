package com.zny.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zny.springboot.entity.App_comment;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Collection;

public interface AppCommentMapper extends BaseMapper<App_comment> {
	@Select("select `ac-c`.id,a.steam_appid,a.name,app_comment.is_exist, app_comment.success,app_comment.`Recent evaluation` Recent_evaluation, app_comment.`Overall evaluation` Overall_evaluation,`ac-c`.`author.steamid` author_steamid, `ac-c`.`author.playtime_forever` author_playtime_forever,`ac-c`.timestamp_created, `ac-c`.timestamp_updated,`ac-c`.language, `ac-c`.review from app_comment inner join `app_comment-content` `ac-c` on app_comment.steam_appid = `ac-c`.steam_appid inner join app a on `ac-c`.steam_appid = a.steam_appid where a.name like concat('%', #{search}, '%')")
	Page<App_comment> innerSelect(Page<App_comment> page, @Param(value = "search")String search);
}
