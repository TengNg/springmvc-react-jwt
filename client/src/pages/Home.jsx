import axios from "../api/axios";
import { useState, useEffect } from "react";
import UserAccount from "../components/UserAccount";
import ProductItem from "../components/product/ProductItem";
import { useNavigate } from 'react-router-dom';

const Home = () => {
    const [products, setProducts] = useState();
    const [error, setError] = useState(false);

    const navigate = useNavigate();

    useEffect(() => {
        const getProducts = async () => {
            const response = await axios.get("/api/products/");
            setProducts(response?.data?.products);
        };
        getProducts().catch(err => {
            console.log(err);
            setError(true);
        });
    }, []);

    const handleOnClick = (id) => {
        navigate(`/product/${id}`);
    };

    if (error) {
        return <section className="w-[100%] grid place-items-center">
            <h1>Oops, server error :(</h1>
        </section>
    }

    return (
        <section className="w-[60%] h-[100%] relative">
            <div className="flex flex-wrap justify-center items-center w-[100%]">
                {products &&
                    products.map(product => {
                        return <ProductItem
                            handleOnClick={handleOnClick}
                            key={product.id} product={product}
                        />
                    })
                }
            </div>
        </section>
    )
}

export default Home
