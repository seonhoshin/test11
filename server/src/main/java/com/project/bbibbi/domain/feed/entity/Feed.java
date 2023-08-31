package com.project.bbibbi.domain.feed.entity;

//import com.project.bbibbi.domain.member.entity.Member;
import com.project.bbibbi.domain.member.entity.Member;
import com.project.bbibbi.global.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Feed extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedId;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 3000, nullable = false)
    private String content;

    @Column
    private Integer views = 0;

    @Column(length = 3000)
    private String coverPhoto;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private RoomType roomType;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private RoomSize roomSize;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private RoomCount roomCount;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private RoomInfo roomInfo;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private Location location;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

//    @Column
//    private Long memberId;

    @OneToMany(mappedBy = "feed", cascade = CascadeType.REMOVE)
    private List<FeedImage> images = new ArrayList<>();

    // Feed 좋아요 조인
    @OneToMany(mappedBy = "feed", cascade = CascadeType.REMOVE)
    private List<FeedLike> feedLikes = new ArrayList<>();

    // 이 게시물의 좋아요 개수
    @Transient
    private Integer likeCount;

}
