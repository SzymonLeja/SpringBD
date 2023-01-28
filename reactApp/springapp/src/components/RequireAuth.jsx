import { useLocation, Navigate, Outlet} from 'react-router-dom';
import useAuth from '../hooks/useAuth';

const RequireAuth = ({allowedType}) => {
    const { auth } = useAuth();
    const location = useLocation();

    const typeArray = Array.isArray(auth.type) ? auth.type : [auth.type];

    return (
        typeArray.find(typeA => allowedType?.includes(typeA))
        ? <Outlet />
        : auth?.username
        ? <Navigate to='/' state={{ from: location }}  replace/>
        : <Navigate to='/login' state={{ from: location }}  replace/>

    );
}

export default RequireAuth;