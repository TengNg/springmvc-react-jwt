import axios from "../api/axios";
import { useState, useEffect } from "react";
import { useNavigate } from 'react-router-dom';
import Products from "../components/product/Products";
import Categories from "../components/category/Categories";
import SearchBar from "../components/SearchBar";

const Home = () => {
    const [products, setProducts] = useState([]);
    const [categories, setCategories] = useState([]);
    const [error, setError] = useState(false);

    const navigate = useNavigate();

    useEffect(() => {
        const getProducts = async () => {
            const response1 = await axios.get("/api/products/");
            const response2 = await axios.get("/api/categories/");
            setProducts(response1?.data?.products);
            setCategories(response2?.data?.categories);
        };

        getProducts().catch(_ => {
            setError(true);
        });
    }, []);

    const handleProductItemOnClick = (id) => {
        navigate(`/shop/products/${id}`);
    };

    // handle request params
    const handleCategoryItemOnClick = (id) => {
        console.log(id);
        const requestParams = {};
        // navigate(`/product/${null}`);
    };

    if (error) {
        return <section className="w-[100%] grid place-items-center">
            <h1>Oops, server error :(</h1>
        </section>
    }

    return (
        <section className="w-[100%] h-[100vh] flex flex-col items-center">
            <SearchBar />

            <div className="flex flex-wrap justify-center items-center w-[80%]">
                <Categories
                    categories={categories}
                    handleCategoryItemOnClick={handleCategoryItemOnClick}
                />
            </div>

            <div className="w-[80%] mt-8">
                <div className='flex select-none m-[0_0_2rem_0]'>
                    <h1 className="text-[2rem] text-gray-700 relative text-center font-bold underline--style--2 underline--hover--2 transition all hover:text-gray-500"
                    >Products</h1>
                </div>
                <div className="div--style flex flex-wrap flex--center gap-6">
                    <Products
                        products={products}
                        handleProductItemOnClick={handleProductItemOnClick}
                    />
                </div>
            </div>
        </section>
    )
}

export default Home
