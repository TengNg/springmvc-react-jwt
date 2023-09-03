import useCart from '../../hooks/useCart.js';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCirclePlus, faCircleMinus, faX } from '@fortawesome/free-solid-svg-icons';
import { formatCurrencyVND } from '../../utils/currencyFormatter.js';

const CartItem = ({ id, image, name, price, quantity, isCheckout }) => {
    const { increaseItemCount, decreaseItemCount, removeItem } = useCart();

    return (
        <>
            <div className='flex flex-row justify-between gap-5 div--style w-[100%] h-auto relative'>

                <div className='flex gap-2'>
                    <div className='flex--center w-[5rem] h-[5rem] border-black border-[3px] bg-center bg-cover overflow-hidden cursor-pointer'>
                        <img className='w-[100%] h-auto' src={image} />
                    </div>

                    <div className="flex flex-col">
                        <p className="text-[1rem]">{name}</p>
                        <p className="text-xs font-normal">Price: {formatCurrencyVND(+price)}</p>
                    </div>
                </div>

                {
                    !isCheckout && (
                        <>
                            <div className="flex flex-col justify-end">
                                <div className='flex--center flex-row'>
                                    <button className="flex--center w-[1rem] h-[1rem]" onClick={() => decreaseItemCount(id)}>
                                        <FontAwesomeIcon className="w-[100%] h-[100%]" icon={faCircleMinus} />
                                    </button>

                                    <input
                                        type="number"
                                        value={quantity}
                                        className="w-[3rem] text-center" />

                                    <button className="flex--center w-[1rem] h-[1rem]" onClick={() => increaseItemCount(id)}>
                                        <FontAwesomeIcon className="w-[100%] h-[100%]" icon={faCirclePlus} />
                                    </button>
                                </div>
                            </div>

                            <button
                                className="absolute top-2 right-3"
                                onClick={() => removeItem(id)}>
                                <FontAwesomeIcon icon={faX} />
                            </button>
                        </>
                    )
                }


            </div>
        </>
    );
}

export default CartItem
