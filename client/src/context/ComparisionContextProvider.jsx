import { createContext } from "react";
import useLocalStorage from "../hooks/useLocalStorage";
import { LOCAL_STORAGE_COMPARISION_KEY } from "../data/localStorageKey";

const ComparsionContext = createContext({});

export const ComparisionContextProvider = ({ children }) => {
    const [comparedItems, setComparedItems] = useLocalStorage(LOCAL_STORAGE_COMPARISION_KEY, []);

    const addItem = (product) => {
        setComparedItems((prev) => {
            if (prev.length === 2
                || prev.find(item => item.productId === product.productId
                    || item.categoryId.categoryName != product.categoryId.categoryName)
            ) {
                return prev;
            }

            return [...prev, product]
        });
    };

    const removeItem = (id) => {
        const newItems = comparedItems.filter(item => item.productId != id);
        setComparedItems(newItems);
    };

    return (
        <ComparsionContext.Provider value={{
            comparedItems,
            setComparedItems,
            addItem,
            removeItem
        }}>
            {children}
        </ComparsionContext.Provider>
    )
}

export default ComparsionContext;
