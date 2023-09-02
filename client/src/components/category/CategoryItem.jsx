const CategoryItem = ({ category, handleOnClick }) => {
    const { categoryId, categoryName } = category;

    return (
        <div
            className='div--style div--hover--style rounded-md'
            onClick={() => {
                handleOnClick(categoryId);
            }}
        >
            <p>{categoryName}</p>
        </div>
    )
}

export default CategoryItem
