import React, { useState, useEffect } from 'react'
import { axiosPrivate } from '../api/axios';
import { useNavigate } from 'react-router-dom';
import useAuth from '../hooks/useAuth';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSquareMinus, faSquarePlus } from '@fortawesome/free-solid-svg-icons';
import useCart from '../hooks/useCart';
import useAxiosPrivate from '../hooks/useAxiosPrivate';
import LOCAL_STORAGE_KEY from '../data/localStorageKey';

export default function UserAccount() {
    const { auth, setAuth } = useAuth();
    const { cart, setCart } = useCart();
    const [show, setShow] = useState(false);
    const navigate = useNavigate();

    const axiosWithInterceptors = useAxiosPrivate();

    useEffect(() => {
        const getUserInformation = async () => {
            const response = await axiosWithInterceptors.get('/api/account/');
            const { user, accessToken } = response.data;
            setAuth({
                accessToken,
                username: user.username,
                userId: user.userId,
                firstName: user.firstName,
                lastName: user.lastName,
                email: user.email,
                address: user.address,
                phone: user.phone,
                userProfileImage: user.imageUrl,
                userRole: user.userRole,
                sellerId: user.userId
            });
        }
        getUserInformation();
    }, []);

    const handleLogout = async () => {
        try {
            await axiosPrivate.get('/logout/');
            setAuth({});
            setCart([]);
            localStorage.removeItem(LOCAL_STORAGE_KEY);
            navigate("/");
        } catch (err) {
            setAuth({});
            navigate('/');
        }
    };

    const handleOpenUserCart = () => {
        setShow(false);
        navigate("/cart");
    };

    return (
        <>
            {Object.keys(auth).length === 0 || !auth?.accessToken ? (
                <div className='flex absolute top-[1rem] right-[1rem] gap-4'>
                    <button
                        onClick={() => navigate("/login")}
                        className="text-sm button--style--2 bg-gray-700 border-gray-700 text-white"
                    >Login</button>
                    <button
                        onClick={() => navigate("/register/for-sellers")}
                        className='text-sm font-normal button--style--2 flex--center bg-gray-300 border-[2px]'
                    >For Business</button>
                </div>
            ) : (
                <>
                    <div className="absolute top-[1rem] right-[1rem] flex flex-row gap-2 w-fit h-fit z-30">
                        {!show ? (
                            <>
                                {/* not full show */}
                                <div onClick={() => setShow(show => !show)} className='section--style--3 bg-gray-50 flex flex-row p-2 gap-2' >
                                    <div className='w-[30px] h-[30px] rounded-full border-black border-[3px] bg-center bg-cover overflow-hidden cursor-pointer'>
                                        <img onClick={() => navigate('/profile')} className="flex--center w-[100%] h-[100%]" src={auth?.userProfileImage} />
                                    </div>
                                    <div className='flex--center w-[100px] select-none cursor-pointer'>
                                        <div className='text-[0.75rem] font-bold w-[100%] min-w-0 overflow-hidden whitespace-nowrap text-ellipsis'>
                                            {auth?.username}
                                        </div>
                                    </div>
                                </div>
                            </>
                        ) : (<div onClick={() => setShow(prev => !prev)} className='div--style flex--center flex-col w-fit gap-3 bg-gray-50'>
                            <div className='flex flex-row flex--center gap-3 px-2'>
                                <div className='flex--center w-[40px] h-[40px] rounded-full border-black border-[3px] bg-center bg-cover overflow-hidden cursor-pointer'>
                                    <img onClick={() => navigate('/profile')} className="flex--center h-[100%] w-[100%]" src={auth?.userProfileImage} />
                                </div>
                                <div className='flex flex-col'>
                                    <div className='select-none text-[0.8rem] font-bold w-[100px] overflow-hidden whitespace-nowrap text-ellipsis'>
                                        {auth?.username}
                                    </div>
                                    <div className='select-none text-[0.55rem] font-normal w-[100px] overflow-hidden whitespace-nowrap'>
                                        {auth?.email}
                                    </div>
                                </div>
                            </div>

                            {auth?.userRole === "ROLE_SELLER"
                                && <button onClick={() => navigate('/for-sellers')} className='text-[0.75rem] w-[95%] button--style--2'>
                                    Your Shop
                                </button>
                            }

                            <button onClick={handleOpenUserCart} className='text-[0.75rem] w-[95%] button--style--2 flex--center gap-1'>
                                Cart {cart.length > 0 && (
                                    <span className="flex--center w-[0.75rem] h-[0.75rem] bg-red-500 text-[0.5rem] text-white rounded-full p-1">
                                        {cart.length}
                                    </span>
                                )}
                            </button>
                            <button onClick={handleLogout} className='text-[0.75rem] w-[95%] button--style--2'>
                                Logout
                            </button>
                        </div>)
                        }

                        <button
                            className='w-7 h-7'
                            onClick={() => setShow(show => !show)}
                        >
                            {show ? <FontAwesomeIcon className="w-[100%] h-[100%]" icon={faSquareMinus} /> : <FontAwesomeIcon className="w-[100%] h-[100%]" icon={faSquarePlus} />}
                        </button>

                    </div>

                </>
            )}
        </>
    )
}

