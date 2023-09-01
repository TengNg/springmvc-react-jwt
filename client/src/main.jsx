import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.jsx'
import { BrowserRouter } from 'react-router-dom'
import { AuthContextProvider } from './context/AuthContextProvider.jsx'
import { CartContextProvider } from './context/CartContextProvider.jsx'

ReactDOM.createRoot(document.getElementById('root')).render(
    <BrowserRouter>
        <AuthContextProvider>
            <CartContextProvider>
                <React.StrictMode>
                    <App />
                </React.StrictMode>,
            </CartContextProvider>
        </AuthContextProvider>
    </BrowserRouter>
)
