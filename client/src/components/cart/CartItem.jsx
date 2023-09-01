import useCart from '../../hooks/useCart.js';

const CartItem = ({ id, name, price, quantity }) => {
    const { increaseItemCount, decreaseItemCount, removeItem } = useCart();
    return (
        <div>
            <p>{name} - ${price} (Quantity: {quantity})</p>
            <div className="w-[6rem] h-[3rem]">
                <button
                    className='button--style button--hover'
                    onClick={() => decreaseItemCount(id)}>Decrease</button>
            </div>
            <div className="w-[6rem] h-[3rem]">
                <button
                    className='button--style button--hover'
                    onClick={() => increaseItemCount(id)}>Increase</button>
            </div>
            <div className="w-[6rem] h-[3rem]">
                <button
                    className='button--style button--hover'
                    onClick={() => removeItem(id)}>Remove</button>
            </div>
        </div>
    );
}

export default CartItem
