import React from 'react'
import { useNavigate } from 'react-router-dom'

export default function NotFound() {
    const navigate = useNavigate();
    return (
        <>
            <div className='flex flex-col flex--center w-[100%] h-[90vh]'>
                <h2 className='font-bold'>Looks like you are lost ...</h2>
                <div className='w-[17rem] h-[5rem]'>
                    <button
                        className='button--style button--hover'
                        onClick={() => navigate('/', { replace: true })}
                    >
                        Go back to home page
                    </button>
                </div>
            </div>
        </>
    )
}
