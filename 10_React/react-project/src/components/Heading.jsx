import React from 'react'

const Heading = ({type, children}) => {
    
    if(props.type === 'h2'){
        return <h2>안녕하세요</h2>
    }
    return (
        <>
                <h1>안녕하세요</h1>
                <h2>{props.children}</h2>
        </>
    )
}

export default Heading