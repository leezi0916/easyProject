import React, { useEffect , useState} from 'react'
import usePostStore from '../store/postStore'
import {
    Container,
    Loading,
    Error,
    Title,
    PostCard,
    Content,
    ButtonContainer,
    LoadingOverlay
} from './styled/PostList.styled'
import { Button } from '../components/styled/common'

const PostList = () => {
    const { posts, error, loading, getPosts, deletePost } = usePostStore();
    const [deletePostId, setDeletePostId] = useState(null);

    useEffect(() => {
        getPosts();
    }, [getPosts]); // 내부적으로 메모이제이션을 해주기 위해 사용  
                    // 그렇지 않으면 매 랜더링마다 새로운 함수로 인식될 수도 있음 
                    // 	getPosts가 변경될 때만 다시 실행되도록 설정

    if (loading && posts.length === 0) return <Loading>로딩중 ...</Loading>
    if (error) return <Error>에러발생 : {error}</Error>

    const handleDelete = async (id) => {
        setDeletePostId(id) //버튼에 "삭제중" 표시
        await deletePost(id); //삭제 요청 수행 : 비동기 요청 -> 끝날 때 까지 기다림      
                                //호출하는 쪽에서 await을 붙여줘야 진짜 기다립니다.
        setDeletePostId(null); //버튼에 "삭제중" 표시제거 : 위에 await걸려 있는 deletePost가 완료되어야만 실행
    }

    return (
        <Container>
            {posts.map((post) => (
                <PostCard
                    key={post.id}
                >
                    <Title>{post.title}</Title>
                    <Content>{post.body}</Content>
                    <ButtonContainer>
                        <Button>수정</Button>
                        <Button
                            disabled={loading}
                            onClick={() => handleDelete(post.id)}
                        >
                            {deletePostId === post.id ? "삭제중..." : "삭제"}
                        </Button>
                    </ButtonContainer>
                    {deletePostId === post.id && (
                        <LoadingOverlay>
                            <div>삭제중...</div>
                        </LoadingOverlay>
                    )
                    }
                </PostCard>
            ))}
        </Container>
    )
}

export default PostList