import useCart from '../hooks/useCart.js';
import { NavLink } from "react-router-dom"
import UserAccount from "./UserAccount"
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHouse, faShop, faCartShopping } from '@fortawesome/free-solid-svg-icons';

const NavBar = () => {
    const { cart } = useCart();

    return (
        <>
            <nav className="div--style abolute w-[200px] h-[3rem] mt-[1rem] mx-auto">
                <ul className="w-[100%] h-[100%] flex justify-around items-center">
                    <li>
                        <NavLink to="/" className={({ isActive }) => isActive ? 'anchor--style--selected' : 'anchor--style'} >
                            <FontAwesomeIcon icon={faHouse} />
                        </NavLink>
                    </li>
                    <li>
                        <NavLink to="/shop" className={({ isActive }) => isActive ? 'anchor--style--selected' : 'anchor--style'} >
                            <FontAwesomeIcon icon={faShop} />
                        </NavLink>
                    </li>
                    <li className="relative">
                        {cart.length > 0 && (
                            <span class="flex--center w-[1rem] h-[1rem] bg-red-500 text-white text-[0.55rem] rounded-full absolute top-0 right-0 transform translate-x-1/2 -translate-y-1/4">
                                {cart.length}
                            </span>
                        )}
                        <NavLink to="/cart" className={({ isActive }) => isActive ? 'anchor--style--selected' : 'anchor--style'} >
                            <FontAwesomeIcon icon={faCartShopping} />
                        </NavLink>
                    </li>
                </ul>
            </nav>
            <UserAccount />
        </>
    )
}

export default NavBar
