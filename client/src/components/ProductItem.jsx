const ProductItem = ({ product, handleOnClick }) => {
    const { id, name, price } = product;
    return (
        <div
            className='div--style w-[15rem] h-[5rem]'
            onClick={() => {
                handleOnClick(id);
            }}
        >
            <p>{name}</p>
            <p>Price: {price}</p>
        </div>
    )
}

export default ProductItem
