import React, { useState } from 'react'
import { useEffect } from 'react';
import { axiosPrivate } from '../api/axios';
import { useNavigate } from 'react-router-dom';
import useAuth from '../hooks/useAuth';
import useAxiosPrivate from '../hooks/useAxiosPrivate';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSquareMinus, faSquarePlus } from '@fortawesome/free-solid-svg-icons';

export default function UserAccount() {
    const { auth, setAuth } = useAuth();
    const [profileImage, setProfileImage] = useState(null);
    const [show, setShow] = useState(false);

    const navigate = useNavigate();

    const axiosWithInterceptors = useAxiosPrivate();

    useEffect(() => {
        const getUserInformation = async () => {
            const response = await axiosWithInterceptors.get('/api/account');
            const { user, accessToken } = response.data;
            setAuth({ username: user.username, email: user.email, accessToken });
            setProfileImage(user.profileImage);
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
                <>
                    <div className="absolute top-[0.75rem] right-[1rem] flex flex-row gap-2 w-fit h-fit z-50">
                        {!show
                            ? (
                                <div
                                    onClick={() => setShow(show => !show)}
                                    className='section--style--3 bg-gray-50 flex flex-row p-2 gap-2'
                                >
                                    <div className='w-[30px] h-[30px] rounded-full border-black border-[3px] bg-center bg-cover overflow-hidden cursor-pointer'>
                                        <img src={profileImage} />
                                    </div>
                                    <div className='flex--center w-[100px] select-none cursor-pointer'>
                                        <div className='text-[0.75rem] font-bold w-[100%] min-w-0 overflow-hidden whitespace-nowrap text-ellipsis'>
                                            {auth?.username}
                                        </div>
                                    </div>
                                </div>)
                            : (<div className='section--style--2 flex--center flex-col p-3 w-fit gap-3 bg-gray-50 rounded-md'>
                                <div className='flex flex-row flex--center gap-4 p-2'>
                                    <div className='w-[50px] h-[50px] rounded-full border-black border-[3px] bg-center bg-cover overflow-hidden cursor-pointer'>
                                        <img src={profileImage} />
                                    </div>
                                    <div className='flex flex-col'>
                                        <div className='select-none text-[1rem] font-bold w-[100px] overflow-hidden whitespace-nowrap text-ellipsis'>
                                            {auth?.username}
                                        </div>
                                        <div className='select-none text-[0.75rem] w-[100px] overflow-hidden whitespace-nowrap'>
                                            {auth?.email}
                                        </div>
                                    </div>
                                </div>
                                <div className='w-[95%] h-[3rem]'><button onClick={handleOpenUserCart} className='button--style button--hover'>Cart</button></div>
                                <div className='w-[95%] h-[3rem]'><button onClick={handleLogout} className='button--style button--hover'>Logout</button></div>
                            </div>)}


                        <button
                            className='w-7 h-7'
                            onClick={() => setShow(show => !show)}
                        >
                            {show
                                ? <FontAwesomeIcon className="w-[100%] h-[100%]" icon={faSquareMinus} />
                                : <FontAwesomeIcon className="w-[100%] h-[100%]" icon={faSquarePlus} />
                            }
                        </button>

                    </div>

                </>
            )}
        </>
    )
}

