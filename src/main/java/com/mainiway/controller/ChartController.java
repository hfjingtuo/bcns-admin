package com.mainiway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.mainiway.common.base.BaseController;
import com.mainiway.service.IChart;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/")
public class ChartController extends BaseController {

	@Autowired
	private IChart iChartService;

	@ApiOperation(value = "链的服务数")
	@RequestMapping(value = "chain_service_count", method = { RequestMethod.GET })
	public Object queryService1() {
		return JSONObject.parse(iChartService.selectById("1").getValue());
	}

	@ApiOperation(value = "workflow调用次数")
	@RequestMapping(value = "workflow_count", method = { RequestMethod.GET })
	public Object queryService2() {
		return JSONObject.parse(iChartService.selectById("2").getValue());
	}

	@ApiOperation(value = "workflow占比")
	@RequestMapping(value = "workflow_class_count", method = { RequestMethod.GET })
	public Object queryService3() {
		return JSONObject.parse(iChartService.selectById("3").getValue());
	}

	@ApiOperation(value = "跨链调用数量")
	@RequestMapping(value = "chain_net", method = { RequestMethod.GET })
	public Object queryService4() {
		return JSONObject.parse(iChartService.selectById("4").getValue());
	}
	
	@ApiOperation(value = "链网容量")
	@RequestMapping(value = "chain_capacity", method = { RequestMethod.GET })
	public Object queryService5() {
		return JSONObject.parse(iChartService.selectById("5").getValue());
	}
	
	@ApiOperation(value = "跨链调用次数")
	@RequestMapping(value = "chain_net_count", method = { RequestMethod.GET })
	public Object queryService6() {
		return JSONObject.parse(iChartService.selectById("6").getValue());
	}
	
	@ApiOperation(value = "服务总数")
	@RequestMapping(value = "service_total", method = { RequestMethod.GET })
	public Object queryService7() {
		return JSONObject.parse(iChartService.selectById("7").getValue());
	}
	
	@ApiOperation(value = "操作合规性")
	@RequestMapping(value = "operation_compliance", method = { RequestMethod.GET })
	public Object queryService8() {
		return JSONObject.parse(iChartService.selectById("8").getValue());
	}

	@ApiOperation(value = "调用次数Top3")
	@RequestMapping(value = "count_top3", method = { RequestMethod.GET })
	public Object queryService9() {
		return JSONObject.parse(iChartService.selectById("9").getValue());
	}
}
