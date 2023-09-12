import './App.css'
import { Routes, Route } from 'react-router-dom'
import Home from './pages/Home'
import Login from './pages/Login'
import Register from './pages/Register'
import Shop from './pages/Shop'
import ProductInfo from './components/product/ProductInfo'
import TransactionDetails from './components/transaction/TransactionDetails'
import NavBar from './components/Navbar'
import NotFound from './pages/NotFound'
import UserProfile from './pages/UserProfile'
import { useLocation } from 'react-router-dom'
import UserShoppingCart from './pages/UserShoppingCart'
import Checkout from './pages/Checkout'
import ForSellersRegister from './pages/ForSellersRegister'
import ProductManagement from './pages/ProductManagement'
import PurchaseHistory from './pages/PurchaseHistory'
import ProductComparision from './pages/ProductComparision'
import ProductEditor from './pages/ProductEditor'

const noNavPaths = ["/login", "/register", "/register/for-sellers", "/notfound", "/seller"];

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

                <Route path={"/profile"} element={<UserProfile />} />

                <Route path={"/shop/products/:productId"} element={<ProductInfo />} />

                {["/shop", "/shop/products"].map((path, index) => {
                    return <Route key={index} path={path} element={<Shop />} />
                })}

                <Route path={"/cart"} element={<UserShoppingCart />} />
                <Route path={"/checkout"} element={<Checkout />} />
                <Route path={"/product-comparision"} element={<ProductComparision />} />

                <Route path={"/for-sellers"} element={<ProductManagement />} />
                <Route path={"/for-sellers/products"} element={<ProductManagement />} />
                <Route path={"/for-sellers/products/:productId"} element={<ProductEditor />} />

                <Route path={"/register/for-sellers"} element={<ForSellersRegister />} />
                <Route path={"/purchase-history"} element={<PurchaseHistory />} />
                <Route path={"/purchase-history/transactions"} element={<PurchaseHistory />} />
                <Route path={"/purchase-history/transactions/:cartId"} element={<TransactionDetails />} />

                <Route path={"/notfound"} element={<NotFound />} />
                <Route path={"*"} element={<NotFound />} />
            </Routes>
        </>
    )
}

export default App
