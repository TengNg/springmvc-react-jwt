import axios from "axios";

const BASE_URL = "http://localhost:8080/ecommerce";

export default axios.create({
    baseURL: `${BASE_URL}`,
});

export const axiosTest = axios.create({
    baseURL: `${BASE_URL}/test`,
});

export const axiosPrivate = axios.create({
    baseURL: `${BASE_URL}`,
    withCredentials: true,
});

