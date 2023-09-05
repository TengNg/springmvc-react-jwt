const CategoryItem = ({ category, handleOnClick }) => {
    const { categoryId, categoryName } = category;

    return (
        <div
            className='div--style div--hover--style rounded-md w-[5rem] h-[3rem] flex--center'
            onClick={() => {
                handleOnClick(categoryId);
            }}
        >
            <p>{categoryName}</p>
        </div>
    )
}

export default CategoryItem
