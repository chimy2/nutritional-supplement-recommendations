package com.test.nutri.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.test.nutri.entity.News;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NewsJDBCRepository {
	
	private final JdbcTemplate template;
	
	@Transactional
	public void saveAll(List<News> list) { 
		
		String sql = "insert into news (title, link, originalLink, description, regDate) values(?, ?, ?, ?, ?)";
		template.batchUpdate(sql,new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				News news = list.get(i);
				ps.setString(1, news.getTitle());
				ps.setString(2, news.getLink());
				ps.setString(3, news.getOriginalLink());
				ps.setString(4, news.getDescription());
				ps.setTimestamp(5, Timestamp.valueOf(news.getRegDate()));
			}
			
			@Override
			public int getBatchSize() {
				return list.size();
			}
		});
	}

}