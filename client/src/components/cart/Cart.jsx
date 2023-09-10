import useCart from '../../hooks/useCart.js';
import CartItem from './CartItem.jsx';
import { formatCurrencyVND } from '../../utils/currencyFormatter.js';
import { useNavigate } from 'react-router-dom';

const Cart = () => {
    const { cart, setCart } = useCart();
    const navigate = useNavigate();

    const handleProceedCart = () => {
        navigate("/checkout");
    };

    if (!cart.length) {
        return <div className='w-[100%] flex flex--center select-none mx-auto  mt-[4rem]'>
            <button
                className="bg-gray-700 text-white px-5 py-3 font-normal"
                onClick={() => navigate("/shop")}
            >Return to shop</button>
        </div>
    }

    return (
        <>
            <div className='w-[100%] flex select-none mx-auto ms-[8rem] mt-[4rem]'>
                <h1 className="text-[2rem] text-gray-700 relative font-bold underline--style--2 underline--hover--2 transition all hover:text-gray-500"
                >Shopping Cart</h1>
            </div>

            <div className='mx-auto div--style flex flex-row justify-between mt-7 w-[1300px] min-h-[200px] p-7 bg-gray-100'>

                <div className='w-[40%] flex flex-col justify-between gap-7'>
                    <div className="w-[100%] flex flex-col gap-4">
                        {cart.map(item => {
                            return <CartItem
                                key={item.itemId}
                                {...item}
                            />
                        })}
                    </div>

                    <hr className="border-black" />

                    <div className="w-[100%] h-auto flex--center gap-2">
                        <button
                            className="w-[100%] border-black border-[2px] p-2 font-normal"
                            onClick={() => navigate("/shop")}
                        >Continue Shopping</button>
                        <button
                            onClick={() => setCart([])}
                            className={`w-[100%] border-black border-[2px] p-2 font-normal`}
                        >Clear cart</button>
                    </div>

                </div>

                <div className='div--style w-[20%] h-[100%] p-5'>
                    <p>Total: ${formatCurrencyVND(cart.reduce((total, item) => total + +item.price * +item.quantity, 0))}</p>
                    <div className="flex--center flex-col gap-3 h-[80%] mt-8 mx-auto">
                        <div className='h-[4rem]'>
                            <button
                                onClick={() => handleProceedCart()}
                                className='text-sm button--style button--hover p-4'
                            >Proceed to checkout</button>
                        </div>
                    </div>
                </div>

            </div>
        </>
    )
}

export default Cart
