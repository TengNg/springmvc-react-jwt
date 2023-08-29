import axios from "axios";

const BASE_URL = "http://localhost:8080/ecommerce";

export default axios.create({
    baseURL: `${BASE_URL}`,
    headers: { "Access-Control-Allow-Origin": "*" }
});

export const axiosTest = axios.create({
    baseURL: `${BASE_URL}/test`,
    headers: { "Access-Control-Allow-Origin": "*" }
});

export const axiosPrivate = axios.create({
    baseURL: `${BASE_URL}`,
    withCredentials: true,
    headers: {
        'Content-Type': 'multipart/form-data',
    },
});

