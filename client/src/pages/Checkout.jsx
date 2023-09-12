import { useState, useEffect } from 'react'
import { formatCurrencyVND } from "../utils/currencyFormatter"
import useCart from '../hooks/useCart.js';
import CartItem from "../components/cart/CartItem";
import axios from "../api/axios";
import useAuth from '../hooks/useAuth';
import { useNavigate } from 'react-router-dom';
import LOCAL_STORAGE_KEY from '../data/localStorageKey';

const Checkout = () => {
    const { cart, setCart } = useCart();
    const { auth } = useAuth();
    const [paymentMethod, setPaymentMethod] = useState("After shipment");
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [address, setAddress] = useState("");
    const [email, setEmail] = useState("");
    const [phone, setPhone] = useState("");

    const navigate = useNavigate();

    useEffect(() => {
        if (Object.keys(auth).length > 0) {
            setFirstName(auth?.firstName);
            setLastName(auth?.lastName);
            setAddress(auth?.address);
            setEmail(auth?.email);
            setPhone(auth?.phone);
        }
    }, [auth]);

    const handleProceedCheckout = async (e) => {
        e.preventDefault();
        const data = { firstName, lastName, email, address, phone }

        try {
            const checkOutResponse = await axios.post("/api/checkout", { username: auth?.username, paymentMethod, items: cart });
            await axios.put(`/api/account/edit/${auth.username}`, data);

            const checkOutData = checkOutResponse.data;
            await axios.post(`/api/save-transaction/`, {
                username: auth?.username,
                cartId: checkOutData.cart.cartId,
                paymentMethod,
                amount: cart.reduce((total, item) => total + +item.price * +item.quantity, 0).toString()
            });

            setCart([]);
            localStorage.removeItem(LOCAL_STORAGE_KEY);
            navigate("/purchase-history");
        } catch (err) {
            navigate('/login');
        }
    };

    if (!cart.length) {
        return <div className='w-[100%] h-full flex flex--center select-none mx-auto  mt-[4rem]'>
            <button
                className="bg-gray-700 text-white px-5 py-3 font-normal"
                onClick={() => navigate("/shop")}
            >Return to shop</button>
        </div>
    }

    return (
        <>
            <div className='w-[100%] flex select-none mx-auto mt-[4rem] ps-[8rem]'>
                <h1 className="text-[2rem] text-gray-700 relative font-bold underline--style--2 underline--hover--2 transition all hover:text-gray-500"
                >Checkout</h1>
            </div>

            <div className='mx-auto div--style flex flex-row justify-between mt-7 w-[1300px] min-h-[200px] p-7 bg-gray-100'>
                <form onSubmit={handleProceedCheckout} id='userInfoForm' className='w-[65%] flex flex-col border-black border-[2px] px-6 py-3 h-fit'>
                    <div className="flex justify-between gap-2">
                        <div className="flex flex-col w-[50%]">
                            <label htmlFor="firstName" className='label--style'>First name:</label>
                            <input
                                className='border-[2px] border-black p-1 font-bold'
                                type="text"
                                id="firstName"
                                autoComplete="off"
                                value={firstName}
                                onChange={(e) => setFirstName(e.target.value)}
                                required
                            />
                        </div>
                        <div className="flex flex-col w-[50%]">
                            <label htmlFor="lastName" className='label--style'>Last name:</label>
                            <input
                                className='border-[2px] border-black p-1 font-bold'
                                type="text"
                                id="lastName"
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

                    <p className="mt-6 font-normal text-sm">Note: Please fill in all the required fields</p>

                </form>

                <div className='flex flex-col gap-4 div--style w-[30%] h-[100%] px-4'>
                    <p className="text-xl text-gray-700">Your order:</p>

                    <div className="w-[100%] flex flex-col gap-4">
                        {cart.map(item => {
                            return <CartItem
                                key={item.id}
                                {...item}
                                isCheckout={true}
                            />
                        })}
                    </div>

                    <div className="border-[1px] border-gray-500 font-normal text-xl text-gray-800 p-4 flex justify-between" >
                        <span> Total </span>
                        <span> ${formatCurrencyVND(cart.reduce((total, item) => total + +item.price * +item.quantity, 0))} </span>
                    </div>

                    <div className="flex--center flex-col gap-3 w-[100%] h-[80%] mx-auto">
                        <button
                            type='submit'
                            form='userInfoForm'
                            className='text-sm text-white p-3 bg-gray-700 hover:bg-gray-600 transition-all w-[100%]'
                        >Place order</button>
                    </div>

                </div>

            </div>


        </>
    )
}

export default Checkout
