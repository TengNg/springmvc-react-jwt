import React from "react";
import { useLocation, Navigate, Outlet } from "react-router-dom";
import useAuth from "../../hooks/useAuth";

const Auth = ({ allowedRole }) => {
    const { auth } = useAuth();

    const location = useLocation();

    return auth.userRole === allowedRole ? (
        <Outlet />
    ) : auth?.name ? (
        <Navigate to="/notfound" state={{ from: location }} replace />
    ) : (
        <Navigate to="/login" state={{ from: location }} replace />
    );
};

export default Auth;
