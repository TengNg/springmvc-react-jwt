import './App.css'
import { Routes, Route } from 'react-router-dom'
import Home from './pages/Home'
import Login from './pages/Login'
import Register from './pages/Register'
import Test from './pages/Test'
import Product from './components/Product'

function App() {
    return (
        <>
            <Routes>
                {["/", "/home", "/index"].map((path, index) => {
                    return (
                        <Route key={index} path={path} element={<Home />} />
                    )
                })}
                <Route path={"/test"} element={<Test />} />
                <Route path={"/login"} element={<Login />} />
                <Route path={"/register"} element={<Register />} />
                <Route path={"/test-page"} element={<Test />} />
                <Route path={"/product/:productId"} element={<Product />} />
            </Routes>
        </>
    )
}

export default App
