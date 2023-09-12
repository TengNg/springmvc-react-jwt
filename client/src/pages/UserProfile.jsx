import { useState, useEffect } from "react";
import useAuth from "../hooks/useAuth";
import axios, { axiosPrivate } from "../api/axios";
import { useNavigate } from "react-router-dom";

const UserProfile = () => {
    const [username, setUsername] = useState("");
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [address, setAddress] = useState("");
    const [email, setEmail] = useState("");
    const [phone, setPhone] = useState("");
    const [changePassword, setChangePassword] = useState(false);
    const [msg, setMsg] = useState();
    const [error, setError] = useState(false);

    const { auth } = useAuth();
    const navigate = useNavigate();

    useEffect(() => {
        const isLoggedIn = async () => {
            await axiosPrivate.get('/api/check-cookies/');
        }
        isLoggedIn().catch(err => {
            console.log(err);
            navigate("/notfound");
        });
    }, []);

    useEffect(() => {
        setUsername(auth?.username);
        setFirstName(auth?.firstName);
        setLastName(auth?.lastName);
        setAddress(auth?.address);
        setEmail(auth?.email);
        setPhone(auth?.phone);
    }, [auth]);

    const handleChangePassword = (e) => {
        e.preventDefault();
        if (!changePassword) {
            setChangePassword(true);
            return;
        }

        // handle change password
    };

    const handleSaveUserInfo = async (e) => {
        e.preventDefault();
        const data = { firstName, lastName, email, address, phone }
        try {
            await axios.put(`/api/account/edit/${auth.username}`, data);
            setMsg("Profile saved");
            setError(false);
        } catch (err) {
            setMsg("Error!");
            setError(true);
        }
    };

    return (
        <div className='mx-auto div--style flex flex-col justify-between mt-7 min-h-[200px] p-7 bg-gray-100 relative'>
            {auth.userRole == "ROLE_SELLER" && <p className="mx-auto text-sm bg-green-800 w-fit text-white p-2">Verified Seller</p>}
            <form id='userInfoForm' className='w-[100%] flex flex-col px-6 py-3 h-fit gap-1'>
                <p className={`absolute top-[1rem] right-[3rem] font-normal ${error ? 'text-red-600' : 'text-green-500'}`}>{msg}</p>
                <label htmlFor="username" className='label--style'>Username:</label>
                <input
                    className='border-[2px] border-black p-1 font-bold'
                    type="text"
                    id="username"
                    autoComplete="off"
                    value={username}
                    onChange={(e) => setFirstName(e.target.value)}
                    required
                />
                <div className="flex justify-between gap-2 mt-2">
                    <div className="flex flex-col w-[50%]">
                        <label htmlFor="username" className='label--style'>First name:</label>
                        <input
                            className='border-[2px] border-black p-1 font-bold'
                            type="text"
                            id="username"
                            autoComplete="off"
                            value={firstName}
                            onChange={(e) => setFirstName(e.target.value)}
                            required
                        />
                    </div>
                    <div className="flex flex-col w-[50%]">
                        <label htmlFor="username" className='label--style'>Last name:</label>
                        <input
                            className='border-[2px] border-black p-1 font-bold'
                            type="text"
                            id="username"
                            value={lastName}
                            onChange={(e) => setLastName(e.target.value)}
                            autoComplete="off"
                            required
                        />
                    </div>
                </div>

                <label htmlFor="address" className='label--style mt-2'>Address:</label>
                <input
                    className='border-[2px] border-black p-1 font-bold'
                    type="text"
                    id="address"
                    autoComplete="off"
                    value={address}
                    onChange={(e) => setAddress(e.target.value)}
                    required
                />

                <label htmlFor="email" className='label--style mt-2'>Email address:</label>
                <input
                    className='border-[2px] border-black p-1 font-bold'
                    type="text"
                    id="email"
                    autoComplete="off"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    required
                />


                <label htmlFor="phoneNumber" className='label--style mt-2'>Phone number:</label>
                <input
                    className='border-[2px] border-black p-1 font-bold'
                    type="text"
                    id="phoneNumber"
                    autoComplete="off"
                    value={phone}
                    onChange={(e) => setPhone(e.target.value)}
                    required
                />

                {changePassword &&
                    <div className="flex flex-col div--style w-[100%] my-4 relative py-8">
                        <p onClick={() => setChangePassword(false)} className="absolute top-[0] right-[0.5rem]">Close</p>
                        <label htmlFor="phoneNumber" className='label--style'>Password:</label>
                        <input
                            className='border-[2px] border-black p-1 font-bold'
                            type="text"
                            id="phoneNumber"
                            autoComplete="off"
                            onChange={(e) => setPhone(e.target.value)}
                            required
                        />

                        <label htmlFor="phoneNumber" className='label--style mt-4'>Confirm Password:</label>
                        <input
                            className='border-[2px] border-black p-1 font-bold'
                            type="text"
                            id="phoneNumber"
                            autoComplete="off"
                            value={phone}
                            onChange={(e) => setPhone(e.target.value)}
                            required
                        />
                    </div>
                }

                <div className="flex flex-col gap-4 mt-4">
                    <button
                        type='submit'
                        form='userInfoForm'
                        onClick={() => false}
                        className='text-sm text-white p-3 bg-gray-700 hover:bg-gray-600 transition-all w-[100%] pointer-events-none'
                    >
                        {!changePassword ? 'Change your password' : 'Check password'}
                    </button>

                    <button
                        type='submit'
                        onClick={handleSaveUserInfo}
                        className='text-sm text-white p-3 bg-gray-700 hover:bg-gray-600 transition-all w-[100%]'
                    >Save</button>
                </div>
            </form>

        </div>
    )
}

export default UserProfile
