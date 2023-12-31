import axios from "../api/axios";
import { useState, useEffect } from "react";
import { useNavigate } from 'react-router-dom';
import Products from "../components/product/Products";
import Categories from "../components/category/Categories";
import SearchBar from "../components/SearchBar";
import useAuth from "../hooks/useAuth";
import Pagination from "../components/Pagination";
import ComparisionBar from "../components/comparison/ComparisionBar";
import useComparision from "../hooks/useComparision";

const PRODUCTS_PER_PAGE = 20;

const Shop = () => {
    const { auth } = useAuth();
    const { comparedItems, setComparedItems } = useComparision();

    const [products, setProducts] = useState([]);
    const [totalItems, setTotalItems] = useState();
    const [categories, setCategories] = useState([]);
    const [error, setError] = useState(false);
    const [productCategory, setProductCategory] = useState("All")
    const [page, setPage] = useState(1);
    const [showComparision, setShowComparision] = useState(false);

    const [productApiUrl, setProductApiUrl] = useState('/api/products?');

    const [sortOption, setSortOption] = useState("");
    const [sortQuery, setSortQuery] = useState("");

    // const query = useQuery();
    const navigate = useNavigate();

    // const categoryQry = !query.get('category') ? "" : `?category=${query.get('category')}`;
    // const productNameQry = !query.get('name') ? "" : `?name=${query.get('name')}`;
    // const sellerNameQry = !query.get('seller') ? "" : `?seller=${query.get('seller')}`;
    // const sortByQry = !query.get('sort_by') ? "" : `?sort_by=${query.get('sort_by')}`;

    // const productApiUrl = query.get('category') ? `/api/products?category=${query.get('category')}` : '/api/products';

    useEffect(() => {
        const getProducts = async () => {
            const response1 = await axios.get(productApiUrl);
            const response2 = await axios.get("/api/categories/");
            setTotalItems(response1?.data?.totalItems);
            setProducts(response1?.data?.products);
            setCategories(response2?.data?.categories);
        };

        getProducts().catch(_ => {
            setError(true);
        });
    }, []);

    useEffect(() => {
        const getData = async () => {
            const response = await axios.get(productApiUrl + `&page=${page - 1}&size=${PRODUCTS_PER_PAGE}` + `&category=${productCategory}` + `&${sortQuery}`);
            setTotalItems(response.data.totalItems);
            setProducts(response.data.products);
        }
        getData();
    }, [productApiUrl, page, productCategory, sortQuery]);

    const handleProductItemOnClick = (id, sellerId) => {
        if (auth?.userId === sellerId) {
            navigate(`/for-sellers`);
            return;
        }
        navigate(`/shop/products/${id}`);
    };

    const handleCategoryItemOnClick = (categoryName) => {
        setPage(1);
        setProductCategory(categoryName);
    };

    const handleSearchProduct = async (searchOption, value) => {
        setProductApiUrl(`/api/products?${searchOption}=${value}`);
    };

    const handleSortProducts = async (name, sortQuery) => {
        if (name === sortOption) {
            setSortOption("");
            setSortQuery("");
            sortQuery = "";
        } else {
            setSortOption(name);
        }

        setPage(1);
        setSortQuery(sortQuery);
    };

    const handlePaginate = (page) => {
        // GET /products?page=1&size=20
        setPage(page);
    };

    const handleCloseComparisionBar = () => {
        setComparedItems([]);
        setShowComparision(false);
    }

    if (error) {
        return <section className="w-[100%] grid place-items-center">
            <h1>Oops, server error :(</h1>
        </section>
    }

    return (
        <section className="w-[100%] relative flex flex-col items-center">
            <SearchBar
                handleSearchProduct={handleSearchProduct}
            />

            <div className="flex flex-wrap justify-center items-center w-[80%] gap-4">
                <div
                    onClick={() => handleCategoryItemOnClick("All")}
                    className='div--style div--hover--style rounded-md w-[5rem] h-[3rem] flex--center'>All</div>
                <Categories
                    categories={categories}
                    handleCategoryItemOnClick={handleCategoryItemOnClick}
                />
            </div>

            <div className="w-[80%] my-8">
                <div className='flex select-none gap-4 my-2 mx-auto flex--center'>
                    {
                        [
                            { name: "Sort By Name (Desc)", query: "sort_by=desc(name)" },
                            { name: "Sort By Name (Asc)", query: "sort_by=asc(name)" },
                            { name: "Sort By Price (Desc)", query: "sort_by=desc(price)" },
                            { name: "Sort By Price (Asc)", query: "sort_by=asc(price)" }
                        ].map(item => {
                            return <div
                                key={item.name}
                                onClick={() => handleSortProducts(item.name, item.query)}
                                className={`div--style div--hover--style text-[0.75rem] rounded-md h-[3rem] flex--center ${sortOption === item.name ? 'bg-gray-700 text-white' : 'bg-white'}`}>
                                {item.name}
                            </div>
                        })
                    }
                </div>

                <Pagination
                    totalItems={totalItems}
                    itemsPerPage={PRODUCTS_PER_PAGE}
                    currentPage={page}
                    handlePaginate={handlePaginate}
                />

                <p className="font-bold text-[0.75rem]">{totalItems} results [category={productCategory}]</p>

                <div className="div--style flex flex-wrap flex--center gap-6">
                    <Products
                        products={products}
                        handleProductItemOnClick={handleProductItemOnClick}
                    />
                </div>
            </div>

            {(showComparision || comparedItems.length > 0) &&
                <ComparisionBar
                    handleClose={handleCloseComparisionBar}
                />
            }

        </section>
    )
}

export default Shop
