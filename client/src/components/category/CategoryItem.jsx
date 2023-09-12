const CategoryItem = ({ category, handleOnClick }) => {
    const { categoryName } = category;

    return (
        <div
            className='div--style div--hover--style rounded-md w-[5rem] h-[3rem] flex--center cursor-pointer'
            onClick={() => {
                handleOnClick(categoryName);
            }}
        >
            <p>{categoryName}</p>
        </div>
    )
}

export default CategoryItem
