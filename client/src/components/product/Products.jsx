import ProductItem from "./ProductItem"

const Products = ({ products, handleProductItemOnClick }) => {
    return (
        <>
            {products.map(product => {
                return <ProductItem
                    handleOnClick={handleProductItemOnClick}
                    key={product.productId}
                    product={product}
                />
            })}
        </>
    )
}

export default Products
