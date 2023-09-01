import useCart from '../../hooks/useCart.js';
import CartItem from './CartItem.jsx';
import { formatCurrencyVND } from '../../utils/currencyFormatter.js';

const Cart = () => {
    const { cart } = useCart();

    return (
        <div>
            <h1>Cart</h1>
            <div className="div--style">
                {cart.map(item => (
                    <CartItem key={item.id} {...item} />
                ))}
                <p>Total: ${formatCurrencyVND(cart.reduce((total, item) => total + +item.price * +item.quantity, 0))}</p>
            </div>
        </div>
    )
}

export default Cart
