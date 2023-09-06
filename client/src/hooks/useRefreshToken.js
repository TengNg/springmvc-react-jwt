import useAuth from './useAuth';
import { axiosPrivate } from '../api/axios';

const useRefreshToken = () => {
    const { setAuth } = useAuth();

    const refresh = async () => {
        const response = await axiosPrivate.get('/api/refresh/');
        setAuth(prev => {
            return { ...prev, accessToken: response.data.accessToken, userRole: response.data.userRole }
        });
        return response.data.accessToken;
    }

    return refresh;
};

export default useRefreshToken;
