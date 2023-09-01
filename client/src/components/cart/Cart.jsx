import useCart from '../../hooks/useCart.js';
import CartItem from './CartItem.jsx';
import { formatCurrencyVND } from '../../utils/currencyFormatter.js';


const Cart = () => {
    const { cart } = useCart();

    return (
        <>
            <div className='flex--center flex-col gap-2 mt-7 w-[100%] px-[12rem]'>
                <div className='w-[100%] flex select-none'>
                    <h1 className="text-[2.5rem] text-gray-700 relative font-bold underline--style--2 underline--hover--2 transition all hover:text-gray-500"
                    >Cart</h1>
                </div>

                <div className="div--style">
                    {cart.map(item => {
                        return <CartItem
                            key={item.id}
                            {...item}
                        />
                    })}
                    <p>Total: ${formatCurrencyVND(cart.reduce((total, item) => total + +item.price * +item.quantity, 0))}</p>
                </div>


            </div>
        </>
    )
}

export default Cart
