package com.kh.jpa.service;

import com.kh.jpa.dto.NoticeDto;
import com.kh.jpa.entity.Member;
import com.kh.jpa.entity.Notice;
import com.kh.jpa.repository.MemberRepository;

import com.kh.jpa.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional//여러개의 Repository에 보낼 값을 가지고 있다가 한번에 트랜잭션 처리할려고 중간 위치에 있는 서비스에 @Transactional작성
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final MemberRepository memberRepository;

    // 공지 생성
    public Long createNotice(NoticeDto.Create dto) {
        // 작성자 조회
        Member member = memberRepository.findOne(dto.getNotice_writer())
                .orElseThrow(() -> new IllegalArgumentException("해당 작성자가 없습니다."));

        // DTO -> Entity 변환
        Notice notice = dto.toEntity(member);

        // 저장
        noticeRepository.save(notice);

        return notice.getNoticeNo();
    }

    @Transactional(readOnly = true)
    @Override
    public NoticeDto.Response findNotice(Long noticeNo) {
        return noticeRepository.findOne(noticeNo)
                .map(NoticeDto.Response::toDto)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 공지입니다."));
    }

    @Override
    public NoticeDto.Response updateNotice(Long noticeNo, NoticeDto.Update updateDto) {
        Notice notice = noticeRepository.findOne(noticeNo)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 공지입니다."));
        notice.updateNoticeInfo(
                updateDto.getNotice_title(),
                updateDto.getNotice_content()
        );

        return NoticeDto.Response.toDto(notice);
    }

    @Override
    public void deleteNotice(Long noticeNo) {
        Notice notice = noticeRepository.findOne(noticeNo)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 공지입니다."));
        noticeRepository.delete(notice);
    }

    @Override
    public List<NoticeDto.Response> findByTitle(String title) {
        return noticeRepository.findByTitle(title).stream()
                .map(NoticeDto.Response::toDto)
                .collect(Collectors.toList());
    }

}
