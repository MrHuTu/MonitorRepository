package com.zhongda.monitor.business.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhongda.monitor.business.service.ThresholdService;
import com.zhongda.monitor.core.model.Result;

/**
 * 
 * Title: 阈值接口
 *
 * Description:处理阈值业务
 *
 * @author 研发中心-LiIverson<1061734892@qq.com>
 * @Date 2018年4月20日 上午9:37:38
 */
@RestController
@RequestMapping(value = "/threshold")
@Api(tags = { "阈值接口" })
public class ThresholdController {

	@Resource
	private ThresholdService thresholdService;

	@GetMapping(value = "/queryThreByProId")
	@ApiOperation(value = "阈值数据--maoping.li", httpMethod = "GET", response = Result.class, notes = "通过项目ID查找阈值数据")
	@ApiImplicitParams({ @ApiImplicitParam(name = "projectId", value = "项目ID", required = true, dataType = "int", paramType = "query") })
	public Result<Map<String, Map<String, Object>>> queryThreByProId(
			Integer projectId) {
		return new Result<Map<String, Map<String, Object>>>()
				.setCode(Result.SUCCESS)
				.setMsg("操作成功")
				.setData(thresholdService.selectThresholdDataByProId(projectId));
	}
}
