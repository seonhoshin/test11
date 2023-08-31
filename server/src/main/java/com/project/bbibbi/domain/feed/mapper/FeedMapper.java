package com.project.bbibbi.domain.feed.mapper;

import com.project.bbibbi.domain.feed.dto.FeedImageDto;
import com.project.bbibbi.domain.feed.dto.FeedPatchDto;
import com.project.bbibbi.domain.feed.dto.FeedPostDto;
import com.project.bbibbi.domain.feed.dto.FeedResponseDto;
import com.project.bbibbi.domain.feed.entity.Feed;
import com.project.bbibbi.domain.feed.entity.FeedImage;
import com.project.bbibbi.domain.member.entity.Member;
import com.project.bbibbi.global.entity.*;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface FeedMapper {
}
