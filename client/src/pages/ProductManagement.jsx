import { useState, useEffect, useRef } from 'react';
import useAuth from "../hooks/useAuth";
import { useNavigate } from "react-router-dom";
import axios, { authAxios, axiosPrivate } from '../api/axios';
import { formatCurrencyVND } from '../utils/currencyFormatter';
import Products from '../components/product/Products';
import Loading from '../components/common/Loading';

const ProductManagement = () => {
    const [name, setName] = useState("");
    const [description, setDescription] = useState("");
    const [price, setPrice] = useState("");
    const [categoryOption, setCategoryOption] = useState("");
    const [showProducts, setShowProduct] = useState(false);
    const [totalRevenue, setTotalRevenue] = useState(0);

    const [image, setImage] = useState();
    const [previewImage, setPreviewImage] = useState();
    const imageInputRef = useRef();

    const [loading, setLoading] = useState(false);
    const [msg, setMsg] = useState("");
    const [error, setError] = useState(false);

    const [products, setProducts] = useState([]);
    const [categories, setCategories] = useState([]);
    const { auth } = useAuth();
    const navigate = useNavigate();

    useEffect(() => {
        const isLoggedIn = async () => {
            await axiosPrivate.get('/api/check-cookies/');
        }
        isLoggedIn().catch(_ => {
            navigate('/notfound');
        });
    }, []);

    useEffect(() => {
        if (Object.keys(auth).length > 0) {
            if (auth?.userRole !== "ROLE_SELLER") {
                navigate("/notfound");
            }

            const getProducts = async () => {
                const response = await authAxios.get(`/api/for-sellers/manage-products/${auth?.userId}`);
                const response2 = await axios.get("/api/categories/");
                const response3 = await authAxios.get(`/api/for-sellers/statistics/${auth?.userId}/`);
                setTotalRevenue(response3.data.totalRevenue);
                setProducts(response.data.products);
                setCategories(response2.data.categories);
            }

            getProducts().catch(err => {
                console.log(err);
                // if (err?.response?.status === 401) {
                //     navigate("/");
                // }
            });
        }
    }, [auth]);

    const handleImageChange = (event) => {
        const file = event.target.files[0];
        setImage(file);

        if (file) {
            const reader = new FileReader();
            reader.onload = () => {
                setPreviewImage(reader.result);
            };
            reader.readAsDataURL(file);
        }
    };

    const handleAddNewProduct = async (e) => {
        e.preventDefault();

        if (!categoryOption || categoryOption.length === 0) {
            setError(true);
            setMsg("Please choose category for this product");
            return;
        }

        const formData = new FormData();
        formData.append('image', image);
        formData.append('sellerName', auth.username);
        formData.append('name', name);
        formData.append('description', description);
        formData.append('price', price);
        formData.append('categoryId', categoryOption);

        try {
            setLoading(true);
            await authAxios.post("/api/products/add/", formData);
            setError(false);
            setMsg("Product is successfully added");
            setLoading(false);
        } catch (err) {
            setMsg("Can't add new product");
            setError(true);
        }
    };

    return (
        <>
            <section className="relative w-[100%] flex flex-col items-center gap-4">
                <Loading loading={loading} />

                <div className="w-[80%] mt-8">
                    <div className='flex select-none m-[0_0_2rem_0]'>
                        <h1 className="text-[2rem] text-gray-700 relative text-center font-bold underline--style--2 underline--hover--2 transition all hover:text-gray-500"
                        >Products Management</h1>
                    </div>
                </div>

                <p>Total Revenue: {formatCurrencyVND(+totalRevenue)}</p>

                <div className='mx-auto div--style flex flex-row p-7 w-[80%] bg-gray-100'>
                    <div
                        className="div--style shadow-none flex-grow me-6 bg-cover bg-center aspect-square"
                        style={{ backgroundImage: `url(${previewImage})` }}
                        onClick={() => imageInputRef.current.click()}
                    >
                        Product image
                        <input className="hidden" ref={imageInputRef} type="file" id="profileImage" accept="image/*" onChange={handleImageChange} />
                    </div>

                    <form
                        onSubmit={handleAddNewProduct}
                        className='w-[65%] flex flex-col border-black border-[2px] px-6 py-3 h-fit'>

                        <p className={`font-normal ${error ? 'text-red-600' : 'text-green-500'}`}>{msg}</p>
                        <label htmlFor="name" className='label--style mt-2'>Name:</label>
                        <input
                            className='border-[2px] border-black p-1 font-bold'
                            type="text"
                            id="name"
                            autoComplete="off"
                            value={name}
                            onChange={(e) => setName(e.target.value)}
                            required
                        />

                        <label htmlFor="description" className='label--style mt-2'>Description:</label>
                        <input
                            className='border-[2px] border-black p-1 font-bold'
                            type="text"
                            id="description"
                            autoComplete="off"
                            value={description}
                            onChange={(e) => setDescription(e.target.value)}
                        />

                        <label htmlFor="price" className='label--style mt-2'>Price:</label>
                        <input
                            className='border-[2px] border-black p-1 font-bold'
                            type="text"
                            id="price"
                            autoComplete="off"
                            value={price}
                            onChange={(e) => setPrice(e.target.value)}
                            required
                        />

                        <select className='mt-8'
                            value={categoryOption}
                            onChange={(e) => setCategoryOption(e.target.value)}>
                            <option value="">Select an category</option>
                            {
                                categories.map((category, index) => {
                                    const { categoryId, categoryName } = category;
                                    return <option key={index} value={categoryId}>
                                        {categoryName}
                                    </option>
                                })
                            }
                        </select>

                        <p className="mt-6 font-normal text-sm">Note: Please fill in all the required fields</p>

                        <button
                            type='submit'
                            className='text-white p-3 bg-gray-700 hover:bg-gray-600 transition-all w-[100%]'
                        >Add new product</button>

                    </form>

                </div>

                <button
                    onClick={() => setShowProduct(prev => !prev)}
                    className='bg-gray-700 text-white p-4'>{showProducts === false ? 'Show' : 'Hide'} selling products</button>

                <div className="w-[80%]">
                    {showProducts === true &&
                        <div className="div--style flex flex-wrap flex--center gap-6 p-8">
                            <Products
                                products={products}
                            />
                        </div>
                    }
                </div>


            </section>
        </>
    )
}

export default ProductManagement
