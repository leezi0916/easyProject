package com.kh.jpa.service;

import com.kh.jpa.dto.BoardDto;
import com.kh.jpa.entity.Board;
import com.kh.jpa.entity.BoardTag;
import com.kh.jpa.entity.Member;
import com.kh.jpa.entity.Tag;
import com.kh.jpa.enums.CommonEnums;
import com.kh.jpa.repository.BoardRepository;
import com.kh.jpa.repository.MemberRepository;
import com.kh.jpa.repository.TagRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final String UPLOAD_PATH = "/Users/easymook/dev_tool";
    private final TagRepository tagRepository;

    @Override
    public Page<BoardDto.Response> getBoardList(Pageable pageable) {
        /*
            getContent() 실제 데이터 리스트 반환
            getTotalPages() 전체 페이지 개수
            getTotalElements() 전체 데이터 수
            getSize() 페이지당 데이터 수
         */
        Page<Board> page = boardRepository.findByStatus(CommonEnums.Status.Y, pageable); //y상태인 애들만 페이징 처리로 갖고오겠다.
        return page.map(BoardDto.Response::toSimpleDto);
    }

    @Transactional
    @Override
    public BoardDto.Response getBoardDetail(Long boardNo) {
        Board board = boardRepository.findById(boardNo)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 게시글입니다."));
        return BoardDto.Response.toDto(board);
    }


    @Transactional
    @Override
    public Long createBoard(BoardDto.Create boardDto) throws IOException {//주 테이블에 필요한 정보들을 서비스 단에서 갖고와아한다 !!!
        // 게시글작성
        //작성자 찾기 -> 객체지향코드를 작성할 것이기 때문에 key를 직접 외래키로 insert하지않고
        //작성자를 찾아 참조해준다.

        Member member = memberRepository.findOne(boardDto.getBoard_writer())
                .orElseThrow(() -> new EntityNotFoundException("회원을 찾을 수 없습니다."));

        String changeName = null;
        String originName = null;

        if (boardDto.getFile() != null && !boardDto.getFile().isEmpty()) {
            originName = boardDto.getFile().getOriginalFilename();
            changeName = UUID.randomUUID().toString() + "_" + originName;

            File uploadDir = new File(UPLOAD_PATH);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            boardDto.getFile().transferTo(new File(UPLOAD_PATH + changeName));
        }

        Board board = boardDto.toEntity();
        board.changeMember(member); //데이터 일관성을 위해서 주인인 board쪽에서 memeber를 세팅해준다
        board.changeFile(originName, changeName);

        if (boardDto.getTags() != null && !boardDto.getTags().isEmpty()) {
            //tag가 왔다.
            for (String tagName : boardDto.getTags()) {
                //tag를 이름으로 조회해서 없으면 새로 만들어라.
                Tag tag = tagRepository.findByTagName(tagName)
                        .orElseGet(() -> tagRepository.save(Tag.builder()
                                .tagName(tagName)
                                .build()));

                BoardTag boardTag = BoardTag.builder() //중계테이블인 보드태그에 태그를 넣어주고
                        .tag(tag)
                        .build();

                boardTag.changeBoard(board); //보드태그에 보드를 넣어준다.
            }
        }

        boardRepository.save(board);

        return board.getBoardNo();
    }

    @Transactional
    @Override
    public Void deleteBoard(Long boardNo) {
        Board board = boardRepository.findById(boardNo)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));

        if (board.getChangeName() != null) {
            new File(UPLOAD_PATH + board.getChangeName()).delete(); //file 삭제 내장함수
        }
        boardRepository.delete(board);
        return null;
    }

    @Override
    public BoardDto.Response updateBoard(Long boardNO, BoardDto.Update boardUpdate) throws IOException {
        Board board = boardRepository.findById(boardNO)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));

        String changeName = board.getChangeName();
        String originName = board.getOriginName();

        if (boardUpdate.getFile() != null && !boardUpdate.getFile().isEmpty()) {
            originName = boardUpdate.getFile().getOriginalFilename();
            changeName = UUID.randomUUID().toString() + "_" + originName;

            File uploadDir = new File(UPLOAD_PATH);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            boardUpdate.getFile().transferTo(new File(UPLOAD_PATH + changeName));
        }
        board.changeContent(boardUpdate.getBoard_content());
        board.changeTitle(boardUpdate.getBoard_title());
        board.changeFile(originName, changeName);

        if (boardUpdate.getTags() != null && !boardUpdate.getTags().isEmpty()) { //사용자가 보낸 태그리스트가 있나 확인하고

            //기존BoardTag -> 연결이 끊기면 필요가 있을까? X
            //연결된 boardTag의 영속성을 제거한다. -> orphanRemoval = true 설정이 되어있다면 실제 db에서 제거
            board.getBoardTags().clear();//영속상태가 끊겼다는건 연관관계를 끊었다라는 뜻 (여기 타이밍에 db에서 삭제 될거임)

            //tag가 왔다.
            for (String tagName : boardUpdate.getTags()) {
                //tag를 이름으로 조회해서 없으면 새로 만들어라.
                Tag tag = tagRepository.findByTagName(tagName)
                                 .orElseGet(() -> tagRepository.save(Tag.builder()
                                    .tagName(tagName)
                                    .build()));

                BoardTag boardTag = BoardTag.builder() //중계테이블인 보드태그에 태그를 넣어주고
                        .tag(tag)
                        .build();

                boardTag.changeBoard(board); //보드태그에 보드를 넣어준다.
            }
        }

        return BoardDto.Response.toDto(board); //수정된 객체
    }


}
