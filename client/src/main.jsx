import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.jsx'
import { BrowserRouter } from 'react-router-dom'
import { AuthContextProvider } from './context/AuthContextProvider.jsx'
import { CartContextProvider } from './context/CartContextProvider.jsx'
import { ComparisionContextProvider } from './context/ComparisionContextProvider.jsx'

ReactDOM.createRoot(document.getElementById('root')).render(
    <BrowserRouter>
        <AuthContextProvider>
            <CartContextProvider>
                <ComparisionContextProvider>
                    <React.StrictMode>
                        <App />
                    </React.StrictMode>
                </ComparisionContextProvider>
            </CartContextProvider>
        </AuthContextProvider>
    </BrowserRouter>
)
