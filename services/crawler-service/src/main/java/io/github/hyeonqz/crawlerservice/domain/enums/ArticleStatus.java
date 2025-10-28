package io.github.hyeonqz.crawlerservice.domain.enums;

public enum ArticleStatus {
    PENDING,     // 크롤링 완료, 분석 대기
    PROCESSING,  // 분석 중
    PROCESSED,   // 분석 완료
    FAILED       // 실패
}
