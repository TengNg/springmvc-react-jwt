import { useState, useEffect, useRef } from 'react';
import useAuth from "../hooks/useAuth";
import { useNavigate, useParams } from "react-router-dom";
import axios, { authAxios, axiosPrivate } from '../api/axios';
import Loading from '../components/common/Loading';

const ProductEditor = () => {
    const { auth } = useAuth();
    const { productId } = useParams();

    const [name, setName] = useState("");
    const [description, setDescription] = useState("");
    const [price, setPrice] = useState("");
    const [categoryOption, setCategoryOption] = useState("");
    const [categories, setCategories] = useState([]);
    const [loading, setLoading] = useState(false);

    const [error, setError] = useState(false);
    const [msg, setMsg] = useState("");
    const [imageUrl, setImageUrl] = useState();

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

            const getProductInfo = async () => {
                const response = await authAxios.get(`/api/products/${productId}/`);
                const categoriesRes = await axios.get(`/api/categories/`);
                setCategories(categoriesRes.data.categories);
                const { name, description, price, categoryId, imageUrl } = response.data.product;
                setName(name);
                setDescription(description);
                setPrice(price);
                setCategoryOption(categoryId.categoryId);
                setImageUrl(imageUrl);
            }

            getProductInfo().catch(err => {
                console.log(err);
                // if (err?.response?.status === 401) {
                //     navigate("/");
                // }
            });
        }
    }, [auth]);

    const handleUpdateProduct = async (e) => {
        e.preventDefault();

        if (!categoryOption || categoryOption.length === 0) {
            setError(true);
            setMsg("Please choose category for this product");
            return;
        }

        const formData = new FormData();
        formData.append('productId', productId);
        formData.append('name', name);
        formData.append('description', description);
        formData.append('price', price);
        formData.append('categoryId', categoryOption);

        try {
            setLoading(true);
            await authAxios.post("/api/products/update/", formData);
            setError(false);
            setMsg("Product is successfully updated");
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
                        >Edit product</h1>
                    </div>
                </div>
                <div className='mx-auto div--style flex flex-row mt-7 p-7 w-[80%] bg-gray-100 gap-3'>
                    <div className='flex--center w-[20rem] h-[20rem] bg-center bg-cover overflow-hidden cursor-pointer'>
                        <img className='w-[100%] h-auto' src={imageUrl} />
                    </div>

                    <form
                        onSubmit={handleUpdateProduct}
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
                        >Update</button>

                    </form>

                </div>
            </section>
        </>
    )
}

export default ProductEditor
