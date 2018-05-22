package com.gpm.pay.utils;


import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigUtil {

	private static Logger logger = LoggerFactory.getLogger(ConfigUtil.class);
	private static ResourceLoader resourceLoader = new DefaultResourceLoader();
	public static Properties loader =  loadProperties("application.properties");


	/**
	 * @Author: 张立华
	 * @Description: 获取配置文件参数值
	 * @params: *
	 * @return: *
	 * @Date: 10:54 2018/5/17
	 */
	public static String getConfig(String param){
		Object valObj = ConfigUtil.loader.get(param) ;
		return valObj == null ? "" : String.valueOf(valObj);
	}
	/**
	 * 载入多个文件, 文件路径使用Spring Resource格式.
	 */
	private static Properties loadProperties(String... resourcesPaths) {
		Properties props = new Properties();

		for (String location : resourcesPaths) {
			InputStream is = null;
			try {
				Resource resource = resourceLoader.getResource(location);
				is = resource.getInputStream();
				props.load(is);
			} catch (IOException ex) {
				logger.info("Could not load properties from path:" + location + ", " + ex.getMessage());
			} finally {
				IOUtils.closeQuietly(is);
			}
		}
		return props;
	}

}
