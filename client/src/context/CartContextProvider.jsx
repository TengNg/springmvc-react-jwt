import { createContext } from "react";
import useLocalStorage from "../hooks/useLocalStorage";
import LOCAL_STORAGE_KEY from "../data/localStorageKey";

const CartContext = createContext({});

export const CartContextProvider = ({ children }) => {
    const [cart, setCart] = useLocalStorage(LOCAL_STORAGE_KEY, []);

    const addToCart = ({ id, image, name, price }) => {
        const newCart = [...cart, { itemId: new Date().getTime().toString(), id, name, image, price, quantity: 1 }];
        setCart(newCart);
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

    return (
        <CartContext.Provider value={{
            cart,
            setCart,
            addToCart,
            increaseItemCount,
            decreaseItemCount,
            updateItemCount,
            removeItem
        }}
        >
            {children}
        </CartContext.Provider>
    )
}

export default CartContext;
