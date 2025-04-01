package com.test.nutri.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.test.nutri.service.NewsService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NewsScheduler {

	private final NewsService service;
	
	@Scheduled(cron = "0 0,30 * * * *")
	public void updateNews() {
		service.updateNews();
	}
}
