import { formatCurrencyVND } from "../utils/currencyFormatter"
import useCart from '../hooks/useCart.js';
import CartItem from "../components/cart/CartItem";

const Checkout = () => {
    const { cart } = useCart();

    return (
        <>
            <div className='w-[100%] flex select-none mx-auto ms-[8rem] mt-[4rem]'>
                <h1 className="text-[2rem] text-gray-700 relative font-bold underline--style--2 underline--hover--2 transition all hover:text-gray-500"
                >Checkout</h1>
            </div>


            <div className='mx-auto div--style flex flex-row justify-between mt-7 w-[1300px] min-h-[200px] p-7 bg-gray-100'>

                <div className='w-[40%] flex flex-col justify-between gap-7'>
                    <div className="w-[100%] flex flex-col gap-4">
                    </div>

                    <hr className="border-black" />

                </div>

                <div className='flex flex-col gap-4 div--style w-[30%] h-[100%] px-4'>
                    <p>Your order:</p>

                    <div className="w-[100%] flex flex-col gap-4">
                        {cart.map(item => {
                            return <CartItem
                                key={item.id}
                                {...item}
                                isCheckout={true}
                            />
                        })}
                    </div>

                    <div className="flex--center flex-col gap-3 w-[100%] h-[80%] mt-8 mx-auto">
                        <button
                            onClick={() => handleProceedCart()}
                            className='text-sm text-white p-3 bg-gray-700 hover:bg-gray-600 transition-all w-[100%]'
                        >Place order</button>
                    </div>

                </div>

            </div>


        </>
    )
}

export default Checkout
