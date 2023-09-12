import { useNavigate } from "react-router-dom";
import useComparision from "../hooks/useComparision"
import { formatCurrencyVND } from "../utils/currencyFormatter";
import useAuth from "../hooks/useAuth";

const ProductComparision = () => {
    const { auth } = useAuth();
    const { comparedItems } = useComparision();
    const navigate = useNavigate();

    return (
        <div className="container mx-auto flex flex-col gap-12">
            <div className='w-[100%] flex select-none mx-auto mt-[4rem] ps-[8rem]'>
                <h1 className="text-[2rem] text-gray-700 relative font-bold underline--style--2 underline--hover--2 transition all hover:text-gray-500"
                >Comparision</h1>
            </div>
            <table className="min-w-full bg-white border-[2px] border-gray-700 div--style">
                <thead>
                    <tr>
                        <th className="py-2 px-4 border border-gray-600">Product name</th>
                        {comparedItems.map((product) => (
                            <th
                                key={product.productId}
                                className="py-2 px-4 border border-gray-600"
                            >
                                {product.name}
                            </th>
                        ))}
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th className="py-2 px-4 border border-gray-600">Image</th>
                        {comparedItems.map((product) => (
                            <td
                                key={product.productId}
                                className="py-2 px-4 border border-gray-600"
                            >
                                <div className='flex--center w-[8rem] h-[8rem] bg-center bg-cover overflow-hidden cursor-pointer'>
                                    <img
                                        className='w-[100%] h-auto' src={product.imageUrl}
                                        onClick={() => {
                                            if (auth?.userId === product.userId.userId) {
                                                navigate(`/for-sellers`);
                                                return;
                                            }
                                            navigate(`/shop/products/${product.productId}`)
                                        }}
                                    />
                                </div>
                            </td>
                        ))}
                    </tr>

                    <tr>
                        <th className="py-2 px-4 border border-gray-600">Price</th>
                        {comparedItems.map((product) => (
                            <td
                                key={product.productId}
                                className="py-2 px-4 border border-gray-600"
                            >
                                {formatCurrencyVND(product.price)}
                            </td>
                        ))}
                    </tr>
                    <tr>
                        <th className="py-2 px-4 border border-gray-600">From seller</th>
                        {comparedItems.map((product) => (
                            <td
                                key={product.productId}
                                className="py-2 px-4 border border-gray-600"
                            >
                                <div className='flex--center w-[3rem] h-[3rem] bg-center bg-cover overflow-hidden cursor-pointer'>
                                    <img
                                        className='w-[100%] h-auto' src={product.userId.imageUrl}
                                    />
                                </div>
                                <p className="font-bold">{product.userId.username}</p>
                            </td>
                        ))}
                    </tr>
                </tbody>
            </table>
        </div>
    )
}

export default ProductComparision
