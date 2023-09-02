import CategoryItem from "./CategoryItem"

const Categories = ({ categories, handleCategoryItemOnClick }) => {
    return <>
        {categories && categories.map(category => {
            return <CategoryItem
                key={category.categoryId}
                handleOnClick={handleCategoryItemOnClick}
                category={category}
            />
        })}
    </>

}

export default Categories
