import { createContext, useState } from "react";
import useLocalStorage from "../hooks/useLocalStorage";

const CartContext = createContext({});

const LOCAL_STORAGE_KEY = "localStorage.USER_SHOPPING_CART";

export const CartContextProvider = ({ children }) => {
    const [cart, setCart] = useLocalStorage(LOCAL_STORAGE_KEY, []);

    const addToCart = ({ id, image, name, price }) => {
        const newCart = [...cart, { id, name, image, price, quantity: 1 }];
        setCart(newCart);
    };

    const decreaseItemCount = (id) => {
        setCart(prev => {
            if (prev.find(item => item.id == id)?.quantity == 1) {
                return prev.filter(item => item.id != id);
            } else {
                return prev.map(item => {
                    return item.id == id ? { ...item, quantity: item.quantity - 1 } : item;
                });
            }
        });
    };

    const increaseItemCount = (id) => {
        setCart(prev => {
            return prev.map(item => {
                return item.id == id ? { ...item, quantity: item.quantity + 1 } : item;
            });
        });
    };

    const removeItem = (id) => {
        const newCart = cart.filter(item => item.id !== id);
        setCart(newCart);
    };

    return (
        <CartContext.Provider value={{ cart, setCart, addToCart, increaseItemCount, decreaseItemCount, removeItem }}>
            {children}
        </CartContext.Provider>
    )
}

export default CartContext;
