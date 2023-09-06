import useAuth from './useAuth';
import { axiosPrivate } from '../api/axios';

const useUserAccount = () => {
    const { setAuth } = useAuth();

    const getUserInformation = async () => {
        const response = await axiosWithInterceptors.get('/api/account/');
        const { user, accessToken } = response.data;
        setAuth({ username: user.username, userProfileImage: user.imageUrl, email: user.email, accessToken, userRole: user.userRole });
        return auth;
    }

    return getUserInformation;
};

export default useUserAccount;
