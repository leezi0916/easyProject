import React from 'react'

import { create } from 'zustand'
const useTodoStore = create((set, get) => ({
    todos: [{
        id: 1,
        text: "밥먹기",
        completed: false,
    },
    {
        id: 2,
        text: "달리기",
        completed: false,
    },
    {
        id: 3,
        text: "잠자기",
        completed: false,
    }],

    filter: 'all', //all, active, completed

    toggleTodo: (id) => set(state => ({
        todos: state.todos.map((todo) =>
            todo.id === id ? { ...todo, completed: !todo.completed } : todo
        ) // state는 여기 있는 값들 -> todos를 불러와서 맵함수로 돌리는데 todo.id 와 받아온 id가 같은 녀석을 상태값 변경
    })),

    deleteTodo: (id) => set(state => ({
        todos: state.todos.filter(todo => todo.id !== id)
    })),

    setFilter: (filter) => set({ filter }), //키값의 값이 동일하면 그냥 하나로 써도 됨

    getFilteredTodos: () => {
        const { todos, filter } = get();
        switch (filter) {
            case "active":
                return todos.filter(todo => !todo.completed)
            case "completed":
                return todos.filter(todo => todo.completed)
            default:
                return todos;
        }
    }
}))

export default useTodoStore