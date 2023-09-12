import { createContext } from "react";
import useLocalStorage from "../hooks/useLocalStorage";
import LOCAL_STORAGE_KEY from "../data/localStorageKey";

const CartContext = createContext({});

export const CartContextProvider = ({ children }) => {
    const [cart, setCart] = useLocalStorage(LOCAL_STORAGE_KEY, []);

    const addToCart = ({ id, image, name, price }) => {
        setCart(prev => {
            if (prev.find(item => item.id == id)) {
                return prev.map(item => item.id == id ? { ...item, quantity: item.quantity + 1 } : item);
            } else {
                return [...prev, { id, name, image, price, quantity: 1 }];
            }
        });

        // const newCart = [...cart, { id, name, image, price, quantity: 1 }];
        // setCart(newCart);
    };

    const decreaseItemCount = (id) => {
        setCart(prev => {
            if (prev.find(item => item.itemId == id)?.quantity == 1) {
                return prev.filter(item => item.itemId != id);
            } else {
                return prev.map(item => {
                    return item.itemId == id ? { ...item, quantity: item.quantity - 1 } : item;
                });
            }
        });
    };

    const updateItemCount = (id, value) => {
        setCart(prev => {
            return prev.map(item => item.itemId == id ? {...item, quantity: value} : item);
        });
    };

    const increaseItemCount = (id) => {
        setCart(prev => {
            return prev.map(item => {
                return item.itemId == id ? { ...item, quantity: item.quantity + 1 } : item;
            });
        });
    };

    const removeItem = (id) => {
        const newCart = cart.filter(item => item.itemId !== id);
        setCart(newCart);
    };

    const getTotalItems = () => {
        return  cart.reduce((total, item) => total + +item.quantity, 0);
    };

    return (
        <CartContext.Provider value={{
            cart,
            setCart,
            addToCart,
            increaseItemCount,
            decreaseItemCount,
            updateItemCount,
            removeItem,
            getTotalItems
        }}
        >
            {children}
        </CartContext.Provider>
    )
}

export default CartContext;
