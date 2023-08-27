import { useState, useEffect } from "react";
import axios from "../api/axios";
import { useParams } from 'react-router-dom';

const Product = () => {
    const { productId } = useParams();
    const [product, setProduct] = useState(null);

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
            <h3>{product?.name}</h3>
            <h3>{product?.price}</h3>
        </>
    )
}

export default Product
