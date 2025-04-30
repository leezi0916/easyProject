import React from 'react'
import useCounterStore from '../store/useCounterStore'
import styled from 'styled-components'
const DisplayContainer = styled.div`
    font-size: 32px;
    margin: 16px;
    padding: 20px;
    border-radius: 8px;
`
const CountText = styled.span`
    font-weight: bold;
`
const CounterDisplay = () => {
    // const count = useCounterStore((state) => state.count); //함수를 갖고오기
    const {count} = useCounterStore(); //구조 분해 할당으로 가져오는 방식

  return (
    <DisplayContainer>
        현재 카운트 : <CountText>{count}</CountText>
    </DisplayContainer>
  )
}

export default CounterDisplay