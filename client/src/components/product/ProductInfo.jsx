import { useState, useEffect } from "react";
import axios from "../../api/axios";
import { useParams, useNavigate } from 'react-router-dom';
import { formatCurrencyVND } from '../../utils/currencyFormatter.js';
import useAuth from "../../hooks/useAuth";
import useCart from "../../hooks/useCart";
import ReviewForm from "../comment/ReviewForm";
import Review from "../comment/Review";

const ProductInfo = () => {
    const { productId } = useParams();

    const [product, setProduct] = useState(null);
    const [reviews, setReviews] = useState([]);

    const navigate = useNavigate();

    const { auth } = useAuth();
    const { addToCart } = useCart();

    useEffect(() => {
        const getData = async () => {
            const response = await axios.get(`/api/products/${productId}`);
            const response2 = await axios.get(`/api/reviews/${productId}`);
            setProduct(response?.data?.product);
            setReviews(response2?.data?.reviews);
        };
        getData().catch(err => {
            console.log(err);
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
            id: product.productId,
            image: product.imageUrl,
            name: product.name,
            price: product.price
        });
    };

    const handlePostComment = async (text) => {
        const { username } = auth;

        const response = await axios.post("/api/reviews/post", {
            username,
            productId,
            reviewText: text
        });

        const review = response?.data?.review;
        const newReviews = [review, ...reviews];
        setReviews(newReviews);
    };

    return (
        <>
            <section className="w-[100%] px-12 py-8">
                <div className="flex flex-row justify-between mt-3">
                    <div className="div--style p-5 ms-[1rem]">
                        <div className="flex justify-center gap-4">
                            <div className='flex--center w-[15rem] h-[15rem] border-black border-[3px] bg-center bg-cover overflow-hidden cursor-pointer'>
                                <img className='w-[100%] h-auto' src={product?.imageUrl} />
                            </div>

                            <div className="flex flex-col gap-3">
                                <p className="text-xl">{product?.name}</p>
                                <p className="text-sm font-normal">Category: {product?.categoryId?.categoryName}</p>
                                <p className="text-sm font-normal">Seller: {product?.userId?.username}</p>
                                <hr className="border-black" />
                                <p className="max-w-[200px] text-sm">Desscription: <span className="text-sm font-normal">{product?.description}</span> </p>
                            </div>
                        </div>

                        <p className="mt-3 ms-1">Price: {formatCurrencyVND(product?.price)}</p>
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

            <section className="flex flex-col w-[100%] px-12 py-4">
                <div className='select-none w-fit'>
                    <h1 className="text-[1.5rem] text-gray-700 relative font-bold underline--style--2 underline--hover--2 transition all hover:text-gray-500"
                    >Comments</h1>
                </div>

                <div className="w-[100%] flex flex-row justify-between mt-3 gap-10">
                    <ReviewForm
                        handlePostComment={handlePostComment}
                    />

                    <div className="div--style flex-grow">

                        {reviews &&
                            reviews.map((review, index) => {
                                console.log(review);
                                return <Review key={index} review={review} />
                            })
                        }

                    </div>
                </div>


            </section>

        </>
    )
}

export default ProductInfo
