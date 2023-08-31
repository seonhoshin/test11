package com.project.bbibbi.domain.feed.controller;

import com.project.bbibbi.feed.dto.FeedPostDto;
import com.project.bbibbi.feed.entity.Feed;
import com.project.bbibbi.feed.entity.FeedImage;
import com.project.bbibbi.feed.mapper.FeedMapper;
import com.project.bbibbi.feed.service.FeedService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/feed")
@Validated
public class FeedController {

    private final static String FEED_DEFAULT_URL = "/feed";

    private final FeedService feedService;

    private final FeedMapper mapper;

    public FeedController(FeedService feedService, FeedMapper mapper) {
        this.feedService = feedService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postFeed(@Valid @RequestBody FeedPostDto requestBody) {

        Feed feed = mapper.feedPostDtoToFeed(requestBody);

        Feed createdFeed = feedService.createFeed(feed);

        URI location = UriComponentsBuilder.newInstance().path(FEED_DEFAULT_URL + "/{feed-id}").buildAndExpand(createdFeed.getFeedId()).toUri();

        return ResponseEntity.created(location).build();

    }

    @PatchMapping("/{feed-id}")
    public ResponseEntity patchFeed(@PathVariable("feed-id") @Positive long feedId,
                                    @Valid @RequestBody FeedPatchDto requestBody){
        requestBody.setFeedId(feedId);

        Feed feed = mapper.feedPatchDtoToFeed(requestBody);

        Feed updatedFeed = feedService.updateFeed(feed);

        FeedResponseDto feedResponseDto = mapper.feedToFeedResponseDto(updatedFeed);

        return new ResponseEntity<>(new SingleResponseDto<>(feedResponseDto), HttpStatus.OK);
    }

    @GetMapping("/{feed-id}")
    public ResponseEntity getFeed(@PathVariable("feed-id") @Positive long feedId){
        Feed feed = feedService.findFeed(feedId);

        FeedResponseDto feedResponseDto = mapper.feedToFeedResponseDto(feed);

        return new ResponseEntity<>(new SingleResponseDto<>(feedResponseDto), HttpStatus.OK);
    }

//    @GetMapping
//    public ResponseEntity getFeeds() {
//        List<Feed> feeds = feedService.findFeeds();
//
//        List<FeedResponseDto> feedResponseDtos = mapper.feedsToFeedResponseDtos(feeds);
//
//        return new ResponseEntity<>(new MultiResponseDto<>(feedResponseDtos), HttpStatus.OK);
//
//    }

    @GetMapping("/filter/{search-code}")
    public ResponseEntity getFeeds(@PathVariable("search-code") String searchCode) {
        List<Feed> feeds = feedService.findFeeds(searchCode);

        List<FeedResponseDto> feedResponseDtos = mapper.feedsToFeedResponseDtos(feeds);

        return new ResponseEntity<>(new MultiResponseDto<>(feedResponseDtos), HttpStatus.OK);

    }

    @DeleteMapping("/{feed-id}")
    public ResponseEntity deleteFeed(@PathVariable("feed-id") @Positive long feedId){
        feedService.deleteFeed(feedId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
