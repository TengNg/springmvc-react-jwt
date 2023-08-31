import { formatCurrencyVND } from '../../utils/currencyFormatter.js';

const ProductItem = ({ product, handleOnClick }) => {
    const { id, name, price, image } = product;
    return (
        <div
            className='flex flex-row gap-2 div--style div--hover--style w-[15rem] h-auto'
            onClick={() => {
                handleOnClick(id);
            }}
        >
            <div className='flex--center w-[5rem] h-[5rem] border-black border-[3px] bg-center bg-cover overflow-hidden cursor-pointer'>
                <img className='w-[100%] h-auto' src={image} />
            </div>

            <div className="flex flex-col">
                <p className="text-[1rem]">{name}</p>
                <p className="text-xs font-normal">Price: {formatCurrencyVND(+price)}</p>
            </div>
        </div>
    )
}

export default ProductItem
