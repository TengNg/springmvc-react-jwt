import axios from "axios";
import cookie from "react-cookies"

const BASE_URL = "http://localhost:8080/ecommerce";

export default axios.create({
    baseURL: `${BASE_URL}`,
});

export const axiosTest = axios.create({
    baseURL: `${BASE_URL}/test`,
});

export const authAxios = axios.create({
    baseURL: `${BASE_URL}`,
    headers: {
        'Authorization': cookie.load("token"),
    }
});

export const axiosPrivate = axios.create({
    baseURL: `${BASE_URL}`,
    withCredentials: true,
});

