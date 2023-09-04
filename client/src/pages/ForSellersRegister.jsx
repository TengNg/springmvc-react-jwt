import { useState, useRef, useEffect } from 'react';
import { Link, useNavigate, useLocation } from 'react-router-dom';
import { axiosPrivate } from '../api/axios';
import Title from '../components/Title';
import BasicLayout from '../components/layout/BasicLayout';
import USER_ROLES from '../data/userRoles';

// const USER_REGEX = /^[A-z][A-z0-9-_]{3,23}$/;
// const PWD_REGEX = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%]).{8,24}$/;
// const PWD_REGEX = /^.{8,24}$/;

export default function ForSellersRegister() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [confirmedPassword, setConfirmedPassword] = useState("");
    const [image, setImage] = useState();
    const [previewImage, setPreviewImage] = useState();

    const passwordInputEl = useRef(null);
    const usernameInputEl = useRef(null);
    const confirmedPasswordInputEl = useRef(null);

    const imageInputRef = useRef();

    const [errMsg, setErrMsg] = useState('');
    const [success, setSuccess] = useState(false);

    const navigate = useNavigate();

    useEffect(() => {
        const isLoggedIn = async () => {
            const response = await axiosPrivate.get('/api/check-cookies/');
            if (response.status === 200) {
                navigate('/');
            }
        }
        isLoggedIn().catch(_ => {
            setSuccess(false);
            usernameInputEl.current.focus();
        });
    }, []);

    const handleImageChange = (event) => {
        const file = event.target.files[0];
        setImage(file);

        if (file) {
            const reader = new FileReader();
            reader.onload = () => {
                setPreviewImage(reader.result);
            };
            reader.readAsDataURL(file);
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        // const matched = PWD_REGEX.test(password);
        //
        // if (matched === false) {
        //     setErrMsg('Password invalid');
        //     passwordInputEl.current.focus();
        //     return;
        // }

        if (confirmedPassword !== password) {
            setErrMsg('Password do not match');
            setSuccess(false);
            confirmedPasswordInputEl.current.focus();
            return;
        }

        const formData = new FormData();
        formData.append('image', image);
        formData.append('username', username);
        formData.append('password', password);
        formData.append('role', USER_ROLES.seller);

        try {
            await axiosPrivate.post('/api/register/', formData);
            setSuccess(true);
            setUsername('');
            setPassword('');
            setConfirmedPassword('');
            navigate('/login');
        } catch (err) {
            if (!err?.response) {
                setErrMsg('No Server Response');
            } else if (err.response?.status === 422) {
                setErrMsg('Missing profile image');
            } else if (err.response?.status === 409) {
                setErrMsg('Username Taken');
            } else {
                setErrMsg('Registration Failed')
            }
        }
    }

    return (
        <>
            <BasicLayout styles={'relative w-[100%] h-[100vh] flex flex-col items-center p-5 gap-2 bg-gray-300'}>
                <Title titleName={"Register"} />

                <p className="font-bold">For Sellers</p>

                <form onSubmit={handleSubmit} className='flex flex-row section--style p-4'>
                    <div
                        className="div--style shadow-none w-[80px] h-[80px] rounded-full me-6 bg-cover bg-center"
                        style={{ backgroundImage: `url(${previewImage})` }}
                        onClick={() => imageInputRef.current.click()}
                    >
                        <input className="hidden" ref={imageInputRef} type="file" id="profileImage" accept="image/*" onChange={handleImageChange} />
                    </div>
                    <div className="flex flex-col">
                        <label htmlFor="username" className='label--style'>Username:</label>
                        <input
                            className='border-[4px] border-black p-1 font-bold'
                            type="text"
                            id="username"
                            autoComplete="off"
                            ref={usernameInputEl}
                            onChange={(e) => setUsername(e.target.value)}
                            value={username}
                            maxLength="25"
                            required
                        />

                        <label htmlFor="password" className='label--style'>Password:</label>
                        <input
                            className='border-[4px] border-black p-1 font-bold'
                            type="password"
                            id="password"
                            ref={passwordInputEl}
                            onChange={(e) => setPassword(e.target.value)}
                            value={password}
                            autoComplete="on"
                            required
                        />

                        <label htmlFor="password" className='label--style'>Confirm Password:</label>
                        <input
                            className='border-[4px] border-black p-1 font-bold'
                            type="confirmed-password"
                            id="confirmed-password"
                            ref={confirmedPasswordInputEl}
                            onChange={(e) => setConfirmedPassword(e.target.value)}
                            value={confirmedPassword}
                            autoComplete="on"
                            required
                        />

                        <div className='h-[1rem] w-[100%] my-1 flex--center'>
                            {success === false && <p className='text-xs text-red-700 top-[1rem] right-[1rem] font-bold select-none'>{errMsg}</p>}
                        </div>

                        <div className='w-[70%] h-[3rem]'>
                            <button className='button--style button--hover bg-white'>Sign up</button>
                        </div>

                    </div>
                </form>

            </BasicLayout>
        </>
    )
}
