package com.ssafy.team8alette.feed.model.dto.Like.Key;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class LikeID implements Serializable {

	@Column(name = "like_feed_number")
	private Long likeFeedNumber;

	@Column(name = "like_member_number")
	private Long likeMemberNumber;

	public LikeID(Long likeFeedNumber, Long likeMemberNumber) {
		this.likeFeedNumber = likeFeedNumber;
		this.likeMemberNumber = likeMemberNumber;
	}

}