import { useEffect, useState } from "react";
import { useParams } from "react-router-dom"
import axios from "../../api/axios"
import { formatCurrencyVND } from "../../utils/currencyFormatter";

const TransactionDetails = () => {
    const { cartId } = useParams();
    const [items, setItems] = useState([]);
    const [totalPrice, setTotalPrice] = useState();


    useEffect(() => {
        const getDetails = async () => {
            const response = await axios.get(`/api/purchase-history/transactions/${cartId}`);
            setItems(response.data.items);
            setTotalPrice(response.data.transaction.amount);
        }
        getDetails();
    }, []);

    return (
        <div className='flex--center flex-col gap-5 w-full h-auto relative mx-auto mt-12'>

            <div className='flex select-none my-8'>
                <h1 className="text-[2rem] text-gray-700 relative text-center font-bold underline--style--2 underline--hover--2 transition all hover:text-gray-500"
                >Transaction Details</h1>
            </div>

            <div className="flex flex-col w-full gap-6">
                {items.map((item, index) => {
                    return <div key={index} className='flex gap-2 w-[60%] mx-auto div--style'>
                        <div className='flex--center w-[5rem] h-[5rem] border-black border-[3px] bg-center bg-cover overflow-hidden cursor-pointer'>
                            <img className='w-[100%] h-auto' src={item.productId.imageUrl} />
                        </div>

                        <div className="flex flex-col">
                            <p className="text-[1rem] font-bold">{item.productId.name}</p>
                            <p className="font-normal">Qty: {item.quantity}</p>
                            <p className="font-normal">Price: {formatCurrencyVND(+item.productId.price)}</p>
                        </div>
                    </div>
                })}
            </div>

            <p className="text-xl text-gray-600 font-bold mt-8">Total: {formatCurrencyVND(totalPrice)}</p>
        </div>
    )
}

export default TransactionDetails
