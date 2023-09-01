import { Link, NavLink } from "react-router-dom"
import UserAccount from "./UserAccount"
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHouse, faShop, faCartShopping } from '@fortawesome/free-solid-svg-icons';

const NavBar = () => {
    return (
        <>
            <nav className="div--style w-[200px] h-[3rem] mx-auto">
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
                    <li>
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
