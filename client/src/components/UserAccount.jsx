import React from 'react'
import { useEffect } from 'react';
import { axiosPrivate } from '../api/axios';
import { useNavigate } from 'react-router-dom';
import useAuth from '../hooks/useAuth';
import useAxiosPrivate from '../hooks/useAxiosPrivate';

export default function UserAccount() {
    const { auth, setAuth } = useAuth();

    const navigate = useNavigate();

    const axiosWithInterceptors = useAxiosPrivate();

    useEffect(() => {
        const getUserInformation = async () => {
            const response = await axiosWithInterceptors.get('/api/account');
            const { user, accessToken } = response.data;
            setAuth({ username: user.username, email: user.email, accessToken });
        }
        getUserInformation().catch(err => {
            console.log(err);
        });
    }, []);

    const handleLogout = async () => {
        try {
            await axiosPrivate.get('/logout');
            setAuth({});
        } catch (err) {
            setAuth({});
            navigate('/', { replace: true });
        }
    };

    const handleOpenUserCart = () => {
    };

    return (
        <>
            {Object.keys(auth).length === 0 || !auth?.accessToken ? (
                <div className='absolute w-[8rem] h-[3rem] top-[0.75rem] right-[1rem]'>
                    <button
                        onClick={() => navigate("/login")}
                        className='button--style button--hover'
                    >Login</button>
                </div>
            ) : (
                <div className='flex--center flex-col section--style p-3 w-fit absolute top-[0.75rem] right-[1rem] gap-3'>
                    <div className='select-none'>
                        <h3 className="font-bold">Username: {auth?.username}</h3>
                        <h3 className="font-bold">Email: {auth?.email}</h3>
                    </div>
                    <div className='w-[95%] h-[3rem]'><button onClick={handleOpenUserCart} className='button--style button--hover'>Cart</button></div>
                    <div className='w-[95%] h-[3rem]'><button onClick={handleLogout} className='button--style button--hover'>Logout</button></div>
                </div>
            )}
        </>
    )
}

