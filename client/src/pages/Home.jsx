import axios from "../api/axios";
import { useState, useEffect } from "react";
import UserAccount from "../components/UserAccount";
import ProductItem from "../components/product/ProductItem";
import { useNavigate } from 'react-router-dom';

const Home = () => {
    const [products, setProducts] = useState();

    const navigate = useNavigate();

    useEffect(() => {
        const getProducts = async () => {
            const response = await axios.get("/api/products/");
            setProducts(response?.data?.products);
        };
        getProducts().catch(err => {
            navigate('/login');
            console.log(err);
        });
    }, []);

    const handleOnClick = (id) => {
        navigate(`/product/${id}`);
    };

    return (
        <section className="w-[100%] h-[100%] relative">
            <UserAccount />
            <div className="flex flex-wrap justify-center items-center max-w-[60%]">
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
