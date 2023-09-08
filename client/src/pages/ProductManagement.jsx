import { useState, useEffect, useRef } from 'react';
import useAuth from "../hooks/useAuth";
import { useNavigate } from "react-router-dom";
import { authAxios, axiosPrivate } from '../api/axios';
import Products from '../components/product/Products';

const ProductManagement = () => {
    const [image, setImage] = useState();
    const [previewImage, setPreviewImage] = useState();
    const [products, setProducts] = useState([]);
    const { auth } = useAuth();
    const navigate = useNavigate();
    const imageInputRef = useRef();

    useEffect(() => {
        const isLoggedIn = async () => {
            await axiosPrivate.get('/api/check-cookies/');
        }
        isLoggedIn().catch(_ => {
            navigate('/notfound');
        });
    }, []);

    useEffect(() => {
        if (Object.keys(auth).length > 0) {
            if (auth?.userRole !== "ROLE_SELLER") {
                navigate("/notfound");
            }

            const getProducts = async () => {
                const response = await authAxios.get(`/api/for-sellers/manage-products/${auth?.userId}/`);
                setProducts(response.data.products);
            }

            getProducts().catch(err => {
                if (err?.response?.status === 401) {
                    navigate("/");
                }
            });
        }
    }, [auth]);

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

    return (
        <>
            <section className="w-[100%] flex flex-col items-center gap-4">
                <div className="w-[80%] mt-8">
                    <div className='flex select-none m-[0_0_2rem_0]'>
                        <h1 className="text-[2rem] text-gray-700 relative text-center font-bold underline--style--2 underline--hover--2 transition all hover:text-gray-500"
                        >Your Selling Products</h1>
                    </div>
                </div>

                <div className="w-[80%] mt-8">
                    <div className="div--style flex flex-wrap flex--center gap-6 p-8">
                        <Products
                            products={products}
                        />
                    </div>
                </div>

                <div className='mx-auto div--style flex flex-row justify-between mt-7 w-[1300px] p-7 bg-gray-100'>
                    <div
                        className="div--style shadow-none flex-grow me-6 bg-cover bg-center"
                        style={{ backgroundImage: `url(${previewImage})` }}
                        onClick={() => imageInputRef.current.click()}
                    >
                        Product image
                        <input className="hidden" ref={imageInputRef} type="file" id="profileImage" accept="image/*" onChange={handleImageChange} />
                    </div>

                    <form className='w-[65%] flex flex-col border-black border-[2px] px-6 py-3 h-fit'>
                        <div className="flex justify-between gap-2">
                            <div className="flex flex-col w-[50%]">
                                <label htmlFor="username" className='label--style'>First name:</label>
                                <input
                                    className='border-[2px] border-black p-1 font-bold'
                                    type="text"
                                    id="username"
                                    autoComplete="off"
                                    required
                                />
                            </div>
                            <div className="flex flex-col w-[50%]">
                                <label htmlFor="username" className='label--style'>Last name:</label>
                                <input
                                    className='border-[2px] border-black p-1 font-bold'
                                    type="text"
                                    id="username"
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
                            required
                        />

                        <label htmlFor="email" className='label--style mt-2'>Email address:</label>
                        <input
                            className='border-[2px] border-black p-1 font-bold'
                            type="text"
                            id="email"
                            autoComplete="off"
                            required
                        />


                        <label htmlFor="phoneNumber" className='label--style mt-2'>Phone number:</label>
                        <input
                            className='border-[2px] border-black p-1 font-bold'
                            type="text"
                            id="phoneNumber"
                            autoComplete="off"
                            required
                        />

                        <p className="mt-6 font-normal text-sm">Note: Please fill in all the required fields</p>

                        <button
                            className='text-white p-3 bg-gray-700 hover:bg-gray-600 transition-all w-[100%]'
                        >Add new product</button>

                    </form>

                </div>

            </section>
        </>
    )
}

export default ProductManagement
