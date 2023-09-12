import { formatCurrencyVND } from '../../utils/currencyFormatter.js';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCirclePlus } from '@fortawesome/free-solid-svg-icons';
import useComparision from '../../hooks/useComparision.js';
import useAuth from '../../hooks/useAuth.js';
import { useNavigate } from 'react-router-dom';

const ProductItem = ({ product, handleOnClick }) => {
    const { auth } = useAuth();
    const { userId, productId: id, name, price, imageUrl } = product;
    const { addItem } = useComparision();
    const navigate = useNavigate();

    return (
        <div
            className='flex flex-row gap-2 div--style div--hover--style w-[15rem] h-auto relative'
        >
            <div className='flex--center w-[5rem] h-[5rem] border-black border-[3px] bg-center bg-cover overflow-hidden cursor-pointer'>
                <img
                    onClick={() => {
                        if (auth?.userRole != "ROLE_SELLER") {
                            handleOnClick(id, userId.userId)
                        } else {
                            navigate(`/for-sellers/products/${id}`);
                        }
                    }}
                    className='w-[100%] h-auto' src={imageUrl}
                />
            </div>

            <div className="flex flex-col">
                <p className="text-[1rem]">{name}</p>
                <p className="text-xs font-normal">Price: {formatCurrencyVND(+price)}</p>
            </div>

            {
                auth?.userRole != "ROLE_SELLER"
                && <button
                    className='absolute right-2 bottom-1 text-gray-600'
                    onClick={() => addItem(product)}
                >
                    <FontAwesomeIcon icon={faCirclePlus} />
                </button>
            }

        </div>
    )
}

export default ProductItem
