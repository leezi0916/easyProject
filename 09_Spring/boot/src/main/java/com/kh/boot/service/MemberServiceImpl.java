package com.kh.boot.service;

import com.kh.boot.domain.vo.Member;
import com.kh.boot.mappers.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service //@Component보다 더 구체화해서 service객체에 알맞게 bean에 등록
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;

    @Override
    public Member loginMember(Member m) {
        return memberMapper.loginMember(m);
    }

    @Override
    public int insertMember(Member m) {
        return memberMapper.insertMember(m);
    }
}
