import React from 'react'
import ProfileCard from './ProfileCard'
import styled from 'styled-components'

const Container = styled.div`
    width: 100%;
    display: flex;
    flex-wrap: wrap;
    justify-content: flex-start;
    gap: 16px;
    border-radius: 15px;
`

const ProfileList = ({profiles}) => {
    return (
        <Container>
            {profiles.map((p, index) => <ProfileCard key={index} profile={p} />)}
        </Container>
    )
}

export default ProfileList