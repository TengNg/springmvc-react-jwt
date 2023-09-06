import { useState, useEffect } from 'react';
import useAuth from "../hooks/useAuth";
import { useNavigate } from "react-router-dom";
import useAxiosPrivate from "../hooks/useAxiosPrivate";
import { authAxios } from '../api/axios';
import Products from '../components/product/Products';

const ProductManagement = () => {
    const [products, setProducts] = useState([]);
    const { auth, setAuth } = useAuth();
    const axiosWithInterceptors = useAxiosPrivate();
    const navigate = useNavigate();

    useEffect(() => {
        if (!Object.keys(auth).length) {
            const getUserInformation = async () => {
                const response = await axiosWithInterceptors.get('/api/account/');
                const { user, accessToken } = response.data;

                setAuth({
                    accessToken,
                    username: user.username,
                    userProfileImage: user.imageUrl,
                    email: user.email,
                    userRole: user.userRole,
                    sellerId: user.userId
                });

                if (user.userRole !== "ROLE_SELLER") {
                    navigate("/notfound");
                }

                const response2 = await authAxios.get(`/api/for-sellers/manage-products/${user.userId}/`);
                setProducts(response2.data.products);
            }
            getUserInformation().catch(err => {
                console.log(err);
                if (err?.response?.status === 401) {
                    navigate("/");
                }
            });
        }
    }, []);

    return (
        <>
            <section className="w-[100%] h-[100vh] flex flex-col items-center">
                <div className="w-[80%] mt-8">
                    <div className='flex select-none m-[0_0_2rem_0]'>
                        <h1 className="text-[2rem] text-gray-700 relative text-center font-bold underline--style--2 underline--hover--2 transition all hover:text-gray-500"
                        >Your Selling Products</h1>
                    </div>
                </div>

                <div className="w-[80%] mt-8">
                    <div className="div--style flex flex-wrap flex--center gap-6">
                        <Products
                            products={products}
                        />
                    </div>
                </div>

            </section>
        </>
    )
}

export default ProductManagement
