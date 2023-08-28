import { useState, useEffect } from "react";
import axios from "../../api/axios";
import { useParams, useLocation, useNavigate } from 'react-router-dom';
import UserAccount from "../UserAccount";

const Product = () => {
    const { productId } = useParams();
    const [product, setProduct] = useState(null);

    const navigate = useNavigate();
    const location = useLocation();
    const from = location.state?.from?.pathname || "/";

    useEffect(() => {
        const getProductData = async () => {
            const response = await axios.get(`/api/products/${productId}`);
            setProduct(response?.data?.product);
        };
        getProductData().catch((err) => {
            console.log(err);
        });
    }, []);

    return (
        <>
            <section className="w-[100%]">
                <UserAccount />
                <div className='w-[100px] h-[3rem] absolute left-[1rem] top-[1rem]'>
                    <button
                        className='button--style button--hover'
                        onClick={() => navigate(from, { replace: true })}
                    >Back</button>
                </div>
                <div className="div--style p-5 ms-[1rem] mt-[5rem]">
                    <div className="h-[200px]">
                        <h2>{product?.name}</h2>
                        <h2>{product?.price}</h2>
                    </div>
                    <div className="flex flex-col gap-5">
                        <div className="w-[150px] h-[50px]">
                            <button className="button--style button--hover">Add to cart</button>
                        </div>
                        <div className="w-[150px] h-[50px]">
                            <button className="button--style button--hover">Buy it now</button>
                        </div>
                    </div>
                </div>
            </section>
        </>
    )
}

export default Product
