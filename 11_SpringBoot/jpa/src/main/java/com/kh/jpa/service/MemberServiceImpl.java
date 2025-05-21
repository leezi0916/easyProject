package com.kh.jpa.service;

import com.kh.jpa.dto.MemberDto;
import com.kh.jpa.entity.Member;
import com.kh.jpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional //여러개의 Repository에 보낼 값을 가지고 있다가 한번에 트랜잭션 처리할려고 중간 위치에 있는 서비스에 @Transactional작성
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override//createDto : 사용자가 입력한 회원정보
    public String createMember(MemberDto.Create createDto) {
        Member member = createDto.toEntity(); //메모리에 올라온 member
        memberRepository.save(member);
        return member.getUserId(); // 여기서 member는 영속 상태 member
    }

    //select만 해오는 것들을 @Transactional(readOnly = true)사용해서 읽기 전용으로만 쓰겠다고 명시
    @Transactional(readOnly = true)
    @Override
    public MemberDto.Response findMember(String userId) {
        return memberRepository.findOne(userId)
                .map(MemberDto.Response::toDto) //있으면 변환해줘.
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
    }

    @Override
    public MemberDto.Response updateMember(String userId, MemberDto.Update updateDto) {
        Member member = memberRepository.findOne(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        member.updateMemberInfo(
                updateDto.getUser_name(),
                updateDto.getEmail(),
                updateDto.getGender(),
                updateDto.getPhone(),
                updateDto.getAddress(),
                updateDto.getAge()
        );
        return MemberDto.Response.toDto(member);
    }

    @Override
    public void deleteMember(String userId) {
        Member member = memberRepository.findOne(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        memberRepository.delete(member);
    }

    @Override
    public List<MemberDto.Response> findAllMembers() { //받아온게 리스트이므로 stream 형태로 변경해야 함
        return memberRepository.findAll().stream()
                .map(MemberDto.Response::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MemberDto.Response> findByName(String name) {
        return memberRepository.findByName(name).stream()
                .map(MemberDto.Response::toDto)
                .collect(Collectors.toList());
    }

}
