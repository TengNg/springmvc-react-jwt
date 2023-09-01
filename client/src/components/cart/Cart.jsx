import useCart from '../../hooks/useCart.js';
import CartItem from './CartItem.jsx';
import { formatCurrencyVND } from '../../utils/currencyFormatter.js';

const Cart = () => {
    const { cart } = useCart();

    const handleProceedCart = () => {

    };

    return (
        <>
            <div className='w-[100%] flex select-none mx-auto ms-[8rem]'>
                <h1 className="text-[2.5rem] text-gray-700 relative font-bold underline--style--2 underline--hover--2 transition all hover:text-gray-500"
                >Cart</h1>
            </div>

            <div className='mx-auto div--style flex flex-row justify-between mt-7 w-[90%]'>

                <div>
                    {cart.map(item => {
                        return <CartItem
                            key={item.id}
                            {...item}
                        />
                    })}
                </div>

                <div className='div--style w-auto h-[100%] p-4'>
                    <p>Total: ${formatCurrencyVND(cart.reduce((total, item) => total + +item.price * +item.quantity, 0))}</p>
                    <div className="flex--center flex-col gap-3 w-[8rem] h-[80%] mt-8 mx-auto">
                        <div className='w-[8rem] h-[4rem]'>
                            <button onClick={() => handleProceedCart()} className='button--style button--hover p-4'>Purchase</button>
                        </div>
                    </div>
                </div>

            </div>
        </>
    )
}

export default Cart
