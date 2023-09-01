import './App.css'
import { Routes, Route } from 'react-router-dom'
import Home from './pages/Home'
import Login from './pages/Login'
import Register from './pages/Register'
import Shop from './pages/Shop'
import ProductInfo from './components/product/ProductInfo'
import NavBar from './components/Navbar'
import NotFound from './pages/NotFound'
import { useLocation } from 'react-router-dom'
import UserShoppingCart from './pages/UserShoppingCart'

const noNavPaths = ["/login", "/register", "/notfound"];

function App() {
    const { pathname } = useLocation();

    return (
        <>
            {!noNavPaths.includes(pathname) && <NavBar />}
            <Routes>
                {["/", "/home", "/index"].map((path, index) => {
                    return (
                        <Route key={index} path={path} element={<Home />} />
                    )
                })}
                <Route path={"/login"} element={<Login />} />
                <Route path={"/register"} element={<Register />} />
                <Route path={"/shop"} element={<Shop />} />
                <Route path={"/shop/products/:productId"} element={<ProductInfo />} />
                <Route path={"/cart"} element={<UserShoppingCart />} />
                <Route path={"/notfound"} element={<NotFound />} />
                {/* <Route path={"*"} element={<Navigate to="/notfound" />} /> */}
            </Routes>
        </>
    )
}

export default App
