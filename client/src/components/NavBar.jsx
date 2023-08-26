import { useNavigate } from "react-router-dom"
import axios, { axiosPrivate, axiosTest } from "../api/axios";

const NavBar = () => {
    const navigate = useNavigate();

    const getData = async () => {
        try {
            const response = await axiosTest.get("/api/products/");
            console.log(response.data);

            const response2 = await axiosTest.get("/api/categories/");
            console.log(response2.data);
        } catch (err) {
            console.log(err);
        }
    };

    const test = async () => {
        try {
            const response = await axios.get("/test/");
            console.log(response);
        } catch (err) {
            console.log(err);
        }
    };

    return (
        <>
            <nav className='flex flex-col justify-center items-center h-fit gap-10'>
                <div className='w-[10rem] h-[5rem]'>
                    <button
                        className='button--style button--hover'
                        onClick={() => navigate('/login')}
                    >
                        Login
                    </button>
                </div>
                <div className='w-[10rem] h-[5rem]'>
                    <button
                        className='button--style button--hover'
                        onClick={() => getData()}
                    >
                        Get data
                    </button>
                </div>
                <div className='w-[10rem] h-[5rem]'>
                    <button
                        className='button--style button--hover'
                        onClick={() => test()}
                    >
                        Test
                    </button>
                </div>
            </nav>
        </>
    )
}

export default NavBar
