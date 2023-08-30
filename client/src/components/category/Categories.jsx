import CategoryItem from "./CategoryItem"

const Categories = ({ categories, handleCategoryItemOnClick }) => {
    return <>
        {categories && categories.map(category => {
            return <CategoryItem
                handleOnClick={handleCategoryItemOnClick}
                key={category.id} category={category}
            />
        })}
    </>

}

export default Categories
