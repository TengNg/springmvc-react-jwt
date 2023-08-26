import useAuth from "../hooks/useAuth";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import useAxiosPrivate from "../hooks/useAxiosPrivate"
import { axiosPrivate } from "../api/axios";
import cookie from "react-cookies"

const Products = () => {
    const { auth, setAuth } = useAuth();
    const [products, setProducts] = useState();
    const navigate = useNavigate();

    const axiosWithInterceptors = useAxiosPrivate();

    useEffect(() => {
        const getProducts = async () => {
            const response = await axiosWithInterceptors.get('/api/products');
            const { user, products, accessToken } = response.data;
            setProducts(products);
            setAuth({ username: user.username, accessToken });
        }
        getProducts().catch(err => {
            console.log(err);
            navigate('/login');
        });
    }, []);

    const logout = async () => {
        try {
            await axiosPrivate.get('/logout');
            setAuth({});
            navigate('/login', { replace: true });
        } catch (err) {
            setAuth({});
            navigate('/', { replace: true });
        }
    };

    return (
        <>
            <div className="section--style w-fit p-4 text-xl font-bold m-2">Username: {auth?.username}</div>
            <div className="w-[100px] h-[50px] m-3">
                <button
                    onClick={logout}
                    className="button--style button--hover">logout</button>
            </div>
            <div className="flex flex-wrap justify-center items-center max-w-[60%]">
                {products &&
                    products.map((product, index) => {
                        const { name, manufacturer, price } = product;
                        return <div
                            className='div--style'
                            key={index}>
                            <p>{name}</p>
                            <p>{manufacturer}</p>
                            <p>{price}</p>
                        </div>
                    })
                }
            </div>
        </>
    )
}

export default Products
