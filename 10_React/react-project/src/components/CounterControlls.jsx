import React from 'react'
import styled from 'styled-components'
import useCounterStore from '../store/useCounterStore'

const Button = styled.button`
    margin-right: 8px;
    padding: 8px 16px;
    border: none;
    border-radius: 4px;
    background-color: #6565ff;
    color: white;
    cursor: pointer;
    &:hover {
        opacity: 0.9;
    }
    &:last-child{
        margin-right: 0px;
    }
`
const CounterControlls = () => {
    const {increase, decrease, reset} = useCounterStore();
  return  (
    <div>
        <Button onClick={increase}>+1</Button>
        <Button onClick={decrease}>-1</Button>
        <Button onClick={reset}>초기화</Button>
    </div>
  )
}
 
export default CounterControlls