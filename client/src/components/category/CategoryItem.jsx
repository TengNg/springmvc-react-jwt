const CategoryItem = ({ category, handleOnClick }) => {
    const { id, name } = category;

    return (
        <div
            className='div--style div--hover--style rounded-md'
            onClick={() => {
                handleOnClick(id);
            }}
        >
            <p>{name}</p>
        </div>
    )
}

export default CategoryItem
