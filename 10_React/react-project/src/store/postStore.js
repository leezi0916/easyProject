import {create} from "zustand";
import axios from "axios";

const usePostStore = create((set) =>({
    posts : [],
    loading : false,
    error : null,
    deleteLoading : false,

    getPosts: async () => {
        set({loading : true, error: null});
        try {
            const response = await axios.get("https://jsonplaceholder.typicode.com/posts");
            set({ posts: response.data, loading : false});
        } catch(error){
            set({loading : false, error: error.message});
        }
       
    },

    deletePost: async (id) => {
        set({deleteLoading : true, error: null});
        try {
            await axios.delete(`https://jsonplaceholder.typicode.com/posts/${id}`); //delete 메서드 방식 지울때 사용 
                                                                                    //데이터 다시 안받아오고 성공적으로 삭제된걸 아니까
                                                                                    // response로 굳이 안받아옴
            set((state) => ({
                posts: state.posts.filter(post => post.id !== id),
                deleteLoading: false,
            }));
        } catch(error){
            set({deleteLoading : false, error: error.message});
        }
    }
}))

export default usePostStore;
