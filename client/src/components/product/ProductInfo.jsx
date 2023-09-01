import { useState, useEffect } from "react";
import axios from "../../api/axios";
import { useParams, useNavigate } from 'react-router-dom';
import { formatCurrencyVND } from '../../utils/currencyFormatter.js';
import useAuth from "../../hooks/useAuth";
import useCart from "../../hooks/useCart";

const ProductInfo = () => {
    const { productId } = useParams();
    const [product, setProduct] = useState(null);

    const navigate = useNavigate();

    const { auth } = useAuth();
    const { addToCart } = useCart();

    useEffect(() => {
        const getProductData = async () => {
            const response = await axios.get(`/api/products/${productId}`);
            setProduct(response?.data?.product);
        };
        getProductData().catch(_ => {
            navigate("/");
        });
    }, []);

    const handleBuyNow = () => {
        if (!auth?.accessToken) {
            navigate("/login");
        }

        console.log("proceed purchase");
    };

    const handleAddToCart = () => {
        // handle add to cart (using localStorage)
        addToCart({
            id: product.id,
            image: product.image,
            name: product.name,
            price: product.price
        });
    };

    return (
        <>
            <section className="w-[100%]">
                <div className='w-[100px] h-[3rem] absolute left-[1rem] top-[0.75rem]'>
                    <button
                        className='button--style button--hover'
                        onClick={() => navigate(-1)}
                    >Back</button>
                </div>

                <div className="flex flex-row mt-[5rem]">
                    <div className="div--style p-5 ms-[1rem]">

                        <div className="flex justify-center gap-4">
                            <div className='flex--center w-[15rem] h-[15rem] border-black border-[3px] bg-center bg-cover overflow-hidden cursor-pointer'>
                                <img className='w-[100%] h-auto' src={product?.image} />
                            </div>

                            <div>
                                <p className="text-xl">{product?.name} - {product?.description}</p>
                                <p className="font-normal">{product?.categoryId?.name}</p>
                            </div>
                        </div>

                        <p className="mb-[2rem]">Price: {formatCurrencyVND(product?.price)}</p>
                    </div>

                    <div className="div--style flex flex-col gap-5 h-fit w-[15rem]">
                        <p className="text-2xl">{formatCurrencyVND(product?.price)}</p>
                        <p className="font-normal">Lorem ipsum dolor sit amet, qui minim labore adipisicing minim sint cillum sint consectetur cupidatat.</p>
                        <div className="w-[100%] h-[50px]">
                            <button
                                className="button--style button--hover"
                                onClick={() => handleAddToCart()}
                            >Add to cart</button>
                        </div>
                        <div className="w-[100%] h-[50px]">
                            <button
                                className="button--style button--hover"
                                onClick={() => handleBuyNow()}
                            >Buy now</button>
                        </div>
                    </div>
                </div>
            </section>
        </>
    )
}

export default ProductInfo
