import './App.css'
import { Routes, Route } from 'react-router-dom'
import Home from './pages/Home'
import Login from './pages/Login'
import Register from './pages/Register'
import Products from './pages/Products'
import Test from './pages/Test'

function App() {
    return (
        <>
            <Routes>
                {["/", "/home", "/index"].map((path, index) => {
                    return (
                        <Route key={index} path={path} element={<Home/>} />
                    )
                })}
                <Route path={"/login"} element={<Login />} />
                <Route path={"/register"} element={<Register />} />
                <Route path={"/products"} element={<Products />} />
                <Route path={"/test-page"} element={<Test />} />
            </Routes>
        </>
    )
}

export default App
