package com.ssafy.team8alette.feed.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ssafy.team8alette.feed.model.dto.Feed.Entity.Feed;

@Repository
public interface FeedRepository extends JpaRepository<Feed, Long> {

	Feed findFeedByFeedNumber(Long feedNumber);

	List<Feed> findByFeedActiveOrderByFeedNumberDesc(boolean feedActive);

	@Query("SELECT f FROM Feed f WHERE f.feedActive = true ORDER BY f.feedLikeCnt DESC, f.feedNumber DESC")
	List<Feed> findByFeedActiveOrderByFeedLikeCntDescAndFeedNumberDesc(boolean feedActive);

	boolean existsByFeedNumberAndFeedActive(Long feedNumber, boolean feedActive);

}
