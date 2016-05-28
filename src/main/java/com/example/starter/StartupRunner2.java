package com.example.starter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.example.config.ConnectionSettings;

public class StartupRunner2 implements CommandLineRunner {
	protected final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private ConnectionSettings settings;
	@Override
	public void run(String... arg0) throws Exception {
		logger.info("加载StartupRunner2");
		logger.info(settings.getUsername());
		logger.info(settings.getRemoteAddress().getHostAddress());

	}

}
