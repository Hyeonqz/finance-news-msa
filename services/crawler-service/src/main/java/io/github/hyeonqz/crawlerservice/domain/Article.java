package io.github.hyeonqz.crawlerservice.domain;

import io.github.hyeonqz.crawlerservice.domain.enums.ArticleStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "articles",
        indexes = {
                @Index(name = "idx_source_url", columnList = "sourceUrl", unique = true),
                @Index(name = "idx_published_at", columnList = "publishedAt"),
                @Index(name = "idx_source_created", columnList = "source,createdAt")
        }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String title;

    @Column(columnDefinition = "mediumtext")
    private String content;

    @Column(nullable = false, length = 1000)
    private String sourceUrl;

    @Comment("출처")
    @Column(nullable = false, length = 100)
    private String source;

    @Column(length = 100)
    private String author;

    private LocalDateTime publishedAt;

    @Column(length = 500)
    private String thumbnailUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ArticleStatus status;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (status == null) {
            status = ArticleStatus.PENDING;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public void markAsProcessed() {
        this.status = ArticleStatus.PROCESSED;
    }

    public void markAsFailed() {
        this.status = ArticleStatus.FAILED;
    }
}
