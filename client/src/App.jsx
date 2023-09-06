import './App.css'
import { useEffect } from 'react'
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
import Checkout from './pages/Checkout'
import ForSellersRegister from './pages/ForSellersRegister'
import ProductManagement from './pages/ProductManagement'
import Auth from './components/auth/Auth'

const noNavPaths = ["/login", "/register", "/notfound", "/seller", "/seller/register"];

function App() {
    const { pathname } = useLocation();

    return (
        <>
            {!noNavPaths.includes(pathname) && <NavBar />}
            <Routes>
                {["/", "/home", "/index"].map((path, index) => {
                    return <Route key={index} path={path} element={<Home />} />
                })}

                <Route path={"/login"} element={<Login />} />
                <Route path={"/register"} element={<Register />} />

                <Route path={"/shop/products/:productId"} element={<ProductInfo />} />

                {["/shop", "/shop/products"].map((path, index) => {
                    return <Route key={index} path={path} element={<Shop />} />
                })}

                <Route path={"/cart"} element={<UserShoppingCart />} />
                <Route path={"/checkout"} element={<Checkout />} />

                <Route path={"/for-sellers"} element={<ProductManagement />} />
                <Route path={"/for-sellers/register"} element={<ForSellersRegister />} />

                <Route path={"/notfound"} element={<NotFound />} />
                <Route path={"*"} element={<NotFound />} />
            </Routes>
        </>
    )
}

export default App
