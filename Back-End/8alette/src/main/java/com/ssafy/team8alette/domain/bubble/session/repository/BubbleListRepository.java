package com.ssafy.team8alette.domain.bubble.session.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.team8alette.domain.bubble.session.model.dto.key.GroupID;
import com.ssafy.team8alette.domain.bubble.session.model.entity.BubbleParticipantEntity;

public interface BubbleListRepository extends JpaRepository<BubbleParticipantEntity, GroupID> {

	Optional<BubbleParticipantEntity> findBubbleListEntitiesByGroupID(GroupID groupID);
}
