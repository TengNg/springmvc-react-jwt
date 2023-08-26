import React from 'react'
import Title from '../components/Title'
import NavBar from '../components/NavBar'

export default function Home() {
    return (
        <>
            <div className="w-[100%] h-[100vh] flex flex-col bg-gray-300">
                <Title titleName={"Testing"}/>
                <NavBar />
            </div>
        </>
    )
}
