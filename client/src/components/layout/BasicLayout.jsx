import { useNavigate, useLocation } from 'react-router-dom';

const BasicLayout = ({ children, styles }) => {
    const navigate = useNavigate();
    const location = useLocation();
    const from = location.state?.from?.pathname || "/";

    return (
        <div className={styles}>
            <div className='w-[100px] h-[3rem] absolute left-[1rem] top-[0.75rem]'>
                <button
                    className='button--style button--hover'
                    onClick={() => navigate(from, { replace: true })}
                >Back</button>
            </div>
            {children}
        </div>
    )
}

export default BasicLayout;